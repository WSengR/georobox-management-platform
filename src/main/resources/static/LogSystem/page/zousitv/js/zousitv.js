$().ready(function() {
	
	$('.tvbody').animate({ scrollLeft: 0 }, 2);
	$('.scrollcon').animate({ scrollTop: 0 }, 2);
	
	var reloadtime = getLangDate();
	$(".lastreloadtime").html("上次刷新：" + reloadtime);
	var clientHeight = document.body.clientHeight;
	$(".tvbody").css("height",clientHeight-50)

	$(window).resize(function(){
		var clientHeightnew = document.body.clientHeight;
		$(".tvbody").css({"height":clientHeightnew-50,"width":"100%"});
	})
	
	setInterval(reload,600000);
	function reload(){
		if($(".autoreload").hasClass("layui-form-checked")){
			location.reload();
		}
	}
	
	$(".autoreload").click(function(){
		$(this).toggleClass("layui-form-checked");
	})
	
	//刷新
	$(".reload_btn").click(function() {
		location.reload();
	})
	
	ZHOUSITV.GetZSList(function(data) {
		var data = data.data;
		var zousinum = data.length;
		var onew = parseInt($(".tvbox").css("width")) + 20;
		var allw = (zousinum + 1) * onew;
		$(".tvlist").css("width", allw)
		for(var i = 0; i < zousinum; i++) {
			var zousibox = "";
			zousibox += '<div class="tvbox tvbox' + i + '" data-num="' + i + '" data-url="' + data[i].zsServeurl + '">';
			zousibox += '<div class="tvbox-tit layui-row">';
			zousibox += '<h2>' + data[i].zsName + '</h2>';
			zousibox += '<div class="tvbox-tit-btnlist">';
			zousibox += '<button class="layui-btn layui-btn-sm layui-btn-danger delZS" data-id="' + data[i].id + '" type="button">移除</button>';
			zousibox += '<button class="layui-btn layui-btn-sm gotoZS" type="button" data-url="' + data[i].zsServeurl + '">转到</button>';
			zousibox += '</div>';
			zousibox += '</div>';
			zousibox += '<div class="tvbox-con"></div>';
			zousibox += '</div>';
			$(".tvlist").prepend(zousibox);
			getdata(i, data[i].zsServeurl)
		}
		$('.tvbody').animate({ scrollLeft: 0 }, 2);
		$('.scrollcon').animate({ scrollTop: 0 }, 2);
		$('.tvbody').niceScroll({
			horizrailenabled: true,
			cursorcolor: "#666", //#CC0071 光标颜色
			cursoropacitymax: 0.9, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0
			touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备
			cursorwidth: "20px", //像素光标的宽度
			cursorborder: "0", // 游标边框css定义
			cursorborderradius: "5px", //以像素为光标边界半径
			autohidemode: true //是否隐藏滚动条
		});

	})

	function getdata(num, zsUrl) {
		var nowday = getNowFormatDate();
		var zousiboxcon = getDom(num);
		$(".tvbox" + num).find(".tvbox-con").html(zousiboxcon);
		var day = "";
		ZHOUSITV.GetZHOUSI(day, zsUrl, function(data) {
			var data = data.data;
			//当所有数据为空时显示暂无数据
			if(data.loginNum == 0 && data.errorNum == 0 && data.userNum == 0) {
				var nomsg = '<div class="nomsg"><img src="../../images/nomsg.png" /></div>';
				$(".tvbox" + num).find(".tvbox-con").html(nomsg);
			}
			//获取错误日志柱状图
			errArr = data.errorDataInfo;
			var errArrName = [00, 01, 02, 03, 04, 05, 06, 07, 08, 09, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23];
			var getdate = new Date();
			var newhours = getdate.getHours();
			newhours = parseInt(newhours) + 1;
			var errArrValue = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
			if(errArr.length > 0) {
				for(var i = 0; i < errArr.length; i++) {
					var errX = errArr[i].name.substr(11, 2);
					errX = parseInt(errX);
					errArrValue.splice(errX, 1, errArr[i].value);
				}
				if(day == nowday || day == "") {
					errArrName = errArrName.slice(0, newhours);
					errArrValue = errArrValue.slice(0, newhours);
				} else {
					errArrName = errArrName;
					errArrValue = errArrValue;
				}
				var zousitvl = echarts.init(document.getElementById('zousitvl' + num));
				zousitvl.setOption(gettvLineOption(errArrName, errArrValue));
			} else {
				var noerr = "";
				noerr += '<div id="highUserNone" style="width: 200px;margin: 30px auto 10px;">';
				noerr += '<img src="../../images/nores.png" style="display: block;width: 164px;margin: 0 auto 10px;" />';
				noerr += '<p class="layui-center" style="font-size: 18px;font-weight: bold;line-height: 20px;text-align: center;">暂无错误日志</p>';
				noerr += '</div>';
				$(".tvbox" + num).find(".errpie").html(noerr);
			}
			//获取登录数量
			$(".tvbox" + num).find(".tvloginnum p").html(data.loginNum);
			//获取崩溃数量
			$(".tvbox" + num).find(".tverrnum p").html(data.errorNum);
			//获取用户数量
			$(".tvbox" + num).find(".tvusernum p").html(data.userNum);
			if(data.plugNumChart.length == 0) {
				$('#zousitvp' + num).hide();
				//$(".tvbox"+num).find(".nomsg").removeClass("hide");
			} else {
				var plugNumChart = data.plugNumChart;
				var zousitvp = echarts.init(document.getElementById('zousitvp' + num));
				zousitvp.setOption(gettvPieOption(plugNumChart));
				$('#zousitvp' + num).show();
			}
			//获取列表信息
			var tablemsg = data.listLimitBean.data;
			var tablecon = "";
			for(var i = 0; i < tablemsg.length; i++) {
				tablecon += '<tr>';
				tablecon += '<td>' + tablemsg[i].infoUser + '</td>';
				tablecon += '<td>' + tablemsg[i].appName + '</td>';
				tablecon += '<td>' + tablemsg[i].infoOperation + '</td>';
				tablecon += '<td>' + tablemsg[i].infoMode + '</td>';
				tablecon += '<td>' + tablemsg[i].datetime + '</td>';
				tablecon += '</tr>';
			}
			$("#stateList" + num + " tbody").append(tablecon);
			if(data.listLimitBean.data.length == 0) {
				$('#stateList' + num).hide();
			}
			var seemore = '<p class="toseemore"><a class="gotoZS" data-url="' + zsUrl + '">前往宙斯查看更多>></a></p>'
			$(".tvbox" + num).find(".scrollcon").append(seemore);
			$('.scrollcon').niceScroll({
				cursorcolor: "#666", //#CC0071 光标颜色
				cursoropacitymax: 0.7, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0
				touchbehavior: true, //使光标拖动滚动像在台式电脑触摸设备
				cursorwidth: "5px", //像素光标的宽度
				cursorborder: "0", // 游标边框css定义
				cursorborderradius: "5px", //以像素为光标边界半径
				nativeparentscrolling: false,
				autohidemode: true //是否隐藏滚动条
			});
		}, function() {
			var errcon = "";
			errcon += '<div class="errajax">';
			errcon += '<img src="../../images/404.png" />';
			errcon += '<p>链接失败，请检测后重试</p>';
			errcon += '</div>';
			$(".tvbox" + num).find(".tvbox-con").html(errcon);
		})
	}

	function getDom(num) {
		var con = "";
		//		con += '<div class="tvbox-con">';
		con += '<div class="scrollcon">';
		con += '<div class="layui-row">';
		con += '<div class="applylist errpie">';
		con += '<div id="zousitvl' + num + '" style="width: 520px;height:280px;margin: 0 auto;" class=""></div>';
		con += '</div>';
		con += '</div>';
		con += '<div class="tvclass layui-row">';
		con += '<div class="tvclassbox tvclass1 tvloginnum">';
		con += '<p></p>';
		con += '<h3>登陆次数</h3>';
		con += '</div>';
		con += '<div class="tvclassbox tvclass2 tverrnum">';
		con += '<p></p>';
		con += '<h3>崩溃次数</h3>';
		con += '</div>';
		con += '<div class="tvclassbox tvclass3 tvusernum">';
		con += '<p></p>';
		con += '<h3>用户数量</h3>';
		con += '</div>';
		con += '</div>';
		con += '<div class="layui-row">';
		con += '<div class="applylist">';
		con += '<div id="zousitvp' + num + '" style="width: 520px;height:250px;margin: 0 auto;" class=""></div>';
		//		con += '<div class="hide nomsg"><img src="../../images/nomsg.png" /></div>';
		con += '</div>';
		con += '</div>';
		con += '<table id="stateList' + num + '" class="layui-table" lay-size="sm">';
		con += '<colgroup>';
		con += '<col width="70">';
		con += '<col width="115">';
		con += '<col width="115">';
		con += '<col width="115">';
		con += '<col width="115">';
		con += '</colgroup>';
		con += '<thead>';
		con += '<tr>';
		con += '<th>用户名</th>';
		con += '<th>插件名称</th>';
		con += '<th>用户操作</th>';
		con += '<th>设备信息</th>';
		con += '<th>时间</th>';
		con += '</tr>';
		con += '</thead>';
		con += '<tbody></tbody>';
		con += '</table>';
		con += '</div>';
		return con;
	}

	/*添加点击按钮*/
	$(".addbox").click(function() {
		$(this).toggleClass("hide").siblings(".addzousimsg").toggleClass("hide");
	})

	/*添加取消按钮*/
	$(".closeadd-btn").click(function() {
		$(this).parents(".addzousimsg").toggleClass("hide").siblings(".addbox").toggleClass("hide");
	});

	/*添加确定按钮*/
	$(".addzousi-btn").click(function() {
		if($("#zousiname").val() == "") {
			JSShow.showMsg("宙斯名称为空，请填写");
		} else if($("#zousiurl").val() == "") {
			JSShow.showMsg("宙斯服务地址为空，请填写");
		} else {
			var ZSName = $("#zousiname").val();
			var ZSUrl = $("#zousiurl").val();
			ZHOUSITV.TestZSList(ZSUrl, function(data) {
				if(data.status == 1 || data.Status == "0") {
					JSShow.confirmShow("确认添加该宙斯监测？", function() {
						ZHOUSITV.AddZSList(ZSName, ZSUrl, function() {
							JSShow.showMsg("上传成功");
							location.reload();
						})
					})
				}
			}, function() {
				layer.alert('无法连接到该宙斯服务器，请检查网址', {
					icon: 2
				});
				return false;
			})
		}
	});

	/*转到*/
	$(document).on("click", ".gotoZS", function() {
		var addrurl = $(this).attr("data-url");
		window.parent.location.href = addrurl;
	})
	/*移除*/
	$(document).on("click", ".delZS", function() {
		var thisid = $(this).attr("data-id");
		JSShow.confirmShow("确认移除该宙斯监测？", function() {
			ZHOUSITV.RemoveZSList(thisid, function() {
				JSShow.showMsg("移除成功");
				location.reload();
			})
		})
	})

	/*获取当天日期*/
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



function dateFilter(date) {
		if(date < 10) {
			return "0" + date;
		}
		return date;
	}

	/*获取当前时间*/
	function getLangDate() {
		var dateObj = new Date(); //表示当前系统时间的Date对象
		var year = dateObj.getFullYear(); //当前系统时间的完整年份值
		var month = dateObj.getMonth() + 1; //当前系统时间的月份值
		var date = dateObj.getDate(); //当前系统时间的月份中的日
		var day = dateObj.getDay(); //当前系统时间中的星期值
		var weeks = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
		var week = weeks[day]; //根据星期值，从数组中获取对应的星期字符串
		var hour = dateObj.getHours(); //当前系统时间的小时值
		var minute = dateObj.getMinutes(); //当前系统时间的分钟值
		var second = dateObj.getSeconds(); //当前系统时间的秒钟值
		//  var timeValue = "" +((hour >= 12) ? (hour >= 18) ? "晚上" : "下午" : "上午" ); //当前时间属于上午、晚上还是下午
		newDate = dateFilter(year) + "年" + dateFilter(month) + "月" + dateFilter(date) + "日 " + " " + dateFilter(hour) + ":" + dateFilter(minute) + ":" + dateFilter(second);
		return newDate;
	}

