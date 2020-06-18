app.controller("departmentController",function ($scope,$location,departmentService,$filter) {
    $scope.report = {} ; //选中的举报
    $scope.imgListSrc=[
        "/imgbox/img/yp2.png",
        "/imgbox/img/st2.png",
        "/imgbox/img/cc2.png",
        "/imgbox/img/td2.png",
        "/imgbox/img/ls2.png",
        "/imgbox/img/qt2.png"
    ];
    $scope.imgListSrcs=[
        "/imgbox/img/yp3.png",
        "/imgbox/img/st3.png",
        "/imgbox/img/cc3.png",
        "/imgbox/img/td3.png",
        "/imgbox/img/ls3.png",
        "/imgbox/img/qt3.png"
    ];
    //获取id
    $scope.voCondition={};
    $scope.voAddInspectList={};
    $scope.voAddInspectList.contont="";
    $scope.list=[];
    $scope.voCondition.type="0";
    $scope.voCondition.isRead=0;
    $scope.voCondition.startTime = '';
    $scope.voCondition.endTime = '';
    $scope.voCondition.transactionState=1;

    $scope.voAddInspectList.reportIds=[];
    $scope.reportIds=[];
    $scope.voCondition.pageNum=1;
    $scope.voCondition.pageSize=10;
    $scope.changetransactionState=function(num){
        $scope.voCondition.transactionState=num;
        $scope.reloadlist();
    };
    //获取类型
    $scope.ss=0;
    $scope.changetype=function(num){
        $scope.ss=num;
        $("#" + num).addClass('activespan').parent(".typeli").siblings().children().removeClass("activespan");
        $scope.voCondition.type=num;
        $scope.reloadlist();
    };
    $scope.findReportModulars=function(){
        departmentService.findReportModulars().success(
            function (respone) {
                $scope.modulars=respone.data
            }
        )
    };
    //获取是否已读
    $scope.isRead=function(num){
        $scope.voCondition.isRead=num;
        $scope.reloadlist();
    };
    //获取时间
    $scope.time=function(num){
        $scope.today = new  Date();
        $scope.timeString = $filter('date')($scope.today, 'yyyy-MM-dd');
        if(num==0){
            $scope.voCondition.startTime = $scope.timeString + " 00:00:01";
            $scope.voCondition.endTime = $scope.timeString + " 23:59:59";
            $scope.paginationConf.currentPage=1;
        }else if(num==1){
            $scope.voCondition.startTime = $filter('date')($scope.today-86400000, 'yyyy-MM-dd') + " 00:00:01";
            $scope.voCondition.endTime = $filter('date')($scope.today-86400000, 'yyyy-MM-dd') + " 23:59:59";
            $scope.paginationConf.currentPage=1;
        }else if(num==2){
            $scope.voCondition.startTime = getLastSevenDays($scope.timeString)+ " 00:00:01";
            $scope.voCondition.endTime = $scope.timeString + " 23:59:59";
            $scope.paginationConf.currentPage=1;
        }else if(num==3){
            $scope.voCondition.startTime = getPreMonth($scope.timeString)+ " 00:00:01";
            $scope.voCondition.endTime = $scope.timeString + " 23:59:59";
            $scope.paginationConf.currentPage=1;
        }else {
            $scope.voCondition.startTime = '';
            $scope.voCondition.endTime = '';
            $scope.paginationConf.currentPage=1;
        }
        $scope.reloadlist();
    };


    $scope.findall=function () {

        departmentService.findall($scope.voCondition).success(function (response) {
            $scope.list=response.data.data;
            if(response.data.data.length>0){
                for (var i = 0; i <$scope.list.length ; i++) {
                    if($scope.list[i].content.length>100){
                        $scope.list[i].content=$scope.list[i].content.substr(0,100)
                    }
                }
            }
            $scope.paginationConf.totalItems = response.data.total;
            $scope.paginationConf.currentPage = response.data.pageNum;
            $scope.paginationConf.itemsPerPage = response.data.pageSize;
            console.log($scope.list);
            $scope.report = {} ;
        })
    };
    /*时间插件*/
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //日期范围
        laydate.render({
            elem: '#test6'
            ,theme: '#3299fe'
            ,range: true,
            done: function(value, date, endDate){
                $scope.voCondition.startTime  = value.substring(0, 10) + " 00:00:01";
                $scope.voCondition.endTime = value.substring(13, 23) + " 23:59:59";
                $scope.reloadlist();
            }
        });

    });
    $scope.voUpdateModular={};
    //线索研判
    $scope.changem=function(reportId){
        var opt=$("#"+reportId).val();
        $scope.voUpdateModular.reportId=reportId;
        $scope.voUpdateModular.modularId=opt;
        departmentService.updateModular($scope.voUpdateModular).success(
            function (respone) {
                $scope.reloadlist();
            }
        )
    };

    $('#all').on('ifChecked', function (event) {
            $('input').iCheck('check');
    });
    $('#all').on('ifUnchecked', function(event){
        $('input').iCheck('uncheck');
    });
    //单列 加入核查,开始
    $scope.addInspect=function (reportId) {
          window.location.href="/PopularOpinion/departmentDetails?id="+reportId;
    };
    //线索详情
    $scope.detail=function(reportId){
        window.location.href="/PopularOpinion/networklistDetails?id="+reportId;
    };
    var clt=true;
    //删除单个
    $scope.delete=function (id) {
        BootstrapDialog.show({
            title: '确认',
            message: '你确认要删除吗？',
            onshow: function(dialog) {
            },
            buttons: [{
                label: '确认',
                action: function(dialogItself) {
                    $scope.reportIds.push(id);
                    departmentService.delete($scope.reportIds).success(
                        function (response) {
                            $scope.reportIds=[];
                            $scope.reloadlist();
                        }
                    )
                    dialogItself.close();
                }
            }, {
                label: '取消',
                action: function(dialogItself) {
                    dialogItself.close();
                }
            }]
        });
    };
    //删除出个
    $scope.deletes=function () {
        var lists =$scope.report.valueOf();
        var ins=0;
        for(var i in lists){
            if (lists[i]){
                ins++;
            }
        }
        if(ins==0){
            return;
        }
        var lists =$scope.report.valueOf();
        var ins=0;
        for(var i in lists){
            if (lists[i]){
                ins++;
            }
        }
        if(ins==0){
            return;
        }
        BootstrapDialog.show({
            title: '确认',
            message: '你确认要删除吗？',
            onshow: function(dialog) {
            },
            buttons: [{
                label: '确认',
                action: function(dialogItself) {
                    var lists =$scope.report.valueOf();
                    for(var i in lists){
                        if (lists[i]){
                            $scope.reportIds.push(i);
                        }
                    }
                    departmentService.delete($scope.reportIds).success(
                        function (response) {
                            $scope.reportIds=[];

                            $scope.reloadlist();
                        }
                    )
                    dialogItself.close();
                }
            }, {
                label: '取消',
                action: function(dialogItself) {
                    dialogItself.close();
                }
            }]
        });
    };

    function getPreMonth(date) {
        var arr = date.split('-');
        var year = arr[0]; //获取当前日期的年份
        var month = arr[1]; //获取当前日期的月份
        var day = arr[2]; //获取当前日期的日
        var days = new Date(year, month, 0);
        days = days.getDate(); //获取当前日期中月的天数
        var year2 = year;
        var month2 = parseInt(month) - 1;
        if (month2 == 0) {
            year2 = parseInt(year2) - 1;
            month2 = 12;
        }
        var day2 = day;
        var days2 = new Date(year2, month2, 0);
        days2 = days2.getDate();
        if (day2 > days2) {
            day2 = days2;
        }
        if (month2 < 10) {
            month2 = '0' + month2;
        }
        var t2 = year2 + '-' + month2 + '-' + day2;
        return t2;
    }
    function getLastSevenDays(date){
        var date = date || new Date(),
            timestamp,
            newDate;
        if(!(date instanceof Date)){
            date = new Date(date.replace(/-/g, '/'));
        }
        timestamp = date.getTime();
        newDate = new Date(timestamp - 7 * 24 * 3600 * 1000);
        var month = newDate.getMonth() + 1;
        month = month.toString().length == 1 ? '0' + month : month;
        var day = newDate.getDate().toString().length == 1 ? '0' + newDate.getDate() :newDate.getDate();
        return [newDate.getFullYear(), month, day].join('-');
    }

    $scope.paginationConf = {
        currentPage : 1,//当前页
        totalItems : 10,//总数
        itemsPerPage : 10,//每页个数
        perPageOptions : [ 10, 20, 30, 40, 50 ],//分页选项
        onChange : function() {//当更改页码时，自动触发事件
            $scope.reloadlist();
        }
    };
    // //刷新列表
    $scope.reloadlist = function() {
        $scope.findPage($scope.paginationConf.currentPage,
            $scope.paginationConf.itemsPerPage);
    }
    // //分页
    $scope.findPage = function(pageNum, pageSize) {
        if(pageNum==0){
            pageNum=1;
            $scope.paginationConf.currentPage=1;
        }
        $scope.voCondition.pageNum=pageNum;
        $scope.voCondition.pageSize=pageSize;
        $scope.findall();
    }
    $scope.changeModularId=function (reportId) {
        for (var  i = 0; i < $scope.voAddInspectList.reportIds.length; i++) {
            if($scope.voAddInspectList.reportIds[i]==reportId){
                $scope.voAddInspectList.reportIds.remove(reportId)
            }else {
                $scope.voAddInspectList.reportIds.push(reportId);
            }
        }

    };

});