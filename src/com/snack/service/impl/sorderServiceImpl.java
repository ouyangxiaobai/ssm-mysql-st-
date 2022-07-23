package com.snack.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snack.mapper.OrderMapper;
import com.snack.mapper.OrderdetailMapper;
import com.snack.mapper.ReceiptinfoMapper;
import com.snack.mapper.SnackinfoMapper;
import com.snack.model.Order;
import com.snack.model.Orderdetail;
import com.snack.model.Receiptinfo;
import com.snack.model.Snackinfo;
import com.snack.service.sorderService;

@Service
public class sorderServiceImpl implements sorderService{

	@Autowired
	private SnackinfoMapper snackinfoDao;
	
	@Autowired
	private ReceiptinfoMapper receiptinfoDao;
	
	@Autowired
	private OrderMapper orderDao;
	
	@Autowired
	private OrderdetailMapper orderdetailDao;
	
	@Override
	public Snackinfo snackfindById(Integer sId) {
		return snackinfoDao.selectByPrimaryKey(sId);
	}


	@Override
	public Receiptinfo addSorder(Receiptinfo receiptinfo, Snackinfo snackinfo) {
		boolean isHave = false;
		Orderdetail orderdetail = snackinfoToOrder(snackinfo);
		//判断当前购物项是否重复，如果重复则添加数量即可
		for (Orderdetail oldOrderdetail : receiptinfo.getOrderdetailSet()) {
			if(oldOrderdetail.getSnackinfo().getsId().equals(orderdetail.getSnackinfo().getsId())){
				oldOrderdetail.setoNum(oldOrderdetail.getoNum() + orderdetail.getoNum());
				oldOrderdetail.setoMoney(orderdetail.getoMoney());
				isHave = true;
				break;
			}
		}
		//说明当前购物项  在购物车中是不是存在
		if(!isHave){
			receiptinfo.getOrderdetailSet().add(orderdetail);
		}
		
		return receiptinfo;
	}


	@Override
	public Orderdetail snackinfoToOrder(Snackinfo snackinfo) {
		Orderdetail orderdetail = new Orderdetail();
		orderdetail.setsName(snackinfo.getsName());
		orderdetail.setoNum(snackinfo.getsNumber());
		orderdetail.setoMoney(snackinfo.getsPrice()*Double.parseDouble(snackinfo.getsDiscount())*0.1);
		orderdetail.setSnackinfo(snackinfo);
		orderdetail.setsId(snackinfo.getsId());
		return orderdetail;
	}


	@Override
	public int addReceiptinfo(Receiptinfo receiptinfo) {
		return  receiptinfoDao.insertSelective(receiptinfo);
	}


	@Override
	public int addOrder(Order order) {
		return orderDao.insertSelective(order);
	}


	@Override
	public int addOrderdetail(Orderdetail orderdetail) {
		return orderdetailDao.insertSelective(orderdetail);
	}


	@Override
	public Double selectOrderdetailPayMoney(String oId) {
		return orderdetailDao.selectOrderdetailPayMoney(oId);
	}


	@Override
	public int updateOrderByoId(String oId) {
		return orderDao.updateOrderByoId(oId);
	}

}
