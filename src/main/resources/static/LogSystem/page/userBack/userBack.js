layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function() {
	var form = layui.form,
		layer = layui.layer,
		$ = layui.jquery,
		element = layui.element,
		laydate = layui.laydate,
		laytpl = layui.laytpl,
		table = layui.table;
	var choseTime = "";
	//日期选项
	laydate.render({
		elem: '#userBackDate',
		min: '2018-1-1',
		max: 0,
		done: function(value, date, endDate) {
			choseTime = value;
		}
	});

	//反馈信息
	var userBackTable = table.render({
		elem: '#userBackTable',
		url: httpUrl + "userbackrun/" + 'GetUserBackListServlet',
		cellMinWidth: 95,
		page: true,
		height: "full-125",
		limit: 15,
		limits: [10, 15, 20, 25],
		loading: true,
		even: true,
		id: "userBackTable",
		cols: [
			[{
					type: 'numbers',
					title: 'id',
					width: 80,
					align: "center"
				},
				{
					field: 'infoUser',
					title: '用户信息',
					align: 'center',
					width: 120,
					sort: true
				},
				{
					field: 'infoBack',
					title: '反馈信息',
					align: 'left',
				},
				{
					field: 'infoMode',
					title: '设备信息',
					align: 'center',
					width: 160,
					sort: true
				},
				{
					field: 'infoAppinfo',
					title: 'App软件版本',
					align: 'center',
					width: 150,
					sort: true
				},
				{
					field: 'infoBackUrl',
					title: '上传图片',
					width: 420,
					style: 'height:150;',
					templet: function(d) {
						if(d.infoBackUrl) {
							var imgurl = d.infoBackUrl;
							imgurl = imgurl.substring(0, imgurl.lastIndexOf(';'));
							var imgurlArr = imgurl.split(";");
							var imgurlHtml = "";
							if(imgurlArr.length <= 0) {
								return;
							} else {
								imgurlHtml += '<div class="layer-photos-list">';
								for(var i = 0; i < imgurlArr.length; i++) {
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
					title: '时间',
					align: 'left',
					width: 180,
					sort: true
				},
				{
					field: 'infoOther',
					title: '其他信息',
					align: 'left'
				},
				{
					title: '操作',
					width: 160,
					templet: '#userBackTableBar',
					fixed: "right",
					align: "center"
				}
			]
		],
		done: function(res, curr, count) {
			layer.photos({
				closeBtn: 2,
				photos: '.layer-photos-list',
				anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机
			});
		}
	});

	//搜索
	$(".search_btn").on("click", function() {
		var keyString = $(".searchVal").val();
		if(keyString != '' || choseTime != '') {
			table.reload("userBackTable", {
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where: {
					searchKey: keyString, //搜索的关键字
					startTime: choseTime,
					endTime: choseTime
				}
			})
		} else {
			layer.msg("请输入搜索的内容");
		}
	});

	//清空
	$(".clear_btn").on("click", function() {
		userBackTable.reload();
	});

	table.on('tool(userBackTable)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		if(layEvent === 'show') { //查看详情
			$("#userBackBoom").html("");
			var userbTable = "";
			userbTable += '<table class="layui-table magt10 errmesstable" lay-even>';
			userbTable += '<tbody>';
			userbTable += '<tr><td><div>用户信息</div></td><td>' + data.infoUser + '</td></tr>';
			userbTable += '<tr><td><div>设备信息</div></td><td>' + data.infoMode + '</td></tr>';
			userbTable += '<tr><td><div>APP版本</div></td><td>' + data.infoAppinfo + '</td></tr>';
			userbTable += '<tr><td><div>反馈信息</div></td><td>' + data.infoBack + '</td></tr>';
			userbTable += '<tr><td><div>反馈时间</div></td><td>' + data.datetime + '</td></tr>';
			if(data.infoOther == "" || data.infoOther == null || data.infoOther == "undefined" || data.infoOther == undefined) {
				userbTable += '<tr><td><div>其他信息</div></td><td>暂无其他信息</td></tr>';
			} else {
				userbTable += '<tr><td><div>其他信息</div></td><td>' + data.infoOther + '</td></tr>';
			}
			userbTable += '</tbody>';
			userbTable += '</table>';
			if(data.infoBackUrl == "" || data.infoBackUrl == null || data.infoBackUrl == "undefined" || data.infoBackUrl == undefined) {
				userbTable += '<p class="noimglist">暂无图片信息</p>';
			} else {
				userbTable += '<div class="ubbimglist">';
				var boomimgurl = data.infoBackUrl;
				boomimgurl = boomimgurl.substring(0, boomimgurl.lastIndexOf(';'));
				var boomimgurlArr = boomimgurl.split(";");
				for(var i = 0; i < boomimgurlArr.length; i++) {
					userbTable += '<img src="' + httpUrl + boomimgurlArr[i] + '" />'
				}
				userbTable += '</div>';
			}
			$("#userBackBoom").append(userbTable);
			JSShow.openShow(1, "用户反馈信息详情", '50%', $('#userBackBoom'), null, null, function(index, layero) {
				$("#userBackBoom").html("");
			})
		} else if(layEvent === 'del') {
			JSShow.confirmShow('是否确定删除该条反馈？', function() {
				var phoneurl = "";
				if(!data.infoBackUrl) {
					phoneurl = "";
				} else {
					phoneurl = data.infoBackUrl;
				}
				UserBack.DeleteUserBack(data.id, function() {
					layer.close();
					table.reload("userBackTable", {
						page: {
							curr: 1 //重新从前一页开始
						}
					});
					layer.msg("删除成功");
				})
			})
		}
	});

})