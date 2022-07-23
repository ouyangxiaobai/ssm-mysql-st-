package com.snack.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snack.model.Admin;
import com.snack.model.Userinfo;
import com.snack.model.domain.DoAdmin;
import com.snack.service.adminUserService;
import com.snack.utils.DataTables;
import com.snack.utils.PageHelp;
import com.snack.utils.ResponseUtil;

@Controller
@RequestMapping("/admin/user/")
public class AdminUserControll {
	
	@Autowired
	private adminUserService adminUserService;
	
	@RequestMapping("mainToUserinfo")
	public String mainToUserinfo(){
		return "admin/user/adminUserinfo";
	}
	
	//加载用户
	@RequestMapping("adminUserinfoLimit")
	@ResponseBody
	public DataTables adminUserinfoLimit(HttpServletRequest request,int start,int length,Userinfo userinfo){
		Map<Object,Object> map=new HashMap<Object, Object>();
		map.put("pageStart",start);
		map.put("pageSize",length);
		PageHelp<Userinfo> pageHelp= adminUserService.selectAdminUserinfoLimit(map,userinfo);
		DataTables datatable=new DataTables();
		datatable.setData(pageHelp.getList());
		datatable.setRecordsFiltered(pageHelp.getRecord());
		datatable.setRecordsTotal(pageHelp.getRecord());
		return datatable;
	}
	
	//删除用户
	@RequestMapping("delUserinfoById")
	@ResponseBody
	public String delUserinfoById(int uId){
		return ResponseUtil.successToClient(adminUserService.delUserinfoById(uId));
	}
	
	//更新加载用户
	@RequestMapping("updateGoUserinfoById")
	@ResponseBody
	public String updateGoUserinfoById(int uId){
		return ResponseUtil.successToClient(adminUserService.updateGoUserinfoById(uId));
	}
	
	//更新用户
	@RequestMapping("updateUserinfoById")
	@ResponseBody
	public String updateUserinfoById(Userinfo userinfo){
		return ResponseUtil.successToClient(adminUserService.updateUserinfoById(userinfo));
	}
	
	@RequestMapping("mainToAdmin")
	public String mainToAdmin(){
		return "admin/user/adminAdmin";
	}
	
	//加载用户
	@RequestMapping("adminAdminLimit")
	@ResponseBody
	public DataTables adminAdminLimit(HttpServletRequest request,int start,int length,DoAdmin doAdmin){
		Map<Object,Object> map=new HashMap<Object, Object>();
		map.put("pageStart",start);
		map.put("pageSize",length);
		PageHelp<Admin> pageHelp= adminUserService.selectAdminLimit(map,doAdmin);
		DataTables datatable=new DataTables();
		datatable.setData(pageHelp.getList());
		datatable.setRecordsFiltered(pageHelp.getRecord());
		datatable.setRecordsTotal(pageHelp.getRecord());
		return datatable;
	}
	
	//新建
	@RequestMapping("addNewAdmin")
	@ResponseBody
	public String addNewAdmin(Admin admin){
		return ResponseUtil.successToClient(adminUserService.addAdmin(admin));
	}
	
	//删除用户
	@RequestMapping("delAdminById")
	@ResponseBody
	public String delAdminById(Admin admin){
		return ResponseUtil.successToClient(adminUserService.delAdmin(admin));
	}
	
	//更新加载用户
	@RequestMapping("updateGoAdminById")
	@ResponseBody
	public String updateGoAdminById(HttpServletRequest request,Admin admin){
		return ResponseUtil.successToClient(adminUserService.selectAdminByOne(admin));
	}
	
	//更新用户
	@RequestMapping("updateAdminById")
	@ResponseBody
	public String updateAdminById(Admin admin){
		return ResponseUtil.successToClient(adminUserService.updateAdmin(admin));
	}

}
