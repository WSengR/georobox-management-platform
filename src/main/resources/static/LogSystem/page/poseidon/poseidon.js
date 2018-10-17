var verifierlist;
var oldVersion = "";
var jurchoselist = [], //权限选择列表
	catchose = "", //分类选择
	isSystem = 0, //是否调用系统方法
	upapkurl = "", //上传Apk插件地址
	upapkname = "", //上传Apk插件名称
	upiconfilename = "", //上传名称
	upiconurl = "", //上传图标地址
	configfileurl = "",
	changeid = "",
	version = "",
	oldicon = "",
	olduploadurl = "",
	olduploadname = "";
oldlinkurl = "";
var jurObj = [];
var catObj = "";

layui.use(['form', 'layer', 'table', 'element', 'upload', 'laydate', 'laytpl', 'jquery'], function() {
	var laydate = layui.laydate,
		form = layui.form,
		upload = layui.upload,
		table = layui.table,
		element = layui.element,
		$ = layui.jquery,
		layer = layui.layer;

	//上传用户操作状态
	var statePlugName = "插件管理";
	var stateClasses = "";
	var stateModeV = 1;
	if(localStorage.getItem("UserInfo")) {
		var stateOptionUserList = localStorage.getItem("UserInfo");
		var stateOptionUserListJson = JSON.parse(stateOptionUserList)
		var stateOptionUser = stateOptionUserListJson.username;
	} else {
		var stateOptionUser = "admin";
	}
	var stateModeInfo = "PC";
	var statePackageName = "com.robox.logsystem";
	var stateOption = "";
	var ExplorerInfo = "浏览器:" + getExplorerInfo().type + "；版本:" + getExplorerInfo().version;

	//跳转等待
	var apkupwait = layer.load(0, {
		shade: false,
		time: 5000
	});

	//审核列表672
	verifierlist = table.render({
		elem: '#verifierlist',
		url: httpUrl + "plug/" + 'AuditPlugList',
		cellMinWidth: 95,
		page: true,
		limit: 15,
		limits: [10, 15, 20, 25],
		height: 'full-163',
		loading: true,
		even: true,
		id: "verifierlist",
		cols: [
			[{
					type: 'numbers',
					title: 'ID',
				}, {
					title: '文件名',
					align: "left",
					templet: function(d) {
						return '<img class="plugicon" src="' + httpUrl + d.plugIcon + '" />' + d.plugName;
					}
				},
				{
					title: '插件类型',
					align: "center",
					width: 115,
					templet: function(d) {
						switch(d.plugType) {
							case 1:
								return '<i class="fasize fa fa-android"></i>';
							case 2:
								return '<i class="fasize fa fa-link"></i>';
							case 3:
								return '<i class="fasize fa fa-file-zip-o"></i>';
						}
					}
				},
				{
					field: 'plugVersioncode',
					title: '插件版本',
					width: 150,
					align: 'center'
				},
				{
					field: 'plugUrl',
					title: '文件地址',
					align: 'center'
				},
				{
					title: '操作',
					width: 180,
					fixed: "right",
					align: "center",
					templet: function(d) {
						var verifierListBar = "";
						switch(d.plugType) {
							case 1:
								verifierListBar += '<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="verify">插件审核</a>'
								verifierListBar += '<a class="layui-btn layui-btn-xs" lay-event="download">下载</a>'
								verifierListBar += '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>'
								break;
							case 2:
								verifierListBar += '<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="verify">插件审核</a>'
								verifierListBar += '<a class="layui-btn layui-btn-xs layui-btn-disabled" lay-event="download">下载</a>'
								verifierListBar += '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>'
								break;
							case 3:
								verifierListBar += '<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="verify">插件审核</a>'
								verifierListBar += '<a class="layui-btn layui-btn-xs" lay-event="download">下载</a>'
								verifierListBar += '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>'
								break;
						}
						return verifierListBar;
					}
				}
			]
		],
		done: function(res, curr, count) {
			var data = res.data;
			$("#verifierlist").next().find('.layui-table-body tr').each(function() {
				var dataindex = $(this).attr('data-index');
				var idx = 0;
				for(var item in data) {
					if(dataindex == idx) {
						$(this).dblclick(function() {
							//双击操作，data[item]
							//showDetails(data[item])
							Poseidon.PsdDetailVerifier(data[item].plugTempId, function(data){
								var data = data.data;
								showDetails(data, "Audit");
							})
						});
						break;
					}
					idx++;
				};
			});
		}
	});
	//审核列表操作
	table.on('tool(verifierlist)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		if(layEvent === 'verify') { //审核
			JSShow.confirmShow('是否确定审核通过该插件？', function() {
				Poseidon.PsdVerifyVerifier(data.plugTempId, function() {
					stateClasses = "VerifierPlusServlet";
					stateOption = "【审核】" + data.plugName + "（版本" + data.plugVersioncode + "）";
					Poseidon.PsdRunState(statePlugName, stateClasses, statePackageName, stateModeInfo, stateModeV, stateOptionUser, stateOption, ExplorerInfo, function() {});
					verifierlist.reload();
					layer.closeAll();
				});
			})
		} else if(layEvent === 'download') { //下载
			if(!$(this).hasClass("layui-btn-disabled")) {
				window.open(httpUrl + data.plugUrl);
			}
		}else if(layEvent === 'del') { //删除
			JSShow.confirmShow('是否确定删除该插件？', function() {
				Poseidon.PsdDelVerifier(data.plugTempId, function() {
					table.reload("verifierlist", {
						page: {
							curr: 1 //重新从当前 页开始
						}
					});
					layer.closeAll();
				});
			})
		}
	});

	//插件列表672
	var pluglist = table.render({
		elem: '#pluglist',
		url: httpUrl + "plug/" + 'PlugList',
		cellMinWidth: 95,
		page: true,
		limit: 15,
		limits: [15],
		height: 'full-163',
		loading: true,
		even: true,
		id: "pluglist",
		cols: [
			[{
					type: 'numbers',
					title: 'ID',
				}, {
					title: '文件名',
					align: "left",
					templet: function(d) {
						return '<img class="plugicon" src="' + httpUrl + d.plugIcon + '" />' + d.plugName;
					}
				},
				{
					title: '插件类型',
					align: "center",
					width: 115,
					templet: function(d) {
						switch(d.plugType) {
							case 1:
								return '<i class="fasize fa fa-android"></i>';
							case 2:
								return '<i class="fasize fa fa-link"></i>';
							case 3:
								return '<i class="fasize fa fa-file-zip-o"></i>';
						}
					}
				},
				{
					field: 'plugVersioncode',
					title: '插件版本',
					width: 150,
					align: 'center'
				},
				{
					field: 'plugUrl',
					title: '文件地址',
					align: 'center'
				},
				{
					field: 'complete',
					title: '上架情况',
					align: 'center',
					width: 150,
					unresize: true,
					templet: function(d) {
						if(d.plugIsDown == "0") {
							return '<input type="checkbox" name="lock" value="' + d.plugId + '" title="插件上架" lay-filter="lockDemo"/>';
						} else {
							return '<input type="checkbox" name="lock" value="' + d.plugId + '" title="插件上架" lay-filter="lockDemo" checked="checked" />';
						}

					}
				},
				{
					title: '操作',
					width: 320,
					fixed: "right",
					align: "center",
					templet: function(d) {
						var plugListBar = "";
						switch(d.plugType) {
							case 1:
								plugListBar += '<a class="layui-btn layui-btn-xs layui-btn-normal lay-up" lay-event="up">上移</a>';
								plugListBar += '<a class="layui-btn layui-btn-xs layui-btn-normal lay-down" lay-event="down">下移</a>';
								plugListBar += '<a class="layui-btn layui-btn-xs" lay-event="download">下载</a>';
								plugListBar += '<a class="layui-btn layui-btn-xs layui-btn-warm plugapk-change" lay-event="change">修改</a>';
								plugListBar += '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>';
								break;
							case 2:
								plugListBar += '<a class="layui-btn layui-btn-xs layui-btn-normal lay-up" lay-event="up">上移</a>';
								plugListBar += '<a class="layui-btn layui-btn-xs layui-btn-normal lay-down" lay-event="down">下移</a>';
								plugListBar += '<a class="layui-btn layui-btn-xs layui-btn-disabled" lay-event="download">下载</a>';
								plugListBar += '<a class="layui-btn layui-btn-xs layui-btn-warm pluglink-change" lay-event="change">修改</a>';
								plugListBar += '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>';
								break;
							case 3:
								plugListBar += '<a class="layui-btn layui-btn-xs layui-btn-normal lay-up" lay-event="up">上移</a>';
								plugListBar += '<a class="layui-btn layui-btn-xs layui-btn-normal lay-down" lay-event="down">下移</a>';
								plugListBar += '<a class="layui-btn layui-btn-xs" lay-event="download">下载</a>';
								plugListBar += '<a class="layui-btn layui-btn-xs layui-btn-warm plugzip-change" lay-event="change">修改</a>';
								plugListBar += '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>';
								break;

						}
						return plugListBar;
					}
				}
			]
		],
		done: function(res, curr, count) {
			if($(".searchVal").val() == ""){
				if(curr == 1) {
					$("#pluglist").next().find("tbody").find("tr").eq(0).find(".lay-up").addClass("layui-btn-disabled");
				}
				var pagenum = Math.ceil(res.count / 15);
				if(curr == pagenum) {
					$("#pluglist").next().find("tbody").find("tr:last").find(".lay-down").addClass("layui-btn-disabled");
				}
			}else{
				$("#pluglist").next().find("tbody").find("tr").find(".lay-up").addClass("layui-btn-disabled");
				$("#pluglist").next().find("tbody").find("trun").find(".lay-down").addClass("layui-btn-disabled");
			}
			layer.close(apkupwait);
			var data = res.data;
			$("#pluglist").next().find('.layui-table-body tr').each(function() {
				var dataindex = $(this).attr('data-index');
				var idx = 0;
				for(var item in data) {
					if(dataindex == idx) {
						$(this).find("td").not("td:nth-child(6)").dblclick(function() {
							Poseidon.PsdDetailPlug(data[item].plugId, function(data){
								var data = data.data;
								showDetails(data, "plug");
							})
						});
						break;
					}
					idx++;
				};
			});

		}
	});
	
	$(".search_btn").on("click", function() {
		reload();
	});

	$(".searchVal").keydown(function(e) {
		if(e.keyCode == 13) {
			reload();
		}
	})
	$(".clear_btn").on("click", function() {
		$(".searchVal").val("");
		location.reload();
	});
	
	
	form.on('checkbox(lockDemo)', function(obj) {
		var plugId = this.value
		layer.confirm('确认修改插件上架状态？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
		 	 Poseidon.PsdOutorUpPlug(plugId, function() {
				JSShow.showMsg("修改完成");
			});
		}, function(){
			if(!obj.othis.hasClass("layui-form-checked")){
				obj.elem.checked = true;
				obj.othis.addClass("layui-form-checked");
			}else{
				obj.elem.checked = false;
				obj.othis.removeClass("layui-form-checked");
			}
		});
	});
	
	table.on('tool(pluglist)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		if(layEvent === 'up') { //上移
			if(!$(this).hasClass("layui-btn-disabled")) {
				stateClasses = "MovePlusServlet";
				stateOption = "【上移】" + data.plugName + "（版本" + data.plugVersioncode + "）";
				Poseidon.PsdUpPlug(data.plugId, function() {
					Poseidon.PsdRunState(statePlugName, stateClasses, statePackageName, stateModeInfo, stateModeV, stateOptionUser, stateOption, ExplorerInfo, function() {});
					pluglist.reload();
				});
			}
		} else if(layEvent === 'down') { //下移
			if(!$(this).hasClass("layui-btn-disabled")) {
				stateClasses = "MovePlusServlet";
				stateOption = "【下移】" + data.plugName + "（版本" + data.plugVersioncode + "）";
				Poseidon.PsdDownPlug(data.plugId, function() {
					Poseidon.PsdRunState(statePlugName, stateClasses, statePackageName, stateModeInfo, stateModeV, stateOptionUser, stateOption, ExplorerInfo, function() {});
					pluglist.reload();
				});
			}
		} else if(layEvent === 'download') { //下载
			if(!$(this).hasClass("layui-btn-disabled")) {
				window.open(httpUrl + data.plugUrl);
			}
		} else if(layEvent === 'change') { //修改
			if($(this).hasClass("plugapk-change")) {
				$(".plug_apk").show();
				$(".plug_zip").hide();
				$(".plug_link").hide();
				$("#uploadfile").hide();
				$("#uploadfile2").show();
			} else if($(this).hasClass("pluglink-change")) {
				$(".plug_apk").hide();
				$(".plug_zip").hide();
				$(".plug_link").show();
			} else if($(this).hasClass("plugzip-change")) {
				$(".plug_apk").hide();
				$(".plug_link").hide();
				$(".plug_zip").show();
				$("#uploadfile").hide();
				$("#uploadfile2").show();
			}
			Poseidon.PsdDetailPlug(data.plugId, function(res) {
				var data = res.data;
				$('#apkchangeSure').removeClass("readyup");
				changeid = data.plugId;
				version = data.plugVersioncode;
				//configfileurl = data.configfileurl;
				oldicon = data.plugIcon;
				var categorynum = data.categorys;
				//var jurlist = data.permission.substring(1, data.permission.length - 1);
				var jurJson = data.permissions;
				var jurlist = [];
				for(var i = 0; i<jurJson.length;i++){
					jurlist.push(jurJson[i].permissionId);
				}
				//jurlist = jurlist.split(";");
				$(".psdupbtn").hide();
				$(".psdchangebtn").show();
				JSShow.openBaseShow(1, "修改插件", "", ['800px', ($(window).height()) * 0.7 + 'px'], $('#upplugform'), function(layero, index) {
					categorychose = "";
					jurisdictionchose = "";
					Poseidon.PsdGetCategory(function(data) {
						var data = data.data;
						for(var i = 0; i < data.length; i++) {
							if(data[i].categoryId == categorynum) {
								catchose = categorynum;
								categorychose += '<input name="categorychose" value="' + data[i].categoryId + '" title="' + data[i].categoryDes + '" type="radio" lay-filter="catchose" checked=true>'
							} else {
								categorychose += '<input name="categorychose" value="' + data[i].categoryId + '" title="' + data[i].categoryDes + '" type="radio" lay-filter="catchose">'
							}
						}
						$("#categorychose").append(categorychose);
						form.render();
					})
					Poseidon.PsdGetjurisdiction(function(data) {
						var data = data.data;
						var dataid = [];
						for(var i = 0; i < data.length; i++) {
							if($.inArray(data[i].permissionId.toString(), jurlist) >= 0) {
								jurchoselist.push(data[i].permissionId);
								jurisdictionchose += '<input name="jurisdictionchose" lay-skin="primary" value="' + data[i].permissionId + '" title="' + data[i].permissionDes + '" type="checkbox" lay-filter="jurchose" checked=true>'
							} else {
								jurisdictionchose += '<input name="jurisdictionchose" lay-skin="primary" value="' + data[i].permissionId + '" title="' + data[i].permissionDes + '" type="checkbox" lay-filter="jurchose">'
							}
						}
						$("#jurisdictionchose").append(jurisdictionchose);
						form.render();
					})
					$("#plugiconuploadval").val(data.plugIcon);
					$("#plugName").val(data.plugName);
					$("#plugInfo").val(data.plugDetails);
					$("#versionCode").val(data.plugVersioncode);
					switch(data.plugType) {
						case 1:
							$("#pluguploadval2").val(data.plugUrl);
							$("#plugPackageName").val(data.plugPackage);
							$("#LauncherActivity").val(data.plugLauncherActivity);
							if(data.plugNeedinstall == 0) {
								$("#isSystem").removeAttr("checked");
								form.render();
							} else if(data.plugNeedinstall == 1) {
								$("#isSystem").attr("checked", true);
								form.render();
							}
							break;
						case 2:
							$("#plugAddr").val(data.plugUrl);
							oldlinkurl = data.plugUrl;
							break;
						case 3:
							$("#pluguploadval2").val(data.plugUrl);
							break;
					}
				}, null, null, 1, function() {
					layer.closeAll();
					verifierlist.reload();
					$("#jurisdictionchose").html("");
					$("#categorychose").html("");
					jurisdictionchose = "";
					categorychose = "";
					catchose = "";
					jurchoselist = [];
					isSystem = 0;
					oldicon = "";
					olduploadurl = "";
					olduploadname = "";
					$("#upplugform")[0].reset();
				})

			})

		} else if(layEvent === 'del') { //删除	
			JSShow.confirmShow('是否确定删除该插件？', function() {
				var plugdata = data;
				Poseidon.PsdDelPlug(data.plugId, function(data) {
					if(data.status == -1){
						layer.msg(data.message, {
							area: '250px',
							time: 4000
						});
					}else{
						stateClasses = "DeletePlusServlet";
						stateOption = "【删除】" + plugdata.plugName + "（版本" + plugdata.plugVersioncode + "）";
						Poseidon.PsdRunState(statePlugName, stateClasses, statePackageName, stateModeInfo, stateModeV, stateOptionUser, stateOption, ExplorerInfo, function() {});
						table.reload("pluglist", {
							page: {
								curr: 1 //重新从当前 页开始
							}
						});
						layer.closeAll();
					}
				});
			})
		}
	});

	//分类列表
	var categorylist = table.render({
		elem: '#categorylist',
		url: httpUrl + "plug/" + 'categoryList',
		cellMinWidth: 95,
		page: true,
		limit: 15,
		limits: [10, 15, 20],
		width: 500,
		height: 'full-160',
		loading: true,
		even: true,
		id: "categorylist",
		cols: [
			[{
					type: 'numbers',
					title: 'ID',
				}, {
					field: 'categoryName',
					title: '分类名称',
					align: "center",

				},
				{
					field: 'categoryDes',
					title: '分类详情',
					align: "center",

				},
				{
					title: '操作',
					width: 135,
					fixed: "right",
					align: "center",
					templet: "#categorylistBar"
				}
			]
		],
		done: function(res, curr, count) {
			catObj = res.data;
			if(count == 0) {
				JSShow.openBaseShow(1, "添加分类", ['添加'], "", $('#addcategory'), null, function(index, layero) {
					if($("#addcategory .engtxt").val() == "") {
						layer.msg("请填写分类名称！");
					} else if($("#addcategory .txt").val() == "") {
						layer.msg("请填写分类详情！");
					} else {
						JSShow.confirmShow('是否确定添加该分类？', function() {
							var categoryName = $("#addcategory .engtxt").val();
							var categoryDes = $("#addcategory .txt").val();
							Poseidon.PsdAddCategory(categoryName, categoryDes, function() {
								location.reload();
								$("#addcategory .engtxt").val("");
								$("#addcategory .txt").val("");
								$("#addcategory").hide();
								layer.closeAll();
							})
						})
					}
				}, null, 0, null)
			}
		}
	});
	//分类列表操作
	table.on('tool(categorylist)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		if(layEvent === 'change') {
			$("#addcategory .engtxt").val(data.categoryName);
			$("#addcategory .txt").val(data.categoryDes);
			JSShow.openBaseShow(1, "修改分类", ['修改', '取消'], "", $('#addcategory'), null, function(index, layero) {
				if($("#addcategory .engtxt").val() == "") {
					layer.msg("请填写分类名称！");
				} else if($("#addcategory .txt").val() == "") {
					layer.msg("请填写分类详情！");
				} else {
					JSShow.confirmShow('是否确定修改该分类？', function() {
						var categoryName = $("#addcategory .engtxt").val();
						var categoryDes = $("#addcategory .txt").val();
						Poseidon.PsdChangCategory(data.categoryId, categoryName, categoryDes, function() {
							categorylist.reload();
							$("#addcategory .engtxt").val("");
							$("#addcategory").hide();
							$("#addcategory .txt").val("");
							layer.closeAll();
						});
					})
				}
			}, function(index, layero) {
				$("#addcategory .engtxt").val("");
				$("#addcategory .txt").val("");
				layer.close();
			}, 1, function() {
				$("#addcategory .engtxt").val("");
				$("#addcategory .txt").val("");
				layer.close();
			})

		} else if(layEvent === 'del') {
			JSShow.confirmShow('是否确定删除该分类？', function() {
				Poseidon.PsdDetCategory(data.categoryId, function() {
					categorylist.reload();
					layer.closeAll();
				});
			})
		}
	});

	//添加分类
	$(".addcategory").click(function() {
		JSShow.openBaseShow(1, "添加分类", ['添加', '取消'], "", $('#addcategory'), null, function(index, layero) {
			if($("#addcategory .engtxt").val() == "") {
				layer.msg("请填写分类名称！");
			} else if($("#addcategory .txt").val() == "") {
				layer.msg("请填写分类详情！");
			} else {
				JSShow.confirmShow('是否确定添加该分类？', function() {
					var categoryName = $("#addcategory .engtxt").val();
					var categoryDes = $("#addcategory .txt").val();
					Poseidon.PsdAddCategory(categoryName, categoryDes, function() {
						location.reload();
						$("#addcategory .engtxt").val("");
						$("#addcategory .txt").val("");
						$("#addcategory").hide();
						layer.closeAll();
					})
				})
			}
		}, function(index, layero) {
			$("#addcategory .engtxt").val("");
			$("#addcategory .txt").val("");
			layer.close();
		}, 1, function() {
			$("#addcategory .engtxt").val("");
			$("#addcategory .txt").val("");
			layer.close();
		})
	})

	//权限列表
	var jurisdiction = table.render({
		elem: '#jurisdiction',
		url: httpUrl + "plug/" + 'permissionList',
		cellMinWidth: 95,
		page: true,
		height: 'full-160',
		width: 500,
		limit: 15,
		limits: [10, 15, 20],
		loading: true,
		even: true,
		id: "jurisdiction",
		cols: [
			[{
					type: 'numbers',
					title: 'ID',
				}, {
					field: 'permissionName',
					title: '权限名称',
					align: "center",

				},
				{
					field: 'permissionDes',
					title: '权限详情',
					align: "center",

				},
				{
					title: '操作',
					width: 135,
					fixed: "right",
					align: "center",
					templet: "#jurisdictionlistBar"
				}
			]
		],
		done: function(res, curr, count) {
			if(count == 0) {

				JSShow.openBaseShow(1, "添加权限", ['添加'], "", $('#addjurisdiction'), null, function(index, layero) {
					if($("#addjurisdiction .engtxt").val() == "") {
						layer.msg("请填写权限名称！");
					} else if($("#addjurisdiction .txt").val() == "") {
						layer.msg("请填写权限详情！");
					} else {
						JSShow.confirmShow('是否确定添加该权限？', function() {
							var permissionName = $("#addjurisdiction .engtxt").val();
							var permissionDes = $("#addjurisdiction .txt").val();
							Poseidon.PsdAddJurisdiction(permissionName, permissionDes, function() {
								location.reload();
								$("#addjurisdiction .engtxt").val("");
								$("#addjurisdiction .txt").val("");
								$("#addjurisdiction").hide();
								layer.closeAll();
							})
						})
					}
				}, null, 0, null)

			} else {
				jurObj = res.data;
			}
		}
	});
	//权限列表操作
	table.on('tool(jurisdiction)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		if(layEvent === 'change') {
			$("#addjurisdiction .engtxt").val(data.permissionName);
			$("#addjurisdiction .txt").val(data.permissionDes);
			JSShow.openBaseShow(1, "修改权限", ['修改', '取消'], "", $('#addjurisdiction'), null, function(index, layero) {
				if($("#addjurisdiction .engtxt").val() == "") {
					layer.msg("请填写权限名称！");
				} else if($("#addjurisdiction .txt").val() == "") {
					layer.msg("请填写权限详情！");
				} else {
					JSShow.confirmShow('是否确定修改该权限？', function() {
						var permissionName = $("#addjurisdiction .engtxt").val();
						var permissionDes = $("#addjurisdiction .txt").val();
						Poseidon.PsdChangJurisdiction(data.permissionId, permissionName, permissionDes, function() {
							jurisdiction.reload();
							$("#addjurisdiction .engtxt").val("");
							$("#addjurisdiction .txt").val("");
							$("#addjurisdiction").hide();
							layer.closeAll();
						})
					})
				}
			}, function(index, layero) {
				$("#addjurisdiction .engtxt").val("");
				$("#addjurisdiction .txt").val("");
				layer.close();
			}, 1, function() {
				$("#addjurisdiction .engtxt").val("");
				$("#addjurisdiction .txt").val("");
				layer.close();
			})
		} else if(layEvent === 'del') {
			JSShow.confirmShow('是否确定删除该权限？', function() {
				Poseidon.PsdDetJurisdiction(data.permissionId, function() {
					jurisdiction.reload();
					layer.closeAll();
				})
			})
		}
	});

	//添加权限
	$(".addjurisdiction").click(function() {

		JSShow.openBaseShow(1, "添加权限", ['添加', '取消'], "", $('#addjurisdiction'), null, function(index, layero) {
			if($("#addjurisdiction .engtxt").val() == "") {
				layer.msg("请填写权限名称！");
			} else if($("#addjurisdiction .txt").val() == "") {
				layer.msg("请填写权限详情！");
			} else {
				JSShow.confirmShow('是否确定添加该权限？', function() {
					var permissionName = $("#addjurisdiction .engtxt").val();
					var permissionDes = $("#addjurisdiction .txt").val();
					Poseidon.PsdAddJurisdiction(permissionName, permissionDes, function() {
						location.reload();
						$("#addjurisdiction .engtxt").val("");
						$("#addjurisdiction .txt").val("");
						$("#addjurisdiction").hide();
						layer.closeAll();
					})
				})
			}
		}, function(index, layero) {
			$("#addjurisdiction .engtxt").val("");
			$("#addjurisdiction .txt").val("");
			layer.close();
		}, 1, function() {
			$("#addjurisdiction .engtxt").val("");
			$("#addjurisdiction .txt").val("");
			layer.close();
		})
	})

	/**
	 * 审核列表与插件列表切换
	 */
	$(".switch").click(function() {
		if(!$(this).hasClass("switch_ver")) {
			$(this).addClass("switch_ver");
			$(this).html("插件列表")
			$(".plugbox").hide();
			$(".verifierbox").show();
			verifierlist.reload()
			$(".search_box").hide();
			$(".search_box").val("");
		} else {
			$(this).removeClass("switch_ver");
			$(this).html("审核列表")
			$(".plugbox").show();
			$(".verifierbox").hide();
			pluglist.reload();
			$(".search_box").show();
			$(".search_box").val("");
		}
	})

	/*右侧选项卡操作*/
	$(".rightbtn").click(function() {
		if($(this).hasClass("categoryshow")) {
			if($(this).hasClass("thisaction")) {
				$(".righttable").removeClass("righttableshow");
				$(this).removeClass("thisaction");
				$(".categorybox").addClass("hide");
				$(".jurisdictionbox").addClass("hide");
			} else {
				$(".righttable").addClass("righttableshow");
				$(this).addClass("thisaction").siblings(".rightbtn").removeClass("thisaction");;
				$(".categorybox").removeClass("hide");
				$(".jurisdictionbox").addClass("hide");
			}
		} else if($(this).hasClass("jurisdictionshow")) {
			if($(this).hasClass("thisaction")) {
				$(".righttable").removeClass("righttableshow");
				$(this).removeClass("thisaction");
				$(".categorybox").addClass("hide");
				$(".jurisdictionbox").addClass("hide");
			} else {
				$(".righttable").addClass("righttableshow");
				$(this).addClass("thisaction").siblings(".rightbtn").removeClass("thisaction");
				$(".categorybox").addClass("hide");
				$(".jurisdictionbox").removeClass("hide");
			}
		}
	})
	/*右侧选项卡关闭*/
	$(".closeright").click(function() {
		$(".righttable").removeClass("righttableshow");
		$(".rightbtn").removeClass("thisaction");
		$(".categorybox").addClass("hide");
		$(".jurisdictionbox").addClass("hide");
	});
	
	//重新加载--用于搜索
	function reload() {
		if($(".searchVal").val() != '') {
			table.reload("pluglist", {
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where: {
					searchKey: $(".searchVal").val() //搜索的关键字
				}
			});
		} else {
			table.reload("pluglist", {
				page: {
					curr: 1 //重新从第 1 页开始
				}
			});
		}
	}
	
	/*双击查看详情*/
	function showDetails(data, type) {

		var detailsCon = "";
		detailsCon += '<tr><td>插件名称：</td><td>' + data.plugName + '</td></tr>';
		detailsCon += '<tr><td>插件Icon：</td><td><img class="plugicon" src="' + httpUrl + data.plugIcon + '" /></td></tr>';
		if(data.plugType == 1){
			detailsCon += '<tr><td>PackageName：</td><td>' + data.plugPackage + '</td></tr>';
			if(data.plugLauncherActivity) {
				detailsCon += '<tr><td>LauncherActivity：</td><td>' + data.plugLauncherActivity + '</td></tr>';
			} else {
				detailsCon += '<tr><td>LauncherActivity：</td><td> </td></tr>';
			}
		}
		if(data.plugVersioncode){
			detailsCon += '<tr><td>Version：</td><td>' + data.plugVersioncode + '</td></tr>';
		}
		switch(data.plugType) {
			case 1:
				detailsCon += '<tr><td>文件地址：</td><td>' + data.plugUrl + '</td></tr>';
				if(data.plugNeedinstall == 0){
					detailsCon += '<tr><td>是否调用系统方法安装： </td><td>否</td></tr>';
				}else{
					detailsCon += '<tr><td>是否调用系统方法安装： </td><td>是</td></tr>';
				}
				break;
			case 2:
				detailsCon += '<tr><td>插件链接： </td><td>' + data.plugUrl + '</td></tr>';
				break;
			case 3:
				detailsCon += '<tr><td>文件地址：</td><td>' + data.plugUrl + '</td></tr>';
				break;
		}
		if(data.plugIsDown == 1) {
			detailsCon += '<tr><td>上架情况：</td><td><span class="plugpass plugpass_yes">上架中</span></td></tr>';
		} else if(data.plugIsDown == 0) {
			detailsCon += '<tr><td>上架情况：</td><td><span class="plugpass plugpass_no">已下架</span></td></tr>';
		}
		
		if(type == "Audit"){
			var thiscategory = data.plugCategory.replace(/;/g, '')
		}else if(type == "plug"){
			var thiscategory = data.categorys;
		}
		for(var i = 0; i < catObj.length; i++) {
			if(catObj[i].categoryId == thiscategory) {
				detailsCon += '<tr><td>分类设置：</td><td>' + catObj[i].categoryDes + '</td></tr>';
			}
		}
		if(type == "Audit"){
			var thispermission = data.plugPermissionList
		}else if(type == "plug"){
			var thispermission = data.permissions;
		}
		var permissionlist = "";
		var thispermissiontxt = "";
		for(var i = 0; i < thispermission.length; i++) {
			thispermissiontxt += thispermission[i].permissionDes + "；";
		}
		detailsCon += '<tr><td>插件权限：</td><td>' + thispermissiontxt + '</td></tr>';
		detailsCon += '<tr><td>插件详情：</td><td>' + data.plugDetails + '</td></tr>';
		$("#plusdetails").html(detailsCon);

		JSShow.openShow(1, "插件详情", "50%", $('#plusdetails'), null, null, function() {
			$('#plusdetails').empty();
		})
	}

});
$(document).ready(function() {
	$(".verifierbox").hide();
});

function getExplorerInfo() {
	var explorer = window.navigator.userAgent.toLowerCase();
	//ie 
	if(explorer.indexOf("msie") >= 0) {
		var ver = explorer.match(/msie ([\d.]+)/)[1];
		return {
			type: "IE",
			version: ver
		};
	}
	//firefox 
	else if(explorer.indexOf("firefox") >= 0) {
		var ver = explorer.match(/firefox\/([\d.]+)/)[1];
		return {
			type: "Firefox",
			version: ver
		};
	}
	//Chrome
	else if(explorer.indexOf("chrome") >= 0) {
		var ver = explorer.match(/chrome\/([\d.]+)/)[1];
		return {
			type: "Chrome",
			version: ver
		};
	}
	//Opera
	else if(explorer.indexOf("opera") >= 0) {
		var ver = explorer.match(/opera.([\d.]+)/)[1];
		return {
			type: "Opera",
			version: ver
		};
	}
	//Safari
	else if(explorer.indexOf("Safari") >= 0) {
		var ver = explorer.match(/version\/([\d.]+)/)[1];
		return {
			type: "Safari",
			version: ver
		};
	}

}