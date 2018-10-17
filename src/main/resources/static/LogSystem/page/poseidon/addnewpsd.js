layui.use(['form', 'layer', 'table', 'element', 'upload', 'laydate', 'laytpl', 'jquery'], function() {
	var laydate = layui.laydate,
		form = layui.form,
		upload = layui.upload,
		table = layui.table,
		element = layui.element,
		$ = layui.jquery,
		layer = layui.layer;
	var categorychose = "";
	var jurisdictionchose = "";
	//	var jurchoselist = [], //权限选择列表
	//		catchose = "", //分类选择
	//		isSystem = 0, //是否调用系统方法
	//		upapkurl = "", //上传Apk插件地址
	//		upapkname = "", //上传Apk插件名称
	//		upiconfilename = "", //上传名称
	//		upiconurl = ""; //上传图标地址

	//上传APK
	/**
	 * 添加新差插件上传方法
	 */
	//========================================添加新插件上传方法============================================================================
	
	var $btn = $('#apkboomSure'); //触发上传Apk按钮
	var uploader = WebUploader.create({
		auto: false, // 选完文件后，是否自动上传
		formData: {  
            folder: "plug"
 		},
		pick: {
			id: '#plugupload',
			label: '点击选择文件',
			multiple: false //默认为true，true表示可以多选文件，HTML5的属性
		},
		resize: false, // 不压缩image
		swf: '../../js/webuploader/Uploader.swf', // swf文件路径
		server: httpUrl + 'customUploadFile', // 文件接收服务端。
		fileNumLimit: 1,
		threads: 1,
	});
	// 当有文件被添加进队列的时候
	uploader.on('fileQueued', function(file) {
		$("#pluguploadval").val(file.name);
	});
	// 文件上传过程中创建进度条实时显示。
	uploader.on('uploadProgress', function(file, percentage) {
		$(".upload-state").text('上传中');
		layui.use('element', function() {
			var element = layui.element;
			element.progress('demo', Math.floor(percentage * 100) + '%');
		});
	});

	// 文件上传成功
	uploader.on('uploadSuccess', function(file, res) {
		layer.msg('上传成功', {
			icon: 6,
			time: 2000
		});
		upapkurl = res.data;
		upapkname = res.data;
		olduploadurl = res.data;
		olduploadname = res.data;
		$(".upload-state").text('已上传' + res.data);
	});
	// 文件上传失败，显示上传出错
	uploader.on('uploadError', function(file) {
		layer.msg("上传失败,点击确认重新上传");
		$("#apkboomSure").click(function() {
			uploader.retry();
		})
	});
	// 完成上传完
	uploader.on('uploadComplete', function(file) {
		if($btn.hasClass("uzip")){
			var plugName = $("#plugName").val();
			var details = $("#plugInfo").val();
			var plugVersioncode = 0;
			var newcatchose = catchose + ";";
			var newjurchoselist = jurchoselist.join(";") + ";";
			Poseidon.UploadZipPlug(plugName, plugVersioncode, newjurchoselist, newcatchose, details, upiconurl, upapkurl, function() {
				layer.closeAll();
				$(".upload-state").text("准备上传");
				element.progress('demo', '0%');
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
				$("#upplugform").hide();
			})
		}else{
			var plugName = $("#plugName").val();
			var PackageName = $("#plugPackageName").val();
			var LauncherActivity = $("#LauncherActivity").val();
			var plugVersioncode = 0;
			var details = $("#plugInfo").val();
			var newcatchose = catchose + ";";
			var newjurchoselist = jurchoselist.join(";") + ";";
			Poseidon.UploadApkPlug(plugName, plugVersioncode, PackageName, LauncherActivity, newjurchoselist, newcatchose, isSystem, details, upiconurl, upapkurl, function() {
				layer.closeAll();
				$(".upload-state").text("准备上传");
				element.progress('demo', '0%');
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
				$("#upplugform").hide();
			})
		}

	});

	//开始上传
	$btn.on('click', function() {
		if($(this).hasClass('disabled')) {
			return false;
		}
		uploader.upload();
	});
	//上传APK按钮
	$("#plugupload").click(function() {
		uploader.reset(); //重置上传列表
	});

	//上传取消
	$("#apkboomStop").click(function() {
		uploader.stop(true);
		layer.close(uploadsure);
	})
	/**
	 * 修改apk插件调用上传方法
	 */
	//==============================================修改apk插件调用上传方法=========================================================================================
	//修改取消
	$("#apkchangeStop").click(function() {
		uploaderchange.stop(true);
		layer.close(changesure);
	})
	var $btnc = $('#apkchangeSure'); //触发上传Apk按钮
	var uploaderchange = WebUploader.create({
		auto: false, // 选完文件后，是否自动上传
		formData: {  
            folder: "plug"
 		},
		pick: {
			id: '#plugupload2',
			label: '点击选择文件',
			multiple: false //默认为true，true表示可以多选文件，HTML5的属性
		},
		resize: false, // 不压缩image
		swf: '../../js/webuploader/Uploader.swf', // swf文件路径
		server: httpUrl + 'customUploadFile', // 文件接收服务端。
		fileNumLimit: 1,
		threads: 1,
	});
	// 当有文件被添加进队列的时候
	uploaderchange.on('fileQueued', function(file) {
		$("#pluguploadval2").val(file.name);
		$btnc.addClass("readyup");
	});
	// 文件上传过程中创建进度条实时显示。
	uploaderchange.on('uploadProgress', function(file, percentage) {
		$(".upload-state").text('上传中');
		layui.use('element', function() {
			var element = layui.element;
			element.progress('demo2', Math.floor(percentage * 100) + '%');
		});
	});

	// 文件上传成功
	uploaderchange.on('uploadSuccess', function(file, res) {
		layer.msg('上传成功', {
			icon: 6,
			time: 2000
		});
		upapkurl = res.data;
		upapkname = res.data;
		olduploadurl = res.data;
		olduploadname = res.data;
		$(".upload-state").text('已上传' + res.url);
	});
	// 文件上传失败，显示上传出错
	uploaderchange.on('uploadError', function(file) {
		layer.msg("上传失败,点击确认重新上传");
		$("#apkchangeSure").click(function() {
			uploaderchange.retry();
		})
	});
	
	// 完成上传完**上传新apk或者ZIP时方法
	uploaderchange.on('uploadComplete', function(file) {
		if($btnc.hasClass("uzip")){
			var plugName = $("#plugName").val();
			var details = $("#plugInfo").val();
			var newcatchose = catchose + ";";
			var newjurchoselist = jurchoselist.join(";") + ";";
			var version = 0;
			Poseidon.ChangeZipPlug(changeid, version, plugName, newjurchoselist, newcatchose, details, oldicon, olduploadurl, function() {
				layer.closeAll();
				element.progress('demo2', '0%');
				$(".upload-state").text("准备上传");
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
				$("#upplugform").hide();
			})

		}else{
			var plugName = $("#plugName").val();
			var PackageName = $("#plugPackageName").val();
			var LauncherActivity = $("#LauncherActivity").val();
			var details = $("#plugInfo").val();
			var newcatchose = catchose + ";";
			var version = 0;
			var newjurchoselist = jurchoselist.join(";") + ";";
			Poseidon.ChangeApkPlug(changeid, version, plugName, PackageName, LauncherActivity, newjurchoselist, newcatchose, isSystem, details, oldicon, olduploadurl, function(data) {
				element.progress('demo2', '0%');
				$(".upload-state").text("准备上传");
				if(data.Status == "-1"){
					layer.msg(data.Message);
					layer.close(changesure);
					$("#pluguploadval2").val("");
				}else{
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
					$("#upplugform").hide();
				}
			})

		}
	});

	//开始上传--无新文件或者zip时方法
	$btnc.on('click', function() {
		if($(this).hasClass('readyup')) {
			uploaderchange.upload();
		} else {
			if($(this).hasClass("uzip")){
				var plugName = $("#plugName").val();
				var PackageName = $("#plugPackageName").val();
				var LauncherActivity = $("#LauncherActivity").val();
				var details = $("#plugInfo").val();
				var version = 0;
				var newcatchose = catchose + ";";
				var newjurchoselist = jurchoselist.join(";") + ";";
				olduploadurl = $("#pluguploadval2").val();
				console.log(olduploadurl);
				olduploadname = "";
				Poseidon.ChangeZipPlug(changeid, version, plugName, newjurchoselist, newcatchose, details, oldicon, olduploadurl, function() {
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
					$("#upplugform").hide();
				})
			}else{
				var plugName = $("#plugName").val();
				var PackageName = $("#plugPackageName").val();
				var LauncherActivity = $("#LauncherActivity").val();
				var details = $("#plugInfo").val();
				var version = 0;
				var newcatchose = catchose + ";";
				var newjurchoselist = jurchoselist.join(";") + ";";
				olduploadurl = $("#pluguploadval2").val();
				console.log(olduploadurl);
				olduploadname = "";
				Poseidon.ChangeApkPlug(changeid, version, plugName, PackageName, LauncherActivity, newjurchoselist, newcatchose, isSystem, details, oldicon, olduploadurl, function(data) {
					if(data.Status == "-1"){
						layer.msg(data.Message);
						layer.close(changesure);
						$("#pluguploadval2").val("");
					}else{
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
					}
				})
			}
		}

	});
	
	//上传APK按钮
	$("#plugupload2").click(function() {
		uploaderchange.reset(); //重置上传列表
	});
	//上传icon
	//上传icon
	//上传icon
	var uploadicon = upload.render({
		elem: '#plugiconupload',
		url: httpUrl + 'customUploadFile',
		data: {
		  folder: "plug"
		},
		before: function(obj) {
			//预读本地文件示例，不支持ie8
			obj.preview(function(index, file, result) {
				$('#plugiconuploadval').val(file.name); //icon地址（base64）
			});
		},
		done: function(res) {
			layer.msg('上传成功');
			if(res.status == "1") {
				upiconfilename = res.data;
				upiconurl = res.data;
				oldicon = res.data;
				layer.msg('上传成功');
			} else {
				layer.msg("上传失败请重试");
			}
		}
	});

	//添加插件
	$(".addnewpsd").click(function() {

		$(".psdupbtn").show();
		$(".psdchangebtn").hide();
		if($(this).hasClass("addnewpsd_apk")) {
			$(".plug_apk").show();
			$(".plug_link").hide();
			$(".plug_zip").hide();
			$("#uploadfile").show();
			$("#uploadfile2").hide();
		} else if($(this).hasClass("addnewpsd_link")) {
			$(".plug_apk").hide();
			$(".plug_link").show();
			$(".plug_zip").hide();
		}else if($(this).hasClass("addnewpsd_zip")){
			$(".plug_apk").hide();
			$(".plug_link").hide();
			$(".plug_zip").show();
			$("#uploadfile").show();
			$("#uploadfile2").hide();
		}

		JSShow.openBaseShow(1, "添加插件", "", ['800px', ($(window).height()) * 0.7 + 'px'], $('#upplugform'), function(layero, index) {
			Poseidon.PsdGetCategory(function(data) {
				var data = data.data;
				for(var i = 0; i < data.length; i++) {
					categorychose += '<input name="categorychose" value="' + data[i].categoryId + '" title="' + data[i].categoryDes + '" type="radio" lay-filter="catchose">'
				}
				$("#categorychose").html(categorychose);
				form.render();
			})
			Poseidon.PsdGetjurisdiction(function(data) {
				var data = data.data;
				for(var i = 0; i < data.length; i++) {
					jurisdictionchose += '<input name="jurisdictionchose" lay-skin="primary" value="' + data[i].permissionId + '" title="' + data[i].permissionDes + '" type="checkbox" lay-filter="jurchose">'
				}
				$("#jurisdictionchose").html(jurisdictionchose);
				form.render();
			})
		}, null, null, 1, function() {
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
	form.on('checkbox(jurchose)', function(data) {
		if(data.elem.checked) {
			jurchoselist.push(data.value);
		} else {
			jurchoselist.splice($.inArray(data.value, jurchoselist), 1);
		}
	});
	form.on('radio(catchose)', function(data) {
		catchose = data.value;
	});
	form.on('checkbox(isSystem)', function(data) {
		if(data.elem.checked) {
			isSystem = 1;
		} else {
			isSystem = 0;
		}
	});

	//上传插件

	$(".newplugup").click(function() {
		if($(this).hasClass("plug_apk")) {
			if($("#pluguploadval").val() == "") {
				layer.msg("请选择上传插件");
			} else if($("#plugiconuploadval").val() == "") {
				layer.msg("请选择插件图标");
			} else if($("#plugName").val() == "") {
				layer.msg("请输入插件名称");
			} else if($("#plugPackageName").val() == "") {
				layer.msg("请输入包名");
			} else if(catchose == "") {
				layer.msg("请选择分类");
			} else if(jurchoselist.length == 0) {
				layer.msg("请选择权限");
			} else {
				uploadsure = layer.open({
					type: 1,
					title: "添加插件",
					area: ["300px", "240px"],
					content: $('#plugupboom'), //这里content是一个普通的String
					success: function(){
						$("#apkboomSure").removeClass("uzip");
					},
					cancel: function() {
						uploader.stop(true);
						layer.close();
					}
				});
			}
		} else if($(this).hasClass("plug_link")) {
			if($("#plugiconuploadval").val() == "") {
				layer.msg("请选择插件图标");
			} else if($("#plugName").val() == "") {
				layer.msg("请输入插件名称");
			} else if($("#plugAddr").val() == "") {
				layer.msg("请输入插件地址");
			} else if(catchose == "") {
				layer.msg("请选择分类");
			} else if(jurchoselist.length == 0) {
				layer.msg("请选择权限");
			} else {
				JSShow.confirmShow('是否确定添加该插件？', function() {
					var plugName = $("#plugName").val();
					var urls = $("#plugAddr").val();
					var details = $("#plugInfo").val();
					var newcatchose = catchose + ";";
					var newjurchoselist = jurchoselist.join(";") + ";";
					var plugVersioncode = 0;
					Poseidon.UploadLinkPlug(plugName, plugVersioncode, newjurchoselist, newcatchose, urls, details, upiconurl, function() {
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
						$("#upplugform").hide();
					})
				})
			}
		}else if($(this).hasClass("plug_zip")){
			if($("#pluguploadval").val() == "") {
				layer.msg("请选择上传插件");
			} else if($("#plugiconuploadval").val() == "") {
				layer.msg("请选择插件图标");
			} else if($("#plugName").val() == "") {
				layer.msg("请输入插件名称");
			} else if(catchose == "") {
				layer.msg("请选择分类");
			} else if(jurchoselist.length == 0) {
				layer.msg("请选择权限");
			} else {
				uploadsure = layer.open({
					type: 1,
					title: "添加插件",
					area: ["300px", "240px"],
					content: $('#plugupboom'), //这里content是一个普通的String
					success: function(){
						$("#apkboomSure").addClass("uzip");
					},
					cancel: function() {
						uploader.stop(true);
						layer.close();
					}
				});
			}
		}
	})

	//修改插件
	$(".changeplug").click(function() {
		/*else if($("#LauncherActivity").val() == "") {
			layer.msg("请输入LauncherActivity");
		}  修改为不必传*/
		if($(this).hasClass("plug_apk")) {
			if($("#pluguploadval2").val() == "") {
				layer.msg("请选择上传插件");
			} else if($("#plugiconuploadval").val() == "") {
				layer.msg("请选择插件图标");
			} else if($("#plugName").val() == "") {
				layer.msg("请输入插件名称");
			} else if($("#plugPackageName").val() == "") {
				layer.msg("请输入包名");
			} else if(catchose == "") {
				layer.msg("请选择分类");
			} else if(jurchoselist.length == 0) {
				layer.msg("请选择权限");
			} else {
				changesure = layer.open({
					type: 1,
					title: "修改插件",
					area: ["300px", "240px"],
					content: $('#plugchangeboom'),
					success: function(){
						$("#apkchangeSure").removeClass("uzip");
					},
					cancel: function() {
						uploaderchange.stop(true);
						layer.close();
					}
				});

			}
		} else if($(this).hasClass("plug_link")) {
			if($("#plugiconuploadval").val() == "") {
				layer.msg("请选择插件图标");
			} else if($("#plugName").val() == "") {
				layer.msg("请输入插件名称");
			} else if($("#plugAddr").val() == "") {
				layer.msg("请输入插件地址");
			} else if(catchose == "") {
				layer.msg("请选择分类");
			} else if(jurchoselist.length == 0) {
				layer.msg("请选择权限");
			} else {
				var urls = $("#plugAddr").val();
//				if(urls == oldlinkurl){
//					urls = "";
//				}
				JSShow.confirmShow('是否确定修改该插件？', function() {
					var plugName = $("#plugName").val();
					var details = $("#plugInfo").val();
					var newcatchose = catchose + ";";
					var newjurchoselist = jurchoselist.join(";") + ";";
					var version = 0;
					Poseidon.ChangeLinkPlug(changeid, version, plugName, newjurchoselist, newcatchose, urls, details, oldicon, function() {
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
						//===============
						$("#upplugform").hide();
					})
				})
			}

		}else if($(this).hasClass("plug_zip")){
			if($("#pluguploadval2").val() == "") {
				layer.msg("请选择上传插件");
			} else if($("#plugiconuploadval").val() == "") {
				layer.msg("请选择插件图标");
			} else if($("#plugName").val() == "") {
				layer.msg("请输入插件名称");
			} else if(catchose == "") {
				layer.msg("请选择分类");
			} else if(jurchoselist.length == 0) {
				layer.msg("请选择权限");
			} else {
				changesure = layer.open({
					type: 1,
					title: "修改插件",
					area: ["300px", "240px"],
					content: $('#plugchangeboom'),
					success: function(){
						$("#apkchangeSure").addClass("uzip");
					},
					cancel: function() {
						uploaderchange.stop(true);
						layer.close();
					}
				});
			}
		}
	})

})