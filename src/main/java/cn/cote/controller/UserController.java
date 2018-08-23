package cn.cote.controller;

import cn.cote.myutils.WebData;
import cn.cote.pojo.User;
import cn.cote.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin
@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    /**
     * 登陆
     * @param request
     * @param session
     * @return 前端数据数组
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public WebData login(HttpServletRequest request, HttpSession session){


     //获取用户数据，放入User模型中
     User this_user =new User();

     WebData data =new WebData();
     this_user.setUserName(request.getParameter("userName"));
     this_user.setUserPassword(request.getParameter("userPassword"));
        //shiro
     Subject subject = SecurityUtils.getSubject();
     UsernamePasswordToken token = new UsernamePasswordToken(this_user.getUserName(),this_user.getUserPassword());

        try {
            subject.login(token);

            this_user=userService.findUserMessage(this_user);
            if(this_user!=null){
                data.setCode(1);
                data.setData(this_user);
                data.setMessage("FindIt");
                session.setAttribute("userId",this_user.getUserId());
            }else{
                data.setCode(0);
                data.setMessage("NotFind");
            }

        } catch (AuthenticationException e) {
            data.setCode(-1);
            data.setMessage(e.getMessage());
        }

        //引用工具类返回前端


//     if(this_user!=null){
//         //------------手动日志-------------
//         System.out.println("Landing: the User found is:"+this_user);
//         //------------手动日志-------------
//         data.setCode(1);
//         data.setMessage("FindIt");
//         data.setData(this_user);
//         session.setAttribute("userId",this_user.getUserId());
//     }else{
//         data.setCode(0);
//         data.setMessage("NotFind");
//     }
     return data;
    }
    /**
     * 注册
     * @param request
     * @return 前端数据数组
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public WebData register(HttpServletRequest request, HttpSession session){
        User this_user =new User();
        WebData data = new WebData();
        this_user.setUserName(request.getParameter("userName"));
        String pwd = request.getParameter("userPassword");
//        md5
        Md5Hash md5Pwd = new Md5Hash(pwd,"CoTe");
        this_user.setUserPassword(md5Pwd.toString());
        //尝试插入当前新用户数据
        int code=userService.insertUser(this_user);

        if(code==1){
          data.setCode(1);
          data.setMessage("注册成功");
          Subject subject = SecurityUtils.getSubject();
          UsernamePasswordToken token = new UsernamePasswordToken(this_user.getUserName(),pwd);
            try {
                subject.login(token);
                //通过Session创建userId
                this_user = userService.findUserMessage(this_user);
                session.setAttribute("userId",this_user.getUserId());
                //------------手动日志-------------
                System.out.println("新入户入驻,userId为："+this_user.getUserId());
                //------------手动日志-------------
            } catch (AuthenticationException e) {
                data.setCode(-2);
                data.setMessage(e.getMessage());
            }
        }else if(code==0){
          data.setCode(0);
          data.setMessage("用户存在");
        }
        else{
          data.setCode(-1);
          data.setMessage("注册失败");
        }
        return data;
    }
    /**
     * 修改昵称信息
     * @param request
     * @param session
     * @return 前端数据数组
     */
    @RequestMapping(value = "registerNc",method = RequestMethod.POST)
    public WebData registerNc(HttpServletRequest request, HttpSession session){
        String this_userNc =request.getParameter("userNc");
        //从当前的Session中获取当前用户的userId
        int this_id= (int) session.getAttribute("userId");
        int code=userService.changeRegisterNc(this_userNc,this_id);
        WebData data=new WebData();
        data.setCode(code);
        data.setMessage("修改成功");
        return data;
    }
    /**
     *修改用户头像
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "registerImg",method = RequestMethod.POST)
    public WebData registerImg(MultipartFile file, HttpSession session, HttpServletRequest request) throws IOException {
        WebData data = new WebData();
        if (!file.isEmpty()) {
            // 设置图片存放路径
            String path = "D:\\Web\\JavaDemo\\EcommerceApp\\src\\main\\resources\\static\\view\\img\\userImg\\";
//            String path = "D:\\WebApp\\IdeaApp\\learngit\\ShopApp\\web\\view\\img\\userImg\\";
//            String path = request.getSession().getServletContext().getInitParameter("IMGPATH") + "userImg\\";
            String originalFileName = file.getOriginalFilename();
            String type =originalFileName.substring(originalFileName.lastIndexOf("."));
            if(".GIF".equals(type.toUpperCase())||".PNG".equals(type.toUpperCase())||".JPG".equals(type.toUpperCase())){
                String newFileName = UUID.randomUUID() + type;// 新的图片名称
                File newFile = new File(path + newFileName);// 新的图片
                //------------手动日志-------------
                System.out.println("接受到图片为：\n"+newFile);
                //------------手动日志-------------
                file.transferTo(newFile);  // 将内存中的数据写入磁盘
//              存入数据库
                int this_id= (int) session.getAttribute("userId");
                int code = userService.addUserImg(newFileName,this_id);
                data.setCode(code);
                data.setMessage("修改头像操作完成");
                data.setData("/img/userImg/"+newFileName);
            }else{data.setCode(0);data.setMessage("图片为不接受的类型");}
        }else {data.setCode(-1);data.setMessage("发生未知错误");}

        return data;
    }
}
