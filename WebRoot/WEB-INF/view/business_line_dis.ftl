

<@override name="title">
业务管理
</@override>

<@override name="link">
<script src="/Nirvana/static/js/up_down.js" type="text/javascript"></script>
<script src="/Nirvana/static/js/line_dis.js" type="text/javascript"></script>
<script src="/Nirvana/static/js/layer/layer.js" type="text/javascript"></script>
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
    .double{
        background: #ebebeb;
    }
    .container{
        width: 60%;
        padding: 30px;
    }
    .disable{
        background: #ebebeb;
        color: #909090;
        cursor:auto;
    }
    .button-dis{
        width: 30px;
        padding: 5px 0;
        margin-bottom: 5px;
    }
    .contain{
        font-size: 14px;
    }
    .contain-nav{
        padding: 10px 20px;
    }
    .content-title{
        display: inline-block;
        width: 100px;
        text-align: right;
        margin-right: 10px;
    }
    .content-title{
        *display: inline;
    }
    .content-radio{
        width: 45px;
    }
    .left-box,.right-box{
        float: left;width: 42%;height: 100%
    }
    .select-range{
        border: 1px solid #d3d3d3;height:92%;overflow-y: auto;padding: 0 5%
    }
    .today{
    	color:red;
    }
    .direct_store label{
    	color:red
    }
</style>
</@override>

<@override name="content">
 <div class="right-slider">
            <div class="contain">
                <div class="downline-1 contain-nav">
					<div class="input-item">
						<div class="content-title">选择主任区</div><select id="position"><#list directorAreas as area><option value=${area.id}>${area.name}</option></#list></select>
					</div>
					
                    <div class="input-item">
                        <div class="content-title">选择小区</div><select id="job"></select>
                    </div>

                    <div class="input-item">
                        <div class="content-title" style="vertical-align: top">拜访线路</div><!--
                        --><div class="suffix-inline" style="border: 1px solid #d3d3d3;padding: 5px">
                            <div>
                                <div class="suffix-inline content-radio">
                                    <input type="radio" id="1" name="line"><label for="1" >1A</label>
                                </div>
                                <div class="suffix-inline content-radio">
                                    <input type="radio" id="2" name="line"><label for="2">2A</label>
                                </div>
                                <div class="suffix-inline content-radio">
                                    <input type="radio" id="3" name="line"><label for="3">3A</label>
                                 </div>
                                <div class="suffix-inline content-radio">
                                    <input type="radio" id="4" name="line"><label for="4">4A</label>
                                 </div>
                                <div class="suffix-inline content-radio">
                                    <input type="radio" id="5" name="line"><label for="5">5A</label>
                                 </div>
                            </div>
                            <div>
                                <div class="suffix-inline content-radio">
                                    <input type="radio" id="6" name="line"><label for="6">1B</label>
                                </div>
                                <div class="suffix-inline content-radio">
                                    <input type="radio" id="7" name="line"><label for="7">2B</label>
                                 </div>
                                <div class="suffix-inline content-radio">
                                    <input type="radio" id="8" name="line"><label for="8">3B</label>
                                 </div>
                                <div class="suffix-inline content-radio">
                                    <input type="radio" id="9" name="line"><label for="9">4B</label>
                                 </div>
                                <div class="suffix-inline content-radio">
                                    <input type="radio" id="10" name="line"><label for="10">5B</label>
                                 </div>
                            </div>
                        </div>
                    </div>
					
					<div class="input-item">
					<div class="content-title"></div>
						<select id="shop-type">
							<option value='storesnotin'>门店</option>
							<option value='dealersnotin'>直营店</option>
						</select>
					</div>
					
                    <div class="input-item">
                        <div class="content-title"></div><input type="button" class="button sure" id="query" value="查询">
                    </div>
                </div>

                <div>

                </div>

                <div style="padding: 2% 15%">
                    <div class="clearfix" style="height: 300px">
                        <div class="left-box" >
                            <div style="height: 8%">
                            计划拜访门店(拜访顺序)
                            </div>
                            <div class="select-range">
                                <ul id="distribution">
                                    
                                </ul>

                            </div>

                        </div>

                        <div  style="float: left;width: 15%;height: 100%;">
                            <div style="position: relative;height: 100%">
                                <div style="position: absolute;top: 32%;left: 30%">
                                <input type="button" value="<" id="single-dis" class="button-dis"></br>
                                <input type="button" value="<<" id="all-dis" class="button-dis"></br>
                                <input type="button" value=">" id="single-undis" class="button-dis"></br>
                                <input type="button" value=">>" id="all-undis" class="button-dis"></br>
                                </div>
                            </div>
                        </div>

                        <div class="right-box">
                            <div style="height: 8%">
                            当前可分配门店
                            </div>
                            <div class="select-range">
                                <ul id="undistribution">
                                    
                                </ul>
                            </div>
                        </div>
                    </div>
					<div>
						*计划拜访线路边<b style="color:red">红色</b>字体为<b style="color:red">直营店</b>用户。</br>
						*移除用户时,请先查询相应的用户。
					</div>
                    <div  style="padding: 20px 0;text-align: center">
                        <input type="button" value="上移" id="up">
                        <input type="button" value="下移" id="down">
                        <input type="button" value="保存" id="save">
                    </div>
                </div>
            </div>

        </div>
</@override>



<@override name="script">
<script>
	$("#${code}").parent().addClass("today");
</script>	
</@override>

<@extends name="business_head.ftl"/> 