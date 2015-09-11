

<@override name="title">
业务管理
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
    .disable{
        background: #ebebeb;
        color: #909090;
        cursor:auto;
    }
    .contain{
        padding: 10px 20px;
        font-size: 14px;
    }
     .content-title{
        display: inline-block;
        width: 90px;
        text-align: right;
        margin-right: 10px;
    }
    .content-title{
        *display: inline;
    }
    label{
    	vertical-align:middle;
    }
</style>
</@override>

<@override name="content">
  <div class="right-slider">
            <div class="link-line" >
                <div class="href-block">分配工作<div class="arrow1"></div></div>
            </div>

            <div class="space-line">
            </div>

            <div class="contain">
                <div class="input-item">
                    <span class="content-title">小区号:</span><span>${realname}</span>
                </div>
                <div class="input-item">
                    <span class="content-title">本周拜访:</span>
                    <select id="week-visit" class="select-input" Onchange="gethasdir()" >
                    	<option value="1">星期一</option>
                    	<option value="2">星期二</option>
                    	<option value="3">星期三</option>
                    	<option value="4">星期四</option>
                    	<option value="5">星期五</option>
                    </select>
                </div>
                <div class="input-item">
                    <span class="content-title">添加拜访门店:</span>
                </div>

                <div style="padding: 0 15%">
                    <div class="clearfix">
                        <div style="float: left;width: 42%;">
                            计划拜访门店(顺序计划)
                            <div style="border: 1px solid #d3d3d3;height:300px;overflow-y: auto;padding:1% 3%">
                            	<div id="had_content">
                                <ul id="distribution">
                                   
                                </ul>
								</div>
                            </div>

                        </div>

                        <div style="float: left;padding:0 1%">
                            <div style="height: 300px;display: table">
				                <div style="display: table-cell;vertical-align: middle">
				                    <div>
				                    <img src="/Nirvana/static/img/pepsi/arrow-right.png" style="margin-bottom:10px" >
				                    </div>
				                    <div>
				                    <img src="/Nirvana/static/img/pepsi/arrow-left.png" >
				                    </div>
				                </div>
				            </div>
                        </div>

                        <div style="float: left;width: 42%;">
                            当前可分配门店
                            <div style="border: 1px solid #d3d3d3;height: 300px;overflow-y: auto;padding:1% 3%">
                            <div id="ud-content">
                                <ul id="undistribution">
                                  
                                </ul>
                             </div>   
                            </div>
                        </div>
                    </div>

                    <div style="padding: 20px 0;text-align: center">
                        <input type="button" value="确认添加" id="test" class="lg-button sure" >
                    </div>
                </div>
            </div>

        </div>
</@override>
<@override name="script">
	<script>
	var stores=new Array();
	$(document).ready(function(){
		gethasdir();
	})
	
	$('#test').click(function(){
		var title
		var template
		var $sotre = new Array();
		var $week=$('#week-visit').val()
		$store=[]
		 $('input[type="checkbox"][name="ud_chk"]:checked').each(
		 	function(){
		 		 title=$(this).parent().find('.title').html()
		 		template = '<li><input type="checkbox" name="chk" id="'+this.id+'"><label for="'+this.id+'" class="title">'+title+'</label></li>'
                $('#distribution').append(template)
                $(this).parent().remove()
		 	}
		 )
		 $('input[type="checkbox"][name="chk"]:checked').each(
		 	function(){
		 		 title=$(this).parent().find('.title').html()
		 		template = '<li><input type="checkbox" name="ud_chk" id="'+this.id+'"><label for="'+this.id+'" class="title">'+title+'</label></li>'
                $('#undistribution').append(template)
                $(this).parent().remove()
		 	}
		 )
		 
		  $('input[type="checkbox"][name="chk"]').each(
		  	function(){
		  		$store.push(this.id);
		  	}
		  )
		  var $json = new Object();
        	var b = new Object();
        	b.list = $store;
		 $.ajax({
   			type:'PUT',
   			url:'/Nirvana/backend/web/api/agents/allocateRoute',
   			datatype:'json',
   			data:{
   				agentAreaId:${agent.id},
   				storeIds:JSON.stringify(b),
   				routeCode:$week
   			},
   			success:function(data){
   				 if(data.response=="success"){
   				 	alert('添加成功')
   				 }
   				 else{
   				 	alert(data.errortext)
   				 }
   			}
   		})
	})
	
	function gethasdir(){
		$had=$('#had_content');
		var $week=$('#week-visit').val()
		var t='';
		$contain=$('#distribution');
		$contain.remove();
		t=t+'<ul id="distribution">'
		stores=[];
		$.getJSON('/Nirvana/backend/web/api/agents/director/VisitRouteNodes?crId=${agent.id}&routeCode='+$week, {}, function (json) {
				for(i in json.list){
					
					t=t+'<li>'+
                     '<input type="checkbox" name="chk" id="'+json.list[i].storeId+'">'+
                      '<label for="'+json.list[i].storeId+'" class="title">'+json.list[i].storeName+'</label>'
                      '</li>'
                      stores[json.list[i].storeId]=json.list[i].storeName
				}
				getuddir()
				t=t+'</ul>'
				$had.append(t);
		})
	}
	
	function getuddir(){
		var template='';
		$ud=$('#undistribution');	
		$content=$('#ud-content');	
		$ud.remove();
		template=template+'<ul id="undistribution">'
		$.getJSON('/Nirvana/backend/web/api/agents/stores?areaId=${agent.id}', {}, function (json) {
				for(i in json.stores){
					
					if (stores[json.stores[i].store_id]){						
	                    // template=template+'<input type="checkbox" disabled="true" name="ud_chk" id="'+json.stores[i].store_id+'">'	              
                     }
                     else{
                     	template=template+'<li>'+
	                    '<input type="checkbox" name="ud_chk" id="'+json.stores[i].store_id+'">'+
                     	'<label for="'+json.stores[i].store_id+'" class="title">'+json.stores[i].store_name+'</label>'+
	                      '</li>' 	
                     } 
                    
                      
				}
				template=template+'</ul>'
				$content.append(template);
		})		
	}
	</script>
</@override>
<@extends name="business_head.ftl"/> 