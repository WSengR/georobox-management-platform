var JSShow = {
	msgVar: null,
	loadVar: null,
	/**
	 * 打开msg弹框
	 * @param {String} 弹框显示内容	 
	 */
	showMsg: function(msg) {
		msgVar = layer.msg(msg);
	},

	/**
	 * 关闭msg弹框
	 * @param {String} 弹框显示内容	 
	 */
	closeMsg: function(msg) {
		if(msgVar != null) {
			layer.close(msgVar);
		}
	},
	showLoad: function() {
		loadVar = layer.load(0, {
			shade: false,
			time: 0
		})
	},

	/**
	 * 关闭loading弹框
	 */
	closeLoad: function() {
		if(loadVar != null) {
			layer.close(loadVar);
		}
	},

	/**
	 * 询问弹框显示
	 * @param {String} title 显示询问内容
	 * @param {function} succFun 点击确认回调
	 * @param {function} closeFun 点击取消回调
	 */
	confirmShow: function(title, succFun, closeFun) {
		if(closeFun == null) {
			closeFun = function() {
				layer.close();
			}
		}
		layer.confirm(title, {
			btn: ['确定', '取消'] //按钮
		}, succFun, closeFun);
	},

	/**
	 * 
	 * @param {int} type 选择类型：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
	 * @param {String} title title显示
	 * @param {Array} btn 底部显示按钮
	 * @param {String/Array} area  在默认状态下，宽高都自适应的，但当你只想定义宽度时，你可以area: '500px'，高度仍然是自适应的。当你宽高都要定义时，你可以area: ['500px', '300px']
	 * @param {Object} content 不仅可以传入普通的html内容，还可以指定DOM，更可以随着type的不同而不同
	 * @param {function} btnFun1 点击确认回调
	 * @param {function} btnFun2 点击取消回调
	 * @param {int} closeBtn 右上角关闭按钮：0不显示，1显示
	 * @param {function} cancelFun 点击右上角关闭回调
	 */
	openBaseShow: function(type, title, btn, area, content, successFun, btnFun1, btnFun2, closeBtn, cancelFun) {
		layer.open({
			type: type,
			title: title,
			area: area,
			content: content, //这里content是一个普通的String
			btn: btn,
			btnAlign: 'r',
			success: successFun,
			btn1: btnFun1,
			btn2: btnFun2,
			closeBtn: closeBtn,
			cancel: cancelFun
		});
	},
	/**
	 * 
	 * @param {int} type 选择类型：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
	 * @param {String} title title显示
	 * @param {String/Array} area  在默认状态下，宽高都自适应的，但当你只想定义宽度时，你可以area: '500px'，高度仍然是自适应的。当你宽高都要定义时，你可以area: ['500px', '300px']
	 * @param {Object} content 不仅可以传入普通的html内容，还可以指定DOM，更可以随着type的不同而不同
	 * @param {function} btnFun1 点击确认回调
	 * @param {function} btnFun2 点击取消回调
	 * @param {function} cancelFun 点击右上角关闭回调
	 */
	openShow: function(type, title, area, content, btnFun1, btnFun2, cancelFun) {
		var btn = ['确定', '取消'];
		if(btnFun1 == null && btnFun2 == null) {
			btn = "";
		}
		if(btnFun2 == null) {
			btnFun2 = function() {
				layer.close();
			}
		}
		var closeBtn = 1;
		this.openBaseShow(type, title, btn, area, content, null, btnFun1, btnFun2, closeBtn, cancelFun);
	}
}