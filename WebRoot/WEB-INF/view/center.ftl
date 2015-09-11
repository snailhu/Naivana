<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>管理中心</title>
    <link rel="stylesheet" href="/Nirvana/static/css/common.css" type="text/css">
</head>
<style>
      body{
        background: #ebebeb;
    }
    .center{
        width: 92%;
        margin: 0 auto;
    }
    .center li{
        float: left;
        width: 33.3%;
        text-align: left;
        padding-top: 50px;
    }
    .main{
        width: 85%;
        min-width: 1000px;
        margin: 3% auto;
        background: #fff;
        padding-bottom: 50px;
    }
    .title-bar{
        margin: 0 30px;
        border-bottom: 2px solid #edecec;
        padding: 15px 0
    }
    .title-bar span{
        border-bottom: 2px solid #0c58ab;
        padding: 15px 0;
    }
    .center img{
        vertical-align: middle;
    }
    .center-item{
        display: inline-block;
        text-align: left;
        height: 64px;
        vertical-align: middle
    }
    .up-line{
        height: 48px;
        line-height: 36px
    }
    .down-line{
        line-height: 32px;
        font-size: 14px
    }
</style>
<body>
	<#include "pepsi_head.ftl">
	<div class="main">
        <div class="title-bar">
            <span>营销管理中心</span>
        </div>
      <ul class="clearfix center">
      	 <@security.authorize ifAnyGranted="BACKEND_ADMIN">
          <li>
          	  <a href="/Nirvana/backend/web/organization_management/list">	
              <img src="/Nirvana/static/img/pepsi/sd.png" class="float-hack">
              <div class="center-item">
                  <span class="up-line">人事管理</span><br>
                  <span class="down-line">职位分配和人事调动</span>
              </div>
              </a>
          </li>
		  </@security.authorize>
		  
		   <@security.authorize ifAnyGranted="BACKEND_SIS,BACKEND_MDM"> 	
          <li>
          	  <a href="/Nirvana/backend/web/sales_management/history">	
              <img src="/Nirvana/static/img/pepsi/promotion_management.png" class="float-hack">
              <div  class="center-item">
                  <span class="up-line">促销管理</span><br>
                  <span class="down-line">促销活动的发布和查询</span>
              </div>
              </a>
          </li>
          </@security.authorize>
          
         <@security.authorize ifAnyGranted="BACKEDN_SIS,BACKEND_CLERK">          
          <li>
              <a href="/Nirvana/backend/web/customer_management/agency">
              <img src="/Nirvana/static/img/pepsi/customer_management.png" class="float-hack">
              <div  class="center-item">
                  <span class="up-line">客户管理</span><br>
                  <span class="down-line">管理门店和经销商</span>
              </div>
              </a>
          </li>
		 </@security.authorize>
		 
		 <@security.authorize ifAnyGranted="BACKEND_ADMIN,BACKEND_SIS,BACKEND_GM,BACKEND_MDM,BACKEND_SISM">
          <li>
          	  <a href="/Nirvana/backend/web/feedback">	
              <img src="/Nirvana/static/img/pepsi/feedback.png" class="float-hack" >
              <div  class="center-item">
                  <span class="up-line">反馈信息统计</span><br>
                  <span class="down-line">收集统计反馈信息</span>
              </div>
              </a>		
          </li>
          </@security.authorize>
          
         <@security.authorize ifAnyGranted="BACKEND_ADMIN,BACKEND_SIS,BACKEND_GM,BACKEND_MDM,BACKEND_SISM,BACKEND_CLERK,BACKEND_TDS,BACKEND_TDM,BACKEND_UM">
          <li>
          	  <a href="/Nirvana/backend/web/business_management/performance_sales">	
              <img src="/Nirvana/static/img/pepsi/business_management.png" class="float-hack">
              <div  class="center-item">
                  <span class="up-line">业务管理</span><br>
                  <span class="down-line">辖区管理、客户分配、工作统计等</span>
              </div>
              </a>
          </li>
          </@security.authorize>

          <li>
          	  <a href="/Nirvana/backend/web/personal">	
              <img src="/Nirvana/static/img/pepsi/personal.png" class="float-hack">
              <div  class="center-item">
                  <span class="up-line">用户中心</span><br>
                  <span class="down-line">用户个人资料查看、修改</span>
              </div>
              </a>
          </li>

      </ul>

    </div>
</body>
</html>