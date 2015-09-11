<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Login</title>

    <link href="/Nirvana/static/css/common.css" rel="stylesheet" type="text/css" >


</head>
<style>
    .username, .pwd{
        width: 100%;
        margin:5px 0;
        border-radius: 4px;
    }
    .login-form{
        width: 30%;
        height: 480px;
        position: absolute;
        top: 0; left: 0; bottom: 10%; right: 0;
        min-width: 300px;
        margin: auto;
    }
    .logo-form{
        text-align: center;
        padding: 10% 0;
    }
    .remember-me{
        color: #808080;
        padding-left: 5px;
        vertical-align: middle;
        font-family: 'microsoft yahei';
    }
    #submit{
        background: #0c57ac;
        width: 100%;
        border: 0;
        color: #fff;
        height: 50px;
        outline: none;
        font-family: "microsoft yahei";
        font-size: 20px;
        letter-spacing: 8px;
        cursor: pointer;
    }
    #submit:active{
        background: #054790;
    }
    .login-input{
        height: 50px;
        margin-bottom: 20px;
    }
    .login-input .placeholder{
        height: 50px;
        line-height: 50px;
    }
    .login-input .form-control{
        width: 97.5%;
        height: 48px;
        line-height: 48px;
    }
    .forgot-pwd{
        float:right;
        color: red;
        text-decoration: none;
        font-family:'microsoft yahei'
    }
</style>
<body>
<div class="container">
    <div class="login-form">
        <div class="logo-form">
            <img src="/Nirvana/static/img/pepsi/pepsi_logo.png">
        </div>
		<form action="/Nirvana/backend/web/login" method="POST" id="myform">
        <div class="group-input login-input">
            <label for="username" class="placeholder">用户名</label>
            <input type="text" id="username" class="form-control" name="username">
        </div>

        <div class="group-input login-input">
            <label for="password" class="placeholder">密码</label>
            <input type="password" id="password" class="form-control" name="password">
        </div>

        <div style="vertical-align: middle" class="clearfix">
            <div style="float: left">
            <input type="checkbox" id="remember-me" name="_spring_security_remember_me"><label for="remember-me" style="color: #999">记住登录状态</label>
            </div>
            <a href="/Nirvana/backend/web/resetpwd/step1" class="forgot-pwd">忘记密码?</a>
        </div>
	    </form>
	    <div style="color:red;padding:15px 0">
	    	${SPRING_SECURITY_LAST_EXCEPTION.message}
	    </div>		
        <div >
            <input type="button" id="submit" value="登录" >
        </div>
    </div>

</div>
</body>
    <script src="/Nirvana/static/js/jquery-1.8.3.min.js"></script>
    <script src="/Nirvana/static/js/placeholder.js"></script>
  	<script src="/Nirvana/static/js/login.js"></script>
  	<script src="/Nirvana/static/js/layer/layer.js"></script>

</html>