var showName=getCookie("showName");
if(showName==""){
    if(confirm("请您登陆")){
        window.location.href="index.html";
    }
    else{
        window.location.href="index.html";
    }
}
$(function () {
  //获取用户信息
        $(".article_left_img>img").attr("src",getCookie("userImg"));
        $("#touxt").attr("src",getCookie("userImg"));
        $(".H1").html(getCookie("showName"));
        $("#showname").val(getCookie("showName"));
        $("#ider").html(getCookie("userName"));
        $(".usershowname").prepend(showName);
        $(".usernameif").unwrap();
        $(".usernameif").remove();
//右边栏的信息
    $.get('/Shop/shop/getShop?number=8',function (data) {
        console.log(data);
        if(data.code==1){
            $.each(data.data,function (index,item) {
                $(".list-group").append("<li class='list-group-item'><a href='javascript:;'data-id="+item.commodityId+">"+item.commodityName+"</a></li>");
            });
        }
    });
//选项卡事件
    $(".article_left_ul ul li").eq(0).css({background:"#333333",borderLeft:"6px solid #ff4a4c"});
    $(".article_left_ul ul li a").eq(0).css("color","white");
    $(".article_left_ul ul li").click(function () {
        //修改导航条样式
        $(".article_left_ul ul li").css({background:"white",border:"none"});
        $(".article_left_ul ul li a").css("color","#282828");
        $(this).css({background:"#333333",borderLeft:"6px solid #ff4a4c"});
        $(this).find("a").css("color","white");
        //修改右侧
        var nowaside=$(this).data("num");
        $(".article_main ul li").fadeOut(300);
        $(".article_main ul li").eq(nowaside-1).fadeIn(300);

    });

    //修改昵称
    $("#showname").dblclick(
        function () {
            if($("#showname").css("border")=="0px none rgb(75, 75, 75)"){
                $("#showname").removeAttr("readOnly");
                $("#showname").css("border","1px solid #979797");
            }
            else{
                $("#showname").attr("readOnly","true");
                $("#showname").css("border","none");
            }
        }
    );
    $("#showname").change(function () {
        //上传
            if($("#showname").val()!=""){
                var data={userNc:$("#showname").val()};
                $.post('/Shop/user/registerNc',data,function(data){
                    if(data.code==1){
                        setCookie("showName",$("#showname").val());
                        $(".H1").html(getCookie("showName"));
                        $("#showname").val(getCookie("showName"));
                        $(".usershowname").empty();
                        $(".usershowname").prepend(getCookie("showName")+" User<span class='caret'></span>");
                        $(".usernameif").unwrap();
                        $(".usernameif").remove();
                    }else{
                        alert("发生了小夜未知的错误 >_< ");
                    }

                });
            }
            else{
                alert("账号不能为空");
                $("#showname").val(getCookie("showName"));
            }
        });
    $("#showname").blur(function () {
        $("#showname").attr("readOnly","true");
        $("#showname").css("border","none");
    });
    var ifimg=false;
    //输入头像
    $("#touxt").click(function () {
        $("#toux").click();
    });
    $("#toux").change(function () {
        var file1=$('#toux')[0].files[0];
        $("#touxt").attr("src",window.URL.createObjectURL(file1)); //获取上传图片的本地路径
    });
    //上传图片
    $("#massage form").submit(function(e){
        e.preventDefault();
        // 使用FormData方法上传图片
        var data = new FormData(this);
        $.ajax({
            data:data,
            type:'post',
            url:'/Shop/user/registerImg',
            // 不缓存请求页面
            cache:false,
            // 不转换请求数据
            processData:false,
            // 不改变数据的类型
            contentType:false,
            success:function(data){
                if(data.code==1){
                    setCookie("userImg",data.data);
                    ifimg=true;
                }else{
                    console.log("发生了小夜未知的错误 >_< ");
                }

            }
        })
    });
    //提交信息
    $("#lonin").click(function () {
        $("#imgloup") .click();
        var imgtime = setInterval(function () {
            if(ifimg){
                clearInterval(imgtime);
                ifimg=false;
                $(".article_left_img>img").attr("src",getCookie("userImg"));
                $("#touxt").attr("src",getCookie("userImg"));
                $(".dropdown>img").attr("src",getCookie("userImg"));
            }
        },100);
    });


    //发布商品有关

    //发布商品图片
    var ifbuimg=false;
    $(".shop_img img").click(function () {
        $(".shop_img form input[type=file]").click();
    });
    $(".shop_img form input[type=file]").change(function () {
        var file1=$('.shop_img form input[type=file]')[0].files[0];
        $(".shop_img img").attr("src",window.URL.createObjectURL(file1)); //获取上传图片的本地路径
    });
    //发布上传信息
    $(".shop_img form").submit(function(e){
        e.preventDefault();
        // 使用FormData方法上传图片
        var datae = new FormData(this);
        $.ajax({
            data:datae,
            type:'post',
            url:'/Shop/shop/commodityImg',
            // 不缓存请求页面
            cache:false,
            // 不转换请求数据
            processData:false,
            // 不改变数据的类型
            contentType:false,
            success:function(data){
                if(data.code==1){
                    console.log(data);
                }else{
                    console.log("发生了小夜未知的错误 >_< ");
                }
            }
        })
    });
   //发布正则
    $(".shop_img a").click(function () {
        var buName=$("#buname").val();
        var buPrice=$("#buprice").val();
        var buNum=1;//暂时默认为1
        var buMassage=$("#bumassage").val();
        if(buName!=""&&buPrice!=""&&buNum!=""&&buMassage!=""){
            var buData={commodityName:buName,commodityPrives:buPrice,commodityMessage:buMassage};
            $.post("/Shop/shop/sendShop",buData,function (data) {
                console.log(data);
                if(data.code>0){
                    $(".shop_img form input[type=submit]").click();
                    alert("发布成功");
                }else{alert("发布失败")}
            });
        }
       else{
            alert("请按规范提交信息！");
        }

    })

    //我的购物车有关
     $.post('/Shop/shop/getBuyShop',function (data) {
         console.log(data);
         if(data.length==0){
             $(".mybuy").append("<p style='color: #838383'>您暂未购买任何物品哦</p>")
         }
         else{
             for(var i=0;i<data.length;i++){
                 $(".mybuy").append("<div><img src="+data.data[i].commodityImg+">" +
                     "<p class='thbu' data-num='"+data.data[i].commodityId+"'>"+data.data[i].commodityName+"</p>" +
                     "<p>"+data.data[i].commodityMasterId+"</p>" +
                     "<a href='javascript:;'>我不要了</a></div>");
             }
             //添加删除事件
             $(".mybuy a").click(function () {
                     if(confirm("不喜欢的话就 Let it Go！")){
                         var buyName=$(this).parent().find(".thbu").data("num");
                         var nowDiv=$(this).parent();
                         $.post('/Shop/shop/deleteDeal',{commodityId:buyName},function (data) {
                             console.log(data);
                             if(data.code==1){
                                 nowDiv.remove();
                                 if($(".mybuy").children().length==0){
                                     $(".mybuy").append("<p style='color: #838383'>您暂未购买任何物品哦</p>")
                                 }
                             }
                         })
                     }
                     else{}
             });
         }
     });

});