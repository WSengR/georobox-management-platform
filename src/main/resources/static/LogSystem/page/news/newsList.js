layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function() {
	var form = layui.form,
		layer = layui.layer,
		$ = layui.jquery,
		laydate = layui.laydate,
		laytpl = layui.laytpl,
		table = layui.table;
	var msg = layer.load(0, {
		shade: false,
		time: 5000
	});
	var newPage = "";
	var pagelinenum = 0;
	//新闻列表
	var tableIns = table.render({
		elem: '#newsList',
		url: httpUrl + "logmanagerun/" + 'GetLogInfoServlet',
		cellMinWidth: 95,
		page: true,
		height: "full-125",
		limit: 15,
		limits: [5, 10, 15, 20, 25],
		loading: true,
		even: true,
		id: "newsListTable",
		cols: [
				[{
					type: "checkbox",
					fixed: "left",
					width: 50
				},
				{
					type: 'numbers',
					title: 'ID',
					width: 70,
				},
				{
					field: 'logText',
					title: 'Log描述',
					align: "center",
					width: 350
				},
				{
					field: 'fileName',
					title: '文件名',
					align: 'center',
					sort: true
				},
				{
					field: 'fileUrl',
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
					title: '操作',
					width: 140,
					templet: '#newsListBar',
					fixed: "right",
					align: "center"
				}
				]
			],
		done: function(res, curr, count) {
			pagelinenum = res.data.length;
			newPage = curr;
			layer.close(msg);
			var data = res.data;
			$('.layui-table-body tr').each(function() {
				var dataindex = $(this).attr('data-index');
				var idx = 0;
				for(var item in data) {
					if(dataindex == idx) {
						$(this).dblclick(function() {
							//双击操作，data[item]
							window.open(httpUrl + data[item].fileUrl);
						});
						break;
					}
					idx++;
				};
			});
		}
	});

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

	form.on("submit(login)", function(data) {
		reload();
		return false;
	});

	//批量删除
	$(".delAll_btn").click(function() {
		var checkStatus = table.checkStatus('newsListTable'),
			data = checkStatus.data,
			detFileUrl = "",
			detId = "";
			var numremove = data.length;
		if(data.length > 0) {
			for(var i in data) {
				detId += (data[i].id) + ";";
			}
			JSShow.confirmShow('确定删除选中的日志和文件？',function(index) {
				LogList.DeleteLog(detId, function(){
					reloadremove(numremove);
				})
				layer.close(index);
			})
		} else {
			layer.msg("请选择需要删除的日志");
		}
	})
	table.on('tool(newsList)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;

		if(layEvent === 'download') { //编辑
			window.open(httpUrl + data.fileUrl);

		} else if(layEvent === 'del') { //删除
			
			JSShow.confirmShow('确定删除这个日志和文件？',function(index) {
				LogList.DeleteLog(data.id, function(){
					reloadremove(1);
				})
				layer.close(index);
			})
		}
	});

	//重新加载
	function reload() {
		if($(".searchVal").val() != '') {
			table.reload("newsListTable", {
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where: {
					key: $(".searchVal").val() //搜索的关键字
				}
			});
		} else {
			table.reload("newsListTable", {
				page: {
					curr: 1 //重新从第 1 页开始
				}
			});
		}
	}
	
	//重新加载
	function reloadremove(num) {
		if($(".searchVal").val() != '') {
			table.reload("newsListTable", {
				page: {
					curr: 1 //重新从第 1 页开始
				},
				where: {
					key: $(".searchVal").val() //搜索的关键字
				}
			});
		} else {
			if(pagelinenum <= num) {
				table.reload("newsListTable", {
					page: {
						curr: newPage - 1 //重新从前一页开始
					}
				});
			} else {
				table.reload("newsListTable", {
					page: {
						curr: newPage //重新从当前 页开始
					}
				});
			}

		}
	}
	

})