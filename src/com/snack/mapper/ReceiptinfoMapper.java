package com.snack.mapper;

import com.snack.model.Receiptinfo;
import com.snack.model.ReceiptinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReceiptinfoMapper {
    int countByExample(ReceiptinfoExample example);

    int deleteByExample(ReceiptinfoExample example);

    int deleteByPrimaryKey(Integer rId);

    int insert(Receiptinfo record);

    int insertSelective(Receiptinfo record);

    List<Receiptinfo> selectByExample(ReceiptinfoExample example);

    Receiptinfo selectByPrimaryKey(Integer rId);

    int updateByExampleSelective(@Param("record") Receiptinfo record, @Param("example") ReceiptinfoExample example);

    int updateByExample(@Param("record") Receiptinfo record, @Param("example") ReceiptinfoExample example);

    int updateByPrimaryKeySelective(Receiptinfo record);

    int updateByPrimaryKey(Receiptinfo record);
}