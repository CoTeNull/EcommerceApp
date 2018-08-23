package cn.cote.mapper;

import cn.cote.pojo.User;
import cn.cote.pojo.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
   //根据条件查询数量
    int countByExample(UserExample example);
  //根据条件删除多条
    int deleteByExample(UserExample example);
  //根据条件删除单条
    int deleteByPrimaryKey(Integer userId);
  //插入数据
    int insert(User record);

    int insertSelective(User record);
  //根据条件查询数据
    List<User> selectByExample(UserExample example);
  //根据主键查询数据
    User selectByPrimaryKey(Integer userId);
  //按条件更新值不为null的字段
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);
  //按条件更新
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);
  //按条件更新
    int updateByPrimaryKeySelective(User record);
  //按主键更新
    int updateByPrimaryKey(User record);

//    自定义
    User selectByNameAndPass(User this_user);
    User selectByName(User this_user);
}