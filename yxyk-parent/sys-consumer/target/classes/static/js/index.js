$(document).ready(function(){
	// 百度地图API功能
    var map = new BMap.Map('map');
    /*var poi = new BMap.Point(116.307852,40.057031);*/
    var poi = new BMap.Point(112.862026,35.495814);
    map.centerAndZoom(poi, 16);
    map.enableScrollWheelZoom();  
    var overlays = [];
	var overlaycomplete = function(e){
        overlays.push(e.overlay);
    };
    var styleOptions = {
        strokeColor:"red",    //边线颜色。
        fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
        strokeWeight: 3,       //边线的宽度，以像素为单位。
        strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
        fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
        strokeStyle: 'solid' //边线的样式，solid或dashed。
    }
    //实例化鼠标绘制工具
    var drawingManager = new BMapLib.DrawingManager(map, {
        isOpen: false, //是否开启绘制模式
        enableDrawingTool: true, //是否显示工具栏
        drawingToolOptions: {
            anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
            offset: new BMap.Size(5, 5), //偏离值
        },
        circleOptions: styleOptions, //圆的样式
        polylineOptions: styleOptions, //线的样式
        polygonOptions: styleOptions, //多边形的样式
        rectangleOptions: styleOptions //矩形的样式
    });  
	 //添加鼠标绘制工具监听事件，用于获取绘制结果
    drawingManager.addEventListener('overlaycomplete', overlaycomplete);
    function clearAll() {
		for(var i = 0; i < overlays.length; i++){
            map.removeOverlay(overlays[i]);
        }
        overlays.length = 0   
    }
})


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
    $(".contentright").css("min-height",h4);
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

}
