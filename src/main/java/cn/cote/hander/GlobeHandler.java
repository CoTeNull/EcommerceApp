package cn.cote.hander;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//异常的全局捕获
@ControllerAdvice
public class GlobeHandler {

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public String isPermission(Exception e){
        return "no permission";
    }

    @ExceptionHandler(value = UnauthenticatedException.class)
    @ResponseBody
    public String isRoles(Exception e){
        return "no Roles";
    }
}
