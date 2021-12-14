layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 车辆列表展示
     */
    var tableIns = table.render({
        elem: '#carList', // 表格绑定的ID
        url : ctx + '/car/manageCar_list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "carListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'车牌号',align:"center"},
            {field: "carName", title:'车名',align:"center"},
            {field: 'carPrice', title: '价格/天',align:"center"},
            {field: 'carOrder', title: '车牌号',align:"center"},
            {field: 'description', title: '车辆信息', align:'center'},
            {field: 'assignTime', title: '出厂时间', align:'center'},
            {field: 'carState', title: '租赁状态', align:'center',templet:function(d){
                    return formatterState(d.carState);
                }
            },
            {title: '操作',
                templet:'#carListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });

    /**
     * 绑定搜索按钮的点击事件
     */
    $(".search_btn").click(function () {
        table.reload('carListTable', {
            where: { //设定异步数据接口的额外参数，任意设
                carName: $("input[name='carName']").val(), //车名
                carPrice: $("input[name='carPrice']").val(), //价格
                carState: $("#state").val() // 租赁状态
            }
            ,page: {
                curr: 1 // 重新从第 1 页开始
            }
        }); // 只重载数据
    });



    /**
     * 头部工具栏 监听事件
     */
    table.on('toolbar(cars)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus);
        switch(obj.event){
            case 'add':
                // 点击添加按钮，打开添加营销机会的对话框
                openAddOrUpdateSaleChanceDialog();
                break;
            case 'del':
                // 点击删除按钮，将对应选中的记录删除
                deleteSaleChance(checkStatus.data);
        };
    });



    /**
     * 表格行 监听事件
     * saleChances为table标签的lay-filter 属性值
     */
    table.on('tool(cars)', function(obj){
        var data = obj.data;
        // 获得当前行数据
        var layEvent = obj.event;
        // 获得 lay-event 对应的值（也可以是表头的 event 参数对应的值
        // 判断事件类型
        if(layEvent === 'edit'){ // 编辑操作
            // 获取当前要修改的行的id
            var saleChanceId = data.id;
            // 点击表格行的编辑按钮，打开更新营销机会的对话框
            openAddOrUpdateSaleChanceDialog(saleChanceId);
        }else if (layEvent == "del") { // 删除操作
            // 询问是否确认删除
            layer.confirm("确定要删除这条记录吗？", {icon: 3, title:"营销机会数据管理"},
                function (index) {
                    // 关闭窗口
                    layer.close(index);
                    // 发送ajax请求，删除记录
                    $.ajax({
                        type:"post",
                        url: ctx + "/car/manageCar_delete",
                        data:{
                            ids:data.id
                        },
                        dataType:"json",
                        success:function (result) {
                            if (result.code == 200) {
                                // 加载表格
                                tableIns.reload();
                            } else {
                                layer.msg(result.msg, {icon: 5});
                            }
                        }
                    });
                });
        }
    });

    /**
     * 打开添加营销机会的对话框
     */
    function openAddOrUpdateSaleChanceDialog(saleChanceId) {
        var title = "<h2>车辆管理 - 车辆添加</h2>";
        var url = ctx + "/car/manageCar_addOrUpdateCarPage";

        // 通过id判断是添加操作还是修改操作
        if (saleChanceId) {

            // 如果id不为空，则为修改操作
            title = "<h2>车辆管理 - 车辆更新</h2>";
            url = url + "?id=" + saleChanceId;
        }
        //打开小窗口跳转
        layui.layer.open({
            title:title,
            type:2, //iframe
            content: url,
            area:["500px","620px"],
            maxmin:true
        });
    }

    /**
     * 删除营销机会数据
     * @param data
     */
    function deleteSaleChance(data) {
        // 判断用户是否选择了要删除的记录
        if (data.length === 0) {
            layer.msg("请选择要删除的记录！");
            return;
        }
        // 询问用户是否确认删除
        layer.confirm("您确定要删除选中的记录吗？",{
            btn:["确认","取消"],
        },function (index) {
            // 关闭确认框
            layer.close(index);
            // ids=1&ids=2&ids=3
            var ids = "ids=";
            // 遍历获取对应的id
            for (var i = 0; i < data.length; i++) {
                if (i < data.length - 1) {
                    ids = ids + data[i].id + "&ids=";
                } else {
                    ids = ids + data[i].id;
                }
            }
            // 发送ajax请求，删除记录
            $.ajax({
                type:"post",
                url: ctx + "/car/manageCar_delete",
                data:ids, // 参数传递的是数组
                dataType:"json",
                success:function (result) {
                    if (result.code === 200) {
                    // 加载表格
                        tableIns.reload();
                    } else {
                        layer.msg(result.msg, {icon: 5});
                    }
                }
            });
        });
    }


    /**
     * 格式化分配状态
     * 0 - 未分配
     * 1 - 已分配
     * 其他 - 未知
     * @param state
     * @returns {string}
     */
    function formatterState(carState){
        if(carState==1) {
            return "<div style='color: green'>可租赁</div>";
        } else {
            return "<div style='color: red'>不可租赁</div>";
        }
    }

});

