/**
 * Created by xqtdn on 2017/6/21.
 */

$(function () {
    //Cookie有关
    var showName=getCookie("showName");
    if(showName!=""){
        $(".usershowname").prepend(showName);
        $(".usernameif").unwrap();
        $(".usernameif").remove();
    }
    else{
        $(".usershowname").prepend("游客");
        $("#endname").unwrap();
        $("#endname").remove();
    }
    if(showName!=""){
        $("#head_login").html(getCookie("showName"));
        $("#head_loginin").html("会员一级");
        $("#head_loginin").attr("src","个人信息.html")
    }
    
    //BUG 修复 浏览器初始样式添加
    $(".article_header_left>ul>li div").css("display","none");
    $(".article_header_right header span").css("border-bottom","");
    $(".article_header_right header span a").css("color","#3b3b3b");
    $(".article_header_right header span").eq(0).css("border-bottom","1px solid #00969f");
    $(".article_header_right header span a").eq(0).css("color","#00969f");
    //头部淡入轮播
    var headindeif=true;
    var headindex=1;
    $(".article_header_footer ul li").click(function () {
        if(headindeif){
            headindeif=false;
            $(".article_header_footer ul li").removeClass("hShow");
            $(this).addClass("hShow");
            headindex =$(this).data("num");
            $.each($(".article_header_main>ul li"),function (index) {
                if(index!=headindex-1){
                    $(this).animate({opacity:0} ,500);
                }
            })
            $(".article_header_main>ul li").eq(headindex-1).animate({opacity:1},500,function () {
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
    $(".article_header").mouseout(
        function () {
            clearInterval(headtimer);
            headtimer= setInterval(function () {
                $.each($(".article_header_footer ul li"),function () {
                    if($(this).data('num')==headindex) {
                        $(this).next().click();return false;
                    }
                    if(headindex==$(".article_header_footer ul li").length){$(".article_header_footer ul li").eq(0).click();}
                })
            },4000);}
    );
    //当鼠标移入关闭计时器
    $(".article_header").mouseover( function () {clearInterval(headtimer);});
    $(".article_header_footer ul li").mouseover( function () {clearInterval(headtimer);});
    /*自动播放-----end*/
    $("aside").eq(0).css("height",$(window).height());
    //控制同一个区域的盒子切换(右)
    $(".article_header_right header span").mouseover(function () {
        //首先全部恢复原状
        $(".article_header_right header span").css("border-bottom","");
        $(".article_header_right header span a").css("color","#3b3b3b");
        //然后改变当前的样式
        $(this).css("border-bottom","1px solid #00969f");
        $(this).find("a").css("color","#00969f");
        //切换盒子
        var nowhovernum=$(this).data("num");
        $(".article_header_right article>ul>li").hide();
        $(".article_header_right article>ul>li").eq(nowhovernum-1).show();
    });
    //控制同一个区域的盒子切换(左)
    $(".article_header_left>ul>li").hover(function () {
            //切换盒子
            var nowhovernum2=$(this).data("num");
            $(".article_header_left>ul>li>span").hide();
            $(".article_header_left>ul>li>span").eq(nowhovernum2-1).show();
            $(".article_header_left>ul>li").eq(nowhovernum2-1).find("div").show();
        },
        function () {
            $(".article_header_left>ul>li div").css("display","none");
            $(".article_header_left>ul>li>span").hide();
        }
    );
    //end
    var hrttime=setInterval(function () {
        $(".article_header_right footer ul").css("animation","hrft 1s");
    },2000);
    var hrttime2=setInterval(function () {
        $(".article_header_right footer ul").css("animation","");
    },4000);


    /*底部轮播事件*/
    $.get('/Shop/shop/getShopAndMaster?number=6',function (data) {
        console.log(data);
        if(data.code==1){
            $.each(data.data,function (index,item) {
                $(".lastblock_main ul").append("<li>" +
                    "<img src="+item.commodityImg+"><div>" +
                    "<p>商品名称:</p><p class='thbuy' data-num='"+item.commodityId+"'>"+item.commodityName+"</p><br>" +
                    "<p>一口价:</p><p>"+item.commodityPrives+"</p><br>" +
                    "<p>卖家:</p><p>"+item.userName+"</p></div>" +
                    "<p>"+item.commodityMessage+"<a href='javascript:;'>加入购物车</a></p></li>");
                if(index==5)return false;
            });
            //----------Buy----------
            $(".lastblock_main ul a").click(function () {
                var userName=getCookie("userName");
                if(userName!=""){
                    if(confirm("看上了它嘛？点击确定加入购物车！")){
                        var buyNameId=$(this).parent().parent().find(".thbuy").data("num");
                        $.post('/Shop/shop/buyShop',{commodityId:buyNameId},function (data) {
                            console.log(data);
                            if(data.code==1){
                                alert("已经放入购物车！");
                            }
                        })
                    }
                    else{}
                }
                else{
                    if(confirm("登陆以后才能购买哦！")){ window.location.href="/";}
                    else{ window.location.href="/";}
                }
            });

        }

        var oLi=$(".lastblock_main ul li"); //获取要轮播的所有li标签
        var oUl=$(".lastblock_main ul");    //获取整个Ul(移动的时候也是设置UL的left属性，来控制轮播图移动的，
//两个按钮                                  // 所以上面的css要将UL设置为绝对定位属性)
        var bLeft= $("#faleft").eq(0);
        var bRight=$("#faright");
//前期准备定义变量
        oUl.append(oLi.clone());  //全部复制li然后添加进去，目的造成无限循环的错觉！ 具体效果可以将上面的css注释看下
        oUl.css("width",oLi.length*oLi.outerWidth(true)*2); //设置UL 的宽度等于li全部加起来的宽度，这样才不会让li换行。
        var nowIndex=1;   //定义值，目的让这个值++，然后下面的函数就可以移动对应的长度。
        var ifRun=true;  //开关—默认为开 (防止事件的多次点击)
//按钮事件---------------------------------------
        bRight.click(function () {
            if(ifRun){     //如果开关是开着的
                nowIndex++;  //当前值加一
                linRun();  //然后执行函数
            }
        });
        bLeft.click(function () {
            if(ifRun){
                nowIndex--;
                linRun();
            }
        });
//轮播函数----------------------------------------
        function linRun() {
            ifRun=false;  //准备开始执行动画，开关关闭——此时连续点击按钮也不会触发事件。
            if(nowIndex <= oLi.length+1 && nowIndex>=1){  //如果当前值没有超过li的个数，执行动画
                //动画模块——让ul整体像左移动li的宽度(边框和li的全部宽度)，当动画执行结束后，打开开关
                oUl.animate({left:-(nowIndex-1)*(oLi.outerWidth(true))+'px'},'slow','swing',function () {ifRun=true});
            }
            else if(nowIndex<1){  //当前值为1的时候还向做移动就会让当前值小于1，此时让ul整体位置移动到副本的位置,造成无限循环的错觉
                nowIndex=oLi.length;
                oUl.css("left",-oLi.length*(oLi.outerWidth(true)));
                oUl.animate({left:-(oLi.length-1)*oLi.outerWidth(true)+'px'},'slow','swing',function () {ifRun=true})}
            else {  //当超过li的个数的时候，让li瞬间回去，然后执行动画，再让当前的值变为2。照成错觉
                nowIndex=2;
                oUl.css("left",0);
                oUl.animate({left:-1*oLi.outerWidth(true)+'px'},'slow','swing',function () {ifRun=true})}
        }
//自动轮播-----------------------------------------
        var linRunTimer= setInterval(function () {  //设置定时器，每隔3秒默认点击一次向右按钮
            bRight.click();
        },4000);
        $(".lastblock_main").hover(
            function () {
                clearInterval(linRunTimer);
            },
            function () {
                linRunTimer= setInterval(function () {  //设置定时器，每隔3秒默认点击一次向右按钮
                    bRight.click();
                },4000);
            }
        );
    });
//轮播结束

});

/* 窗体的滚动事件*/
$(document).scroll(function ()
{
    var st = $(this).scrollTop();//获取滚动条的距离顶部的距离
    if(st>600){
        $("#fei").show();
        $('#fei').click(function(){
            $('body').animate({scrollTop: '0px'});
            $('html').animate({scrollTop: '0px'});//兼容火狐
            setTimeout(function () {
                $('body').stop();
                $('html').stop();
            },500);
        });
    }
    else{
        $("#fei").hide();
    }
});