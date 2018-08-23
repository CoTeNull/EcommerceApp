/**
 * Created by xqtdn on 2017/6/24.
 */
$(function () {
    //头部淡入轮播
    var headindeif=true;
    var headindex=1;
    var headImgIf=0;
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


    //密码显示和隐藏
    $("#eye2").click(function () {
        if( $("#pass").attr("type")=="password"){$("#pass").attr("type","text");$("#pass2").attr("type","text");$("#eye").addClass("fa-unlock-alt");$("#eye2").addClass("fa-unlock-alt")}
        else{$("#pass").attr("type","password");$("#pass2").attr("type","password");$("#eye").removeClass("fa-unlock-alt");$("#eye2").removeClass("fa-unlock-alt")}
    });
    $("#eye").click(function () {
        if( $("#pass").attr("type")=="password"){$("#pass").attr("type","text");$("#pass2").attr("type","text");$("#eye").addClass("fa-unlock-alt");$("#eye2").addClass("fa-unlock-alt")}
        else{$("#pass").attr("type","password");$("#pass2").attr("type","password");$("#eye").removeClass("fa-unlock-alt");$("#eye2").removeClass("fa-unlock-alt")}
    });
    //注册正则
    $("#name").change(function () {
        if($(this).val().length>20){$(this).css("border","1px solid #ff4a4c");$(this).val("");$(this).attr("placeholder","您刚才输入的用户名过长,请重新输入")}
        else{$(this).css("border","none");$(this).attr("placeholder","请输入用户ID")}
    });
    $("#pass").change(function () {
        if($(this).val().length>25){$(this).css("border","1px solid #ff4a4c");$(this).val("");$(this).attr("placeholder","您刚才输入的密码过长,请重新输入")}
        else{$(this).css("border","none");$(this).attr("placeholder","请输入密码")}
    });
    $("#pass2").change(function () {
        if($(this).val()!=$("#pass").val()){$(this).css("border","1px solid #ff4a4c");$(this).val("");$(this).attr("placeholder","输入的密码不一致,请重新输入")}
        else{$(this).css("border","none");$(this).attr("placeholder","请输入密码")}
    });
    $("#showname").change(function () {
        if($(this).val().length>10){$(this).css("border","1px solid #ff4a4c");$(this).val("");$(this).attr("placeholder","昵称过长,请输入少于10个字符")}
        else{$(this).css("border","1px solid skyblue");$(this).attr("placeholder","请创建您的昵称")}
    });
    //注册事件-------------------------------


    //--------------------------------------------------------------------------------
    var handlerEmbed = function (captchaObj) {
        $("#zhuche").click(function () {

            var validate = captchaObj.getValidate();
            if (!validate) {//如果未完成验证的方法
                alert("验证失败");
                return;
            }

            if($("#name").val()!="" && $("#pass").val()!="" && $("#pass2").val()!=""){
                //*//上传信息
                var ifdata=null;
                var data={"userName":$("#name").val(),"userPassword":$("#pass").val()};
                $.post('/Shop/user/register/',data,function(data){
                    console.log(data);
                    if(data.code==1){
                        //执行动画
                        $("#massage").show();
                        $("#massage").addClass("myshow");
                        $("#longin").addClass("myhidden");
                    }else if(data.code==0){
                        alert("改账号已被注册");
                    }else{
                        alert("发生了小夜未知的错误 >_< ");
                    }
                });

            }
            else{
                alert("请按规范填写信息")
            }
        });
        // 将验证码加到id为captcha的元素里，同时会有三个input的值：geetest_challenge, geetest_validate, geetest_seccode
        captchaObj.appendTo("#nowlogin");
        captchaObj.onReady(function () {
            $("#nowlogin").html("");
        });
        // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
    };
    //yzm--------------------------------------------------
    $.ajax({
        // 获取id，challenge，success（是否启用failback）
        url: "/Shop/gt/register1?t=" + (new Date()).getTime(), // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
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


    /*----------创建昵称-----------*/
        $("#lonin").click(function () {
            if($("#showname").val()!=""){
                var data={userNc:$("#showname").val()};
                $.post('/Shop/user/registerNc/',data,function(data){
                    console.log(data);
                    if(data.code==1){
                        setCookie("showName",$("#showname").val());
                        setCookie("userName",$("#name").val());
                        if(headImgIf==1){
                            $("#imgloup").click();
                        }else {headImgIf==0;}
                        if(confirm("欢迎使用！点击确定进入展示大厅")){
                            window.location.href="main.html";
                        }
                        else{
                            window.location.href="main.html";
                        }
                    }else{
                        alert("昵称未改变");
                        console.log(data);
                    }

                });

            }else {alert("昵称不能为空")}
        });
    /*-----------头像上传-----------*/

    //输入头像
    $("#touxt").click(function () {
        $("#toux").click();
    });
    $("#toux").change(function () {
        var file1=$('#toux')[0].files[0];
        headImgIf=1;
        $("#touxt").attr("src",window.URL.createObjectURL(file1)); //获取上传图片的本地路径
    });
    $("#massage form").submit(function(e){
        e.preventDefault();
        // 使用FormData方法上传图片
        var data = new FormData(this);
        $.ajax({
            data:data,
            type:'post',
            url:'/Shop/user/registerImg/',
            // 不缓存请求页面
            cache:false,
            // 不转换请求数据
            processData:false,
            // 不改变数据的类型
            contentType:false,
            success:function(data){
                console.log(data);
                if(data.code==1){
                    setCookie("userImg",data.data);
                }else{
                    alert("发生了小夜未知的错误 >_< ");
                }
            }
        })
    })
});

