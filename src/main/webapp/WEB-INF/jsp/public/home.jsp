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
    <title>home page</title>
</head>
<body>
    <jsp:include page="../component/header.jsp"/>
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6">
                <jsp:include page="../component/car_item.jsp"/>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6">
                <jsp:include page="../component/car_item.jsp"/>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6">
                <jsp:include page="../component/car_item.jsp"/>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6">
                <jsp:include page="../component/car_item.jsp"/>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6">
                <jsp:include page="../component/car_item.jsp"/>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6">
                <jsp:include page="../component/car_item.jsp"/>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6">
                <jsp:include page="../component/car_item.jsp"/>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6">
                <jsp:include page="../component/car_item.jsp"/>
            </div>
        </div>

        <jsp:include page="../component/footer.jsp"/>
    </div>

    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script src="static/js/public/home.js"></script>
</body>
</html>
