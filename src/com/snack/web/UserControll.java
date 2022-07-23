package com.snack.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snack.model.Account;
import com.snack.model.Integral;
import com.snack.model.Userinfo;
import com.snack.model.domain.DoAdminInfo;
import com.snack.model.domain.DoMyOrder;
import com.snack.model.domain.DoPayMoney;
import com.snack.service.adminOrderService;
import com.snack.service.sorderService;
import com.snack.service.userService;
import com.snack.utils.AjaxResult;
import com.snack.utils.DataTables;
import com.snack.utils.PageHelp;
import com.snack.utils.ResponseUtil;

@Controller
@RequestMapping("/user/")
public class UserControll {
	
	@Autowired
	private userService userService;
	
	@Autowired
	private sorderService sorderService;
	
	@Autowired
	private adminOrderService adminOrderService;
	
	//跳转注册
	@RequestMapping("userRegisterGet")
	public String gotoRegister(){
		return "user/register";
	}	
	
	//注册判断用户名存在
	@RequestMapping("selectUserOne")
	@ResponseBody
	public AjaxResult getUserOne(Userinfo userinfo){
		AjaxResult aj=new AjaxResult();
		 Userinfo rs = userService.selectUserOne(userinfo);
		 aj.setTag(rs);
		 return aj;
	}
	
	//注册
	@RequestMapping("addUserRegister")
	@ResponseBody
	public AjaxResult addUserOne(Userinfo userinfo){
		 AjaxResult aj=new AjaxResult();
		 int rs = userService.addUserOne(userinfo);
		 aj.setTag(rs);
		 return aj;
	}
	
	//查登陆的用户
	@RequestMapping("readUserinfo")
	@ResponseBody
	public AjaxResult readUserinfo(Userinfo userinfo,HttpServletRequest request){
		 AjaxResult aj=new AjaxResult();
		 Userinfo usr= userService.selectUserinfoById(userinfo);
		 if(usr!=null){
			 aj.setTag(usr);
		 }
		 return aj;
	}
	
	//修改个人信息
	@RequestMapping("editUserinfo")
	@ResponseBody
	public int editUserinfo(Userinfo userinfo){
		 int rs= userService.updateUserinfoById(userinfo);
		 return rs;
	}
	
	//跳转注册
	@RequestMapping("userPersonalInfo")
	public String gotoPersonalInfo(){
		return "user/personalInfo";
	}
	
	//加载个人信息界面
	@RequestMapping("userInfo")
	public String gotoUserInfo(){
		return "user/userInfo";
	}
	
	//跳转个人积分页面
	@RequestMapping("userIntegral")
	public String userIntegral(){
		return "user/integral";
	}
	
	//跳转个人图片
	@RequestMapping("userGerenImg")
	public String userGerenImg(){
		return "user/gerenImg";
	}
	
	//加载个人积分
	@RequestMapping("userIntegralLimit")
	@ResponseBody
	public DataTables userIntegralLimit(HttpServletRequest request,int start,int length){
		Map<Object,Object> map=new HashMap<Object, Object>();
		map.put("uId",request.getSession().getAttribute("frontuserId"));
		map.put("pageStart",start);
		map.put("pageSize",length);
		PageHelp<Integral> pageHelp= userService.selectIntegralLimit(map);
		DataTables datatable=new DataTables();
		datatable.setData(pageHelp.getList());
		datatable.setRecordsFiltered(pageHelp.getRecord());
		datatable.setRecordsTotal(pageHelp.getRecord());
		return datatable;
	}
	
	//跳转个人账户页面
	@RequestMapping("userAccount")
	public String userAccount(){
		return "user/account";
	}
	
	//加载个人积分
	@RequestMapping("userAccountLimit")
	@ResponseBody
	public DataTables userAccountLimit(HttpServletRequest request,int start,int length){
		Map<Object,Object> map=new HashMap<Object, Object>();
		map.put("uId",request.getSession().getAttribute("frontuserId"));
		map.put("pageStart",start);
		map.put("pageSize",length);
		PageHelp<Account> pageHelp= userService.selectAccountLimit(map);
		DataTables datatable=new DataTables();
		datatable.setData(pageHelp.getList());
		datatable.setRecordsFiltered(pageHelp.getRecord());
		datatable.setRecordsTotal(pageHelp.getRecord());
		return datatable;
	}
	
	//获得金额
	@RequestMapping("getMoneyAcount")
	@ResponseBody
	public Object getMoneyAcount(HttpServletRequest request){
		return request.getSession().getAttribute("money");
	}
	
