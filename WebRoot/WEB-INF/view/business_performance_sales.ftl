

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
    .container{
        width: 60%;
        padding: 30px;
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
    .input-item select{
        width: 124px;
        height: 25px;
    }
</style>
</@override>

<@override name="content">
 <div class="right-slider">
            <div class="link-line" >
                <a href="/Nirvana/backend/web/business_management/performance_sales"><div class="href-block">销量<div class="arrow1"></div> </div></a>
                <a href="/Nirvana/backend/web/business_management/performance_productivity"><div class="href-block">生产力</div></a>
            </div>

            <div class="space-line">
            
            </div>

            <div class="contain">
                <div style="padding: 10px 30px">
                        <div class="input-item">
                            <span>时间筛选:</span><input type="text" id="start-date-max" class="date-input left"><input type="text" id="end-date-max" class="left date-input">
                        </div>
                </div>

                <div style="padding: 15px 0 15px 30px;border-top: 1px solid #e3e3e3;border-bottom: 1px solid #e3e3e3">
                    <input type="button" id="submit"value="确认" class="button sure">
                    <input type="button" value="取消" class="button sure left">
                </div>

                <div>
                    <div style="padding: 10px 0">

                    </div>
                    <div class="table-container">
                    </div>
                </div>
            </div>
			<div id="pager" style="text-align:center"></div>
        </div>
</@override>
<@override name="script">
  <script>
  var current_size=5;
  var current_page=1;
 
	$('#submit').click(function(){
		var $startmonth=$('#start-month').val()
		var $endmonth=$('#end-month').val()
		var start=new Date($startmonth.replace("-", "/"));  
		var end=new Date($endmonth.replace("-", "/")); 
		if (!$startmonth && !$endmonth){
			alert('请选择开始时间和结束时间')
		}
		else if(start>end){
	 		alert('开始时间不能大于结束时间')
	 	}
	 	else{
	 		getlist()
	 	}
	})
	
	
	  PageClick = function(pageclickednumber) {
            current_page=pageclickednumber;
    		getlist()
        }
        
	function getlist(){
		$('.ver-bus-table').remove()
		var $startmonth=$('#start-month').val()
		var $endmonth=$('#end-month').val()
		$.getJSON('/Nirvana/backend/web/api/agents/salesVols/director/month?startDate='+$startmonth+'&endDate='+$endmonth+'&page='+current_page+'&size='+current_size, {}, function (json) {
			var temp='<table class="ver-bus-table">'+
                        '<thead>'+
                            '<tr>'+
                                '<td>大区</td>'+
                                '<td>经理区</td>'+
                                '<td>主任区</td>'+
                                '<td>岗位</td>'+
                                '<td>人员</td>'+
                                '<td>销量目标</td>'+
                                '<td>实际完成销量</td>'+
                                '<td>完成率(%)</td>'+
                            '</tr>'+
                        '</thead>'
    		temp=temp+'<tbody>'
    		var number=0
               for(i in json.salesVolumeList){
               	   number++;
               	   if (number%2==0){
                   		temp=temp+'<tr class="double">';
                   }
                   else{
                   		temp=temp+'<tr >';
                   }	
                   temp=temp+'<td>'+json.salesVolumeList[i].bigArae+'</td>'+
                   '<td>'+json.salesVolumeList[i].manageArae+'</td>'+
                   '<td>'+json.salesVolumeList[i].dirArae+'</td>'+
                   '<td>'+json.salesVolumeList[i].agentArae+'</td>'+
                   '<td>'+json.salesVolumeList[i].agentName+'</td>'+
                     '<td>'+json.salesVolumeList[i].salesGoal+'</td>'+
                      '<td>'+json.salesVolumeList[i].salesVol+'</td>'+	
                      '<td>'+Math.round(json.salesVolumeList[i].finishPercent*10000)/100+'</td>'+	
					'</tr>'
               }
             temp=temp+'</tbody></table>';
              $('.table-container').append(temp)
             $("#pager").pager({ pagenumber: current_page, pagecount: json.totalPages, buttonClickCallback: PageClick });
			
		})
	}
	
 
	</script>			
</@override>
<@extends name="business_head.ftl"/> 