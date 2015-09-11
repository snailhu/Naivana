<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/Nirvana/static/css/common.css" type="text/css">
    <title>用户中心</title>
</head>
<style>
      body{
        background: #ebebeb;
    }
    .content{
        background: #fff;
        padding: 20px 30px;
    }
    .main{
        padding-top: 30px;
        width: 60%;
        margin: 0 auto;
    }
    .activity-list{
        color: #404040;
    }
    .location{
        color: #404040;
        padding-bottom: 10px;
    }
    .input-item input[type=text],input[type=password]{
        height: 30px;
        width: 60%;
    }
    .edit-title{
    	padding:10px 0;
    	font-size:16px;
    	color:#000;
    }
    #sendcaptcha{
    	margin-left:10px;
    }
    .standby{
    	display:none;
    }
</style>
<body>
	<#include "pepsi_head.ftl">
	<div class="main">
    <div class="location clearfix">
        <span class="float-hack">您当前的位置:<a href="/Nirvana/backend/web/center">首页</a>>>用户中心 </span>
    </div>

    <div class="content">
        <div style="padding: 5px 0;text-align: center;font-size: 18px">
            <span>用户账号设置</span>
        </div>
        <div style="padding: 0 5%">
            

            <div class="input-item">
                <span class="suffix-inline" style="width: 100px">联系电话</span>
                <span id="phone"><#if user.phone !="">${user.phone?substring(0,3)}****${user.phone?substring(7,11)}</#if></span>
                <a href="javascript:void(0);" style="color:#0066b3;margin:0 20px;" id="unbind" <#if user.phone =="">class="standby"</#if>>解除绑定</a>
               <a href="javascript:void(0);" style="color:#0066b3;" id="bind" <#if user.phone !="">class="standby"</#if>>绑定手机</a>
            </div>

            <div class="input-item">
                <span class="suffix-inline" style="width: 100px">密码</span>
                <input type="password" id="password">
               
            </div>
            
            <div class="input-item">
                <span class="suffix-inline" style="width: 100px">重复密码</span>
                <input type="password" id="repeatpassword">
            </div>
            
            <div class="input-item">
            	<span class="suffix-inline" style="width:100px">验证码</span>
            	<input type="text" id="passwordcaptcha" style="width:40%">
            	<input type="button" value="获取验证码" class="button sure" id="changecaptcha">
            </div>
            
            

            <div style="text-align: center" class="input-item" >
                <input type="button" value="确定" class="button sure" id="submit">
                <input type="button" value="取消" class="button left" id="cancel">
            </div>
        </div>
    </div>
</div>
</body>
	<script src="/Nirvana/static/js/jquery.js" type="text/javascript"></script>
	<script src="/Nirvana/static/js/layer/layer.js" type="text/javascript"></script>
	<script src="/Nirvana/static/js/personal.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/Nirvana/static/css/layer_content.css" type="text/css">
    
<script>
	
</script>
</html>