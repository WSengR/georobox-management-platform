var size = 10;
var myName = $('.adminName', window.parent.document).html();
$().ready(function() {

	//点赞SendFavort
//	$(document).on("click", ".zanbtn", zan); //点赞，功能完成

	function zan() {
		var itemId = $(this).parents(".fr-show").attr("id");
		var zannum = $(this).find(".zannum").html();
		var that = $(this)
		Friend.SendCirleFavor(myName, itemId, function(data) {
			layer.close(layer.index);
			if(data.state == "success") {
				if(!that.hasClass("on")) { //判断是否已经点赞
					that.addClass("on layui-anim layui-anim-scaleSpring").parents(".fr-show").find(".fr-reply").removeClass("hide") //取消隐藏
					zannum++;
					that.find(".zannum").html(zannum);
					if(that.parents(".fr-show").find(".fr-zanlist").html() == "") { //判断是不是第一个点赞
						var zanHtml = '<span class="fa fa-heart"></span><span class="zanname ' + myName + '">' + myName + '</span>';
						that.parents(".fr-show").find(".fr-zanlist").append(zanHtml);
					} else {
						var zanHtml = '<span class="zanname ' + myName + '">' + myName + '</span>';
						that.parents(".fr-show").find(".fr-zanlist").append(zanHtml);
					}
				} else {
					zannum--;
					that.find(".zannum").html(zannum);
					that.removeClass("on layui-anim layui-anim-scaleSpring").parents(".fr-show").find("." + myName).remove();
					//判断是否有其他zan
					if(!that.parents(".fr-show").find(".fr-zanlist").find("span").hasClass("zanname")) {
						that.parents(".fr-show").find(".fr-zanlist").empty();
						//判断是否有评论，否则隐藏回复框
						if(!that.parents(".fr-show").find(".fr-reply-list").has("p").length) {
							that.parents(".fr-show").find(".fr-reply").addClass("hide");
						}
					}

				}
			} else {
				layer.msg("请求延时，请重试！");
			}

		})

	}

	//评论SendItemComment
	$(document).on("click", ".replybtn", commenta);

	function commenta() {
		var itemId = $(this).parents(".fr-show").attr("id");
		var txtCon = $(this).siblings("textarea").val();
		var that = $(this);
		Friend.SendCirleComment(myName, itemId, txtCon, "", function(data) {
			var mess = data.bodyObject;
			var txtHtml = "";
			if(data.state == "success") {
				if(!txtCon == "") {
					txtHtml += '<p data-id="' + mess.commentId + '"><span class="fr-user">' + mess.userId + '</span>：' + mess.content + '<a class="replythis">回复</a><a class="delthis">删除</a></p>'
					that.parents(".fr-show").find(".fr-reply").removeClass("hide"); //取消隐藏
					that.parents(".fr-show").find(".fr-reply-list").append(txtHtml);
				}
				that.parents(".replytxt").hide().removeClass("on").find("textarea").val("");
			} else {
				layer.msg("请求延时，请重试！");
			}

		})
	}

	//回复评论SendItemComment
	$(document).on("click", ".rereplybtn", reply); //绑定事件
	function reply() { //定义方法
		var itemId = $(this).parents(".fr-show").attr("id");
		var txtCon = $(this).siblings("textarea").val();
		var that = $(this);
		Friend.SendCirleComment(myName, itemId, txtCon, toReplyId, function(data) {
			var mess = data.bodyObject;
			var txtHtml = "";
			if(data.state == "success") {
				if(!txtCon == "") {
					txtHtml += '<p data-id="' + mess.commentId + '"><span class="fr-user">' + mess.userId + '</span>回复<span class="fr-user">' + mess.toReplyId + '</span>：' + mess.content + '<a class="replythis">回复</a><a class="delthis">删除</a></p>'
					that.parents(".fr-show").find(".fr-reply").removeClass("hide"); //取消隐藏
					that.parents(".fr-show").find(".fr-reply-list").append(txtHtml);
				}
				that.parents(".rereplytxt").hide().removeClass("on").find("textarea").val("");
			} else {
				layer.msg("请求延时，请重试！");
			}

		})
	}
	$(document).on("click", ".replythis", replythis);
	$(document).on("click", ".delthis", delthis);
})
//请求后台列表
function getList(userId, size, startime, endtime, lastTime) {
	Friend.GetCirleList(userId, size, startime, endtime, lastTime, function(data) {
		var time = "";
		var lastOne = "";
		if(data.length == 0 && lastTime == null) {
//			$(".circle-list").append("暂无数驱蚊器翁据");
			$(".fribox").addClass("hide");
			$(".nocircle").removeClass("hide");
		} else {
			$(".fribox").removeClass("hide");
			$(".nocircle").addClass("hide");
			for(var i = 0; i < data.length; i++) {
				time = new Date(parseInt(data[i].createTime));
				var crTime = getDate(time);
				var circleHtml = "";
				lastOne = data[data.length - 1].createTime;
				if(!data[i].photoUrls == "" || !data[i].photoUrls == undefined || !data[i].photoUrls == null || !data[i].photoUrls == "undefined" || !data[i].photoUrls == "null") {
					var photoUrlsList = data[i].photoUrls.split(";");
					if(photoUrlsList.length == 2) {
						circleHtml += '<div id="' + data[i].id + '" class="fr-show layui-clear layui-row layui-col-space20 style1">'
					} else if(photoUrlsList.length > 2) {
						circleHtml += '<div id="' + data[i].id + '" class="fr-show layui-clear layui-row layui-col-space20 style2">'
					}
				} else {
					circleHtml += '<div id="' + data[i].id + '" class="fr-show layui-clear layui-row layui-col-space20 style1">'
				}
//				circleHtml += '<span class="fa fa-edit edit"></span>'  //编辑按钮，功能已完成
				circleHtml += '<div class="pull-left user-head">'
				circleHtml += '<img src="../../images/a6.jpg" />'
				circleHtml += '</div>'
				circleHtml += '<div class="fr-con">'
				circleHtml += '<p class="fr-name"><span class="user">' + data[i].userId + '</span><span class="fr-time">' + crTime + '</span></p>'
				if(!data[i].content == "" || !data[i].content == undefined || !data[i].content == null || !data[i].content == "undefined" || !data[i].content == "null") {
					circleHtml += '<p class="fr-contxt">'+data[i].content+'</p>'
				}

				if(!data[i].photoUrls == "" || !data[i].photoUrls == undefined || !data[i].photoUrls == null || !data[i].photoUrls == "undefined" || !data[i].photoUrls == "null") {

					circleHtml += '<div class="fr-imglist layui-clear magt10 layui-col-space10">'
					for(var imgnum = 0; imgnum < photoUrlsList.length - 1; imgnum++) {
						circleHtml += '<img class="fr-img layui-col-md3 layui-col-xs6" layer-src="' + httpUrl_friend + photoUrlsList[imgnum] + '" src="' + httpUrl_friend + photoUrlsList[imgnum] + '" />'
					}
					circleHtml += '</div>'
				}
				circleHtml += '<div class="layui-clear">'
				circleHtml += '<div class="layui-btn-group">'
				var favpeo = [];
				for(var favnum = 0; favnum < data[i].favorters.length; favnum++) {
					favpeo.push(data[i].favorters[favnum].userId);
				}
				if($.inArray(myName, favpeo) < 0) {
					circleHtml += '<button class="layui-btn layui-btn-primary zanbtn"><span class="fa fa-thumbs-o-up "></span><span class="zannum">' + data[i].favorters.length + '</span></button>'
				} else {
					circleHtml += '<button class="layui-btn layui-btn-primary zanbtn on layui-anim layui-anim-scaleSpring"><span class="fa fa-thumbs-o-up "></span><span class="zannum">' + data[i].favorters.length + '</span></button>'
				}
//				circleHtml += '<button class="layui-btn layui-btn-primary plbtn"><span class="fa fa-twitch"></span>评论</button>'//评论按钮，已完成
				circleHtml += '<button class="layui-btn layui-btn-primary fr-detele"><span class="fa fa-trash"></span>删除</button>'
				circleHtml += '</div>'
				circleHtml += '</div>'
				circleHtml += '</div>'
				if(data[i].favorters == "" && data[i].comments == "") {
					circleHtml += '<div class="fr-reply hide">'
				} else {
					circleHtml += '<div class="fr-reply">'
				}
				circleHtml += '<div class="triangle-up"></div>'
				if(data[i].favorters == "" || data[i].favorters == undefined || data[i].favorters == null || data[i].favorters == "undefined" || data[i].favorters == "null") {
					circleHtml += '<div class="fr-zanlist"></div>'
				} else {
					circleHtml += '<div class="fr-zanlist">'
					circleHtml += '<span class="fa fa-heart"></span>'
					for(var zanum = 0; zanum < data[i].favorters.length; zanum++) {
						circleHtml += '<span class="zanname ' + data[i].favorters[zanum].userId + '">' + data[i].favorters[zanum].userId + '</span>'
					}
					circleHtml += '</div>'

				}
				circleHtml += '<div class="fr-reply-list">'
				for(var conum = 0; conum < data[i].comments.length; conum++) {
					//<a class="replythis">回复</a> 回复按钮
					if(data[i].comments[conum].toReplyId == "" || data[i].comments[conum].toReplyId == undefined || data[i].comments[conum].toReplyId == null || data[i].comments[conum].toReplyId == "undefined" || data[i].comments[conum].toReplyId == "null") {
						circleHtml += '<p data-id="' + data[i].comments[conum].commentId + '"><span class="fr-user">' + data[i].comments[conum].userId + '</span>：' + data[i].comments[conum].content + '<a class="delthis">删除</a></p>';
					} else {
						circleHtml += '<p data-id="' + data[i].comments[conum].commentId + '"><span class="fr-user">' + data[i].comments[conum].toReplyId + '</span>回复<span class="fr-user">' + data[i].comments[conum].userId + '</span>：' + data[i].comments[conum].content + '<a class="delthis">删除</a></p>'
					}
				}
				circleHtml += '</div>'
				circleHtml += '</div>'
				circleHtml += '<div class="replytxt retxt layui-row">'
				circleHtml += '<textarea class="layui-textarea"></textarea>'
				circleHtml += '<button class="layui-btn replybtn">回复</button>'
				circleHtml += '<button class="layui-btn closebtn">关闭</button>'
				circleHtml += '</div>'

				circleHtml += '<div class="rereplytxt retxt layui-row">'
				circleHtml += '<textarea class="layui-textarea"></textarea>'
				circleHtml += '<button class="layui-btn rereplybtn">回复</button>'
				circleHtml += '<button class="layui-btn reclosebtn">关闭</button>'
				circleHtml += '</div>'

				circleHtml += '</div>'

				$(".circle-list").append(circleHtml);

			}
		}

		if(size == null || size == "" || size == undefined) {
			size = 10;
		}
		if(data.length >= size) {
			$(".circle-list").append('<button class="layui-btn layui-center layui-btn-lg morebtn" data-last="' + lastOne + '">查看更多</button>');
		}
		layer.photos({
			closeBtn: 2,
			photos: '.fr-imglist',
			anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机
		});
	})
}

