<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="table-info-container">
        <table class="table-info">
            <thead>
                <tr>
                    <th>名称</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>
                        <span>操作</span>
                        <button class="glyphicon glyphicon-plus btn btn-default btn-xs btn-add" onclick="showAddModal()"></button>
                    </th>
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
                    <span class="modal-title">车辆详情</span>
                </div>
                <div class="modal-body">
                    <div class="img-container-modal text-center">
                        <img src="" alt="图片无法显示" class="img-thumbnail img-modal">
                        <input type="file" class="img-file-upload" accept="image/*">
                    </div>
                    <div class="content-modal text-left">
                        <div class="car-id-modal"></div>
                        <div>
                            <span>车辆名：</span>
                            <input type="text" class="car-name-modal" data-toggle="tooltip" data-placement="auto top"
                                   oninput="checkValue('.car-name-modal','eSpetial')" maxlength="32">
                        </div>
                        <div>
                            <span>价格：</span>
                            <strong>
                                <input type="number" class="price-modal"
                                       oninput="checkValue('.price-modal','num')">
                            </strong>
                            <span>&nbsp;&nbsp;元</span>
                        </div>
                        <div>
                            <span>剩余数量：</span>
                            <strong>
                                <input type="number" class="number-modal"
                                       oninput="checkValue('.price-modal','num')">
                            </strong>
                            <span>&nbsp;&nbsp;辆</span>
                        </div>
                        <div>
                            <span>交易地点：</span>
                            <strong>
                                <input type="text" class="trade-place-modal" data-toggle="tooltip" data-placement="auto top"
                                       oninput="checkValue('.car-name-modal','eSpetial')" maxlength="200">
                            </strong>
                        </div>
                        <div>
                            <span>描述：</span>
                            <textarea class="car-description-text-modal" maxlength="200" oninput="checkValue('.car-name-modal','eSpetial')"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary btn-alter">确定</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade drop-car-info-model" data-backdrop="static">
        <div class="modal-dialog content-container dialog-container">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                </div>
                <div class="modal-body">
                    确定要删除该车信息吗？
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary btn-xs btn-drop">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>