/**
 * Created by jsMode on 2017/4/14.
 */

//region ------获取一个特定 oDiv标签下的特定类名函数------
function getByClass(oDiv,sClass){
    var oEle=oDiv.getElementsByClassName('*');
    var aResult=[];
    for(var i=0;i<oEle.length;i++){
        if(aResult[i].className==sClass){
            aResult.push(oEle[i]);
        }
    }
    return aResult;                   //输出的数组为符合条件的标签组
}
//endregion

//region ------获取css表中的值------
function getStyle(obj,name){
    if(obj.currentStyle)
    {return obj.currentStyle[name];}              //IE
    else
    {return getComputedStyle(obj,false)[name];}   //FF、Chrome
}
//endregion

//region ------任意值的运动框架------
function startMove(obj,json,fnEnd){//分别表示对象，json({width:300,height:300})，执行结束之后的函数
 clearInterval(obj.timer);
    obj.timer=setInterval(function () {
        var bStop=true; //用来判断json是否都完成了运动
        for(var attr in json)
        {
            var cur=0;
            if(attr=='opacity'){//特别处理的属性
                cur=Math.round(parseFloat(getStyle(obj,attr))*100);//让小数取整
            }
            else{
                cur=parseFloat(getStyle(obj,attr));
                cur=cur>0?Math.ceil(cur):Math.floor(cur);
            }
            var speed=(json[attr]-cur)/10;
            speed=speed>0?Math.ceil(speed):Math.floor(speed);
            if(cur!=json[attr]){
               bStop=false;   //如果有目标没有达成就不停止
            }
            if(attr=='opacity'){
                    obj.style.filter='alpha(opacity:'+(cur+speed)+')';//IE兼容
                    obj.style.opacity=(cur+speed)/100;
                }
            else{
                    obj.style[attr]=cur+speed+'px';
                }
        }
        if(bStop){  //如果所有的运动完成
            clearInterval(obj.timer);
            if(fnEnd)fnEnd(); //只有外面fnEnd存在的时候会执行；
        }
    },10)

}
//endregion

//region ------返回鼠标的真正位置------
function getMous(ev){
   var scrollTop=document.documentElement.scrollTop||document.body.scrollTop;    //获取滚动条的位置
   var scrollLeft=document.documentElement.scrollLeft||document.body.scrollLeft;
    return {x:ev.clientX+scrollLeft , y:ev.clientY+scrollTop};  //输出json组,真正的鼠标位置
}
//endregion

//region ------AJAX------
  function ajax (url,fnSucc,fnFaild) {
      var oAjax=new XMLHttpRequest();        //①创建AjAX对象;
      oAjax.open('GET',url,true);           //②连接服务器
      oAjax.send();                          //③发送请求
      oAjax.onreadystatechange=function(){  //④接受返回请求
          if( oAjax.readyState==4){         //读取完成
              if(oAjax.status==200){        //判断是否成功获取 404?著名的失败码
                  fnSucc(oAjax.responseText);
              }
              else{
                  if(fnFaild);
                  {
                      fnFaild(oAjax.status);//自定义出错
                  }
              }
          }
      }
  }
//endregion

//region-----设置和获取以及删除Cookie------
//设置Cookie
function setCookie(name,value,lostTime) {
    if(lostTime)
    {
        var Cookietime=new Date();
        Cookietime.setDate(Cookietime.getDate()+lostTime);
        document.cookie=name+'='+value+';expires='+Cookietime;
    }
    else
    {
        document.cookie=name+"="+value;
    }
}
//获取Cookie
function getCookie(name) {
    var arr=document.cookie.split('; ');
    for(var i=0;i<arr.length;i++){
        var arr2=arr[i].split('=');
        if(arr2[0] == name ){ return  arr2[1];}
    }
    return '';
}
//删除Cookie
function removeCookie(name) {
    setCookie(name,1,-1);
}
//endregion

