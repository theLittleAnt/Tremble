<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container info-container">
    <div class="personal-info-container text-left">
        <div>
            <span>姓名：</span>
            <input class="info-name infos" type="text" disabled
                   oninput="checkValue('.info-name','eSpetial')" maxlength="32">
        </div>
        <div>
            <span>年龄：</span>
            <input class="info-age infos" type="text" disabled
                   oninput="checkValue('.info-age','num')" maxlength="3">
        </div>
        <div>
            <span>身份证：</span>
            <input class="info-id-card infos" type="text" disabled
                   oninput="checkValue('.info-id-card','id-card')" maxlength="18">
        </div>
        <div>
            <span>手机：</span>
            <input class="info-phone infos" type="text" disabled
                   oninput="checkValue('.info-phone','num')" maxlength="11">
        </div>
        <div>
            <span>邮件地址：</span>
            <input class="info-email infos" type="text" disabled
                   oninput="checkValue('.info-email','email')" maxlength="45">
        </div>
        <div>
            <span>住址：</span>
            <input class="info-address infos" type="text" disabled
                   oninput="checkValue('.info-address','eSpetial')" maxlength="200">
        </div>
        <div class="text-center">
            <button class="btn btn-sm btn-default info-alter" onclick="toggle('1')">修改信息</button>
            <button class="btn btn-sm btn-default info-pwd"
                    data-toggle="modal" data-target=".alter-pwd-modal">修改密码</button>
            <button class="btn btn-sm btn-default info-save" onclick="toggle('2')">保存</button>
            <button class="btn btn-sm btn-default info-cancel" onclick="toggle('0')">取消</button>
        </div>
    </div>
</div>
<div>
    <div class="modal fade alter-pwd-modal" data-backdrop="static">
        <div class="modal-dialog content-container dialog-container">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <span class="modal-title">修改密码</span>
                </div>
                <div class="modal-body">
                    <table class="table  text-center">
                        <tr>
                            <td>新密码：</td>
                            <td>
                                <input type="password" class="new-pwd" oninput="checkValue('.new-pwd','word')"
                                       maxlength="32" data-toggle="tooltip" data-placement="auto top" title="不能为空">
                            </td>
                        </tr>
                        <tr>
                            <td>再次输入：</td>
                            <td>
                                <input type="password" class="new-pwd-again" oninput="checkValue('.new-pwd-again','word')"
                                       maxlength="32" data-toggle="tooltip" data-placement="auto top">
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="alterPwd()">修改</button>
                </div>
            </div>
        </div>
    </div>
</div>
