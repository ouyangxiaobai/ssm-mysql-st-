package com.snack.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snack.mapper.SnackinfoMapper;
import com.snack.model.Snackinfo;
import com.snack.service.adminSnackInfoService;
import com.snack.utils.PageHelp;

@Service
public class adminSnackinfoServiceImpl implements adminSnackInfoService{
	@Autowired
	private SnackinfoMapper snackinfoDao;
	
	@Override
	public PageHelp<Snackinfo> selectAdminSnackinfoLimit(Map<Object, Object> map) {
		PageHelp<Snackinfo> pageUtil=new PageHelp<Snackinfo>();
		List<Snackinfo> selectAdminSnackLimit = snackinfoDao.selectAdminSnackLimit(map);
		int selectAdminSnackCount = snackinfoDao.selectAdminSnackCount(map);
		pageUtil.setList(selectAdminSnackLimit);
		pageUtil.setRecord(selectAdminSnackCount);
		return pageUtil;
	}

	@Override
	public int addAdminSnackinfo(Snackinfo snackinfo) {
		return snackinfoDao.insertSelective(snackinfo);
	}

	@Override
	public int delAdminSnackinfo(int sId) {
		return snackinfoDao.deleteByPrimaryKey(sId);
	}

	@Override
	public Snackinfo selectAdminUserinfoBysId(int sId) {
		return snackinfoDao.selectByPrimaryKey(sId);
	}

	@Override
	public int updateAdminSnackinfo(Snackinfo snackinfo) {
		return snackinfoDao.updateByPrimaryKeySelective(snackinfo);
	}

}
