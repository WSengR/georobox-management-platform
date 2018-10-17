/**
 * ajax方法
 * @param urls {string}请求地址
 * @param ways {string}post/get方式
 * @param param {object}上传参数
 * @param async {Boolean}同步异步选择
 * @param succFun(){function}成功方法
 */

var dataType = "JSON";
var httpTimeOut = 30000;

/**
 * 同步POST请求
 * @param {Object} urls
 * @param {Object} param
 * @param {Object} succFun
 */
function HttpPostAjax(urls, param, succFun) {
	HttpBaseAjax(urls, "POST", param, false, succFun, function erroeFun() {});
}
/**
 * 异步POST请求
 * @param {Object} urls
 * @param {Object} param
 * @param {Object} async
 * @param {Object} succFun
 */
function HttpPostAsyncAjax(urls, param, succFun) {
	HttpBaseAjax(urls, "POST", param, true, succFun, function erroeFun() {});
}
/**
 * 同步GET请求
 * @param {Object} urls
 * @param {Object} param
 * @param {Object} succFun
 */
function HttpGetAjax(urls, param, succFun) {
	HttpBaseAjax(urls, "GET", param, false, succFun, function erroeFun() {});
}
/**
 * 异步GET请求(包含错误回调)
 * @param {Object} urls
 * @param {Object} param
 * @param {Object} succFun
 */
function HttpGetAsyncAjaxErr(urls, param, succFun, erroeFun) {
	HttpBaseAjax(urls, "GET", param, true, succFun, erroeFun);
}
/**
 * 异步GET请求
 * @param {Object} urls
 * @param {Object} param
 * @param {Object} succFun
 */
function HttpGetAsyncAjax(urls, param, succFun) {
	HttpBaseAjax(urls, "GET", param, true, succFun, function erroeFun() {});
}
/**
 * 底层请求 notime
 * @param {Object} urls
 * @param {Object} ways
 * @param {Object} param
 * @param {Object} async
 * @param {Object} succFun
 * @param {Object} err
 */
function HttpNoTimeOutBaseAjax(urls, ways, param, async, succFun, erroeFun) {
	$.ajax({
		url: urls,
		type: ways,
		data: param,
		dataType: dataType,
		async: async,
		timeout: 0,
		beforeSend:function(){
			JSShow.showLoad();
		},
		success: function(data) {
			succFun(data);
			JSShow.closeLoad();
		},
		error: function(request) {
			JSShow.closeLoad();
			var colse= true;	
			if(erroeFun){
				colse = erroeFun();
			}
			if(colse){
				JSShow.showMsg("请求失败请重试！");
			}
			
		}
	})
}
/**
 * 底层请求
 * @param {Object} urls
 * @param {Object} ways
 * @param {Object} param
 * @param {Object} async
 * @param {Object} succFun
 * @param {Object} err
 */
function HttpBaseAjax(urls, ways, param, async, succFun, erroeFun) {
	$.ajax({
		url: urls,
		type: ways,
		data: param,
		dataType: dataType,
		async: async,
		timeout: httpTimeOut,
		beforeSend:function(){
			JSShow.showLoad();
		},
		success: function(data) {
			succFun(data);
			JSShow.closeLoad();
		},
		error: function(request) {
			JSShow.closeLoad();
			var colse= true;	
			if(erroeFun){
				colse = erroeFun();
			}
			if(colse){
				JSShow.showMsg("请求失败请重试！");
			}
			
		}
	})
}