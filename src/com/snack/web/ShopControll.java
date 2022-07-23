package com.snack.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snack.model.Snackinfo;
import com.snack.model.Userinfo;
import com.snack.model.domain.DoSnack;
import com.snack.model.domain.DoSnackType;
import com.snack.service.shopService;
import com.snack.utils.DataTables;
import com.snack.utils.PageHelp;
import com.snack.utils.TypeUtil;

@Controller
@RequestMapping("/shop/")
public class ShopControll {
	
	@Autowired
	public shopService shopService=null;
	
	//主页
	@RequestMapping("index")
	public String gotoIndex(HttpServletRequest request){
		List<Snackinfo> HotSnackList = shopService.getHotSnack();
		List<Snackinfo> newSnackList = shopService.getNewSnack();
		request.setAttribute("HotSnackList",HotSnackList);
		request.setAttribute("newSnackList",newSnackList);
		System.out.println("00");
		return "user/index";
	}
	
	//登录
	@RequestMapping("userLoginGet")
	public String gotoUserLogin(){
		return "user/userLogin";
	}
	
	@RequestMapping("userLoginPost")
	public String gotoUserLogin(Userinfo userinfo,HttpServletRequest request){
		Userinfo exituser =  shopService.userLogin(userinfo);
		if(exituser ==null){
			request.setAttribute("msg","用户名或密码错误");
			return "user/userLogin";
		}
		request.getSession().setAttribute("exituser", exituser);
		request.getSession().setAttribute("frontuser", exituser.getuUsername());
		request.getSession().setAttribute("frontuserId", exituser.getuId());
		request.getSession().setAttribute("money", exituser.getuMoney());
		return "redirect:index";
	}
	
	//退出登录
	@RequestMapping("userlogout")
	public String showlogin(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("frontuser");
		session.removeAttribute("frontuserId");
		return "redirect:userLoginGet";
	}
	
	
	//商品详情
	@RequestMapping("proDatail")
	public String gotoProDatail(HttpServletRequest request,Snackinfo snack){
		Snackinfo sck = shopService.selectById(snack);
		request.setAttribute("sck", sck);
		return "product/proDetail";
	}
	
	//查询订单
	@RequestMapping("selectOrderSnack")
	@ResponseBody
	public DataTables selectOrderSnack(String oId,int start,int length){
		Map<Object,Object> map=new HashMap<Object, Object>();
		map.put("oId", oId);
		map.put("pageStart",start);
		map.put("pageSize",length);
		PageHelp<DoSnack> pageHelp = shopService.selectOrderSnack(map);
		DataTables datatable=new DataTables();
		datatable.setData(pageHelp.getList());
		datatable.setRecordsFiltered(pageHelp.getRecord());
		datatable.setRecordsTotal(pageHelp.getRecord());
		return datatable;
	}
	
	//type页面
	@RequestMapping("puffingType")
	public String puffing(HttpServletRequest request,DoSnackType doSnackType){
		List<Snackinfo> TypeSnackList=shopService.selectPuffing(doSnackType.getType(),doSnackType.getSnackName());
		Map<Integer, String> allType = TypeUtil.getAllType();
		String typeString = allType.get(doSnackType.getType());
		if(typeString == null || "".equals(typeString)){
			typeString = "搜索结果";
		}
		if(doSnackType.getType() == 666){
			typeString = "进口类";
		}
		request.setAttribute("TypeSnackTitle",typeString);
		
		request.setAttribute("TypeNum",doSnackType.getType());
		request.setAttribute("TypeSnackList",TypeSnackList);
		return "user/typePage";
	}
	
	/**
	 * 获取商品分类
	 * @return
	 */
	@RequestMapping("get_type")
	@ResponseBody
	public Map<String, Object> getType(){
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("type", "success");
		ret.put("content", TypeUtil.getAllDoType());
		return ret;
	}
}
