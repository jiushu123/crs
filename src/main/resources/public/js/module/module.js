layui.use(['table', 'layer', ], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /*** 绑定搜索按钮的点击事件 */
    $(".search_btn").on("click", function () {
        table.reload("userListTable", {
            page: {
                curr: 1
                //重新从第 1 页开始
            }, where: {
                moduleName: $("input[name='moduleName']").val(), //权限名
                optValue: $("input[name='optValue']").val(), //权限码
            }
        })
    });

    /**
     * 用户列表展示
     */
    var tableIns = table.render({
        elem: '#userList',
        url: ctx + '/module/list',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "userListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'moduleName', title: '权限名', minWidth: 50, align: "center"},
            {field: 'optValue', title: '权限码', minWidth: 100, align: 'center'},
            {field: 'createDate', title: '创建时间', align: 'center', minWidth: 150},
            {field: 'updateDate', title: '更新时间', align: 'center', minWidth: 150},
            {title: '操作', templet: '#userListBar', fixed: "right", align: "center", minWidth: 150}
        ]]
    });

    //监听工具行
    table.on('tool(users)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
       if (layEvent === 'edit') {
            // 记录修改
            openUpdateModuleDialog(data.id);
        } else if (layEvent === 'del') {
            layer.confirm('确定删除当前菜单？', {icon: 3, title: "菜单管理"}, function (index) {
                $.post(ctx + "/module/delete", {id: data.id}, function (data) {
                    if (data.code == 200) {
                        layer.msg("操作成功！");
                        window.location.reload();
                    } else {
                        layer.msg(data.msg, {icon: 5});
                    }
                });
            })
        }
    });


        //头工具栏绑定
        table.on('toolbar(users)', function (obj) {
            switch (obj.event) {
                case "add":
                    openAddModuleDialog();
                    break;
            };
        });


        // 打开添加菜单对话框----添加
        function openAddModuleDialog() {
            var grade = grade;
            var url = ctx + "/module/addModulePage";
            var title = "菜单添加";
            layui.layer.open({
                title: title,
                type: 2,
                area: ["700px", "450px"],
                maxmin: true,
                content: url
            });
        }

        // 打开添加菜单对话框----修改
        function openUpdateModuleDialog(id) {
            var url = ctx + "/module/updateModulePage?id=" + id;
            var title = "菜单更新";
            layui.layer.open({
                title: title,
                type: 2,
                area: ["700px", "450px"],
                maxmin: true,
                content: url
            });
        }
    }
);

