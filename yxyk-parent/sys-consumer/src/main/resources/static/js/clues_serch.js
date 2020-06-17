$(document).ready(function(){
	var width = $(window).width();
	var leftw = $(".contentleft").width();
	var rightw = width - leftw;
	$(".serch_bootom").css("width",rightw);
	 /*选择框*/
    $('input').iCheck({
    	checkboxClass: 'icheckbox_minimal-blue',
        radioClass: 'iradio_minimal-blue',
        increaseArea: '20%' // optional
    });
    
    $(".serch_key").find("span").click(function(){
    	$(".serch_inp").val( $(this).text());
    })
   
})
