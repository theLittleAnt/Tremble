$(function () {
    getUserBill(1);
})
//获取用户个人订单信息
function getUserBill(page) {
    $.ajax({
        url:"/cars-sale/bill/paginated-buyer",
        type:"post",
        data:"page="+page,
        success:function (data) {
            if(data.code==200){
                var datas = data.data;
                var dataList = datas.billsData;
                var len = dataList.length;
                pageOpration('getUserBill',page,data.data.totalSize);
                if(len>0){
                    var str = [];
                    for(var i=0;i<len;i++){
                        var status="未交易";
                        var buttons="<button class=\"btn btn-default btn-xs\" onclick=\"showBillDetails("+dataList[i].status+",'"+dataList[i].carId+"')\">查看</button>";
                        switch(dataList[i].status){
                            case 1:
                                status="已确认";
                                break;
                            case 2:
                                status="订单完成";
                                break;
                            case 3:
                                status="订单关闭";
                                break;
                            default:
                                buttons="<button class=\"btn btn-default btn-xs btn-detail\" onclick=\"showBillDetails("+dataList[i].status+",'"+dataList[i].carId+"')\">查看</button>" +
                                        "<button class=\"btn btn-default btn-xs btn-alter\" onclick=\"alterBillStatus(this,'"+dataList[i].billId+"',1,'"+dataList[i].carId+"')\">确认</button>" +
                                        "<button class=\"btn btn-default btn-xs btn-alter\" onclick=\"alterBillStatus(this,'"+dataList[i].billId+"',3,'"+dataList[i].carId+"')\">取消</button>";
                                break;
                        }
                        str.push("<tr>" +
                                "    <td>" +
                                "       <span>"+(i+1)+"</span>" +
                                "       <span class=\"bill-id\">"+dataList[i].billId+"</span>" +
                                "    </td>" +
                                "    <td>" +
                                "       <span class=\"bill-state\">"+status+"</span>" +
                                "    </td>" +
                                "    <td>" +
                                "       <span class=\"bill-car-id\">"+dataList[i].carId+"</span>" +
                                "    </td>" +
                                "    <td>"+buttons+"</td>" +
                                "</tr>")
                    }
                    document.querySelector("tbody").innerHTML=str.join("");
                }
            }
        }
    })
}
//查看订单详情
function showBillDetails(type,carId) {
        var ownerId="";
        $.ajax({
            url:"/cars-sale/car-info/single",
            type:"post",
            async:false,
            data:"carId="+carId,
            success:function (data) {
                if(data.code==200 && data!=null){
                    var carInfo=data.data;
                    if(carInfo!=null){
                        ownerId = carInfo.carOwner;
                        document.querySelector(".img-modal").src=carInfo.carMainPic;
                        document.querySelector(".car-name-modal").innerHTML=carInfo.carName;
                        document.querySelector(".price-modal").innerHTML=carInfo.carPrice;
                        document.querySelector(".trade-place-modal").innerHTML=carInfo.carTradePlace;
                        document.querySelector(".car-description-modal").innerHTML=carInfo.carDescription;
                    }
                }else{
                    alert("请求出错");
                    return;
                }
            }
        })
        $.ajax({
            url:"/cars-sale/user/userId",
            type:"post",
            async:false,
            data:"userId="+ownerId,
            success:function (data) {
                if(data.code==200 && data!=null){
                    var seller = data.data;
                    if(seller!=null){
                        document.querySelector(".owner-name-modal").innerHTML=seller.name;
                        document.querySelector(".owner-phone-modal").innerHTML=seller.phone;
                    }
                }
            }
        })
        var status = "未交易";
        switch (type){
            case 1:
                status="已确认";
                break;
            case 2:
                status="订单完成";
                break;
            case 3:
                status="订单关闭";
                break;
        }
        document.querySelector(".state-modal").innerHTML=status;
        $(".car-info-model").modal("show");

}
//修改订单状态
function alterBillStatus(obj,billId,type,carId) {
    var bill = {};
    bill.billId=billId;
    var status;
    switch (type){
        case 1://确认交易
            bill.status=1;
            status="已确认"
            break;
        case 3://关闭交易
            bill.status=3;
            status="订单关闭";
            break;
    }
    $.ajax({
        url:"/cars-sale/bill/alter",
        type:"post",
        data:bill,
        success:function (data) {
            if(data.code==200){
                var row = obj.parentNode.parentNode;
                row.cells[1].innerHTML=status;
                var buttoms = row.cells[3].childNodes;
                while(buttoms.length>1){
                    buttoms[buttoms.length-1].parentNode.removeChild(buttoms[buttoms.length-1]);
                }
                buttoms[0].onclick="";
                buttoms[0].onclick=function(){
                    showBillDetails(type,carId);
                }
                alert("操作成功");
            }else if(data.code==401){
                alert("请重新登录");
            }else{
                alert("操作失败");
            }
        }
    })
}
