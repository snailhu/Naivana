

<@override name="title">
业务管理
</@override>

<@override name="link">
	<script src="/Nirvana/static/js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="/Nirvana/static/js/jquery.mtz.monthpicker.js" type="text/javascript"></script>
	<script src="/Nirvana/static/js/timepicker_init.js" type="text/javascript"></script>
	 <link rel="stylesheet" href="/Nirvana/static/css/jquery-ui-1.7.2.custom.css" type="text/css">
	 <script src="/Nirvana/static/js/jquery.multiselect.min.js" type="text/javascript"></script>
    <link href="/Nirvana/static/css/jquery.multiselect.css" type="text/css" rel="stylesheet">
    <script src="/Nirvana/static/js/layer/layer.js" type="text/javascript"></script>
     <script src="/Nirvana/static/js/jquery.uploadify.js" type="text/javascript"></script>
    <script src="/Nirvana/static/js/target_sdo.js" type="text/javascript"></script>
     
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
    .double{
        background: #ebebeb;
    }
    .container{
        margin: 0 20px;
        font-size: 14px;
    }
    .contain{
        padding: 30px 0;
        font-size: 14px;
    }
    .target-table{
        border-collapse: collapse;
        width: 100.1%;
        text-align: center;
    }
    .target-table thead tr{
        background: #0066b3;
    }
    .target-table  tr td{
    	width:20%;
        padding: 5px 0;
    }

    .submit-line{
        text-align: right;
        padding: 15px 0;
    }
    .content-title{
        display: inline-block;
        width: 70px;
        text-align: right;
        margin-right: 10px;
    }
    .content-title{
        *display: inline;
    }
    .table-title{
        padding: 20px 2px 6px ;
        font-size: 15px;
        color: #0066b3;
    }
    .table-sdo{
    	margin:20px 0;
    	width:100%;
    	text-align:center;
    	 border-collapse:collapse;
    }
    .table-sdo tr{
    	height:30px;
    }
    .table-sdo tr td{
    	border:1px solid #e3e3e3;
    }
    .sdo-name{
    	width:10%;
    	text-align:center;
    }
    .sdo-title{
    	font-weight:bold;
    }
    .hidden{
    	display:none;
    }
</style>
</@override>

<@override name="content">
   <div class="right-slider">
            <div class="link-line">
				<@security.authorize ifAnyGranted="BACKEND_TDS,BACKEND_TDM,BACKEND_UM">
                <a href="/Nirvana/backend/web/business_management/target_sales"><div class="href-block" >NR目标</div></a>
                </@security.authorize>
                
                 <@security.authorize ifAnyGranted="BACKEND_TDS,BACKEND_SIS">
                <a href="/Nirvana/backend/web/business_management/target_sdo"><div class="href-block"> SDO目标<div class="arrow1"></div></div></a>
                </@security.authorize>
            </div>
            
            
            <div class="space-line">
            </div>
            <div class="contain">

            <div class="container">
                <div class="input-item" style="position:relative">
                    <span class="content-title">选择时间</span>
                    <input type="text" class="month-picker date-input" >
                    <@security.authorize ifAnyGranted="BACKEND_TDS">               
                    <a id="getsdo" href="javascript:;" style="margin-left:5%">获取信息</a>
                    </@security.authorize>
                    <@security.authorize ifAnyGranted="BACKEND_SIS">
                    <div style="display:inline-block;position:absolute;right:5%"><input type="file" id="file_upload" hidden="hidden"></div>
                    </@security.authorize>         
                </div>
            </div>
            
            <div class="hidden">
			<table class="table-sdo">
            </table>
            	
            	 <div class="container">
	                <div class="input-item">
	                	<span class="content-title">选择主任区</span>
	                	<select class="select-input" id="position"><#list directorAreas as area><option value=${area.id}>${area.name}</option></#list></select>
	                    <span class="content-title">执行者</span>
	                    <select class="select-input" id="job"></select>
	                    <span class="content-title">SDO项目</span>
	                    <select class="select-input" id="sdo-item" multiple="multiple"></select>               
	                </div>
	                <div class="input-item">
	                	  <input type="button" id='submit' class="button sure" value="确认">
                    		<input type="button" id='cancel' class="button cancel" value="取消">
	                </div>
	            </div>
            
                <div class="table-title">
                    SDO列表
                </div>

            <table class="target-table" >
                <thead style="background: #0066b3;color: #fff">
                <tr >
                    <td>类型</td>
                    <td>日期</td>
                    <td>创建人</td>
                    <td>执行者</td>
                    <td>SDO项目</td>
                </tr>
                </thead>
                <tbody>
               
                </tbody>
            </table>
        </div>
        </div>
        </div>
</@override>

<@override name="script">
  <script>

 	</script>
</@override>
<@extends name="business_head.ftl"/> 