	//查看当前订单
	@RequestMapping("userOrderLimit")
	@ResponseBody
	public DataTables userOrderLimit(HttpServletRequest request,int start,int length){
		Map<Object,Object> map=new HashMap<Object, Object>();
		map.put("uId",request.getSession().getAttribute("frontuserId"));
		map.put("pageStart",start);
		map.put("pageSize",length);
		PageHelp<DoMyOrder> pageHelp= userService.selectOrderLimit(map);
		DataTables datatable=new DataTables();
		datatable.setData(pageHelp.getList());
		datatable.setRecordsFiltered(pageHelp.getRecord());
		datatable.setRecordsTotal(pageHelp.getRecord());
		return datatable;
	}
	
	//跳转订单页面
	@RequestMapping("userGoOrder")
	public String userGoOrder(HttpSession session){
		return "user/myOrder";
	}
	
	//跳转历史订单
	@RequestMapping("userGoSuccessOrder")
	public String userGoSuccessOrder(){
		return "user/myOrderSuccess";
	}
	
	//查看当前订单
	@RequestMapping("userSucOrderLimit")
	@ResponseBody
	public DataTables userSucOrderLimit(HttpServletRequest request,int start,int length){
		Map<Object,Object> map=new HashMap<Object, Object>();
		map.put("uId",request.getSession().getAttribute("frontuserId"));
		map.put("pageStart",start);
		map.put("pageSize",length);
		PageHelp<DoMyOrder> pageHelp= userService.selectMySuccessOrderLimit(map);
		DataTables datatable=new DataTables();
		datatable.setData(pageHelp.getList());
		datatable.setRecordsFiltered(pageHelp.getRecord());
		datatable.setRecordsTotal(pageHelp.getRecord());
		return datatable;
	}
	
	//请确认收货
	@RequestMapping("userOrderConfirm")
	@ResponseBody
	public String userOrderConfirm(HttpServletRequest request,int id,int typeNum){
		int eId;
		if(typeNum==0){
		    eId = (int)request.getSession().getAttribute("adminId");
		}else{
			eId=0;
		}
	return ResponseUtil.successToClient(userService.upDateByorderConfirm(id,typeNum,eId));
	}
	
	//去付款
	@RequestMapping("userOrderPayMoney")
	@ResponseBody
	public String userOrderPayMoney(HttpServletRequest request,String oId){
		DoPayMoney dpm=new DoPayMoney();
		Userinfo selectUserinfoMoney = userService.selectUserinfoMoney((int)request.getSession().getAttribute("frontuserId"));
		Double selectOrderdetailPayMoney = sorderService.selectOrderdetailPayMoney(oId);
		dpm.setPayMoney(selectOrderdetailPayMoney.toString());
		dpm.setuMoney(selectUserinfoMoney.getuMoney());
	return ResponseUtil.successToClient(dpm);
	}
	
	//确认付款
	@RequestMapping("userOrderPayMoneyConfirm")
	@ResponseBody
	public String userOrderPayMoneyConfirm(HttpServletRequest request,Float payNumber,Float uMoney,String uPasword,String oId){
		Userinfo userinfo = (Userinfo)request.getSession().getAttribute("exituser");
		if(userinfo.getuPassword().equals(uPasword)){
			sorderService.updateOrderByoId(oId);
			userService.updateUserinfoBypay(userinfo.getuId(), uMoney-payNumber);
		}else{
			return ResponseUtil.errorToClient();
		}
	   return ResponseUtil.successToClient();
	}
	
	//删除订单
		@RequestMapping("deleteMyOrder")
		@ResponseBody
		public String deleteAdminOrder(String oId){
			return ResponseUtil.successToClient(adminOrderService.deleteAdminOrder(oId));
		} 
	
	//跳转管理员信息
	@RequestMapping("mainToAdminuser")
	public String mainToAdminuser(){
		return "admin/persionInfo/adminInfo";
	}
	//加载管理员信息
	@RequestMapping("mainSelectAdminuser")
	@ResponseBody
	public String mainSelectAdminuser(HttpServletRequest request){
		int adId =(int)request.getSession().getAttribute("adminId");
		return ResponseUtil.successToClient(userService.selectAdminInfo(adId));
	}
	
	//更新管理员信息
	@RequestMapping("mainUpdateAdminuser")
	@ResponseBody
	public String mainUpdateAdminuser(DoAdminInfo admin){
		return ResponseUtil.successToClient(userService.updateAdminInfo(admin));
	}
}
