(function(){
$(document).ready(function(){
   		
        $('.more').dropMenu({
             event:[
                 {
                     content:'编辑用户',
                     callback:function(id){
                         window.location.href="/Nirvana/backend/web/customer_management/agency/"+id+"/detail";
                     }
                 },
                 {
                 	content:'分配门店',
                 	callback:function(id){
                 		var addList=[],
                 			removeList=[];
                 		var pageii=layer.open({
			                type: 1,   //0-4的选择,（1代表page层）
			                area: ['600px', '500px'],
			                shade: [0.5,'#000'],  //不显示遮罩
			                shadeClose:true,
			                border: [0], //不显示边框
							move:false,
			                title: [
			                    '给分配门店',
			                    //自定义标题风格，如果不需要，直接title: '标题' 即可
			                    'border-bottom;1px solid #d3d3d3;font-size:18px '
			                ],
			                bgcolor: '#fff', //设置层背景色
			                content: '<div class="ui-dis-wrap">' +
			                    '<div class="ui-dis-left-slider">'+
			                    '<div class="ui-dis-title"><label style="line-height: 40px">已管理门店</label></div>'+
			                    '<div id="dis" class="ui-dis-content">'+
			                    '<ul id="distribution">'+
			                    '</ul>'+
			                    '</div>'+
			                    '</div>'+
		                                '<div style="position: absolute;top: 32%;left: 47%">'+
		                                '<input type="button" value="<" id="single-dis" class="button-dis"></br>'+
		                                '<input type="button" value="<<" id="all-dis" class="button-dis"></br>'+
		                                '<input type="button" value=">" id="single-undis" class="button-dis"></br>'+
		                                '<input type="button" value=">>" id="all-undis" class="button-dis"></br>'+
		                                '</div>'+
			                    '<div class="ui-dis-right-slider">'+
			                    '<div  class="ui-dis-title"><label style="line-height: 40px">经销商和门店</label>' +
			                    '<div style="height:30px;border: 1px solid #d3d3d3;width: 140px;float: right;margin:4px 0">'+
			                    '<input type="text" style="width: 120px;height: 28px;border: 0;outline: 0;">'+
			                    '<img src="/Nirvana/static/img/pepsi/serch.png" style="vertical-align: middle;">'+
			                    '</div>'+
			                    '</div>'+
			                    '<div id="undis" class="ui-dis-content">'+
			                    '<ul id="undistribution">'+
			                    '</ul>'+
			                    '</div>'+
			                    '</div>'+
			                    '</div>'+
			                    '<div style="text-align:center;"><span id="layer-errorMessage"></span></div>'
			                ,
			                btns:2,
			                btn:['确定','取消'],
			                yes:function() {
			                	if(removeList=='' && addList==''){
			                		layer.close(pageii);
			                	}
			                	else{
				                	var url='/Nirvana/backend/web/api/dealers/'+id+'/allot';
				                	$('#layer-errorMessage').html("数据提交中,请稍等...")
				                  	 $.ajax({
						                type:"POST",
						                url:url,
						                datatype:"json",
						                data:{
						                	outIds:JSON.stringify(removeList),
						                	inIds:JSON.stringify(addList)
						                },
						                success:function(data){
						                	if(data.response=="success"){
						                		layer.close(pageii);
						                		
						                		layer.msg("分配成功!",{icon:1})
						                	}
						                	else{
						                		$('#layer-errorMessage').html(data.data.text)
						                	}
						                },
						                error:function(data){
						                    return false;
						                }
						            });
						         }   
			                },
			                shift: 'top' //从上动画弹出
			            });
			            url='/Nirvana/backend/web/api/stores/notalloted';
			            $('#layer-errorMessage').html("数据加载中,请稍等...")
			            $.getJSON(url,{},function(data){
			            	$('#layer-errorMessage').html("")
			            	var t='';
			           		for(var i=0;i<data.data.length;i++){
            					t=t+'<li><input type="checkbox" name="undis" id="'+data.data[i].store_id+'"><label for="'+data.data[i].store_id+'">'+data.data[i].store_name+'</label></li>';
            				}
            				
            				$("#undis").find('ul').append(t);
            				 $('#single-dis').click(function(){
						    	$('input[type=checkbox][name=undis]:checked').each(function(){
						            $('#distribution').append('<li>'+$(this).parent().html().replace('undis','dis')+'</li>');
						             
						             var dx=removeList.indexOf($(this).attr('id'));
						             if(dx>=0){
						             	removeList.splice(dx,1);
						             }
						             else
						             {
						             	addList.push($(this).attr('id'));
						             }
						            $(this).parent().remove();
						           
						        });
						    });
						    
						    
						
						    $('#all-dis').click(function(){
						        $('input[type=checkbox][name=undis]').each(function(){
						            $('#distribution').append('<li>'+$(this).parent().html().replace('undis','dis')+'</li>');
						            var dx=removeList.indexOf($(this).attr('id'));
						             if(dx>=0){
						             	removeList.splice(dx,1);
						             }
						             else
						             {
						             	addList.push($(this).attr('id'));
						             }
						            $(this).parent().remove();
						            
						        });
						    });
						
						    $('#single-undis').click(function(){
						        $('input[type=checkbox][name=dis]:checked').each(function(){
						            $('#undistribution').append('<li>'+$(this).parent().html().replace('dis','undis')+'</li>');
						            var dx=addList.indexOf($(this).attr('id'));
						             if(dx>=0){
						             	addList.splice(dx,1);
						             }
						             else
						             {
						             	removeList.push($(this).attr('id'));
						             }
						            $(this).parent().remove();
						        });
						    });
						
						    $('#all-undis').click(function(){
						        $('input[type=checkbox][name=dis]').each(function(){
						            $('#undistribution').append('<li>'+$(this).parent().html().replace('dis','undis')+'</li>');
						            var dx=addList.indexOf($(this).attr('id'));
						             if(dx>=0){
						             	addList.splice(dx,1);
						             }
						             else
						             {
						             	removeList.push($(this).attr('id'));
						             }
						             $(this).parent().remove();
						        });
						        
						    });
            			});
            			
            			url1='/Nirvana/backend/web/api/dealers/'+id+'/stores';
            			$.getJSON(url1,{},function(data){
            				var t='';
			           		for(var i=0;i<data.data.length;i++){
            					t=t+'<li><input type="checkbox" name="dis" id="'+data.data[i].store_id+'"><label for="'+data.data[i].store_id+'">'+data.data[i].store_name+'</label></li>';
            				}
            				$("#dis").find('ul').append(t);
            			});
            			
			          }
			       }
             ]
        });
    });
    
 })();   