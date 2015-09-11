<@override name="title">发布活动促销信息</@override>



<@override name="style">
<style>
    html, body {
        height: 100%;
        margin: 0;
        padding: 0;
        background:#ebebeb ;
        font-family: 'microsoft yahei';
    }
    .main{
        padding-top: 30px;
        width: 80%;
        min-width:800px;
        margin: 0 auto;
        padding-bottom: 60px;
    }
    .content{
        background: #fff;
        padding-bottom: 30px;
    }
    .container{
        width: 60%;
        padding: 30px;
    }
    .container input[type=text]{
        width:50% ;
        height: 30px;
    }
    .info-label{
        display: inline-block;
        width: 15%;
    }
    .footer{
        padding-top: 30px;
        text-align: center;
    }
   .subnav{
       height: 45px;
       border-bottom: 1px solid #d3d3d3;
       border-top: 1px solid #d3d3d3;
       background: #e3e3e3;
   }
  .center{
      padding: 20px 50px;
      font-size: 15px;

  }
    .sales-item{
        padding: 10px 0;
    }
    .sales-title{
        width: 100px;
        color: #0066b3;
    }
    .sales-input{
        height: 30px;
        width: 30%;
    }
    .sales-item textarea{
        width: 60%;
        resize: none;
    }
    .sales-link{
        padding: 0 15px
    }
    .sales-current{
        background: #fff;
        height: 43px;
        line-height: 43px;
        border-top: 2px solid #0066b3;
    }
    .sales-item img{
    	width:85px;
    	height:85px;
    	margin-right:20px;
    }
    .pic-wrap{
    	display:inline-block;
    	position:relative;
    	vertical-align:top;
    }
    .pic-wrap span{
 		 position: absolute;
	    display: inline-block;
	    width: 30px;
	    height: 30px;
	    top: -12px;
	    right: 2px;
	    cursor: pointer;
	    background: url("/Nirvana/static/img/pepsi/arrow.png");
	    background-position: 0 0 ;
    }
 </style>   
</@override>

<@override name="contain">
     <!--<form action="/Nirvana/backend/promotion/promotions" method="POST">-->
     
	 <div class="center">
	 		
             <div class=" sales-item">
                 <span class="suffix-inline sales-title">促销主题:</span>
                 <input id="title" type="text" class="sales-input" value="${promotion.title}">
             </div>

            <div class="sales-item">
                <span class="suffix-inline sales-title">促销对象:</span>
				<select  class="sales-input" id="channelSelectId"><#list channels as channel><option value="${channel.code}" <#if channel.code == promotion.channel.code>selected=selected</#if>>${channel.description}</option></#list></select>
            </div>


            <div class="sales-item">
                <span class="suffix-inline sales-title">促销时间:</span>
                <input type="text" name="start-time" class="date-input" id="start-date-min" readonly=true value= "${promotion.startDate?substring(0,4)+'-'+promotion.startDate?substring(4,6)+'-'+promotion.startDate?substring(6,8)}">
                <input type="text" name="end-time"class="date-input left" id="end-date-min" readonly=true value="${promotion.endDate?substring(0,4)+'-'+promotion.endDate?substring(4,6)+'-'+promotion.endDate?substring(6,8)}">
            </div>

            <div class="sales-item" rows="5">
                <span class="suffix-inline sales-title" style="vertical-align: top">促销奖励:</span>
                <textarea name="reward" rows="5" id="content" >${promotion.reward}</textarea>
            </div>

            <div class="sales-item" >
                <span class="suffix-inline sales-title" style="vertical-align:top" >签核文件上传:</span>
                <div class="suffix-inline">
	                <div  id="imgArea">
	                	
		                	<div style="display:inline-block;vertical-align:top;">
		                	<input type="file" name="file_upload" id="file_upload"/>
		                	</div> 
		                    <#list promotion.pics as pic>
		                      <div class="pic-wrap">	
		                   	 	<img src="${pic.url}" style="cursor: pointer">
		                   	 	<span></span>
		                   	 </div>
		                    </#list>
	                 
	                      
	                </div>
	                <div style="color:red">
	                    	(*审核文件最多不超过5张)
	                   </div>
                 </div>   
               
            </div>
            
        </div>

         <div class="footer">
             <input type="submit" value="提交" id="submit" class="button sure">
         </div>
    <!--</form>-->
</@override>

<@override name="javascript">
 <script src="/Nirvana/static/js/jquery-ui.min.js" type="text/javascript"></script>
 <script src="/Nirvana/static/js/timepicker_init.js" type="text/javascript"></script>
  <script src="/Nirvana/static/js/jquery.uploadify.js" type="text/javascript"></script>
  <script src="/Nirvana/static/js/sales_rejected.js" type="text/javascript"></script>
 <link rel="stylesheet" href="/Nirvana/static/css/jquery-ui-1.7.2.custom.css">
  <script src="/Nirvana/static/js/jPhoto.js" type="text/javascript"></script>
  <link rel="stylesheet" href="/Nirvana/static/css/photo.css">
</@override>

<@override name="script">
	<script>
		 
		 
		var promotionId=${promotion.id}
		$("#imgArea").photo();
	</script>
</@override>


<@extends name="sales_head.ftl"/> 