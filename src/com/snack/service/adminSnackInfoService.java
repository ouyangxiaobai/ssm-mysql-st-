package com.snack.service;

import java.util.Map;

import com.snack.model.Snackinfo;
import com.snack.utils.PageHelp;

public interface adminSnackInfoService {
	PageHelp<Snackinfo> selectAdminSnackinfoLimit(Map<Object,Object> map);
	int addAdminSnackinfo(Snackinfo snackinfo);
	int delAdminSnackinfo(int sId);
	Snackinfo selectAdminUserinfoBysId(int sId);
	int updateAdminSnackinfo(Snackinfo snackinfo);
}
