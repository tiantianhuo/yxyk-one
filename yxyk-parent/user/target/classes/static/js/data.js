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











$(function(){
	var h1=$(window).height();
    var h2=$(".dataheader").height();
    var h3=$(".datafooter").height();
    var h4=h1-h2-h3-30;
    var h5=h1-h2-h3-110;
    $(".datacenter").css("height",h4);
    $(".contentL").css("height",h5);
    $(".contentC").css("height",h5);
    $(".contentR").css("height",h5);
    
    
    
    
    
    
    
})
