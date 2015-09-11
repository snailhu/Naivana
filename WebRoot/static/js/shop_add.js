(function(){

$('#submit').click(function(){
			var $codeInERP=$('#codeInERP').val(),
				$name=$('#name').val(),
				$manager=$('#manager').val(),
				$postalCode=$('#postalCode').val(),
				$phoneNum=$('#phoneNum').val(),
				$faxNum=$('#faxNum').val(),
				$deliveryAddr=$('#deliveryAddr').val(),
				$username=$('#username').val(),
				$pwd=$('#pwd').val(),
				$repwd=$('#repwd').val(),
				$phone=$('#phone').val();
				$Email=$('#Email').val();
				var re=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ ;
				if(!re.exec($Email)){
					layer.msg("邮箱格式不正确!",{icon:7})
					return false;
				}
				if ($pwd != $repwd){
					layer.msg("两次输入密码不一致!",{icon:7})
					return false;
				};
				var re=/^1[3|5|7|8][0-9]{9}$/;
				 if(!re.exec($phoneNum)){
				 	layer.msg("手机号码格式不正确!",{icon:7})
	                return false;
            	}
				
				if( $name && $manager && $postalCode && $phoneNum && $faxNum && $pwd && $repwd && $phone && $username && $Email){
						var url='/Nirvana/backend/web/api/stores';
			    		var loading=layer.msg('数据提交中，请稍等...', {icon: 16,shade: [0.8, '#393D49']});

						 $.ajax({
				                type:'POST',
				                url:url,
				                datatype:"json",
				                data:{
				                	username:$username,
				                	bindNum:$phone,
				                	password:$pwd,
				                	pwdrepeat:$repwd,
				                	codeInERP:$codeInERP,
				                	manager:$manager,
				                	faxNum:$faxNum,
				                	name:$name,
				                	postalCode:$postalCode,	
				                	phoneNum:$phoneNum,
				                	deliveryAddr:$deliveryAddr,
				                	email:$Email
				                	
				                },
				                success:function(data){
				                	layer.close(loading);
				                	if(data.response=='success'){
										layer.msg("添加成功!",{icon:1})
				                	}
				                	else{
				                		layer.msg(data.data.text,{icon:2})
				                	}
				                },
				                error:function(data){
				                	layer.msg("内部错误,请重试!",{icon:2})
				                    return false;
				                }
				            });
					
				}
				else{
					layer.msg("请完成所有基础信息!",{icon:7})
				}
		});
	
})();		
		