<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />	
	<div class="nav">
	    <div class="logo-bar">
	
	        <div class="wrap">
	            <div class="hack">
	                <div class="cnt">
	                	<a href="/Nirvana/backend/web/center">
	                    <img src="/Nirvana/static/img/pepsi/logo-min.png" style="vertical-align: middle">
	                	</a>	
	                </div>
	            </div>
	        </div>
	
	        <div class="wrap">
	            <div class="hack right">
	                <div class="cnt ">
	                    <#if username != ''><a href="#" >${username}</a></#if><#if username == ''><a href="/Nirvana/backend/web/login.html">登录</a></#if>
	                    <#if username != ''><a href="/Nirvana/backend/web/logout">注销</a></#if>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	
	