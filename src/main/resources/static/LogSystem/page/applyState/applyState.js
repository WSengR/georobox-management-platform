layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function() {
	var form = layui.form,
		layer = layui.layer,
		$ = layui.jquery,
		laydate = layui.laydate,
		laytpl = layui.laytpl,
		table = layui.table;
	var choseTime = "";
	var topchoseTime = "";
	
	
	var unitchoose = '<option value="">请选择</option>', //选择插件下拉
		packchoose = '<option value="">请选择</option>', //选择包名下拉
		userchoose = '<option value="">请选择</option>', //选择用户下拉
		todayNumArr = []; //今日下载信息
	var todayDate = getNowFormatDate();
	//日期选项
	laydate.render({
		elem: '#stateDate',
		range: true,
		min: '2018-1-1',
		max: 0,
		done: function(value, date, endDate) {
			choseTime = value;
		}
	});
	//上方图表日期选项
	laydate.render({
		elem: '#pieDate',
		range: true,
		min: '2018-1-1',
		max: 0,
		done: function(value, date, endDate) {
			topchoseTime = value;
		}
	});
	//表格
	var stateTable = table.render({
		elem: '#stateList',
		url: httpUrl + "apprun/" + 'GetRunStateListServlet',
		cellMinWidth: 95,
		page: true,
		height: 'auto',
		limit: 10,
		limits: [10, 15, 20, 25],
		loading: true,
		even: true,
		id: "stateList",
		cols: [
			[{
					type: 'numbers',
					title: 'ID',
					width: 70,
				},
				{
					field: 'appName',
					title: '插件名称',
					width: 150
				},
				{
					field: 'infoMode',
					title: '设备信息',
					align: 'left',
					width: 140,
				},
				{
					field: 'modeVerisnon',
					title: '系统版本',
					align: 'left',
					width: 140,
					templet: function(d) {
						if(!d.modeVerison || d.modeVerison == "null") {
							return "";
						} else {
							return d.modeVerison;
						}

					}
				},
				{
					field: 'infoUser',
					title: '用户信息',
					align: 'left',
					width: 140
				},
				{
					field: 'infoOperation',
					title: '操作信息',
					align: 'left',
				},
				{
					field: 'infoOther',
					title: '其他信息',
					align: 'left',
				},
				{
					field: 'datetime',
					title: '时间',
					align: 'center',
					width: 210
				},
				{
					title: '操作',
					templet: '#stateListbar',
					align: "center",
					fixed: "right",
					width: 100
				}
			]
		],
		done: function(res, curr, count) {

		}
	});
	
	//查看详情
	table.on('tool(stateList)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		if(layEvent === 'watchMore') { //查看详情
			$("#elstaa").html("");
			var errTable = "";
			errTable += '<table class="layui-table magt10 statemsgtable" lay-even>'
			errTable += '<tbody>'
			errTable += '<tr><td><div>插件名称</div></td><td>' + data.appName + '</td></tr>'
			errTable += '<tr><td><div>系统版本</div></td><td>' + data.modeVerison + '</td></tr>'
			errTable += '<tr><td><div>设备型号</div></td><td>' + data.infoMode + '</td></tr>'
			errTable += '<tr><td><div>用户信息</div></td><td>' + data.infoUser + '</td></tr>'
			errTable += '<tr><td><div>类名</div></td><td>' + data.classString + '</td></tr>'
			errTable += '<tr><td><div>包名</div></td><td>' + data.appPackage + '</td></tr>'
			errTable += '<tr><td><div>操作信息</div></td><td>' + data.infoOperation + '</td></tr>'
			if(data.infoOther){
				errTable += '<tr><td><div>其他信息</div></td><td>' + data.infoOther + '</td></tr>'
			}else{
				errTable += '<tr><td><div>其他信息</div></td><td>暂无信息</td></tr>'
			}
			errTable += '<tr><td><div>时间</div></td><td>' + data.datetime + '</td></tr>'
			errTable += '</tbody>'
			errTable += '</table>'
			$("#elstaa").html(errTable);
			JSShow.openShow(1, "应用运行状态详情", "50%", $('#elstaa'), null, null, function() {
				$("#elstaa").html("");
			})

		}
	});
	
	//搜索
	$(".statesearch_btn").on("click", function() {
		var load = layer.load();
		var aChoose = $("#stateapp .layui-this").html();
		var pChoose = $("#statepack .layui-this").html();
		var uChoose = $("#stateuser .layui-this").html();
		if(aChoose == "请选择" || aChoose == "undefined" || aChoose == undefined) {
			aChoose = "";
		}
		if(pChoose == "请选择" || pChoose == "undefined" || pChoose == undefined) {
			pChoose = "";
		}
		if(uChoose == "请选择" || uChoose == "undefined" || uChoose == undefined) {
			uChoose = "";
		}
		var starTime = choseTime.substr(0, 10);
		var endTime = choseTime.substr(13);
		var searVal = $(".searchStateVal").val();
		table.reload("stateList", {
			page: {
				curr: 1 //重新从第 1 页开始
			},
			where: { //搜索的关键字
				searchKey: searVal,
				appName: aChoose,
				appPackage: pChoose,
				userInfo: uChoose,
				startTime: starTime,
				endTime: endTime
			}
		})
		layer.close(load);
	});

	//重置列表
	$(".stateclear_btn").on("click", function() {
		stateTable.reload();
	});

	//查看选框
	$('.radioGroup .layui-unselect').click(function() {
		var todayDate = getNowFormatDate();
		if($(this).hasClass('layui-form-checked')) {
			if($(this).hasClass("chosepie")){
				var startTime = topchoseTime.substr(0, 10);
				var endTime = topchoseTime.substr(13);
				if(topchoseTime == "" || topchoseTime == null || topchoseTime == undefined){
					layer.tips('搜索日期为空，请填写', $("#pieDate"), {
						tips: [1, '#3595CC'],
						time: 2000
					});
				}else{
					getTopcon(startTime,endTime)
				}
			}
		} else {
			if($(this).hasClass("todaypie")){
				$(this).addClass('layui-form-checked').siblings().removeClass('layui-form-checked');
				getTopcon(todayDate,todayDate)
			}else if($(this).hasClass("allpie")){
				$(this).addClass('layui-form-checked').siblings().removeClass('layui-form-checked');
				getTopcon("","")
			}else if($(this).hasClass("chosepie")){
				var startTime = topchoseTime.substr(0, 10);
				var endTime = topchoseTime.substr(13);
				if(topchoseTime == "" || topchoseTime == null || topchoseTime == undefined){
					layer.tips('搜索日期为空，请填写', $("#pieDate"), {
						tips: [1, '#23c6c8'],
						time: 2000
					});
				}else{
					$(this).addClass('layui-form-checked').siblings().removeClass('layui-form-checked');
					getTopcon(startTime,endTime)
				}
			}
		}
	})
	
	
	getSelect();

	//获取下拉筛选列表
	function getSelect() {
		AppState.GetAppState("", "", function(data) {
			var stateGet = data.data;
			//选择插件下拉填充
			for(var i = 0; i < stateGet.plugCountDatas.length; i++) {
				unitchoose += '<option value="' + stateGet.plugCountDatas[i].name + '">' + stateGet.plugCountDatas[i].name + '</option>'
			}
			$("#stateappChoose").append(unitchoose);
			//选择用户下拉填充
			for(var i = 0; i < stateGet.userCountDatas.length; i++) {
				userchoose += '<option value="' + stateGet.userCountDatas[i].name + '">' + stateGet.userCountDatas[i].name + '</option>'
			}
			$("#stateuserChoose").append(userchoose);
			layui.form.render();
		})
	}
	
	
	getTopcon(todayDate,todayDate);
	function getTopcon(startTime, endTime) {
		AppState.GetAppState(startTime, endTime, function(data) {
			//992px
			var width = $(window).width();
			width = parseInt(width) - 30;
			var newwidth = 0;
			if(width > 992) {
				newwidth = width / 3;
			} else {
				newwidth = width
			}
			$("#statec").css("width", newwidth);
			if(data.status == 1) {
				var stateGet = data.data;
				//获取饼状图数据
				var statec = []; //饼状图值
				statec = stateGet.plugCountDatas;
				var statep = echarts.init(document.getElementById('statec'));
				//state饼状图对应数组[ {name:xx,value:xx}, {name:xx,value:xx} ]
				statep.setOption(getPieOption(statec));
				if(stateGet.plugCountDatas.length == 0){
					$("#highUserNone").show();
					$(".applylistpie").hide();
				}else{
					$("#highUserNone").hide();
					$(".applylistpie").show();
					statec = stateGet.plugCountDatas;
				}

				var statelName = []; //柱形图X轴
				var statelValue = []; //柱形图值
				//获取条形图数据
				for(var i = 0; i < stateGet.runCountDatas.length; i++) {
					statelName.push(stateGet.runCountDatas[i].name.substr(5, 5));
					statelValue.push(stateGet.runCountDatas[i].value);
				}
				$("#stateTodayNum").html(stateGet.runNum + "次");
				
				
				//插件总数统计
				var appNum = stateGet.plugCountDatas.length;
				$("#stateAppNum").html(appNum + "个");

				//用户总数统计
				var userNum = stateGet.userCountDatas.length;
				$("#stateUserNum").html(userNum + "人");

				//最多插件
				if(statec.length > 0) {
					var stateUnit = statec[0];
					$("#stateUnit").html(stateUnit.value + '次<span>' + stateUnit.name + '</span>');
				}else{
					$("#stateUnit").html( '0次<span>暂无插件信息</span>');
				}

				//右侧插件列表
				var stateUnitList = "";
				for(var i = 0; i < statec.length; i++) {
					if(i < 3) {
						stateUnitList += '<li class="layui-center layui-clear">';
						stateUnitList += '<p class="phone_name layui-col-xs7">' + statec[i].name + '</p>';
						stateUnitList += '<p class="use_num layui-col-xs5">' + statec[i].value + '次</p>';
						stateUnitList += '</li>';
					}
				}
				$("#stateUnitList").html(stateUnitList);

				var statel = echarts.init(document.getElementById('statel'));
				
				//图表适配
				//statelName——X轴坐标
				//statelValue——X轴对应的值
				statel.setOption(getLineOption(statelName, statelValue));

				
				
				/**
				 * 根据饼状图是否有数据显示数据是否为空界面
				 */
				//			if(statec.length <= 0){
				//				$(".nohasInfo").show();
				//				$(".hasInfo").hide();
				//			}
				/*窗口自适应，关键代码*/
				setTimeout(function() {
					window.onresize = function() {
						statel.resize();
						statep.resize();
					}
				}, 200)
			} else {
				//$("body").html("页面加载失败，请刷新重试");
			}

		})
	}

	function Appendzero(obj) {
		if(obj < 10) return "0" + "" + obj;
		else return obj;
	}

	function getNowFormatDate() {
		var date = new Date();
		var seperator1 = "-";
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		if(month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if(strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var currentdate = year + seperator1 + month + seperator1 + strDate;
		return currentdate;
	}
	
	

})