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
    <link rel="stylesheet" href="/cars-sale/static/css/component/pagination.css">
    <link rel="stylesheet" href="/cars-sale/static/css/public/home.css">
    <title>home page</title>
</head>
<body>
    <jsp:include page="../component/header_with_img.jsp"/>
    <div class="container items-container">
        <div class="row">

        </div>
        <jsp:include page="../component/pagination.jsp"/>
    </div>
    <div>
        <div class="modal fade car-info-model" data-backdrop="static">
            <div class="modal-dialog content-container dialog-container">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                                aria-hidden="true">×
                        </button>
                        <span class="modal-title">车辆信息</span>
                    </div>
                    <div class="modal-body">
                        <div class="img-container-modal text-center">
                            <img src="" alt="图片无法显示" class="img-thumbnail img-modal">
                        </div>
                        <div class="content-modal text-left">
                            <div class="car-id-modal"></div>
                            <div>
                                <span>车辆名：</span>
                                <span class="car-name-modal"></span>
                            </div>
                            <div>
                                <span>价格：</span>
                                <strong><span class="price-modal"></span></strong>
                                <span>&nbsp;&nbsp;元</span>
                            </div>
                            <div>
                                <span>剩余数量：</span>
                                <strong><span class="number-modal"></span></strong>
                                <span>&nbsp;&nbsp;辆</span>
                            </div>
                            <div>
                                <span>交易地点：</span>
                                <strong>
                                    <span class="trade-place-modal"></span>
                                </strong>
                            </div>
                            <div>
                                <span>描述：</span>
                                <p class="car-description-modal"></p>
                            </div>
                            <div>
                                <span>卖家姓名：</span>
                                <span class="owner-name-modal"></span>
                            </div>
                            <div>
                                <span>联系电话：</span>
                                <span class="owner-phone-modal"></span>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary buy-modal">购买</button>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <jsp:include page="../component/footer.jsp"/>

    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script src="static/js/public/home.js"></script>
    <script src="static/js/common/common.js"></script>
    <script src="static/js/common/pagination.js"></script>
</body>
</html>
