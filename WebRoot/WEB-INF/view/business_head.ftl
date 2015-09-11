<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title><@block name="title"></@block></title>
    <script src="/Nirvana/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/Nirvana/static/css/common.css" type="text/css">
    <link rel="stylesheet" href="/Nirvana/static/css/business_management.css" type="text/css">
</head>
<@block name="style"></@block>
<body>
	<#include "pepsi_head.ftl">
	
	<div class="main">
	    <div class="location">
	        <span>您当前的位置:<a href="/Nirvana/backend/web/center">首页</a>>>业务管理</span>
	    </div>
	    
	     <div class="content clearfix">
	        <!--左侧导航栏开始-->
	        <div class="left-slider">
	             <ul>
	             
	            <@security.authorize ifAnyGranted="BACKEND_SIS,BACKEND_CLERK">             
                <li class="downline-1 <#if type == "user">current</#if>">
                    <a href="/Nirvana/backend/web/business_management/user_list"><div class="ablock">小区管理</div></a>
                </li>
                </@security.authorize>
                
               <@security.authorize ifAnyGranted="BACKEND_TDS,BACKEND_TDM,BACKEND_UM,BACKEND_SIS">
                <li class="downline-1 <#if type == "target">current</#if>">
					<@security.authorize ifAnyGranted="BACKEND_TDS,BACKEND_TDM,BACKEND_UM"> <a href="/Nirvana/backend/web/business_management/target_sales"></@security.authorize>
                	<@security.authorize ifAnyGranted="BACKEND_SIS,BACKEND_TDS"> <a href="/Nirvana/backend/web/business_management/target_sdo"></@security.authorize>
                   <div class="ablock"> 目标分配</div></a>
                </li>
                </@security.authorize>
                
                <@security.authorize ifAnyGranted="BACKEND_TDS">
                <li class="downline-1 <#if type == "dis">current</#if>">
                    <a href="/Nirvana/backend/web/business_management/line_dis"><div class="ablock">线路分配</div> </a>
                </li>
                </@security.authorize>
                
                 <@security.authorize ifAnyGranted="BACKEND_ADMIN,BACKEND_SIS,BACKEND_GM,BACKEND_MDM,BACKEND_SISM,BACKEND_CLERK,BACKEND_TDS,BACKEND_TDM,BACKEND_UM">
                <li class="downline-1 <#if type == "performance">current</#if>">
                    <a href="/Nirvana/backend/web/business_management/performance_sales"><div class="ablock"> 业绩版</div></a>
                </li>
                </@security.authorize>
                
              <@security.authorize ifAnyGranted="BACKEND_ADMIN,BACKEND_SIS,BACKEND_SISM,BACKEND_CLERK,BACKEND_TDS,BACKEND_TDM,BACKEND_UM">
                <li class="<#if type == "visit">current</#if>">
                    <a href="/Nirvana/backend/web/business_management/visit"><div class="ablock">拜访记录</div></a>
                </li>
                </@security.authorize>
            </ul>
	        </div>
	        <!--左侧导航栏结束-->
	        
            <@block name="content"></@block>

    </div>
 </div>   
</body>
<@block name="link"></@block>

<@block name="script"></@block>
</html>