<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title><@block name="title"></@block></title>
    <link rel="stylesheet" href="/Nirvana/static/css/common.css" type="text/css">
	<@block name="css"></@block>
</head>
<@block name="style"></@block>
<body>
	<#include "pepsi_head.ftl">
	
	<div class="main">
    <div class="location clearfix">
        <span>您当前的位置:<a href="/Nirvana/backend/web/center">首页</a>>>促销管理</span>
    </div>

    <div class="content">
        <div class="subnav">
        	<@security.authorize ifAnyGranted="BACKEND_SIS"> 		
        	<a href="/Nirvana/backend/web/sales_management/publish">
            <div class="suffix-inline <#if type == "publish">sales-current</#if> sales-link">
                发布促销活动
            </div></a></@security.authorize><@security.authorize ifAnyGranted="BACKEND_SIS,BACKEND_MDM"><!--
            --><a href="/Nirvana/backend/web/sales_management/history"><div class="suffix-inline <#if type == "history">sales-current</#if> sales-link">
                历史促销活动
            </div></a></@security.authorize>
            <@security.authorize ifAnyGranted="BACKEND_MDM">
            <a href="/Nirvana/backend/web/sales_management/review"><div class="suffix-inline <#if type == "review">sales-current</#if> sales-link">
              	待审核
            </div></a>
            </@security.authorize>
            <@security.authorize ifAnyGranted="BACKEND_SIS">
            <a href="/Nirvana/backend/web/sales_management/rejected"><div class="suffix-inline <#if type == "rejected">sales-current</#if> sales-link">
              	未通过
            </div></a>
            </@security.authorize>

        </div>
      
       <@block name="contain"></@block>
    


</div>
</body>
<script src="/Nirvana/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="/Nirvana/static/js/layer/layer.js" type="text/javascript"></script>
<@block name="javascript"></@block>
<@block name="script"></@block>
</html>