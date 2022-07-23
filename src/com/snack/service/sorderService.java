package com.snack.service;

import com.snack.model.Order;
import com.snack.model.Orderdetail;
import com.snack.model.Receiptinfo;
import com.snack.model.Snackinfo;


public interface sorderService {

	
	public Snackinfo snackfindById(Integer sId);
	
	//计算购物总价格
	public Receiptinfo addSorder(Receiptinfo receiptinfo,Snackinfo snackinfo);
	
	//商品转化成订单项
	public Orderdetail snackinfoToOrder(Snackinfo snackinfo);
	
	//添加收货信息
	public int addReceiptinfo(Receiptinfo receiptinfo);
	
	//添加订单信息
	public int addOrder(Order order);
	
	//添加订单商品
	public int addOrderdetail(Orderdetail orderdetail);
	
	//支付金额
	Double selectOrderdetailPayMoney(String oId);
	
	//未付款到已付款
	int updateOrderByoId(String oId);
}
