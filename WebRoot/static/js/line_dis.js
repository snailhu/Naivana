
	var id="";
	var $job="";
	var type="";
	var shopcacheable=true;
	var dealercacheable=true;
	var shopList='';
	var dealerList='';
	var $lasttype='';
	$.ajaxSetup({
		cache:false
	})
	
	
setDirectorarea($('#position').val());
	
	$('#position').change(function(){
		setDirectorarea($(this).val());
	});
	
	function setDirectorarea(id){
		var url='/Nirvana/backend/web/api/area/directorareas/{id}/agentareas';
		url=url.replace('{id}',id)
		$('#job').empty();
		$.getJSON(url,{},function(data){
				var info=data.data;
				
				for(var i=0;i<data.data.length;i++){
					$('#job').append('<option value="'+info[i].id+'">'+info[i].areaName+'</option>');  
				}
        });
		
	}
	
	/*获取已分配路线*/
	function getline(){
		$("#undistribution").find('li').remove();
		$("#distribution").find('li').remove();
		
		var url='/Nirvana/backend/web/api/area/agentareas/{id}/visitroutes/{routeid}';
		url=url.replace('{id}',$job).replace('{routeid}',$(this).attr('id'));
		var loading=layer.msg('获取加载中，请稍等...', {icon: 16});
		$.getJSON(url,{},function(data){
					layer.close(loading);
					var info=data.data.nodes;
					var t='';
					if(!info)return false;
				    for(var i=0;i<info.length;i++){
				    	if (info[i].type=="DISTRIBUTE_STORE"){
	            			t=t+'<li><input type="checkbox" name="dis" id="shop'+info[i].store.id+'" data-type="storesnotin"><label for="shop'+info[i].store.id+'">'+info[i].store.storefrontInfo.name+'</label></li>';
						}
						if(info[i].type=="DIRECT_STORE"){
	            			t=t+'<li class="direct_store"><input type="checkbox" name="dis" id="Directshop'+info[i].dealer.id+'" data-type="dealersnotin"><label for="Directshop'+info[i].dealer.id+'">'+info[i].dealer.storefrontInfo.name+'</label></li>';
	            		}

	    			}
	    			$("#distribution").append(t);	
	    	});		
	}
	
	

	$("input[type=radio]").click(function(){
		$job=$('#job').val();
		id=$('input[name="line"]:checked').attr('id');
		shopcacheable=true;
		dealercacheable=true;
		
		if(id && $job){
			getline();
		}
		else{
			alert('请选择线路或职位!');
			layer.msg('请选择线路或职位',{icon:7})
		}
	})
	
	$('#job').change(function(){
		$job=$('#job').val();
		id=$('input[name="line"]:checked').attr('id');
		shopcacheable=true;
		dealercacheable=true;
		if(id && $job){
			getline();
		}
		else{
			
			layer.msg('请选择线路！',{icon:7})
		}
	
	});
	

	
	$('#query').click(function(){
		
		if(id){
			if($lasttype){
				if (type=="storesnotin"){
					shopList=$("#undistribution").children();
				}
				else if(type == "dealersnotin"){
					dealerList=$("#undistribution").children();
				}
				
			}
			$("#undistribution").find('li').remove();
			type=$("#shop-type").val();
			$lasttype=type;
			if (type=="storesnotin"){
				if (shopcacheable){
					var disUrl='/Nirvana/backend/web/api/area/agentareas/{id}/visitroutes/{routeid}/storesnotin';
					disUrl=disUrl.replace('{id}',$job).replace('{routeid}',id);
					/*获取不在线路中的门店*/
					var loading=layer.msg('数据加载中，请稍等...', {icon: 16});
					$.getJSON(disUrl,{},function(data){
						layer.close(loading);
						shopcacheable=false;
						createShopDom(data);
						
		        	});
				}
				else{
					$("#undistribution").append(shopList);
				}
			}
			else if(type == "dealersnotin"){
				if(dealercacheable){
					var disUrl='/Nirvana/backend/web/api/area/agentareas/{id}/visitroutes/{routeid}/dealersnotin';
					disUrl=disUrl.replace('{id}',$job).replace('{routeid}',id);
					
					/*获取不在线路中的直营店*/
					var loading=layer.msg('数据加载中，请稍等...', {icon: 16});
					$.getJSON(disUrl,{},function(data){
						layer.close(loading);
						dealercacheable=false;
						createDealerDom(data);
						
		        	});
				}
				else{
					$("#undistribution").append(dealerList);
				}
				
			}
			else{
				return false;
			}
			
		}
		else{
			layer.msg('请选择拜访线路！',{icon:7})
		}
	});
	
	
	/*添加未分配门店*/
	function createShopDom(data){
		var info=data.data.data;
		var t='';
		if(!info)return false;
	    for(var i=0;i<info.length;i++){
    		t=t+'<li><input type="checkbox" name="undis" id="shop'+info[i].store_id+'" data-type="storesnotin"><label for="shop'+info[i].store_id+'">'+info[i].store_name+'</label></li>';
    	}
    	$("#undistribution").append(t);
	}
	
	/*添加未分配直营店*/
	function createDealerDom(data){
		var info=data.data.data;
		var t='';
		if(!info)return false;
	    for(var i=0;i<info.length;i++){
    		t=t+'<li><input type="checkbox" name="undis" id="Directshop'+info[i].id+'" data-type="dealersnotin"><label for="Directshop'+info[i].id+'">'+info[i].storefrontInfo.name+'</label></li>';
    	}
    	$("#undistribution").append(t);
	}
	
	$("#save").click(function(){
	
		var url="/Nirvana/backend/web/api/area/agentareas/{id}/visitroutes/{routeid}"
		url=url.replace('{id}',$job).replace('{routeid}',id);
		var json=[];
		
		$('input[name="dis"]').each(function(){
			
			var data={};
			if ($(this).attr('data-type')=="storesnotin"){
				data.id=$(this).attr('id').replace('shop','');
				data.type="DISTRIBUTE_STORE";
			}
			else if($(this).attr('data-type') == "dealersnotin"){
				data.id=$(this).attr('id').replace('Directshop','');
				data.type="DIRECT_STORE";
			}
			
			json.push(data);

		})
		var loading=layer.msg('数据提交中，请稍等...', {icon: 16,shade: [0.8, '#393D49']});
		$.ajax({
                type:"POST",
                url:url,
                datatype:"json",
                data:{
                	nodesets:JSON.stringify(json)
                },
                success:function(data){
                	layer.close(loading);
                	if(data.response=="success"){
                		layer.msg('分配成功！',{icon:1})
                	}
                	else{
                		layer.msg(data.data.text,{icon:2})
                	}
                },
                error:function(data){
                	layer.msg('内部错误,请重试！',{icon:2})
                    return false;
                }
            });
	})
	
