layui.use(['form', 'layer', 'laydate', 'table', 'laytpl', 'rate'], function() {
	var form = layui.form,
		layer = layui.layer,
		$ = layui.jquery,
		laydate = layui.laydate,
		laytpl = layui.laytpl,
		rate = layui.rate,
		table = layui.table;
	var msg = layer.load(0, {
		shade: false,
		time: 5000
	});
	var newPage = "";
	var pagelinenum = 0;

	//评价列表
	var gradelist = table.render({
		elem: '#gradelist',
		url: httpUrl + "scorerun/" + 'GetScoreListServlet',
		cellMinWidth: 95,
		page: true,
		height: "full-125",
		limit: 15,
		limits: [5, 10, 15, 20, 25],
		loading: true,
		even: true,
		id: "gradelist",
		cols: [
			[{
					type: 'numbers',
					title: 'ID',
					width: 70,
					align: "center"
				},
				{
					field: 'userInfo',
					title: '用户名',
					align: "center",
					width: 120
				},
				{
					field: 'starScore',
					title: '用户评分',
					align: 'center',
					width: 156,
					templet: function(d) {
						if(d.starScore){
							return '<div id="star' + d.id + '"></div>'
						}else{
							return '尚未评分'
						}
					}
				},
				{
					field: 'tag',
					title: '评价标签',
					align: 'left',
					width: 300,
					templet: function(d) {
						if(d.tag) {
							console.log(d.tag)
							var Taglist = d.tag;
							var TaglistArr = Taglist.split(";");
							var TagHtml = '';
							for(var i = 0; i < TaglistArr.length - 1; i++) {
								TagHtml += '<span class="layui-badge badge' + i + '">' + TaglistArr[i] + '</span>'
							}
							return TagHtml;
						} else {
							return "";
						}
					}
				},
				{
					field: 'body',
					title: '用户评价',
					align: 'left'
				},
				{
					field: 'imageUrl',
					title: '上传图片',
					width: 420,
					style: 'height:150;',
					templet: function(d) {
						if(d.imageUrl) {
							var imgurl = d.imageUrl;
							var imgurlArr = imgurl.split(";");
							var imgurlHtml = "";
							if(imgurlArr.length <= 0) {
								return;
							} else {
								imgurlHtml += '<div class="layer-photos-list">';
								for(var i = 0; i < imgurlArr.length; i++) {
									if(imgurlArr[i] == ""){
										continue;
									}
									imgurlHtml += '<img style="width: 80px;height: 80px;margin-right: 15px;" layer-src="' + httpUrl + imgurlArr[i] + '" src="' + httpUrl + imgurlArr[i] + '">';
								}
								imgurlHtml += '</div>';
								return imgurlHtml;
							}
						} else {
							return "未上传图片数据";
						}

					}
				},
				{
					field: 'datetime',
					title: '上传时间',
					align: 'center',
					width: 165,
					sort: true
				},
				{
					field: 'otherInfo',
					title: '其他信息',
					align: 'left'
				},
				{
					title: '操作',
					width: 180,
					templet: '#gradelistBar',
					fixed: "right",
					align: "center"
				}
			]
		],
		done: function(res, curr, count) {
			layer.close(msg);
			var d = res.data
			for(var i = 0; i < d.length; i++) {
				rate.render({
					elem: '#star' + d[i].id + '',
					value: d[i].starScore,
					readonly: true
				})
			}
			layer.photos({
				closeBtn: 2,
				photos: '.layer-photos-list',
				anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机
			});
		}
	});

	//获取随机数填写背景颜色
	function randombg() {
		var num = parseInt(5 * Math.random());
		switch(num) {
			case 0:
				return "layui-bg-orange";
			case 1:
				return "layui-bg-green";
			case 2:
				return "layui-bg-cyan";
			case 3:
				return "layui-bg-blue";
			case 4:
				return "layui-bg-black";
			default:
				return "layui-bg-black";
		}
	}

	//搜索
	$(".search_btn").on("click", function() {
		reload();
	});

	$(".searchVal").keydown(function(e) {
		if(e.keyCode == 13) {
			reload();
		}
	})

	//清空
	$(".clear_btn").on("click", function() {
		$(".searchVal").val("");
		location.reload();
	});
	
	//填充标签列表方法
	function tagContext(){
		Grade.GetTagList(function(data){
			var data = data.data;
			var badgeBoxHtml = "";
			if(data.length>0){
				for(var i = 0;i<data.length;i++){
					badgeBoxHtml += '<div class="layui-inline badgebox">';
					badgeBoxHtml += 	'<span class="layui-badge">'+data[i].tag+'</span>'
					badgeBoxHtml += 	'<i class="fa fa-times-circle-o delicon" data="' + data[i].tag + '"></i>'
					badgeBoxHtml += '</div>'
				}
			}else{
				badgeBoxHtml = "暂无标签，快去添加吧";
			}
			$(".tagshowlist").html(badgeBoxHtml);
			$(".addtagtxt").val("");
		})
	}
	
	//删除标签方法
	$(document).on("click", ".delicon", function(){
		var thistag = $(this).attr("data");
		JSShow.confirmShow('确定删除该标签？', function(index) {
			Grade.DeleteTagList(thistag, function() {
				layer.close(index);
				tagContext();
				layer.msg("删除成功");
			})
			
		})
	})
	
	//标签管理
	$(".edit_tag").click(function() {
		layer.open({
			type: 1,
			title: "标签管理",
			area: ["50%","485px"],
			content: $("#tagBoom"),
			skin: 'tagboom',
			success: function() {
				tagContext();
			},
			closeBtn: 0,
			btn: ["关闭"],
			btnAlign: 'r',
			btn0: function(index) {
				$("#tagBoom").hide();
				$(".tagshowlist").html("");
			}
		})
	})
	
	$(".addtagtxt").keydown(function(e){
		var tagtxt = $(".addtagtxt").val();
		if(tagtxt.length == 8 && e.keyCode != 8){
			layer.tips('每条最多可添加8个文字', $(".addtagtxt"), {
				tips: [1, '#3595CC'],
				time: 2000
			});
		}
	})
	
	$(".addtag_btn").click(function(){
		var tagtxt = $(".addtagtxt").val();
		if(tagtxt == ""){
			layer.msg("请输入添加内容");
		}else{
			Grade.AddTagList(tagtxt,function(data){
				tagContext();
				layer.msg("添加成功！");
			})
		}
	})

	

	table.on('tool(gradelist)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		if(layEvent === 'show') { //查看详情
			$("#gradeBoom").html("");
			var userbTable = "";
			userbTable += '<table class="layui-table magt10 errmesstable" lay-even>';
			userbTable += '<tbody>';
			if(data.userInfo){
				userbTable += '<tr><td><div>用户信息</div></td><td>' + data.userInfo + '</td></tr>';
			}else{
				userbTable += '<tr><td><div>用户信息</div></td><td>暂无用户信息</td></tr>';
			}
			if(data.modeInfo){
				userbTable += '<tr><td><div>设备信息</div></td><td>' + data.modeInfo + '</td></tr>';
			}else{
				userbTable += '<tr><td><div>设备信息</div></td><td>暂无设备信息</td></tr>';
			}
			if(data.appInfo){
				userbTable += '<tr><td><div>APP版本</div></td><td>' + data.appInfo + '</td></tr>';
			}else{
				userbTable += '<tr><td><div>APP版本</div></td><td>暂无APP版本信息</td></tr>';
			}
			if(data.starScore){
				userbTable += '<tr><td><div>用户评分</div></td><td><div id="defstar' + data.id + '"></div></td></tr>';
			}else{
				userbTable += '<tr><td><div>用户评分</div></td><td>尚未评分</td></tr>';
			}
			if(data.tag){
				var Taglist = data.tag;
				var TaglistArr = Taglist.split(";");
				userbTable += '<tr><td><div>评价标签</div></td><td>'
				for(var i = 0; i < TaglistArr.length - 1; i++) {
					userbTable += '<span class="layui-badge badge' + i + '">' + TaglistArr[i] + '</span> ';
				}
				userbTable += '</td></tr>'
			}else{
				userbTable += '<tr><td><div>评价标签</div></td><td>无标签</td></tr>';
			}
			
			userbTable += '<tr><td><div>评分信息</div></td><td>' + data.body + '</td></tr>';
			userbTable += '<tr><td><div>评分时间</div></td><td>' + data.datetime + '</td></tr>';
			if(data.otherInfo == "" || data.otherInfo == null || data.otherInfo == "undefined" || data.otherInfo == undefined) {
				userbTable += '<tr><td><div>其他信息</div></td><td>暂无其他信息</td></tr>';
			} else {
				userbTable += '<tr><td><div>其他信息</div></td><td>' + data.otherInfo + '</td></tr>';
			}
			userbTable += '</tbody>';
			userbTable += '</table>';
			if(data.imageUrl == "" || data.imageUrl == null || data.imageUrl == "undefined" || data.imageUrl == undefined) {
				userbTable += '<p class="noimglist">暂无图片信息</p>';
			} else {
				userbTable += '<div class="ubbimglist">';
				var boomimgurl = data.imageUrl;
				//boomimgurl = boomimgurl.substring(0, boomimgurl.lastIndexOf(';'));
				var boomimgurlArr = boomimgurl.split(";");
				for(var i = 0; i < boomimgurlArr.length; i++) {
					if(boomimgurlArr[i] == ""){
						continue;
					}
					userbTable += '<img src="' + httpUrl + boomimgurlArr[i] + '" />'
				}
				userbTable += '</div>';
			}
			$("#gradeBoom").append(userbTable);
			rate.render({
				elem: '#defstar' + data.id + '',
				value: data.starScore,
				readonly: true
			})
			layer.open({
				type: 1,
				title: "用户评分信息详情",
				area: "50%",
				offset: '50px',
				content: $('#gradeBoom'), //这里content是一个普通的String
				btn: null,
				cancel: function(){
					$("#gradeBoom").html("");
				}
			});
		} else if(layEvent === 'del') { //删除
			JSShow.confirmShow('确定删除该用户评分？', function(index) {
				var imageUrl = "";
				if(data.imageUrl){
					imageUrl = data.imageUrl;
				}
				Grade.DeleteGradeList(data.id, function() {
					gradelist.reload();
				})
				layer.close(index);
			})
		}
	});

	//重新加载
	function reload() {
		if($(".searchVal").val() != '') {
			table.reload("gradelist", {
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where: {
					searchKey: $(".searchVal").val() //搜索的关键字
				}
			});
		} else {
			table.reload("gradelist", {
				page: {
					curr: 1 //重新从第 1 页开始
				}
			});
		}
	}

	//重新加载
	function reloadremove(num) {
		if($(".searchVal").val() != '') {
			table.reload("gradelist", {
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where: {
					searchKey: $(".searchVal").val() //搜索的关键字
				}
			});
		} else {
			if(pagelinenum <= num) {
				table.reload("gradelist", {
					page: {
						curr: newPage - 1 //重新从前一页开始
					}
				});
			} else {
				table.reload("gradelist", {
					page: {
						curr: newPage //重新从当前 页开始
					}
				});
			}

		}
	}

})