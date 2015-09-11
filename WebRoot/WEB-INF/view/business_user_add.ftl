

<@override name="title">
业务管理
</@override>

<@override name="link">
 	<script src="/Nirvana/static/js/layer/layer.js" type="text/javascript"></script>
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
        width: 100%;
        padding: 30px;
    }
    .container input[type=text],.container input[type=password]{
        width:30% ;
        height: 30px;
    }
    .info-label{
        display: inline-block;
        width: 12%;
    }
    .footer{
        padding-top: 30px;
        padding-left: 15%;
    }
</style>
</@override>

<@override name="content">
 <div class="right-slider">
            <div class="link-line clearfix">
                <a class="float-hack" href="/Nirvana/backend/web/business_management/user_list"><div class="href-block" >小区列表</div></a>
                <a href="/Nirvana/backend/web/business_management/user_add" class="float-hack"><div class="href-block" >添加新小区<div class="arrow1"></div> </div></a>
                <!--
                <div class="search-wrap">
                    <div class="hack">
                        <div class="cnt">
                            <div class="group-input search-box">
                                <label for="search" class="placeholder">区或客户编码</label>
                                <input type="text" id="search" class="form-control">
                                <img src="/Nirvana/static/img/pepsi/serch.png">
                            </div>
                        </div>
                    </div>
                </div>
                -->
            </div>
            
            <div class="space-line">

            </div>

            <div class="container">
                 
                <div class="input-item">
                    <span class="info-label">小区号:</span>
                    <input type="text" id="code">
                </div>
                
                <div class="input-item">
                    <span class="info-label">小区名称:</span>
                    <input type="text" id="name">
                </div>
                  <div class="input-item">
                    <span class="info-label">小区类型:</span>
                    <select id="workType" class="select-input"><#list workType as type><option value="${type}">${type}</option></#list></select>
                </div>
                 <div class="input-item">
                    <span class="info-label">区域选择:</span>
                    <span>大区</span><select id="bigarea" class="select-input left"><#list bigAreas as bigArea><option value="${bigArea.id}">${bigArea.name}</option></#list></select>
                    <span class="left">经理区</span><select id="managerarea" class="select-input left"></select>
                    <span class="left">主任区</span><select id="directorarea" class="select-input left"></select>
                </div>
                <div class="footer">
                    <input type="button" id='submit' class="button sure" value="确认添加">
                </div>
            </div>

        </div>
</@override>
<@override name="script">
<script>
    setManageArea($('#bigarea').val());
	
	$('#bigarea').change(function(){
		setManageArea($(this).val());
	})
	
	$('#managerarea').change(function(){
		setDirectorarea($(this).val());
	
	})
	
	function setManageArea(id){
		url='/Nirvana/backend/web/api/area/bigareas/{id}/managerareas';
		url=url.replace('{id}',id);
		$.getJSON(url,{},function(data){
				var info=data.data;
				$('#managerarea').empty();
				for(var i=0;i<data.data.length;i++){
					$('#managerarea').append('<option value="'+info[i].id+'">'+info[i].areaName+'</option>');  
				}
				setDirectorarea($('#managerarea').val());
            });
	}
	
	function setDirectorarea(id){
		url='/Nirvana/backend/web/api/area/managerareas/{id}/directareas';
		url=url.replace('{id}',id);
		$.getJSON(url,{},function(data){
				var info=data.data;
				$('#directorarea').empty();
				for(var i=0;i<data.data.length;i++){
					$('#directorarea').append('<option value="'+info[i].id+'">'+info[i].areaName+'</option>');  
				}
        });
		
	}
	
	$('#submit').click(function(){
		url='/Nirvana/backend/web/api/area/agentareas';
		$code=$('#code').val();
		$name=$('#name').val();
		$id=$('#directorarea').val();
		if($code && $name && $id){
			var loading=layer.msg('数据提交中,请稍等...', {icon: 16,shade: [0.8, '#393D49']});
			$.ajax({
       			type:'POST',
       			url:url,
       			data:{
       				areaCode:$code,
       				directorAreaId:$id,
       				areaName:$name,
       				type:$('#workType').val()
       			},
       			success:function(data){
       				layer.close(loading);
       				 if(data.response == 'success'){
       				 	layer.msg('添加成功!！',{icon:1})
       				 	$('#code').val('');
       				 	$('#name').val('');
       				 	
       				 }
       				 else if(data.response == 'error'){
       				 	layer.msg(data.data.text,{icon:2});
       				 }
       			},
       			error:function(){
       				layer.msg('内部错误,请重试！',{icon:2})
       			}
       		});
		}
		else{
			layer.msg('请完成所有的内容！',{icon:7})
		}
	})
</script>
</@override>
<@extends name="business_head.ftl"/> 