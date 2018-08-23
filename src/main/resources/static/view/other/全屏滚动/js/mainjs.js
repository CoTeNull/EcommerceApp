/**
 * Created by xqtdn on 2017/6/27.
 */
$(function(){
    //顶部轮播
    var loUl= $(".lunBo ul");
    var loLi=$(".lunBo ul li");
    //禁止图片拖动
    $(".lunBo li img").attr("ondragstart","return false");
    loLi.css("width",$(window).width());
    loUl.css("width", loLi.length*loLi.width());
    var headif=true;
    var headindex=1;

    $(".lunBoFoot li").click(function () {
        //添加样式
        $(".lunBoFoot li").removeClass("hShow");
        $(this).addClass("hShow");
        //执行轮播
        headindex=$(this).data("num");
        headRun();
    });
    //轮播函数
    function headRun() {
        if(headif){
            headif=false;
            if(headindex<=loLi.length)
                loUl.animate({left:-(headindex-1)*loLi.width()+'px'},500,'swing',function () {headif=true});
            else{
                headindex=1;
                loUl.animate({left:0+'px'},500,'swing',function () {headif=true});
            }
        }
    }
    //定时器启动自动轮播
    var headtimer= setInterval(function () {
        clearInterval(headtimer);
        $.each($(".lunBoFoot li"),function () {
            if($(this).data('num')==headindex) {
                $(this).next().click();return false;
            }
            if(headindex==$(".lunBo ul li").length){$(".lunBoFoot li").eq(0).click();}
        })
    },5000);
    $(".lunBoFoot li").mouseout(
        function () {
            clearInterval(headtimer);
            headtimer= setInterval(function () {
                $.each($(".lunBoFoot li"),function () {
                    if($(this).data('num')==headindex) {
                        $(this).next().click();return false;
                    }
                    if(headindex==$(".lunBo ul li").length){$(".lunBoFoot li").eq(0).click();}
                })
            },5000);}
    );
    $(".lunBoFoot li").mouseover( function () {clearInterval(headtimer);});

    /*---------全屏滚动----------*/
    $('#dowebok').fullpage({
        sectionsColor: ['#f4f6fb', '#f4f6fb', '#f4f6fb', 'rgba(64, 64, 64, 0.76)', '#f4f6fb'],
        'navigation': true,
        //当画面加载完成后执行动画(页面滑动结束)
        afterLoad: function(anchorLink, index){
          if(index==4){
              $(".sdWrap").show();
          }
        },
        //当前页面离开时发生(页面刚刚开始滑动)
        onLeave: function(index, direction){
            if(index == 1){
                $("nav").addClass("navcolr");
                $(".secul").addClass("seculbig");
                $(".secul").animate({opacity:1},1000);
            }
            if(index == 2){
                $("nav").removeClass("navcolr");
                $(".main").animate({opacity:1},2000);
            }
            if(index == 3){
                $(".sdWrap").addClass("gobegin");
                $("nav").addClass("navcolr");
            }
            if(index == 4){
                $("nav").removeClass("navcolr");
                $(".sdWrap").hide();
                $(".sdWrap").removeClass("gohead goright goleft");
                $(".five_img").addClass("fiveimgmove")
                $(".five_article").addClass("fivemove")
            }
            if(index==5){
            }
        }
    });


    /*lunbo*/   //前提引入，声明变量---------------------------
    var oLi=$(".main ul li"); //获取要轮播的所有li标签
    var oUl=$(".main ul");    //获取整个Ul(移动的时候也是设置UL的left属性，来控制轮播图移动的，
    //两个按钮                                  // 所以上面的css要将UL设置为绝对定位属性)
    var bLeft=$(".main_left");
    var bRight=$(".main_right");
    //前期准备定义变量
    oUl.append(oLi.clone());  //全部复制li然后添加进去，目的造成无限循环的错觉！ 具体效果可以将上面的css注释看下
    oUl.css("width",oLi.length*oLi.outerWidth(true)*2); //设置UL 的宽度等于li全部加起来的宽度，这样才不会让li换行。
    var nowIndex=1;   //定义值，目的让这个值++，然后下面的函数就可以移动对应的长度。
    var ifRun=true;  //开关—默认为开 (防止事件的多次点击)
    //按钮事件---------------------------------------
    bRight.click(function () {
        if(ifRun){     //如果开关是开着的
            nowIndex--;  //当前值加一
            linRun();  //然后执行函数
        }
    });
    bLeft.click(function () {
        if(ifRun){
            nowIndex++;
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
        bLeft.click();
    },3000);

    $(".main").hover(         //当鼠标移入的时候，关闭定时器，移出打开定时器。
        function () { clearInterval(linRunTimer); },
        function () { clearInterval(linRunTimer);
            linRunTimer= setInterval(function () {
                bLeft.click();
            },3000);});

    /*-------3D-------*/
    $(".sdWrap_after").click(function () {
        $(".sdWrap").removeClass("goright goleft");
        $(".sdWrap").addClass("gohead");
    });
    $(".sdWrap_right").click(function () {
        $(".sdWrap").removeClass("gohead goleft");
        $(".sdWrap").addClass("goright");
    });
    $(".sdWrap_left").click(function () {
        $(".sdWrap").removeClass("gohead goright");
        $(".sdWrap").addClass("goleft");
    });
    $(".sdWrap_top").click(function () {
        $(".sdWrap").removeClass("gohead goright goleft");
    })

});//----------onLoadEnd
