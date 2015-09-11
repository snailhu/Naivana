

(function($){
    var checkExist=function(elem,options){
        var _self=this;
        this.elem = $( elem );
        this.options = options;
        this.lastValue="";
        this.layer=0;
        this.elem.on('blur', function (event) { event.preventDefault(); _self._init(); return false; });
    };
    checkExist.prototype={
        defaults:{
            url:"/Nirvana/backend/web/api/checkexist/backend/username",
            type:1,
            errorMessage:"此用户名已被注册",
            tips:"此用户名暂未被使用",
            layertype:2,
        },
        _init:function(){
            var _self=this,
                url="";
            
            _self.config = $.extend( {},_self.defaults, _self.options );
            if(_self.lastValue ==_self.elem.val()){
            	return false;
            }
            else{
            	_self.lastValue=_self.elem.val();
            }
            switch (_self.config.type){
                case 1:
                    url=_self.config.url+"?username="+_self.elem.val();
                    break;
                case 2:
                	re=/^1[3|5|7|8][0-9]{9}$/;
                    if(!re.exec(_self.elem.val())){
                    	layer.close(_self.layer)
                    	_self.layer=layer.tips("手机号码格式不正确!", _self.elem,{
                       	 tips: [_self.config.layertype,"#FF4130"],
                       	 time:0,
                       	 tipsMore:true 
                       });
                       return false;
                    }
                    url=_self.config.url+"?phone="+_self.elem.val();
                    break;
                default :
                    break;
            }
            _self._jude(url);
        },

        _jude:function(url){
            var _self=this;
            $.when(_self._check(url) ).then(
                function(status) {
                    if(status){
                    	layer.close(_self.layer)
                        _self.layer=layer.tips(_self.config.errorMessage, _self.elem,{
                        	tips:[_self.config.layertype,"#FF4130"],
                        	time:0,
                        	tipsMore:true 
                        });
                    }
                    else{
                    	layer.close(_self.layer)
                        _self.layer=layer.tips(_self.config.tips, _self.elem,{
                        	 tips: [_self.config.layertype,'#78BA32'],
                        	 time:0,
                        	 tipsMore:true 
                        });
                    }
                }
            )
        },


        _check:function(url){
            var   jdf = jQuery.Deferred();
            $.getJSON(url,function(data){
                if (data.response=="success"){
                    jdf.resolve(data.data);
                }
                else{
                    jdf.reject(data.errorMessage);
                }
            });
            return jdf.promise();
        }
    };
    $.fn.checkExist = function( options ) {
        return this.each( function() {
            new checkExist( this, options );
        });
    };
    return $;

}(jQuery));






