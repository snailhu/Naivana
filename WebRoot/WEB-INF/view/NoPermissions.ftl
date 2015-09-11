<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>404</title>
    <meta http-equiv=refresh content="10;url=/Nirvana/backend/web/center">
    <link rel="stylesheet" href="/Nirvana/static/css/common.css" type="text/css">
	<@block name="css"></@block>
</head>
<style>
.error-wrap{
	padding:50px;
	text-align:center;
}
.info{
	font-size:40px;
}
</style>
<body>
	<#include "pepsi_head.ftl">
	<div class="error-wrap">
	<div class="info">
		您没有权限操作！
	</div>
	<div id="time-tip">
		10秒后自动跳转到首页
	</div>
	<div>
		<a href="/Nirvana/backend/web/center">点击跳转</a>
	</div>
	<div>


</body>
<script src="/Nirvana/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script>
	$(document).ready(function(){
		 var sec=10;
		  function seconed(){
                sec--;
                if(sec>=0){
 						$('#time-tip').html(sec+'秒后自动跳转到首页')
     				 }
     			else{
     				 clearInterval(st)
     				$('#time-tip').html("如果浏览器没有自动跳转请点击跳转！")	
     			}
     	}		
 			var st=setInterval(seconed,1000);
	})
</script>
</html>