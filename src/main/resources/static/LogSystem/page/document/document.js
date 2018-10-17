(function($) {
	// 当domReady的时候开始初始化
	$(function() {

		layui.use(['form', 'layer', 'table', 'element', 'upload', 'laydate', 'laytpl', 'jquery'], function() {
			var laydate = layui.laydate,
				form = layui.form,
				upload = layui.upload,
				table = layui.table,
				element = layui.element,
				$ = layui.jquery,
				layer = layui.layer;

			var fileList = table.render({
				elem: '#fileList',
				url: httpUrl + "documentrun/" + 'GetDocumentListServlet',
				cellMinWidth: 95,
				page: true,
				height: "full-125",
				limit: 15,
				limits: [5, 10, 15, 20, 25],
				loading: true,
				even: true,
				id: "fileList",
				cols: [
					[{
							type: 'numbers',
							title: 'ID',
							width: 70,
							align: "center"
						},
						{
							field: 'fileName',
							title: '文件名',
							align: 'left',
							sort: true
						},
						{
							field: 'fileSize',
							title: '文件大小',
							width: 130,
							align: 'center',
							sort: true
						},
						{
							field: 'datetime',
							title: '上传时间',
							width: 170,
							align: 'center',
							sort: true
						},
						{
							title: '操作',
							width: 140,
							templet: '#fileListBar',
							fixed: "right",
							align: "center"
						}
					]
				],
				done: function(res, curr, count) {

				}
			});

			table.on('tool(fileList)', function(obj) {
				var layEvent = obj.event,
					data = obj.data;

				if(layEvent === 'download') { //下载
					window.open(httpUrl + data.filePath);

				} else if(layEvent === 'del') { //删除
					JSShow.confirmShow('确定删除选中的文档？',function(index) {
						Document.DeleteDocument(data.id, function(){
							fileList.reload();
							layer.msg("删除成功，请重置上传队列后继续上传！");
						})
						layer.close(index);
					})
				}
			});
			//重置列表
			$(".clear_btn").on("click", function() {
				$(".searchVal").val("");
				fileList.reload();
			});
			//刷新页面
			$("#reload").on("click", function() {
				location.reload();
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
			//重新加载
			function reload() {
				if($(".searchVal").val() != '') {
					table.reload("fileList", {
						page: {
							curr: 1 //重新从第 1 页开始
						},
						where: {
							searchKey: $(".searchVal").val() //搜索的关键字
						}
					});
				} else {
					table.reload("fileList", {
						page: {
							curr: 1 //重新从第 1 页开始
						}
					});
				}
			}
/*========================================= 上传功能  =======================================*/
			var $wrap = $('#uploader'),

				// 图片容器
				$queue = $('<ul class="filelist"></ul>')
				.appendTo($wrap.find('#dndArea')),

				// 状态栏，包括进度和控制按钮
				$statusBar = $wrap.find('.statusBar'),

				// 文件总体选择信息。
				$info = $statusBar.find('.info'),

				// 上传按钮
				$upload = $wrap.find('.uploadBtn'),

				// 没选择文件之前的内容。
				$placeHolder = $wrap.find('.placeholder'),

				$progress = $statusBar.find('.progress').hide(),

				// 添加的文件数量
				fileCount = 0,

				// 添加的文件总大小
				fileSize = 0,

				// 优化retina, 在retina下这个值是2
				ratio = window.devicePixelRatio || 1,

				// 缩略图大小
				thumbnailWidth = 110 * ratio,
				thumbnailHeight = 110 * ratio,
				state = 'pedding',
				percentages = {},
				uploader;

			// 实例化
			uploader = WebUploader.create({
				pick: {
					id: '#filePicker',
					label: '点击选择文件'
				},
//				formData: {
//					folder: "documentCenter"
//				},
				dnd: '#dndArea',
				paste: '#uploader',
				swf: '../../js/webuploader/Uploader.swf',
				auto: true,
				server: httpUrl + "documentrun/" + 'DocumentUpServlet',
				method: 'POST',
				// 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
				disableGlobalDnd: true,
				fileNumLimit: 300,
				fileSizeLimit: 2000 * 1024 * 1024, // 200 M
				fileSingleSizeLimit: 500 * 1024 * 1024 // 50 M
			});
			// 添加“添加文件”的按钮，
			uploader.addButton({
				id: '#filePicker2',
				label: '继续添加'
			});
			uploader.on('ready', function() {
				window.uploader = uploader;
			});

			// 当有文件添加进来时执行，负责view的创建
			function addFile(file) {
				var $li = $('<li id="' + file.id + '">' +
						//                  '<p class="imgWrap"></p>'+
						'<p class="title">' + file.name + '</p>' +
						'<p class="sizeshow">' + WebUploader.formatSize(file.size) + '</p>' +
						'<p class="progress"><span></span></p>' +
						'</li>'),

					$btns = $('<div class="file-panel">' +
						'<span class="cancel">删除</span>' +
						'</div>').appendTo($li),

					$prgress = $li.find('p.progress span'),
					$wrap = $li.find('p.imgWrap'),
					$info = $('<p class="error"><i class="fa fa-minus-square"></i></p>'),

					showError = function(code) {
						$info.appendTo($li);
					};

				if(file.getStatus() === 'invalid') {
					showError(file.statusText);
				} else {
					percentages[file.id] = [file.size, 0];
					file.rotation = 0;
				}

				file.on('statuschange', function(cur, prev) {
					if(prev === 'progress') {
						$prgress.hide().width(0);
					} else if(prev === 'queued') {
						//                  $li.off( 'mouseenter mouseleave' );
						$btns.remove();
					}

					// 成功
					if(cur === 'error' || cur === 'invalid') {
						showError(file.statusText);
						percentages[file.id][1] = 1;
					} else if(cur === 'interrupt') {
						showError('interrupt');
					} else if(cur === 'queued') {
						percentages[file.id][1] = 0;
					} else if(cur === 'progress') {
						$info.remove();
						$prgress.css('display', 'block');
					} else if(cur === 'complete') {
						$li.append('<span class="success"><i class="fa fa-check-square-o"></i></span>');
					}

					$li.removeClass('state-' + prev).addClass('state-' + cur);
				});
				$btns.on('click', 'span', function() {
					var index = $(this).index(),
						deg;

					switch(index) {
						case 0:
							uploader.removeFile(file);
							return;

						case 1:
							file.rotation += 90;
							break;

						case 2:
							file.rotation -= 90;
							break;
					}

					if(supportTransition) {
						deg = 'rotate(' + file.rotation + 'deg)';
						$wrap.css({
							'-webkit-transform': deg,
							'-mos-transform': deg,
							'-o-transform': deg,
							'transform': deg
						});
					} else {
						$wrap.css('filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation=' + (~~((file.rotation / 90) % 4 + 4) % 4) + ')');
					}

				});

				$li.appendTo($queue);
			}

			// 负责view的销毁
			function removeFile(file) {
				var $li = $('#' + file.id);

				delete percentages[file.id];
				updateTotalProgress();
				$li.off().find('.file-panel').off().end().remove();
			}

			function updateTotalProgress() {
				var loaded = 0,
					total = 0,
					spans = $progress.children(),
					percent;

				$.each(percentages, function(k, v) {
					total += v[0];
					loaded += v[0] * v[1];
				});

				percent = total ? loaded / total : 0;

				spans.eq(0).text(Math.round(percent * 100) + '%');
				spans.eq(1).css('width', Math.round(percent * 100) + '%');
				updateStatus();
			}

			function updateStatus() {
				var text = '',
					stats;

				if(state === 'ready') {
					text = '选中' + fileCount + '个文件，共' +
						WebUploader.formatSize(fileSize) + '。';
				} else if(state === 'confirm') {
					stats = uploader.getStats();
					if(stats.uploadFailNum) {
						text = '已成功上传' + stats.successNum + '文件，' +
							stats.uploadFailNum + '文件上传失败，<a class="retry" href="#">重新上传</a>或<a class="ignore" href="#">忽略</a>'
					}

				} else {
					stats = uploader.getStats();
					text = '共' + fileCount + '个（' +
						WebUploader.formatSize(fileSize) +
						'），已上传' + stats.successNum + '个';

					if(stats.uploadFailNum) {
						text += '，失败' + stats.uploadFailNum + '个';
					}
				}

				$info.html(text);
			}

			function setState(val) {
				var file, stats;

				if(val === state) {
					return;
				}

				$upload.removeClass('state-' + state);
				$upload.addClass('state-' + val);
				state = val;

				switch(state) {
					case 'pedding':
						$placeHolder.removeClass('hasfile');
						$placeHolder.find("#filePicker").show();
						$queue.hide();
						$statusBar.addClass('element-invisible');
						uploader.refresh();
						break;

					case 'ready':
						$placeHolder.addClass('hasfile');
						$placeHolder.find("#filePicker").hide();
						$('#filePicker2').removeClass('element-invisible');
						$queue.show();
						$statusBar.removeClass('element-invisible');
						uploader.refresh();
						break;

					case 'uploading':
						$('#filePicker2').addClass('element-invisible');
						$progress.show();
						$upload.text('上传中');
						break;

					case 'paused':
						$progress.show();
						$upload.text('继续上传');
						break;

					case 'confirm':
						$progress.hide();
						fileList.reload();
						$('#filePicker2').removeClass('element-invisible');
						$upload.text('开始上传');
						stats = uploader.getStats();
						if(stats.successNum && !stats.uploadFailNum) {
							setState('finish');
							return;
						}
						break;
					case 'finish':

						stats = uploader.getStats();
						if(stats.successNum) {
						} else {
							// 没有成功的图片，重设
							state = 'done';
							location.reload();
						}
						break;
				}

				updateStatus();
			}

			uploader.onUploadProgress = function(file, percentage) {
				var $li = $('#' + file.id),
					$percent = $li.find('.progress span');

				$percent.css('width', percentage * 100 + '%');
				percentages[file.id][1] = percentage;
				updateTotalProgress();
			};
			
			uploader.onFileQueued = function(file) {
				fileCount++;
				fileSize += file.size;

				if(fileCount === 1) {
					$placeHolder.addClass('hasfile');
					$placeHolder.find("#filePicker").hide();
					$statusBar.show();
				}

				addFile(file);
				setState('ready');
				updateTotalProgress();
			};

			uploader.onFileDequeued = function(file) {
				fileCount--;
				fileSize -= file.size;

				if(!fileCount) {
					setState('pedding');
				}

				removeFile(file);
				updateTotalProgress();

			};
			
			uploader.on('uploadSuccess', function(file, res) {
				if(res.status == "-1"){
					$("#"+file.id).find(".title").append("<span style='color: red;'>文件已存在，请先删除再重新上传</span>")
				}
			});
			
			uploader.on('all', function(type) {
				var stats;
				switch(type) {
					case 'uploadFinished':
						setState('confirm');
						break;

					case 'startUpload':
						setState('uploading');
						break;

					case 'stopUpload':
						setState('paused');
						break;

				}
			});

			uploader.onError = function(code) {
				console.log(code)
				if(code == "F_EXCEED_SIZE") {
					layer.msg("文件超出限制大小（最大可传512M）");
				} else if(code == "F_DUPLICATE") {
					layer.msg("禁止上传重复文件");
				} else {
					layer.msg("上传失败请重试");
				}

			};

			$upload.on('click', function() {
				if($(this).hasClass('disabled')) {
					return false;
				}

				if(state === 'ready') {
					uploader.upload();
				} else if(state === 'paused') {
					uploader.upload();
				} else if(state === 'uploading') {
					uploader.stop();
				}
			});

			$info.on('click', '.retry', function() {
				uploader.retry();
			});

			$info.on('click', '.ignore', function() {
				location.reload();
			});

			$upload.addClass('state-' + state);
			updateTotalProgress();
		})
	});

})(jQuery);