<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/Nirvana/static/css/common.css" type="text/css">
    <title>反馈信息统计</title>
</head>
<style>
    html, body {
        height: 100%;
        margin: 0;
        padding: 0;
        background:#ebebeb ;
        font-family: 'microsoft yahei';
    }
    .content{
        background: #fff;
    }
    .main{
        padding-top: 30px;
        min-width:800px;
        width: 80%;
        margin: 0 auto;
    }
    .content{
        padding: 20px 30px;
    }
    .column{
        width: 98%;
        padding: 20px 0;
        margin: 0 auto;
        font-size: 14px;
        border-bottom: 1px solid #0066b3;
    }
    .column-title{
        padding: 5px 0;
        border-bottom: 2px solid #0066b3;
    }
    .column-left{
        width: 10%;
        text-align: center;
    }
    .column-center div{
        line-height: 30px
    }
    .column-right{
        float: right;
        color: #0066b3;
    }
    .column-footer{
        padding-top: 20px;
    }
    .column-footer a{
        color: #0066b3;
    }
    .column-userName{
        font-size: 16px;
    }
</style>
<body>
	<#include "pepsi_head.ftl">
	<div class="main">
    <div class="location clearfix">
        <span class="float-hack">您当前的位置:<a href="/Nirvana/backend/web/center">首页</a>>>反馈信息统计</span>
    </div>

    <div class="content">
         <div class="column-title">
         </div>
		
		<#list complaints as complaint>
				<div class="column">
	             <!--
	             --><div class="column-center suffix-inline" style="vertical-align: top">
	                 <div style="line-height: 30px"> <span>${complaint.contact}</span></div>
	             </div>
	
	             <div class="column-right suffix-inline">
	                 	反馈类型:${complaint.type.getName()}
	             </div>
	
	             <div class="column-content">
	                 <span>${complaint.content}</span>
	             </div>
	
	             <div class="column-footer">
	                <span>${complaint.createDate}</span>
	                <a href="/Nirvana/backend/web/feedback/${complaint.id}/detail" class="left">详情</a>
	             </div>
	         </div>
		</#list>
          <div id="pager" style="text-align:center"></div>

    </div>
</div>
<script src="/Nirvana/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
 <script src="/Nirvana/static/js/jquery.pager.js" type="text/javascript"></script>
  <link rel="stylesheet" href="/Nirvana/static/css/Pager.css" type="text/css">
</body>
<script>
PageClick = function(pageclickednumber) {
                window.location.href = "/Nirvana/backend/web/feedback?page="+pageclickednumber;
            };
$("#pager").pager({ pagenumber: ${currentpage}, pagecount: ${totalpage}, buttonClickCallback: PageClick });
</script>
</html>