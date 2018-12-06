<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <link rel="stylesheet" href="/cars-sale/static/css/component/component.css">
    <link rel="stylesheet" href="/cars-sale/static/css/index.css">

    <title>login page</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-xs-8 col-md-8">
                <div class="text-center">
                    <img src="/cars-sale/static/pictures/touxiang.jpg" alt="无法显示" class="img-thumbnail">
                </div>
            </div>
            <div class="col-lg-4 col-xs-4 col-md-4">
                <div class="loginContainer">
                    <div class="content">
                        <div class="inputComponent">
                            <label>账号</label>
                            <input type="text" class="account" oninput="checkValue('.account','word')"
                                   onfocus="inputFocus(this)" onblur="inputBlur(this)" maxlength="32" minlength="8"
                                   data-toggle="tooltip" data-placement="auto left" title="账号不能为空">
                        </div>
                        <div class="inputComponent">
                            <label>密码</label>
                            <input type="password" class="pwd" oninput="checkValue('.pwd','word')" data-toggle="tooltip"
                                   onfocus="inputFocus(this)" onblur="inputBlur(this)" maxlength="32" minlength="8"
                                   data-toggle="tooltip" data-placement="auto left" title="密码不能为空">
                        </div>
                        <div class="text-center">
                            <input type="button" value="登陆" class="btn-default loginBtn" onclick="login()">
                            <input type="button" value="注册" class="btn-default registerBtn" onclick="register()">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="jsp/component/footer.jsp"/>
    </div>

    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/cars-sale/static/js/common/common.js"></script>
    <script src="/cars-sale/static/js/index.js"></script>

</body>
</html>
