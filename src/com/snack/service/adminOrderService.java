package com.snack.service;

import java.util.List;
import java.util.Map;

import com.snack.model.Orderdetail;
import com.snack.model.Receiptinfo;
import com.snack.model.domain.DoAdminOrder;
import com.snack.model.domain.DoExcelOrder;
import com.snack.model.domain.DoSnack;
import com.snack.utils.PageHelp;

public interface adminOrderService {
	PageHelp<DoAdminOrder> selectAdminOrderLimit(Map<Object,Object> map);
	int deleteAdminOrder(String oId);
	Receiptinfo updateSelectAdminOrder(int rId);
	int updateAdminOrder(Receiptinfo receiptinfo);
	DoSnack updateSelectAdminOrderDetial(int dId);
	int updateOrderdetail(Orderdetail orderdetail);
	List<Orderdetail> selectOrderdetailByoId(String oId);
	int deleteOrderdetailBydId(int dId);
	List<DoExcelOrder> outExcelOrderDetail(String oId);
}
