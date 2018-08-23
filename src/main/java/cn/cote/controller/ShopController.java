package cn.cote.controller;

import cn.cote.myutils.WebData;
import cn.cote.pojo.Commodity;
import cn.cote.pojo.Deal;
import cn.cote.pojo.MyCommodity;
import cn.cote.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("shop")
@ResponseBody
public class ShopController {
    @Autowired
    ShopService shopService;
    /**
     * 根据页数来获取商品信息
     * @param request
     * @return 前端数据数组
     */
    @RequestMapping(value = "getShopByPage",method = RequestMethod.GET)
    public WebData getShopByPage(HttpServletRequest request){
        int pageId = Integer.parseInt(request.getParameter("pageId"));
        System.out.println("当前页数："+pageId);
        List<Commodity> this_list=shopService.getShopByPage(pageId);
        WebData data= new WebData();
        if(this_list.size()>0){
            data.setCode(1);
        }else{data.setCode(0);}
        data.setLength(this_list.size());
        data.setMessage("成功查找");
        data.setData(this_list);
        return data;
    }
    /**
     * 获取商品信息，数据量自定义
     * @param request
     * @return 前端数据数组
     */
    @RequestMapping(value = "getShop",method = RequestMethod.GET)
    public WebData getShop(HttpServletRequest request){
        int number = Integer.parseInt(request.getParameter("number"));
        System.out.println("获取商品个数为："+number);
        List<Commodity> this_list=shopService.getShop(number);
        WebData data= new WebData();
        if(this_list.size()>0){
            data.setCode(1);
        }else{data.setCode(0);}
        data.setLength(this_list.size());
        data.setMessage("成功查找");
        data.setData(this_list);
        return data;
    }
    /**
     * 获取商品信息和master信息，数据量自定义
     * @param request
     * @return 前端数据数组
     */
    @RequestMapping(value = "getShopAndMaster",method = RequestMethod.GET)
    public WebData getShopAndMaster(HttpServletRequest request){
        int number = Integer.parseInt(request.getParameter("number"));
        System.out.println("获取商品个数为："+number);
        List<MyCommodity> this_list=shopService.getShopAndMaster(number);
        WebData data= new WebData();
        if(this_list.size()>0){
            data.setCode(1);
        }else{data.setCode(0);}
        data.setLength(this_list.size());
        data.setMessage("成功查找");
        data.setData(this_list);
        return data;
    }
    /**
     * 发布商品
     * @param request
     * @param session
     * @return 前端数据数组
     */
    @RequestMapping(value = "sendShop",method = RequestMethod.POST)
    public WebData sendShop(HttpServletRequest request, HttpSession session){
    Commodity commodity = new Commodity();
    commodity.setCommodityMasterId((Integer) session.getAttribute("userId"));
    commodity.setCommodityMessage(request.getParameter("commodityMessage"));
    commodity.setCommodityName(request.getParameter("commodityName"));
    commodity.setCommodityPrives(Double.parseDouble(request.getParameter("commodityPrives")));
    commodity.setCommodityImg("/img/commoditys/code-online-soon.png");

    int code=shopService.insertCommodity(commodity);
    session.setAttribute("commodityId",code);
    WebData data = new WebData();
    data.setCode(code);
    data.setMessage("该处理完成");
    return data;
    }
    /**
     *修改商品图像
     * @param file
     * @return 前端数据数组
     * @throws IOException
     */
    @RequestMapping(value = "commodityImg",method = RequestMethod.POST)
    public WebData registerImg(MultipartFile file, HttpSession session, HttpServletRequest request) throws IOException {
        WebData data = new WebData();
        if (!file.isEmpty()) {
            // 设置图片存放路径
//            String path = "D:\\Web\\JavaDemo\\ShopApp\\web\\view\\img\\commoditys\\";
//            String path = "D:\\WebApp\\IdeaApp\\learngit\\ShopApp\\web\\view\\img\\commoditys\\";
            String path = request.getSession().getServletContext().getInitParameter("IMGPATH") + "commoditys\\";
            String originalFileName = file.getOriginalFilename();
            String type =originalFileName.substring(originalFileName.lastIndexOf("."));
            if(".GIF".equals(type.toUpperCase())||".PNG".equals(type.toUpperCase())||".JPG".equals(type.toUpperCase())){
                String newFileName = UUID.randomUUID() + type;// 新的图片名称
                File newFile = new File(path + newFileName);// 新的图片
                //------------手动日志-------------
                System.out.println("接受到Commodity图片为：\n"+newFile);
                //------------手动日志-------------
                file.transferTo(newFile);  // 将内存中的数据写入磁盘
//              存入数据库
                int this_id= (int) session.getAttribute("commodityId");
                System.out.println("this_id"+this_id);
                int code = shopService.addCommodityImg(newFileName,this_id);
                data.setCode(code);
                data.setMessage("修改图片操作完成");
                data.setData("/img/commoditys/"+newFileName);
                data.setLength(1);
            }else{data.setCode(0);data.setMessage("图片为不接受的类型");}
        }else {data.setCode(-1);data.setMessage("发生未知错误");}

        return data;
    }
    /**
     * 购买商品，添加交易记录
     * @param request
     * @param session
     * @return 前端数据数组
     */
    @RequestMapping(value = "buyShop",method = RequestMethod.POST)
    public WebData buyShop(HttpServletRequest request, HttpSession session){
        int shopId= Integer.parseInt(request.getParameter("commodityId"));
        int userId= (int) session.getAttribute("userId");
        Date date=new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        sdf.format(date);
        Deal newDeal =new Deal();
        newDeal.setDealShopId(shopId);
        newDeal.setDealUserId(userId);
        newDeal.setDealTime(date);
        newDeal.setDealCode("yes");
        int code;
        code=shopService.buyShop(newDeal);
        WebData data= new WebData();
        data.setCode(code);
        data.setMessage("Success");
        return data;
    }

