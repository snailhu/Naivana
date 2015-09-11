/**
 * Created by 2014 on 2015/1/7.
 */

    $(document).ready(function() {
    	 $('.form-control').each(
    	            function(){
    	                if ($(this).val() != '') {
    	                    $(this).parent().find('.placeholder').css('z-index', '-1')
    	                }
    	            }
    	        );
    	 
        $('.form-control').bind("input propertychange",function () {
            if ($(this).val() != '') {
                $(this).parent().find('.placeholder').css('z-index', '-1')
            }
            else {
                $(this).parent().find('.placeholder').css('z-index', '99')
            }
        })
    })