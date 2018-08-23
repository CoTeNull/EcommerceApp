window.onload=function () {
    $(".Maincenter article ul li>img:nth-of-type(1)").css("height",$(window).height());
    $(".Maincenter article ul li>img:nth-of-type(1)").css("width",$(window).width());
    var nowwt=0;
    var ifif=0;
    var changewidowtop=0;
    //region--引入参数--
    var oPhonenav=document.getElementsByClassName('leftnav')[0];
    var oPhoneall=document.getElementById('phonenav');
    var oBody=document.getElementById('body');
    var oPhoneclose=document.getElementById('closenav');
    var oSeach=document.getElementById('seacher');
    var osearch=document.getElementsByClassName('search')[0];
    var ophoneseach=document.getElementById('phoneseach');
    var oCoverBlack=document.getElementsByClassName('coverblack')[0];
    var oHeader=document.getElementsByTagName('header')[0];
    //endregion
    if(nowwt==0){
        $(".Maincenter article li:nth-of-type(1) div img:nth-of-type(2)").addClass("iphonefrist1");
        $(".Maincenter article li:nth-of-type(1) div img:nth-of-type(3)").addClass("iphonefrist2");
    }
    var allImg=$(".Maincenter article ul li div img");
//region--滚动条事件--
    setInterval(function () {
        ifif=0;
    },100);
    window.onscroll=function () {
        allImg.attr("class","");
        var widowtop=document.documentElement.scrollTop||document.body.scrollTop;
        if(ifif==0){
            if(changewidowtop<=widowtop && nowwt<$(".Maincenter article ul li").length-1) {
                clearInterval(oTop.timer);
                oTop.timer = setInterval(function () {
                    var cur = 0;
                    cur = widowtop;
                    var speed = ($(window).height() * (nowwt + 1) - cur) / 20;
                    speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);
                    if (widowtop >= $(window).height() * (nowwt + 1)) {
                        clearInterval(oTop.timer);
                        window.scrollTo(0, $(window).height() * (nowwt + 1));
                        nowwt++;
                        ifif = -1;
                        changewidowtop = widowtop;
                        if(nowwt==1){
                            $(".Maincenter article li:nth-of-type(2) div img").addClass("iphonesecend1");
                            $(".Maincenter article li:nth-of-type(2) div img:nth-of-type(7)").addClass("iphonesecend2");
                        }
                        else if(nowwt==2){
                            $(".Maincenter article li:nth-of-type(3) div img:nth-of-type(odd)").addClass("iphonethree1");
                            $(".Maincenter article li:nth-of-type(3) div img:nth-of-type(even)").addClass("iphonethree2");
                        }
                        else if(nowwt==3){
                            $(".Maincenter article li:nth-of-type(4) div img:nth-of-type(2)").addClass("iphonefour");
                        }
                    }
                    else {
                        Math.floor(cur + speed);
                        window.scrollTo(0, cur + speed);
                    }
                }, 15)
            }
            else if(nowwt==$(".Maincenter article ul li").length-1 ){
                if(changewidowtop<=widowtop){
                    clearInterval(oTop.timer);
                    oTop.timer = setInterval(function () {
                        var cur = 0;
                        cur = widowtop;
                        var speed = ($(window).height() * (nowwt)+100 - cur) / 20;
                        speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);
                        if (widowtop >= $(window).height() * (nowwt)+100) {
                            clearInterval(oTop.timer);
                            window.scrollTo(0, $(window).height() * (nowwt)+100);
                            nowwt++;
                            ifif = -1;
                            changewidowtop = widowtop;
                        }
                        else {
                            Math.floor(cur + speed);
                            window.scrollTo(0, cur + speed);
                        }
                    }, 15)
                }
                else{
                    clearInterval(oTop.timer);
                    oTop.timer = setInterval(function () {
                        var cur = 0;
                        cur = widowtop;
                        var speed = ($(window).height() * (nowwt-1) - cur) / 20;
                        speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);
                        if (widowtop <= $(window).height() * (nowwt-1)) {
                            clearInterval(oTop.timer);
                            window.scrollTo(0, $(window).height() * (nowwt-1));
                            nowwt--;
                            ifif = -1;
                            lastnumber++;
                            changewidowtop = widowtop;
                            if(nowwt==2){
                                $(".Maincenter article li:nth-of-type(3) div img:nth-of-type(odd)").addClass("iphonethree1");
                                $(".Maincenter article li:nth-of-type(3) div img:nth-of-type(even)").addClass("iphonethree2");
                            }
                        }
                        else {
                            Math.floor(cur + speed);
                            window.scrollTo(0, cur + speed);
                        }
                    }, 15)
                }
            }

            else{
                clearInterval(oTop.timer);
                oTop.timer= setInterval(function () {
                    var cur=0;
                    cur=widowtop;
                    var speed=($(window).height()*(nowwt-1)-cur)/20;
                    speed=speed>0?Math.ceil(speed):Math.floor(speed);

                    if(widowtop<=$(window).height()*(nowwt-1))
                    {
                        clearInterval(oTop.timer);
                        window.scrollTo(0,$(window).height()*(nowwt-1));
                        nowwt--;
                        ifif=-1;
                        lastnumber=0;
                        changewidowtop=widowtop;
                        if(nowwt==0){
                            $(".Maincenter article li:nth-of-type(1) div img:nth-of-type(2)").addClass("iphonefrist1");
                            $(".Maincenter article li:nth-of-type(1) div img:nth-of-type(3)").addClass("iphonefrist2");
                        }
                        else if(nowwt==1){
                            $(".Maincenter article li:nth-of-type(2) div img").addClass("iphonesecend1");
                            $(".Maincenter article li:nth-of-type(2) div img:nth-of-type(7)").addClass("iphonesecend2");
                        }
                        else if(nowwt==2){
                            $(".Maincenter article li:nth-of-type(3) div img:nth-of-type(odd)").addClass("iphonethree1");
                            $(".Maincenter article li:nth-of-type(3) div img:nth-of-type(even)").addClass("iphonethree2");
                        }
                        else if(nowwt==3){
                            $(".Maincenter article li:nth-of-type(4) div img:nth-of-type(2)").addClass("iphonefour");
                        }
                    }
                    else
                    {
                        Math.floor(cur+speed);
                        window.scrollTo(0,cur+speed);
                    }
                },15)
            }
        }
