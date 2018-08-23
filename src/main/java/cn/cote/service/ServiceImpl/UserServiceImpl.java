package cn.cote.service.ServiceImpl;

import cn.cote.mapper.RolesPermissionsMapper;
import cn.cote.mapper.UserMapper;
import cn.cote.mapper.UserRolesMapper;
import cn.cote.pojo.*;
import cn.cote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserMessage(User this_user) {
      UserExample example = new UserExample();
      UserExample.Criteria criteria =example.createCriteria();
      criteria.andUserNameEqualTo(this_user.getUserName());
      List<User> user=userMapper.selectByExample(example);
      if(user.size()!=0){
          return user.get(0);
      }else{
          return null;
      }
    }

    @Override
    public int insertUser(User this_user) {
        int code = 0;
//      创建条件类
       UserExample example = new UserExample();
//      生成条件类
       UserExample.Criteria criteria = example.createCriteria();
//      创建条件
       criteria.andUserNameEqualTo(this_user.getUserName());
       List<User> userExample=userMapper.selectByExample(example);
       int number = userExample.size();
//       判断用户是否存在
       if(number>0){
           code = 0;
       }
       else if(number==0){
           //设置默认的用户名和头像 ，然后创建该用户
           this_user.setUserNc("小跳");
           this_user.setUserImg("img/userImg/toux.jpg");
           userMapper.insert(this_user);
           code = 1;
       }else{
           code = -1;
       }
       return code;
    }

    @Override
    public int changeRegisterNc(String this_userNc,int this_id) {
      User this_user;
      this_user = userMapper.selectByPrimaryKey(this_id);
      this_user.setUserNc(this_userNc);
      int code = userMapper.updateByPrimaryKey(this_user);
      return code;
    }

    @Override
    public int addUserImg(String newFileName,int this_id) {
        User this_user;
        this_user = userMapper.selectByPrimaryKey(this_id);
        this_user.setUserImg("/img/userImg/"+newFileName);
        int code = userMapper.updateByPrimaryKey(this_user);
        return code;
    }

    @Override
    public String findPassWordByName(String userName) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<User> list =userMapper.selectByExample(example);
        String password =list.get(0).getUserPassword();
        if(password != null){
            return password;
        }else{return null;}
    }

    @Autowired
    private RolesPermissionsMapper rolesPermissionsMapper;

    @Override
    public Set<String> findPermissionsByUserName(String userName) {
        Set<String> str = findRolesByUserName(userName);
        Set<String> resout = new HashSet<>();
        if(str != null) {
            RolesPermissionsExample example = new RolesPermissionsExample();
            RolesPermissionsExample.Criteria criteria = example.createCriteria();
            List<String> result = new ArrayList<>(str);
            criteria.andRolesNameIn(result);
            List<RolesPermissions> list = rolesPermissionsMapper.selectByExample(example);

            if (list != null){
                for (RolesPermissions rp : list){
                    resout.add(rp.getPermissionsName());
                }
            }
        }
        return resout;
    }

    @Autowired
    private UserRolesMapper userRolesMapper;

    @Override
    public Set<String> findRolesByUserName(String userName) {
        UserRolesExample example = new UserRolesExample();
        UserRolesExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<UserRoles> list = userRolesMapper.selectByExample(example);
        List<String> list1 = new ArrayList<>();
        if(list.size() != 0){
            for(UserRoles userRoles:list){
                list1.add(userRoles.getRolesName());
            }
            Set<String> rolesSet = new HashSet<>(list1);
            return rolesSet;
        }else{
            return null;
        }
    }
}
