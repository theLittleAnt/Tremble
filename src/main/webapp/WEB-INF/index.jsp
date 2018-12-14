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
                            <input type="button" value="注册" class="btn-default registerBtn"
                                   data-toggle="modal" data-target=".register-modal">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <div class="modal fade register-modal" data-backdrop="static">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">×
                            </button>
                            <span class="modal-title">
                                用户注册
                            </span>
                        </div>
                        <div class="modal-body">
                            <table class="table  text-center">
                                <tr>
                                    <td>账号：</td>
                                    <td>
                                        <input type="text" class="reg-account" oninput="checkValue('.reg-account','word')"
                                               maxlength="32" data-toggle="tooltip" data-placement="auto top" title="账号不能为空">
                                    </td>
                                </tr>
                                <tr>
                                    <td>密码：</td>
                                    <td>
                                        <input type="password" class="reg-pwd" oninput="checkValue('.reg-pwd','word')"
                                               maxlength="32" data-toggle="tooltip" data-placement="auto top" title="密码不能为空">
                                    </td>
                                </tr>
                                <tr>
                                    <td>再次输入：</td>
                                    <td>
                                        <input type="password" class="reg-pwd-again" oninput="checkValue('.reg-pwd-again','word')"
                                               maxlength="32" data-toggle="tooltip" data-placement="auto top">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" onclick="register()">注册</button>
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
