package cn.cote.mapper;

import cn.cote.pojo.RolesPermissions;
import cn.cote.pojo.RolesPermissionsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolesPermissionsMapper {
    int countByExample(RolesPermissionsExample example);

    int deleteByExample(RolesPermissionsExample example);

    int deleteByPrimaryKey(Integer rolesPermissionsId);

    int insert(RolesPermissions record);

    int insertSelective(RolesPermissions record);

    List<RolesPermissions> selectByExample(RolesPermissionsExample example);

    RolesPermissions selectByPrimaryKey(Integer rolesPermissionsId);

    int updateByExampleSelective(@Param("record") RolesPermissions record, @Param("example") RolesPermissionsExample example);

    int updateByExample(@Param("record") RolesPermissions record, @Param("example") RolesPermissionsExample example);

    int updateByPrimaryKeySelective(RolesPermissions record);

    int updateByPrimaryKey(RolesPermissions record);
}