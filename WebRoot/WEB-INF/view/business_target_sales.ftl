

<@override name="title">
业务管理
</@override>

<@override name="link">
	<script src="/Nirvana/static/js/jquery.mtz.monthpicker.js" type="text/javascript"></script>
	<script src="/Nirvana/static/js/layer/layer.js" type="text/javascript"></script>
	<script src="/Nirvana/static/js/target_sales.js" type="text/javascript"></script>
	 <link rel="stylesheet" href="/Nirvana/static/css/jquery-ui-1.7.2.custom.css" type="text/css">
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
    .arrow{
        display: inline-block;
        width: 35%;
        text-align: center;
    }
    .label{
        color: #909090;
    }
    .container{
        margin: 0 20px;
        font-size: 14px;
        border-bottom: 1px solid #d3d3d3;
    }
    .contain{
        padding: 30px 0;
    }
    .target-table{
        border-collapse: collapse;
        width: 100.1%;
        text-align: center;
        font-size: 14px;
    }
    .target-table thead tr{
        background: #0066b3;
    }
    .target-table  tr td{
        padding: 5px 0;
        width:16%;
    }
    .info-table{
        border-collapse: collapse;
        width: 30%;
        text-align: center;
        margin-top: 20px;
    }
    .info-table thead tr td{
        border: 1px solid #d3d3d3;
        height:25px;
    }
    .info-table tbody tr td{
        width: 20%;
        border: 1px  solid #d3d3d3;
        height:35px;
    }
    .target-table tbody tr td input[type=text]{
        width: 80%;
        height: 100%;
        text-align: center;
        outline: none;
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
    .submit-line{
        padding: 15px 30px;
        text-align:right
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
                <a href="/Nirvana/backend/web/business_management/target_sales"><div class="href-block" >NR目标<div class="arrow1"></div></div></a>
                </@security.authorize>
                
                 <@security.authorize ifAnyGranted="BACKEND_TDS,BACKEND_SIS">
                <a href="/Nirvana/backend/web/business_management/target_sdo"><div class="href-block"> SDO目标</div></a>
                </@security.authorize>
            </div>

            <div class="space-line" >
				
            </div>
            <div class="contain">
             <div class="container">
             	<div class="input-item">
             		<div class="content-title">选择职位</div>
	             	<select  id="position">
	             	<#list positions as position>
	             			
							<#if position.type == 'TDS'><option original='tds' value='${position.directorArea.id}'>${position.directorArea.getName()}主任</option></#if>
							<#if position.type == 'TDM'><option original='tdm' value='${position.mManagerArea.id}'>${position.mManagerArea.getName()}经理</option></#if>
							<#if position.type == 'UM'><option original='um' value='${position.mBigArea.id}'>${position.mBigArea.getName()}经理</option></#if>
							<#if position.type == 'SIS'><option original="sis">系统维护员</option></#if>
							
						
					</#list>		
					</select>
				</div>
				
                  <div class="input-item">
                      <div class="content-title" >选择时间</div>
                      <input type="text" readonly="true" class="date-input month-picker">
                  </div>
                  
                  <div class="input-item">
                      <div class="content-title" >当月指标</div>
                      <span id="standrd"></span>
                  </div>
                  
                  <div class="input-item">
                      <div class="content-title" ></div>
                      <input type="button" id="getinfo" value="获取信息" class="button sure">
                  </div>

             </div>
            <div class="hidden"> 
            <div class="table-title">
                	目标项列表
            </div>
            <div class="table-container">
            </div>
            <div class="submit-line">
                       <input type="button" value="保存" class="sure button" id="save">
                       <input type="button" value="取消" class="left cancel button">
            </div>
            </div>
        </div>
    </div>
</@override>
<@override name="script">
<script>

 </script>
</@override>
<@extends name="business_head.ftl"/> 