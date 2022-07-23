package com.snack.mapper;

import com.snack.model.Orderdetail;
import com.snack.model.OrderdetailExample;
import com.snack.model.domain.DoExcelOrder;
import com.snack.model.domain.DoSnack;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrderdetailMapper {
    int countByExample(OrderdetailExample example);

    int deleteByExample(OrderdetailExample example);

    int deleteByPrimaryKey(Integer dId);

    int insert(Orderdetail record);

    int insertSelective(Orderdetail record);

    List<Orderdetail> selectByExample(OrderdetailExample example);

    Orderdetail selectByPrimaryKey(Integer dId);

    int updateByExampleSelective(@Param("record") Orderdetail record, @Param("example") OrderdetailExample example);

    int updateByExample(@Param("record") Orderdetail record, @Param("example") OrderdetailExample example);

    int updateByPrimaryKeySelective(Orderdetail record);

    int updateByPrimaryKey(Orderdetail record);
    
    DoSnack selectOrderdetail(int dId);
    
    List<Orderdetail> selectOrderdetailByoId(String oId);
    
    Double selectOrderdetailPayMoney(String oId);
    
    List<DoExcelOrder> outExcelOrderDetail(String oId);
}