package com.snack.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snack.mapper.AdminMapper;
import com.snack.mapper.AdminMenuChildMapper;
import com.snack.mapper.AdminMenuMapper;
import com.snack.mapper.PermissionMapper;
import com.snack.model.Admin;
import com.snack.model.AdminExample;
import com.snack.model.AdminExample.Criteria;
import com.snack.model.AdminMenu;
import com.snack.model.AdminMenuChild;
import com.snack.model.Permission;
import com.snack.service.adminLoginService;

@Service
public class adminLoginServiceImpl implements adminLoginService{
	
	@Autowired
	private AdminMapper adminDao;
	
	@Autowired
	private AdminMenuMapper AdminMenuDao;
	
	@Autowired
	private AdminMenuChildMapper AdminMenuChildDao;
	
	@Autowired
	private PermissionMapper permissionDao;

	@Override
	public List<Admin> adminLogin(Admin admin) {
		AdminExample ae=new AdminExample();
		Criteria cr = ae.createCriteria();
		cr.andAdUsernameEqualTo(admin.getAdUsername());
		cr.andAdPasswordEqualTo(admin.getAdPassword());
		return adminDao.selectByExample(ae);
	}

	@Override
	public List<AdminMenu> selectAllAdminMenu() {
		return AdminMenuDao.selectByExample(null);
	}

	@Override
	public List<AdminMenuChild> selectAllAdminMenuChild() {
		return AdminMenuChildDao.selectByExample(null);
	}

	@Override
	public List<Permission> selectAllPermission() {
		return permissionDao.selectByExample(null);
	}

	@Override
	public List<Permission> selectRolePermission(int roleId) {
		return permissionDao.selectRolePermission(roleId);
	}

}
