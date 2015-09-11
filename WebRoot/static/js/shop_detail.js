(function(){
$('#submit').click(function(){
			var $codeInERP=$('#codeInERP').val(),
				$name=$('#name').val(),
				$manager=$('#manager').val(),
				$postalCode=$('#postalCode').val(),
				$phoneNum=$('#phoneNum').val(),
				$faxNum=$('#faxNum').val(),
				$deliveryAddr=$('#deliveryAddr').val(),
				$isClosed=$('#isClosed').val(),
				$closeReason=$('#closeReason').val();
				if(  $name && $manager && $postalCode && $phoneNum && $faxNum && $deliveryAddr && $isClosed){
					if($isClosed == "true" && $closeReason==''){
						layer.msg("关闭原因不能为空!",{icon:7})
					}
					else{
						
						var data={};
						data.codeInERP=$codeInERP;
						data.name=$name;
						data.manager=$manager;
						data.postalCode=$postalCode;
						data.phoneNum=$phoneNum;
						data.faxNum=$faxNum;
						data.deliveryAddr=$deliveryAddr;
						var url='/Nirvana/backend/web/api/stores/{id}/info/edit';
						url=url.replace('{id}',store_id);
			    		var loading=layer.msg('数据提交中，请稍等...', {icon: 16});
						 $.ajax({
				                type:'PUT',
				                url:url,
				                datatype:"json",
				                data:{
				                	data:JSON.stringify(data),
				                	isClose:$isClosed,
				                	closeReason:$closeReason 
				                },
				                success:function(data){
				                	layer.close(loading);
				                	if(data.response=='success'){
										layer.msg("修改成功!",{icon:1})

				                	}
				                	else{
										layer.msg("data.data.text",{icon:2})

				                	}
				                },
				                error:function(data){
									layer.msg("内部错误,请重试!",{icon:7})
				                    return false;
				                }
				            });
					}
				}
				else{
					layer.msg("请完成所有基础信息!",{icon:7})
				}
		})
		
		
		$('#save').click(function(){
			var $username=$('#username').val(),
				$pwd=$('#pwd').val(),
				$repwd=$('#repwd').val(),
				$phone=$('#phone').val();
			if($username && $phone){
				if($pwd == $repwd){
					var url='/Nirvana/backend/web/api/stores/{id}/edit';
					url=url.replace('{id}',store_id);
					$.ajax({
				                type:'PUT',
				                url:url,
				                datatype:"json",
				                data:{
				                	phoneNum:$phone,
				                	username:$username,
				                	password:$pwd,
				                	pwdrepeat:$repwd
				                },
				                success:function(data){
				                	if(data.response=='success'){
				                		layer.msg("修改成功!",{icon:1});
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
					layer.msg("两次输入的密码不一致!",{icon:7})
				}
			}
			else{
				layer.msg("用户名和手机号不能为空!",{icon:7})
			}
				
		});
})();		