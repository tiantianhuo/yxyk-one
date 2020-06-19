$(function(){
	var myChart1 = echarts.init(document.getElementById('main1'));
	var myChart2 = echarts.init(document.getElementById('main2'));
	var myChart5 = echarts.init(document.getElementById('main5'));
	var myChart6 = echarts.init(document.getElementById('main6'));
	var myChart7 = echarts.init(document.getElementById('main7'));
	/*var myChart3 = echarts.init(document.getElementById('main3'));
	var myChart4 = echarts.init(document.getElementById('main4'));*/
	option1 = {
		 // 图表的位置
	    grid: {
	        position: 'center',
	     },
	     tooltip : {
	     //雷达图的tooltip不会超出div，也可以设置position属性，position定位的tooltip 不会随着鼠标移动而位置变化，不友好
	        confine: true,
	        enterable: true, //鼠标是否可以移动到tooltip区域内
	     },
	    radar: {
			//shape: 'circle',
 			splitNumber: 2, // 雷达图圈数设置
 			nameGap : 2, // 图中工艺等字距离图的距离
 			center:['40%','49%'], // 图的位置
	        name: {
	            textStyle: {
	                color: '#fff',
	                borderRadius: 3,
	                padding: [3, 5]
	           }
	        },
	        // 设置雷达图中间射线的颜色
	        axisLine: {
	            lineStyle: {
	                color: '#2e406d',
	                },
	        },
	        indicator: [
	           { name: '审查批捕', max: 6500},
	           { name: '审查起诉', max: 16000},
	           { name: '诉讼监督', max: 30000},
	           { name: '发回重审', max: 38000},
	           { name: '重审', max: 52000}
	        ],
	         //雷达图背景的颜色，在这儿随便设置了一个颜色，完全不透明度为0，就实现了透明背景
	        splitArea : {
	            show : false,
	            areaStyle : {
	                color: '#050949', // 图表背景的颜色
	            },
	        },
	        splitLine : {
	            show : true,
	            lineStyle : {
	                width : 1,
	                color : '#2b3b68', // 设置网格的颜色
	            },
	        },

	    },
	    series: [{
	        name: '789',
	        type: 'radar',
	        // areaStyle: {normal: {}},
	        data : [
	            {
	                value : [4300, 10000, 28000, 35000, 50000, 19000],
	                name : '123',
	                  // 设置区域边框和区域的颜色
	                itemStyle: {
	                    normal: {
	                        color: '#0ae4fc',
	                        lineStyle: {
	                            color: '#3e82ef'
	                        }
	                    },
	                }
	            },
	             {
	                value : [5000, 14000, 28000, 31000, 42000, 21000],
	                name : '456',
	                  // 设置区域边框和区域的颜色
	                itemStyle: {
	                    normal: {
	                        color: '#427aee',
	                        lineStyle: {
	                            color: '#3e82ef'
	                        }
	                    },
	                }
	            }
	        ]
	    }]
};

	
	option2 = {
	    title : {
	        text: '',
	        subtext: '',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	    	orient: 'vertical',
	        x : '55%',
			//图例文字样式
	        textStyle:{
	                	color:'#fff',
	              		fontSize:12,
	              		fontWeight:'700'
	          },
	        data:['审查批捕','审查起诉','诉讼监督','抗诉案件']
	    },
	    grid: {
	        left: '3%',
	        right: '3%',
	        bottom: '3%',
	        containLabel: true
        },
	    toolbox: {
	        show : false,
	    },
	    calculable : true,
	   color:['#08a1f1','#b94ded','#ffc451','#f1f084','#6ad49f','#0577eb','#ffc451','#f1f084','#eb0556'],//自己设置扇形图颜色
	    series : [
	       
	        {
	            name:'图例占比',
	            type:'pie',
	            radius : [20, 75],
	            center : ['30%','50%'],
	            roseType : 'radius',
	            x: '50%',               // for funnel
	            max: 40,                // for funnel
	            sort : 'ascending',     // for funnel
	            data:[
	                {value:40, name:'审查批捕'},
	                {value:30, name:'审查起诉'},
	                {value:25, name:'诉讼监督'},
	                {value:15, name:'抗诉案件'}
	            ],
			//标线的属性设置，以及显示的文字
	         itemStyle: {
	           normal:{
	             label:{
	             show:true,
	             formatter: '{c}个',
	            textStyle:{
	                	/*color:'#fff',*/
	              		fontSize:12,
	              		fontWeight:'700'
	                }
	               
	             },
				 //标线长度，宽度
	             labelLine:{
	            	show:true,
	               length:10,
	               lineStyle:{
	               	width:2
	               }
	             }
	             },
	             emphasis: {
	                   shadowBlur: 10,
	                   shadowOffsetX: 0,
	                   shadowColor: 'rgba(0, 0, 0, 0.5)'
	               }
	           },
	        
	        }
	    ]
	};


	

	option3 = {
		grid: {
	        left: '3%',
	        right: '3%',
	        bottom: '3%',
	        containLabel: true
        },
	    tooltip: {
	        trigger: 'item',
	        formatter: "{a} <br/>{b}: {c} ({d}%)"
	    },
	    color:['#08a1f1','#b94ded','#ffc451','#f1f084','#6ad49f','#0577eb'],//自己设置扇形图颜色
	    series: [
	       
	        {
	            name:'访问来源',
	            type:'pie',
	            radius: ['40%', '65%'],
	            label: {
	               
	            },
	            data:[
	                {value:335, name:'电话举报'},
	                {value:310, name:'网络'},
	                {value:234, name:'随手拍'},
	                {value:135, name:'网络舆情'},
	                {value:1048, name:'飞行取证'},
	                {value:102, name:'其他'}
	            ]
	        }
	    ]
	};
	

	option4 = {
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
			//图例文字样式
	        textStyle:{
	                	color:'#fff',
	              		fontSize:12,
	              		fontWeight:'700'
	          },
        data: ['民事公告', '公益诉讼','检查建议']
    },
    color:['#eae557','#0486ed','#57ee85'],//自己设置扇形图颜色
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true,
        height:150
    },
   xAxis : [
        {
            type : 'category',
            data : ['灵丘县院','大同市院','左云县院','新荣区院','天镇县院','阳高县院','广灵县院','城区院'],
            splitLine:{show: false},
            splitArea : {show : false},
            axisTick: {
                alignWithLabel: true
            },
            axisLine: {
                   lineStyle: {
                       type: 'solid',
                       color:'#fff',
                       width:'2'
                   }
            },
            axisLabel: {
                    nterval: 0,
                    textStyle: {
                       color: '#fff'
                   },
                    /*formatter:function(value)
                    {
                       return value.split("").join("\n");
                    }*/
                   interval:0,//横轴信息全部显示
				   rotate:30//-30度角倾斜显示
            }

        }
    ],
    yAxis : [
           {
               type : 'value',
               interval: 15,
               splitLine:{show: false},
               splitArea : {show : false},
               axisLine: {
                   lineStyle: {
                       type: 'solid',
                       color:'#fff',
                       width:'2'
                   }
               },
               axisLabel: {
                   textStyle: {
                       color: '#fff'
                   }
               }
           }
       ],
    series: [
        {
            name: '民事公告',
            type: 'bar',
            stack: 'one',
            label: {
                normal: {
                    show: false,
                    position: 'insideRight'
                }
            },
            data: [0, 7, 9, 12, 19, 7, 6,0]
        },
        {
            name: '公益诉讼',
            type: 'bar',
            stack: 'one',
            label: {
                normal: {
                    show: false,
                    position: 'insideRight'
                }
            },
            data: [4, 0, 8, 1, 16, 6, 4,8]
        },
        {
            name: '检查建议',
            type: 'bar',
            stack: 'one',
            label: {
                normal: {
                    show: false,
                    position: 'insideRight'
                }
            },
            data: [0, 11, 6, 20, 8, 2, 5,19]
        }
    ]
};


