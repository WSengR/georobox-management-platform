/*========================================= 配置左侧导航栏  =======================================*/
var NavOption = {
	/**
	 * 获取数据库导航栏；列表
	 * @param {int} isopen  导航栏开启状态“0”关闭，“1”开启
	 * @param {Function} succFun  成功回调
	 */
	GetNavList: function(isopen, succFun){
		var param = {
			isOpen: isopen
		};
		var urls = httpUrl + "navconfigrun/" + "GetMainMenuServlet";
		HttpGetAsyncAjax(urls, param, succFun);
	},
	/**
	 * 修改导航栏列表
	 * @param {int} id  对应导航列ID返回格式（1;2;3;）
	 * @param {int} isopen  该列开启状态“0”关闭，“1”开启
	 * @param {Function} succFun  成功回调
	 */
	ChangeNavList: function(id, isopen, succFun){
		var param = {
			id: id,
			isOpen: isopen
		};
		var urls = httpUrl + "navconfigrun/" + "ChangMenuStateServlet";
		HttpGetAsyncAjax(urls, param, succFun);
	},
	/**
	 * 新增导航栏列表
	 * @param {Object} title  导航栏列表名称
	 * @param {Object} icon  导航栏列表图标
	 * @param {Object} href  导航栏列表链接地址
	 * @param {Object} succFun  成功回调
	 */
	AddNavList: function(title, icon, href, succFun){
		var param = {
			title: title,
			icon: icon,
			href: href
		};
		var urls = httpUrl + "navconfigrun/" + "PostAddMenuServlet";
		HttpGetAsyncAjax(urls, param, succFun);
	},
	/**
	 * 删除导航栏列表
	 * @param {Object} id  删除导航栏列表ID
	 * @param {Object} succFun  成功回调
	 */
	DeleteNavList: function(id, succFun){
		var param = {
			id: id
		};
		var urls = httpUrl + "navconfigrun/" + "DeleteMenuServlet";
		HttpGetAsyncAjax(urls, param, succFun);
	}
}

