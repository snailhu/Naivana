

<@override name="title">
业务管理
</@override>

<@override name="link">
	<script src="/Nirvana/static/js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="/Nirvana/static/js/timepicker_init.js" type="text/javascript"></script>
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
    .double{
        background: #ebebeb;
    }
    .status-line{
        padding: 12px 0 12px;
        margin-left: 10px;
    }
    .container{
        width: 60%;
        padding: 30px;
    }
    .work-title{
        line-height: 30px;

    }
    .contain{
        font-size: 14px;
    }
    .ver-bus-table{
        width: 100.1%;
        border-collapse: collapse;
        text-align: center;
    }
    .ver-bus-table thead{
        background: #0066b3;
        color: #fff;
    }
    .ver-bus-table tr td{
        padding: 5px 0;
        width: 11.1%;
    }
    .left-slider ul li a{
        width: 100%;
    }
</style>
</@override>

<@override name="content">
<div class="right-slider">
            <div class="link-line" >
                <a href="/Nirvana/backend/web/business_management/visit"><div class="href-block">拜访计划完成情况<div class="arrow1"></div> </div></a>
                <a class="left" href=""><div class="href-block">查看照片统计</div></a>
            </div>

            <div class="space-line">
            </div>

            <div class="contain">
                <div style="padding: 10px 30px">
                    <div class="input-item">
                        <span>时间筛选:</span><input type="text" id="start-date-max" class="date-input left" ><input type="text" id="end-date-max" class="left date-input">
                    </div>
                    
                    <div class="input-item">
                        <span>选择职位:</span><!--	
						--><select class="select-input left" id="position">
	                    	<#list positions as position>
	             			
								<#if position.type == 'TDS'><option original='tds' value='${position.directorArea.id}'>${position.directorArea.getName()}主任</option></#if>
								<#if position.type == 'TDM'><option original='tdm' value='${position.mManagerArea.id}'>${position.mManagerArea.getName()}经理</option></#if>
								<#if position.type == 'UM'><option original='um' value='${position.mBigArea.id}'>${position.mBigArea.getName()}经理</option></#if>
								<#if position.type == 'AGENT'><option original='agent' value='${position.agentArea.id}'>${position.agentArea.getName()}业务员</option></#if>
								<#if position.type == 'CLERK'><option original='clerk' value='${position.cManagerArea.id}'>${position.cManagerArea.getName()}文员</option></#if>
								<#if position.type == 'FSIS'><option original='fsis'value='${position.fBigArea.id}'>${position.fBigArea.getName()}区域系统主管</option></#if>
								<#if position.type == 'SIS'>系统维护员</#if>
								<#if position.type == 'INFO_MINISTRY'>咨询部</#if>
								<#if position.type == 'CDM'>渠道经理</#if>
								<#if position.type == 'SISM'>销售能力经理</#if>
								<#if position.type == 'MDM '>市场拓展总监</#if>
								<#if position.type == 'GM'>总经理</#if>
							
						
							</#list>		
                    	</select>    
                    </div>
                    
                    <div class="input-item">
                        <span>选择小区:</span><select id="job" class="select-input left"></select>
                    </div>
                </div>

                <div style="padding: 15px 0 15px 30px;border-top: 1px solid #e3e3e3;border-bottom: 1px solid #e3e3e3">
                    <input type="button" id="submit" value="确认" class="button sure">
                    <input type="button" value="取消" class="button sure left">
                </div>

                <div>
                    <div style="padding: 10px 0">

                    </div>
                    <div class="table-contain">
                    </div>
                </div>
            </div>
			<div id="pager" style="text-align:center"></div>
        </div>
</@override>
<@override name="script">
<script>

$(document).ready(function(){
 	
 
})
 var current_page=1;
 var current_size=5;
 $('#submit').click(function(){
 	$startDate=$('#start-date-max').val()
 	$endDate=$('#end-date-max').val()
 	$id=$('#job').val()
 	if ($startDate && $endDate && $id){
		getlist()	
		}
		else{
			alert('请将数据填写完整!')
		}	
			
 })
 
 
  PageClick = function(pageclickednumber) {
            current_page=pageclickednumber;
    		getlist()
        }
        
        
        
        
        
  function getlist(){
  	$('.ver-bus-table').remove()
  	$('.pages').remove()
  	$startDate=$('#start-date-max').val()
 	$endDate=$('#end-date-max').val()
 	$id=$('#job').val()
  	$.getJSON('/Nirvana/backend/web/api/area/agentareas/'+$id+'/visitrecords?startDate='+$startDate+'&endDate='+$endDate+'&page='+current_page+'&size='+current_size, {}, function (json) {
				var temp='<table class="ver-bus-table">'+
                        '<thead>'+
                        '<tr>'+
                            '<td>序号</td>'+
                            '<td>客户代码</td>'+
                            '<td>拜访客户</td>'+
                            '<td>地址</td>'+
                            '<td>拜访累计时间</td>'+
                            '<td>拜访日期</td>'+
                        '</tr>'+
                        '</thead>'
	    		temp=temp+'<tbody>'
	    		var number=0
	               for(i in json.records){
	               	   number++;
	               	   if (number%2==0){
	                   		temp=temp+'<tr class="double">';
	                   }
	                   else{
	                   		temp=temp+'<tr >';
	                   }	
	                   temp=temp+'<td>'+json.records[i].id+'</td>'+
	                   '<td>'+json.records[i].storeId+'</td>'+
	                   '<td>'+json.records[i].storeName+'</td>'+
	                   '<td>'+json.records[i].storeAddr+'</td>'+
	                   '<td>'+json.records[i].visitDate+'</td>'+
	                     '<td>'+json.records[i].visitTime+'</td>'+
	               
						'</tr>'
	               }
	             temp=temp+'</tbody></table>';
	              $('.table-contain').append(temp)
	             $("#pager").pager({ pagenumber: current_page, pagecount: json.totalPages, buttonClickCallback: PageClick });
	              
			})
  }      

getJobList($('#position').val());

$('#position').change(function(){
	getJobList($('#position').val());
})

function getJobList(id){

	if ($('#position').find("option:selected").attr('original')=='um'){
		var listUrl='/Nirvana/backend/web/api/area/bigareas/'+$('#position').val()+'/managerareas';
		var standardUrl='/Nirvana/backend/web/api/area/bigarea/'+$('#position').val()+'/goal';
		var name="areaName"
	}
	else if ($('#position').find("option:selected").attr('original')=='tdm'){
		var listUrl='/Nirvana/backend/web/api/area/managerareas/'+$('#position').val()+'/directareas';
		var standardUrl='/Nirvana/backend/web/api/area/managerarea/'+$('#position').val()+'/goal';
		var name="areaName"
	}
	else if ($('#position').find("option:selected").attr('original')=='tds'){
		var listUrl='/Nirvana/backend/web/api/area/directorareas/'+$('#position').val()+'/agentareas';
		var standardUrl='/Nirvana/backend/web/api/area/directorarea/'+$('#position').val()+'/goal';
		var name="areaName"
	}
	else{
		return false;
	}
	$.getJSON(listUrl, {}, function (json) {
 			var info=json.data;
				$('#job').empty();
				for(var i=0;i<info.length;i++){
					$('#job').append('<option value="'+info[i].id+'">'+info[i][name]+'</option>');  
				}
 	})
}	
 </script>
</@override>
<@extends name="business_head.ftl"/> 