option5 = {
		grid: {
	        left: '0',
	        right: '10%',
	        top: '5%',
	        containLabel: true
        },
	     tooltip : {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            label: {
	                backgroundColor: '#6a7985'
	            }
	        }
	    },
	    xAxis: {
	    	name:'月份',
	    	splitLine:{show: false},//去除网格线
	        type: 'category',
	         axisLine: {
	            lineStyle: {
	              // 设置x轴颜色
	              color: '#fff'
	            }
           },
	        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月','8月', '9月', '10月', '11月', '12月'],
	        boundaryGap: false,
	    },
	    yAxis: {
	    	name:'办理案件数',
	    	splitLine:{show: false},//去除网格线
	        type: 'value',
	         axisLine: {
	            lineStyle: {
	              // 设置x轴颜色
	              color: '#fff'
	            }
           }
	    },
	    series: [
	    	{
            name: '审查批捕',
            data: [5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 85,90],
            type: 'line',
            // 设置小圆点消失
            // 注意：设置symbol: 'none'以后，拐点不存在了，设置拐点上显示数值无效
            // 设置折线弧度，取值：0-1之间
            smooth: 0.5,
             itemStyle:{
              normal:{
                // 拐点上显示数值
                label : {
                show: true
                },
                borderColor:'#bb3738',  // 拐点边框颜色
                lineStyle:{                 
                  width:2,  // 设置线宽
                  type:'solid'  //'dotted'虚线 'solid'实线
                }
              }
            }
         }
	    ],
	    color: ['#bb3738', '#bb3738']
};

