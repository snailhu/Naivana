/**
 * Created by 2014 on 2015/2/10.
 */

(function($){
    var photo=function(elem,options){
        var _this=this;
        this.elem = $( elem );
        this.options = options;
        this.index=null
        this.thumbList=[]
        this.thumbLen=0
        this.IMGElem={};
        this.IMG={};
        this.tips={};
        this.currentUrl="";
        this.elem.delegate('img','click',function (event) { event.preventDefault();_this.currentUrl=this.src; _this._init(); return false; });
    };
    photo.prototype={
        defaults: {
            url : 'data.json',
            lazyload: 'images/lazyload.gif',
            loadding : 'images/loading.gif'
        },
        _init:function(){
            this.config = $.extend( {}, this.defaults, this.options );
            this._createDOM();
            this.thumbList=[]
            this._setData();
            this._showIMG();
            this._showIcoList();
        },

        _setData:function(){
            var _this=this,
                $img=_this.elem.find("img");
            for(var i=0;i<$img.length;i++){
            	
                _this.thumbList.push($img[i].src);
                if(_this.IMG=$img[i]){
                	_this.index=i;
                }
            }
            _this.thumbLen=_this.thumbList.length;
            if(_this.thumbLen>0){
                _this.index=_this.thumbList.indexOf(_this.currentUrl)
                
            }
            else{
                layer.msg("无数据!",{icon:7})
                _this._close()
                return false;
            }

        },

        _createDOM:function(){
            var _this=this;
            $('body').append(photo.template);
            _this.IMGElem=$('.ui-dialog-photo');
            _this.tips=$('.ui-dialog-tips');
            _this.ulElem=$('.ui-dialog-ico');
            _this.divElem=$('.ui-dialog-nav');
        },

        _showIMG:function(){
            var _this=this;
            _this.IMGElem.find('img').attr('src','');
            _this.IMGElem.addClass('loading');
            
            _this._loadingIMG(_this.thumbList[_this.index],function(){
                _this.IMGElem.removeClass('loading');
                _this.IMGElem.find('img').attr('src',_this.thumbList[_this.index]);
                _this.tips.find('span').html(_this.index+1+'/'+_this.thumbLen)
                
            })

        },

        _showIcoList:function(){
            var _this=this;
            var template='';
            $('.ui-dialog-lef-arrow').on('click',function(event){
                _this._preIMG()
            })
            $('.ui-dialog-right-arrow').on('click',function(event){
                _this._nextIMG()
            })
            $('.ui-dialog-close').on('click',function(event){
               _this._close()
            })
            $('.ui-dialog-shape').on('click',function(event){
                _this._close()
            })
        },

        _loadingIMG:function(url,callback){
            var image = new Image()
            image.src = url;
            if ( image.complete ){
                if ( typeof callback !== 'undefined' ) callback.call();
                return;
            }
            image.onload = function(){
                if ( typeof callback !== 'undefined' ) callback.call();
                return;
            }
        },

        _preIMG:function(){
            var _this=this;
            if(_this.index>0) {
            	_this.index--;
                _this._showIMG();
            }
            else{
                layer.msg("已到第一张!",{icon:7})
            }
        },

        _nextIMG:function(){
            var _this = this
            if(_this.index<_this.thumbLen-1){
                _this.index++
                _this._showIMG()
            }
            else{
                layer.msg("已到最后一张!",{icon:7})
            }
        },

        _resize:function(){
            var _this=this;
            $(window).bind( 'resize', function(event){
                 _this._move()
            })
        },
        _close:function(){
            $('.ui-dialog-arrowLeft').unbind()
            $('.ui-dialog-arrowRight').unbind()
            $('.ui-dialog-lef-arrow').unbind()
            $('.ui-dialog-right-arrow').unbind()
            $('.ui-dialog-close').unbind()
            $('.ui-dialog-shape').unbind()
            $('.ui-dialog-wrap').remove()
            $('.ui-dialog-shape').remove()
            $(window).unbind()
            layer.closeAll();
            delete photo;
        }

    }
    photo.template='<div class="ui-dialog-shape"></div>'+
                        '<div class="ui-dialog-wrap">'+
                            '<div class="ui-dialog-contain">'+
                                '<div class="ui-dialog-main">'+
                                    '<div class="ui-dialog-photo">'+
                                        '<a href="javascript:void(0);;" class="ui-dialog-lef-arrow" ><em></em></a>'+
                                        '<a href="javascript:void(0);;" class="ui-dialog-right-arrow" ><em></em></a>'+
                                        '<span></span>'+
                                        '<img  id="pic" style="display: inline">'+
                                    '</div>'+
                                    '<div class="ui-dialog-tips">'+
                                    
                                    	'<div class="ui-dialog-tips-shape"></div>'+
                                    	'<span></span>'+
                                    '</div>'+
                                '</div>'+

                            '</div>'+

                            '<div class="ui-dialog-close">'+
                                '<a href="javascript:void(0);;"></a>'+
                            '</div>'+
                        '</div>'

    $.fn.photo = function( options ) {
        return this.each( function() {
            new photo( this, options );
        });
    };
    return $;

}(jQuery));


