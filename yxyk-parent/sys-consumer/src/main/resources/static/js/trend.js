$(function () {
    setMinHeight();	
});
//设置wrapper的最小高度
function setMinHeight() {
    var h1=$(window).height();
    var h2=$(".nav-top").height();
    var h3=$(".footer").height();
    var h4=h1-h2-h3;
    var h5=h4-100;
    $(".wrapper").css("min-height",h1);
    $(".content").css("min-height",h4);
    $(".contentleft").css("height",h4);
    $(".contentright").css("height",h4);
    $(".contentone").css("min-height",h4);
    $(".setbox").css("min-height",h5); 
    var h6=$(".contentbox").height();
    var h7=h4-85;
    var h8=$(".shujubox").height();
    var h10=$(".legend").height();
    var h11=$(".legendtop").height();
    var h9=h10-h11-h8;
    var w1=$(".shujubox").width();
    $(".indexcontent").css("height",h7);
    $(".trendcontent").css("height",h7);
    $("#allmap").css("height",h7);
    $("#main").css("width",w1);
    $(".propelling").css("height",h9);
    
    var w2=$(".trendcontent").width()-40;
    $("#main1").css("width",w2);

}
