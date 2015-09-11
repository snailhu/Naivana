<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
     <script src="/Nirvana/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="/Nirvana/static/js/placeholder.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/Nirvana/static/css/common.css" type="text/css">
</head>
<style>
       .steps{
        width: 755px;
        margin: 0 auto;
        padding: 0;
        height: 60px;
    }
    .steps li{
        list-style-type: none;
        float: left;
        width: 33.3%;
        text-align: center;
        font-family: 'microsoft yahei';

    }
    .main{
        width: 60%;
        min-width: 820px;
        margin: 3% auto;
    }
    .progess-bar {
        height: 25px;
        width: 526px;
        margin: 0 auto;
        background: url("/Nirvana/static/img/pepsi/step1.png") no-repeat;
    }
    .progress{
        margin: 5% 0;
    }
    .container-form{
        width: 50%;
        margin: 0 auto;
    }
    #submit{
        width: 100%;
        background: #0c57ac;
        border: none;
        height: 50px;
        color: #fff;
        font-size: 20px;
        font-family: 'microsoft yahei';
        cursor: pointer;
    }
    #submit:active{
        background:#054790;
    }
    .find-pwd{
        height: 50px;
        margin-bottom: 20px;
    }
    .find-pwd .placeholder{
        height: 50px;
        line-height: 50px;
    }
    .find-pwd .form-control{
        width: 97%;
        height: 48px;
        line-height: 48px;
    }
    .find-pwd a{
        display: inline-block;
        width: 22%;
        height: 48px;
        cursor: pointer;
    }
    #category{
        width: 65%;
    }
    .errorMessage{
    	color:red;
    	height:30px;
    }
</style>
<body>
	<#include "pepsi_head.ftl">
	  <div class="main">
       <span style="font-size: 30px;text-align: center;width: 100%;display: block">找回登录密码</span>
        <div class="progress">
           <ul class="steps">
               <div class="progess-bar"></div>
             <li>手机验证</li>
             <li>重置密码</li>
             <li>完成</li>
              <br/>
           </ul>
        </div>
        <div class="container-form" >
        	<div class="errorMessage">
        	${errorMessage}
        	</div>
        	<form action="/Nirvana/backend/web/resetpwd/step2" method="POST" id="myform">
            <div class="group-input find-pwd">
                <label for="username" class="placeholder">手机号</label>
                <input type="text" id="phone" class="form-control" name="phone" value="${phone}">
            </div>
            <div class="group-input find-pwd"><!--
                --><label for="category" class="placeholder">验证码</label>
                <input type="text" id="category" class="form-control" name="category"><!--
                --><span id="getcategory" style="padding: 18px 10px;position:absolute;vertical-align: middle;cursor: pointer;border-left:1px solid #d3d3d3;font-size: 14px;color: #0066b3;text-align:center;width:28%">获取验证码</span>
            </div>
            </form>
             <input type="button" id="submit" value="下一步">
        </div>
       
    </div>
</body>
<script>
	$('#getcategory').click(function(){
		getCaptcha()
	})

	function getCaptcha(){
                var re=/^1[3|5|7|8][0-9]{9}$/;
                var phone=$('#phone').val();
                if(!re.exec(phone)){
                    alert('手机号码格式不正确');
                    return false;
                }
              else{
            	
	              $.ajax({
	                type:'GET',
	                url:'/Nirvana/backend/web/api/account/findback/getCaptcha',
	                dataType: "json",
	                data:{
	                	number:phone
	                },
	                success:function(data){
	                	if (data.response=="success"){
	               			$('#getcategory').unbind('click');
	                		  var sec=60
                            function seconed(){
                                sec--;
                                if(sec>=0){
                                     document.getElementById("getcategory").innerHTML=sec+'秒之后重新发送'
                                }
                                else{
                                    clearInterval(st)
                                    document.getElementById("getcategory").innerHTML='获取验证码'
                                    $('#getcategory').bind('click',getCaptcha)
                                }
                                
                            }
                 			var st=setInterval(seconed,1000);
                            
	               		}
		               	else{
		               		alert(data.data.text)
		               		return false;
		                }
	                },
	               error:function(data){
	               		alert('请重试！')
	                    return false;
	                }
	            })
	           }       
	        }
 	
 	$("#submit").click(function(){
 		var re=/^1[3|5|7|8][0-9]{9}$/;
        var phone=$('#phone').val();
        if(!re.exec(phone)){
            alert('手机号码格式不正确');
            return false;
        }
        var category=$("#category").val();
        if(phone && category){
        	$("#myform").submit();
        }
        else{
        	alert("请将内容填写完整!")
        }
        
        
 	})
</script>
</html>