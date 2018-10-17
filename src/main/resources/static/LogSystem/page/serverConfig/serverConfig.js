layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function() {
	var form = layui.form,
		layer = layui.layer,
		$ = layui.jquery,
		laydate = layui.laydate,
		laytpl = layui.laytpl,
		table = layui.table;

	//添加
	$(".addnewserver").click(function() {
		$("#serverName").val("");
		$("#serverUrl").val("");
		$("#serverDis").val("");
		JSShow.openShow(1, "新增服务信息", "440px", $('.addnewserverbox'), function(index, layero) {
			JSShow.confirmShow('是否确定内容并提交？', function() {
				var serverName = $("#serverName").val();
				var serverUrl = $("#serverUrl").val();
				var serverDis = $("#serverDis").val();
				if(serverName == "") {
					layer.msg("服务名称为空，请填写");
				} else if(serverUrl == "") {
					layer.msg("服务地址为空，请填写");
				} else {
					ServerUrl.UplaodServer(serverName, serverUrl, serverDis, function() {
						serverTable.reload();
					})
					layer.closeAll();
					layer.msg('上传成功！');
				}
			});
		});
	});

	//获取列表
	var serverTable = table.render({
		elem: '#serverTable',
		url: httpUrl + "serverurlrun/" + 'GetServerUrlServlet',
		cellMinWidth: 95,
		page: true,
		height: "full-125",
		limit: 15,
		limits: [10, 15, 20, 25],
		loading: true,
		even: true,
		id: "serverTable",
		cols: [
			[{
					type: 'numbers',
					title: 'ID',
					width: 70,
				},
				{
					field: 'serverName',
					title: '服务名称',
					width: 220
				},
				{
					field: 'serverUrl',
					title: '服务地址',
					align: 'left'
				},
				{
					field: 'serverDis',
					title: '服务描述',
					align: 'left'
				},
				{
					field: 'datetime',
					title: '时间',
					width: 200,
					align: 'left',
					sort: true
				},
				{
					title: '操作',
					width: 140,
					templet: '#serverTableBar',
					fixed: "right",
					align: "center"
				}
			]
		],
		done: function(res, curr, count) {}
	});

	//	修改删除
	table.on('tool(serverTable)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;

		if(layEvent === 'editserver') { //编辑
			$("#serverName").val(data.serverName);
			$("#serverUrl").val(data.serverUrl);
			$("#serverDis").val(data.serverDis);
			var id = data.id;

			JSShow.openShow(1, "修改服务信息", '440px', $('.addnewserverbox'), function(index, layero) {
				JSShow.confirmShow('是否修改内容并提交？', function() {
					var serverName = $("#serverName").val();
					var serverUrl = $("#serverUrl").val();
					var serverDis = $("#serverDis").val();
					if(serverName == "") {
						layer.msg("服务名称为空，请填写");
					} else if(serverUrl == "") {
						layer.msg("服务地址为空，请填写");
					} else {
						ServerUrl.ReviseServer(id, serverName, serverUrl, serverDis, function() {
							serverTable.reload();
						})
						layer.closeAll();
						layer.msg('修改成功！');
					}

				})

			})

		} else if(layEvent === 'delserver') { //删除

			JSShow.confirmShow('确定删除这条服务信息？', function(index) {
				ServerUrl.DeleteServer(data.id, function() {
					table.reload("serverTable", {
						page: {
							curr: 1 //重新从前一页开始
						}
					});
					layer.close(index);
				})
			})
		}
	});

})