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
                console.log(data);
            }
        }
    })
}