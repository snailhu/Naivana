

<@override name="title">
用户管理
</@override>

<@override name="link">
		<script src="/Nirvana/static/js/agency_detail.js" type="text/javascript"></script>
		<script src="/Nirvana/static/js/checkExist.js" type="text/javascript"></script>
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
   
</style>
</@override>

<@override name="content">
	 <div class="right-container">

            <!--内容-->
            <div class="container">
                <div class="content-title">
                    <span>基础信息</span>
                </div>
                
                <div class="contain" style="border: 1px solid #0066b3;border-bottom:0;padding:8px 0;">
	                <div class="suffix-inline" style="width: 50%;vertical-align: top">
	                	  		<div class="user-input-group">
	                                <span class="suffix-inline user-input-title">用户名</span>
	                                <input type="text" id="username" value="${dealer.user.username}">
	                            </div>
	                            
	                            <div class="user-input-group">
	                                <span class="suffix-inline user-input-title">密码</span>
	                                <input type="password" id="pwd" >
	                            </div>
	                </div>	
	                
	                <div class="suffix-inline" style="width: 49%;vertical-align: top">
	                	  		<div class="user-input-group">
	                                <span class="suffix-inline user-input-title">电话号码</span>
	                                <input type="text" id="phone" value="${dealer.user.phone}">
	                            </div>
	                            
	                            <div class="user-input-group">
	                                <span class="suffix-inline user-input-title">确认密码</span>
	                                <input type="password" id="rpwd" >
	                            </div>
	                </div>                  
                </div>
                
                <div class="contain" style="border: 1px solid #0066b3;">
                    <div class="input-item downline-blue-1">
                        <div class="suffix-inline" style="width: 50%;vertical-align: top">
                            <div class="user-input-group">
                                <span class="suffix-inline user-input-title">经销商编号</span>
                                <input type="text" id='code' value="${dealer.codeInERP}" readonly="true">
                            </div>
                            <div class="user-input-group">
                                <span class="suffix-inline user-input-title">大区</span>
                                <input type="text" value="${dealer.directorArea.name}" readonly="true">
                            </div>
                            <div class="user-input-group">
                                <span class="suffix-inline user-input-title">渠道</span>
                                <input type="text"  value="${dealer.channel.description}" readonly="true">
                            </div>
                            <div class="user-input-group">
                                <span class="suffix-inline user-input-title">邮编</span>
                                <input type="text"  value="${dealer.storefrontInfo.postalCode}" readonly="true">
                            </div>
                            <div class="user-input-group">
                                <span class="suffix-inline user-input-title">传真号</span>
                                <input type="text" value="${dealer.storefrontInfo.faxNum}" readonly="true">
                            </div>
                            <div class="user-input-group">
                                <span class="suffix-inline user-input-title">备注</span>
                                <input type="text" value="${dealer.notes}" readonly="true">
                            </div>
                              <div class="user-input-group">
                                <span class="suffix-inline user-input-title">关闭经销商</span>
                               	<select id="isClosed"><option <#if !dealer.isClose>selected="selected" </#if> value="false">否</option><option <#if dealer.isClose>selected="selected"</#if> value="true">是</option></select>
                            </div>
                        </div>

                        <div class="suffix-inline" style="width: 49%">
                            <div class="user-input-group">
                               <span class="suffix-inline user-input-title">经销商名称</span>
                                <input type="text"  value="${dealer.storefrontInfo.name}" readonly="true">
                            </div>
                            <div class="user-input-group">
                                <span class="suffix-inline user-input-title">联系人</span>
                                <input type="text"  value="${dealer.storefrontInfo.manager}" readonly="true">
                            </div>
                              <div class="user-input-group">
                                <span class="suffix-inline user-input-title">渠道描述</span>
                                <input type="text" value="${dealer.channel.description}" readonly="true">
                            </div>
                            <div class="user-input-group">
                                <span class="suffix-inline user-input-title">地址</span>
                                <input type="text"  value="${dealer.storefrontInfo.businessAddr}" readonly="true">
                            </div>
                          
                            <div class="user-input-group">
                                <span class="suffix-inline user-input-title">电话手机</span>
                                <input type="text"  value="${dealer.storefrontInfo.contactValue}" readonly="true">
                            </div>
                            <div class="user-input-group">
                                <span class="suffix-inline user-input-title">Email</span>
                                <input type="text"  value="${dealer.storefrontInfo.email}" readonly="true">
                            </div>
                            <div class="user-input-group">
                                <span class="suffix-inline user-input-title">关闭原因</span>
                                <input id="closeReason" type="text" value="${dealer.closeReason}">
                            </div>
                        </div>
                    </div>

                    <div class="clearfix" style="padding: 15px 12px">
                        <div style="float: right">
                            <input type="button" value="确定" class="button sure" id="submit">
                            <input type="button" value="取消" class="button sure" id="cancel">
                        </div>
                    </div>
                </div>
            </div>
            <!--内容结束-->
			

        </div>
</@override>



<@override name="script">
  <script>
		var url='/Nirvana/backend/web/api/dealers/${dealer.id}/edit';
		
	$("#username").checkExist({
   		url:'/Nirvana/backend/web/api/checkexist/dealer/username',
   		type:1,
   		layertype:1
   		
   	})
    
   	$("#phone").checkExist({
   		url:'/Nirvana/backend/web/api/checkexist/dealer/phone',
   		type:2,
   		errorMessage:"此手机号已被注册",
        tips:"此手机号暂未被使用",
        layertype:1
   	})
 	</script>
</@override>
<@extends name="customer_head.ftl"/> 