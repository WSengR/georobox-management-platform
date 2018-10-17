layui.use(['form', 'layer', 'laydate', 'code', "upload", 'table', 'laytpl'], function() {
	var form = layui.form,
		upload = layui.upload,
		layer = layui.layer,
		$ = layui.jquery,
		laydate = layui.laydate,
		laytpl = layui.laytpl,
		table = layui.table;
	var deployconfig = {
		accept: "images",
		exts: "jpg|png|gif|bmp|jpeg"
	};
	var configType = "PIC";
	var beforeName = "";
	var doneName = "";
	var changebeforeName = "";
	var changedoneName = "";
	//加载更多方法
	function getDeployTypeList(page, limit) {
		Deploy.GetDeployList(page, limit, function(data) {
			var listdata = data.data;
			var listcon = "";
			if(page == 1 && listdata.length == 0) {
				$(".typelist").append("暂无数据");
			} else {
				for(var i = 0; i < listdata.length; i++) {
					listcon += '<div class="deploybox">';
					listcon += '<div class="deployboxhead">';
					if(listdata[i].type == "PIC") {
						listcon += '<img class="imgfile" src="../../images/imgfile.png" />';
					} else if(listdata[i].type == "XML") {
						listcon += '<img class="xmlfile" src="../../images/xmlfile.png" />';
					} else if(listdata[i].type == "APK") {
						listcon += '<img class="apkfile" src="../../images/apkfile.png" />';
					}
					listcon += '<p class="deployname layui-center">' + listdata[i].name + '</p>';
					listcon += '</div>';
					listcon += '<div class="deployfoot">';
					listcon += '<div class="layui-btn-group">';
					listcon += '<button class="layui-btn typelistedit">修改</button>';
					listcon += '<button class="layui-btn layui-btn-danger typelistdel">删除</button>';
					listcon += '</div>';
					listcon += '</div>';

					listcon += '<div class="showdet hide">';
					listcon += '<p class="deployid">ID：<em>' + listdata[i].id + '</em></p>';
					listcon += '<p class="deployname"><span>Name</span>：<em>' + listdata[i].name + '</em></p>';
					listcon += '<p class="deployaddr"><span>URL</span>：<em>' + listdata[i].url + '</em></p>';
					listcon += '<p class="deploytype"><span>Type</span>：<em>' + listdata[i].type + '</em></p>';
					if(listdata[i].type == "APK") {
						listcon += '<p class="deploypack"><span>PackageName</span>：<em>' + listdata[i].packagename + '</em></p>';
					}
					listcon += '<p class="deploynath"><span>NativePath</span>：<em>' + listdata[i].nativepath + '</em></p>';
					listcon += '<p class="deployver"><span>Version</span>：<em>' + listdata[i].version + '</em></p>';
					listcon += '<p class="deploytime"><span>dateTime</span>：<em>' + listdata[i].datetime + '</em></p>';
					listcon += '</div>';
					listcon += '</div>';
				}
				if(listdata.length == limit) {
					listcon += '<div class="deploymore"><button class="layui-btn deploymorebtn" data-num="' + page + '">加载更多</button></div>';
				}
				$(".typelist").append(listcon);
				listcon = "";
			}

			//鼠标放置显示详情
			$(".deploybox").mouseover(function() {
				$(this).find(".showdet").show();
			})
			//鼠标放置显示详情
			$(".deploybox").mouseleave(function() {
				$(this).find(".showdet").hide();
			})

		})
	}

	//点击加载更多
	$(document).on("click", ".deploymorebtn", function() {
		var newpage = parseInt($(this).attr("data-num")) + 1;
		$(this).parents(".deploymore").remove();
		getDeployTypeList(newpage, 15)
	})

	//切换图标
	$(".typechose .layui-btn").click(function() {
		if($(this).hasClass("showtable")) {
			$(".typelist").empty();
			if(!$(this).hasClass("on")) {
				$(this).addClass("on").siblings(".layui-btn").removeClass("on");
				$(".typetable").show();
				$(".typelist").hide();
			}
		} else if($(this).hasClass("showlist")) {
			if(!$(this).hasClass("on")) {
				$(this).addClass("on").siblings(".layui-btn").removeClass("on");
				$(".typelist").empty();
				$(".typetable").hide();
				$(".typelist").show();
				getDeployTypeList(1, 15)
			}
		}

	})

	//列表模式删除
	$(document).on("click", ".typelistdel", function() {
		var deployid = $(this).parents(".deploybox").find(".deployid em").html();
		JSShow.confirmShow('确定删除这条配置资源信息？', function(index) {
			Deploy.DeleteDeployList(deployid, function() {
				$(".typelist").empty();
				getDeployTypeList(1, 15)
				layer.close(index);
			})

		})
	})

	//列表模式编辑
	$(document).on("click", ".typelistedit", function() {
		var getname = $(this).parents(".deploybox").find(".deployname em").html();
		var gettype = $(this).parents(".deploybox").find(".deploytype em").html();
		var geturl = $(this).parents(".deploybox").find(".deployaddr em").html();
		var getnath = $(this).parents(".deploybox").find(".deploynath em").html();
		var getpack = $(this).parents(".deploybox").find(".deploypack em").html();
		var id = $(this).parents(".deploybox").find(".deployid em").html();
		if(gettype == "APK") {
			$("#editdeployPackageName").val(getpack);
			$(".deployChange .deploy_apk").removeClass("hide");
			var editexts = "apk";
			editChangeExts(editexts);
		} else if(gettype == "PIC") {
			$(".deployChange .deploy_apk").addClass("hide");
			var editexts = "jpg|png|gif|bmp|jpeg";
			editChangeExts(editexts);
		} else if(gettype == "XML") {
			$(".deployChange .deploy_apk").addClass("hide");
			var editexts = "xml";
			editChangeExts(editexts);
		}
		$("#editdeployType").val(gettype);
		$("#editdeployName").val(getname);
		$("#editdeployNativePath").val(getnath);
		$("#editdeployuploadval").val(geturl);
		$("#editdeployboomSure").attr("dataid", id);
		JSShow.openShow(1, "修改配置资源信息", "500px", $(".deployChange"), null, null, function() {
			$(".deployChange").hide();
			layer.closeAll();
		});
	})

	//点击下载
	$(".dlxmlbtn").click(function() {
		Deploy.GetMainConfig(function(data) {
			parent.location.href = (httpUrl + data.massage);
		})
	})

	//点击预览
	$(".previewbtn").click(function() {
		Deploy.GetMainConfig(function(data) {
			var data = data.data;
			var pre = '&lt;?xml version="1.0" encoding="UTF-8"?>\n'
			pre += "&lt;root>\n"
			for(var i = 0; i < data.length; i++) {
				pre += '  &lt;Resource>\n'
				pre += '    &lt;Name>' + data[i].name + '&lt;/Name>\n'
				pre += '    &lt;Type>' + data[i].type + '&lt;/Type>\n'
				pre += '    &lt;URL>' + httpUrl + data[i].url + '&lt;/URL>\n'
				if(data[i].type == "APK") {
					pre += '    &lt;PackageName>' + data[i].packagename + '</PackageName>\n'
				}
				pre += '    &lt;NativePath>' + data[i].nativepath + '&lt;/NativePath>\n'
				pre += '    &lt;Version>' + data[i].version + '&lt;/Version>\n'
				pre += '  &lt;/Resource>\n'

			}
			pre += "&lt;/root>"
			$(".previewcon").append(pre);
			layui.code({
				encode: true,
				about: false
			});
			JSShow.openShow(1, "配置文件预览", ["50%", ($(window).height()) * 0.7 + 'px'], $('.preview'), null, null, function() {
				$('.preview').hide();
				$(".previewcon").empty();
				pre = "";
			})
		})
	})

	//重置上传列表
	$(".deployreset").click(function() {
		$(".deploy_apk").addClass("hide");
		$("#deployboomSure").removeClass("chosefile");
		//changeExts("jpg|png|gif|bmp|jpeg");
	})

	//选择上传种类
	form.on('select(deployclass)', function(data) {
		if(data.value == "xml") {
			configType = "XML";
			$(".deploy_apk").addClass("hide");
			deployconfig.accept = "file";
			deployconfig.exts = "xml";
		} else if(data.value == "apk") {
			configType = "APK";
			$(".deploy_apk").removeClass("hide");
			deployconfig.accept = "file";
			deployconfig.exts = "apk";
		} else if(data.value == "pic") {
			configType = "PIC";
			$(".deploy_apk").addClass("hide");
			deployconfig.accept = "images";
			deployconfig.exts = "jpg|png|gif|bmp|jpeg";
		}
		changeExts(deployconfig.exts);
	});

	function changeExts(exts) {
		deployupload.config.exts = exts; //更改上传文件属性配置
	}
	//上传文件

	var deployupload = upload.render({
		elem: '#deployupload',
		url: httpUrl + 'customUploadFile',
		auto: false,
		data: {
		  folder: "configResources"
		},
		//bindAction: '#deployboomSure',
		accept: "file",
		exts: "jpg|png|gif|bmp|jpeg",
		choose: function(obj) {
			//预读本地文件示例，不支持ie8
			obj.preview(function(index, file, result) {
				beforeName = file.name;
				$('#deployuploadval').val(file.name); //icon地址（base64）
				$("#deployboomSure").addClass("chosefile");
				$(".layui-form-select dd").click(function() {
					$('#deployuploadval').val("");
					$("#deployboomSure").removeClass("chosefile");
				})

			});
		},
		done: function(res, index, upload) {
			if(res.status == 1) {
				var configName = $("#deployName").val();
				var deployurl = res.data;
				var configPackageName = $("#deployPackageName").val();
				var nativePath = $("#deployNativePath").val();
				Deploy.AddDeployList(configName, configType, deployurl, configPackageName, nativePath, function() {
					beforeName = "";
					doneName = "";
					location.reload();
					layer.closeAll();
					$(".deployreset").click();
					$("#deployboomSure").removeClass("chosefile");
				})
				layer.msg('上传成功');
			} else {
				layer.msg("上传失败请重试");
			}
		}
	});

	//点击确定上传
	$("#deployboomSure").click(function() {
		var configName = $("#deployName").val();
		var configPackageName = $("#deployPackageName").val();
		var nativePath = $("#deployNativePath").val();
		//如果没有选择上传资源（直接输入）
		if(!$(this).hasClass("chosefile")) {
			deployurl = $("#deployuploadval").val();
			Deploy.AddDeployList(configName, configType, deployurl, configPackageName, nativePath, function() {
				beforeName = "";
				doneName = "";
				location.reload();
				layer.closeAll();
				$(".deployreset").click();
				$("#deployboomSure").removeClass("chosefile");
			})
		} else {
			doneName = $("#deployuploadval").val();
			//如果先点击上传后修改（输入框内上传内容）
			if(beforeName != doneName) {
				var deployurl = $("#deployuploadval").val();
				Deploy.AddDeployList(configName, configType, deployurl, configPackageName, nativePath, function() {
					beforeName = "";
					doneName = "";
					location.reload();
					layer.closeAll();
					$(".deployreset").click();
					$("#deployboomSure").removeClass("chosefile");
				})
			} else {
				//正常选择文件直接上传
				deployupload.upload();
			}

		}
	})
	//点击上传配置资源弹框
	$(".adddeploy").click(function() {
		if($("#deployName").val() == "") {
			JSShow.showMsg("请输入资源名称");
		} else if($("#deployNativePath").val() == "") {
			JSShow.showMsg("请输入资源保存的本地路径");
		} else if($("#deployuploadval").val() == "") {
			JSShow.showMsg("选择上传资源文件");
		} else {
			if(!$(".deploy_apk").hasClass("hide") && $("#deployPackageName").val() == "") {
				JSShow.showMsg("请输入资源包名");
			} else {
				JSShow.openShow(1, "请确定上传内容", ['300px', '180px'], $("#deployboom"), null, null, null)
			}
		}

	})
	//点击取消
	$("#deployboomStop").click(function() {
		layer.closeAll();
	})

	//获取列表
	var deployTable = table.render({
		elem: '#deployTable',
		url: httpUrl + "configmanagerun/" + 'GetConfigMangerServlet',
		cellMinWidth: 80,
		page: true,
		height: "full-125",
		limit: 15,
		limits: [10, 15, 20, 25],
		loading: true,
		even: true,
		id: "deployTable",
		cols: [
			[{
					type: 'numbers',
					title: 'ID',
					width: 80,
					align: "center"
				},
				{
					field: 'name',
					title: '资源名称',
					width: 160
				},
				{
					field: 'type',
					title: '资源类型',
					width: 90,
					align: 'center'

				},
				{
					field: 'url',
					title: '资源地址',
					align: 'left'
				},
				{
					field: 'packagename',
					title: '资源包名',
					align: 'left'
				},
				{
					field: 'nativepath',
					title: '资源本地路径',
					align: 'left'
				},
				{
					field: 'version',
					title: '资源版本号 ',
					align: 'left'
				},
				{
					field: 'datetime',
					title: '时间',
					width: 160,
					align: 'left',
					sort: true
				},
				{
					title: '操作',
					width: 140,
					templet: '#deployListBar',
					fixed: "right",
					align: "center"
				}
			]
		],
		done: function(res, curr, count) {}
	});

	function editChangeExts(exts) {
		editdeployupload.config.exts = exts; //更改上传文件属性配置
	}
	//	修改删除
	table.on('tool(deployTable)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		if(layEvent === 'change') { //编辑
			
			if(data.type == "APK") {
				$("#editdeployPackageName").val(data.packagename);
				$(".deployChange .deploy_apk").removeClass("hide");
				var editexts = "apk";
				editChangeExts(editexts);
			} else if(data.type == "PIC") {
				$(".deployChange .deploy_apk").addClass("hide");
				var editexts = "jpg|png|gif|bmp|jpeg";
				editChangeExts(editexts);
			} else if(data.type == "XML") {
				$(".deployChange .deploy_apk").addClass("hide");
				var editexts = "xml";
				editChangeExts(editexts);
			}
			$("#editdeployType").val(data.type);
			$("#editdeployName").val(data.name);
			$("#editdeployNativePath").val(data.nativepath);
			$("#editdeployuploadval").val(data.url);
			$("#editdeployboomSure").attr("dataid", data.id);
			JSShow.openShow(1, "修改配置资源信息", "500px", $(".deployChange"), null, null, function() {
				$(".deployChange").hide();
				layer.closeAll();
			})
		} else if(layEvent === 'del') { //删除

			JSShow.confirmShow('确定删除这条配置资源信息？', function(index) {

				Deploy.DeleteDeployList(data.id, function() {
					table.reload("deployTable", {
						page: {
							curr: 1 //重新从前一页开始
						}
					});
					layer.close(index);
				})

			})
		}
	});

	//修改文件上传
	var editdeployupload = upload.render({
		elem: '#editdeployupload',
		url: httpUrl + 'customUploadFile',
		auto: false,
		data: {
		  folder: "configResources"
		},
		//bindAction: '#deployboomSure',
		accept: "file",
		exts: "jpg|png|gif|bmp|jpeg",
		choose: function(obj) {
			//预读本地文件示例，不支持ie8
			obj.preview(function(index, file, result) {
				changebeforeName = file.name;
				$('#editdeployuploadval').val(file.name); //icon地址（base64）
				$("#editdeployboomSure").addClass("chosefile");

			});
		},
		done: function(res, index, upload) {
			if(res.status == 1) {
				var configName = $("#editdeployName").val();
				var deployurl = res.data;
				var changeType = $("#editdeployType").val();
				var configPackageName = $("#editdeployPackageName").val();
				var nativePath = $("#editdeployNativePath").val();
				var id = $("#editdeployboomSure").attr("dataid");
				Deploy.ChangeDeployList(id, configName, changeType, deployurl, configPackageName, nativePath, function() {
					changebeforeName = "";
					changedoneName = "";
					location.reload();
					layer.closeAll();
					$(".deployreset").click();
					$("#editdeployboomSure").removeClass("chosefile");
				})
				layer.msg('上传成功');
			} else {
				layer.msg("上传失败请重试");
			}
		}
	});

	$(".editdeploy").click(function() {
		if($("#editdeployName").val() == "") {
			JSShow.showMsg("请输入资源名称");
		} else if($("#editdeployNativePath").val() == "") {
			JSShow.showMsg("请输入资源保存的本地路径");
		} else if($("#editdeployuploadval").val() == "") {
			JSShow.showMsg("选择上传资源文件");
		} else {
			if(!$(".deploy_apk").hasClass("hide") && $("#editdeployPackageName").val() == "") {
				JSShow.showMsg("请输入资源包名");
			} else {
				JSShow.openShow(1, "请确定修改内容", ['300px', '180px'], $("#editdeployboom"), null, null, null)
			}
		}

	})

	$(".closedeploy").click(function() {
		$(".deployChange").hide();
		layer.closeAll();
	})

	//点击确定上传
	$("#editdeployboomSure").click(function() {
		var configName = $("#editdeployName").val();
		var configPackageName = $("#editdeployPackageName").val();
		var changeType = $("#editdeployType").val();
		var nativePath = $("#editdeployNativePath").val();
		var nativePath = $("#editdeployNativePath").val();
		var id = $(this).attr("dataid");
		//如果没有选择上传资源（直接输入）
		if(!$(this).hasClass("chosefile")) {
			deployurl = $("#editdeployuploadval").val();
			Deploy.ChangeDeployList(id, configName, changeType, deployurl, configPackageName, nativePath, function() {
				changebeforeName = "";
				changedoneName = "";
				location.reload();
				layer.closeAll();
				$("#editdeployboomSure").removeClass("chosefile");
			})
		} else {
			changedoneName = $("#editdeployuploadval").val();
			//如果先点击上传后修改（输入框内上传内容）
			if(changebeforeName != changedoneName) {
				var deployurl = $("#editdeployuploadval").val();
				Deploy.ChangeDeployList(id, configName, changeType, deployurl, configPackageName, nativePath, function() {
					changebeforeName = "";
					changedoneName = "";
					location.reload();
					layer.closeAll();
					$("#editdeployboomSure").removeClass("chosefile");
				})
			} else {
				//正常选择文件直接上传
				editdeployupload.upload();
			}

		}
	})

})