<!DOCTYPE html>
<html>
<head>
    <title>我的租车历史</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="carName" class="layui-input searchVal" placeholder="车名" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="carOrder" class="layui-input searchVal" placeholder="车牌号" />
                </div>

                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>
    <!-- 数据表格 -->
    <table id="carChanceList" class="layui-table" lay-filter="carChances"></table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">

            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                还车
            </a>
        </div>
    </script>

    <!--操作-->
    <script id="saleChanceListBar" type="text/html">
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">还车</a>
    </script>

</form>
<script type="text/javascript" src="${ctx}/js/car/scan_car.js">
</script>
</body>
</html>