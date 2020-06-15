layui.use('form', function(){
  var form = layui.form;
  
  //监听提交
  form.on('submit(formDemo)', function(data){
    layer.msg(JSON.stringify(data.field));
    return false;
  });
});

// 日期选择器
layui.use('laydate', function(){
  var laydate = layui.laydate;
  
  //执行一个laydate实例
  laydate.render({
    elem: '#test1' //指定元素
  });
});
//test2
layui.use('laydate', function(){
  var laydate = layui.laydate;
  
  //执行一个laydate实例
  laydate.render({
    elem: '#test2' //指定元素
  });
});


// 分页
layui.use(['laypage', 'layer'], function(){
  var laypage = layui.laypage
  ,layer = layui.layer;
  
 
  
  //完整功能
  laypage.render({
    elem: 'demo1'
    ,count: 100
    ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
    ,jump: function(obj){
      console.log(obj)
    }
  });
});

function setMinHeight() {
    var h1=$(window).height();
    var h2=$(".nav-top").height();
    var h3=$(".footer").height();
    var h4=h1-h2-h3;
    var h5=$(".span").height();
    var h7=$(".guandian").height();
    var h6=h4-h5-78;
    $(".content").css("min-height",h4);
    $(".center").css("min-height",h6);
    $(".dmc_left").css("height",h7);
    $(".dmc_right").css("height",h7);   
}