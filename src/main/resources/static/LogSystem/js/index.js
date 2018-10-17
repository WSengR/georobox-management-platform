var $, tab, dataStr, layer;
layui.config({
	base: "js/"
}).extend({
	"bodyTab": "bodyTab"
})
layui.use(['bodyTab', 'form', 'element', 'layer', 'jquery'], function() {
	var form = layui.form,
		element = layui.element;
	$ = layui.$;
	layer = parent.layer === undefined ? layui.layer : top.layer;
	tab = layui.bodyTab({
		openTabNum: "50", //最大可打开窗口数量
//		url : "json/navs.json" //获取菜单json地址
		url: httpUrl + "navconfigrun/" + "GetMainMenuServlet" //获取菜单json地址
	});
	//通过顶部菜单获取左侧二三级菜单   注：此处只做演示之用，实际开发中通过接口传参的方式获取导航数据
	function getData(json) {
		$.getJSON(tab.tabConfig.url, {
			isOpen: 1
		}, function(data) {
			//var data = data.data;
		//$.getJSON(tab.tabConfig.url,function(data){
			if(data.contentManagement.length == 0) {
				getNavOptionList("one");
				layer.open({
					type: 1,
					title: "配置导航信息",
					area: ['400px', '400px'],
					content: $('#navoption'), //这里content是一个普通的String
					btn: ['确定'],
					btnAlign: 'r',
					btn1: function() {
						optionNavTure();
					},
					closeBtn: 0
				});
			} else {
				if(json == "contentManagement") {
					dataStr = data.contentManagement;
					//重新渲染左侧菜单
					tab.render(dataStr);
				} else if(json == "memberCenter") {
					dataStr = data.memberCenter;
					//重新渲染左侧菜单
					tab.render(dataStr);
				} else if(json == "systemeSttings") {
					dataStr = data.systemeSttings;
					//重新渲染左侧菜单
					tab.render(dataStr);
				} else if(json == "seraphApi") {
					dataStr = data.seraphApi;
					//重新渲染左侧菜单
					tab.render(dataStr);
				}
				
				//自动设置左侧导航栏菜单高度
				var height = $(".leftnav").height()
				$("#navBar").css("height",height+"px");
				var linum = $(".leftnav li").length;
				var ullen = parseInt(linum) * 45;
				$(".leftnav").css("min-height",ullen+"px");
				
			}

		})
	}
	
	$(".nonavgoto").click(function(){
		getNavOptionList("noone");
		layer.open({
			skin: 'chosenavboom',
			type: 1,
			title: "配置导航信息",
			area: ['400px', '400px'],
			content: $('#navoption'), //这里content是一个普通的String
			//btn: ["新增","确定","取消"],
			btn: ["配置完成"],
			btnAlign: 'r',
			//新增（已经写好，需要时直接取消注释绑定按键）
//			btn1: function() {
//				layer.open({
//					skin: 'addnavboom',
//					type: 1,
//					title: "新增导航类目",
//					area: ['400px', '280px'],
//					content: $('#addnavoption'), //这里content是一个普通的String
//					btn: ["确定","取消"],
//					btnAlign: 'r',
//					btn1: function() {
//						var title = $("#addnavtitle").val();
//						var href = $("#addnavhref").val();
//						var icon = $("#addnavicon").val();
//						if(title == ""){
//							layer.msg("请输入导航栏名称");
//						}else if(href == ""){
//							layer.msg("请输入导航栏地址");
//						}else if(icon == ""){
//							layer.msg("请输入导航栏图标");
//						}else{
//							NavOption.AddNavList(title, icon, href, function(){
//								$("#addnavtitle").val("");
//								$("#addnavhref").val("");
//								$("#addnavicon").val("");
//								layer.closeAll();
//								layer.msg("添加成功");
//							})
//						}
//						return false;
//					},
//					closeBtn: 0
//				});
//				return false;
//			},
			btn1: function() {
				optionNavTure();
				return false;
			},
			btn2: function() {
				layer.closeAll();
			},
			closeBtn: 0
		});
	})
	
	
	
	
	function optionNavTure(){
		var openlist = [];
		var noopenlist = [];
		var trnum = $("#optionnav tbody").find("tr").length;
		var tr = $("#optionnav tbody").find("tr");
		for(var i = 0; i < trnum; i++) {
			var thisdom = tr.eq(i).find('.navchose input')
			var isopen = thisdom.attr("isopen");
			if(isopen == "1") {
				openlist.push(thisdom.val());
			} else if(isopen == "0") {
				noopenlist.push(thisdom.val());
			}
		}
		
		if(noopenlist.length>0){
			noopenlist = noopenlist.join(";") + ";";
		}else{
			noopenlist = '';
		}
		if(openlist.length>0){
			layer.closeAll();
			location.reload();
		}else{
			openlist = '';
			layer.msg("请至少开启一个模块");
		}
	}
	
	function getNavOptionList(isone) {
		NavOption.GetNavList("", function(data) {
			var data = data.contentManagement;
			var navcon = "";
			for(var i = 0; i < data.length; i++) {
				navcon += '<tr>';
				navcon += '<td align="right" style="width: 200px;">' + data[i].title + '</td>'
				if(isone == "noone"){
					if(data[i].isOpen == "0") {
						navcon += '<td class="navchose" align="center" style="width: 200px;"><input value="' + data[i].id + '" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF" isopen="0" type="checkbox"></td>'
					} else {
						navcon += '<td class="navchose" align="center" style="width: 200px;"><input value="' + data[i].id + '" checked="" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF" isopen="1" type="checkbox"></td>'
					}
				}else if(isone == "one"){
					navcon += '<td class="navchose" align="center" style="width: 200px;"><input value="' + data[i].id + '" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF" isopen="1" type="checkbox"></td>'
				}
				//删除按钮
				//navcon += '<td class="deletenav" align="center" style="width: 20px;"><i class="layui-icon">&#x1007;</i></td>'
				navcon += '</tr>'
			}
			$("#optionnav tbody").html(navcon);
			form.render();
		})
	}

	form.on('switch(switchTest)', function(obj) {
		if(obj.elem.checked) {
			$(this).attr("isopen", "1");
			var isopen = $(this).attr("isopen");
			var id = $(this).parents(".navchose").find("input").val();
			NavOption.ChangeNavList(id, isopen, function(){
				layer.msg("修改成功");
			})
		} else {
			$(this).attr("isopen", "0");
			var isopen = $(this).attr("isopen");
			var id = $(this).parents(".navchose").find("input").val();
			NavOption.ChangeNavList(id, isopen, function(){
				layer.msg("修改成功");
			})
		}
	});
	
	$(document).on("click", ".deletenav .layui-icon", function(){
		var id = $(this).parents(".deletenav").siblings(".navchose").find("input").val();
		layer.confirm('确定删除该配置项?', {
			icon: 3,
			title: '提示'
		}, function(index) {
			NavOption.DeleteNavList(id, function(){
				layer.closeAll();
				location.reload();
				layer.msg("删除成功");
			})
		});
	})
	
	
	//页面加载时判断左侧菜单是否显示
	//通过顶部菜单获取左侧菜单
	$(".topLevelMenus li,.mobileTopLevelMenus dd").click(function() {
		if($(this).parents(".mobileTopLevelMenus").length != "0") {
			$(".topLevelMenus li").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
		} else {
			$(".mobileTopLevelMenus dd").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
		}
		$(".layui-layout-admin").removeClass("showMenu");
		$("body").addClass("site-mobile");
		getData($(this).data("menu"));
		//渲染顶部窗口
		tab.tabMove();
	})

	//隐藏左侧导航
	$(".hideMenu").click(function() {
		if($(".topLevelMenus li.layui-this a").data("url")) {
			layer.msg("此栏目状态下左侧菜单不可展开"); //主要为了避免左侧显示的内容与顶部菜单不匹配
			return false;
		}
		$(".layui-layout-admin").toggleClass("showMenu");
		//渲染顶部窗口
		tab.tabMove();
	})

	//通过顶部菜单获取左侧二三级菜单   注：此处只做演示之用，实际开发中通过接口传参的方式获取导航数据
	getData("contentManagement");

	//手机设备的简单适配
	$('.site-tree-mobile').on('click', function() {
		$('body').addClass('site-mobile');
	});
	$('.site-mobile-shade').on('click', function() {
		$('body').removeClass('site-mobile');
	});

	// 添加新窗口
	$("body").on("click", ".layui-nav .layui-nav-item a:not('.mobileTopLevelMenus .layui-nav-item a')", function() {
		//如果不存在子级
		if($(this).siblings().length == 0) {
			addTab($(this));
			$('body').removeClass('site-mobile'); //移动端点击菜单关闭菜单层
		}
		$(this).parent("li").siblings().removeClass("layui-nav-itemed");
	})

	//清除缓存
	$(".clearCache").click(function() {
		window.sessionStorage.clear();
		window.localStorage.clear();
		var index = layer.msg('清除缓存中，请稍候', {
			icon: 16,
			time: false,
			shade: 0.8
		});
		setTimeout(function() {
			layer.close(index);
			layer.msg("缓存清除成功！");
		}, 1000);
	})

	//刷新后还原打开的窗口
	if(cacheStr == "true") {
		if(window.sessionStorage.getItem("menu") != null) {
			menu = JSON.parse(window.sessionStorage.getItem("menu"));
			curmenu = window.sessionStorage.getItem("curmenu");
			var openTitle = '';
			for(var i = 0; i < menu.length; i++) {
				openTitle = '';
				if(menu[i].icon) {
					if(menu[i].icon.split("-")[0] == 'icon') {
						openTitle += '<i class="seraph ' + menu[i].icon + '"></i>';
					} else {
						openTitle += '<i class="layui-icon">' + menu[i].icon + '</i>';
					}
				}
				openTitle += '<cite>' + menu[i].title + '</cite>';
				openTitle += '<i class="layui-icon layui-unselect layui-tab-close" data-id="' + menu[i].layId + '">&#x1006;</i>';
				element.tabAdd("bodyTab", {
					title: openTitle,
					content: "<iframe src='" + menu[i].href + "' data-id='" + menu[i].layId + "'></frame>",
					id: menu[i].layId
				})
				//定位到刷新前的窗口
				if(curmenu != "undefined") {
					if(curmenu == '' || curmenu == "null") { //定位到后台首页
						element.tabChange("bodyTab", '');
					} else if(JSON.parse(curmenu).title == menu[i].title) { //定位到刷新前的页面
						element.tabChange("bodyTab", menu[i].layId);
					}
				} else {
					element.tabChange("bodyTab", menu[menu.length - 1].layId);
				}
			}
			//渲染顶部窗口
			tab.tabMove();
		}
	} else {
		window.sessionStorage.removeItem("menu");
		window.sessionStorage.removeItem("curmenu");
	}
})

//打开新窗口
function addTab(_this) {
	tab.tabAdd(_this);
}

function dateFilter(date) {
	if(date < 10) {
		return "0" + date;
	}
	return date;
}

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
	document.getElementById("showtimenow").innerHTML = newDate + "　" + week;
	setTimeout("getLangDate()", 1000);
}
getLangDate();