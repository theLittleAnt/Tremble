$(function () {
    getSellerBill(1);
})
//获取卖家收到的订单信息
function getSellerBill(page) {
    $.ajax({
        url:"/cars-sale/bill/paginated-seller",
        type:"post",
        data:"page="+page,
        success:function (data) {
            if(data.code==200){
                var datas = data.data;
                var dataList = datas.billsData;
                var len = dataList.length;
                pageOpration('getSellerBill',page,data.data.totalSize);
                if(len>0){
                    var str = [];
                    for(var i=0;i<len;i++){
                        var status="订单交易";
                        var buttons="<button class=\"btn btn-default btn-xs\" onclick=\"showBillDetails("+dataList[i].status+",'"+dataList[i].carId+"','"+dataList[i].buyerAccount+"')\">查看</button>";
                        switch(dataList[i].status){
                            case 0:
                                status="未交易";
                                buttons+="<button class=\"btn btn-default btn-xs\" onclick=\"alterBillStatus(this,'"+dataList[i].billId+"',3,'"+dataList[i].carId+"','"+dataList[i].buyerAccount+"')\">取消</button>";
                                break;
                            case 1:
                                status="待确认";
                                buttons+="<button class=\"btn btn-default btn-xs\" onclick=\"alterBillStatus(this,'"+dataList[i].billId+"',2,'"+dataList[i].carId+"','"+dataList[i].buyerAccount+"')\">确认</button>";
                                break;
                            case 3:
                                status="订单关闭";
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
function showBillDetails(type,carId,buyerId) {
        $.ajax({
            url:"/cars-sale/car-info/single",
            type:"post",
            async:false,
            data:"carId="+carId,
            success:function (data) {
                if(data.code==200 && data!=null){
                    var carInfo=data.data;
                    if(carInfo!=null){
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
            url:"/cars-sale/user/user-info",
            type:"post",
            async:false,
            data:"userId="+buyerId,
            success:function (data) {
                if(data.code==200 && data!=null){
                    var buyer = data.data;
                    if(buyer!=null){
                        document.querySelector(".buyer-name-modal").innerHTML=buyer.name;
                        document.querySelector(".buyer-phone-modal").innerHTML=buyer.phone;
                    }
                }
            }
        })
        var status = "未交易";
        switch (type){
            case 1:
                status="待确认";
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
function alterBillStatus(obj,billId,type,carId,buyerId) {
    var bill = {};
    var tag=false;
    var msg;
    bill.billId=billId;
    var status;
    switch (type){
        case 2://交易完成
            bill.status=2;
            status="交易完成"
            break;
        case 3://关闭交易
            bill.status=3;
            status="订单关闭";
            break;
        default:
            return;
    }
    $.ajax({
        url:"/cars-sale/bill/alter",
        type:"post",
        data:bill,
        success:function (data) {
            if (data.code == 200) {
                var row = obj.parentNode.parentNode;
                row.cells[1].innerHTML = status;
                var buttons = row.cells[3].childNodes;
                while (buttons.length > 1) {
                    buttons[buttons.length - 1].parentNode.removeChild(buttons[buttons.length - 1]);
                }
                buttons[0].onclick = "";
                buttons[0].onclick = function () {
                    showBillDetails(type, carId, buyerId);
                }
                alert("操作成功");
            } else if (data.code == 401) {
                msg = "请重新登录";
                tag = true;
            } else {
                msg = "操作失败";
                tag = true;
            }
        }
    })
    if(tag){
        alert(msg);
        return;
    }
    if(type==3){
        increaseCarNum(carId);
    }
}
//取消订单后对应的车辆数量加
function increaseCarNum(carId) {
    var carInfo;
    $.ajax({
        url:"/cars-sale/car-info/single",
        type:"post",
        data:"carId="+carId,
        async:false,
        success:function (data) {
            if(data.code==200){
                carInfo=data.data;
            }
        }
    })
    carInfo.carNum=carInfo.carNum+1;
    $.ajax({
        url:"/cars-sale/car-info/alter",
        type:"post",
        data:carInfo,
        async:false,
        success:function (data) {
            if(data.code==200){
                console.log("数量增加成功");
            }
        }
    })
}