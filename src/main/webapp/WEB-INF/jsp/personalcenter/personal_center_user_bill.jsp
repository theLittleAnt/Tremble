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
    <link rel="stylesheet" href="/cars-sale/static/css/personalcenter/personal_center.css">
    <link rel="stylesheet" href="/cars-sale/static/css/personalcenter/user-bill-info.css">
    <link rel="stylesheet" href="/cars-sale/static/css/component/pagination.css">

    <title>personal center</title>
</head>
<body>

    <jsp:include page="../component/header.jsp"/>

    <div class="container page-container text-center">
        <jsp:include page="items/user_bill_info.jsp"/>
        <jsp:include page="../component/pagination.jsp"/>
    </div>

    <jsp:include page="../component/footer.jsp"/>

    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script src="static/js/personalcenter/personal_center.js"></script>
    <script src="static/js/personalcenter/user_bill_info.js"></script>
    <script src="static/js/common/common.js"></script>
    <script src="static/js/common/pagination.js"/>
</body>
</html>
