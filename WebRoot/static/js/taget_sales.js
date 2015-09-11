var mydate=new Date();
	var $selectYear=mydate.getFullYear();
	options = {
     pattern: 'yyyy-mm', // Default is 'mm/yyyy' and separator char is not mandatory
     selectedYear: $selectYear,
     startYear: $selectYear-1,
     finalYear: $selectYear+1,
     monthNames: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
 };
  $('.month-picker').monthpicker(options);
  
  
var url='';
var year='';
var month='';
var area='';
var areaId='';    
$('#getinfo').click(function(){
	if($('.month-picker').val()!=''){
		areaId=$('#position').val()
		if ($('#position').find("option:selected").attr('original')=='um'){
			var standardUrl='/Nirvana/backend/web/api/area/bigarea/'+areaId+'/goal';
			url='/Nirvana/backend/web/api/area/managerarea/goal';
			area='bigAreaId';
			
		}
		else if ($('#position').find("option:selected").attr('original')=='tdm'){
			var standardUrl='/Nirvana/backend/web/api/area/managerarea/'+areaId+'/goal';
			url='/Nirvana/backend/web/api/area/directorarea/goal';
			area='managerAreaId';
		}
		else if ($('#position').find("option:selected").attr('original')=='tds'){
			var standardUrl='/Nirvana/backend/web/api/area/directorarea/'+areaId+'/goal';
			url='/Nirvana/backend/web/api/area/agentarea/goal';
			area='directAreaId';
		}
		else{
			return false;
		}
		year=$('.month-picker').val().split('-')[0];
		month=$('.month-picker').val().split('-')[1];
		standardUrl=standardUrl+'?year='+year+'&month='+month;
		
		getlist(standardUrl);
	}
	else{
		layer.msg('请选择日期！',{icon:7})
	}
})
 
 

     
    function getlist(url){
    		$('#standrd').html('');
    		var loading=layer.msg('数据加载中，请稍等...', {icon: 16});
    		$('.target-table').remove()
    		var temp='<table class="target-table" >'+
                '<thead style="background: #0066b3;color: #fff">'+
                '<tr >'+
                    '<td>类型</td>'+
                    '<td>时间</td>'+
                    '<td>当月指标(￥)</td>'+
                    '<td>创建人</td>'+
                    '<td>执行者</td>'+
                    '<td>NR(￥)</td>'+
                '</tr>'+
                '</thead>'
                 
    	  	$.getJSON(url, {}, function (json) {
    	  		layer.close(loading);
    	  		if(json.response=="success"){
	    	  		$('#standrd').html(json.data.father.nr)
	    	  		temp=temp+'<tbody>'
	    	  		var info=json.data.sons==undefined?json.data.sonAreas:json.data.sons;
	    	  		for(i in info){
	    	  			 var areaId=info[i].areaId==undefined?info[i].id:info[i].areaId;
	    	  			 var val=  info[i].nr==undefined?'':info[i].nr;		
	    	  			 temp=temp+'<tr data-id="'+areaId+'"><td>NR目标</td>'+
		                   '<td>'+$('.month-picker').val()+	'</td>'+
		                   '<td>'+json.data.father.nr+'</td>'+
		                    '<td>'+$('#position option:selected').text()+'</td>'+
		                    '<td>'+info[i].areaName+'</td>'+
							'<td><input type="text" class="nrval" value="'+val+'"></td>'+
		               
							'</tr>'
						//$list.append("<option value='"+json.list[i].date+"'>"+json.list[i].date+"</option>");
									
	    	  		}
	    	  		
	    	  		 temp=temp+'</tbody></table>';
		              $('.table-container').append(temp)
	             }
	             else{
	            	 layer.msg(json.data.text,{icon:2});
	             } 
    	  	})
    	
    
    }
    
    
    $('#save').click(function(){
    	var data={}
    	$('.nrval').each(function(){
    		data[$(this).parent().parent().attr('data-id')]=$(this).val();
    	})
    	var info={};
    	info[area]=areaId;
    	info['year']=year;
    	info['month']=month;
    	info['nrs']=JSON.stringify(data);
    	$.ajax({
       			type:'PUT',
       			url:url,
       			data:info,
       			success:function(data){
       				if(data.response=="success"){
       					alert("保存成功!")
       				}
       				else{
       				 	alert(data.data.text)
       				} 
       			}
       	});
    })
    