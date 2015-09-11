<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/Nirvana/static/css/common.css" type="text/css">
    <title>反馈信息统计</title>
</head>
<style>
    body{
        background: #ebebeb;
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
    .column{
        width: 90%;
        padding: 20px 0;
        margin: 0 auto;
        font-size: 14px;
    }
    .column-title{
        padding:15px
    }
    .column-title a{
    	float:right;
    	color:#0066b3;
    }
    .column-name{
    	font-size:20px;
    }
    .column-phone{
    	font-size:16px;
    }
    .column-content{
    	font-size:14px;
    	padding:10px 0;
    }
    .column-footer span{
    	margin-right:15px;
    }
</style>
<body>
	<#include "pepsi_head.ftl">
	<div class="main">
    <div class="location clearfix">
        <span class="float-hack">您当前的位置:<a href="/Nirvana/backend/web/center">首页>><a href="">反馈信息统计</a> </span>
    </div>

    <div class="content">
         <div class="downline-1 column-title clearfix">
             <a href="/Nirvana/backend/web/feedback">返回反馈列表</a>
         </div>
		
		<div class="column">
			
			<div class="column-phone">
			${complaint.contact}
			</div>
			<div class="column-content">
			${complaint.content}
			</div>
			<div class="column-footer">
			<span>${complaint.createDate}</span>
			
			<span>${complaint.type.getName()}</span>
			 <@security.authorize ifAnyGranted="BACKEND_SIS"><a href="javascript:void();;" id="delete">删除</a></@security.authorize>
			</div>
        </div> 

    </div>
</div>
<script src="/Nirvana/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="/Nirvana/static/js/layer/layer.js" type"text/javascript"></script>
</body>
<script>
	$("#delete").click(function(){
		layer.confirm('确定要删除此条记录', {icon: 3, title:'提示'}, function(index){
 			var url='/Nirvana/backend/web/api/complaints/${complaint.id}';
			var loading=layer.msg('数据提交中，请稍等...', {icon: 16,shade: [0.8, '#393D49']});
			$.ajax({
       			type:'DELETE',
       			url:url,
       			success:function(data){
       				layer.close(loading);
       				if(data.response=='success'){
       					layer.msg("删除成功",{icon:1})
       					window.location.href="/Nirvana/backend/web/feedback"			
       				}
       				else{
       					layer.msg(data.data.text,{icon:2})
       					
       				}
       			},
       			error:function(){
       				layer.msg("内部错误,请重试!",{icon:2})
       			}
       		});
    		layer.close(index);
		});
	})
</script>
</html>