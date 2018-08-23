/**
 * Created by xqtdn on 2017/6/24.
 */
$(function () {
    //头部淡入轮播
    var headindeif=true;
    var headindex=1;
    $(".article_header_footer ul li").click(function () {
        if(headindeif){
            headindeif=false;
            $(".article_header_footer ul li").removeClass("hShow");
            $(this).addClass("hShow");
            headindex =$(this).data("num");
            $.each($("aside>ul li"),function (index) {
                if(index!=headindex-1){
                    $(this).animate({opacity:0} ,500);
                }
            })
            $("aside>ul li").eq(headindex-1).animate({opacity:1},500,function () {
                headindeif=true;
            });
        }
    });
    /*自动播放*/
    var headtimer= setInterval(function () {
        $.each($(".article_header_footer ul li"),function () {
            if($(this).data('num')==headindex) {
                $(this).next().click();return false;
            }
            if(headindex==$(".article_header_footer ul li").length){$(".article_header_footer ul li").eq(0).click();}
        })
    },3000);
    //当鼠标移出打开计时器
    $(".article_header_footer ul li").mouseout(
        function () {
            clearInterval(headtimer);
            headtimer= setInterval(function () {
                $.each($(".article_header_footer ul li"),function () {
                    if($(this).data('num')==headindex) {
                        $(this).next().click();return false;
                    }
                    if(headindex==$(".article_header_footer ul li").length){$(".article_header_footer ul li").eq(0).click();}
                })
            },3000);}
    );
    //当鼠标移入关闭计时器
    $(".article_header_footer ul li").mouseover( function () {clearInterval(headtimer);});
    /*自动播放-----end*/

    //登陆事件-------------------------------
    //密码显示和隐藏
    $("#eye").click(function () {
        if( $("#pass").attr("type")=="password"){$("#pass").attr("type","text");$("#eye").addClass("fa-unlock-alt")}
        else{$("#pass").attr("type","password");$("#eye").removeClass("fa-unlock-alt")}
    })
    var londtimer=null;

//--------------------------------------------------------------------------------
    var handlerEmbed = function (captchaObj) {
        $("#denglu").click(function () {
           // //验证码————————————————————
            var validate = captchaObj.getValidate();
            if (!validate) {//如果未完成验证的方法
                alert("验证失败");
                return;
            }
           //  //验证码————————————————————
            //向服务器传入数据
            var data={"userName":$("#name").val(),"userPassword":$("#pass").val()};
            /*获取账号信息准备输出*/
            var idimg=null;
            var idname=null;
            if(data.userName==''||data.userPassword==''){
                alert("账号或者密码不能为空");
            }
            else{
                //访问路由
                $.post('/Shop/user/login',data,function(data){
                    console.log(data);
                    if(data.code==1){
                        idimg=data.data.userImg;
                        idname=data.data.userNc;
                        //处理用户信息--------
                        $("#userimg").attr("src",idimg);
                        $("#username").html(idname);
                        //执行动画
                        $("#massage").show();
                        $("#massage").addClass("myshow");
                        $("#longin").addClass("myhidden");
                        //设置Cook值
                        setCookie("showName",idname);
                        setCookie("userName",$("#name").val());
                        setCookie("userImg",idimg);
                        //延迟跳转
                        var timer=3;
                        londtimer= setInterval(function () {
                            $("#time").get(0).innerHTML=timer--;
                            if(timer==-1){
                                $("#lonin").click();
                            }
                        },1000);
                    }else{
                        alert("用户名密码错误");
                    }
                });
            }
        });
        // // 将验证码加到id为captcha的元素里，同时会有三个input的值：geetest_challenge, geetest_validate, geetest_seccode
        captchaObj.appendTo("#nowlogin");
        captchaObj.onReady(function () {
            $("#nowlogin").html("");
        });
        // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
    };
 //---------------------------------------------------------------------------------
    $("#lonin").click(function () {
        clearInterval(londtimer);
        window.location.href="main.html"
    });
//yzm--------------------------------------------------
    $.ajax({
        // 获取id，challenge，success（是否启用failback）
        url: "/Shop/gt/register1?t=" + (new Date()).getTime(), // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
            // console.log(data);
            // 使用initGeetest接口
            // 参数1：配置参数
            // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
            initGeetest({
                gt: data.gt,
                challenge: data.challenge,
                new_captcha: data.new_captcha,
                product: "embed", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
                offline: !data.success // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
            }, handlerEmbed);
        }
    });
    //yzm--------------------------------------------------
});
