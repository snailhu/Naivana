/**
 * Created by 2014 on 2015/2/12.
 */

(function($){
    var dropMenu=function(elem,options){
        var _this=this;
        this.elem = $( elem );
        this.options = options;
        this.list={};
        this.id=0;
        this.flag=true;
        this.item=[];
        this.info=[];
        this.elemList={};
        this.elem.find('img').bind('click', function (event) { event.preventDefault(); _this._init(); return false; });
    };
    dropMenu.prototype={
        defaults:{
            event:[{
                content:'',
                callback:undefined
            }]
        },
        _init:function(){
            this.config= $.extend({},this.defaults,this.options);
            this._createDOM();
            this._createEvent();
        },
        _createDOM:function(){
            var _this=this,
                item=[],
                t='',
                id=0;
            _this.elem.append(dropMenu.template);
            _this.list=$('#drop-list');
    
            _this.list.append(t);
            for(var i=0;i<_this.config.event.length;i++){

                t=dropMenu.liTemplate.replace('{content}',_this.config.event[i].content);
                _this.list.append(t);
            }
            _this.elem.addClass('relative');
            _this.flag=true;
            id=_this.elem.parent().parent().attr('data-id');
            _this.id=id;
            _this.elem.parent().parent().parent().parent().find('thead').find('td').each(function(){
                item.push($(this).text());
            });
            _this.item=item;
            _this.elem.parent().parent().find('td').each(function(){
                _this.info.push($(this).text());
            });
        },

        _createEvent:function(){
            var _this=this,
                li=_this.list.find('li');
            for (var i=0;i<li.length;i++) {
                var event = _this.config.event[i];
                if (event.callback != undefined && typeof event.callback == "function") {
                    $(li[i]).bind('click',{callback:event.callback,id:_this.id},function(event){
                        event.data.callback(event.data.id);
                        _this._close();
                    });
                }
            }
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

        _close:function(){
            var _this=this,
                li=_this.list.find('li');
            for (var i=0;i<li.length;i++){
                $(li[i]).unbind();
            }
            $(document).unbind();
            _this.elem.removeClass('relative');
            _this.list.remove();
            delete dropMenu;
        }

    }
    dropMenu.template="<ul id='drop-list'></ul>"
    dropMenu.liTemplate= "<li><a href='javascript:void(0);;' class='ui-drop-close-user'>{content}</a></li>"


    $.fn.dropMenu = function( options ) {
        return this.each( function() {
            new dropMenu( this, options );
        });
    };
    return $;
}(jQuery));