/**
 * Created by 2014 on 2015/1/17.
 */



	
	jQuery(function($){
	    $.datepicker.regional['zh-CN'] = {
	        closeText: '确定',
	        prevText: '<上月',
	        nextText: '下月>',
	        currentText: '今天',
	        monthNames: ['一月','二月','三月','四月','五月','六月',
	            '七月','八月','九月','十月','十一月','十二月'],
	        monthNamesShort: ['一','二','三','四','五','六',
	            '七','八','九','十','十一','十二'],
	        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
	        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
	        dayNamesMin: ['日','一','二','三','四','五','六'],
	        weekHeader: '周',
	        dateFormat: 'yy-mm-dd',
	        firstDay: 1,
	        isRTL: false,
	        showMonthAfterYear: true,
	        yearSuffix: '年'};
	    $.datepicker.setDefaults($.datepicker.regional['zh-CN']);
	});
	
$(document).ready(function(){

	
        $( "#start-date-min" ).datepicker({
            defaultDate: "+w",
            numberOfMonths: 1,
            showAnim:"show",
            minDate:0,
            onClose: function( selectedDate ) {
                if (selectedDate==''){
                    selectedDate=0
                }
                $( "#end-date-min" ).datepicker( "option", "minDate", selectedDate );
            }
    });
       
    $( "#end-date-min" ).datepicker({
        defaultDate: "+w",
        numberOfMonths: 1,
        showAnim:"show",
        minDate:0,
        onClose: function( selectedDate ) {
        	if(selectedDate==''){
        		selectedDate=0
        	}
            $( "#start-date-min" ).datepicker( "option", "maxDate", selectedDate );
        }
    });
    
//    $( "#start-date-min" ).datepicker('setDate', $('#start-date-min').val());
//    $( "#end-date-min" ).datepicker('setDate', $('#end-date-min').val());
//    
    
    $( "#start-date-max" ).datepicker({
        defaultDate: "+w",
        numberOfMonths: 1,
        showAnim:"show",
        maxDate:0,
        onClose: function( selectedDate ) {
            if (selectedDate==''){
                selectedDate=0
            }
            $( "#end-date-max" ).datepicker( "option", "minDate", selectedDate );
        }
    });

	$( "#end-date-max" ).datepicker({
	    defaultDate: "+w",
	    numberOfMonths: 1,
	    showAnim:"show",
	    maxDate:0,
	    onClose: function( selectedDate ) {
	    	if (selectedDate==''){
                selectDate=0;
            }
	        $( "#start-date-max" ).datepicker( "option", "maxDate", selectedDate );
	    }
	});
	
	 $( "#start-date" ).datepicker({
	        defaultDate: "+w",
	        numberOfMonths: 1,
	        showAnim:"show",
	        onClose: function( selectedDate ) {
	            if (selectedDate==''){
	                selectedDate=0
	            }
	            $( "#end-date" ).datepicker( "option", "minDate", selectedDate );
	        }
	    });

		$( "#end-date" ).datepicker({
		    defaultDate: "+w",
		    numberOfMonths: 1,
		    showAnim:"show",
		    onClose: function( selectedDate ) {
		        $( "#start-date" ).datepicker( "option", "maxDate", selectedDate );
		    }
		});

    $( "#datepicker" ).datepicker({
        defaultDate: "+1w",
        numberOfMonths: 1,
        minDate:0,
        showAnim:"show",
        onClose: function( getday ) {
            var days= ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'];
            var currentDate = $( "#datepicker" ).datepicker( "getDate").getDay();
            $('#weekpicker').val(days[currentDate])
        }
    });

})

