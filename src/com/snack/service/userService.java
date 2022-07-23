package com.snack.service;

import java.util.Map;

import com.snack.model.Account;
import com.snack.model.Admin;
import com.snack.model.Integral;
import com.snack.model.Userinfo;
import com.snack.model.domain.DoAdminInfo;
import com.snack.model.domain.DoMyOrder;
import com.snack.utils.PageHelp;

public interface userService {
	public Userinfo selectUserOne(Userinfo userinfo);
	public int addUserOne(Userinfo userinfo);
	public Userinfo selectUserinfoById(Userinfo userinfo);
	public int updateUserinfoById(Userinfo userinfo);
	public PageHelp<Integral> selectIntegralLimit(Map<Object,Object> map);
	public PageHelp<Account>  selectAccountLimit(Map<Object,Object> map);
	public PageHelp<DoMyOrder>  selectOrderLimit(Map<Object,Object> map);
	public PageHelp<DoMyOrder> selectMySuccessOrderLimit(Map<Object,Object> map);
	public int upDateByorderConfirm(int id,int typeNum,int eId);
	Userinfo selectUserinfoMoney(int uId);
	int updateUserinfoBypay(int uId,float uMoney);
	int updateAdminInfo(DoAdminInfo doadminInfo);
	Admin selectAdminInfo(int adId);
}