option6 = {
	    title : {
	        text: '',
	        subtext: '',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    /*legend: {
	    	orient: 'vertical',
	        x : '55%',
			//图例文字样式
	        textStyle:{
	                	color:'#fff',
	              		fontSize:12,
	              		fontWeight:'700'
	          },
	        data:['审查批捕','审查起诉','诉讼监督','抗诉案件']
	    },*/
	    grid: {
	        left: '3%',
	        right: '3%',
	        bottom: '3%',
	        containLabel: true
        },
	    toolbox: {
	        show : false,
	    },
	    calculable : true,
	   color:['#08a1f1','#b94ded','#ffc451','#f1f084','#6ad49f','#0577eb','#ffc451','#f1f084','#eb0556'],//自己设置扇形图颜色
	    series : [
	       
	        {
	            name:'图例占比',
	            type:'pie',
	            radius : [40, 70],
	            center : ['15%','40%'],
	            /*roseType : 'radius',*/
	            x: '50%',               // for funnel
	            max: 40,                // for funnel
	            sort : 'ascending',     // for funnel
	            data:[
	                {value:40, name:'审查批捕'},
	                {value:30, name:'审查起诉'},
	                {value:25, name:'诉讼监督'},
	                {value:40, name:'审查批捕1'},
	                {value:30, name:'审查起诉2'},
	                {value:25, name:'诉讼监督3'},
	                {value:15, name:'抗诉案件'}
	            ],
			//标线的属性设置，以及显示的文字
	         itemStyle: {
	           normal:{
	             label:{
	             show:false,
	             formatter: '{c}个',
	            textStyle:{
	                	/*color:'#fff',*/
	              		fontSize:12,
	              		fontWeight:'700'
	                }
	               
	             },
				 //标线长度，宽度
	             labelLine:{
	            	show:true,
	               length:10,
	               lineStyle:{
	               	width:2
	               }
	             }
	             },
	             emphasis: {
	                   shadowBlur: 10,
	                   shadowOffsetX: 0,
	                   shadowColor: 'rgba(0, 0, 0, 0.5)'
	               }
	           },
	        
	        },
	        {
	            name:'图例占比',
	            type:'pie',
	            radius : [40, 70],
	            center : ['46%','40%'],
	            /*roseType : 'radius',*/
	            x: '50%',               // for funnel
	            max: 40,                // for funnel
	            sort : 'ascending',     // for funnel
	            data:[
	                {value:40, name:'审查批捕'},
	                {value:30, name:'审查起诉'},
	                {value:25, name:'诉讼监督'},
	                {value:80, name:'审查起诉2'},
	                {value:5, name:'诉讼监督3'},
	                {value:15, name:'抗诉案件'}
	            ],
			//标线的属性设置，以及显示的文字
	         itemStyle: {
	           normal:{
	             label:{
	             show:false,
	             formatter: '{c}个',
	            textStyle:{
	                	/*color:'#fff',*/
	              		fontSize:12,
	              		fontWeight:'700'
	                }
	               
	             },
				 //标线长度，宽度
	             labelLine:{
	            	show:true,
	               length:10,
	               lineStyle:{
	               	width:2
	               }
	             }
	             },
	             emphasis: {
	                   shadowBlur: 10,
	                   shadowOffsetX: 0,
	                   shadowColor: 'rgba(0, 0, 0, 0.5)'
	               }
	           },
	        
	        },
	        {
	            name:'图例占比',
	            type:'pie',
	            radius : [40, 70],
	            center : ['77%','40%'],
	            /*roseType : 'radius',*/
	            x: '50%',               // for funnel
	            max: 40,                // for funnel
	            sort : 'ascending',     // for funnel
	            data:[
	                {value:40, name:'审查批捕'},
	                {value:30, name:'审查起诉'},
	                {value:25, name:'诉讼监督'},
	                {value:15, name:'抗诉案件'}
	            ],
			//标线的属性设置，以及显示的文字
	         itemStyle: {
	           normal:{
	             label:{
	             show:false,
	             formatter: '{c}个',
	            textStyle:{
	                	/*color:'#fff',*/
	              		fontSize:12,
	              		fontWeight:'700'
	                }
	               
	             },
				 //标线长度，宽度
	             labelLine:{
	            	show:true,
	               length:10,
	               lineStyle:{
	               	width:2
	               }
	             }
	             },
	             emphasis: {
	                   shadowBlur: 10,
	                   shadowOffsetX: 0,
	                   shadowColor: 'rgba(0, 0, 0, 0.5)'
	               }
	           },
	        
	        }
	        
	    ]
	};

option7 = {
	 grid: {
        left: '1%',
        right: '7%',
        top: '10%',
        containLabel: true,
        height:160
    },
    xAxis: {
    	splitLine:{show: false},//去除网格线
        type: 'category',
        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月','12月'],
        axisLine: {
                   lineStyle: {
                       type: 'solid',
                       color:'#fff',
                       width:'2'
                   }
            }
    },
    yAxis: {
    	splitLine:{show: false},//去除网格线
        type: 'value',
        axisLine: {
                   lineStyle: {
                       type: 'solid',
                       color:'#fff',
                       width:'2'
                   }
        }
    },
    series: [{
        data: [120, 200, 150, 80, 70, 110, 130,120, 200, 150, 80, 70],
        type: 'bar'
    }],
    color: ['#5dc992']
};

myChart1.setOption(option1);
myChart2.setOption(option2);
/*myChart3.setOption(option3);
myChart4.setOption(option4);*/
myChart5.setOption(option5);
myChart6.setOption(option6);
myChart7.setOption(option7);
        
})
