

<@override name="title">
业务管理
</@override>

<@override name="link">
 	<script src="/Nirvana/static/js/layer/layer.js" type="text/javascript"></script>
    <script src="/Nirvana/static/js/jquery.pager.js" type="text/javascript"></script>
 	<script src="/Nirvana/static/js/placeholder.js" type="text/javascript"></script>
    <script src="/Nirvana/static/js/jDroplist.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/Nirvana/static/css/layer_content.css" type="text/css">
    <link rel="stylesheet" href="/Nirvana/static/css/Pager.css" type="text/css">
</@override>

<@override name="style">
<style>
 html, body {
        height: 100%;
        margin: 0;
        padding: 0;
        background:#ebebeb ;
        font-family: 'microsoft yahei';
    }
    .user-table{
        border-collapse: collapse;
        width: 100%;
        text-align: center;
        font-size: 14px;
    }
    .user-table thead{
        background: #0066b3;
        color: #fff;
    }
    .user-table thead td{
        width: 14%;
        padding: 10px 0;
    }
    .user-table tbody td{
        padding: 8px 0;
    }
    .left-slider ul li:last-child{
        border: none;
    }

    .user-table tr td img{
        cursor: pointer;
    }
    .label{
        color: #909090;
    }
	.button-dis{
 		width:35px;
 		height:30px;
 		margin-top:8px;
 	}  
    .layer-title{
    	cursor:pointer;
    	padding:7px 10px 9px;
    }
    .layer-title.layer-current{
    	color:#0066b3;
    	border-bottom:2px solid #0066b3;
    	z-index:19891220;
    }
</style>
</@override>

<@override name="content">
 <div class="right-slider">
            <!--右侧导航栏开始-->
            <div class="link-line clearfix">
            <#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /><@security.authorize ifAnyGranted="BACKEND_SIS,BACKEND_CLERK">
                <a class="float-hack search-height" href="/Nirvana/backend/web/business_management/user_list"><div class="href-block" >小区列表<div class="arrow1"></div></div></a>
            </@security.authorize>
            <@security.authorize ifAnyGranted="BACKEND_SIS">
                <a href="/Nirvana/backend/web/business_management/user_add" class="float-hack search-height"><div class="href-block">添加新小区</div></a>
            </@security.authorize>    
                
                <div class="search-wrap">
                    <div class="hack">
                        <div class="cnt">
                            <div class="group-input search-box">
                                <label for="search" class="placeholder">区或客户编码</label>
                                <input type="text" id="search" class="form-control">
                                <img src="/Nirvana/static/img/pepsi/serch.png">
                            </div>
                        </div>
                    </div>
                </div>
                
            </div>
            <!--右侧导航栏结束-->
            <div class="space-line">
            </div>


            <div class="status-line clearfix">
                <span class="float-hack" id="total-user"></span>
                <div style="float: right">
                    <img src="/Nirvana/static/img/pepsi/fresh.png"  class="fresh-logo"><!--
                    --><img src="/Nirvana/static/img/pepsi/see.png" class="edit-logo">
                </div>
            </div>
            
		
            <!--右侧内容开始-->
            <table class="user-table" style="width: 100.1%">
                  
             </table>
            <!--右侧内容结束-->
            <div class="table-content">
            </div>
            <div id="pager" style="text-align:center"></div>
        </div>
</@override>
<@override name="script">
<script>
	$(document).ready(function(){
	   
	    $('.user-table').dropList({
	    	url:'/Nirvana/backend/web/api/area/agentareas?page={page}&size={size}',
	    	editUrl:'/Nirvana/backend/web/api/area/agentareas/{id}/edit',
	    	tableText:['小区号','小区名称','小区类型','业务姓名','联系电话'],
	    	 dataText:['areaCode','areaName','workType','realName','phone'],
	    	 closeUrl:'/Nirvana/backend/web/api/area/agentareas/{id}/close',
            closeType:'PUT',
            id:'agentAreaId',
	    	close:true,
	    	<@security.authorize ifAnyGranted="BACKEND_CLERK">
	    	edit:true,
	    	dis:true,		
	    	</@security.authorize>
	    	size:10
	    	
	    })
	   
    })
   
    

</script>
</@override>
<@extends name="business_head.ftl"/> 