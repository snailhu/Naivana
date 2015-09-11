/**
 * Created by 2014 on 2015/3/3.
 */

!function(){
    function getdisarr(){
        var arr=[];
        $('input[type=checkbox][name=dis]:checked').each(function(){
                arr.push($(this).parent().index());
        });
        return arr;
    }

    $('#up').click(function(){	
    	var arr=getdisarr();
        var $liElem=$('#distribution li');
        for(var i=0;i<arr.length;i++){
            var index=arr[i];
            if (index>0) {
                var temp = $liElem[index].innerHTML;
                $liElem[index].innerHTML = $liElem[index - 1].innerHTML;
                $liElem[index - 1].innerHTML = temp;
                $($liElem[index - 1]).find('input[type=checkbox]').click();
            }
        }
    });
    

    $('#down').click(function(){	
    	var arr=getdisarr();
        var $liElem=$('#distribution li');
        for(var i=arr.length-1;i>=0;i--){
            var index=arr[i];
            if(index<$liElem.length-1) {
                var temp = $liElem[index].innerHTML;
                $liElem[index].innerHTML = $liElem[index + 1].innerHTML;
                $liElem[index + 1].innerHTML = temp;
                $($liElem[index + 1]).find('input[type=checkbox]').click();
            }
        }
    });
   

   
    $('#single-dis').click(function(){
    	$('input[type=checkbox][name=undis]:checked').each(function(){
            $('#distribution').append('<li>'+$(this).parent().html().replace('undis','dis')+'</li>');
            $(this).parent().remove();
            
        });
    });
    
    

    $('#all-dis').click(function(){
        $('input[type=checkbox][name=undis]').each(function(){
            $('#distribution').append('<li>'+$(this).parent().html().replace('undis','dis')+'</li>');
            $(this).parent().remove();
        });
    });

    $('#single-undis').click(function(){
    		var flag=true;
	        $('input[type=checkbox][name=dis]:checked').each(function(){
	        	if (type!='' && type==$(this).attr('data-type')){
		            $('#undistribution').append('<li>'+$(this).parent().html().replace('dis','undis')+'</li>');
		            $(this).parent().remove();
	        	}
	        	else{
	        		flag=false;
	        	}
	        	if(type==''){
	        		layer.msg('请先查询用户!',{icon:7})
	        		return false;
	        	}
	        	if (!!!flag){
	        		layer.msg('部分用户未被移除,请先查询相应的用户!',{icon:7})
	        	}
	        });
    	
    });

    $('#all-undis').click(function(){
    	var flag=true;
        $('input[type=checkbox][name=dis]').each(function(){
        	if (type!='' && type==$(this).attr('data-type')){
	            $('#undistribution').append('<li>'+$(this).parent().html().replace('dis','undis')+'</li>');
	            $(this).parent().remove();
        	}
        	else{
        		flag=false;
        	}
        	if(type==''){
        		layer.msg('请先查询用户!',{icon:7})
        		return false;
        	}
        	if (!!!flag){
        		layer.msg('部分用户未被移除,请先查询相应的用户!',{icon:7})
        	}
        });
    });
}();