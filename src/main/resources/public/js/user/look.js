layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 营销机会列表展示
     */
    var userId = $("input[id='userId']").val();
    var tableIns = table.render({
        elem: '#saleChanceList', // 表格绑定的ID
        url : ctx + '/user/scan_car?userId='+userId, // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "saleChanceListTable",
        cols : [[
            {field: "id", title:'编号',align:"center"},
            {field: "carName", title:'车名',align:"center"},
            {field: "carOrder", title:'车牌号',align:"center"},
            {field: 'carPrice', title: '价格/天',align:"center"},
            {field: 'description', title: '车辆信息', align:'center'},
            {field: 'assignTime', title: '出厂时间', align:'center'},
        ]]
    });
});

