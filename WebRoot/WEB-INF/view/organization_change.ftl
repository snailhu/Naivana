

<@override name="title">
添加用户
</@override>

<@override name="link">
	<script src='/Nirvana/static/js/dynatree-master/jquery/jquery-ui.custom.js' type="text/javascript"></script>
    <script src='/Nirvana/static/js/dynatree-master/jquery/jquery.cookie.js' type="text/javascript"></script>
    <link rel='stylesheet' type='text/css' href='/Nirvana/static/js/dynatree-master/dist/skin/ui.dynatree.css'>
   <script src='/Nirvana/static/js/dynatree-master/dist/jquery.dynatree.js' type="text/javascript"></script>
   <link rel='stylesheet' type='text/css' href='/Nirvana/static/css/layer_content.css'>
   <script src='/Nirvana/static/js/organization_change.js' type="text/javascript"></script>
   
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
    .right-container{
    	padding-top:20px;
    	min-height:200px;
    }
    .hide{
    	display:none;
    }
</style>
</@override>

<@override name="content">
 	          <div class="right-container">
 	          	<div id="tree">
 	          		
			 </div>

                 			<input type="button" id="addnode" class="button sure" value="添加">
                 			<input type="button" id="closenode" class="button sure hide" value="关闭">
                 			<input type="button" id="activenode" class="button sure hide" value="激活">
 	          </div>
</@override>

<@override name="script">
<script>
	


</script>
</@override>
<@extends name="organization_header.ftl"/> 