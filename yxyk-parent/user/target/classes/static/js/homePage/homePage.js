/**************************banner*******************************/
//法律法规

// 查询所有banner
function findAllBanner(curr, limit, time, type) {
    var name = $("#name").val();
    var t = $("#test6").val();
    if (t != '') {
        time = t;
    }
    if (time != null && time != "") {
        var times = time.split(" ");
        var startTime = times[0] + ' 00:00:00';
        var endTime = times[2] + ' 23:59:59';
    }
    $.ajax({
        url: "/banners/findAllBanner",
        type: "post",
        data: {
            "startTime": startTime,
            "endTime": endTime,
            "pageNum": curr,
            "pageSize": limit,
            "name": name,
            "type":type
        },
        success: function (result) {
            console.log(result);
            if (result.success) {
                var data = result.data;
                if (data.list.length != 0) {
                    // 序号
                    var number = (curr - 1) * limit;
                    var str = '';
                    $(data.list).each(function (i, obj) {
                        number++;
                        var openState = obj.openState;

                        var class1 = "yuan";
                        if (openState == 1) {
                            openState = "开启";
                        } else {
                            openState = "关闭";
                            class1 = "yuan2";
                        }
                        str +=
                            '<tr>' +
                            '<td>' + number + '</td>' +
                            '<td><img src="' + obj.path + '" /></td>' +
                            '<td>' + obj.title + '</td>' +
                            '<td><span class=' + class1 + '></span>' + openState + '</td>' +
                            '<td>' + obj.createTime + '</td>' +
                            '<td>' +
                            '<span>' +
                            '<select class="selects" id="change_'+ obj.id +'" onchange="changeImage('+ obj.id +')">' +
                            '<option value="" >管理</option>';
                        if (number != 1) {
                            str += '<option value="3" >置顶</option>';
                        }
                        if (number != data.list.length) {
                            str += '<option value="4" >置底</option>';
                        }
                        //第一个不需要上移
                        if (number != 1 && data.list.length > 1) {
                            str += '<option value="1" >上移</option>';
                        }
                        //最后一个不需要下移
                        if (number != data.list.length && data.list.length > 1) {
                            str += '<option value="2">下移</option>';
                        }
                        str += '</select>' +
                            '</span>' +
                            '<span class="look-one"  onclick="look(' + obj.id + ')">查看</span>' +
                            '<span onclick="deleteOne(' + obj.id + ')">删除</span>' +
                            '</td>' +
                            '</tr>';
                    });
                    $("#bannerBody").html(str);
                    // 大于10条显示 分页
                    if (data.totalCount > limit) {
                        $("#demo").show();
                        page(data.totalCount, curr, limit,type);
                    } else {
                        $("#demo").hide();
                    }
                } else {
                    $("#demo").hide();
                    // 无数据展示图片
                    $("#bannerBody").html('<td colspan="6"><img src="/static/common/noneDate.png" style="width: 123px;margin:50px 0;"></td>');
                }
            }

        }
    })
}

//查看详情
function look(id) {
    window.location.href = "../Index/banner?id=" + id;
}

// 修改图片位置
function changeImage(obj) {
    var number =  $("#change_"+obj).val();
    $.post("/banners/changeBannerIndex", {id: obj, event: number}, function (result) {
        if (result) {
            findAllBanner(1, 10, null);
        } else {
            layer.msg("移动失败");
        }
    });
}

//删除
function deleteOne(id) {
    layer.confirm("确定要删除吗?", {skin: 'demo-class'}, function () {
        $.ajax({
            url: "/banners/deleteBannerById",
            type: "post",
            data: {
                id: id
            },
            success: function (result) {
                console.log(result);
                if (result.success) {
                    layer.closeAll('dialog'); //关闭信息框
                    findAllBanner(1, 10, null);
                } else {
                    layer.msg(result.msg);
                }
            }
        })
    });
}

/*分页*/
function page(count, curr, limit,type) {
    layui.use(['laypage', 'layer'], function () {
        var laypage = layui.laypage
            , layer = layui.layer;
        //完整功能
        laypage.render({
            elem: 'demo'
            , count: count
            , curr: curr
            , limit: limit
            , layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
            , jump: function (obj, first) {
                curr = obj.curr;
                limit = obj.limit;
                if (!first) {
                    findAllBanner(curr, limit, null,type);
                }
            }
        });

    });
}

