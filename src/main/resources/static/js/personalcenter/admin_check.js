$(function () {
    getApplyInfo();
})
//获取用户资质信息
function getApplyInfo() {
    $.ajax({
        url:"/cars-sale/user/seller-request",
        type:"post",
        success:function (data) {
            if(data.code==200){
                var dataList = data.data;
                var content = [];
                var len = dataList.length;
                if(len>0){
                    for (var i=0;i<len;i++){
                        content.push("<tr>" +
                                    "    <td>" +
                                    "       <span>"+dataList[i].name+"</span>" +
                                    "    </td>" +
                                    "    <td>" +
                                    "       <span>"+dataList[i].idCard+"</span>" +
                                    "    </td>" +
                                    "    <td>" +
                                    "       <span>"+dataList[i].phone+"</span>" +
                                    "    </td>" +
                                    "    <td>"+
                                    "       <button class=\"btn btn-default btn-xs\" onclick=\"getDetail('"+dataList[i].account+"',this)\">查看</button>" +
                                    "       <button class=\"btn btn-default btn-xs\" onclick=\"pass('"+dataList[i].account+"',this)\">通过</button>" +
                                    "       <button class=\"btn btn-default btn-xs\" onclick=\"erase('"+dataList[i].account+"',this)\">撤销</button>" +
                                    "    </td>" +
                                    "</tr>")
                    }
                }
                document.querySelector("tbody").innerHTML=content.join("");
            }else if(data.code==401){
                alert("请重新登录!");
            }else{
                alert("请求出错");
            }
        }
    })
}
//查看具体资质信息
function getDetail(account,obj) {
    $.ajax({
        url:"/cars-sale/user/user-info",
        type:"post",
        data:"userId="+account,
        success:function (data) {
            if(data.code==200){
                document.querySelector(".img-modal").src=data.data.qualifications;
                $(".btn-cancel").unbind();
                $(".btn-cancel").bind("click",function () {
                    erase(account,obj);
                });
                $(".btn-pass").unbind();
                $(".btn-pass").bind("click",function () {
                    pass(account,obj);
                });
                $(".info-model").modal("show");
            }else if(data.code==401){
                alert("请重新登录!");
            }else{
                alert("请求出错");
            }
        }
    })
}
//通过审核
function pass(account,obj) {
    $.ajax({
        url:"/cars-sale/user/passed",
        type:"post",
        data:"account="+account,
        success:function (data) {
            if(data.code==200){
                var row = obj.parentNode.parentNode;
                document.querySelector("tbody").removeChild(row);
                $(".info-model").modal("hide");
                alert("操作成功");
            }else if(data.code==401){
                alert("请重新登录!");
            }else{
                alert("请求出错");
            }
        }
    })
}
//撤销审核资格
function erase(account,obj) {
    $.ajax({
        url:"/cars-sale/user/erase",
        type:"post",
        data:"account="+account,
        success:function (data) {
            if(data.code==200){
                var row = obj.parentNode.parentNode;
                document.querySelector("tbody").removeChild(row);
                $(".info-model").modal("hide");
                alert("操作成功");
            }else if(data.code==401){
                alert("请重新登录!");
            }else{
                alert("请求出错");
            }
        }
    })
}