    /**
     * 查找当前用户的交易记录
     * @param session
     * @return 前端数据数组
     */
    @RequestMapping(value = "getBuyShop",method = RequestMethod.POST)
    public WebData getBuyShop(HttpSession session){
        int userId=(int)session.getAttribute("userId");
        List<Commodity> newList = shopService.getBuyShop(userId);
        WebData data = new WebData();

        if(newList.size()>0){
            data.setCode(1);
            data.setLength(newList.size());
            data.setMessage("获取到数据");
            data.setData(newList);
        }else{
            data.setCode(0);
            data.setMessage("发生错误");
        }
        return data;
    }

    /**
     * 删除用户交易记录
     * @param request
     * @param session
     * @return 前端数据数组
     */
    @RequestMapping(value = "deleteDeal",method = RequestMethod.POST)
    public WebData deleteDeal(HttpServletRequest request, HttpSession session){
        int userId=(int)session.getAttribute("userId");
        WebData data= new WebData();
        if(userId>0){
          int commodityId= Integer.parseInt(request.getParameter("commodityId"));
          int code= shopService.deleteDeal(commodityId);
          data.setCode(code);
          data.setMessage("删除成功");
        }else{
          data.setCode(0);
          data.setMessage("不存在用户进行操作");
        }
        return data;
    }

    /**
     * 模糊查询
     * @param request
     * @return 前端数据数组
     */
    @RequestMapping(value = "findLike",method = RequestMethod.POST)
    public WebData findLike(HttpServletRequest request){
        String word = request.getParameter("name");
        System.out.println("输入字符："+word);
        List<Commodity> list = shopService.getShopByLike(word);
        WebData data= new WebData();
        data.setCode(1);
        if(list.size()>0){
            data.setLength(list.size());
            data.setMessage("找到商品");
            data.setData(list);
        }else{
            data.setLength(list.size());
            data.setMessage("未找到有关商品");
        }
        return data;
    }
}
