/**
 * Created by 2014 on 2015/2/15.
 */



(function($){
    var getInfoList=function(elem,options){
        var _this=this;
        this.elem=$(elem);
        this.options = options;
        this.thumList=[];
        this.page=1;
        this.totalPages=0;
        _this._init();
        $('.fresh-logo').bind('click', function (event) { event.preventDefault(); _this._init(); return false; });
        //this.elem.bind('click', function (event) { event.preventDefault(); _this._init(); return false; });
    };
    getInfoList.prototype={
        defaults:{
            url:'/Nirvana/backend/web/api/agents?page={page}&size={size}',
            tableText:[],
            dataText:['nameCode','realName','phone'],
            size:5,
            callback:{}
        },
        _init:function(){
            this.config= $.extend({},this.defaults,this.options);
            this._getJson();

        },
        _getJson:function(){
            var _this=this,
                url=this.config.url.replace('{page}',_this.page).replace('{size}',_this.config.size)
            $.getJSON(url,{},function(data){
                _this._setData(data);
            });
        },
        _setData:function(data){
            var _this=this,
                thumList=data.userList;
            _this.thumList=thumList;
            _this.totalPages=data.totalPages;
            _this._createDom();
        },
        _createDom:function(){
            var _this=this,
                i= 0,
                j=0,
                template='',
                tableText=this.config.tableText,
                DataText=this.config.dataText,
            template= '<thead><tr>';
            for(;i<tableText.length;i++){
               template+='<td>'+tableText[i]+'</td>';
            }
            template+='<td>更多</td>';
            template+='</tr></thead>';
            template+='<tbody>';
            for(;j<_this.thumList.length;j++){
            	template+='<tr data-id="'+_this.thumList[j]["id"]+'">';
            	var k=0;
            	for(;k<DataText.length;k++){
            		template+='<td>'+_this.thumList[j][DataText[k]]+'</td>';
            	}
            	template+='<td><div class="more"><img src="/Nirvana/static/img/pepsi/more.png"></div></td>';
            	template+='</tr>';
            }
            template+='</tbody>';
            _this.elem.html(template);
            _this._page(_this.totalPages);
           
            
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
        _fresh:function(){
        	_this.init();
        }
    };
    $.fn.getInfoList = function( options ) {
        return this.each( function() {
            new getInfoList( this, options );
        });
    };
    return $;
}(jQuery));