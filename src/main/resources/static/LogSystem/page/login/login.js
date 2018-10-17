layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    $(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
            time:5000
        });
    })

    //登录按钮
    form.on("submit(login)",function(data){
    	var _self = $(this);
        $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
		 console.log("-------"+$("#userName").val());
         console.log("-------"+$("#password").val());
         if($("#userName").val()=="admin"&&$("#password").val()=="123456"){
         	   setTimeout(function(){
		        window.location.href = "../../index.html";
       		 },1000);
         }else{

         	setTimeout(function(){
		        _self.text("登录").attr("disabled",false).removeClass("layui-disabled");
		   	    layer.msg("请输入正确的账号密码",{
            		time:3000
         		});
       		 },1000);
         	
         }

        return false;
    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})