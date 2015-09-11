

<@override name="title">
业务管理
</@override>

<@override name="link">
	<script src="/Nirvana/static/js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="/Nirvana/static/js/timepicker_init.js" type="text/javascript"></script>
	 <link rel="stylesheet" href="/Nirvana/static/css/jquery-ui-1.7.2.custom.css" type="text/css">
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
    .label{
        color: #909090;
    }
    .contain{
        font-size: 14px;
    }
    .ver-bus-table{
        border-collapse: collapse;
        text-align: center;
        *width:240%;
    }
    .ver-bus-table thead{
        background: #0066b3;
        color: #fff;
        *width:11.1%
    }
    .ver-bus-table tr td{
        padding: 5px 0;
        min-width: 60px;
    }

    .sure{
        background: #0066b3;
        color: #fff;
    }
</style>
</@override>

<@override name="content">
 <div class="right-slider">
            <div class="link-line" >
                <a href="/Nirvana/backend/web/business_management/performance_sales"><div class="href-block">销量</div></a>
                <a  href="/Nirvana/backend/web/business_management/performance_productivity"><div class="href-block">生产力<div class="arrow1"></div> </div></a>
            </div>

            <div class="space-line">
            </div>

            <div class="contain">
                <div style="padding: 10px 30px">
                    <div class="input-item">
                        <span>时间筛选:</span><input type="text" id="start-date-max" class="date-input"><input type="text" id="end-date-max" class="left date-input">
                    </div>
                    <div class="input-item">
                        <span>地域筛选:</span><select><option>全部</option></select>
                    </div>
                </div>

                <div style="padding: 15px 0 15px 30px;border-top: 1px solid #e3e3e3;border-bottom: 1px solid #e3e3e3">
                    <input type="button" value="确认" class="button sure">
                    <input type="button" value="取消" class="button sure left">
                </div>


                    <div style="padding: 10px 0">

                    </div>

                <div style="overflow-x: auto">
                    <table class="ver-bus-table">
                        <thead>
                        <tr>
                            <td>大区</td>
                            <td>经理区</td>
                            <td>主任区</td>
                            <td>岗位</td>
                            <td>人员</td>
                            <td>职位</td>
                            <td>服务客户数</td>
                            <td>累计工作日</td>
                            <td>预访客户数</td>
                            <td>实访客户数</td>
                            <td>拜访完成率</td>
                            <td>订货客户数</td>
                            <td>拜访完成率</td>
                            <td>订单销量</td>
                            <td>线内销量</td>
                            <td>线内外销量</td>
                            <td>户均订单销量</td>
                            <td>订货SKU数</td>
                            <td>户均订单SKU数</td>
                            <td>库存客户数</td>
                            <td>拍照客户数</td>
                            <td>总拜访时间</td>
                            <td>户均拜访时间</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>南京</td>
                            <td>宁南</td>
                            <td>宁南DSD</td>
                            <td>NN80</td>
                            <td>路人甲</td>
                            <td>业务员</td>
                            <td></td>
                            <td>5</td>
                            <td>111111 1111</td>
                            <td>11</td>
                            <td>5</td>
                            <td>11</td>
                            <td>11</td>
                            <td>5</td>
                            <td>11</td>
                            <td>11</td>
                            <td>5</td>
                            <td>11</td>
                            <td>11</td>
                            <td>11</td>
                            <td>11</td>
                            <td>03:20</td>
                            <td>03:20</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
</@override>
<@extends name="business_head.ftl"/> 