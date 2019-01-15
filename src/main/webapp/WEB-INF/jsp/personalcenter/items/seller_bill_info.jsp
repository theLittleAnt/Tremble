<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="table-info-container">
        <table class="table-info">
            <thead>
                <tr>
                    <th>订单序号</th>
                    <th>订单状态</th>
                    <th>车辆ID</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</div>
<div>
    <div class="modal fade car-info-model" data-backdrop="static">
        <div class="modal-dialog content-container dialog-container">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <span class="modal-title">订单详情</span>
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
                            <span>买家姓名：</span>
                            <span class="buyer-name-modal"></span>
                        </div>
                        <div>
                            <span>联系电话：</span>
                            <span class="buyer-phone-modal"></span>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div>
                        <span>订单状态：</span>
                        <strong><span class="state-modal"></span></strong>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>