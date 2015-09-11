(function(){
$("#submit").click(function(){
			$pwd=$('#pwd').val();
			$rpwd=$('#rpwd').val();
			$username=$('#username').val();
			$phone=$('#phone').val();
			$isClosed=$('#isClosed').val();
			$closeReason=$('#closeReason').val();
			if($pwd != $rpwd){
				layer.msg("两次收入密码不一致",{icon:7})
				return false;
			}
			re=/^1[3|5|7|8][0-9]{9}$/;
            if(!re.exec($phone)){
                layer.msg("手机号码格式不正确",{icon:7})
                $('#phone').val('');
                return false;
               
            }
            if ($username && $phone){
            	if($isClosed == "true" && $closeReason==''){
						layer.msg("关闭原因不能为空",{icon:7})
						
					}
				else{	
					var loading=layer.msg('数据提交中，请稍等...', {icon: 16,shade: [0.8, '#393D49']});
					$.ajax({
		                type:'PUT',
		                url:url,
		                data:{
		                    username:$username,
		                    phoneNum:$phone,
		                    password:$pwd,
		                    pwdrepeat:$rpwd,
		                    isClose:$isClosed,
		                    closeReason:$closeReason	
		                },
		                success:function(data){
		                	layer.close(loading)
		                	if(data.response=='success'){
		                    	layer.msg("修改成功",{icon:1})
		                    }
		                    else{
		                    	layer.msg(data.data.text,{icon:2})
		                    }
		                },
		                error:function(data){
		                    
		                    layer.msg("内部错误，请重试",{icon:2})
		                }
		            });
	            }
            }
            else{

            	layer.msg("用户名和手机号码不能为空！",{icon:7})
            }
		});

$("#cancel").click(function(){
	window.history.go(-1);
})
})();		