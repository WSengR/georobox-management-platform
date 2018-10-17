var photoList = []; //发布朋友圈图片列表
var editphotoList = []; //修改朋友圈图片列表
var choseTime = "";
var txtImg = [];
var editemID = "";
$().ready(function() {
	//初始化列表GetFriendsItemList
	getList("", 10, null, null, null);

	//搜索
	$(".search_btn").click(function() {
		$(".circle-list").empty();
		var userId = $("#userName").val();
		if(choseTime != "") {
			var starTime = choseTime.substr(0, 10);
			var endTime = choseTime.substr(13);
		}
		getList(userId, 10, starTime, endTime, "");

	})

	//加载更多
	$(document).on("click", ".morebtn", function() {
		var userId = $("#userName").val();
		var lastTime = $(this).attr("data-last");
		$(this).remove();
		if(choseTime != "") {
			var starTime = choseTime.substr(0, 10);
			var endTime = choseTime.substr(13);
		} else {
			var starTime = "";
			var endTime = "";
		}
		getList(userId, "", starTime, endTime, lastTime);
	});

})
layui.use(['form', 'layer', 'upload', 'laydate', 'laytpl', 'jquery'], function() {
	var laydate = layui.laydate,
		upload = layui.upload,
		$ = layui.jquery,
		//		userN = $('.adminName', window.parent.document).html(),
		//		aa = userN,
		layer = layui.layer;
	laydate.render({
		elem: '#time',
		range: true,
		min: '2018-1-1',
		max: 0,
		done: function(value, date, endDate) {
			choseTime = value;
		}
	});

	//发布朋友圈
	$(".addnew").click(function() {
		$(".edit-add-fr textarea").val("");
		$(".useupload").show();
		getupload();
		$(".usechange").hide();
		JSShow.openBaseShow(1, "发布朋友圈", null, [($(window).width()) * 0.7 + 'px', '530px'], $("#showupnew"), function() {
			getupload();
		}, null, null, 0, null);
		$(".upload-btn").addClass("uploadnew");
	});
	//暂无内容点击事件
	$(document).on("click", ".goupcircle", function() {
		$(".edit-add-fr textarea").val("");
		$(".useupload").show();
		getupload();
		$(".usechange").hide();
		JSShow.openBaseShow(1, "发布朋友圈", null, [($(window).width()) * 0.7 + 'px', '530px'], $("#showupnew"), function() {
			getupload();
		}, null, null, 0, null);
		$(".upload-btn").addClass("uploadnew");
	})
	//发布朋友圈上传图片
	var imguplist = $('.imgupshowlist');

	function getupload() {
		return uploadListIns = upload.render({
			elem: '#imgup',
			url: httpUrl_friend + 'UploadPicServlet',
			multiple: true,
			auto: false,
			bindAction: '#upfirend',
			choose: function(obj) {
				upfiles = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
				//读取本地文件
				obj.preview(function(index, file, result) {
					var imgHtml = $(['<div class="img-box layui-upload-img pull-left">', '<img src="' + result + '" alt="' + file.name + '" />', '<div class="bg-black">', '<span class="fa fa-trash"></span>', '</div>', '</div>'].join(''));
					imguplist.prepend(imgHtml);
					//删除
					imgHtml.find('.fa-trash').on('click', function() {
						delete upfiles[index]; //删除对应的文件
						$(this).parents(".img-box").remove();
						uploadListIns.config.elem.next()[0].value = '';
					});
				});

			},
			before: function() {
				photoList = [];
			},
			done: function(data) {
				photoList.push(data.url);
			},
			allDone: function(data) {
				photoUrls = photoList.join(";");
				photoUrls += ";";
				var con = $(".edit-add-fr").find("textarea").val();
				var num = 1;
				userid = $('.adminName', window.parent.parent.document).html();
				layer.load();
				Friend.SendCirleList(userid, num, con, photoUrls, function() {
					layer.closeAll();
					JSShow.showMsg("发布成功");
					location.reload();
				})
			},
			error: function(index, upload) {
				layer.msg("请求失败，请重试");
			}
		});
	}

	//发布按钮点击弹框
	$(".uploadnew").click(function() {
		JSShow.openShow(1, "提示", "260px", $("#uploadboom"), null, null);
	})
	$(".uploadedit").click(function() {
		JSShow.openShow(1, "修改", "260px", $("#changeboom"), null, null);
	})
	$(".upload-closenew-btn").click(function() {
		uploadListIns = null;
		parent.layer.closeAll();
		$(".imgupshowlist .img-box").remove();
	})
	$(".upload-closeedit-btn").click(function() {
		parent.layer.closeAll();
		$("#changefirend").removeClass("addnewpic");
		txtBox = "";
		imgHtml = "";
		txtImg = [];
		$(".imguplist").find(".img-box").remove();
		editemID = "";
	})
	//发布按钮确认
	$("#upfirend").click(function() {
		if(!$("#showupnew .edit-add-fr").find(".imguplist").has("img").length) {
			photoUrls = ""
			var num = 0;
			var con = $("#showupnew .edit-add-fr").find("textarea").val();
			userid = $('.adminName', window.parent.parent.document).html();
			if(con == "") {
				JSShow.showMsg("内容为空，无法发布");
			} else {
				layer.load();
				Friend.SendCirleList(userid, num, con, photoUrls, function() {
					layer.closeAll();
					JSShow.showMsg("发布成功");
					location.reload();
				})
			}
		}
	})

	//编辑朋友圈按钮确认
	$(document).on("click", "#changefirend", function() {
		if(!$(this).hasClass("addnewpic")) {
			var content = $("#showedit .edit-add-fr textarea").val();
			if(txtImg.length == 0 && content == "") {
				JSShow.showMsg("内容为空，无法发布！")
			} else {
				if(txtImg.length == 0) {
					var typenum = 0;
					var phonelist = "";
				} else {
					var typenum = 1;
					var phonelist = txtImg.join(";");
					phonelist = phonelist + ";";
				}

				Friend.ChangCirleList(editemID, typenum, content, phonelist, function() {
					JSShow.showMsg("发布成功");
					location.reload();
					txtImg = [];
				})
			}

		}
	})
	//编辑朋友圈上传图片
	var changeimguplist = $('.imgchangeshowlist'),
		changeListIns = upload.render({
			elem: '#changeimgup',
			url: httpUrl_friend + 'UploadPicServlet',
			multiple: true,
			auto: false,
			bindAction: '#changefirend',
			choose: function(obj) {
				$("#changefirend").addClass("addnewpic");
				changefiles = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
				//读取本地文件
				obj.preview(function(index, file, result) {
					var imgHtml = $(['<div class="img-box layui-upload-img pull-left">', '<img src="' + result + '" alt="' + file.name + '" />', '<div class="bg-black">', '<span class="fa fa-trash"></span>', '</div>', '</div>'].join(''));
					changeimguplist.prepend(imgHtml);
					//删除
					imgHtml.find('.fa-trash').on('click', function() {
						delete changefiles[index]; //删除对应的文件
						$(this).parents(".img-box").remove();
						if(changeListIns.config.elem.next()[0]) {
							changeListIns.config.elem.next()[0].value = '';
						}
					});
				});
			},
			before: function() {
				editphotoList = [];
			},
			done: function(data) {
				editphotoList.push(data.url);
			},
			allDone: function(data) {
				editphotoList.push.apply(editphotoList, txtImg); //合并新上传数组与原有图片数组
				phonelist = editphotoList.join(";");
				phonelist += ";";
				var content = $(".edit-add-fr").find("textarea").val();
				var typenum = 1;
				Friend.ChangCirleList(editemID, typenum, content, phonelist, function() {
					JSShow.showMsg("发布成功");
					location.reload();
					txtImg = [];
				})
			},
			error: function(index, upload) {
				layer.msg("请求失败，请重试");
			}
		});


	//编辑朋友圈
	$(document).on("click", ".edit", function() {
		var that = $(this);
		editemID = that.parents(".fr-show").attr("id");
		$(".useupload").hide();
		$(".usechange").show();
	
		layer.open({
			title: "编辑朋友圈",
			offset: ($(window).height() - 570) / 2 + 'px',
			type: 1,
			area: [($(window).width()) * 0.7 + 'px', '530px'],
			//content: ['addnew.html', 'no'], //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
			content: $('#showedit'),
			closeBtn: 0,
			success: function(layero, index) {

				$(".imgup").attr("id", "changeimg");
				$(".upload-btn").removeClass("uploadnew");
				var txtBox = that.parents(".fr-show").find(".fr-contxt").html();
				var txtImglength = that.parents(".fr-show").find(".fr-imglist").find("img").length;
				var imgListVal = $(".imguplist");
				for(var i = 0; i < txtImglength; i++) {
					var txtImglist = that.parents(".fr-show").find(".fr-imglist").find("img").eq(i).attr("src");
					txtImg.push(txtImglist.replace(new RegExp(httpUrl_friend), ""));
					var imgHtml = "";
					imgHtml += '<div class="img-box layui-upload-img pull-left">'
					imgHtml += '<img src="' + txtImglist + '"/>'
					imgHtml += '<div class="bg-black">'
					imgHtml += '<span class="fa fa-trash"></span>'
					imgHtml += '</div>'
					imgHtml += '</div>'
					imgListVal.prepend(imgHtml);
				}
				var textareaVal = $("textarea");
				textareaVal.val(txtBox);
				var faatrash = $(".layui-upload-img .fa-trash");
				faatrash.click(function() {
					var delimg = $(this).parents(".img-box").find("img").attr("src");
					delimg = delimg.replace(new RegExp(httpUrl_friend), "")
					txtImg.remove(delimg)
					$(this).parents(".img-box").remove();
				})
			}
		});
	})
	Array.prototype.indexOf = function(val) {
		for(var i = 0; i < this.length; i++) {
			if(this[i] == val) return i;
		}
		return -1;
	};
	Array.prototype.remove = function(val) {
		var index = this.indexOf(val);
		if(index > -1) {
			this.splice(index, 1);
		}
	};
	//重置按钮
	$(".clear_btn").click(function() {
		$("#time").val("");
		$("#userName").val("");
		location.reload();
	});

	//删除朋友圈
	$(document).on("click", ".fr-detele", function(e) {
		var that = $(this);
		var myName = $('.adminName', window.parent.document).html();
		JSShow.confirmShow('确定要删除该条朋友圈么？', function() {
			var itemId = that.parents(".fr-show").attr("id");
			Friend.DeleteCirleList(myName, itemId, function() {
				that.parents(".fr-show").remove();
				location.reload();
				layer.msg("删除成功");
			})

		})
	})

	//评论
	$(document).on("click", ".plbtn", function() {
		if($(this).parents(".fr-show").find(".replytxt").hasClass("on")) {
			$(this).parents(".fr-show").find(".replytxt").hide().removeClass("on").find("textarea").val("");
		} else {
			$(".retxt").removeClass("on").hide().find("textarea").val("");
			$(this).parents(".fr-show").find(".replytxt").show();
			var replyName = $(this).parents(".fr-show").find(".fr-name").find(".user").html();
			$(this).parents(".fr-show").find(".replytxt").addClass("on").find("textarea").attr("placeholder", "回复" + replyName + "：").focus();
		}

	});

	//评论-关闭
	$(document).on("click", ".closebtn", function() {
		$(this).parents(".replytxt").hide().removeClass("on").find("textarea").val("");
	});

	//评论上点击事件-评论窗口-关闭
	$(document).on("click", ".reclosebtn", function() {
		$(this).parents(".rereplytxt").hide().removeClass("on").find("textarea").val("");
	})

	//图片点击放大
	layer.photos({
		closeBtn: 2,
		photos: '.fr-imglist',
		anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机
	});

})