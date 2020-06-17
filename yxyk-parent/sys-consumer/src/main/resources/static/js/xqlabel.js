function setMinHeight() {
    var h1=$(window).height();
    var h2=$(".nav-top").height();
    var h3=$(".footer").height();
    var h4=h1-h2-h3;
    var h5=h4-100;
    var h6=h4-120;
    $(".wrapper").css("min-height",h1);
    $(".content").css("min-height",h4);
    $(".contentleft").css("height",h4);
    $(".contentright").css("height",h4);
    $(".biaozhu").css("min-height",h5);
}