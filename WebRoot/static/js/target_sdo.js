$(document).ready(function(){
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
	  
	
	$('.month-picker').change(function(){
		$('.table-sdo').children().remove();
		$('#sdo-item').empty();
		$("#sdo-item").multiselect("refresh");
		$(".hidden").css('display','none')
	})
			
		$('#getsdo').click(function(){
		if( !$('.month-picker').val()){
			layer.msg('请选择日期！',{icon:7})
			return false;
		}	
		$('.table-sdo').children().remove();
		$('#sdo-item').empty();
		$("#sdo-item").multiselect("refresh");
		$(".hidden").css('display','none')
		 var year=$('.month-picker').val().split('-')[0];
	    var month=$('.month-picker').val().split('-')[1];	
		var t='<thead>'+
	            '<tr><td class="sdo-name sdo-title">月份</td><td class="sdo-title" colspan="{length}">'+month+'月份SDO明细表</td></tr>'+
	           '</thead>'+
	           '<tbody>'+
	           	'<tr><td class="sdo-name sdo-title">SDO项目</td>{sdoName}</tr>'+
	           	'<tr><td class="sdo-name sdo-title">执行标准或相应规定{sdoStandard}</td></tr>'+
	           	'<tr><td class="sdo-name sdo-title">计算方式与数据来源{sdoSoure}</td></tr>'+
	           	'<tr><td class="sdo-name sdo-title">目标数值{sdoTarget}</td></tr>'+	
	           	'</tbody>';
	  
	    var url='/Nirvana/backend/web/api/sdos?year={year}&month={month}';
	    url=url.replace('{year}',year).replace('{month}',month);
	    var loading=layer.msg('数据加载中，请稍等...', {icon: 16});
		$.getJSON(url,{},function(data){
					layer.close(loading);
					var info=data.data;
					var sdoName='';
					var sdoStandard='';
					var sdoSource='';
					var sdoTarget='';
					if(info==''){
						layer.msg('暂无数据',{icon:7});
						return false
					}
					$(".hidden").css('display','block')
	               	for(var i=0;i<info.length;i++){
	               		sdoName+='<td>'+info[i].name+'</td>';
	               		sdoStandard+='<td>'+info[i].criterion+'</td>';
	               		sdoSource+='<td>'+info[i].method+'</td>';
	               		sdoTarget+='<td>'+info[i].value+'</td>';
	               		$('#sdo-item').append("<option value='"+info[i].id+"'>"+info[i].name+"</option>");
	               	}
	               	t=t.replace('{sdoName}',sdoName)
	               	   .replace('{sdoStandard}',sdoStandard)
	               	   .replace('{sdoSoure}',sdoSource)
	               	   .replace('{sdoTarget}',sdoTarget)
	               	   .replace('{length}',info.length);
	               $('.table-sdo').append(t);
	               
	                setDirectorarea($('#position').val());
	                getCRSDO($('#position').val());
	               $('.hidden').css('display','block');	   
	            });       		 
		
		
		
		})
		
	            
	            
	     
		
		$('#position').change(function(){
			setDirectorarea($(this).val());
			SDOData=[];
			getCRSDO($(this).val())
		})
		
		$('#job').change(function(){
		 	getdeault($(this).val())
		})
		
		function setDirectorarea(id){
			$('#job').empty();
			var url='/Nirvana/backend/web/api/area/directorareas/{id}/agentareas';
			url=url.replace('{id}',id);
			$.getJSON(url,{},function(data){
					var info=data.data;
					$('#job').empty();
					for(var i=0;i<data.data.length;i++){
						$('#job').append('<option value="'+info[i].id+'">'+info[i].areaName+'</option>');  
					}
	        });
			
		}
		
		init();
		var SDOData=[]; 
		
		function init(){
			 $("#sdo-item").multiselect({
	            height: 120,
	            minWidth: 205,
	            checkAllText: '全选',
	            uncheckAllText: '全不选',
	            noneSelectedText: '---请选择---',
	            selectedText: '# 个选中',
	            selectedList: 2,
	            open: function (event, ui) {
	            }
	        });
		}
		
		
		$("#submit").click(function(){
			var array_of_checked_values = $("#sdo-item").multiselect("getChecked").map(function(){
	        	return this.value;
	    	}).get();
	    	var url="/Nirvana/backend/web/api/sdo/agentareas/{id}"
	    	alert($('#job').val());	
	    	url=url.replace('{id}',$('#job').val())
	    	 var loading=layer.msg('数据提交中，请稍等...', {icon: 16,shade: [0.8, '#393D49']});
	    	$.ajax({
	       			type:'PUT',
	       			url:url,
	       			data:{
	       				 sdoIds:JSON.stringify(array_of_checked_values),
		       			 year:$('.month-picker').val().split('-')[0],
		    			 month:$('.month-picker').val().split('-')[1]
	       			},
	       			success:function(data){
	       				layer.close("loading")
	       				if(data.response=='success'){
	       					layer.msg('分配成功',{icon:1})
	       					getCRSDO($('#position').val())
	       				}
	       				else{
	       					layer.msg(data.data.text,{icon:2})
	       				}
	       			}
	       		});
			
		})
		
		
		
		function getCRSDO(id){
			$('.target-table').children().remove();
	    	var temp=
	                '<thead style="background: #0066b3;color: #fff">'+
	                '<tr >'+
	                    '<td>类型</td>'+
	                    '<td>日期</td>'+
	                  
	                    '<td>执行者</td>'+
	                    '<td>SDO项目</td>'+
	                '</tr>'+
	                '</thead>'+
	                '<tbody>';
			var url="/Nirvana/backend/web/api/area/directorareas/{id}/sonsdos?year={year}&month={month}";
			var year=$('.month-picker').val().split('-')[0];
		    var month=$('.month-picker').val().split('-')[1];
		    url=url.replace('{year}',year).replace('{month}',month).replace('{id}',id);
			$.getJSON(url,{},function(data){
					  if (data.response=="success"){
							for(var i=0;i<data.data.length;i++){
								var sdos='';
								for (var j=0;j<data.data[i].sdos.length;j++){
								 	sdos=sdos+data.data[i].sdos[j].name
								}
							
					        temp=temp+'<tr><td>SDO目标</td>'+
			                   '<td>'+data.data[i].date+'</td>'+
			                   
			                    '<td>'+data.data[i].agentAreaName+'</td>'+
			                    '<td>'+sdos+'</td>'+
								'</tr>';
							}
							temp=temp+'</tbody>';	
					  		for(var i=0;i<data.data.length;i++){
					  			SDOData[data.data[i].agentAreaId]=data.data[i].sdos;
					  		}
					  		$('.target-table').append(temp);
					  		getdeault($('#job').val());
					  }
					  else{
						  layer.msg(data.data.text,{icon:2})
					  }
	        });
		}
		
		function getdeault(id){
			var data=SDOData[id];
		 	var sdo=[];
		 	if (data!=undefined){
				for(var i=0;i<data.length;i++){
					sdo.push(data[i].id);
				}
			}
			$('#sdo-item').val(sdo);
			$("#sdo-item").multiselect("refresh");
		}
		
		/*导入excel*/
		$('.month-picker').change(function(){
			setTimeout(function(){
			$("#file_upload").uploadify({
				'buttonText' : '上传文件',
				
				 'formData':{
					 'year':$('.month-picker').val().split('-')[0],
					 'month':$('.month-picker').val().split('-')[1]
				 },
		         'fileTypeDesc' : 'excel',
		         'fileTypeExts': '*.xls; *.xlsx',
		         swf           : '/Nirvana/static/uploadify.swf',
		         uploader      : '/Nirvana/backend/web/api/sdo',
		         fileObjName     : 'file',   
		         width         : 85,
		        'uploadLimit' : 5,
		         'fileSizeLimit':5000,
		         
		         'onUploadStart':function(file){
		         	layer.msg("正在上传文件,请稍等...",{icon: 16, shade:[0.8, '#393D49']})
		         },
		        'onUploadSuccess' : function(file, data, response) {
		     	  info=JSON.parse(data);
		     	   if (info.response=="success"){
		     		   layer.msg("上传成功!",{icon:1})
		     	   }
		     	   else{
		     		   layer.msg(info.data.text,{icon:2})
		     	   }
		     	   
		         },
		         'onUploadError' : function(file, errorCode, errorMsg, errorString) {
		         	 layer.msg("内部错误,请重试!",{icon:2})
		         }
		     });
			},10);
		})
		
})		