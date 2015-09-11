<@override name="title">详细信息</@override>



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
    	width:100px;
    	height:100px;
    }
 </style>   
</@override>

<@override name="contain">
     <!--<form action="/Nirvana/backend/promotion/promotions" method="POST">-->
     
	 <div class="center">
	 		
             <div class=" sales-item">
                 <span class="suffix-inline sales-title">促销主题:</span>
                 <span>${promotion.title}</span>
             </div>

            <div class="sales-item">
                <span class="suffix-inline sales-title">促销对象:</span>
                <span>${promotion.channel.description}</span>
            </div>


            <div class="sales-item">
                <span class="suffix-inline sales-title">促销时间:</span>
                <span> ${promotion.startDate?substring(0,4)+'-'+promotion.startDate?substring(4,6)+'-'+promotion.startDate?substring(6,8)}</span>——
                <span> ${promotion.endDate?substring(0,4)+'-'+promotion.endDate?substring(4,6)+'-'+promotion.endDate?substring(6,8)}</span>
            </div>

            <div class="sales-item" rows="5">
                <span class="suffix-inline sales-title" style="vertical-align: top">促销奖励:</span>
                <span>${promotion.reward}</span>
            </div>

            <div class="sales-item" >
                <span class="suffix-inline sales-title" style="vertical-align:top" >签核文件:</span>
                <div class="suffix-inline" id="imgArea">
                	<#list promotion.pics as pic>
                	<div class="pic-wrap">
                   	 <img src="${pic.url}" style="cursor: pointer">
                   	 </div>
                    </#list>
                </div>
            </div>
            
        </div>

       
    <!--</form>-->
</@override>

<@override name="javascript">
 <script src="/Nirvana/static/js/jPhoto.js" type="text/javascript"></script>
  <link rel="stylesheet" href="/Nirvana/static/css/photo.css">
</@override>

<@override name="script">
	<script>
	$("#imgArea").photo();
	</script>
</@override>


<@extends name="sales_head.ftl"/> 