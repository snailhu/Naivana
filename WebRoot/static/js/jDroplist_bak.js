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
        _this._init();
        //this.elem.find('img').bind('click', function (event) { event.preventDefault(); _this._init(); return false; });
    };
    dropList.prototype={
        defaults:{
        	editType:'PUT',
	        editUrl:'/Nirvana/backend/web/api/agents/{id}',
            closeUrl:'/Nirvana/backend/web/api/agents/{id}/close',
            closeType:'PUT',
            disUrl:'data.json',
            url:'/Nirvana/backend/web/api/agents?page={page}&size={size}',
            activeUrl:'/Nirvana/backend/web/api/agents/{id}/activate',
            activeType:'PUT',
            activeListUrl:'/Nirvana/backend/web/api/agents/closed',
            tableText:[],
            dataText:['nameCode','realName','phone'],
            size:5,
            userText:'用户',
            showName:'nameCode',
            close:true,
            edit:true,
            dis:true,
            more:true,
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
            $.getJSON(url,{},function(data){
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
	        	template+='<td>更多</td>';
	        }
	        template+='</tr></thead>';
	        template+='<tbody>';
	        
	        for(;j<_this.thumList.length;j++){
	        	if(j%2==0){
	        		template+='<tr data-id="'+_this.thumList[j]["id"]+'" class="single">';
	        	}
	        	else{
	        		template+='<tr data-id="'+_this.thumList[j]["id"]+'" class="double">';
	        	}
	        	var k=0;
	        	for(;k<DataText.length;k++){
	        		template+='<td>'+_this.thumList[j][DataText[k]]+'</td>';
	        	}
	        	if (_this.config.more){
	        		template+='<td><div class="more"><img src="/Nirvana/static/img/pepsi/more.png"></div></td>';
	        	}
	        	template+='</tr>';
	        }
	        template+='</tbody>';
	        _this.elem.html(template);
	        _this._page(_this.totalPages);
	        $('.more img').bind('click',function(){
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
            if (document.getElementById('drop-list')===null)_this.listElem.append(dropList.template);
            _this.list=$('#drop-list');
            if (this.config.close)t+=dropList.closeTemplate;
            if (this.config.edit)t+=dropList.editTemplate;
            if (this.config.dis)t+=dropList.disTemlate;
            _this.list.html(t);
            _this.listElem.addClass('relative');
            _this.flag=true;
            id=_this.listElem.parent().parent().attr('data-id');
            _this.id=id;
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
            closeLayer+= '<div class="attention">注:用户关闭后将不能再使用关于百事系统</div></div>';
            _this._close();
            _this.layer=$.layer({
                type: 1,   //0-4的选择,（1代表page层）
                area: ['500px', '300px'],
                shade: [0.5,'#000'],  //不显示遮罩
                shadeClose:true,
                border: [0], //不显示边框
                title: [
                    '您确定要关闭'+_this.info[0]+'用户吗?',
                    //自定义标题风格，如果不需要，直接title: '标题' 即可
                    'border-bottom;1px solid #d3d3d3;font-size:18px '
                ],
                bgcolor: '#fff', //设置层背景色
                page: {
                    html: closeLayer
                },
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
                url=this.config.closeUrl.replace('{id}',_this.id),
                type=this.config.closeType;
            $.ajax({
                type:type,
                url:url,
                datatype:"json",
                success:function(data){
                    layer.close(_this.layer);
                    _this._fresh();
                },
                error:function(data){
                    return false;
                }
            });
        },

        /*编辑用户*/
        _editUser:function(){
            var _this=this;
            _this._close();
            _this.layer=$.layer({
                type: 1,   //0-4的选择,（1代表page层）
                area: ['400px', '300px'],
                shade: [0.5,'#000'],  //不显示遮罩
                shadeClose:true,
                border: [0], //不显示边框

                title: [
                    '编辑用户',
                    //自定义标题风格，如果不需要，直接title: '标题' 即可
                    'border-bottom;1px solid #d3d3d3;font-size:18px '
                ],
                bgcolor: '#fff', //设置层背景色
                page: {
                    html: '<div style="padding:10px 60px;color:#909090;width: 280px;">' +
                    '<div class=edit-item><span class=e"dit-title">小区号</span><span></span>'+
                    '<div class="edit-item"><span class="edit-title">绑定的业务员编号:</span><input type="text" id="layer-name" value='+_this.info[1]+'></div>'+
                    '<div style="color: red;text-align: center;padding-top: 10px"><span id="layer-errorMessage" ></span></div>'+
                    '</div>'
                },
                btns:2,
                btn:['确定','取消'],
                yes:function(){
                    var name=$('#layer-name').val()||false,
                        tel=$('#layer-tel').val()||false,
                        pwd=$('#layer-pwd').val()||false,
                        rePwd=$('#layer-repwd').val()||false;
                    _this.name=name;
                    _this.tel=tel;
                    _this.pwd=pwd;
                    _this.rePwd=rePwd;
                    if ( _this._validate())_this._sendEditUser();
                },
                shift: 'top' //从上动画弹出
            });
        },

        _sendEditUser:function(){
            var _this=this,
                url=this.config.editUrl.replace('{id}',_this.id),
                type=this.config.editType;
            
            $.ajax({
                type:type,
                url:url,
                dataType: "json",
                data:{
                    password:_this.pwd,
                    phoneNum:_this.tel,
                    repPassword:_this.rePwd,
                    realName:_this.name
                },
                success:function(data){
                    layer.close(_this.layer);
                    _this._fresh();
                },
                error:function(data){
                    return false;
                }
            });
        },

        /*分配用户*/
        _disUser:function(){
            var _this=this;
            _this._close();
            $.layer({
                type: 1,   //0-4的选择,（1代表page层）
                area: ['600px', '500px'],
                shade: [0.5,'#000'],  //不显示遮罩
                shadeClose:true,
                border: [0], //不显示边框

                title: [
                    '给'+_this.config.userText+_this.info[1]+'分配门店',
                    //自定义标题风格，如果不需要，直接title: '标题' 即可
                    'border-bottom;1px solid #d3d3d3;font-size:18px '
                ],
                bgcolor: '#fff', //设置层背景色
                page: {
                    html: '<div class="ui-dis-wrap">' +
                    '<div class="ui-dis-left-slider">'+
                    '<div class="ui-dis-title"><label style="line-height: 40px">已管理门店</label></div>'+
                    '<div id="dis" class="ui-dis-content">'+
                    '<ul>'+
                    '<li><input type="checkbox" name="dis" id="1"><label for="1">长江七号</label> </li>'+
                    '</ul>'+
                    '</div>'+
                    '</div>'+
                    '<div class="ui-dis-right-slider">'+
                    '<div  class="ui-dis-title"><label style="line-height: 40px">经销商和门店</label>' +
                    '<div style="height:30px;border: 1px solid #d3d3d3;width: 140px;float: right;margin:4px 0">'+
                    '<input type="text" style="width: 120px;height: 28px;border: 0;outline: 0;">'+
                    '<img src="/Nirvana/static/img/pepsi/serch.png" style="vertical-align: middle;">'+
                    '</div>'+
                    '</div>'+
                    '<div id="undis" class="ui-dis-content">'+
                    '<ul>'+
                    '<li><input type="checkbox" name="undis" id="2"><label for="2">长江七号</label> </li>'+
                    '</ul>'+
                    '</div>'+
                    '</div>'+
                    '</div>'
                },
                btns:2,
                btn:['确定','取消'],
                yes:function() {
                    var t=null,
                        $undis={};
                    $('#undis').find('input[type=checkbox][name=undis]:checked').each(function(){
                        $undis=$(this).parent().parent();
                        $undis.remove();
                        t=$undis.html().replace('undis','dis');
                        $('#dis').find('ul').append(t);
                    });
                },
                shift: 'top' //从上动画弹出
            });
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
        
        _active:function(elem){
        		$elem=$(elem),
        		id=$elem.parent().attr('data-id'),
        		url=this.config.activeUrl.replace('{id}',id),
        		type=this.config.activeType;
        	$.ajax({
       			type:type,
       			url:url,
       			success:function(data){
       				 $elem.parent().remove();
       			}
       		});
        }
    };
    dropList.template="<ul id='drop-list'></ul>";
    dropList.closeTemplate= "<li><a href='javascript:void(0);;' class='ui-drop-close-user'>关闭用户 </a></li>";
    dropList.editTemplate="<li><a href='javascript:void(0);;' class='ui-drop-edit-user'>编辑用户 </a></li>";
    dropList.disTemlate="<li><a href='javascript:void(0);;' class='ui-drop-dis-user'>分配用户 </a></li>";
    $.fn.dropList = function( options ) {
        return this.each( function() {
            new dropList( this, options );
        });
    };
    return $;
}(jQuery));