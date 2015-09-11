

<@override name="title">
用户管理
</@override>

<@override name="link">
    <script src="/Nirvana/static/js/shop_detail.js" type="text/javascript"></script>
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
   .content{
   		padding:10px 0;
   }
</style>
</@override>

<@override name="content">
	 <div class="right-container">

            <!--内容-->
          <div class="container">
                <div class="content-title">
                    <span>账号信息</span>
                
                </div>
                
                 <div class="contain" style="border: 1px solid #0066b3;border-bottom:0;padding:8px 0;">
      
	                
	                <div class="suffix-inline" style="width: 50%;vertical-align: top">
	                			<div class="user-input-group">
	                                <span class="suffix-inline user-input-title">用户名</span>
	                                <input type="text"  id="username" value="${store.user.username}">
	                            </div>
	                	  		<div class="user-input-group">
	                                <span class="suffix-inline user-input-title">密码</span>
	                                <input type="password"  id="pwd">
	                            </div>
	                            

	                </div>
	                
	                <div class="suffix-inline" style="width: 49%;vertical-align: top">
	                	  		
	                            
	                            <div class="user-input-group">
	                                <span class="suffix-inline user-input-title">手机号</span>
	                                <input type="text" id="phone" value="${store.user.phone}">
	                            </div>
	                            <div class="user-input-group">
	                                <span class="suffix-inline user-input-title">确认密码</span>
	                                <input type="password" id="repwd">
	                            </div>
	                </div>
	                
	                <div class="clearfix" style="padding:15px 12px;">
                    	<div  style="float:right">
                    	<input type="button" value="保存" class="button sure" id="save">	
                    	</div>		
                    </div>
                                   
	             </div>   
                <div class="contain" style="border: 1px solid #0066b3;">
					<div class="content-title">
	                    <span>基础信息</span>
	                </div>
	                <div class="content">
                        <div class="user-input-group">
                            <div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">客户编号</span>
                                <input id="codeInERP" type="text" value="${store.codeInERP}">
                            </div>
                            <div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">客户名称</span>
                                <input id="name" type="text" value="${store.storefrontInfo.name}">
                            </div>
                        </div>

          

                        <div class="user-input-group">
                            <div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">负责人</span>
                                <input id="manager" type="text" value="${store.storefrontInfo.manager}">
                            </div>
                              <div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">邮编</span>
                                <input id="postalCode" type="text" value="${store.storefrontInfo.postalCode}">
                            </div>
                        </div>

                        <div class="user-input-group">
                            <div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">电话手机</span>
                                <input id="phoneNum" type="text" value="${store.storefrontInfo.phoneNum}">
                            </div>
 							<div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">传真号</span>
                                <input id="faxNum" type="text" value="${store.storefrontInfo.faxNum}">
                            </div> 
                           
                        </div>

                     
                        <div class="user-input-group">
                           
                            <div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">送货地址</span>
                                <input id="deliveryAddr" type="text" value="${store.storefrontInfo.deliveryAddr}">
                            </div>
                            
                        </div>
                        
                        
                        <div class="user-input-group">
                           
                            <div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">关闭门店</span>
                                <select id="isClosed"><option <#if !store.isClose>selected="selected" </#if> value="false">否</option><option <#if store.isClose>selected="selected"</#if> value="true">是</option></select>
                            </div>
                            <div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">关闭原因</span>
                                <input id="closeReason" type="text" value="${store.closeReason}">
                            </div>
                        </div>

                    </div>
                    
                    <div class="clearfix" style="padding:15px 12px;">
                    	<div  style="float:right">
                    	<input type="button" value="保存" class="button sure" id="submit">	
                    	</div>		
                    </div>
                </div>
            </div>
            <!--内容结束-->
        </div>
</@override>

<@override name="script">
  <script>
		var store_id=${store.id}
		
		$("#username").checkExist({
   		url:'/Nirvana/backend/web/api/checkexist/store/username',
   		type:1,
   		layertype:1
   		
   	})
    
   	$("#phone").checkExist({
   		url:'/Nirvana/backend/web/api/checkexist/store/phone',
   		type:2,
   		errorMessage:"此手机号已被注册",
        tips:"此手机号暂未被使用",
        layertype:1
   	})
 	</script>
</@override>
<@extends name="customer_head.ftl"/> 