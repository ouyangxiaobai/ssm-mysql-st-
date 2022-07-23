package com.snack.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snack.mapper.AdminMapper;
import com.snack.mapper.UserinfoMapper;
import com.snack.model.Admin;
import com.snack.model.Userinfo;
import com.snack.model.UserinfoExample;
import com.snack.model.UserinfoExample.Criteria;
import com.snack.model.domain.DoAdmin;
import com.snack.service.adminUserService;
import com.snack.utils.PageHelp;

@Service
public class adminUserServiceImpl implements adminUserService {

	@Autowired
	private UserinfoMapper userinfoDao;
	
	@Autowired
	private AdminMapper adminDao;
	
	@Override
	public PageHelp<Userinfo> selectAdminUserinfoLimit(Map<Object, Object> map,Userinfo userinfo) {
		map.put("userinfo", userinfo);
		PageHelp<Userinfo> pageUtil=new PageHelp<Userinfo>();
		List<Userinfo> selectUserinfoLimit = userinfoDao.selectUserinfoLimit(map);
		UserinfoExample ue=new UserinfoExample();
		Criteria cr = ue.createCriteria();
		if(""!=userinfo.getuUsername() && null!=userinfo.getuUsername()){
		cr.andUUsernameEqualTo(userinfo.getuUsername());
		}
		if(""!=userinfo.getuPhone() && null!=userinfo.getuPhone()){
		cr.andUPhoneEqualTo(userinfo.getuPhone());
		}
		int countByExample = userinfoDao.countByExample(ue);
		pageUtil.setList(selectUserinfoLimit);
		pageUtil.setRecord(countByExample);
		return pageUtil;
	}

	@Override
	public int delUserinfoById(int uId) {
		return userinfoDao.deleteByPrimaryKey(uId);
	}

	@Override
	public Userinfo updateGoUserinfoById(int uId) {
		return userinfoDao.selectByPrimaryKey(uId);
	}

	@Override
	public int updateUserinfoById(Userinfo userinfo) {
		return userinfoDao.updateByPrimaryKeySelective(userinfo);
	}

	@Override
	public PageHelp<Admin> selectAdminLimit(Map<Object, Object> map,
			DoAdmin doAdmin) {
		map.put("admin", doAdmin);
		PageHelp<Admin> pageUtil=new PageHelp<Admin>();
		List<Admin> selectUserinfoLimit = adminDao.selectAdminLimit(map);
		int countAdminLimit = adminDao.countAdminLimit(map);
		pageUtil.setList(selectUserinfoLimit);
		pageUtil.setRecord(countAdminLimit);
		return pageUtil;
	}

	@Override
	public int addAdmin(Admin admin) {
		return adminDao.insertSelective(admin);
	}

	@Override
	public int delAdmin(Admin admin) {
		return adminDao.deleteByPrimaryKey(admin.getAdId());
	}

	@Override
	public DoAdmin selectAdminByOne(Admin admin) {
		return adminDao.selectAdminByOne(admin.getAdId());
	}

	@Override
	public int updateAdmin(Admin admin) {
		return adminDao.updateByPrimaryKeySelective(admin);
	}

}
