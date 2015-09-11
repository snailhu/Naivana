

<@override name="title">
用户管理
</@override>

<@override name="link">
    <script src="/Nirvana/static/js/layer/layer.min.js" type="text/javascript"></script>
    <script src="/Nirvana/static/js/jMenu.js" type="text/javascript"></script>
    <script src="/Nirvana/static/js/jquery.pager.js" type="text/javascript"></script>
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
    .user-input-group input[type=text]{
        width: 65%;
    }
    .user-input-group select{
        width: 30%;
    }
    .half-part{
        width: 49.5%;
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
   
</style>
</@override>

<@override name="content">
	 <div class="right-container">

            <div class="link-line downline-1 clearfix" style="padding:5px 0">
            
			</div>
			
			<div class="link-line  clearfix" style="position: relative;padding:5px 0">
                <a href="/Nirvana/backend/web/customer_management/shop"><span class="href-block clicked float-hack">门店列表</span></a>
                <a href="/Nirvana/backend/web/customer_management/shop/add"><span class="href-block  float-hack">添加用户</span></a>
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
            	<thead><tr><td>门店编号</td><td>门店称号</td><td>门店地址</td><td>联系电话</td><td>小区</td><td>更多</td></tr></thead>
            	<tbody>
            	<#list stores as store>
            		<tr data-id=${store.id}>
            			<td>${store.codeInERP}</td>
            			<td>${store.storefrontInfo.name}</td>
            			<td>${store.storefrontInfo.deliveryAddr}</td>
            			<td>${store.storefrontInfo.phoneNum}</td>
            			<td>${store.agentArea.name}</td>
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
                window.location.href = "/Nirvana/backend/web/customer_management/shop?page="+pageclickednumber;
            };
$("#pager").pager({ pagenumber: ${currentpage}, pagecount: ${totalpage}, buttonClickCallback: PageClick });


   $(document).ready(function(){
        $('.more').dropMenu({
             event:[
                 {
                     content:'编辑用户',
                     callback:function(id){
                         window.location.href="/Nirvana/backend/web/customer_management/shop/"+id+"/detail"
                     }
                 }
             ]
        })
    })
 	</script>
</@override>
<@extends name="customer_head.ftl"/> 