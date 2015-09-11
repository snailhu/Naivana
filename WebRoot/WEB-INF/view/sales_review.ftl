<@override name="title">审核促销信息</@override>


<@override name="css">
 	
</@override>


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
        padding-bottom: 60px;
    }

    .subnav{
        height: 45px;
        border-bottom: 1px solid #d3d3d3;
        border-top: 1px solid #d3d3d3;
        background: #e3e3e3;
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
    .sales-history{
        padding: 20px 5%;
        position: relative;
    }
    .sales-history-time{
        position: absolute;
        right: 5%;
    }
 </style>   
</@override>

<@override name="contain">
	   	
	   	<#list promotions as promotion>
        <div class="center">
        <a href="/Nirvana/backend/web/sales_management/${promotion.id}/review">
            <div class="sales-history downline-1">
            
                <div class="suffix-inline sales-history-title">
                    	${promotion.title}
                </div>
                <div class="suffix-inline sales-history-time">
                   ${promotion.createDate}
                </div>
              
            </div>
         </a>     
        </div>
		</#list>
		
     <div id='pager' style='text-align: center'></div>
</@override>

<@override name="javascript">
    <script src="/Nirvana/static/js/jquery.pager.js" type="text/javascript"></script>
	<script src="/Nirvana/static/js/jMenu.js" type="text/javascript"></script>
	<link rel="stylesheet" href="/Nirvana/static/css/layer_content.css" type="text/css">
    <link rel="stylesheet" href="/Nirvana/static/css/Pager.css" type="text/css">
</@override>


<@override name="script">
	<script>
	PageClick = function(pageclickednumber) {
                window.location.href = "/Nirvana/backend/web/sales_management/review?page="+pageclickednumber;
            };
$("#pager").pager({ pagenumber: ${currentpage}, pagecount: ${totalpage}, buttonClickCallback: PageClick });
	</script>
</@override>
	
<@extends name="sales_head.ftl"/> 