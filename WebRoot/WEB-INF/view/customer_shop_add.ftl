

<@override name="title">
用户管理
</@override>

<@override name="link">
    <script src="/Nirvana/static/js/shop_add.js" type="text/javascript"></script>
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
    	padding:8px;
    }
   
</style>
</@override>

<@override name="content">
	 <div class="right-container">

            <div class="link-line downline-1 clearfix" style="padding:5px 0">
            
			</div>
			
			<div class="link-line  clearfix" style="position: relative;padding:5px 0">
                <a href="/Nirvana/backend/web/customer_management/shop"><span class="href-block float-hack">门店列表</span></a>
                <a href="/Nirvana/backend/web/customer_management/shop/add"><span class="href-block clicked float-hack">添加用户</span></a>
                
            </div>
			
			
			         <div class="container">
                <div class="content-title">
                    <span>账号信息</span>
                </div>
                
                 <div class="contain" style="border: 1px solid #0066b3;border-bottom:0;padding:8px 0;">
      
	                
	                <div class="suffix-inline" style="width: 50%;vertical-align: top">
	                			<div class="user-input-group">
	                                <span class="suffix-inline user-input-title">用户名</span>
	                                <input type="text"  id="username" >
	                            </div>
	                	  		<div class="user-input-group">
	                                <span class="suffix-inline user-input-title">密码</span>
	                                <input type="password"  id="pwd">
	                            </div>
	                            

	                </div>
	                
	                <div class="suffix-inline" style="width: 49%;vertical-align: top">
	                	  		
	                            
	                            <div class="user-input-group">
	                                <span class="suffix-inline user-input-title">手机号</span>
	                                <input type="text" id="phone" >
	                            </div>
	                            <div class="user-input-group">
	                                <span class="suffix-inline user-input-title">重复密码</span>
	                                <input type="password" id="repwd">
	                            </div>
	                </div>
                                   
	             </div>   
                <div class="contain" style="border: 1px solid #0066b3;">
					
	                <div class="content">
                        <div class="user-input-group">
                            <div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">客户编号</span>
                                <input id="codeInERP" type="text" >
                            </div>
                            <div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">客户名称</span>
                                <input id="name" type="text" >
                            </div>
                        </div>

          

                        <div class="user-input-group">
                            <div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">负责人</span>
                                <input id="manager" type="text" >
                            </div>
                              <div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">邮编</span>
                                <input id="postalCode" type="text" >
                            </div>
                        </div>

                        <div class="user-input-group">
                            <div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">电话手机</span>
                                <input id="phoneNum" type="text" >
                            </div>
 							<div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">传真号</span>
                                <input id="faxNum" type="text" >
                            </div> 
                           
                        </div>

                     
                        <div class="user-input-group">
                           
                            <div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">送货地址</span>
                                <input id="deliveryAddr" type="text" >
                            </div>
                             <div class="suffix-inline half-part">
                                <span class="suffix-inline user-input-title">Email</span>
                                <input id="Email" type="text" >
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
            
           
        </div>
</@override>

<@override name="script">
  <script>
		
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