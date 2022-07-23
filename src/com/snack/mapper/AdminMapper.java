package com.snack.mapper;

import com.snack.model.Admin;
import com.snack.model.AdminExample;
import com.snack.model.domain.DoAdmin;
import com.snack.model.domain.DoAdminInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer adId);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer adId);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    List<Admin> selectAdminLimit(Map<Object,Object> map);
     
    int countAdminLimit(Map<Object,Object> map);
    
    DoAdmin selectAdminByOne(int adId);
    
    int updateAdminInfo(DoAdminInfo doadminInfo);
}