

<@override name="title">
添加用户
</@override>

<@override name="link">
 	<script src="/Nirvana/static/js/layer/layer.js" type="text/javascript"></script>
    <script src="/Nirvana/static/js/jquery.pager.js" type="text/javascript"></script>
 
    <script src="/Nirvana/static/js/jDroplist.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/Nirvana/static/css/layer_content.css" type="text/css">
    <script src="/Nirvana/static/js/checkExist.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/Nirvana/static/css/Pager.css" type="text/css">
</@override>

<@override name="style">
<style>
 body{
        background: #ebebeb;
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
    .fresh-logo{
        padding-right: 20px;
        border-right: 1px solid #d3d3d3;
    }
    .edit-logo{
        padding-left: 20px;
    }
    .container{
        width: 60%;
        padding: 30px;
    }
    .container input[type=text],.container input[type=password]{
        width:50% ;
        height: 30px;
    }
    .info-label{
        display: inline-block;
        width: 20%;
    }
    .footer{
        padding-top: 30px;
        padding-left: 15%;
    }
</style>
</@override>

<@override name="content">
 	          <div class="right-container">
            <div class="link-line downline clearfix">
                <a class="float-hack" href="/Nirvana/backend/web/organization_management/list"><div class="href-block">用户列表</div></a>
                <a href="/Nirvana/backend/web/organization_management/add" class="float-hack "><div class="href-block clicked">添加用户</div></a>
            </div>


            <div>
                <div class="container">
                    <div class="input-item">
                        <span class="info-label">用户名:</span>
                        <input type="text" id="username">
                    </div>
                    <div class="input-item">
                        <span class="info-label">密码:</span>
                        <input type="password" id="pwd">
                    </div>
                    <div class="input-item">
                        <span class="info-label">重复密码:</span>
                        <input type="password" id="repwd">
                    </div>
                    <div class="input-item">
                        <span class="info-label">编号:</span>
                        <input type="text" id="eCode">
                    </div>
                    <div class="input-item">
                        <span class="info-label">姓名:</span>
                        <input type="text" id="realName">
                    </div>
                    <div class="input-item">
                        <span class="info-label">手机号:</span>
                        <input type="text" id="phoneNum">
                    </div>
                    <div class="footer">
                        <input type="button" id='submit' class="button sure" value="确认添加">
                        <input type="button" id='cancel' class="button cancel" value="取消">
                    </div>
                </div>
            </div>
        </div>
</@override>
<@override name="script">
<script>
	$('#submit').click(function(){
		var $username=$('#username').val(),
		$pwd=$('#pwd').val(),
		$repwd=$('#repwd').val(),
		$eCode=$('#eCode').val(),
		$realName=$('#realName').val(),
		$phoneNum=$('#phoneNum').val();
		re=/^1[3|5|7|8][0-9]{9}$/;
            if(!re.exec($phoneNum)){
                layer.msg('手机号码格式不正确',{icon:7})
                $('#phone').val('');
                return false
               
            }
		if($repwd!=$pwd){
			layer.msg('密码不一致',{icon:7})
			return false;
		}
		
		if($username && $pwd && $repwd && $eCode && $realName && $phoneNum ){
			var url="/Nirvana/backend/web/api/employees";
			var loading=layer.msg('数据提交中，请稍等...', {icon: 16,shade: [0.8, '#393D49']});
			$.ajax({
		                type:'POST',
		                url:url,
		                data:{
		                    username:$username,
		                    phoneNum:$phoneNum,
		                    realName:$realName,
		                    eCode:$eCode,
		                    password:$pwd
		                },
		                success:function(data){
		                layer.close(loading);
		                	if(data.response=='success'){
		                    	 layer.msg('添加成功!',{icon:1})
		                    }
		                    else{
		                    	layer.msg(data.data.text,{icon:2})
		                    }
		                },
		                error:function(data){
		                	layer.close(loading);
		                    layer.msg("内部错误,请重试!",{icon:2})
		                }
		            });
		}
		else{
			ayer.msg("请将内容填写完整!",{icon:7})
		}
	})
   
   	
   	$("#username").checkExist({
   		url:'/Nirvana/backend/web/api/checkexist/backend/username',
   		type:1
   	})
    
   	$("#phoneNum").checkExist({
   		url:'/Nirvana/backend/web/api/checkexist/backend/phone',
   		type:2,
   		errorMessage:"此手机号已被注册",
        tips:"此手机号暂未被使用"
   	})

</script>
</@override>
<@extends name="organization_header.ftl"/> 