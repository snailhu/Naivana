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
        background: url("/Nirvana/static/img/pepsi/step2.png") no-repeat;
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
        <div class="container-form">
        	<form action="/Nirvana/backend/web/resetpwd/step3" method="POST" id="myform">
            <div class="group-input find-pwd">
                <label for="username" class="placeholder">新密码</label>
                <input type="password" id="pwd" class="form-control" name="password">
            </div>
            <div class="group-input find-pwd">
                <label for="username" class="placeholder">重复新密码</label>
                <input type="password" id="repwd" class="form-control" name="repeatPassword">
            </div>
            </form>
            <input type="button" id="submit" value="下一步">
        </div>
    </div>
</body>
<script>
	$("#submit").click(function(){
		if($("#pwd").val()==$("#repwd").val()){
			$("#myform").submit()
		}
		else{
			alert("两次密码输入不一致!")
		}
	})
</script>
</html>