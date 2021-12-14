<!DOCTYPE html>
<html>
<head><#include "../common.ftl"> </head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">

        <input type="hidden" name="id" id="id" value="${module.id}">
        <div class="layui-form-item layui-row layui-col-xs12"><label class="layui-form-label">权限名</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input userName" lay-verify="required"
                       name="moduleName" id="moduleName"
                       placeholder="请输入权限名" value="${module.moduleName}">
            </div>
        </div>

        <div class="layui-form-item layui-row layui-col-xs12"><label class="layui-form-label">权限码</label>
            <div class="layui-input-block"><input type="text" class="layui-input userName" lay-verify="required"
                       name="optValue" id="optValue"
                       placeholder="请输入权限码" value="${module.optValue}"></div>
        </div>

        <div class="layui-form-item layui-row layui-col-xs12"><label class="layui-form-label">父级权限ID</label>
            <div class="layui-input-block"><input type="text" class="layui-input userName"
                         lay-verify="required" name="parentId"
                         id="parentId" placeholder="请输入父级权限ID" value="${module.parentId}"></div>
        </div>

        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">菜单级别</label>
            <div class="layui-input-block">
                <select name="grade">
                    <option value="0" selected="selected">一 级菜单</option>
                    <option value="1" selected="selected">二 级菜单</option>
                    <option value="2" selected="selected">三 级菜单</option>
                </select>
            </div>
        </div>


    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="updateModule">确认</button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/module/update.js"></script>
</body>
</html>