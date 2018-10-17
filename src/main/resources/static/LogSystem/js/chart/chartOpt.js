/*========================================= 应用运动状态监测  =======================================*/

/**
 * 运行次数统计
 * 柱状图
 * @param {Array} data X坐标配置
 * @param {Array} value 对应X轴的具体数值 
 */
function getLineOption(data, value) {
	return option1 = {
		color: ['#3398DB'],
		title: {
			text: '运行次数统计',
			x: 'left',
			y: 'top',
			textStyle: {
				fontSize: 18,
				fontWeight: 'bold',
			}
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: { // 坐标轴指示器，坐标轴触发有效
				type: 'line' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		grid: {
			left: '4%',
			right: '5%',
			bottom: '40px',
			containLabel: true
		},
		xAxis: [{
			type: 'category',
			data: data,
			axisTick: {
				alignWithLabel: true
			}
		}],
		yAxis: [{
			type: 'value'
		}],
		series: [{
			name: '数量',
			type: 'bar',
			barWidth: '60%',
			data: value
		}]
	};
}
/**
 * 插件运行统计
 * 饼状图
 * @param {Array} data X坐标配置
 * Array [ {name:xx,value:xx}, {name:xx,value:xx} ]
 */
function getPieOption(data) {
	return option2 = {
		title: {
			text: '插件运行统计',
			x: 'left',
			y: 'top',
			textStyle: {
				fontSize: 18,
				fontWeight: 'bold',
			}
		},
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)"
		},

		series: [{
			name: '插件运行统计',
			type: 'pie',
			radius: '70%',
			center: ['50%', '50%'],
			data: data,
			itemStyle: {
				emphasis: {
					shadowBlur: 10,
					shadowOffsetX: 0,
					shadowColor: 'rgba(0, 0, 0, 0.5)'
				}
			}
		}]
	};
}

/*========================================= 错误日志管理  =======================================*/
/**
 * 近日数量折线图
 * 折线图
 */
function getErrorLineOption(data, value) {
	return option3 = {
		tooltip: {
			trigger: 'axis',
			axisPointer: { // 坐标轴指示器，坐标轴触发有效
				type: 'line', // 默认为直线，可选为：'line' | 'shadow'
				lineStyle: {
					color: '#48b',
					width: 2,
					type: 'dotted'
				},
			}
		},
		title: {
			text: '近日错误日志数量',
			x: '60px',
			y: '20px'
		},
		grid: {
			left: '50px',
			right: '60px',
			bottom: '40px',
			containLabel: true
		},
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: data,
			axisLabel: {
				interval: 0
			},
			axisLine: {
				show: false
			},
			// 控制网格线是否显示
			splitLine: {
				show: false
			},
			// 去除y轴上的刻度线
			axisTick: {
				show: false
			}
		},
		yAxis: {
			type: 'value',
			axisLine: {
				show: false
			},
			// 控制网格线是否显示
			splitLine: {
				show: false
			},
			// 去除y轴上的刻度线
			axisTick: {
				show: false
			}

		},
		series: [{
			data: value,
			type: 'line',
			smooth: true,
			areaStyle: {
				color: 'rgba(111,186,226, 0.5)'
			},
			//			 markPoint : {
			//              data : [
			//                  {type : 'max', name: '最大值'},
			//                  {type : 'min', name: '最小值'}
			//              ]
			//          },
			itemStyle: {
				normal: {
					//					label: {
					//						show: true
					//					},
					color: "#2ec7c9",
					lineStyle: {
						color: '#6fbae2'
					}
				}
			}

		}]
	}
}

/**
 * 高频用户图表+高频设备图表
 * 饼状图
 * @param {String} name 列表名称
 * @param {Array} data X坐标配置
 * Array [ {name:xx,value:xx}, {name:xx,value:xx} ]
 */
function getErrorPieOption(name, data) {
	return option4 = {
		title: {
			text: name,
			x: 'center',
			y: 'bottom'
		},
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)"
		},

		series: [{
			name: name,
			type: 'pie',
			radius: '70%',
			center: ['50%', '50%'],
			data: data,
			itemStyle: {
				emphasis: {
					shadowBlur: 10,
					shadowOffsetX: 0,
					shadowColor: 'rgba(0, 0, 0, 0.5)'
				}
			}
		}]
	}
}
/*========================================= ZOUSItv  =======================================*/
/**
 * ZOUSItv
 * 柱状图
 * @param {Array} data X坐标配置
 * @param {Array} value 对应X轴的具体数值 
 */
function gettvLineOption(data, value) {
	return option = {
		color: ['#3398DB'],
		tooltip: {
			trigger: 'axis',
			axisPointer: { // 坐标轴指示器，坐标轴触发有效
				type: 'line' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		xAxis: [{
			name: '时',
			type: 'category',
			data: data,
			boundaryGap: false,
			show: true,
			axisLabel:{
                 //X轴刻度配置
                 interval: 1 //0：表示全部显示不间隔；auto:表示自动根据刻度个数和宽度自动设置间隔个数
           },
			axisTick: {
				alignWithLabel: true
			}
		}],
		yAxis: [{
			type: 'value',
			show: false
		}],
		series: [{
			type: 'line',
			smooth: true,
			areaStyle: {
				color: 'rgba(111,186,226, 0.5)'
			},
//			markPoint: {
//				symbolSize: 50,
//				symbolOffset: [0, 5],
//				data: [{
//					type: 'max',
//					name: '最大值'
//				}]
//			},
			itemStyle: {
				normal: {
					label: {
						show: true,
						formatter: function(params) {
							if(params.value > 0) {
								return params.value;
							} else {
								return '';
							}
						}
					},
					color: "#2ec7c9",
					lineStyle: {
						color: '#6fbae2'
					}
				}
			},
			data: value
		}]
	};
}
/**
 * 插件运行统计
 * 饼状图
 * @param {Array} data X坐标配置
 * Array [ {name:xx,value:xx}, {name:xx,value:xx} ]
 */
function gettvPieOption(data) {
	return option2 = {
		//		title: {
		//			text: '插件运行统计',
		//			x: 'left',
		//			y: 'top',
		//			textStyle: {
		//				fontSize: 18,
		//				fontWeight: 'bold',
		//			}
		//		},
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)"
		},

		series: [{
			name: '插件运行统计',
			type: 'pie',
			radius : '60%',
			center: ['50%', '50%'],
			data: data,
			itemStyle: {
				normal: {
					label: {
						show: true,
						formatter: '{b} : {c}'
					}

				},
				emphasis: {
					shadowBlur: 10,
					shadowOffsetX: 0,
					shadowColor: 'rgba(0, 0, 0, 0.5)'
				}
			}
		}]
	};
}