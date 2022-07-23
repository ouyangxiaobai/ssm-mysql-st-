package com.snack.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snack.mapper.AccountMapper;
import com.snack.model.Admin;
import com.snack.model.Permission;
import com.snack.service.adminLoginService;
import com.snack.utils.ResponseUtil;

@Controller
@RequestMapping("/admin/login/")
public class AdminLogin {
	
	@Autowired
	private adminLoginService adminLoginService;
	
	@Autowired
	private AccountMapper accountMapper;
	
	//登录login
	@RequestMapping("adminLogin")
	public String adminLogin(){
		System.out.println("存储过程相加:"+accountMapper.selectNum(4, 6));
		return "admin/adminLogin";
	}
	
	//登录
	@RequestMapping("adminGoLogin")
	public String adminGoLogin(HttpServletRequest request,Admin admin){
		List<Admin> adminSelect = adminLoginService.adminLogin(admin);
		if(adminSelect.size()==1){
			request.getSession().setAttribute("adminName", adminSelect.get(0).getAdUsername());
			request.getSession().setAttribute("adminId", adminSelect.get(0).getAdId());
			request.getSession().setAttribute("roleId", adminSelect.get(0).getRoleId());
			List<Permission> allPer = adminLoginService.selectAllPermission();
			List<Permission> userPer = adminLoginService.selectRolePermission(adminSelect.get(0).getRoleId());
			request.getSession().setAttribute("allPer", allPer);
			request.getSession().setAttribute("userPer", userPer);
			return "redirect:adminGoMain";
		}
		request.setAttribute("msg","用户名或密码错误");
		return "admin/adminLogin";
	}
	
	//登录Main
	@RequestMapping("adminGoMain")
	public String adminLogin(HttpServletRequest request){
		request.setAttribute("adminMenu",adminLoginService.selectAllAdminMenu());
		request.setAttribute("adminMenuChild",adminLoginService.selectAllAdminMenuChild());
		return "admin/adminMain";
	}
	
	//退出
	@RequestMapping("adminMainToLoginOut")
	@ResponseBody
	public String adminMainToLoginOut(HttpServletRequest request){
		request.getSession().removeAttribute("adminName");
		return ResponseUtil.successToClient();
	}
	

}
