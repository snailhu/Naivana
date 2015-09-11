

<@override name="title">
用户管理
</@override>

<@override name="link">
    <script src="/Nirvana/static/js/jquery.pager.js" type="text/javascript"></script>
	<script src="/Nirvana/static/js/jMenu.js" type="text/javascript"></script>
	<script src="/Nirvana/static/js/customer_agency.js" type="text/javascript"></script>
	<link rel="stylesheet" href="/Nirvana/static/css/layer_content.css" type="text/css">
    <link rel="stylesheet" href="/Nirvana/static/css/Pager.css" type="text/css">
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
    .container{
        margin-top: 20px;
        width: 100%;
        font-size: 14px;
    }
    .user-input-title{
        width: 30%;
        text-align: right;
    }
    .user-input-group{
        padding: 3px 0;
    }
    .user-input-group input[type=text],.user-input-group input[type=password]{
        width: 65%;
    }
    
    select{
        width: 30%;
    }
     .user-table{
        border-collapse: collapse;
        width: 100%;
        text-align: center;
        font-size: 14px;
    }
    .user-table thead{
        background: #0066b3;
        color: #fff;
    }
    .user-table thead td{
        width: 11.1%;
        padding: 10px 0;
    }
    .user-table tbody td{
        padding: 8px 0;
    }
 	.button-dis{
 		width:35px;
 		height:30px;
 		margin-top:8px;
 	}  
</style>
</@override>

<@override name="content">
	 <div class="right-container">

            <div class="link-line downline-1 clearfix" style="padding:5px 0">
            
			</div>
			
			<div class="link-line clearfix" style="position: relative;padding:5px 0">
                <span class="href-block clicked float-hack">经销商列表</span>
                <div class="search-wrap">
                    <div class="hack">
                        <div class="cnt">
                            <div class="group-input search-box">
                                <label for="search" class="placeholder">区或客户编码</label>
                                <input type="text" id="search" class="form-control">
                                <img src="/Nirvana/static/img/pepsi/serch.png">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
			
            <table class="user-table">
            	<thead><tr><td>经销商编号</td><td>经销商名称</td><td>联系电话</td><td>地址</td><td>用户状态</td><td>更多</td></tr></thead>
            	<tbody>
            		<#list dealers as dealer>
            		<tr data-id="${dealer.id}" original="${dealer.isClose?string("yes", "no")}">
            			<td>${dealer.codeInERP}</td>
            			<td>${dealer.storefrontInfo.name}</td>
            			<td>${dealer.storefrontInfo.contactValue}</td>
            			<td>${dealer.storefrontInfo.businessAddr}</td>
            			<td><#if dealer.isClose><img src="/Nirvana/static/img/pepsi/lock.png"></#if></td>
            			<td><div class="more"><img src="/Nirvana/static/img/pepsi/more.png" ></div></td>
            		</tr>
            		</#list>
            	</tbody>
            </table>
            <div id='pager' style='text-align: center'></div>
        </div>
</@override>



<@override name="script">
  <script>
	PageClick = function(pageclickednumber) {
                window.location.href = "/Nirvana/backend/web/customer_management/direct_sale?page="+pageclickednumber;
            };
$("#pager").pager({ pagenumber: ${currentpage}, pagecount: ${totalpage}, buttonClickCallback: PageClick });

	
 
 	</script>
</@override>
<@extends name="customer_head.ftl"/> 