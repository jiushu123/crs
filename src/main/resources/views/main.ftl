<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>CRS-智能办公系统</title>
    <#include "common.ftl">
    <style>
        /*展开和用户名显示的地方背景色*/
        .layuimini-tool,#jibenziliao{
            background-color: #1b1b21;
        }
        /*选项卡一行的背景色*/
        .layui-tab{
            background-color: #1b1d21;
        }
    </style>
</head>
<body class="layui-layout-body layuimini-all">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header">
        <div class="layui-logo">
            <a href="">
                <img src="images/logo.png" alt="logo">
                <h1>CRS-智能办公</h1>
            </a>
        </div>
        <a>
            <div class="layuimini-tool"><i title="展开" class="fa fa-outdent" data-side-fold="1"></i></div>
        </a>
        <ul class="layui-nav layui-layout-right" id="jibenziliao">
            <li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
            </li>
            <li class="layui-nav-item layuimini-setting">
                <a href="javascript:;">${(user.userName)!""}</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/user/toSettingPage" data-title="基本资料" data-icon="fa fa-gears">基本资料</a>
                    </dd>
<!--                    <dd>-->
<!--                        <a href="javascript:;" data-iframe-tab="${ctx}/user/toPasswordPage" data-title="修改密码" data-icon="fa fa-gears">修改密码</a>-->
<!--                    </dd>-->
                    <dd>
                        <a href="javascript:;" class="login-out">退出登录</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;"></a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll layui-left-menu">
                <ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="currency">

                    <#if permissions?seq_contains("10")>
                        <li class="layui-nav-item">
                            <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-street-view"></i><span class="layui-left-nav"> 我的车辆 </span> <span class="layui-nav-more"></span></a>
                            <dl class="layui-nav-child">

                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-1" data-tab="car/scan_car" target="_self"><i class="fa fa-tty"></i><span class="layui-left-nav"> 我的租车 </span></a>
                                    </dd>


                            </dl>
                        </li>
                    </#if>
                    <#if permissions?seq_contains("20")>
                        <li class="layui-nav-item">
                            <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-flag"></i><span class="layui-left-nav"> 我要租车 </span> <span class="layui-nav-more"></span></a><dl class="layui-nav-child">

                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-4" data-tab="car/rent_car" target="_self"><i class="fa fa-user-times"></i><span class="layui-left-nav"> 我要租车</span></a>
                                    </dd>
                            </dl>
                        </li>
                    </#if>


<!--                    <li class="layui-nav-item">-->
<!--                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-flag"></i><span class="layui-left-nav"> 我的余额 </span> <span class="layui-nav-more"></span></a><dl class="layui-nav-child">-->

<!--                            <dd>-->
<!--                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-4" data-tab="user/money" target="_self"><i class="fa fa-user-times"></i><span class="layui-left-nav"> 我的余额 </span></a>-->
<!--                            </dd>-->
<!--                        </dl>-->
<!--                    </li>-->
                <#if permissions?seq_contains("30")>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-flag"></i><span class="layui-left-nav"> 用户管理 </span> <span class="layui-nav-more"></span></a><dl class="layui-nav-child">

                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-4" data-tab="user/user" target="_self"><i class="fa fa-user-times"></i><span class="layui-left-nav"> 用户管理 </span></a>
                            </dd>
                        </dl>
                    </li>
                </#if>
                <#if permissions?seq_contains("40")>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-flag"></i><span class="layui-left-nav"> 角色管理 </span> <span class="layui-nav-more"></span></a><dl class="layui-nav-child">

                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-4" data-tab="role/role" target="_self"><i class="fa fa-user-times"></i><span class="layui-left-nav"> 角色管理 </span></a>
                            </dd>
                        </dl>
                    </li>
                </#if>
                <#if permissions?seq_contains("50")>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-flag"></i><span class="layui-left-nav"> 权限管理 </span> <span class="layui-nav-more"></span></a><dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-4" data-tab="module/module" target="_self"><i class="fa fa-user-times"></i><span class="layui-left-nav"> 权限管理 </span></a>
                            </dd>
                        </dl>
                    </li>
                </#if>
                <#if permissions?seq_contains("60")>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-flag"></i><span class="layui-left-nav"> 车辆设置 </span> <span class="layui-nav-more"></span></a><dl class="layui-nav-child">

                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-4" data-tab="car/management_car" target="_self"><i class="fa fa-user-times"></i><span class="layui-left-nav"> 车辆设置 </span></a>
                            </dd>
                        </dl>
                    </li>
                </#if>


                    <span class="layui-nav-bar" style="top: 201px; height: 0px; opacity: 0;"></span>
                </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="layuiminiTab" id="top_tabs_box">
            <ul class="layui-tab-title" id="top_tabs">
                <li class="layui-this" id="layuiminiHomeTabId" lay-id="welcome"><i class="fa fa-home"></i>
                    <span>首页</span></li>
            </ul>

            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"> <i class="fa fa-dot-circle-o"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-page-close="other"><i class="fa fa-window-close"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" data-page-close="all"><i class="fa fa-window-close-o"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show">
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${ctx}/js/main.js"></script>
</body>
</html>
