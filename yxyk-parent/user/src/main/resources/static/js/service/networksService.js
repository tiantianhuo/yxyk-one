app.service("networksService",function ($http) {
    this.updateModular=function (voUpdateModular) {
        return $http.post("../../thread/updateModular",voUpdateModular)
    };
    this.saveIsRead=function (reportId) {
        return $http.post("../../thread/saveIsRead",reportId)
    };
    this.findReportModulars=function () {
        return $http.post("../../thread/findReportModulars")
    };
    this.findall=function (voCondition) {
        return $http.post("../../thread/findReportCluePage",voCondition)
    };
    this.addInspectList=function (voAddInspectList) {
        return $http.post("../../thread/addInspectList",voAddInspectList)
    };
    this.delete=function (reportIds) {
        return $http.post("../../thread/deleteByIds",reportIds)
    }
});
