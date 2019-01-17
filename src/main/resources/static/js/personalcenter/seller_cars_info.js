$(function () {
    getCarsInfo(1);
})
//获取卖家下的车辆信息
function getCarsInfo(page) {
    $.ajax({
        url:"/cars-sale/car-info/owners",
        type:"post",
        data:"page="+page,
        success:function (data) {
            if(data.code==200){
                var datas = data.data;
                var dataList = datas.carsData;
                var len = dataList.length;
                pageOpration('getCarsInfo',page,data.data.totalSize);
                if(len>0){
                    var str=[];
                    for(var i=0;i<len;i++){
                        str.push("<tr>" +
                                "    <td>" +
                                "       <span class=\"car-name\">"+dataList[i].carName+"</span>" +
                                "    </td>" +
                                "    <td>" +
                                "       <span class=\"car-price\">"+dataList[i].carPrice+"</span>" +
                                "    </td>" +
                                "    <td>" +
                                "       <span class=\"car-num\">"+dataList[i].carNum+"</span>" +
                                "    </td>" +
                                "    <td>"+
                                "       <button class=\"btn btn-default btn-xs\" onclick=\"showModal('"+dataList[i].carId+"',0)\">查看</button>" +
                                "       <button class=\"btn btn-default btn-xs\" onclick=\"showModal('"+dataList[i].carId+"',1,this)\">修改</button>" +
                                "       <button class=\"btn btn-default btn-xs\" onclick=\"showInform(this,'"+dataList[i].carId+"')\">删除</button>" +
                                "    </td>" +
                                "</tr>");
                    }
                    document.querySelector("tbody").innerHTML=str.join("");
                }
            }
        }
    })
}
//显示查看看或修改框
function showModal(carId,type,obj) {
    var mainPic=document.querySelector(".img-modal");
    var imgFileUpload = document.querySelector(".img-file-upload");
    var name=document.querySelector(".car-name-modal");
    var price=document.querySelector(".price-modal");
    var num=document.querySelector(".number-modal");
    var tradePlace=document.querySelector(".trade-place-modal");
    var description=document.querySelector(".car-description-text-modal");
    var btn=document.querySelector(".btn-alter");
    var oldData={};
    var newData={};
    $.ajax({
        url:"/cars-sale/car-info/single",
        type:"post",
        async:false,
        data:"carId="+carId,
        success:function (data) {
            if(data.code==200){
                var carInfo=data.data;
                oldData=carInfo;
                if(carInfo!=null){
                    mainPic.src=carInfo.carMainPic;
                    name.value=carInfo.carName;
                    price.value=carInfo.carPrice;
                    num.value=carInfo.carNum;
                    tradePlace.value=carInfo.carTradePlace;
                    description.value=carInfo.carDescription;
                }
            }
        }
    })
    switch (type){
        case 0://查看
            toggleModalItems(1);
            break;
        case 1://修改
            toggleModalItems(0);
            btn.onclick="";
            btn.onclick=function () {
                newData.carId=carId;
                var formData = new FormData();
                formData.append("carId",carId);
                var cmpLen=1;
                if(name.value!=oldData.carName){
                    newData.carName=name.value;
                    formData.append("carName",name.value);
                }
                newData.carPrice=price.value==""?0:parseFloat(price.value);
                if(price.value==oldData.carPrice){
                    cmpLen+=1;
                }
                formData.append("carPrice",price.value==""?0:parseFloat(price.value));

                newData.carNum=num.value==""?0:parseInt(num.value);
                if(num.value==oldData.carNum){
                    cmpLen+=1;
                }
                formData.append("carNum",num.value==""?0:parseInt(num.value));
                if(tradePlace.value!=oldData.carTradePlace){
                    newData.carTradePlace=tradePlace.value;
                    formData.append("carTradePlace",tradePlace.value);
                }
                if(description.value!=oldData.carDescription){
                    newData.carDescription=description.value;
                    formData.append("carDescription",description.value);
                }
                if(imgFileUpload.files.length>0){
                    formData.append("file",imgFileUpload.files[0]);
                }

                var names= Object.getOwnPropertyNames(newData);
                if(names.length>cmpLen || formData.has("file")){
                    alterCarInfo(formData);
                    $(".car-info-model").modal("hide");
                    var cells = obj.parentNode.parentNode.cells;
                    if(newData.carName!=undefined){
                        cells[0].children[0].innerHTML=newData.carName;
                    }
                    if(newData.carPrice!=undefined){
                        cells[1].children[0].innerHTML=newData.carPrice;
                    }
                    if(newData.carNum!=undefined){
                        cells[2].children[0].innerHTML=newData.carNum;
                    }
                }else{
                    alert("没有值发生改变");
                }
            }
            break;
    }
    $(".car-info-model").modal("show");
}
//修改车辆信息
function alterCarInfo(formData) {
    $.ajax({
        url:"/cars-sale/car-info/alter",
        type:"post",
        cache:false,
        processData: false,
        contentType: false,
        data:formData,
        success:function (data) {
            if(data.code==200){
                alert("修改成功");
            }else if(data.code==401){
                alert("请重新登录");
            }else{
                alert("请求出错");
            }
        }
    })
}
//显示提示框
function showInform(obj,carId) {
    $(".btn-drop").unbind();
    $(".btn-drop").bind("click",function () {
        dropCarInfo(obj,carId);
    });
    $(".drop-car-info-model").modal("show");
}
//删除车辆信息
function dropCarInfo(obj,carId) {
    $.ajax({
        url:"/cars-sale/car-info/drop",
        type:"post",
        data:"carId="+carId,
        success:function (data) {
            if(data.code==200){
                var tbody = document.querySelector("tbody");
                var item = obj.parentNode.parentNode;
                tbody.removeChild(item);
                $(".drop-car-info-model").modal("hide");
            }else if(data.code==401){
                alert("请重新登录");
            }else{
                alert("请求出错");
            }
        }
    })
}
//显示新增模块
function showAddModal() {
    var mainPic=document.querySelector(".img-modal");
    var imgFileUpload = document.querySelector(".img-file-upload");
    var name=document.querySelector(".car-name-modal");
    var price=document.querySelector(".price-modal");
    var num=document.querySelector(".number-modal");
    var tradePlace=document.querySelector(".trade-place-modal");
    var description=document.querySelector(".car-description-text-modal");
    var btn=document.querySelector(".btn-alter");

    toggleModalItems(0);

    mainPic.src="/cars-sale/static/touxiang.jpg";
    name.value="";
    price.value=0;
    num.value=0;
    tradePlace.value="";
    description.value="";
    btn.onclick="";
    btn.onclick=function () {
        addCarInfo();
    }
    $(".car-info-model").modal("show");
}
//添加车辆信息
function addCarInfo() {
    var imgFileUpload = document.querySelector(".img-file-upload");
    var name=document.querySelector(".car-name-modal");
    var price=document.querySelector(".price-modal");
    var num=document.querySelector(".number-modal");
    var tradePlace=document.querySelector(".trade-place-modal");
    var description=document.querySelector(".car-description-text-modal");

    var formData=new FormData();
    if(imgFileUpload.files.length>0){
        formData.append("file",imgFileUpload.files[0]);
    }
    if(name.value==""){
        showToolTip(".car-name-modal","show",1000,"车辆名不能为空");
        return;
    }
    if(tradePlace.value==""){
        showToolTip(".trade-place-modal","show",1000,"交易地点不能为空");
        return;
    }
    formData.append("carName",name.value);
    formData.append("carNum",parseInt(num.value==""?0:num.value));
    formData.append("carPrice",parseFloat(price.value==""?0:price.value));
    formData.append("carTradePlace",tradePlace.value);
    if(description.value!=""){
        formData.append("carDescription",description.value);
    }
    $.ajax({
        url:"/cars-sale/car-info/save",
        type:"post",
        data:formData,
        cache:false,
        contentType:false,
        processData:false,
        success:function (data) {
            if (data.code == 200) {
                getCarsInfo(1);
                alert("新增成功");
            } else if (data.code == 200) {
                alert("请重新登录");
            } else {
                alert("出现错误");
            }
        }
    })
    $(".car-info-model").modal("hide");
}
//modal控件是否可用0可用1不可用
function toggleModalItems(type) {
    var imgFileUpload = document.querySelector(".img-file-upload");
    var name=document.querySelector(".car-name-modal");
    var price=document.querySelector(".price-modal");
    var num=document.querySelector(".number-modal");
    var tradePlace=document.querySelector(".trade-place-modal");
    var description=document.querySelector(".car-description-text-modal");
    var btn=document.querySelector(".btn-alter");
    switch (type){
        case 0:
            name.disabled=false;
            imgFileUpload.disabled=false;
            price.disabled=false;
            num.disabled=false;
            tradePlace.disabled=false;
            description.disabled=false;
            btn.disabled=false;
            btn.style.display="unset";
            break;
        case 1:
            name.disabled=true;
            imgFileUpload.disabled=true;
            price.disabled=true;
            num.disabled=true;
            tradePlace.disabled=true;
            description.disabled=true;
            btn.disabled=true;
            btn.style.display="none";
            break;
    }
}