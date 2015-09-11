var phoneNum=""
	$("#unbind").click(function(){
		var page=layer.open({
                type: 1,   //0-4的选择,（1代表page层）
                area: ['500px', '300px'],
                shade: [0.5,'#000'],  //不显示遮罩
                border: [0], //不显示边框

                title: [
                    '解绑手机',
                    //自定义标题风格，如果不需要，直接title: '标题' 即可
                    'border-bottom;1px solid #d3d3d3;font-size:18px '
                ],
                bgcolor: '#fff', //设置层背景色
                content: '<div style="padding:50px 20px 10px;color:#909090;width: 460px;overflow-y: auto;">' +
                    '<div class=edit-item><span class="edit-title">绑定手机号:</span><span>'+$("#phone").html()+'</span></div>'+
                    '<div class=edit-item><span class="edit-title" >验证码:</span><input type="text" id="captcha"><input type=button value="获取验证码" id="sendcaptcha" class="button sure"></div>'+'</div>'+
                    '<div class="error-line" style="text-align:center;color:red"></div>'
                ,
                btns:2,
                btn:['确定','取消'],
                yes:function(){
                	if(unbind()){
                		$("#phone").html('');
                		$("#unbind").addClass("standby");
                		$("#bind").removeClass("standby");
                		layer.close(page);
                		
                	}
                },
                shift: 'top' //从上动画弹出
            });
            $('#sendcaptcha').bind("click",getcaptcha);
       })
       
       	
       function getcaptcha(){
	              $.ajax({
	                type:'GET',
	                url:'/Nirvana/backend/web/api/account/getCaptcha',
	                dataType: "json",
	                success:function(data){
	                	if (data.response=="success"){
	                		phoneNum=phone;
	                		$(".error-line").html('');
	               			$('#sendcaptcha').unbind();
	                		  var sec=60
                            function seconed(){
                                sec--;
                                if(sec>=0){
                                     $("#sendcaptcha").val('正在发送('+sec+')');
                                }
                                else{
                                    clearInterval(st)
                                   $("#sendcaptcha").val('获取验证码');
                                   $('#sendcaptcha').bind('click',getcaptcha);
                                }
                                
                            }
                 			var st=setInterval(seconed,1000);
                            
	               		}
		               	else{
		               		$(".error-line").html(data.data.text);
		               		return false;
		                }
	                },
	               error:function(data){
	               		$(".error-line").html("请重试");
	                    return false;
	                }
	            })
	            
	        }
	        
	    function unbind(){
	    	if(!$('#captcha').val()){
	    		$(".error-line").html("请填写验证码");
	    		return false;
	    	}
	    	$(".error-line").html("数据提交中,请稍等...");
	    	$.ajax({
	                type:'PUT',
	                url:'/Nirvana/backend/web/api/account/unbindNumber',
	                dataType: "json",
	                data:{
	                	captcha:$('#captcha').val()
	                },
	                success:function(data){
	                	if (data.response=="success"){
                            return true;
	               		}
		               	else{
		               		$(".error-line").html(data.data.text);
		               		return false;
		                }
	                },
	               error:function(data){
	               		$(".error-line").html("请重试");
	                    return false;
	                }
	            })
	            return true;	
	    }
	    
	    
	    $("#bind").click(function(){
	    	var page=layer.open({
                type: 1,   //0-4的选择,（1代表page层）
                area: ['500px', '280px'],
                shade: [0.5,'#000'],  //不显示遮罩
                border: [0], //不显示边框
                title: [
                    '绑定手机',
                    //自定义标题风格，如果不需要，直接title: '标题' 即可
                    'border-bottom;1px solid #d3d3d3;font-size:18px '
                ],
                bgcolor: '#fff', //设置层背景色
                content: '<div style="padding:50px 20px 10px;color:#909090;width: 460px;overflow-y: auto;">' +
                    '<div class=edit-item><span class="edit-title">绑定手机号:</span><input type="text" id="phoneNum"></div>'+
                    '<div class=edit-item><span class="edit-title" >验证码:</span><input type="text" id="captcha"><input type=button value="获取验证码" id="sendcaptcha" class="button sure"></div>'+'</div>'+
                    '<div class="error-line" style="text-align:center;color:red"></div>'
                ,
                btns:2,
                btn:['确定','取消'],
                yes:function(){
                	if(bind()){
                		var phoneNum=$('#phoneNum').val();
                		$("#phone").html(phoneNum.substring(0,3)+"****"+phoneNum.substring(7,11));
                		$("#bind").addClass("standby");
                		$("#unbind").removeClass("standby");
                		layer.close(page);
                		
                	}
                },
                shift: 'top' //从上动画弹出
            });
            $('#sendcaptcha').bind("click",getbindcaptcha);
	    })    
	    
	    
	    function getbindcaptcha(){
	    		var re=/^1[3|5|7|8][0-9]{9}$/;
                var phone=$('#phoneNum').val();
                if(!re.exec(phone)){
                   $(".error-line").html('手机号码格式不正确');
                    return false;
                }
                $(".error-line").html("数据提交中,请稍等...")
                $.ajax({
	                type:'POST',
	                url:'/Nirvana/backend/web/api/account/verifyNumber',
	                dataType: "json",
	                data:{
	                	number:phone
	                },
	                success:function(data){
	                	if (data.response=="success"){
	               			$(".error-line").html('');
	               			$('#sendcaptcha').unbind();
	                		  var sec=60
                            function seconed(){
                                sec--;
                                if(sec>=0){
                                     $("#sendcaptcha").val('正在发送('+sec+')');
                                }
                                else{
                                    clearInterval(st)
                                   $("#sendcaptcha").val('获取验证码');
                                   $('#sendcaptcha').bind('click',getbindcaptcha);
                                }
                                
                            }
                 			var st=setInterval(seconed,1000);
                            
	               		}
		               	else{
		               		$(".error-line").html(data.data.text)
		               		return false;
		                }
	                },
	               error:function(data){
	               		$(".error-line").html('请重试！')
	                    return false;
	                }
	            })
	    }
	    
	    
	    function bind(){
	    	var re=/^1[3|5|7|8][0-9]{9}$/;
            var phone=$('#phoneNum').val();
            if(!re.exec(phone)){
               $(".error-line").html('手机号码格式不正确');
                return false;
         	}
	    	if(!$('#captcha').val()){
	    		$(".error-line").html("请填写验证码");
	    		return false;
	    	}
	    	$.ajax({
	                type:'PATCH',
	                url:'/Nirvana/backend/web/api/account/bindNumber',
	                dataType: "json",
	                data:{
	                	captcha:$('#captcha').val()
	                },
	                success:function(data){
	                	if (data.response=="success"){
                            return true;
	               		}
		               	else{
		               		$(".error-line").html(data.data.text);
		               		return false;
		                }	
	                },
	               error:function(data){
	               		$(".error-line").html("请重试");
	                    return false;
	                }
	            })
	         return true;	
	    }
	    
	    
	    function getchangecaptcha(){
	    	  $.ajax({
	                type:'GET',
	                url:'/Nirvana/backend/web/api/account/getCaptcha',
	                dataType: "json",
	                success:function(data){
	                	if (data.response=="success"){
	                		phoneNum=phone;
	                		$(".error-line").html('');
	               			$('#changecaptcha').unbind();
	                		  var sec=60
                            function seconed(){
                                sec--;
                                if(sec>=0){
                                     $("#changecaptcha").val('正在发送('+sec+')');
                                }
                                else{
                                    clearInterval(st)
                                   $("#changecaptcha").val('获取验证码');
                                   $('#changecaptcha').bind('click',getchangecaptcha);
                                }
                                
                            }
                 			var st=setInterval(seconed,1000);
                            
	               		}
		               	else{
		               		layer.msg(data.data.text,{icon:7});
		               		return false;
		                }
	                },
	               error:function(data){
	               		layer.msg(data.data.text,{icon:2});
	                    return false;
	                }
	            })
	    }
	    
	    $("#changecaptcha").click(function(){
	    	getchangecaptcha();
	    })
	    
	    $("#submit").click(function(){
	    	var pwd=$("#password").val();
	    	var repwd=$("#repeatpassword").val();
	    	if(!pwd || !repwd){
	    		layer.msg("请将内容填写完整!",{icon:7})
	    		return false;
	    	}
	    	if(pwd!=repwd){
	    		layer.msg("两次输入密码不一致!",{icon:7})
	    		return false;
	    	}
	    	if(!$('#passwordcaptcha').val()){
	    		layer.msg("请填写验证码!",{icon:7})
	    		return false;
	    	}
			var loading=layer.msg('数据提交中，请稍等...', {icon: 16,shade: [0.8, '#393D49']});
	    	$.ajax({
	                type:'PUT',
	                url:'/Nirvana/backend/web/api/account/editPassword',
	                dataType: "json",
	                data:{
	                	captcha:$('#passwordcaptcha').val(),
	                	password:pwd,
	                },
	                success:function(data){
	                	layer.close(loading);
	                	if (data.response=="success"){
                            layer.msg("修改成功!",{icon:1})
                            window.location.href="/Nirvana/backend/web/center";
	               		}
		               	else{
		               		layer.msg(data.data.text,{icon:2})
		               		return false;
		                }	
	                },
	               error:function(data){
	            	   layer.msg("内部错误,请重试!",{icon:2})
	                    return false;
	                }
	            })
	         
	    })