/**************************公告*******************************/
// 查询所有公告
function findAllAffiche(curr, limit, time,type) {
    var searchAffiche = $("#searchAffiche").val();

    var t = $("#test2").val();
    if (t != '') {
        time = t;
    }
    var state = $('#isTop option:selected').val();
    if(state==undefined){
        state = -1;
    }
    if (time != null && time != "") {
        var times = time.split(" ");
        var startTime = times[0] + ' 00:00:00';
        var endTime = times[2] + ' 23:59:59';
    }
    $.ajax({
        url: "/apis/affiche/findAll",
        type: "post",
        data: {
            "startTime": startTime,
            "endTime": endTime,
            "pageNum": curr,
            "pageSize": limit,
            "name": searchAffiche,
            "state": state,
            "type":type
        },
        success: function (result) {
            console.log(result);
            if (result.success) {
                var data = result.data;
                if (data.list.length != 0) {
                    // 序号
                    var number = (curr - 1) * limit;
                    var str = '';
                    $(data.list).each(function (i, obj) {
                        number++;
                        var state = obj.state;
                        str += '<tr>' +
                            '<td class="td-c">' + number + '</td>' +
                            '<td>' + obj.title + '</td>' +
                            '<td>' + obj.outline + '</td>' +
                            '<td class="td-c">' + obj.createTime + '</td>' +
                            '<td class="td-c">';
                        if (state === 0) {
                            str += '<span class="z-top" onclick="changeState(' + obj.id + ',' + 1 + ')">置顶</span>';
                        } else {
                            str += '<span class="z-top" onclick="changeState(' + obj.id + ',' + 2 + ')">取消置顶</span>';
                        }
                        str += '<span class="look-two" onclick="lookAffiche(' + obj.id + ')">查看</span>' +
                            '<span data-toggle="modal" data-target="#delete" onclick="delAffiche(' + obj.id + ')" >删除</span>' +
                            '</td>' +
                            '</tr>';
                    });
                    $("#afficheBody").html(str);
                    // 大于10条显示 分页
                    if (data.totalCount > limit) {
                        $("#demo").show();
                        page2(data.totalCount, curr, limit,type);
                    } else {
                        $("#demo").hide();
                    }
                } else {
                    $("#demo").hide();
                    // 无数据展示图片
                    $("#afficheBody").html('<td colspan="5"><img src="/static/common/noneDate.png" style="width: 123px;margin:50px 0;"></td>');
                }
            }

        }
    });
}
//修改置顶状态
function changeState(id,number){
    $.post("/apis/affiche/changeState", {id: id, event: number}, function (result) {
        if (result) {
            findAllAffiche(1, 10, null);
        } else {
            layer.msg("操作失败");
        }
    });
}
//查看公告
function lookAffiche(id){
    window.location.href = "../Index/gongGao?id=" + id;
}
//删除公告
function delAffiche(id){
    layer.confirm("确定要删除吗?", {skin: 'demo-class'}, function () {
        $.ajax({
            url: "/apis/affiche/deleteById",
            type: "post",
            data: {
                id: id
            },
            success: function (result) {
                console.log(result);
                if (result.success) {
                    layer.closeAll('dialog'); //关闭信息框
                    window.location.href = "/Index/index?liNumber=1";
                } else {
                    layer.msg(result.msg);
                }
            }
        })
    });
}
//分页
function page2(count, curr, limit,type) {
    layui.use(['laypage', 'layer'], function () {
        var laypage = layui.laypage
            , layer = layui.layer;
        //完整功能
        laypage.render({
            elem: 'demo'
            , count: count
            , curr: curr
            , limit: limit
            , layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
            , jump: function (obj, first) {
                curr = obj.curr;
                limit = obj.limit;
                if (!first) {
                    findAllAffiche(curr, limit, null,type);
                }
            }
        });

    });
}

/**************************举报指引*******************************/
//查看举报指引
function findGuide(){
    id = $("#id").val();
    $.ajax({
        url: "/apis/guidance/findOne",
        type: "post",
        data: {},
        success: function (result) {
            console.log(result);
            var content = result.data.content;
            if (result.success) {
                ue.ready(function () {//编辑器初始化完成再赋值
                    ue.setContent(content);  //赋值给UEditor
                });
                booka.innerHTML = content;
            } else {
                console.log("查询出错");
            }
        }
    });
}

/***
 * 保存指引
 */
function saveGuidance() {
    var content = ue.getContent();
    $.post("/apis/guidance/add", {content: content}, function (result) {
        if (result) {
            layer.msg("修改成功!");
            findGuide();
        } else {
            layer.msg("操作失败");
        }
    });
}