//转化时间戳添“0”方法
function add0(m) {
	return m < 10 ? '0' + m : m
};
//转化时间戳
function getDate(sjc) {
	var time = new Date(sjc);
	var y = time.getFullYear();
	var m = time.getMonth() + 1;
	var d = time.getDate();
	var h = time.getHours();
	var mm = time.getMinutes();
	var s = time.getSeconds();
	return y + '-' + add0(m) + '-' + add0(d) + ' ' + add0(h) + ':' + add0(mm) + ':' + add0(s);
};

//评论上点击事件-回复
function replythis() {
	if($(this).parents(".fr-show").find(".rereplytxt").hasClass("on")) {
		$(this).parents(".fr-show").find(".rereplytxt").hide().removeClass("on").find("textarea").val("");
	} else {
		$(".retxt").removeClass("on").hide().find("textarea").val("");
		$(this).parents(".fr-show").find(".rereplytxt").show();
		toReplyId = $(this).siblings(".fr-user").html();
		$(this).parents(".fr-show").find(".rereplytxt").addClass("on").find("textarea").attr("placeholder", "回复" + toReplyId + "：").focus();
	}
}
//评论上点击事件-删除
function delthis() {
	//	var itemId = $(this).parents(".fr-show").attr("id");
	//	var txtCon = $(this).siblings("textarea").val();
	var that = $(this);
	var conId = that.parents("p").attr("data-id");
	var box = that.parents(".fr-reply");
	var frelist = that.parents(".fr-reply-list");
	var zan = that.parents(".fr-show").find(".fr-zanlist").find("span");
	JSShow.confirmShow('确定删除该条评论？', function(index) {
		Friend.DeleteCirleComment(conId, function(data) {
			layer.close(index);
			that.parents("p").remove();
			if(frelist.has("p").length || zan.hasClass("zanname")) {

			} else {
				box.addClass("hide");
			}
		})
	})

}