$(document).ready(function(){
       	var pic=[];
       	$(".pic-wrap").each(function(){
       		pic.push($(this).find("img").attr('src'));
       	})
            $("#file_upload").uploadify({
               'buttonImage' : '/Nirvana/static/img/pepsi/addPic.png',
                height        : 30,
                'fileTypeDesc' : 'image',
                'fileTypeExts': '*.jpg; *.png',
                swf           : '/Nirvana/static/uploadify.swf',
                uploader      : '/Nirvana/backend/web/api/promotions/upload',
                fileObjName     : 'pictures',   
                width         : 85,
                height:85,
               'uploadLimit' :999,
                'fileSizeLimit':5000,
                'onUploadStart':function(file){
                	layer.msg("正在上传图片,请稍等...",{icon: 16, shade:[0.8, '#393D49']})
                },
               'onUploadSuccess' : function(file, data, response) {
            	  info=JSON.parse(data);
            	   if (info.response=="success"){
            		   layer.msg("上传成功!",{icon:1})
            		   pic.push(info.data.url);
            		   addDom(info.data.url);
            	   }
            	   else{
            		   layer.msg(info.data.text,{icon:2})
            	   }
            	   
                },
                'onUploadError' : function(file, errorCode, errorMsg, errorString) {
                	 layer.msg("内部错误,请重试!",{icon:2})
                }
            });
            
         function addDom(url){

        	 var div=document.createElement('div');
        	 var img=document.createElement('img');
        	 var span=document.createElement('span');
        	 $(div).addClass("pic-wrap");
 			$(div).append(img);	
 	        $(img).attr("src",url);
 	        $(div).append(span);
 	        $("#imgArea").append(div)
         }
         
         $("#imgArea").on("click","span",function(){
        	 $(this).parent().remove();
        	 var url=$(this).parent().find("img").attr("src")
        	 var dx=pic.indexOf(url)
        	 pic.splice(dx, 1);
         })
         
         $("#submit").click(function(){
        	var startarr=$("#start-date-min").val().split('-');
      		var beginDate=startarr[0]+startarr[1]+startarr[2];
      		var endarr=$("#end-date-min").val().split('-');
     		var endDate=endarr[0]+endarr[1]+endarr[2];
     		var title=$("#title").val();
     		var channelId=$("#channelSelectId").val();
     		var content=$("#content").val();
     		if(pic.length<=0){
     			layer.msg("请上传签核文件！",{icon:7})
     			return false;
     		}
     		if(pic.length>5){
     			layer.msg("审核文件最多不超过5张！",{icon:7})
     			return false;
     		}
     		if(title && channelId && beginDate && endDate && content){
     			var url="/Nirvana/backend/web/api/promotions/{id}";
     			url=url.replace("{id}",promotionId);
	     		$.ajax({
	                 type:"POST",
	                 url:url,
	                 datatype:"json",
	                 data:{
	                     "title":$("#title").val(),
	                     "channelId":$('#channelSelectId').val(),
	                     "beginDate":beginDate,
	                     "endDate":endDate,
	                     "content":$("#content").val(),
	                     "pictures":JSON.stringify(pic)
	                 },
	                 success:function(data){
	                 	if(data.response=='success'){
	                 		layer.msg("保存成功",{icon:1})
	                 		window.location.href="/Nirvana/backend/web/sales_management/rejected";
	                 	}
	                 	else{
	                 		layer.msg(data.data.text,{icon:2})
	                 	}
	                 },
	                 error:function(data){
	                	 layer.msg("内部错误,请重试!",{icon:2})
	                 }
	             });
     		}
     		else{
     			layer.msg("请完成所有内容！",{icon:7})
     		}
         })
})
    