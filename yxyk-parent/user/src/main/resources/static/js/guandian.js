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
  
  laydate.render({
    elem: '#test16'
    ,type: 'datetime'
    ,range: '~'
    ,format: 'yyyy-M-d'
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
    var h6=h4-h5-78;
    $(".content").css("min-height",h4);
     $(".center").css("min-height",h6);
}
