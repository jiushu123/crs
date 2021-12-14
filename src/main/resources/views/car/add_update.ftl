<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <#--隐藏域 设置车辆的id-->
            <input type="hidden" name="id" value="${(car.id)!}">
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">车名</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-verify="required" name="carName" id="carName" lay-verify="required"  value="${(car.carName)!}" placeholder="请输入汽车名称">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">价格/天</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"  name="carPrice" id="carPrice" lay-verify="required" value="${(car.carPrice)!}" placeholder="请输入汽车价格">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">车牌号</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="carOrder"  lay-verify="required"  value="${(car.carOrder)!}" placeholder="请输入车牌号">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">车辆信息</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入车辆信息" name="description" class="layui-textarea">${(car.description)!}</textarea>
                </div>
            </div>
            <br/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit=""  lay-filter="addOrUpdateCarPage">确认 </button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
                </div>
            </div>
        </form>
    <script type="text/javascript" src="${ctx}/js/rentCar/add.update.js"></script>
    </body>
</html>