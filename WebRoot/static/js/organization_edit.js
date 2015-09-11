(function(){
$.ajaxSetup({
	cache:false
})

$('#position-line').delegate('.close-ico','click',function(){
	var tip="确定要删除"+$(this).parent().find('.user-content').html()+"职位吗?";
	var positionId=$(this).parent().attr('data-id')
	layer.confirm(tip, {icon: 3}, function(index){
	    //do something
		var url="/Nirvana/backend/web/api/employees/{employee}/positions/{positionId}";
		url=url.replace('{employee}',employeeId);
		url=url.replace('{positionId}',positionId);
		var $elem=$(this).parent();
		var loading=layer.msg('数据提交中，请稍等...', {icon: 16,shade: [0.8, '#393D49']});
		$.ajax({
	                type:'DELETE',
	                url:url,
	                success:function(data){
	                	layer.close(loading);
	                	if(data.response=='success'){
	                    	layer.msg("删除成功",{icon:1})
	                    	getPositionInfo();
	                    }
	                    else{
	                    	layer.msg(data.data.text,{icon:2})

	                    }
	                },
	                error:function(data){
	                	layer.close(loading);
	                    layer.msg("内部错误请重试!",{icon:2})
	                }
	            });
	})
});	
	
$('.close-ico').click(function(){
		
	})
   
 $(document).ready(function(){
	 getPositionInfo();
    $("#tree").dynatree({
        selectMode: 2,
//    	 children: [
//       				{title: "全部职位", key: "all-0","isLazy": true,unselectable: true,hideCheckbox:true},
//       			],
        children:{title: "全部职位", key: "all-0","isLazy": true,unselectable: true,hideCheckbox:true},
       	checkbox:true,	
        onLazyRead: function(node){
        	var type=node.data.key.split('-')[0];
        	var areaid=node.data.key.split('-')[1];
        	var url='';
        	var res=[];
        	switch  (type)
        	{
        	case 'all':
        		url='/Nirvana/maindata/api/bigareas';
        		 res.push({title: "GM",key: "GM-"+areaid });
        		 res.push({title: "资讯",key:"INFO_MINISTRY-"+areaid });
        		 res.push({title: "SISM",key:"SISM-"+areaid});
        		 res.push({title: "MDM",key:"MDM-"+areaid });
        		 res.push({title: "CDM" ,key:"CDM-"+areaid});
        		 res.push({title: "SIS",key:"SIS-"+areaid });
        		break;
        	case 'bigArea':
        		  res.push({title: "UM",key:"UM-"+areaid  });
        		  res.push({title: "FSIS",key:"FSIS-"+areaid  });
        		 
        		url='/Nirvana/maindata/api/bigareas/{id}/managerareas';
        		break;
        	case 'managerArea':
        		 res.push({title: "TDM",key:"TDM-"+areaid  });
        		 res.push({title: "文员",key:"CLERK-"+areaid  });
        		url='/Nirvana/maindata/api/managerareas/{id}/directorareas';
        		break;
        	case 'directorArea':
        	 	res.push({title: "TDS",key:"TDS-"+areaid  });
        		url='/Nirvana/maindata/api/directorareas/{id}/agentareas';
        		break;		  					
        	}
        	
        	url=url.replace('{id}',node.data.key.split('-')[1]);
        	
        	function addnode(node,res){
        		node.addChild(res);
        	}
            node.appendAjax({url: url,
                              },addnode,res);
           
            
        	             
        },
    
        onSelect: function(select, node) {
            // Display list of selected nodes
            var selNodes = node.tree.getSelectedNodes();
            // convert to title/key array
            var selKeys = $.map(selNodes, function(node){
                return "[" + node.data.key + "]: '" + node.data.title + "'";
            });
        },
        onClick: function(node, event) {
            // We should not toggle, if target was "checkbox", because this
            // would result in double-toggle (i.e. no toggle)
            if( node.getEventTargetType(event) == "title" )
                node.toggleSelect();
        },
        onKeydown: function(node, event) {
            if( event.which == 32 ) {
                node.toggleSelect();
                return false;
            }
        }
    });
    
    
})  


	$('#edit').click(function(){
		var nodes=$("#tree").dynatree("getSelectedNodes");
		var json=[];
		for(var i=0;i<nodes.length;i++){
			var data={};
			data["type"]=nodes[i].data.key.split('-')[0]=='agentArea'?'AGENT':nodes[i].data.key.split('-')[0];
			data["areaId"]=nodes[i].data.key.split('-')[1];
			json.push(data);
		}
		var url="/Nirvana/backend/web/api/employees/{employee}/positions";
		url=url.replace('{employee}',employeeId);
		if (json.length>0){
			var loading=layer.msg('数据提交中，请稍等...', {icon: 16,shade: [0.8, '#393D49']});
			$.ajax({
		                type:'POST',
		                url:url,
		                data:{
		                	datas:JSON.stringify(json)
		                },
		                success:function(data){
		                	layer.close(loading)
		                	if(data.response=='success'){
		                    
		                    	layer.msg("添加成功!",{icon:1})
		                    	getPositionInfo();
		                	}	
		                    else{
	
		                    	layer.msg(data.data.text,{icon:2})
		                    }
		                },
		                error:function(data){
		                	layer.close(loading)
		                    layer.msg("内部错误请重试!",{icon:2})
		                }
		            });
		}
		else{
			layer.msg("请选择要添加的职位!",{icon:7})
		}
	});
	
$('#save').click(function(){
		var ecode=$('#ecode').val(),
			pwd=$('#pwd').val(),
			repwd=$('#repwd').val(),
			phone=$('#phone').val(),
			isclose=$('#isclose').val(),
			name=$('#name').val(),
			url='/Nirvana/backend/web/api/employees/{id}';
			url=url.replace('{id}',employeeId);
			re=/^1[3|5|7|8][0-9]{9}$/;
        if(!re.exec(phone)){

            layer.msg("手机号码格式不正确！",{icon:7})
            $('#phone').val('');
            return false
        }
			if(repwd!=pwd){
				layer.msg("两次输入的密码不一致!",{icon:7})
				return false;
			}
			if(ecode && phone && isclose && name){
				var loading=layer.msg('数据提交中，请稍等...', {icon: 16,shade: [0.8, '#393D49']});
				$.ajax({
                type:'PUT',
                url:url,
                dataType: "json",
                data:{
                   password:pwd,
                   realName:name,
                   eCode:ecode,
                   phoneNum:phone,
                   close:isclose,
                },
                success:function(data){
                	layer.close(loading);
                	if (data.response=="success"){
						layer.msg("修改成功!",{icon:1})

                	}
                	else{
                		alert(data.data.text)
        				layer.msg(data.data.text,{icon:2})

                	}
                },
                error:function(data){
                	layer.msg("内部错误，请重试！",{icon:2})
                    return false;
                }
            });	
			}
			else{
				layer.msg("请将内容填写完整！",{icon:7})

			}
   		

})

	function getPositionInfo(){
		$('#position-line').children().remove();
		var url='/Nirvana/backend/web/api/position/{id}';
		var template='<span class="suffix-inline user-lab" data-id="{positionId}">'+
        	'<span class="user-content">{content}</span>'+
		'<span  class="suffix-inline close-ico"></span></span>';
		$.getJSON(url.replace('{id}',employeeId),{},function(data){
				$('#position-count').html('已有职位('+data.data.length+')');
				for(var i=0;i<data.data.length;i++){
					var t=template.replace('{positionId}',data.data[i].id).replace('{content}',data.data[i].positionName);
					$('#position-line').append(t);
				}
        });
	}
	
	
	
})();	