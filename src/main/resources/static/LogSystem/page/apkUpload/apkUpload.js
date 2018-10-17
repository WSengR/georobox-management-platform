layui.use(['form', 'layer', 'table', 'element', 'upload', 'laydate', 'laytpl', 'jquery'], function() {
	var laydate = layui.laydate,
		form = layui.form,
		upload = layui.upload,
		table = layui.table,
		element = layui.element,
		$ = layui.jquery,
		layer = layui.layer;
	var upapk = "",
		upapkicon = "",
		fileSize = "",
		oldapkVersionName = "",
		oldapkVersionCode = "";
	//声明请求数据	
	var apkName = '',
		apkVersionName = '',
		apkVersionCode = '',
		packageName = '',
		apkDescirbe = '',
		apkUploadInfo = '',
		isMustUpload = 0;
	//上个版本的信息
	var oldapkName = '',
		//oldapkUrl = '',
		oldapkIconUrl = '',
		oldapkVersionName = '',
		oldapkVersionCode = '',
		oldpackageName = '',
		oldapkDescirbe = '',
		oldapkUploadInfo = '';
	//跳转等待
	var apkupwait = layer.load(0, {
		shade: false,
		time: 5000
	});
	//新版本信息点击
	$(".apk-download").click(function() {
		$(".apk-uploadbox")[0].reset();
		$(".apk-uploadbox").addClass("hide");
		$(".apk-downloadbox").removeClass("hide");
	});
	//发布新版本点击
	$(".apk-upload").click(function() {
		$(".apk-uploadbox")[0].reset();
		$(".apk-downloadbox").addClass("hide");
		$(".apk-uploadbox").removeClass("hide");
		$("#apkName").val(oldapkName);
		$("#packageName").val(oldpackageName);
		$("#apkVersionCode").val(oldapkVersionCode);
		$("#apkiconuploadval").val(oldapkIconUrl);
		$("#apkDescirbe").val(oldapkDescirbe);
	});
	//上传新版本
	$(".goupapk").click(function() {
		$(".yesapk").removeClass("hide");
		$(".noapk").addClass("hide");
		$(".quoteBox").addClass("hide");
		$(".apk-downloadbox").addClass("hide");
		$(".apk-uploadbox").removeClass("hide");

	});
	var apkDownUrl = "";
	//请求数据渲染页面
	ApkUpload.GetApkUpload(function(data) {
		if(data.data.length == 0) {
			$(".yesapk").addClass("hide");
			$(".noapk").removeClass("hide");
		} else {
			var apkget = data.data[0];
			oldapkName = apkget.apkName;
			oldapkUrl = apkget.apkUrl;
			oldapkIconUrl = apkget.apkIconUrl;
			oldapkVersionName = apkget.apkVersion;
			oldapkVersionCode = apkget.apkVersionCode;
			oldpackageName = apkget.apkPackagename;
			oldapkDescirbe = apkget.apkDescribe;
			oldapkUploadInfo = apkget.apkUploadInfo;
			var apkheadinfo = "",
				apkinfo = "";
			oldapkVersionName = apkget.apkVersion;
			oldapkVersionCode = apkget.apkVersionCode;
			apkheadinfo += '<label class="layui-inline magr15">' + apkget.apkName + '</label>';
			apkheadinfo += '<label class="layui-inline magr15">当前版本：' + apkget.apkVersion + '</label>';
			apkheadinfo += '<label class="layui-inline magr15">上传时间：' + apkget.datetime + '</label>';
			apkheadinfo += '<label class="layui-inline magr15">已更新：' + apkget.downloadNum + '次</label>';
			$(".apkheadinfo").append(apkheadinfo);
			$(".apkinfoname").append(apkget.apkName);
			$("#apklistinfo").append(apkget.apkDescribe);
			if(!apkget.apkUploadInfo) {
				var apkUploadInfoArr = "";
			} else {
				var apkUploadInfoArr = apkget.apkUploadInfo.split(";");
//				var apkUploadInfoArr = apkget.apkUploadInfo.split("\n");
				var newapkUploadInfo = "";
				for(var i = 0; i < apkUploadInfoArr.length; i++) {
					newapkUploadInfo += '<p>' + apkUploadInfoArr[i] + '</p>'
				}
				$("#apklistup").append(newapkUploadInfo);
			}
			$("#apkicon").append('<img class="apklogo" src="' + httpUrl + apkget.apkIconUrl + '" />')
			apkinfo += '<p>大小：' + apkget.apkSize + 'MB </p>';
			apkinfo += '<p>更新：' + apkget.datetime + '</p>';
			apkinfo += '<p>版本：' + apkget.apkVersion + '</p>';
			apkinfo += '<p>更新次数：' + apkget.downloadNum + '次</p>';
			$("#apkinfo").append(apkinfo);
			apkDownUrl = httpUrl + apkget.apkUrl;
			var apkDownUrla = apkDownUrl.replace('\\', ''); //删除地址中第一个\
			var apkDownUrlb = apkDownUrla.replace(/\\/g, '/'); //将地址中所有的\转换为/
			jQuery('#apkerw').qrcode({
				text: apkDownUrlb,
				render: "canvas",
				width: 140,
				height: 140,
				ecLevel: 'H', //误差校正水平选择最高级
				mode: 2, //label模式选择2
				correctLevel: 2
			});
		}
	})

	//点击下载最新版
	$(".apkdownload-btn").click(function() {
		window.open(apkDownUrl);
	});
	var tableIns = table.render({
		elem: '#apklist',
		url: httpUrl + "apkuprun/" + 'GetApkVersionListServlet',
		cellMinWidth: 95,
		page: true,
		//		height: "full-125",
		limit: 15,
		limits: [10, 15, 20, 25],
		loading: true,
		even: true,
		id: "apkListTable",
		cols: [
			[{
					type: 'numbers',
					title: 'ID',
					width: 70,
				},
				{
					field: 'apkName',
					title: '项目名称',
					align: "center"
				},
				{
					field: 'apkVersion',
					title: '项目版本',
					align: 'center',
					sort: true
				},
				{
					field: 'apkUrl',
					title: '文件地址',
					align: 'center'
				},
				{
					field: 'datetime',
					title: '上传时间',
					align: 'center',
					sort: true
				},
				{
					field: 'downloadNum',
					title: '更新次数',
					align: 'center',
					sort: true
				},
				{
					title: '操作',
					width: 220,
					templet: '#apkListTable',
					fixed: "right",
					align: "center"
				}
			]
		],
		done: function(res, curr, count) {
			layer.close(apkupwait);
		}
	});

	table.on('tool(apklist)', function(obj) {
		var versionId = '',
			versionIdhtml = '';
		var layEvent = obj.event,
			data = obj.data;
		if(layEvent === 'delapk') {
			JSShow.confirmShow('是否确定删除该版本Apk？', function() {
				ApkUpload.DeleteApkUpload(data.id, function() {
					layer.close();
					location.reload();
					layer.msg("删除成功");
				})

			})
		} else if(layEvent === 'download') {
			window.open(httpUrl + data.apkUrl);
		} else if(layEvent === 'downloadlist') {
			versionIdhtml = '';
			$(".downlistapk").find(".apklistit").html("版本更新记录");
			versionIdhtml = '<span>（版本：' + data.apkVersion + '）</span>'
			$(".downlistapk").removeClass("hide").find(".apklistit").prepend(versionIdhtml);

			versionId = data.apkVersion;
			var downloadIns = table.render({
				elem: '#apkdllist',
				url: httpUrl + "apkuprun/" + 'GetApkLoadInfoServlet',
				cellMinWidth: 95,
				where: {
					version: versionId
				},
				page: true,
				//		height: "full-125",
				limit: 15,
				limits: [10, 15, 20, 25],
				loading: true,
				even: true,
				cols: [
					[{
							type: 'numbers',
							title: 'ID',
							width: 70,
						},
						{
							field: 'modeInfo',
							title: '手机型号',
							align: 'center',
							sort: true
						},
						{
							field: 'userInfo',
							title: '下载用户',
							align: 'center'
						},
						{
							field: 'datetime',
							title: '下载时间',
							width: 178,
							align: 'center',
							sort: true
						}
					]
				],
				done: function(res, curr, count) {
					versionId = "";
					versionIdhtml = "";
				}
			});

		}
	});

	var $btn = $('#apkboomSure'); //触发上传Apk按钮
	var uploader = WebUploader.create({
		auto: false, // 选完文件后，是否自动上传
		formData: {  
            folder: "apkUp"
 		},
		pick: {
			id: '#apkupload',
			label: '点击选择Apk',
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
		$("#apkboomSure").removeClass("apkupnew");
		$("#apkuploadval").val(file.name);
		fileSize = file.size / 1024 / 1024;
		fileSize = fileSize.toFixed(2);
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
		upapk = res.data;
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
		if(upapkicon == "" || upapkicon == null || upapkicon == undefined) {
			upapkicon = oldapkIconUrl;
		}
		var apkDescirbeImage = "";
		ApkUpload.UploadApkUpload(apkName, apkVersionName, apkVersionCode, apkDescirbe, upapkicon, apkUploadInfo, upapk, packageName, fileSize, isMustUpload, apkDescirbeImage, function(data) {
			$(".quoteBox").removeClass("hide");
			if(data.status == 1 || data.status == "1") {
				layer.closeAll();
				location.reload();
				element.progress('demo', '0%');
			} else {
				layer.closeAll();
				layer.msg("上传失败请重试");
			}
		})

	});

	//开始上传
	$btn.on('click', function() {
		if($(this).hasClass('disabled')) {
			return false;
		} else {
			uploader.upload();
		}

	});
	//上传APK按钮
	$("#apkupload").click(function() {
		uploader.reset(); //重置上传列表
	});

	form.on('checkbox(mustUpload)', function(data) {
		if(data.elem.checked) {
			isMustUpload = 1;
		} else {
			isMustUpload = 0;
		}
	});

	//确认更新按钮
	$("#newapkup").click(function() {
		var oldapkVersionNameArr = oldapkVersionName.split(".")
		var oldapkVersionNameStr = oldapkVersionNameArr.join("");
		apkName = $("#apkName").val();
		apkVersionName = $("#apkVersionName").val();
		apkVersionCode = $("#apkVersionCode").val();
		packageName = $("#packageName").val();
		apkDescirbe = $("#apkDescirbe").val();
		apkUploadInfo = $("#apkUploadInfo").val();
		apkiconuploadval = $("#apkiconuploadval").val();
		var apkval = $("#apkuploadval").val();
		var newapkVersionNameArr = apkVersionName.split(".")
		var newapkVersionNameStr = newapkVersionNameArr.join("");
		if(apkName == "" || apkName == undefined || apkName == null) {
			layer.msg("请输入Apk名称");
		} else if(packageName == "" || packageName == undefined || packageName == null) {
			layer.msg("请输入包名");
		} else if(apkVersionName == "" || apkVersionName == undefined || apkVersionCode == null) {
			layer.msg("请输入版本名");
		} else if(apkVersionCode == "" || apkVersionCode == undefined || apkVersionCode == null) {
			layer.msg("请输入版本号");
		} else if(apkval == "" || apkval == undefined || apkval == null) {
			layer.msg("请选择上传apk");
		} else if(apkiconuploadval == "" || apkiconuploadval == undefined || apkiconuploadval == null) {
			layer.msg("请上传icon");
		} else if(parseInt(oldapkVersionCode) >= parseInt(apkVersionCode)) {
			layer.msg("输入的版本号过低，请重新输入");
		} else {
			JSShow.openShow(1, '请确认上传内容', ['300px', '220px'], $('#apkupboom'), null, null, function(){
				uploader.stop(true);
				layer.closeAll();
			})
		}

	})
	//上传进度暂停继续
	//	$(".stopupload").click(function(){
	//		if(!$(this).hasClass("goupload")){
	//			uploader.stop(true);
	//			$(this).addClass("goupload").text("重新上传");
	//		}else{
	//			uploader.upload();
	//			$(this).removeClass("goupload").text("暂停上传");
	//		}
	//	})

	var uploadiconInst = upload.render({
		elem: '#apkiconupload',
		url: httpUrl + 'customUploadFile',
		data: {
		  folder: "apkUp"
		},
		before: function(obj) {
			//预读本地文件示例，不支持ie8
			obj.preview(function(index, file, result) {
				$('#apkiconuploadval').val(file.name); //icon地址（base64）
			});
		},
		done: function(res) {
			if(res.status == 1) {
				upapkicon = res.data;
				layer.msg('上传成功');
			} else {
				layer.msg("上传失败请重试");
			}
		}
	});
	//重置列表
	$(".resetapk").click(function() {
		//清空图片参数
		upapk = "";
		upapkicon = "";
		isMustUpload = 0;

	});

	//取消
	$("#apkboomStop").click(function() {
		uploader.stop(true);
		layer.closeAll();
	})

})