package com.snack.service;

import java.util.Map;

import com.snack.model.Admin;
import com.snack.model.Userinfo;
import com.snack.model.domain.DoAdmin;
import com.snack.utils.PageHelp;

public interface adminUserService {

	 PageHelp<Userinfo> selectAdminUserinfoLimit(Map<Object,Object> map,Userinfo userinfo);
	
	 int delUserinfoById(int uId);
	 
	 Userinfo updateGoUserinfoById(int uId);
	 
	 int updateUserinfoById(Userinfo userinfo);
	 
	 PageHelp<Admin> selectAdminLimit(Map<Object,Object> map,DoAdmin doAdmin);
	 
	 int addAdmin(Admin admin);
	 
	 int delAdmin(Admin admin);
	 
	 DoAdmin selectAdminByOne(Admin admin);
	 
	 int updateAdmin(Admin admin);
}
