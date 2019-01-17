<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="table-info-container">
        <table class="table-info">
            <thead>
                <tr>
                    <th>姓名</th>
                    <th>身份证号</th>
                    <th>联系电话</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</div>
<div>
    <div class="modal fade info-model" data-backdrop="static">
        <div class="modal-dialog content-container dialog-container">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <span class="modal-title">资质详情</span>
                </div>
                <div class="modal-body">
                    <div class="img-container-modal text-center">
                        <img src="" alt="图片无法显示" class="img-thumbnail img-modal">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default btn-cancel">撤销</button>
                    <button class="btn btn-primary btn-pass">通过</button>
                </div>
            </div>
        </div>
    </div>
</div>