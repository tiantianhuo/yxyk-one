$(document).ready(function(){
	/*日历*/
	layui.use('laydate',function(){
      var laydate = layui.laydate;
      laydate.render({
        elem:'.cont_apptabtime'
      });
    });
    layui.use('laydate',function(){
      var laydate = layui.laydate;
      laydate.render({
        elem:'.cont_apptabtime1'
      });
    });
    /*表格不同行样式*/
    $(".table").find("tbody").find("tr").attr("bgColor", "#f4f6f8"); //为单数行表格设置背景颜色   
    $(".table").find("tbody").find("tr:even").attr("bgColor", "#fff"); //为双数行表格设置背颜色素  
    /*分页*/
    layui.use('laypage', function(){
  	var laypage = layui.laypage;
	     //完整功能
	    laypage.render({
	    	elem: 'demo7'
	    	,count: 100
	    	,layout: [ 'prev', 'page', 'next', 'limit', 'skip']
	    	,jump: function(obj){
	      	console.log(obj)
	    	}
	  	});
	});
	
	var h1=$(window).height();
    var h2=$(".nav-top").height();
    var h3=$(".footer").height();
    var h4=h1-h2-h3;
    $(".cont_appbox").css("min-height",h4);
    $(".cont_apptabbox").css("min-height",h4 - 110);
  
    $(".cont_apptabpad").css("min-height", h4- 110);
    $(".cont_apptabpad").find('.cont_apptab').css("min-height", h4- 110);
    $(".off_app_talsr").css("height", h4-110);
    /*富文本*/
    
	var editor;
	KindEditor.ready(function(K) {
	editor = K.create('textarea[name="content"]', {
		allowFileManager : true
		});

	});
	var right_tals = $(".off_app_tals").height();
	$(".off_app_talsl").find('form').find("textarea").css("height", right_tals - 2);
	
	
	$(".off_app_file_del").click(function(){
		$(this).parent("p").parent(".off_app_talsrc").parent(".off_app_talsrb").hide();
		
	})
	
	
})
