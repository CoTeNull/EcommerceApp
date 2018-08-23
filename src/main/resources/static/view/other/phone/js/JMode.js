/**
 * Created by xqtdn on 2017/4/14.
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
function startMove(obj,attr,iTarget){//分别表示对象，属性，参数
 clearInterval(obj.timer);
    obj.timer=setInterval(function () {
        var cur=0;
         if(attr=='opacity'){//特别处理的属性
             cur=Math.round(parseFloat(getStyle(obj,attr))*100);//让小数取整
         }
        else{
             cur=parseFloat(getStyle(obj,attr));
             cur=cur>0?Math.ceil(cur):Math.floor(cur);
         }
        var speed=(iTarget-cur)/10;
        speed=speed>0?Math.ceil(speed):Math.floor(speed);
        if(cur==iTarget){
            clearInterval(obj.timer);
        }
        else{
            if(attr=='opacity'){
               obj.style.filter='alpha(opacity:'+(cur+speed)+')';//IE兼容
                obj.style.opacity=(cur+speed)/100;
            }
            else{
                obj.style[attr]=cur+speed+'px';
            }
        }
    },10)
}
//endregion

//region ------任意值的运动框架(行间样式)------
function startStyleMove(obj,attr,iTarget) {//分别表示对象，属性，参数
    clearInterval(obj.timer);
    obj.timer = setInterval(function () {
        var cur = 0;
        if (attr == 'opacity') {//特别处理的属性
            cur = Math.round(parseFloat(obj.style[attr]) * 100);//让小数取整
        }
        else {
            cur = parseFloat(obj.style[attr]);
            cur = cur > 0 ? Math.ceil(cur) : Math.floor(cur);
        }
        var speed = (iTarget - cur) / 10;
        speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);
        if (cur == iTarget) {
            clearInterval(obj.timer);
        }
        else {
            if (attr == 'opacity') {
                obj.style.filter = 'alpha(opacity:' + (cur + speed) + ')';//IE兼容
                obj.style.opacity = (cur + speed) / 100;
            }
            else {
                obj.style[attr] = cur + speed + 'px';
            }
        }
    }, 10)
}
//endregion
