//本地请求地址
var httpUrl = "http://localhost:8080/georobox-wab/";
//var httpUrl = "http://192.168.100.122:8083/georobox-wab/";

//var xhrUrl = "http://demo-dx.geostar.com.cn:8899/RoboxZHOUSI/"
//var httpUrl = "http://192.168.36.112:8080/RoboxZHOUSI/";
//var httpUrl_friend = "http://192.168.36.112:8080/friend_circle/";

//线上请求地址
//var httpUrl = "http://demo-dx.geostar.com.cn:8899/ZSguangzhouphone/";//列表接口地址
var httpUrl_friend = httpUrl;
//var httpUrl_friend = "http://demo-dx.geostar.com.cn:8899/friend_circle/";//朋友圈接口地址

xhrUrl = httpUrl;
var httpUrl_friend = httpUrl;


var HttpBaseAjax = "LogSystem/js/own/HttpBaseAjax.js";
var HttpUtls = "LogSystem/js/own/HttpUtls.js";
var JSShow = "LogSystem/js/own/JSShow.js";


/**
 * 关联网络工具库
 * 绝对路径   httpUrl + HttpBaseAjax
 */
document.write('<script type="text/javascript" src="' + httpUrl + JSShow + '" ></script>');
document.write('<script type="text/javascript" src="' + httpUrl + HttpBaseAjax + '" ></script>');
document.write('<script type="text/javascript" src="' + httpUrl + HttpUtls + '" ></script>');

