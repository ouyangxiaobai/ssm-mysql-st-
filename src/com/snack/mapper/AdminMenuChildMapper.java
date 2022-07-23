package com.snack.mapper;

import com.snack.model.AdminMenuChild;
import com.snack.model.AdminMenuChildExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminMenuChildMapper {
    int countByExample(AdminMenuChildExample example);

    int deleteByExample(AdminMenuChildExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminMenuChild record);

    int insertSelective(AdminMenuChild record);

    List<AdminMenuChild> selectByExample(AdminMenuChildExample example);

    AdminMenuChild selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminMenuChild record, @Param("example") AdminMenuChildExample example);

    int updateByExample(@Param("record") AdminMenuChild record, @Param("example") AdminMenuChildExample example);

    int updateByPrimaryKeySelective(AdminMenuChild record);

    int updateByPrimaryKey(AdminMenuChild record);
}