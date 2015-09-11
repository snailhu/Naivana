<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
     <script src="/Nirvana/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
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
        background: url("/Nirvana/static/img/pepsi/step3.png") no-repeat;
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
    
    .tips-line{
        padding-bottom: 40px;
    }
    .tips-line{
        text-align: center;
        font-size: 18px;
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
        <div class="tips-line">
            <span>新密码已生效</span>
        </div>
        <input type="button" id="submit" value="返回登录">
    </div>
    </div>
</body>
<script>
	$("#submit").click(function(){
		window.location.href="/Nirvana/backend/web/login.html"
	})
</script>
</html>