/**
 * Created by 2014 on 2015/2/14.
 */

(function($){
    var activeUser=function(elem,options){
        var _this=this;
        this.elem=$(elem);
        this.options = options;
        this.thumList=[];
        this.elem.bind('click', function (event) { event.preventDefault(); _this._init(); return false; });
    };
    activeUser.prototype={
        defaults:{
            activeUrl:'/Nirvana/backend/web/api/agents/{id}/activate',
            activeType:'PUT',
            activeListUrl:'/Nirvana/backend/web/api/agents/closed'
        },
        _init:function(){
            this.config= $.extend({},this.defaults,this.options);
            this._getList()
            
        },
        _layer:function(){
            var t='',
            	_this=this;
                i= 0,
                pageii={};
                for(;i<_this.thumList.length;i++) {
                    t +='<span class="suffix-inline user-lab" data-id="'+
                    _this.thumList[i].id+
                    '">' + _this.thumList[i].nameCode + '<span  class="suffix-inline close-ico"></span></span>'
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
                    html: '<div style="padding:10px 20px;color:#909090;width: 460px">' +t+'</div>'
                },
                btns:2,
                btn:['确定','取消'],
                yes:function(){
                    layer.close(pageii);
                },
                shift: 'top' //从上动画弹出
            });
        },
        _getList:function(){
            var _this=this,
                url=this.config.activeListUrl
            $.getJSON(url,{},function(data){
                _this._setData(data)
            })
        },
        _setData:function(data){
            var _this=this,
                thumList=data.userList;
            _this.thumList=thumList;
            _this._layer();
            _this._createEvent();
        },
        _createEvent:function(){
        	var _this=this;
            $('.close-ico').bind('click',function(){
                _this._active(this);
            })
        },
        _active:function(elem){
        	var _this=this,
        		$elem=$(elem),
        		id=$elem.parent().attr('data-id'),
        		url=this.config.activeUrl.replace('{id}',id),
        		type=this.config.activeType;
        	$.ajax({
       			type:type,
       			url:url,
       			success:function(data){
       				 $elem.parent().remove()
       			}
       		})
        },
        
    };
    $.fn.activeUser = function( options ) {
        return this.each( function() {
            new activeUser( this, options );
        });
    };
    return $;
}(jQuery));