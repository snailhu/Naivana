 $(document).ready(function(){
	 $.ui.dynatree.nodedatadefaults = {
			 icon:'/Nirvana/static/img/pepsi/lock.png',
	 }
    $("#tree").dynatree({
        selectMode: 2,
        children: {title: "全部区域", key: "all-0","isLazy": true,icon:false},
       onLazyRead: function(node){
       	var type=node.data.key.split('-')[0];
       	var url='';
       	switch  (type)
       	{
       	case 'all':
       		url='/Nirvana/maindata/api/bigareas';
       		break;
       	case 'bigArea':
       		url='/Nirvana/maindata/api/bigareas/{id}/managerareas';
       		break;
       	case 'managerArea':
       		url='/Nirvana/maindata/api/managerareas/{id}/directorareas';
       		break;
       	case 'directorArea':
       		url='/Nirvana/maindata/api/directorareas/{id}/agentareas';
       		break;		  					
       	}
       	
       	url=url.replace('{id}',node.data.key.split('-')[1]);
           node.appendAjax({url: url,
                             });
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
        	var position=node.data.key.split('-')[0];
        	$("#closenode").addClass("hide");
        	$("#activenode").addClass("hide");
        	if (node.data.icon){
        		
        		$("#activenode").removeClass("hide");
        	}
        	else{
        		$('#closenode').removeClass("hide");
        	}
        	
        },
        onKeydown: function(node, event) {
            if( event.which == 32 ) {
                node.toggleSelect();
                return false;
            }
        },
        	dnd: {
			onDragStart: function(node) {
				return true;
			},
			autoExpandMS: 1000,
			onDragEnter: function(node, sourceNode,hitMode) {
				var sourcekey=sourceNode.data.key.split('-')[0],
					key=node.data.key.split('-')[0];
				if(key=='bigArea' && sourcekey == 'managerArea'){
					return true;
				}
				else if(key == 'managerArea' && sourcekey == 'directorArea'){
					return true;
				}
				else if(key == 'directorArea' && sourcekey == 'agentArea'){
					return true;
				}
				else{
					return false;
				}
			},
			onDragOver: function(node, sourceNode, hitMode) {

				if( hitMode === "after" ||hitMode === "before" ){
					return false;
				}
			},
			onDrop: function(node, sourceNode, hitMode, ui, draggable) {
				var sourcekey=sourceNode.data.key.split('-')[0],
					key=node.data.key.split('-')[0];
				if(key=='bigArea' && sourcekey == 'managerArea'){
					var url="/Nirvana/backend/web/api/managerareas/{areaId}/move";
					var data={bigAreaId:node.data.key.split('-')[1]};
					
				}
				else if(key == 'managerArea' && sourcekey == 'directorArea'){
					var url="/Nirvana/backend/web/api/directareas/{areaId}/move";
					var data={managerAreaId:node.data.key.split('-')[1]};
				}
				else if(key == 'directorArea' && sourcekey == 'agentArea'){
					var url="/Nirvana/backend/web/api/agentareas/{areaId}/move";
					var data={directAreaId:node.data.key.split('-')[1]};
				}
				else{
					return false;
				}
				url=url.replace('{areaId}',sourceNode.data.key.split('-')[1])
				$.ajax({
		                type:'PUT',
		                url:url,
		                data:data,
		                success:function(data){
		                	if(data.response=='success'){
		            				layer.msg("移动成功！",{icon:1})
		                    		sourceNode.move(node, hitMode);
		                    }
		                    else{
		                    	layer.msg(data.data.text,{icon:2})
		                    }
		                },
		                error:function(data){
		    				layer.msg("内部错误，请重试!",{icon:2})
		                }
				})
				
			},
		}
    });
    
    $('#addnode').click(function(){
		var node = $("#tree").dynatree("getActiveNode");
		if (node==null){
			layer.msg("请选择一个节点!",{icon:7})
			return false;
		}
		var temp='';
		var position=node.data.key.split('-')[0];
		var id=node.data.key.split('-')[1];
	
		if (position == 'agentArea'){
			layer.msg("该区域下无法添加新的职位!",{icon:7})

			return false;
		}
		else{
			if(position=="bigArea"){
				var title='添加经理区';
				var url='/Nirvana/backend/web/api/managerareas';
				var type="managerArea";
			}
			else if (position == "managerArea"){
				var title='添加主任区';
				var url='/Nirvana/backend/web/api/directareas';
				var type="directorArea";
			}
			else if(position == "directorArea"){
				var title='添加小区';
				var url='/Nirvana/backend/web/api/agentareas';
				var type="agentArea";
				 $.getJSON('/Nirvana/backend/web/api/worktypes',{},function(data){
					 temp='<div class="edit-item"><span class="edit-title">区域类型:</span><select id="workType" class="select-input">';
					 for(var i=0;i<data.data.length;i++){
							temp=temp+'<option value="'+data.data[i]+'">'+data.data[i]+'</option>';
						}
					 temp=temp+'</select></div>';
					 alertBox(title,temp,url,id,node,type);
	            });
				 return false;
			}
			else if(position == "all"){
				var title='添加大区';
				var url='/Nirvana/backend/web/api/bigareas';
				var type="bigArea";
			}
			else{
				return false;
			}
			alertBox(title,temp,url,id,node,type);
			
			
		}
		
	})
    
	
	$('#closenode').click(function(){
		var node = $("#tree").dynatree("getActiveNode");
		if (node==null){
			layer.msg("请选择一个节点!",{icon:7})
			return false;
		}
		var position=node.data.key.split('-')[0];
		var id=node.data.key.split('-')[1];
		console.log(node.data)
		if(position=="bigArea"){
			var title='关闭大区';
			var url='/Nirvana/backend/web/api/bigareas/{id}/close';
		}
		else if (position == "managerArea"){
			var title='关闭经理区';
			var url='/Nirvana/backend/web/api/managerareas/{id}/close';
		}
		else if(position == "directorArea"){
			var title='关闭主任区';
			var url='/Nirvana/backend/web/api/directareas/{id}/close';
		}
		else if(position == "agentArea"){
			var title='关闭小区';
			var url='/Nirvana/backend/web/api/agentareas/{id}/close';
		}
		else if(position == "all"){
			layer.msg("此节点不能关闭",{icon:7})
			return false;
		}
		else{
			return false;
		}
		url=url.replace("{id}", id);
		layer.confirm('是否关闭'+node.data.title+'区域', {icon: 3, title:'提示'}, function(index){
			$.ajax({
                type:'PUT',
                url:url,
                success:function(data){
                	if(data.response=='success'){
			           
						layer.close();
						layer.msg("关闭成功!",{icon:1})
						node.getParent().reloadChildren();
                    }	
                    else{
                    	layer.msg(data.data.text,{icon:7})
                    }
                },
                error:function(data){
                	layer.msg("内部错误，请重试!",{icon:2})
                }
            });	
		});
	})
	
	function alertBox(title,temp,url,id,node,type){
    	 var page=layer.open({
             type: 1,   //0-4的选择,（1代表page层）
             area: ['500px', '300px'],
             shade: [0.5,'#000'],  //不显示遮罩
             shadeClose:true,
             border: [0], //不显示边框

             title: [
                 title,
                 //自定义标题风格，如果不需要，直接title: '标题' 即可
                 'border-bottom;1px solid #d3d3d3;font-size:18px '
             ],
             bgcolor: '#fff', //设置层背景色
             content: '<div style="padding:10px 40px;color:#909090;width: 400px;">' +
                 '<div class=edit-item><span class="edit-title"></span><span></span></div>'+
                 '<div class="edit-item"><span class="edit-title">区域名称:</span><input type="text" id="area" value=></div>'+
               '<div class="edit-item"><span class="edit-title">区域编码:</span><input type="text" id="code" value=></div>'+temp+
                 '<div style="color: red;text-align: center;padding-top: 10px"><span id="layer-errorMessage" ></span></div>'+
                 '</div>'
             ,
             btns:2,
             btn:['确定','取消'],
             yes:function(){
             	$.ajax({
		                type:'POST',
		                url:url,
		                data:{
		                	name:$('#area').val(),
		                	code:$('#code').val(),
		                	type:$('#workType').val(),
		                	fatherId:id
		                },
		                success:function(data){
		                	if(data.response=='success'){
					             var childNode = node.addChild({
								    title: $('#area').val(),
								
									key:type+'-'+data.data.id
								});
								layer.close(page);
								layer.msg("添加成功!",{icon:1})
		                    }
		                    else{
		                    	$("#layer-errorMessage").html(data.data.text)
		                    }
		                },
		                error:function(data){
		                    $("#layer-errorMessage").html("内部错误，请重试！")
		                }
		            });	
                
             },
             shift: 'top' //从上动画弹出
         });
    }
	
    
    $('#activenode').click(function(){
		var node = $("#tree").dynatree("getActiveNode");
		if (node==null){
			layer.msg("请选择一个节点!",{icon:7})
			return false;
		}
		var position=node.data.key.split('-')[0];
		var id=node.data.key.split('-')[1];
		console.log(node.data)
		if(position=="bigArea"){
			var title='激活大区';
			var url='/Nirvana/backend/web/api/bigareas/{id}/activate';
		}
		else if (position == "managerArea"){
			var title='激活经理区';
			var url='/Nirvana/backend/web/api/managerareas/{id}/activate';
		}
		else if(position == "directorArea"){
			var title='激活主任区';
			var url='/Nirvana/backend/web/api/directareas/{id}/activate';
		}
		else if(position == "agentArea"){
			var title='激活小区';
			var url='/Nirvana/backend/web/api/agentareas/{id}/activate';
		}
		else if(position == "all"){
			layer.msg("此节点不能关闭",{icon:7})
			return false;
		}
		else{
			return false;
		}
		url=url.replace("{id}", id);
		layer.confirm('是否激活'+node.data.title+'区域', {icon: 3, title:'提示'}, function(index){
			$.ajax({
                type:'PUT',
                url:url,
                success:function(data){
                	if(data.response=='success'){
			           
						layer.close();
						layer.msg("激活成功!",{icon:1})
						node.getParent().reloadChildren();
                    }
                    else{
                    	layer.msg(data.data.text,{icon:7})
                    }
                },
                error:function(data){
                	layer.msg("内部错误，请重试!",{icon:2})
                }
            });	
		});
	})
})  
    