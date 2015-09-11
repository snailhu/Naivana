

<@override name="title">
添加用户
</@override>

<@override name="link">
	<script src='/Nirvana/static/js/dynatree-master/jquery/jquery-ui.custom.js' type="text/javascript"></script>
    <script src='/Nirvana/static/js/dynatree-master/jquery/jquery.cookie.js' type="text/javascript"></script>
    <link rel='stylesheet' type='text/css' href='/Nirvana/static/js/dynatree-master/dist/skin/ui.dynatree.css'>
   <script src='/Nirvana/static/js/dynatree-master/dist/jquery.dynatree.js' type="text/javascript"></script>
   <script src='/Nirvana/static/js/organization_edit.js' type="text/javascript"></script>
    <script src='/Nirvana/static/js/checkExist.js' type="text/javascript"></script>
    <link rel='stylesheet' type='text/css' href='/Nirvana/static/css/layer_content.css'>
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
        padding: 10px 0;
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
    #tree{
        width:500px;
    }
    .submit-line{
        padding: 15px 30px;
        text-align:right
    }
</style>
</@override>

<@override name="content">
 	         <div class="right-container">
            <!--用户信息-->
            <div class="container">
                <div class="contain" style="border: 1px solid #0066b3;">


                    <div style="height: 25px;background: #0066b3;padding: 0 10px">
                        <span style="line-height: 25px;color: #ffffff">用户信息</span>
                    </div>
                    <div class="user-input-group">
                        <div class="suffix-inline half-part">
                            <span class="suffix-inline user-input-title">姓名</span>
                            <input type="text" id="name" value="${employee.name}">
                        </div>
                        
                           <div class="suffix-inline half-part">
                            <span class="suffix-inline user-input-title">编号</span>
                            <input type="text" id="ecode" value="${employee.ECode}">
                        </div>
                        
                    </div>

                    <div class="user-input-group">
                     	<div class="suffix-inline half-part">
                            <span class="suffix-inline user-input-title">密码</span>
                            <input id="pwd" type="password">
                        </div>
                        
                        <div class="suffix-inline half-part">
                            <span class="suffix-inline user-input-title">重复密码</span>
                            <input id="repwd" type="password">
                        </div>
                       
                    </div>
					
					<div class="user-input-group">
					 	<div class="suffix-inline half-part">
                            <span class="suffix-inline user-input-title">手机号</span>
                            <input type="text" id="phone"value="${employee.user.phone}">
                        </div>
 						
 						  <div class="suffix-inline half-part">
                       		<span class="suffix-inline user-input-title" >锁定账号:</span><select style="width:50px" id="isclose"><option <#if !employee.user.accountNonLocked>selected="selected"</#if> value="true">是</option><option <#if employee.user.accountNonLocked>selected="selected"</#if> value="false">否</option></select>
                        </div>
                        
                        
                     </div>
                     <div class="submit-line" style="border-top:1px solid #e3e3e3">
                       <input type="button" value="保存" class="sure button" id="save">
            		</div> 
                </div>
            </div>
            <!--用户信息结束-->

            <!--职位信息-->
            <div class="container">
                <div class="contain" style="border: 1px solid #0066b3;">


                    <div style="height: 25px;background: #0066b3;padding: 0 10px">
                        <span style="line-height: 25px;color: #ffffff">职位信息</span>
                    </div>


                    <div class="user-input-group">
                        	编辑职位:
                        	
                        	
                        	<div id="tree">
						       
						    </div>
						    <input type="button" style="margin:5px"class="button sure" id="edit" value="添加">
                    </div>
                    <div class="user-input-group">
                        <span id="position-count">已有职位</span>
                        
                        
                        <div id="position-line">
                        	
                        </div>
                    </div>
                    
                     <div class="user-input-group">
                     </div>

                </div>
            </div>
            <!--职位信息结束-->
        
        </div>
</@override>
<@override name="script">
<script>
	var employeeId=${employee.id}
   
   	$("#phone").checkExist({
   		url:'/Nirvana/backend/web/api/checkexist/backend/phone',
   		type:2,
   		errorMessage:"此手机号已被注册",
        tips:"此手机号暂未被使用",
        layertype:3
   	})
</script>
</@override>
<@extends name="organization_header.ftl"/> 