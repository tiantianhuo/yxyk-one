//初始化模块
var app = angular.module("xiansuo", ['tm.pagination']);
app.directive('icheck', ['$timeout', '$parse', function ($timeout, $parse) {
        return {
            restrict: 'A',
            require: '?ngModel',
            link: function (scope, element, attr, ngModel) {
                $timeout(function () {
                    var value = attr.value;

                    $(element).iCheck({
                        checkboxClass: attr.checkboxClass || 'icheckbox_minimal-blue',
                        radioClass: attr.radioClass || 'iradio_minimal-blue'
                    }).on('ifChanged', function (e) {
                        var num=0;
                        for (var i = 0; i < $('input[type=checkbox]').length; i++) {
                            if($('input[type=checkbox]')[i].checked){
                                $(".caozuo").addClass("a")
                            }else {
                                num++;
                            }
                            if(num==$('input[type=checkbox]').length){
                                $(".caozuo").removeClass("a")
                            }
                        }
                        // $('input[type=checkbox]').each(function (i, obj) {
                        //
                        // });

                        if ($(element).attr('type') === 'checkbox' && attr['ngModel']){
                            scope.$apply(function () {
                                return ngModel.$setViewValue(e.target.checked);
                            });
                        }
                        if ($(element).attr('type') === 'radio' && attr['ngModel']) {
                            return scope.$apply(function () {
                                return ngModel.$setViewValue(value);
                            });
                        }
                    });
                    var a=1;
                    if(a==1){
                        a++;
                    }else {
                        function update(checked) {
                            if (attr.type === 'radio') {
                                ngModel.$setViewValue(value);
                            } else {
                                ngModel.$setViewValue(checked);
                            }
                        }
                        scope.$watch(attr.ngChecked, function (checked) {
                            if (typeof checked === 'undefined') checked = !!ngModel.$viewValue;
                            update(checked)
                        }, true);
                        scope.$watch(attr.ngModel, function (model) {
                            $(element).iCheck('update');
                        }, true);
                    }
                })
            }
        }
    }]);


