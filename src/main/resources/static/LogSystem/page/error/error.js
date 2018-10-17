layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function() {
	var form = layui.form,
		//		layer = parent.layer === undefined ? layui.layer : top.layer,
		layer = layui.layer,
		$ = layui.jquery,
		laydate = layui.laydate,
		laytpl = layui.laytpl,
		table = layui.table;
	var topchoseTime = "";
	var pageLoad = layer.load(0, {
		shade: false,
		time: 5000
	});
	var choseTime = "";
	//日期选项
	laydate.render({
		elem: '#errorDate',
		range: true,
		min: '2018-1-1',
		max: 0,
		done: function(value, date, endDate) {
			choseTime = value;
		}
	});

	//上方图表日期选项
	laydate.render({
		elem: '#errpieDate',
		range: true,
		min: '2018-1-1',
		max: 0,
		done: function(value, date, endDate) {
			topchoseTime = value;
		}
	});

	//错误列表
	var errtable = table.render({
		elem: '#errorList',
		url: httpUrl + "errorlogrun/" + 'GetErrorLogListServlet',
		cellMinWidth: 95,
		page: true,
		height: 500,
		limit: 10,
		limits: [10, 15, 20, 25],
		loading: true,
		even: true,
		id: "errortable",
		cols: [
			[{
					type: 'numbers',
					title: 'ID',
					width: 70,
				}, {
					field: 'phoneMode',
					title: '设备型号',
					align: "left",
					sort: true,
					width: 240,
				},

				{
					field: 'phoneVersion',
					title: '系统版本',
					width: 100,
					align: 'center'
				},
				{
					field: 'codeVersionname',
					title: '软件版本',
					align: 'center',
					sort: true,
					width: 180
				},
				{
					field: 'appName',
					title: 'APP名称',
					align: "left"
				},
				{
					field: 'userInfo',
					title: '用户账号',
					align: 'left',
					width: 160
				},
				{
					field: 'datetime',
					title: '时间',
					align: 'left',
					sort: true
				},
				{
					field: 'isCompleted',
					title: '处理情况',
					width: 110,
					unresize: true,
					templet: function(d) {
						if(d.isCompleted == "0") {
							return '<input type="checkbox" name="lock" value="' + d.id + '" title="已处理" lay-filter="lockDemo"/>';
						} else {
							return '<input type="checkbox" name="lock" value="' + d.id + '" title="已处理" lay-filter="lockDemo" checked="checked" />';
						}

					}
				},
				{
					title: '操作',
					templet: '#errorListbar',
					align: "center",
					fixed: "right",
					width: 160
				}
			]
		],
		done: function(res, curr, count) {
			layer.close(pageLoad);
			var data = res.data;
		}
	});

	form.on('checkbox(lockDemo)', function(obj) {
		var isCompleted;
		if(obj.elem.checked) {
			isCompleted = 1;
		} else {
			isCompleted = 0
		}
		var id = this.value
		ErrorData.CompletedErr(id, isCompleted, function() {
			JSShow.showMsg("修改完成");
		});
	});

	//搜索
	$(".errsearch_btn").on("click", function() {
		var load = layer.load();
		var pChoose = $("#phoneChoose .layui-this").html();
		var uChoose = $("#userChoose .layui-this").html();
		if(pChoose == "请选择" || pChoose == "undefined" || pChoose == undefined) {
			pChoose = "";
		}
		if(uChoose == "请选择" || uChoose == "undefined" || uChoose == undefined) {
			uChoose = "";
		}
		var starTime = choseTime.substr(0, 10);
		var endTime = choseTime.substr(13);
		table.reload("errortable", {
			page: {
				curr: 1 //重新从第 1 页开始
			},
			where: { //搜索的关键字
				phoneMode: pChoose,
				userInfo: uChoose,
				startTime: starTime,
				endTime: endTime
			}
		})
		layer.close(load);
	});

	//重置列表
	$(".errclear_btn").on("click", function() {
		errtable.reload({
			page: {
				curr: 1 //重新从第 1 页开始
			}
		});
	});

	//逾期删除
	$(".overDel_btn").click(function() {

		JSShow.confirmShow('是否删除30天以外的日志', function(index) {
			ErrorData.DeleteOver(function(data) {
				errtable.reload({
					page: {
						curr: 1 //重新从第 1 页开始
					}
				});
				var con = data.data;
				JSShow.showMsg('删除成功,已删除' + con + '条')
			})
			layer.close(index);
		})

	})

	//查看详情
	table.on('tool(errorList)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		if(layEvent === 'watchMore') { //查看详情
			$("#elstaa").html("");
			var errTable = "";
			errTable += '<table class="layui-table magt10 errmesstable" lay-even>'
			errTable += '<tbody>'
			errTable += '<tr><td><div>手机型号</div></td><td>' + data.phoneMode + '</td></tr>'
			errTable += '<tr><td><div>手机系统版本</div></td><td>' + data.phoneVersion + '</td></tr>'
			errTable += '<tr><td><div>手机制造商</div></td><td>' + data.phoneManufacturer + '</td></tr>'
			errTable += '<tr><td><div>手机MAC地址</div></td><td>' + data.phoneMac + '</td></tr>'
			errTable += '<tr><td><div>软件版本</div></td><td>' + data.codeVersionname + '</td></tr>'
			errTable += '<tr><td><div>App包名</div></td><td>' + data.appPackagename + '</td></tr>'
			errTable += '<tr><td><div>App名称</div></td><td>' + data.appName + '</td></tr>'
			errTable += '<tr><td><div>错误日志地址</div></td><td>' + data.logUrl + '</td></tr>'
			errTable += '<tr><td><div>用户账号</div></td><td>' + data.userInfo + '</td></tr>'
			errTable += '<tr><td><div>上传时间</div></td><td>' + data.datetime + '</td></tr>'
			errTable += '<tr><td><div>其他信息</div></td><td>' + data.info + '</td></tr>'
			if(data.errorInfo){
				errTable += '<tr><td><div>详细错误内容</div></td><td>' + data.errorInfo + '</td></tr>'
			}
			errTable += '</tbody>'
			errTable += '</table>'
			$("#elstaa").append(errTable);

			JSShow.openShow(1, "错误日志详情", "50%", $('#elstaa'), null, null, function() {
				$("#elstaa").html("");
			})

		} else if(layEvent === 'errorOpen') {
			window.open(httpUrl + data.logUrl);
		}
	});

	//查看选框
	$('.radioGroup .layui-unselect').click(function() {
		var todayDate = getNowFormatDate();
		if($(this).hasClass('layui-form-checked')) {
			if($(this).hasClass("chosepie")) {
				var startTime = topchoseTime.substr(0, 10);
				var endTime = topchoseTime.substr(13);
				if(topchoseTime == "" || topchoseTime == null || topchoseTime == undefined) {
					layer.tips('搜索日期为空，请填写', $("#errpieDate"), {
						tips: [1, '#3595CC'],
						time: 2000
					});
				} else {
					getTopcon(startTime, endTime)
				}
			}
		} else {
			if($(this).hasClass("todaypie")) {
				$(this).addClass('layui-form-checked').siblings().removeClass('layui-form-checked');
				getTopcon(todayDate, todayDate)
			} else if($(this).hasClass("allpie")) {
				$(this).addClass('layui-form-checked').siblings().removeClass('layui-form-checked');
				getTopcon("", "")
			} else if($(this).hasClass("chosepie")) {
				var startTime = topchoseTime.substr(0, 10);
				var endTime = topchoseTime.substr(13);
				if(topchoseTime == "" || topchoseTime == null || topchoseTime == undefined) {
					layer.tips('搜索日期为空，请填写', $("#errpieDate"), {
						tips: [1, '#23c6c8'],
						time: 2000
					});
				} else {
					$(this).addClass('layui-form-checked').siblings().removeClass('layui-form-checked');
					getTopcon(startTime, endTime)
				}
			}
		}
	})

	getSelect();

	function getSelect() {
		ErrorData.GetErrorData("", "", function(data) {

			var userSel = '<option value="">请选择</option>',
				modeSel = '<option value="">请选择</option>';
			var modeDatas = data.data.mostModeDatas;
			var userDatas = data.data.mostUserDatas;
			for(var i = 0; i < modeDatas.length; i++) {
				modeSel += '<option value="' + modeDatas[i].name + '">' + modeDatas[i].name + '</option>';
			}
			if(userDatas.length == 0) {
//				chartUserLable.push("暂无用户");
//				chartUserValue.push("0");
			} else {
				for(var i = 0; i < userDatas.length; i++) {
					userSel += '<option value="' + userDatas[i].name + '">' + userDatas[i].name + '</option>';
				}
			}
			$("#highPsel").html(modeSel);
			$("#highUsel").html(userSel);
			layui.form.render();
		})
	}
	var todayDate = getNowFormatDate();
	getTopcon(todayDate, todayDate)

	function getTopcon(startTime, endTime) {
		ErrorData.GetErrorData(startTime, endTime, function(data) {

			//992px
			var width = $(window).width();
			width = parseInt(width) - 30;
			var newwidth = 0;
			if(width > 992) {
				newwidth = width / 4;
			} else {
				newwidth = width
			}
			$("#highPhone").css("width", newwidth);
			$("#highUser").css("width", newwidth);
			$("#highUserNone").css("width", newwidth);

			var chartLable = [],
				chartValue = [],
				chartMode = "",
				chartUser = "",
				chartModeLable = [],
				chartModeValue = [],
				chartUserLable = [],
				chartUserValue = [];
			var userSel = '<option value="">请选择</option>',
				modeSel = '<option value="">请选择</option>';
			var rightUserlist = '',
				rightUserBox = '',
				rightModelist = '',
				rightModeBox = '';
			var chartDatas = data.data.dateChartDatas;
			var modeDatas = data.data.mostModeDatas;
			var userDatas = data.data.mostUserDatas;
			chartMode = data.data.mostModeDatas;
			chartUser = data.data.mostUserDatas;
			if(modeDatas.length == 0) {
				$("#topnocon").show();
				$("#toppiecon").hide();
			} else {
				$("#topnocon").hide();
				$("#toppiecon").show();
			}
			//获取近七天数据
			for(var i = 0; i < chartDatas.length; i++) {
				chartLable.push(chartDatas[i].name.substr(5, 5));
				chartValue.push(chartDatas[i].value);
			}
				//最多显示15条
			if(chartLable.length>15){
				var len = parseInt(chartLable.length) - 15;
				chartLable = chartLable.slice(len, chartLable.length);
				chartValue = chartValue.slice(len, chartValue.length);
			}else{
				chartLable = chartLable;
				chartValue = chartValue;
			}
			//今日错误数量
			var errTodayNum = chartDatas[chartDatas.length - 1];
			var todayNum = ""
			var day = new Date();
			var today = day.getDate();
			today = Appendzero(today);
			if(errTodayNum) {
				if(today == errTodayNum.name.substr(8, 2)) {
					todayNum = errTodayNum.value
				} else {
					todayNum = 0
				}
			} else {
				todayNum = 0
			}

			//获取高频手机数据
			if(modeDatas.length == 0) {
				chartModeLable.push("暂无手机");
				chartModeValue.push("0");
			} else {
				for(var i = 0; i < modeDatas.length; i++) {
					chartModeLable.push(modeDatas[i].name);
					chartModeValue.push(modeDatas[i].value);
					rightModelist += '<li class="layui-center layui-clear">';
					rightModelist += '<p class="phone_name layui-col-xs7">' + modeDatas[i].name + '</p>';
					rightModelist += '<p class="use_num layui-col-xs5">' + modeDatas[i].value + '次</p>';
					rightModelist += '</li>';
				}
			}

			//获取高频用户数据

			if(userDatas.length == 0) {
				chartUserLable.push("暂无用户");
				chartUserValue.push("0");
			} else {
				for(var i = 0; i < userDatas.length; i++) {
					chartUserLable.push(userDatas[i].name);
					chartUserValue.push(userDatas[i].value);
					rightUserlist += '<li class="layui-center layui-clear">';
					rightUserlist += '<p class="phone_name layui-col-xs7">' + userDatas[i].name + '</p>';
					rightUserlist += '<p class="use_num layui-col-xs5">' + userDatas[i].value + '次</p>';
					rightUserlist += '</li>';
				}
			}
			var newtodayNum = data.data.errCount
			rightModeBox += '<div class="layui-row">'
			rightModeBox += '<div class="todayerr">错误日志数量：<span>' + newtodayNum + '</span>次</div>'
			rightModeBox += '</div>'
			rightModeBox += '<div class="elist-head">';
			rightModeBox += '<p class="elist-head-h">高频手机</p>';
			rightModeBox += '<p class="elist-head-c">' + chartModeValue[0] + '次<span>' + chartModeLable[0] + '</span></p>';
			rightModeBox += '</div>'
			rightModeBox += '<ul class="elist-con">';
			rightModeBox += rightModelist
			rightModeBox += '</ul>';
			$("#emessbox-p").html(rightModeBox);
			rightUserBox += '<div class="elist-head">';
			rightUserBox += '<p class="elist-head-h">高频用户</p>';
			rightUserBox += '<p class="elist-head-c">' + chartUserValue[0] + '次<span>' + chartUserLable[0] + '</span></p>';
			rightUserBox += '</div>'
			rightUserBox += '<ul class="elist-con">';
			rightUserBox += rightUserlist
			rightUserBox += '</ul>';
			$("#emessbox-u").html(rightUserBox);

			//近日数量折线图
			var errl = echarts.init(document.getElementById('eList'));

			//高频用户图表
			var errlu = echarts.init(document.getElementById('highUser'));

			//高频设备图表		
			var errlp = echarts.init(document.getElementById('highPhone'));

			//图表适配
			errl.setOption(getErrorLineOption(chartLable, chartValue));
			if(chartUser.length == 0) {
				$("#highUser").hide();
				$("#highUserNone").show();
			} else {
				$("#highUser").show();
				$("#highUserNone").hide();
				errlu.setOption(getErrorPieOption("高频用户", chartUser));
			}

			errlp.setOption(getErrorPieOption("高频手机", chartMode));

			/*窗口自适应，关键代码*/
			setTimeout(function() {
				window.onresize = function() {
					var width = $(window).width();
					width = parseInt(width) - 30;
					var newwidth = 0;
					if(width > 992) {
						newwidth = width / 4;
					} else {
						newwidth = width
					}
					$("#highPhone").css("width", newwidth);
					$("#highUser").css("width", newwidth);
					$("#highUserNone").css("width", newwidth);

					errl.resize();
					errlu.resize();
					errlp.resize();
				}
			}, 200)

		});
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

	function Appendzero(obj) {
		if(obj < 10) return "0" + "" + obj;
		else return obj;
	}

})