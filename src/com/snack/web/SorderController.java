package com.snack.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snack.model.Order;
import com.snack.model.Orderdetail;
import com.snack.model.Receiptinfo;
import com.snack.model.Snackinfo;
import com.snack.model.Userinfo;
import com.snack.model.domain.DoMoneyFK;
import com.snack.service.sorderService;
import com.snack.service.userService;
import com.snack.utils.DateUtil;
import com.snack.utils.ResponseUtil;

@Controller
@RequestMapping("/sorder")
public class SorderController {

	@Autowired
	private sorderService sorderService=null;
	
	@Autowired
	private userService userService;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addSorder(Snackinfo snackinfo,HttpSession session) {
			
			System.out.println("-------addCar()------"+snackinfo.getsNumber());
			
			// 1:通过 product.id获取当前的商品数据
			Snackinfo findSnackinfo = sorderService.snackfindById(snackinfo.getsId());
			
			findSnackinfo.setsNumber(snackinfo.getsNumber());
			
			//2:判断当前session是否有购物车，如果没有则创建
			if (session.getAttribute("receiptinfo")==null) {
				
				//创建购物车，存到session中
				session.setAttribute("receiptinfo", new Receiptinfo(new HashSet<Orderdetail>()));
			}
			Receiptinfo receiptinfo = (Receiptinfo)session.getAttribute("receiptinfo");
			
			//3:把商品信息转化为sorder,并且添加到购物车中(判断购物车是否重复)
			receiptinfo = sorderService.addSorder(receiptinfo, findSnackinfo);
			receiptinfo.setoPhone(cluTotal(receiptinfo));
			return "redirect:gotocar";
		}
	
	
	    @RequestMapping("gotocar")
	    public String gotocar() {
			return "product/car";
		}
	
	  	//计算总金额
		public String cluTotal(Receiptinfo receiptinfo) {
				
				double tal=0.0;
				for(Orderdetail temp : receiptinfo.getOrderdetailSet()){
					
					tal+=temp.getoMoney()*temp.getoNum();
				}
				
				return String.valueOf(tal);
		}
		
		
		@RequestMapping(value="/delete",method=RequestMethod.GET)
		public String deleteSorder(Snackinfo snackinfo,HttpSession session) {
			
			Receiptinfo receiptinfo = (Receiptinfo)session.getAttribute("receiptinfo");
			
			Set<Orderdetail> set = receiptinfo.getOrderdetailSet();
			
			
			 Iterator<Orderdetail> iterator = set.iterator();
			
			 while(iterator.hasNext()){
				 Orderdetail orderdetail = iterator.next();
				 if(orderdetail.getSnackinfo().getsId()==snackinfo.getsId()){
					iterator.remove();
					receiptinfo.setoPhone(String.valueOf((Double.parseDouble(receiptinfo.getoPhone())-orderdetail.getoMoney()*orderdetail.getoNum())));	 
				}
		     }
			 if( set.size() <= 0){
				 session.removeAttribute("receiptinfo");
			 }
			return "redirect:gotocar";
		}
		
		@RequestMapping(value="/clear",method=RequestMethod.GET)
		public String clearSorder(Snackinfo snackinfo,HttpSession session) {
			Receiptinfo receiptinfo = (Receiptinfo)session.getAttribute("receiptinfo");
			if(receiptinfo!=null){
				Set<Orderdetail> set = receiptinfo.getOrderdetailSet();
				set.clear();
			}
			receiptinfo.setoPhone(String.valueOf(0d));
			 session.removeAttribute("receiptinfo");
			return "redirect:gotocar";
		}
		
		
		//下订单
		@RequestMapping("setOrder")
		@ResponseBody
		public String setOrder(HttpServletRequest request,HttpSession session,Receiptinfo receiptinfo) {
			DoMoneyFK dfk=new DoMoneyFK();
			Receiptinfo receiptinfoSession = (Receiptinfo)session.getAttribute("receiptinfo");
			Date day=new Date();    
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd"); 
			String oId=df.format(day).toString()+String.valueOf((int)(Math.random()*9000+1000));
			receiptinfo.setoId(oId);
			receiptinfo.setOrderdetailSet(receiptinfoSession.getOrderdetailSet());
			sorderService.addReceiptinfo(receiptinfo);
			
			Order order=new Order();
			order.setoId(oId);
			order.setuId((int)session.getAttribute("frontuserId"));
			order.setoTime(DateUtil.getCurrentTime());
			order.setoType(-1);
			sorderService.addOrder(order);
			
			Orderdetail orderdetail=new Orderdetail();
			orderdetail.setoId(oId);
			for (Orderdetail orderdetailSession : receiptinfoSession.getOrderdetailSet()) {
				orderdetail.setoNum(orderdetailSession.getoNum());
				orderdetail.setoMoney(orderdetailSession.getoMoney());
				orderdetail.setsId(orderdetailSession.getsId());
				sorderService.addOrderdetail(orderdetail);
			}
			Userinfo selectUserinfoMoney = userService.selectUserinfoMoney((int)request.getSession().getAttribute("frontuserId"));
			Double selectOrderdetailPayMoney = sorderService.selectOrderdetailPayMoney(oId);
			dfk.setoId(oId);
			dfk.setPayMoney(selectOrderdetailPayMoney.toString());
			dfk.setuMoney(selectUserinfoMoney.getuMoney());
			session.removeAttribute("receiptinfo");
			return ResponseUtil.successToClientStr(dfk);
		}
}
