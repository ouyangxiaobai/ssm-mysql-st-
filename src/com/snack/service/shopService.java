package com.snack.service;

import java.util.List;
import java.util.Map;

import com.snack.model.Snackinfo;
import com.snack.model.Userinfo;
import com.snack.model.domain.DoSnack;
import com.snack.utils.PageHelp;

public interface shopService {
	public List<Snackinfo> getHotSnack();
    public List<Snackinfo> getNewSnack();
    public Userinfo userLogin(Userinfo userinfo);
    public Snackinfo selectById(Snackinfo snackinfo);
    PageHelp<DoSnack> selectOrderSnack(Map<Object,Object> map);
    List<Snackinfo> selectPuffing(int type,String snackName);
}
