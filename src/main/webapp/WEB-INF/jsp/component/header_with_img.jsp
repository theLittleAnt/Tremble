<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<header>
    <div class="jumbotron text-center" style="margin-bottom:0">
        <h1>我的第一个 Bootstrap 页面</h1>
        <p>重置浏览器窗口大小查看效果！</p>
    </div>
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
                    <li class="active">
                        <a href="/cars-sale/home" class="glyphicon glyphicon-home">&nbsp;主页</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="checked-in">
                        <a href="javascript:void(0)">
                            <span class="glyphicon glyphicon-user checked-user" onclick="toPersonal()"></span>
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