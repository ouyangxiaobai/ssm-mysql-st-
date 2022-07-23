package com.snack.mapper;

import com.snack.model.Order;
import com.snack.model.OrderExample;
import com.snack.model.Orderdetail;
import com.snack.model.domain.DoAdminOrder;
import com.snack.model.domain.DoMyOrder;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    List<DoMyOrder> selectMyOrderLimit(Map<Object,Object> map);
    
    List<DoMyOrder> selectMySuccessOrderLimit(Map<Object,Object> map);
    
    List<DoAdminOrder> selectAdminOrderLimit(Map<Object,Object> map);
    
    int countAdminOrder(Map<Object,Object> map);
    
    int deleteAdminOrder(String oId);
    
    int updateOrderByoId(String oId);
    
    List<Orderdetail> selectPushSnakcNum(int id);
}