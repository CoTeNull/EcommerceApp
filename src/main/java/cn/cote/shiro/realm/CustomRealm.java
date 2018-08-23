package cn.cote.shiro.realm;

import cn.cote.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

//    用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //通过认证数据获取用户信息
        String userName = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名查找用户角色和权限
        Set<String> roles = getRolesByUserName(userName);
        Set<String> permissions = getPermissionsByUserName(userName);
        //创建简单的角色权限信息对象并返回
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    //    用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//       从主体传来的认证信息中获取用户名和密码
        String userName = (String)authenticationToken.getPrincipal();
//       通过用户名从数据库中查找密码(Service)
        String password = getPasswordByUserName(userName);
        if(password == null){
            return null;
        }else{
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName,password,"customRealm");
            authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("CoTe"));//声明盐
            return authenticationInfo;
        }
    }

//    获取认证的方法
    @Autowired
    private UserService userService;
    private String getPasswordByUserName(String userName) {
        String password = userService.findPassWordByName(userName);
       return password;
    }
   //权限
    private Set<String> getPermissionsByUserName(String userName) {
        Set<String> set = userService.findPermissionsByUserName(userName);
        return set;
    }

    //角色
    private Set<String> getRolesByUserName(String userName) {
        Set<String> set = userService.findRolesByUserName(userName);
        return set;
    }
}
