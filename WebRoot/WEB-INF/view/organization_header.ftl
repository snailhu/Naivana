<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title><@block name="title"></@block></title>
    <script src="/Nirvana/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="/Nirvana/static/js/placeholder.js" type="text/javascript"></script>
    <script src="/Nirvana/static/js/layer/layer.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/Nirvana/static/css/common.css" type="text/css">
    <link rel="stylesheet" href="/Nirvana/static/css/customer_management.css" type="text/css">
</head>
<style>
	.arrow{
		width:28%;
	}
</style>
<@block name="style"></@block>
<body>
	<#include "pepsi_head.ftl">
	
	<div class="main">
    <div class="location clearfix">
         <span>您当前的位置:<a href="/Nirvana/backend/web/center">首页</a>>>人事管理</span>
    </div>

    <div class="content clearfix">
        
        <div class="left-container">
            <ul>
                <li>
                    <div class="arrow clearfix">
                        <img src="/Nirvana/static/img/pepsi/arrow1.png">
                    </div>
                    <span class="label"> 用户添加</span>
                </li>
                <li <#if type == "user">class="current"</#if>>
                    <div class="arrow clearfix">

                    </div>
                    <a href="/Nirvana/backend/web/organization_management/list"> 百事用户</a>
                </li>
                <li>
                    <div class="arrow clearfix">
                        <img src="/Nirvana/static/img/pepsi/arrow2.png">
                    </div>
                    <span class="label"> 组织管理</span>
                </li>
                <li <#if type == "change">class="current"</#if>>
                    <div class="arrow clearfix">

                    </div>
                    <a href="/Nirvana/backend/web/organization_management/change">组织架构调整</a>
                </li>
            </ul>
        </div>
        
        <@block name="content"></@block>
        
    </div>
</body>
<@block name="link"></@block>

<@block name="script"></@block>
</html>