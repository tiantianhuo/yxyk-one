function initData() {

    $.post("/apis/procuratorate/findProcuratorate", function (result) {
        if (result.success) {
            initTree(result.data)
        }
    });

}

function initTree(item) {
    var str = "";
    if (item.name !== "") {
        str += '<h2 class="layui-colla-title" data_id="' + item.id + '">' + item.name + '<i class="layui-icon layui-colla-icon"></i></h2>';
        if (item.childrenList.length != 0) {
            str += "<div class=\"layui-colla-content layui-show\"><div class=\"layui-collapse\" lay-accordion=\"\">";
            $.each(item.childrenList, function (i, obj) {
                str += "<div class=\"layui-colla-item\">";
                str += initTree(obj);
                str += "</div>";
            })
        }
    }
}