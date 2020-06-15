window.onload = function(){
    /*720代表设计师给的设计稿的宽度，你的设计稿是多少，就写多少;100代表换算比例，这里写100是
      为了以后好算,比如，你测量的一个宽度是100px,就可以写为1rem,以及1px=0.01rem等等*/
    getRem(1920,100)
};
window.onresize = function(){
    getRem(1920,100)
};
function getRem(pwidth,prem){
    var html = document.getElementsByTagName("html")[0];
    var oWidth = document.body.clientWidth || document.documentElement.clientWidth;
    html.style.fontSize = oWidth/pwidth*prem + "px";
}
function alertmsg(messge){
    layer.msg(messge, {
        time: 2000, //1s后自动关闭
    });
}
$(function () {
    showHeaderMessage();//头部消息盒子的显示
    setMinHeight();
    // showEleFilePop();
	
	/*左侧的功能*/
	$(".listlefta").click(function(){
		$(this).addClass("actives").siblings().removeClass("actives");
	})
	$(".shouqione").click(function(){
		$(this).addClass("blues").siblings().removeClass("blues");
	})
	$(".yincang").click(function(){
  		$(".shouqi").toggle();
	});
	
	
   
});
//设置wrapper的最小高度
function setMinHeight() {
    var h1=$(window).height();
    var h2=$(".nav-top").height();
    var h3=$(".footer").height();
    var h7=$(".tishibox").height();
    var h8=$(".read").height();
    var h4=h1-h2;
    var h5=h4-h3;
    var h6=h4+h3;
    var h9=h6-h7-h8;
    $(".wrapper").css("max-height",h1);
    $(".content").css("height",h4);
    $(".contentright").css("min-height",h4);
    $(".contentrighttwo").css("max-height",h4);
    $(".contentleft").css("height",h4);
    $(".notice").css("height",h6);
    $(".news").css("height",h9);
}

/*头部通知*/
function showHeaderMessage() {
    $(document).on("click",function (e) {
        if($(e.target).parents('.message-hidden-box').length == 0){
            $(".message-hidden-box").fadeOut();
        }
    });
    $("#showMessageBox").on("click",function (e) {
        e.stopPropagation()
        $(".message-hidden-box").fadeToggle();
    });
}