//                 if(widowtop>0){
//                    startMove(oHeader,'top',-96);
//                    oHeader.style.position='fixed';
//                else if(widowtop<90){
////                    startMove(oHeader,'top',0);
////                    oHeader.style.position='relative';
//                    }
//                }
        else {
            changewidowtop=widowtop;
            return 0;
        }

    };
    //region--返回顶部--
    var oTop=document.getElementsByClassName('righttop')[0];
    oTop.onclick=function () {
        window.scrollTo(0,0);
    };
    //endregion

    //region--手机端导航--
    oPhoneall.onclick=function () {
        if(oPhonenav.offsetLeft<0){
            startMove(oPhonenav,'left',0);
        }
        else{
            startMove(oPhonenav,'left',Math.round(-oBody.offsetWidth*0.6));
        }
    };
    oPhoneclose.onclick=function () {
        startMove(oPhonenav,'left',Math.round(-oBody.offsetWidth*0.6));
    };
    //endregion

    //region--搜索事件--
    oSeach.onclick=function () {
        window.scrollTo(0,0);
        var nowtop=getStyle(osearch,'margin-top');
        if(nowtop==-90+'px'){
            startMove(osearch,'margin-top',-20);
        }
        else{
            startMove(osearch,'margin-top',-90);
        }
    };
    ophoneseach.onclick=function () {
        if(getStyle(oCoverBlack,'display')=='none'){
            oCoverBlack.style.display='block';
            startMove(oCoverBlack,'opacity',100);
        }
        else{
            startMove(oCoverBlack,'opacity',0);
            setTimeout(function () {
                oCoverBlack.style.display='none';
            },200);
        }
    };
    //endregion
}
