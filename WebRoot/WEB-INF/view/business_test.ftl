

<@override name="title">
业务管理
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
        width: 25%;
        padding: 10px 0;
    }
    .user-table tbody td{
        padding: 8px 0;
    }
    .left-slider ul li:last-child{
        border: none;
    }
    .double{
        background: #ebebeb;
    }
    .status-line{
        padding: 12px 0 12px;
        margin-left: 10px;
    }
    .user-table tr td img{
        cursor: pointer;
    }
    .label{
        color: #909090;
    }
    .fresh-logo{
        padding:0 12px;
        border-right: 1px solid #d3d3d3;
    }
    .edit-logo{
        padding:0 12px;
    }
</style>
</@override>

<@override name="content">
 <div class="status-line">
                <span class="float-hack">用户205位</span>
                <div style="float: right">
                    <img src="/backend/Nirvana/static/img/pepsi/fresh.png" class="fresh-logo"><!--
                    --><img src="/Nirvana/static/img/pepsi/see.png" class="edit-logo">
                </div>
            </div>

            <!--右侧内容开始-->
            <table class="user-table" style="width: 100.1%">
                    <thead>
                    <tr>
                        <td>小区号</td>
                        <td>业务姓名</td>
                        <td>联系电话</td>
                        <td>更多</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="single">
                        <td>10021</td>
                        <td>蒙奇奇</td>
                        <td>15061231489</td>
                        <td><div><img src="/Nirvana/static/img/pepsi/more.png" onclick="droplist(this)"></div>

                        </td>
                    </tr>
                    <tr class="double">
                        <td>10021</td>
                        <td>蒙奇奇</td>
                        <td>15061231489</td>
                        <td><div><img src="/Nirvana/static/img/pepsi/more.png"  onclick="droplist(this)"></div>

                        </td>
                    </tr>
                    </tbody>
                </table>
</@override>
<@extends name="business_head.ftl"/> 