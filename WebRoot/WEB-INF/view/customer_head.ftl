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
<@block name="style"></@block>
<body>
	<#include "pepsi_head.ftl">
	
	<div class="main">
    <div class="location clearfix">
         <span>您当前的位置:<a href="/Nirvana/backend/web/center">首页</a>>>客户管理</span>
    </div>

    <div class="content clearfix">
        <div class="left-container">
            <ul>
                <li>
                    <div class="arrow">
                        <img src="/Nirvana/static/img/pepsi/arrow1.png">
                    </div>
                    <span class="label"> 客户列表</span>
                </li>
                <li <#if type == "agency">class="current"</#if>>
                    <div class="arrow">

                    </div>
				<a href="/Nirvana/backend/web/customer_management/agency" >经销商</a>
                </li>
                <li <#if type == "direct-sale">class="current"</#if>>
                    <div class="arrow">

                    </div>
                     	 <a href="/Nirvana/backend/web/customer_management/direct_sale">直营店</a>
                </li>
                <li <#if type == "shop">class="current"</#if> >
                    <div class="arrow">

                    </div>
                     	 <a href="/Nirvana/backend/web/customer_management/shop">门店用户</a>
                </li>
                <!--
                <li>
                    <div class="arrow">
                        <img src="/Nirvana/static/img/pepsi/arrow2.png">
                    </div>
                    <span class="label"> 注册审核</span>
                </li>
                <li>
                    <div class="arrow">		

                    </div>
                    	<a href=""> 门店用户</a>
                </li>
           		-->
            </ul>
        </div>
        
        <@block name="content"></@block>
        
    </div>
</body>
<@block name="link"></@block>

<@block name="script"></@block>
</html>