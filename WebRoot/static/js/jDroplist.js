/**
 * Created by 2014 on 2015/2/12.
 */

(function($){
    var dropList=function(elem,options){
        var _this=this;
        this.elem = $( elem );
        this.listElem={};
        this.options = options;
        this.id=0;
        this.flag=true;
        this.info=[];
        this.name=null;
        this.tel=null;
        this.pwd=null;
        this.rePwd=null;
        this.layer={};
        this.thumList=[];
        this.thubList=[];
        this.page=1;
        this.totalPages=0;
        this.shopType='';
        this.removeList=[];
        this.addList=[];
        this.loading={};
        this.disCommitUrl='/Nirvana/backend/web/api/area/agentareas/{id}/allotdealer';
        _this._init();
        //this.elem.find('img').bind('click', function (event) { event.preventDefault(); _this._init(); return false; });
    };
    dropList.prototype={
        defaults:{
        	editType:'PUT',
	        editUrl:'/Nirvana/backend/web/api/area/agentareas/{id}/edit',
            closeUrl:'/Nirvana/backend/web/api/agents/{id}/close',
            closeType:'PUT',
            url:'/Nirvana/backend/web/api/agents?page={page}&size={size}',
            activeUrl:'/Nirvana/backend/web/api/area/agentareas/{id}/active',
            activeType:'PUT',		
            dealersUrl:'/Nirvana/backend/web/api/area/agentareas/{id}/dealers/both',
            directstoresUrl:'/Nirvana/backend/web/api/area/agentareas/{id}/directstores/both',
            storesUrl:'/Nirvana/backend/web/api/area/agentareas/{id}/stores/both ',
            allotdealerUrl:'/Nirvana/backend/web/api/area/agentareas/{id}/allotdealer',
            allotdirectstoresUrl:'/Nirvana/backend/web/api/area/agentareas/{id}/allotdirectstores',
            allotstoresUrl:'/Nirvana/backend/web/api/area/agentareas/{id}/allotstores',
            tableText:[],
            dataText:['nameCode','realName','phone'],
            size:5,
            userText:'用户',
            showName:'nameCode',
            close:false,
            edit:false,
            dis:false,
            more:true,
            id:'id',
            callback:{}
        },
        
        _init:function(){
        	$.ajaxSetup ({ 
        		cache: false //close AJAX cache 
        	});
            this.config= $.extend({},this.defaults,this.options);
            this._getInfoJson();
            this._bindEvent();
            
        },
        
        /*获取table数据并创建DOM元素*/
        _getInfoJson:function(){
            var _this=this,
                url=this.config.url.replace('{page}',_this.page).replace('{size}',_this.config.size);
            _this.loading=layer.msg('加载中...', {icon: 16});
            $.getJSON(url,{},function(data){
            		layer.close(_this.loading)
	            	if(data.data.length==0 && _this.page>1){
	            		_this.page--;
	            		_this._getInfoJson();
	            	}
	            	else{
	            		_this._setData(data);
	            	}
            	
            });
        },
        
        _setData:function(data){
        	var _this=this,
            thumList=data.data.data;
	        _this.thumList=thumList;
	        _this.totalPages=data.data.totalPages;
	        _this._createTable();
        },
        
        _createTable:function(){
        	var _this=this,
            i= 0,
            j=0,
            template='',
            tableText=this.config.tableText,
            DataText=this.config.dataText;
	        template= '<thead><tr>';
	        for(;i<tableText.length;i++){
	           template+='<td>'+tableText[i]+'</td>';
	        }
	        
	        if(_this.config.more){
	        	template+='<td>用户状态</td><td>更多</td>';
	        }
	        template+='</tr></thead>';
	        template+='<tbody>';
	        
	        for(;j<_this.thumList.length;j++){
	        		/*template+='<tr data-id="'+_this.thumList[j][_this.config.id]+'" class="single">';*/
	        		template+='<tr data-id="'+j+'" class="single">'
	        	var k=0;
	        	for(;k<DataText.length;k++){
	        		template+='<td>'+_this.thumList[j][DataText[k]]+'</td>';
	        	}
	        	if(_this.thumList[j]['isClosed']){
	        		template+='<td><img src="/Nirvana/static/img/pepsi/lock.png"></td>';
	        	}
	        	else{
	        		template+='<td></td>'
	        	}
	        	if (_this.config.more){
	        		template+='<td><div class="more"><img src="/Nirvana/static/img/pepsi/more.png"></div></td>';
	        	}
	        	if(_this.thumList[j].ecode==undefined)_this.thumList[j].ecode='';
	        	template+='</tr>';
	        }
	        template+='</tbody>';
	        _this.elem.html(template);
	        _this._page(_this.totalPages);
	        $('.more img').bind('click',function(){
	        	_this.shopType='dealers'
	        	_this.listElem=$(this).parent();
            	_this._createDOM();
	        	_this._createEvent();
            });	
        },
        
        _page:function(totalPages){
        	var _this=this;
        	PageClick = function(pageclickednumber) {
                current_page=pageclickednumber;
                _this.page=current_page;
                _this._init();
            };
            $("#pager").pager({ pagenumber: _this.page, pagecount: totalPages, buttonClickCallback: PageClick });
            
        },
        /*刷新页面*/
        _fresh:function(){
        	var _this=this;
        	_this._init();
        },
        _bindEvent:function(){
        	var _this=this;
        	$('.fresh-logo').unbind();
        	$('.edit-logo').unbind();
        	$('.fresh-logo').bind('click',function(){
        		_this._fresh();
        	});
        	$('.edit-logo').bind('click',function(){
        		_this._getList();
        	});
        },
        
        /*下拉菜单功能开始*/
        _createDOM:function(){
            var _this=this,
                id=0,
                info=[],
                t='';
            id=_this.listElem.parent().parent().attr('data-id');
            _this.id=id;
            if (document.getElementById('drop-list')===null)_this.listElem.append(dropList.template);
            _this.list=$('#drop-list');
            //if (this.config.close && !_this.thumList[_this.id].isClosed)t+=dropList.closeTemplate;)
            if(_this.thumList[_this.id].isClosed){
            	t+=dropList.openTemplate;
            }
            else{
            	t+=dropList.closeTemplate
            }
            if (this.config.edit)t+=dropList.editTemplate;
            if (this.config.dis)t+=dropList.disTemlate;
            _this.list.html(t);
            _this.listElem.addClass('relative');
            _this.flag=true;
            
            _this.listElem.parent().parent().find('td').each(function(){
                info.push($(this).text());
            });
            _this.info=info;
        },
       
        _createEvent:function(){
            var _this=this;
           
            $('.ui-drop-close-user').bind('click',function(){
                _this._closeUser();
                _this.flag=true;
            });
            $('.ui-drop-edit-user').bind('click',function(){
                _this._editUser();
                _this.flag=true;
            });
            $('.ui-drop-dis-user').bind('click',function(){
                _this._disUser();
                _this.flag=true;
            });
            $('.ui-drop-open-user').bind('click',function(){
                _this._openUser();
                _this.flag=true;
            });
            _this.list.bind('mouseover',function(){
                _this.flag=false;
            });
            _this.list.bind('mouseout',function(){
                _this.flag=true;
            });
            $(document).bind('mousedown',function(event){
                if(_this.flag)_this._close();
            });

        },

        /*关闭用户*/
        _closeUser:function(){
            var _this=this,
                i= 0,
                tableText=this.config.tableText,
                closeLayer='';
            closeLayer='<div style="padding:10px 20px;color:#909090;width: 460px">';
            for(;i<tableText.length;i++){
                closeLayer+='<div class="confirm-item">'+tableText[i]+':'+_this.info[i]+'</div>';
            }
            closeLayer+= '<div class="attention">注:小区移除后将不能再使用关于百事系统</div></div>';
            closeLayer+='<div style="color: red;text-align: center;padding-top: 10px"><span id="layer-errorMessage" ></span></div>';
            _this._close();
            _this.layer=layer.open({
                type: 1,   //0-4的选择,（1代表page层）
                area: ['500px', '300px'],
                shade: [0.5,'#000'],  //不显示遮罩
                shadeClose:true,
                border: [0], //不显示边框
                title: [
                    '您确定要移除'+_this.info[0]+'小区吗?',
                    //自定义标题风格，如果不需要，直接title: '标题' 即可
                    'border-bottom;1px solid #d3d3d3;font-size:18px '
                ],
                bgcolor: '#fff', //设置层背景色
                content: closeLayer,
                btns:2,
                yes:function(){
                    _this._sendCloseUser();
                },
                btn:['确定','取消'],
                shift: 'top' //从上动画弹出
            });
        },

        /*给服务器端发送关闭用户信息*/
        _sendCloseUser:function(){
            var _this=this,
                url=this.config.closeUrl.replace('{id}',_this.thumList[_this.id].id),
                type=this.config.closeType;
            $('#layer-errorMessage').html('数据提交中，请稍等...');
            $.ajax({
                type:type,
                url:url,
                datatype:"json",
                success:function(data){
                	if(data.response=='success'){
	                    layer.close(_this.layer);
	                    _this._fresh();
                	}
                	else{
                		$('#layer-errorMessage').html(data.data.text);
                	}
                },
                error:function(data){
                	layer.close(_this.loading);
                	$('#layer-errorMessage').html("内部错误请重试");
                    return false;
                }
            });
        },

        /*编辑用户*/
        _editUser:function(){
            var _this=this;
            _this._close();
            _this.layer=layer.open({
                type: 1,   //0-4的选择,（1代表page层）
                area: ['450px', '230px'],
                shade: [0.5,'#000'],  //不显示遮罩
                shadeClose:true,
                border: [0], //不显示边框

                title: [
                    '绑定业务员',
                    //自定义标题风格，如果不需要，直接title: '标题' 即可
                    'border-bottom;1px solid #d3d3d3;font-size:18px '
                ],
                bgcolor: '#fff', //设置层背景色
                content: '<div style="padding:10px 20px;color:#909090;width: 400px;">' +
                    '<div class=edit-item><span class="edit-title">小区号:</span><span>'+_this.thumList[_this.id].areaCode+'</span></div>'+
                    '<div class="edit-item"><span class="edit-title">绑定的业务员编号:</span><input type="text" id="ecode" value='+_this.thumList[_this.id].ecode+'></div>'+
                    '<div style="color: red;text-align: center;padding-top: 10px"><span id="layer-errorMessage" ></span></div>'+
                    '</div>',
                
                btns:2,
                btn:['确定','取消'],
                yes:function(){
                   /* var name=$('#layer-name').val()||false,
                        tel=$('#layer-tel').val()||false,
                        pwd=$('#layer-pwd').val()||false,
                        rePwd=$('#layer-repwd').val()||false;
                    _this.name=name;
                    _this.tel=tel;
                    _this.pwd=pwd;
                    _this.rePwd=rePwd;
                    if ( _this._validate())*/
                	_this._sendEditUser();
                },
                shift: 'top' //从上动画弹出
            });
        },

        _sendEditUser:function(){
            var _this=this,
                url=this.config.editUrl.replace('{id}',_this.thumList[_this.id].id),
                type=this.config.editType;
            $('#layer-errorMessage').html("数据提交中,请稍微...");
            $.ajax({
                type:type,
                url:url,
                dataType: "json",
                data:{
                   eCode:$('#ecode').val()
                },
                success:function(data){
                	if (data.response=="success"){
                		layer.close(_this.layer);
                		_this._fresh();
                	}
                	else{
                		$('#layer-errorMessage').html(data.data.text);
                	}
                },
                error:function(data){
                	$('#layer-errorMessage').html("内部错误，请重试!");
                    return false;
                }
            });
        },

        _getNotallotedData:function(url,callback){
        	var _this=this;
        	 $('#layer-errorMessage').html("载入中,请稍等...")
        	$.getJSON(url.replace('{id}',_this.thumList[_this.id].id),{},function(data){
        		 if (data.response=="success"){
	        		 $('#layer-errorMessage').html("")
	                callback(data.data);
        		 }
        		 else{
        			 $('#layer-errorMessage').html(data.data.text)
        		 }
            });
        },
        
        /*分配用户*/
        _disUser:function(){
            var _this=this;
            _this._close();
            _this.removeList=[];
            _this.addList=[];
           
            _this.layer=layer.open({
                type: 1,   //0-4的选择,（1代表page层）
                area: ['600px', '500px'],
                shade: [0.5,'#000'],  //不显示遮罩
                shadeClose:true,
                border: [0], //不显示边框
                title:['<span id="dealers" class="layer-title layer-current">经销商</span><span id="directstores" class="layer-title">直营店</span><span id="stores" class="layer-title">门店</span>'],
                move:false,
                bgcolor: '#fff', //设置层背景色
                content: '<div class="downline-1"></div>'+
                    '<div class="ui-dis-wrap">' +
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
                	_this._sendDis();
                },
                shift: 'top' //从上动画弹出
            });
            _this._createDisEvent();
            _this._setDisData();
           
        },
        
        _sendDis:function(){
        	var _this=this;
        	var url=_this.disCommitUrl.replace('{id}',_this.thumList[_this.id].id);
        	  if(_this.removeList=='' && _this.addList==''){
        		  layer.close(_this.layer);
        	  }
        	  else{
        		  $('#layer-errorMessage').html("数据提交中,请稍等...")
	        	  $.ajax({
	                  type:'PUT',
	                  url:url,
	                  dataType: "json",
	                  data:{
	                    outIds:JSON.stringify(_this.removeList),
	                    inIds:JSON.stringify(_this.addList)	
	                  },
	                  
	                  success:function(data){
	                  	if (data.response=="success"){
	                  		layer.close(_this.layer);
	                  		layer.msg("分配成功!",{icon:1})
	                  	}
	                  	else{
	                  		$('#layer-errorMessage').html(data.data.text)
	                  	}
	                  },
	                  error:function(data){
	                	  $('#layer-errorMessage').html("内部错误，请重试!")
	                      return false;
	                  }
	              });
        	  }
        },
 
        _createDisEvent:function(){
        	
        	var _this=this;
        	$('#dealers').bind('click',function(){
        		_this.shopType='dealers';
        		_this.disCommitUrl='/Nirvana/backend/web/api/area/agentareas/{id}/allotdealer';
        		$('.layer-title.layer-current').removeClass('layer-current');
        		$(this).addClass('layer-current');
        		_this.removeList=[];
        		_this.addList=[];
        		_this._setDisData();
        		
        	});
        	$('#directstores').bind('click',function(){
        		_this.disCommitUrl='/Nirvana/backend/web/api/area/agentareas/{id}/allotdirectstores';
        		_this.shopType='directstores';
        		$('.layer-title.layer-current').removeClass('layer-current');
        		$(this).addClass('layer-current');
        		_this.removeList=[];
        		_this.addList=[];
        		_this._setDisData();
        		
        		
        	});
        	$('#stores').bind('click',function(){
        		_this.shopType='stores';
        		_this.disCommitUrl='/Nirvana/backend/web/api/area/agentareas/{id}/allotstores';
        		$('.layer-title.layer-current').removeClass('layer-current');
        		$(this).addClass('layer-current');
        		_this.removeList=[];
        		_this.addList=[];
        		_this._setDisData();
        	});
        	
        	 $('#single-dis').click(function(){
			    	$('input[type=checkbox][name=undis]:checked').each(function(){
			            $('#distribution').append('<li>'+$(this).parent().html().replace('undis','dis')+'</li>');
			             
			             var dx=_this.removeList.indexOf($(this).attr('id'));
			             if(dx>=0){
			             	_this.removeList.splice(dx,1);
			             }
			             else
			             {
			             	_this.addList.push($(this).attr('id'));
			             }
			            $(this).parent().remove();
			           
			        });
			    });
			    
			    
			
			    $('#all-dis').click(function(){
			        $('input[type=checkbox][name=undis]').each(function(){
			            $('#distribution').append('<li>'+$(this).parent().html().replace('undis','dis')+'</li>');
			            var dx=_this.removeList.indexOf($(this).attr('id'));
			             if(dx>=0){
			             	_this.removeList.splice(dx,1);
			             }
			             else
			             {
			             	_this.addList.push($(this).attr('id'));
			             }
			            $(this).parent().remove();
			            
			        });
			    });
			
			    $('#single-undis').click(function(){
			        $('input[type=checkbox][name=dis]:checked').each(function(){
			            $('#undistribution').append('<li>'+$(this).parent().html().replace('dis','undis')+'</li>');
			            var dx=_this.addList.indexOf($(this).attr('id'));
			             if(dx>=0){
			             	_this.addList.splice(dx,1);
			             }
			             else
			             {
			             	_this.removeList.push($(this).attr('id'));
			             }
			            $(this).parent().remove();
			        });
			    });
			
			    $('#all-undis').click(function(){
			        $('input[type=checkbox][name=dis]').each(function(){
			            $('#undistribution').append('<li>'+$(this).parent().html().replace('dis','undis')+'</li>');
			            var dx=_this.addList.indexOf($(this).attr('id'));
			             if(dx>=0){
			             	_this.addList.splice(dx,1);
			             }
			             else
			             {
			             	_this.removeList.push($(this).attr('id'));
			             }
			             $(this).parent().remove();  
			        });
			    });
        },
        
        _setDisData:function(){
        	var t='';
            var tmp='';
        	var _this=this;
        	  $('#dis').find('ul').find('li').remove();
        	  $('#undis').find('ul').find('li').remove();
        	  if (_this.shopType=='dealers'){
  	            _this._getNotallotedData(_this.config.dealersUrl,function(data){
  	            	 for(var i=0;i<data.dealersNotAlloted.length;i++){
  	                 	t=t+'<li><input type="checkbox" name="undis" id="'+data.dealersNotAlloted[i].id+'"><label for="'+data.dealersNotAlloted[i].id+'">'+data.dealersNotAlloted[i].storefrontInfo.name+'</label></li>';
  	                 }
  	                 $('#undis').find('ul').append(t);
  	                 for(var i=0;i<data.dealers.length;i++){
  	                  	tmp=tmp+'<li><input type="checkbox" name="dis" id="'+data.dealers[i].id+'"><label for="'+data.dealers[i].id+'">'+data.dealers[i].storefrontInfo.name+'</label></li>';
  	                  }
  	                  $('#dis').find('ul').append(tmp);
  	                 
  	            })
              }
              else if(_this.shopType=='directstores'){
              	_this._getNotallotedData(_this.config.directstoresUrl,function(data){
  	            	 for(var i=0;i<data.dealersNotAlloted.length;i++){
  	                 	t=t+'<li><input type="checkbox" name="undis" id="'+data.dealersNotAlloted[i].id+'"><label for="'+data.dealersNotAlloted[i].id+'">'+data.dealersNotAlloted[i].storefrontInfo.name+'</label></li>';
  	                 }
  	                 $('#undis').find('ul').append(t);
  	                 for(var i=0;i<data.dealers.length;i++){
  	                  	tmp=tmp+'<li><input type="checkbox" name="dis" id="'+data.dealers[i].id+'"><label for="'+data.dealers[i].id+'">'+data.dealers[i].storefrontInfo.name+'</label></li>';
  	                  }
  	                  $('#dis').find('ul').append(tmp);
  	                 
  	            })
              }
              else if(_this.shopType=='stores'){
              	_this._getNotallotedData(_this.config.storesUrl,function(data){
  	            	 for(var i=0;i<data.storesNotAlloted.length;i++){
  	                 	t=t+'<li><input type="checkbox" name="undis" id="'+data.storesNotAlloted[i].store_id+'"><label for="'+data.storesNotAlloted[i].store_id+'">'+data.storesNotAlloted[i].store_name+'</label></li>';
  	                 }
  	                 $('#undis').find('ul').append(t);
  	                 for(var i=0;i<data.stores.length;i++){
  	                  	tmp=tmp+'<li><input type="checkbox" name="dis" id="'+data.stores[i].store_id+'"><label for="'+data.stores[i].store_id+'">'+data.stores[i].store_name+'</label></li>';
  	                  }
  	                  $('#dis').find('ul').append(tmp);
  	                 
  	            })
              }
              else{
            	  return false;
              }
        	
        },
        /*验证编辑信息
         * 信息完整性
         * 手机号码合法性
         * 密码一致性*/
        _validate:function() {

            var _this=this,
                re=/^1[3|5|7|8][0-9]{9}$/,
                $errorMessage=$('#layer-errorMessage');
            function initPwd(){
                $('#layer-pwd').val('');
                $('#layer-repwd').val('');
            }
            if(!_this.name || !_this.tel || !_this.pwd || !_this.rePwd) {
                $errorMessage.html('请完成所有内容');
                initPwd();
                return false;
            }
            if(!re.exec(_this.tel)){
                $errorMessage.html('手机号码格式不正确');
                initPwd();
                return false;
            }
            if(_this.pwd!=_this.rePwd){
                $errorMessage.html('两次输入的密码不一致');
                initPwd();
                return false;
            }
            return true;
        },
        
        _close:function(){
            var _this=this;
            $('#dealers').unbind();
        	$('#directstores').unbind();
        	$('#stores').unbind();
            $('.ui-drop-close-user').unbind();
            $('.ui-drop-edit-user').unbind();
            $(document).unbind();
            _this.listElem.removeClass('relative');
            _this.listElem.find('ul').remove();
            delete dropList;
        },
        
        /*激活用户功能开始*/
        _layer:function(){
            var t='',
            	_this=this;
                i= 0,
                showName=this.config.showName,
                pageii={};
                for(;i<_this.thubList.length;i++) {
                    t +='<span class="suffix-inline user-lab" data-id="'+
                    _this.thubList[i].id+
                    '">' + _this.thubList[i][showName] + '<span  class="suffix-inline close-ico"></span></span>';
                }
                pageii=$.layer({
                type: 1,   //0-4的选择,（1代表page层）
                area: ['500px', '300px'],
                shade: [0.5,'#000'],  //不显示遮罩
                shadeClose:true,
                border: [0], //不显示边框

                title: [
                    '激活用户',
                    //自定义标题风格，如果不需要，直接title: '标题' 即可
                    'border-bottom;1px solid #d3d3d3;font-size:18px '
                ],
                bgcolor: '#fff', //设置层背景色
                page: {
                    html: '<div style="padding:10px 20px;color:#909090;width: 460px;overflow-y: auto;">' +t+'</div>'
                },
                btns:2,
                btn:['确定','取消'],
                yes:function(){
                	_this._fresh();
                    layer.close(pageii);
                },
                shift: 'top' //从上动画弹出
            });
        },
        _getList:function(){
            var _this=this,
                url=this.config.activeListUrl;
            $.getJSON(url,{},function(data){
                _this._setList(data);
            });
        },
        
        _setList:function(data){
            var _this=this,
            thubList=data.data;
        _this.thubList=thubList;
        _this._layer();
        _this._createListEvent();
        },
        
        _createListEvent:function(){
        	var _this=this;
            $('.close-ico').bind('click',function(){
                _this._active(this);
            });
        },
        
        _openUser:function(){
        	var _this=this;
        	 _this.loading=layer.msg('数据提交中,请稍等...', {icon: 16});
        	$.ajax({
       			type:_this.config.activeType,
       			url:_this.config.activeUrl.replace('{id}',_this.thumList[_this.id].id),
       			success:function(data){
       				layer.close(_this.loading)
       				if(data.response=='success'){
       					layer.msg('激活成功');
       					_this._fresh();
       				}
       				else{
       					layer.msg(data.data.text);
       				}
       			}
       		});
        }
    };
    dropList.template="<ul id='drop-list'></ul>";
    dropList.closeTemplate= "<li><a href='javascript:void(0);;' class='ui-drop-close-user'>移除小区 </a></li>";
    dropList.editTemplate="<li><a href='javascript:void(0);;' class='ui-drop-edit-user'>绑定业务员</a></li>";
    dropList.disTemlate="<li><a href='javascript:void(0);;' class='ui-drop-dis-user'>分配门店 </a></li>";
    dropList.openTemplate="<li><a href='javascript:void(0);;' class='ui-drop-open-user'>激活用户 </a></li>"
    $.fn.dropList = function( options ) {
        return this.each( function() {
            new dropList( this, options );
        });
    };
    return $;
}(jQuery));