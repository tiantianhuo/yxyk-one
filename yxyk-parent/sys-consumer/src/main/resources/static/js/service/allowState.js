function updateAllowState() {
    var allowState = $("input[name='iChecks']:checked").val();
    var time = $("#test6").val();
    var startTime = null;
    var endTime = null;
    if (time !== "") {
        var arr = time.split(" ");
        startTime = arr[0];
        endTime = arr[2];
    }
    var forever = $("#forever").is(':checked') ? 1 : 0;
    var msgId=getUrlParam("messageId");
    var param = {
        allowState: allowState,
        startTime: startTime,
        endTime: endTime,
        forever: forever,
        msgId:msgId
    }
    $.ajax({
        url: '/apis/user/updateAllowState',
        type: 'post',
        data: param,
        success: function (res) {
            console.log(res)
            layui.use('layer', function () {
                var layer=layui.layer;
                if (res.success == true) {
                    layer.msg("保存成功");
                }else {
                    layer.msg(res.msg);
                }
            })
        }
    })
}

function initForm() {
    $.ajax({
        url: '/apis/user/getAllowState',
        type: 'post',
        success: function (res) {
            console.log(res)
            layui.use('layer', function () {
                var layer=layui.layer;
                if (res.success == true) {
                    var roAllowState=res.data;
                    if(roAllowState.allowState==null||roAllowState.allowState===0){
                        $("input[name='iChecks'][value='0']").iCheck('check')
                    }else {
                        $("input[name='iChecks'][value='1']").iCheck('check')
                    }
                    if(roAllowState.startTime!=null&&roAllowState.endTime!=null){
                        $("#test6").val(roAllowState.startTime+' - '+roAllowState.endTime);
                    }
                    if(roAllowState.forever!==null&&roAllowState.forever!==0){
                        $("#forever").iCheck('check');
                    }
                }else {
                    layer.msg(res.msg);
                }
            })
        }
    })
}
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return r[2];
    return "";
}