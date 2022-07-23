package com.snack.mapper;

import com.snack.model.Snackinfo;
import com.snack.model.SnackinfoExample;
import com.snack.model.domain.DoSnack;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SnackinfoMapper {
    int countByExample(SnackinfoExample example);

    int deleteByExample(SnackinfoExample example);

    int deleteByPrimaryKey(Integer sId);

    int insert(Snackinfo record);

    int insertSelective(Snackinfo record);

    List<Snackinfo> selectByExample(SnackinfoExample example);

    Snackinfo selectByPrimaryKey(Integer sId);

    int updateByExampleSelective(@Param("record") Snackinfo record, @Param("example") SnackinfoExample example);

    int updateByExample(@Param("record") Snackinfo record, @Param("example") SnackinfoExample example);

    int updateByPrimaryKeySelective(Snackinfo record);

    int updateByPrimaryKey(Snackinfo record);
    
    //查询热销商品
    List<Snackinfo> selectHot(Map<String,String> map);
    
    //查询最新商品
    List<Snackinfo> selectNew();
    
    //查询膨化类
    List<Snackinfo> selectPuffing(Map<String,String> map);
    
    List<DoSnack> selectOrderSnack(Map<Object,Object> map);
    
    List<Snackinfo> selectAdminSnackLimit(Map<Object,Object> map);
    
    int selectAdminSnackCount(Map<Object,Object> map);
}