<!DOCTYPE html>
<html>
<head>
    <title>用户租车</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
    </blockquote>

    <input type="hidden" id="userId" name="userId" value="${userId}">
    <!-- 数据表格 -->
    <table id="saleChanceList" class="layui-table" lay-filter="saleChances"></table>

</form>
<script type="text/javascript" src="${ctx}/js/user/look.js">
</script>
</body>
</html>