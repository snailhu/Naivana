

<@override name="title">
人事管理
</@override>

<@override name="link">
    <script src="/Nirvana/static/js/jquery.pager.js" type="text/javascript"></script>
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
    .content{
        background: #fff;
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
    .user-table tbody tr{
    	 cursor:pointer;
    }
    .user-table thead td{
        width: 11.1%;
        padding: 10px 0;
    }
    .user-table tbody td{
        padding: 8px 0;
    }
    .double{
        background: #ebebeb;
    }
    .status-line{
        padding: 12px 0 12px;
        margin-left: 10px;
    }
</style>
</@override>

<@override name="content">
 	<div class="right-container">
            <div class="link-line downline clearfix">
                <a class="float-hack" href="/Nirvana/backend/web/organization_management/list"><div class="href-block clicked">用户列表</div></a>
                <a href="/Nirvana/backend/web/organization_management/add" class="float-hack"><div class="href-block">添加用户</div></a>
            </div>
            <div class="status-line">

            </div>
            <div>

                <table class="user-table">
                    <thead>
                    <tr>
                        <td>编号</td>
                        <td>用户名</td>
                        <td>联系电话</td>
                        <td>姓名</td>
                        <td>用户状态</td>
                    </tr>
                    </thead>
                    <tbody>
                     <#list employees as employee>
                    <tr data-id=${employee.id}>
                    	<td>${employee.ECode}</td>
                    	<td>${employee.user.username}</td>
                    	<td>${employee.user.phone}</td>
                    	<td>${employee.name}</td>
                    	<td><#if !employee.user.accountNonLocked><img src="/Nirvana/static/img/pepsi/lock.png"></#if></td>
                    </tr>
                    </#list>
					</tbody>
                </table>
            </div>
            <div id='pager' style='text-align: center'></div>
        </div>
        
        
</@override>
<@override name="script">
<script>
	PageClick = function(pageclickednumber) {
                window.location.href = "/Nirvana/backend/web/organization_management/list?page="+pageclickednumber;
            };
	$("#pager").pager({ pagenumber: ${currentpage}, pagecount: ${totalpage}, buttonClickCallback: PageClick });
	
   $('.user-table tr').dblclick(function(){
   var url='/Nirvana/backend/web/organization_management/{id}/edit';
   url=url.replace('{id}',$(this).attr('data-id'));
   	window.location.href=url
   })
    

</script>
</@override>
<@extends name="organization_header.ftl"/> 