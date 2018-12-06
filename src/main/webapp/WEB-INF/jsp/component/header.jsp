<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <li class="active">
                        <a href="/cars-sale/home" class="glyphicon glyphicon-home">&nbsp;主页</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${not empty userInfo}">
                        <li style="display: none;">
                            <a href="#">
                                <span class="lgd_account" value="${userInfo.account}">${userInfo.account}</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-user" value="${userInfo.name}">&nbsp;${userInfo.name}</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-log-out" onclick="logout()">&nbsp;退出</span>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${empty userInfo}">
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-log-in" onclick="toLogin()">&nbsp;登陆</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</header>