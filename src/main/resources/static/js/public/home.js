//进入页面获取用户信息
$(function () {
    getUserInfo();
    getCarPaginationInfo(1);
});


//获取登录的信息
function getUserInfo() {
    $.ajax({
        type:"post",
        url:"/cars-sale/user/info",
        // async:false,
        success:function (data) {
            var sItems = document.getElementsByClassName("checked-in");
            var hItems = document.getElementsByClassName("not-checked-in");
            if(data!=null && data != "" && data.code==200){
                var len = sItems.length;
                for(var i=0;i<len;i++){
                    sItems[i].style.display="inline-block";
                }
                len = hItems.length;
                for(var i=0;i<len;i++){
                    hItems[i].style.display="none";
                }
                var chekedUser = document.querySelector(".checked-user");
                chekedUser.innerHTML ="&nbsp;"+data.data.name;
            }else{
                var len = sItems.length;
                for(var i=0;i<len;i++){
                    sItems[i].style.display="none";
                }
                len = hItems.length;
                for(var i=0;i<len;i++){
                    hItems[i].style.display="inline-block";
                }
            }
        }
    })
}
//获取车辆分页信息
function getCarPaginationInfo(page) {
    $.ajax({
        url:"/cars-sale/car-info/paginated",
        type:"post",
        data:"page="+page,
        success:function (data) {
            if(data.code==200){
                pageOpration('getCarPaginationInfo',page,data.data.totalSize);
                var dataList = [];
                var carsList = data.data.carsData;
                var len = carsList.length;
                for(var i =0;i<len;i++){
                    //拼接成caritem
                    dataList.push("<div class=\"col-md-3 col-sm-4 col-xs-6\" onclick='showCarInfoModal("+carsList[i].carId+")'>");
                    dataList.push("<div class=\"carItemContainer\">");
                    dataList.push("<div class=\"imgContainer text-center\">");
                    dataList.push("<img src=\""+carsList[i].carMainPic+"\" alt=\"图片无法显示\" class=\"img-thumbnail\"></div>");
                    dataList.push("<div class=\"infoContainer text-left\">");
                    dataList.push("<div class=\"car-id\">"+carsList[i].carId+"</div>");
                    dataList.push("<div><span>名称：</span><span class=\"carName\">"+carsList[i].carName+"</span></div>");
                    dataList.push("<div><span>价格：</span><strong><span class=\"price\">"+carsList[i].carPrice+"</span></strong>" +
                                  "<span>&nbsp;&nbsp;元</span></div></div>");
                    dataList.push("<div class=\"operationContainer text-right\">");
                    dataList.push("<button class=\"glyphicon glyphicon-shopping-cart btn btn-default" +
                        " btn-xs buyBtn\" onclick='buy("+carsList[i].carId+")'>购买</button></div></div></div>");
                }
                var row = document.querySelector(".row");
                row.innerHTML=dataList.join("");//数组转字符串
            }
        }
    })
}
//购买车辆
function buy(carId) {
    cancelBubble();
    var type = sessionStorage.getItem("type");
    if(type==null || type ==""){
        alert("请登录后再操作");
        return;
    }
    var bill = new Object();
    bill.carId = carId;
    $.ajax({
        url:"/cars-sale/car-info/buy",
        data:bill,
        type:"post",
        success:function (data) {
            if(data.code==200){
                alert("购买成功！");
            }else{
                alert("购买失败,请登录！");
            }
        }
    })
    $(".car-info-model").modal("hide");
}
//显示车辆具体信息
function showCarInfoModal(carId) {
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
                    document.querySelector(".number-modal").innerHTML=carInfo.carNum;
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
        url:"/cars-sale/user/seller-info",
        type:"post",
        async:false,
        data:"ownerId="+ownerId,
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
    $(".buy-modal").unbind();
    $(".buy-modal").bind("click",function () {
        buy(carId);
    });
    $(".car-info-model").modal("show");
}