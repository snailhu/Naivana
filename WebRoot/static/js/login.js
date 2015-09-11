(function(){
	$(function(){
	        if (document.addEventListener) {
		    //如果是Firefox
	           document.addEventListener("keypress", enterEvent, true);
	        } else {
	            //如果是IE
	            document.attachEvent("onkeypress", enterEvent);
	        }
	        function enterEvent(evt) {
	            if (evt.keyCode == 13) {
	                document.getElementById("submit").click();
	                //do something
	            }
	        }
	    });
	    
	    
	$('#submit').click(function(){
		var $username=$('#username').val();
		var $password=$('#password').val();
		if($username && $password){
			$("#myform").submit();
	       }
	       else{
	       		layer.msg("账号或密码不能为空!",{icon:7})
	       }         
	});
})();