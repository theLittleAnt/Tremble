<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target=".header_navbar">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="javascript:">cars sale</a>
            </div>
            <div class="collapse navbar-collapse header_navbar">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/cars-sale/home" class="glyphicon glyphicon-home">&nbsp;主页</a>
                    </li>
                    <li class="dropdown user-op">
                        <a href="javascript:void(0)" class="dropdown-toggle glyphicon glyphicon-piggy-bank" data-toggle="dropdown">
                            用户功能
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="javascript:toPersonal()">用户信息</a></li>
                            <li><a href="javascript:toPersonalUserBill()">订单信息</a></li>
                            <li><a href="javascript:toPersonalUserApply()">申请卖家</a></li>
                        </ul>
                    </li>
                    <li class="dropdown seller-op">
                        <a href="javascript:void(0)" class="dropdown-toggle glyphicon glyphicon-scissors" data-toggle="dropdown">
                            卖家功能
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="javascript:toPersonalSellerCars()">车辆信息</a></li>
                            <li><a href="javascript:toPersonalSellerBill()">订单信息</a></li>
                        </ul>
                    </li>
                    <li class="dropdown admin-op">
                        <a href="javascript:void(0)" class="dropdown-toggle glyphicon glyphicon-filter" data-toggle="dropdown">
                            管理员功能
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="javascript:toPersonalAdminCheck()">审核资质</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="checked-in">
                        <a href="javascript:void(0)">
                            <span class="glyphicon glyphicon-user checked-user"></span>
                        </a>
                    </li>
                    <li class="checked-in">
                        <a href="javascript:void(0)">
                            <span class="glyphicon glyphicon-log-out" onclick="logout()">&nbsp;退出</span>
                        </a>
                    </li>
                    <li class="not-checked-in">
                        <a href="javascript:void(0)">
                            <span class="glyphicon glyphicon-log-in" onclick="toLogin()">&nbsp;登陆</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>