/*========================================= 宙斯电视机  =======================================*/
var ZHOUSITV = {
	/**
	 * 获取ZSTV数据
	 * @param {date} dataTimes  获取数据日期
	 * @param {String} httpUrl  获取宙斯数据地址
	 * @param {Function} succFun  成功回调
	 */
	GetZHOUSI: function(dataTimes, httpUrl, succFun, errorFun) {
		var param = {
			dataTime: dataTimes
		};
		var urls = httpUrl + "/zeustvrun" + "/MainTVServlet";
		HttpGetAsyncAjaxErr(urls, param, succFun, errorFun);
	},
	/**
	 * 添加宙斯信息
	 * @param {String} ZSName  宙斯名称
	 * @param {String} ZSUrl  宙斯地址
	 * @param {Function} succFun  成功回调
	 */
	AddZSList: function(ZSName, ZSUrl, succFun){
		var param = {
			zsName: ZSName,
			zsServeurl: ZSUrl
		};
		var urls = httpUrl + "zeustvrun/" + "AddZSTvServlet";
		HttpGetAsyncAjax(urls, param, succFun);	
	},
	/**
	 * 获取宙斯列表
	 * @param {Function} succFun  成功回调
	 */
	GetZSList: function(succFun){
		var param = {};
		var urls = httpUrl + "zeustvrun/" + "GetZSTvListServlet";
		HttpGetAsyncAjax(urls, param, succFun);	
	},
	/**
	 * 测试宙斯连接
	 * @param {String} httpUrl  宙斯地址
	 * @param {Function} succFun 成功回调
	 * @param {Function} erroeFun  失败回调
	 */
	TestZSList: function(httpUrl, succFun, erroeFun){
		var param = {};
		var urls = httpUrl + "/zeustvrun" + "/ZSTestConnetServlet";
		HttpGetAsyncAjaxErr(urls, param, succFun, erroeFun);	
	},
	/**
	 * 移除宙斯地址
	 * @param {Object} id  移除宙斯ID
	 * @param {Object} succFun  成功回调
	 */
	RemoveZSList: function(id, succFun){
		var param = {
			id: id
		};
		var urls = httpUrl + "zeustvrun/" + "RemoveZSServlet";
		HttpGetAsyncAjax(urls, param, succFun);	
	}
	
}
/*========================================= 日志列表  =======================================*/
var LogList = {
	/**
	 * 删除日志列表
	 * @param {String} serverName  删除日志对应id
	 * @param {String} fileUrl  删除日志对应文件名
	 * @param {function} succFun  删除成功后回调
	 */
	DeleteLog: function(id, succFun) {
		var param = {
			id: id
		};
		var urls = httpUrl + "logmanagerun/" + "DeleteLogInfoServlet";
		HttpGetAsyncAjax(urls, param, succFun);
	}
}
/*========================================= 错误日志管理  =======================================*/
var ErrorData = {
	/**
	 * 错误日志列表渲染
	 */
	GetErrorData: function(startTime, endTime, succFun) {
		var param = {
			startTime: startTime,
			endTime: endTime
		};
		var urls = httpUrl + "errorlogrun/" + "GetPhoneMoreInfoServlet";
		HttpGetAsyncAjax(urls, param, succFun);
	},
	/**
	 * 错误日志逾期删除
	 */
	DeleteOver: function(succFun) {
		var param = {};
		var urls = httpUrl + "errorlogrun/" + "DeleteOverdueErrorLog";
		HttpGetAsyncAjax(urls, param, succFun);
	},
	/**
	 * 处理错误日志
	 * @param {Object} id  错误日志id
	 * @param {Object} completed  处理状态1  为已完成   0为未完成
	 * @param {Object} succFun  
	 */
	CompletedErr: function(id, isCompleted, succFun){
		var param = {
			id: id,
			isCompleted: isCompleted
		};
		var urls = httpUrl + "errorlogrun/" + "ChangErrorCompletedServlet";
		HttpGetAsyncAjax(urls, param, succFun);
	}
}
/*========================================= 用户信息反馈  =======================================*/
var UserBack = {
	/**
	 * 删除反馈
	 * @param {int} id  删除反馈内容Id
	 * @param {function} succFun  修改成功回调
	 */
	DeleteUserBack: function(id, succFun) {
		var param = {
			id: id
		}
		var urls = httpUrl + "userbackrun/" + "DeleteUserBackServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	}
}
/*========================================= 朋友圈系统管理  =======================================*/
var Friend = {
	/**
	 * 获取朋友圈列表+加载更多+搜索共用接口
	 * @param {String} userId （搜索用）对应用户ID
	 * @param {int} size  每次获取朋友圈条数（默认为10）
	 * @param {Date String} startime （搜索用）时间筛选开始时间
	 * @param {Date String} endtime  （搜索用）时间筛选结束时间
	 * @param {Date String} lastTime  （加载更多用）上次获取列表中最后一条数据时间
	 * @param {Function} succFun  获取成功回调
	 */
	GetCirleList: function(userId, size, startTime, endTime, lastItemTime, succFun) {
		var param = {
			userId: userId,
			size: size,
			startTime: startTime,
			endTime: endTime,
			lastItemTime: lastItemTime
		}
		var urls = httpUrl_friend + "GetFriendItemListForFilter";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 发布朋友圈
	 * @param {String} userid  发布人ID
	 * @param {int} type  发布类型（0/1）；0为无图片朋友圈，1为有图片朋友圈
	 * @param {String} content  发布文字内容
	 * @param {String} photoUrl  发布图片地址（地址一;地址二;......）
	 * @param {Function} succFun  发布成功回调
	 */
	SendCirleList: function(userId, type, content, photoUrl, succFun) {
		var param = {
			userId: userId,
			type: type,
			content: content,
			photoUrl: photoUrl
		}
		var urls = httpUrl_friend + "SendFriendsItem";
		HttpPostAjax(urls, param, succFun);
	},
	/**
	 * 修改朋友圈
	 * @param {int} id  修改朋友圈ID
	 * @param {int} type  修改类型（0/1）；0为无图片朋友圈，1为有图片朋友圈
	 * @param {String} content 修改文字内容
	 * @param {String} photoUrl  修改图片地址（地址一;地址二;......）
	 * @param {Function} succFun  修改成功回调
	 */
	ChangCirleList: function(id, type, content, photoUrl, succFun) {
		var param = {
			id: id,
			type: type,
			content: content,
			photoUrl: photoUrl
		}
		var urls = httpUrl_friend + "ChangItemServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 删除朋友圈
	 * @param {int} userId 删除用户ID
	 * @param {int} itemId  删除朋友圈的ID
	 * @param {Function} succFun  删除成功回调
	 */
	DeleteCirleList: function(userId, itemId, succFun) {
		var param = {
			userId: userId,
			itemId: itemId
		}
		var urls = httpUrl_friend + "SendDeleteItem";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 点赞
	 * @param {String} userId  点赞用户ID
	 * @param {int} itemId  被点赞朋友圈ID
	 * @param {Function} succFun  点赞成功回调
	 */
	SendCirleFavor: function(userId, itemId, succFun) {
		var param = {
			userId: userId,
			itemId: itemId
		}
		var urls = httpUrl_friend + "SendFavort";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 添加评论
	 * @param {String} userId  评论人ID
	 * @param {int} itemId  评论朋友圈ID
	 * @param {String} content  评论内容
	 * @param {int} toReplyId  评论回复人ID（直接评论朋友圈时为空）
	 * @param {Function} succFun  评论成功回调
	 */
	SendCirleComment: function(userId, itemId, content, toReplyId, succFun) {
		var param = {
			userId: userId,
			itemId: itemId,
			content: content,
			toReplyId: toReplyId
		}
		var urls = httpUrl_friend + "SendItemComment";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 删除评论
	 * @param {int} commentId 需要删除评论ID
	 * @param {Function} succFun 删除成功回调
	 */
	DeleteCirleComment: function(commentId, succFun) {
		var param = {
			commentId: commentId
		}
		var urls = httpUrl_friend + "SendDeleteComment";
		HttpPostAsyncAjax(urls, param, succFun);
	}
}

/*========================================= 即时通讯  =======================================*/
/*========================================= APK发布与更新  =======================================*/
var ApkUpload = {
	/**
	 * 获取最新版本apk内容
	 * @param {function} succFun 成功后回调
	 */
	GetApkUpload: function(succFun) {
		var param = {};
		var urls = httpUrl + "apkuprun/" + "GetApkVersionListServlet";
		HttpGetAsyncAjax(urls, param, succFun);
	},
	/**
	 * 删除APK
	 * @param {int} id  删除APK文件Id
	 * @param {String} apkUrl  删除APK文件
	 * @param {function} succFun  修改成功回调
	 */
	DeleteApkUpload: function(id, succFun) {
		var param = {
			id: id
		}
		var urls = httpUrl + "apkuprun/" + "DeleteApkServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 更新APK
	 * @param {String} apkName  更新APK名称
	 * @param {String} apkVersionName  更新APK版本名
	 * @param {String} apkVersionCode  更新APK版本号
	 * @param {String} apkDescirbe  更新APK详情
	 * @param {String} apkIconUrl  更新APK图标
	 * @param {String} apkUploadInfo  更新APK更新信息
	 * @param {String} apkUrl  更新APK地址
	 * @param {String} packageName  更新APK包名
	 * @param {String} apkSize  更新APK大小
	 * @param {String} isMustUpload  更新APK是否强制更新
	 * @param {String} apkDescirbeImage  更新APK图标列表（暂为空）
	 * @param {function} succFun  修改成功回调
	 */
	UploadApkUpload: function(apkName, apkVersionName, apkVersionCode, apkDescirbe, apkIconUrl, apkUploadInfo, apkUrl, packageName, apkSize, isMustUpload, apkDescirbeImage, succFun) {
		var param = {
			apkName: apkName,
			apkVersion: apkVersionName,
			apkVersionCode: apkVersionCode,
			apkDescribe: apkDescirbe,
			apkIconUrl: apkIconUrl,
			apkUploadInfo: apkUploadInfo,
			apkUrl: apkUrl,
			apkPackagename: packageName,
			apkSize: apkSize,
			mastupload: isMustUpload,
			apkDescribeImage: apkDescirbeImage
		}
		var urls = httpUrl + "apkuprun/" + "UploadApkInfoServlet";
		HttpPostAjax(urls, param, succFun);
	}
}
/*========================================= 波塞冬  =======================================*/
var Poseidon = {
	/**
	 * 插件列表上移
	 * @param {string} id  上移插件id
	 * @param {function} succFun  添加成功回调
	 */
	PsdUpPlug: function(plugId, succFun) {
		var param = {
			plugId: plugId
		}
		var urls = httpUrl + "plug/" + "PlugMoveUp";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 插件列表下移
	 * @param {string} id  下移插件id
	 * @param {function} succFun  添加成功回调
	 */
	PsdDownPlug: function(plugId, succFun) {
		var param = {
			plugId: plugId
		}
		var urls = httpUrl + "plug/" + "PlugMoveDown";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 获取插件详情
	 * @param {string} plugId  插件id
	 * @param {function} succFun  添加成功回调
	 */
	PsdDetailPlug: function(plugId, succFun) {
		var param = {
			plugId: plugId
		}
		var urls = httpUrl + "plug/" + "PlugDetail";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 插件上架
	 * @param {string} plugId  插件id
	 * @param {function} succFun  添加成功回调
	 */
	PsdOutorUpPlug: function(plugId, succFun) {
		var param = {
			plugId: plugId
		}
		var urls = httpUrl + "plug/" + "PlugOutorUp";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 插件列表删除
	 * @param {string} plugId  删除插件id
	 * @param {function} succFun  添加成功回调
	 */
	PsdDelPlug: function(plugId, succFun) {
		var param = {
			plugId: plugId
		}
		var urls = httpUrl + "plug/" + "PlugDelete";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 上传操作状态
	 * @param {Object} appName
	 * @param {Object} classes
	 * @param {Object} packageName
	 * @param {Object} modeInfo
	 * @param {Object} modeVersion
	 * @param {Object} userInfo
	 * @param {Object} operationInfo
	 * @param {Object} otherInfo
	 * @param {Object} succFun
	 */
	PsdRunState: function(appName, classString, appPackage, infoMode, modeVerison, infoUser, infoOperation, infoOther, succFun){
		var param = {
			appName: appName,
			classString: classString,
			appPackage: appPackage,
			infoMode: infoMode,
			modeVerison: modeVerison,
			infoUser: infoUser,
			infoOperation: infoOperation,
			infoOther: infoOther
		}
		var urls = httpUrl + "apprun/" + "PostRunStateInfoServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 审核列表审核
	 * @param {string} id  删除审核插件id
	 * @param {function} succFun  添加成功回调
	 */
	PsdVerifyVerifier: function(plugTempId, succFun) {
		var param = {
			plugTempId: plugTempId
		}
		var urls = httpUrl + "plug/" + "PlugAudit";
		HttpPostAsyncAjax(urls, param, succFun);
	},

	/**
	 * 审核列表删除
	 * @param {string} id  删除审核插件id
	 * @param {function} succFun  添加成功回调
	 */
	PsdDelVerifier: function(plugTempId, succFun) {
		var param = {
			plugTempId: plugTempId
		}
		var urls = httpUrl + "plug/" + "AuditPlugDelete";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 审核列表详情
	 * @param {string} id  删除审核插件id
	 * @param {function} succFun  添加成功回调
	 */
	PsdDetailVerifier: function(plugTempId, succFun) {
		var param = {
			plugTempId: plugTempId
		}
		var urls = httpUrl + "plug/" + "AuditPlugDetail";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 分类添加
	 * @param {string} name  添加后中文名
	 * @param {string} engName  添加后英文名
	 * @param {function} succFun  添加成功回调
	 */
	PsdAddCategory: function(categoryName, categoryDes, succFun) {
		var param = {
			categoryName: categoryName,
			categoryDes: categoryDes
		}
		var urls = httpUrl + "plug/" + "categorySave";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 分类修改
	 * @param {string} id  修改条目ID
	 * @param {string} name  修改后中文名
	 * @param {string} engName  修改后英文名
	 * @param {function} succFun  修改成功回调
	 */
	PsdChangCategory: function(categoryId, categoryName, categoryDes, succFun) {
		var param = {
			categoryId: categoryId,
			categoryName: categoryName,
			categoryDes: categoryDes
		}
		var urls = httpUrl + "plug/" + "categoryChange";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 分类删除
	 * @param {string} id  删除条目ID
	 * @param {function} succFun  删除成功回调
	 */
	PsdDetCategory: function(categoryId, succFun) {
		var param = {
			categoryId: categoryId,
		}
		var urls = httpUrl + "plug/" + 'categoryDelete';
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 权限添加
	 * @param {string} name  添加后中文名
	 * @param {string} engName  添加后英文名
	 * @param {function} succFun  添加成功回调
	 */
	PsdAddJurisdiction: function(permissionName, permissionDes, succFun) {
		var param = {
			permissionName: permissionName,
			permissionDes: permissionDes
		}
		var urls = httpUrl + "plug/" + "permissionSave";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 权限修改
	 * @param {string} id  修改条目ID
	 * @param {string} name  修改后中文名
	 * @param {string} engName  修改后英文名
	 * @param {function} succFun  修改成功回调
	 */
	PsdChangJurisdiction: function(permissionId, permissionName, permissionDes, succFun) {
		var param = {
			permissionId: permissionId,
			permissionName: permissionName,
			permissionDes: permissionDes
		}
		var urls = httpUrl + "plug/" + "permissionChange";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 权限删除
	 * @param {string} id  删除条目ID
	 * @param {function} succFun  删除成功回调
	 */
	PsdDetJurisdiction: function(permissionId, succFun) {
		var param = {
			permissionId: permissionId,
		}
		var urls = httpUrl + "plug/" + 'permissionDelete';
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 上传插件获取分类列表
	 * @param {function} succFun  修改成功回调
	 */
	PsdGetCategory: function(succFun) {
		var param = {
			limit: 1000
		}
		var urls = httpUrl + "plug/" + "categoryList";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 上传插件获取分类列表
	 * @param {function} succFun  修改成功回调
	 */
	PsdGetjurisdiction: function(succFun) {
		var param = {
			limit: 1000
		}
		var urls = httpUrl + "plug/" + "permissionList";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 上传APK插件
	 * @param {function} succFun  修改成功回调
	 */
	UploadApkPlug: function(fileName, plugVersioncode, PackageName, LauncherActivity, permission, category, needInstall, details, imageUrl, apkFileUrl, succFun) {
		var param = {
			plugType: 1,
			plugName: fileName,
			plugVersioncode: plugVersioncode,
			plugPackage: PackageName,
			plugLauncherActivity: LauncherActivity,
			plugPermission: permission,
			plugCategory: category,
			plugNeedinstall: needInstall,
			plugDetails: details,
			plugIcon: imageUrl,
			plugUrl: apkFileUrl
		}
		var urls = httpUrl + "plug/" + "PlugUpload";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 上传链接插件
	 * @param {function} succFun  修改成功回调
	 */
	UploadLinkPlug: function(fileName, plugVersioncode, permission, category, urls, details, imageUrl, succFun) {
		var param = {
			plugType: 2,
			plugName: fileName,
			plugVersioncode: plugVersioncode,
			plugPermission: permission,
			plugCategory: category,
			plugUrl: urls,
			plugDetails: details,
			plugIcon: imageUrl
		}
		var urls = httpUrl + "plug/" + "PlugUpload";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 上传文件插件
	 * @param {function} succFun  修改成功回调
	 */
	UploadZipPlug: function(fileName, plugVersioncode, permission, category, details, imageUrl, apkFileUrl, succFun) {
		var param = {
			plugType: 3,
			plugName: fileName,
			plugVersioncode: plugVersioncode,
			plugPermission: permission,
			plugCategory: category,
			plugLauncherActivity: "ZIP",
			plugDetails: details,
			plugIcon: imageUrl,
			plugUrl: apkFileUrl
		}
		var urls = httpUrl + "plug/" + "PlugUpload";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 修改APK插件
	 * @param {function} succFun  修改成功回调
	 */
	ChangeApkPlug: function(id, version, fileName, PackageName, LauncherActivity, permission, category, needInstall, details, imageUrl, apkFileUrl, succFun) {
		var param = {
			plugType: 1,
			plugId: id,
			plugVersioncode: version,
			plugName: fileName,
			plugPackage: PackageName,
			plugLauncherActivity: LauncherActivity,
			plugPermission: permission,
			plugCategory: category,
			plugNeedinstall: needInstall,
			URL: "",
			plugDetails: details,
			plugIcon: imageUrl,
			plugUrl: apkFileUrl
		}
		var urls = httpUrl + "plug/" + "PlugChange";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 修改链接插件
	 * @param {function} succFun  修改成功回调
	 */
	ChangeLinkPlug: function(id, version, fileName, permission, category, linkUrl, details, imageUrl, succFun) {
		var param = {
			plugType: 2,
			plugId: id,
			plugVersioncode: version,
			plugName: fileName,
			plugPermission: permission,
			plugCategory: category,
			plugUrl: linkUrl,
			plugDetails: details,
			plugIcon: imageUrl
		}
		var urls = httpUrl + "plug/" + "PlugChange";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 修改文件插件
	 * @param {function} succFun  修改成功回调
	 */
	ChangeZipPlug: function(id, version, fileName, permission, category, details, imageUrl, apkFileUrl, succFun) {
		var param = {
			plugType: 3,
			plugId: id,
			plugVersioncode: version,
			plugName: fileName,
			plugLauncherActivity: "ZIP",
			plugPermission: permission,
			plugCategory: category,
			plugDetails: details,
			plugIcon: imageUrl,
			plugUrl: apkFileUrl
		}
		var urls = httpUrl + "plug/" + "PlugChange";
		HttpPostAsyncAjax(urls, param, succFun);
	}	
}

/*========================================= 配置资源管理  =======================================*/
var Deploy = {
	/**
	 * 获取资源列表
	 * @param {int} page  获取资源页码
	 * @param {int} limit  获取资源条数
	 * @param {Function} succFun  获取成功回调
	 */
	GetDeployList: function(page, limit, succFun) {
		var param = {
			page: page,
			limit: limit
		}
		var urls = httpUrl + "configmanagerun/" + "GetConfigMangerServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 上传配置资源信息
	 * @param {String} configName 资源名称
	 * @param {String} configType 资源类型目前仅仅支持PIC/XML/APK
	 * @param {String} configUrl 配置资源存储地址
	 * @param {String} configPackageName 配置资源包名
	 * @param {String} nativePath 资源本地存储位置
	 * @param {Function} succFun 成功回调
	 */
	AddDeployList: function(configName, configType, configUrl, configPackageName, nativePath, succFun) {
		var param = {
			name: configName,
			type: configType,
			url: configUrl,
			packagename: configPackageName,
			nativepath: nativePath
		}
		var urls = httpUrl + "configmanagerun/" + "AddConfigMangerServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 修改配置资源
	 * @param {Object} id  修改配置资源ID
	 * @param {Object} configName  修改配置资源名称
	 * @param {Object} configType  修改配置资源类型目前仅仅支持PIC/XML/APK
	 * @param {Object} configUrl  修改配置资源存储地址
	 * @param {Object} configPackageName  修改配置资源包名
	 * @param {Object} nativePath  修改配置资源本地存储位置
	 * @param {Object} succFun  修改配置成功回调
	 */
	ChangeDeployList: function(id, configName, configType, configUrl, configPackageName, nativePath, succFun){
		var param = {
			id: id,
			name: configName,
			type: configType,
			url: configUrl,
			packagename: configPackageName,
			nativepath: nativePath
		}
		var urls = httpUrl + "configmanagerun/" + "ChangConfigFileServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 删除配置资源信息
	 * @param {int} id 删除配置资源对应id
	 * @param {String} configUrl 删除配置资源对应文件地址
	 * @param {Function} succFun 成功回调
	 */
	DeleteDeployList: function(id, succFun) {
		var param = {
			id: id
		}
		var urls = httpUrl + "configmanagerun/" + "DeleteConfigMangerServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 预览配置资源信息
	 * @param {Function} succFun 成功回调
	 */
	GetMainConfig: function(succFun) {
		var param = {}
		var urls = httpUrl + "configmanagerun/" + "GetMainConfigServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	}
}
/*========================================= 应用状态监测  =======================================*/
var AppState = {
	/**
	 * 应用状态列表渲染
	 * @param {Object} startTime 开始时间（默认为今天）
	 * @param {Object} endTime  结束时间（默认为今天）
	 * @param {Object} succFun  成功回调
	 */
	GetAppState: function(startTime, endTime, succFun) {
		var param = {
			startTime: startTime,
			endTime: endTime
		};
		var urls = httpUrl + "apprun/" + "GetAppStatisticsServlet";
		HttpGetAsyncAjax(urls, param, succFun);
	}
}

/*========================================= 服务端信息配置  =======================================*/
var ServerUrl = {
	/**
	 * 新增服务信息
	 * @param {String} serverName  服务名称
	 * @param {String} serverUrl  服务地址
	 * @param {String} serverDis  服务详情
	 * @param {function} succFun  新增成功后回调
	 */
	UplaodServer: function(serverName, serverUrl, serverDis, succFun) {
		var param = {
			serverName: serverName,
			serverUrl: serverUrl,
			serverDis: serverDis
		}
		var urls = httpUrl + "serverurlrun/" + "UplaodServerUrlServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 修改服务信息
	 * @param {String} serverName  服务对应id
	 * @param {String} serverName  服务名称
	 * @param {String} serverUrl  服务地址
	 * @param {String} serverDis  服务详情
	 * @param {function} succFun  修改成功后回调
	 */
	ReviseServer: function(id, serverName, serverUrl, serverDis, succFun) {
		var param = {
			id: id,
			serverName: serverName,
			serverUrl: serverUrl,
			serverDis: serverDis
		}
		var urls = httpUrl + "serverurlrun/" + "ReviseServerUrlServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 删除服务信息
	 * @param {String} serverName  服务对应id
	 * @param {function} succFun  删除成功后回调
	 */
	DeleteServer: function(id, succFun) {
		var param = {
			id: id
		}
		var urls = httpUrl + "serverurlrun/" + "DeleteServerUrlServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	}
}

/*========================================= 文档中心  =======================================*/
var Document = {
	/**
	 * 删除文档
	 * @param {int} id  文档对应ID
	 * @param {Function} succFun  成功回调
	 */
	DeleteDocument: function(id, succFun){
		var param = {
			id: id
		}
		var urls = httpUrl + "documentrun/" + "DeleteDocumentServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	}
}
/*========================================= 评分管理  =======================================*/
var Grade = {
	/**
	 * 删除用户评分
	 * @param {int} id  用户评分ID
	 * @param {String} fileUrl  用户评分上传图片
	 * @param {Function} succFun  删除成功回调
	 */
	DeleteGradeList: function(id, fileUrl, succFun){
		var param = {
			id: id
		}
		var urls = httpUrl + "scorerun/" + "DeleteScoreServer";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 获取标签列表
	 * @param {Function} succFun  获取成功回调
	 */
	GetTagList: function(succFun){
		var param = {}
		var urls = httpUrl + "scorerun/" + "GetTagListServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 删除标签
	 * @param {int} id  删除标签ID
	 * @param {Function} succFun  删除成功回调
	 */
	DeleteTagList: function(id, succFun){
		var param = {
			tag: id
		}
		var urls = httpUrl + "scorerun/" + "DeleteTagServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 添加标签
	 * @param {int} tag  添加标签内容
	 * @param {Function} succFun  添加成功回调
	 */
	AddTagList: function(tag, succFun){
		var param = {
			tag: tag
		}
		var urls = httpUrl + "scorerun/" + "AddTagServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	}
}

/*========================================= 用户授权管理  =======================================*/
var Accredit = {
	/**
	 * 取消授权
	 * @param {int} id  授权ID
	 * @param {Function} succFun
	 */
	RemoveToken: function(id, succFun){
		var param = {
			id: id
		}
		var urls = httpUrl + "DeleteTokenServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	}
}

/*========================================= 即时通讯  =======================================*/
var Chart = {
	/**
	 * 获取组织结构列表
	 * @param {Object} succFun
	 */
	GetGroupList: function(succFun){
		var param = {};
		var urls = xhrUrl + "GetChatRoomsServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 获取聊天信息(参数齐全)
	 * @param {int} page 页码
	 * @param {int} limit 每页条数
	 * @param {int} groupType 用户类型，1个人，2聊天组，3组织机构，4系统
	 * @param {int} msgType  信息类型，1text，2image，3voice，4video，5file，6location
	 * @param {String} groupId 聊天组ID
	 * @param {String} fromUserId  发送用户
	 * @param {String} toUserId  收取用户
	 * @param {String} startTime  时间搜索 开始时间
	 * @param {String} endTime  时间搜索 结束时间
	 * @param {String} key  搜索关键字
	 * @param {Function} succFun  成功回调
	 */
	GetMsgList: function(page, limit, groupType, msgType, groupId, fromUserId, toUserId, startTime, endTime, key, succFun){
		var param = {
			page: page,
			limit: limit,
			groupType: groupType,
			msgType: msgType,
			groupId: groupId,
			fromUserId: fromUserId,
			toUserId: toUserId,
			startTime: startTime,
			endTime: endTime,
			key: key
		}
		var urls = xhrUrl + "GetImAllMassageListServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 获取用户聊天内容
	 * @param {Object} page
	 * @param {Object} limit
	 * @param {Object} msgType
	 * @param {Object} fromUserId
	 * @param {Object} toUserId
	 * @param {Object} startTime
	 * @param {Object} endTime
	 * @param {Object} key
	 * @param {Object} succFun
	 */
	GetUserMsgList: function(page, limit, msgType, fromUserId, toUserId, startTime, endTime, key, succFun){
		var param = {
			page: page,
			limit: limit,
			msgType: msgType,
			fromUserId: fromUserId,
			toUserId: toUserId,
			startTime: startTime,
			endTime: endTime,
			key: key
		}
		var urls = xhrUrl + "GetUserMassageServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 获取群组聊天内容
	 * @param {Object} page
	 * @param {Object} limit
	 * @param {Object} msgType
	 * @param {Object} groupId
	 * @param {Object} startTime
	 * @param {Object} endTime
	 * @param {Object} key
	 * @param {Object} succFun
	 */
	GetGroupMsgList: function(page, limit, msgType, groupId, startTime, endTime, key, succFun){
		var param = {
			page: page,
			limit: limit,
			groupType: 2,
			msgType: msgType,
			groupId: groupId,
			startTime: startTime,
			endTime: endTime,
			key: key
		}
		var urls = xhrUrl + "GetGroupMassageServlet";
		HttpPostAsyncAjax(urls, param, succFun);
	},
	/**
	 * 同步用户 
	 * @param {string} accessToken  传参
	 * @param {Function} succFun  成功回调
	 */
	SyncUser: function(accessToken, succFun, errFun){
		var param = {
			accessToken: accessToken
		}
		var urls = xhrUrl + "SyncAllUserListServlet";
		HttpNoTimeOutBaseAjax(urls, "GET", param, true, succFun, errFun);
	}
	
}
