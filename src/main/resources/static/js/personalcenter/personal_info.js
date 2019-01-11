$(function () {
    initUserInfo();
})

var oldData = {};
//修改取消保存操作
function toggle(tag) {
    var alter_btn=document.querySelector(".info-alter");
    var save_btn=document.querySelector(".info-save");
    var cancel_btn=document.querySelector(".info-cancel");
    var alter_pwd =document.querySelector(".info-pwd");
    var name = document.querySelector(".info-name");
    var idCard = document.querySelector(".info-id-card");
    var phone = document.querySelector(".info-phone");
    var email = document.querySelector(".info-email");
    var age = document.querySelector(".info-age");
    var address = document.querySelector(".info-address");
    switch (tag){
        case "1"://修改内容
            oldData.name = name.value;
            oldData.idCard = idCard.value;
            oldData.phone = phone.value;
            oldData.email = email.value;
            oldData.age = age.value;
            oldData.address = address.value;
            if(name.value==""){
                name.disabled=false;
            }
            idCard.disabled=false;
            phone.disabled=false;
            email.disabled=false;
            age.disabled=false;
            address.disabled=false;

            alter_btn.style.display="none";
            alter_pwd.style.display="none";
            save_btn.style.display="unset";
            cancel_btn.style.display="unset";
            break;
        case "0"://取消修改
            name.value = oldData.name;
            idCard.value = oldData.idCard;
            phone.value = oldData.phone;
            email.value = oldData.email;
            age.value = oldData.age;
            address.value = oldData.address;

            name.disabled=true;
            idCard.disabled=true;
            phone.disabled=true;
            email.disabled=true;
            age.disabled=true;
            address.disabled=true;

            alter_btn.style.display="unset";
            alter_pwd.style.display="unset";
            save_btn.style.display="none";
            cancel_btn.style.display="none";
            break;
        case "2":
            var userInfo = {};
            var tagLength = 0;
            if(oldData.name==""){
                userInfo.name = name.value;
            }
            if(idCard.value!=oldData.idCard){
                userInfo.idCard = idCard.value;
            }
            if(phone.value!=oldData.phone){
                userInfo.phone = phone.value;
            }
            if(email.value!=oldData.email){
                userInfo.email = email.value;
            }
            if(oldData.age!=""){//如果年龄旧值不为空，年龄必须带上,不然后台因为null默认给age设置为0
                userInfo.age = parseInt(age.value!=""?age.value:oldData.age);
                if(age.value==oldData.age){//相等意味的没修改
                    tagLength=1;
                }
            }
            if(address.value!=oldData.address){
                userInfo.address = address.value;
            }
            var names = Object.getOwnPropertyNames(userInfo);
            if(names.length>tagLength){
                saveUserInfo(userInfo);
            }else{
                alert("没有值发生改变");
            }
            break;
        default:
            return;
    }
}
//保存修改内容
function saveUserInfo(userInfo) {
    $.ajax({
        type:"post",
        url:"/cars-sale/user/alter-userinfo",
        data:userInfo,
        async:false,
        success:function (data) {
            if(data.code==200){
                alert("修改成功");
                resetOldData(userInfo);
                toggle("0");
            }else{
                alert("修改失败");
            }
        }
    })
}
//修改后重新设置oldData
function resetOldData(userInfo) {
    if(userInfo.idCard!=null){
        oldData.idCard=userInfo.idCard;
    }
    if(userInfo.phone!=null){
        oldData.phone=userInfo.phone;
    }
    if(userInfo.name!=null){
        oldData.name=userInfo.name;
    }
    if(userInfo.address!=null){
        oldData.address=userInfo.address;
    }
    if(userInfo.age!=null){
        oldData.age=userInfo.age;
    }
    if(userInfo.email!=null){
        oldData.email=userInfo.email;
    }
}
//获取用户信息并设置
function initUserInfo() {
    $.ajax({
        type:"post",
        url:"/cars-sale/user/info",
        success:function (data) {
            if(data.code==200){
                var name = document.querySelector(".info-name");
                var idCard = document.querySelector(".info-id-card");
                var phone = document.querySelector(".info-phone");
                var email = document.querySelector(".info-email");
                var age = document.querySelector(".info-age");
                var address = document.querySelector(".info-address");

                var userInfo = data.data;
                name.value = userInfo.name;
                idCard.value = userInfo.idCard;
                phone.value = userInfo.phone;
                email.value = userInfo.email;
                age.value = userInfo.age;
                address.value = userInfo.address;
            }else{
                alert("请重新登陆");
            }
        }
    })
}
//修改密码
function alterPwd() {
    var newPwd = document.querySelector(".new-pwd");
    var newPwdAgain = document.querySelector(".new-pwd-again");
    if(newPwd.value==""){
        showToolTip(".new-pwd",'show',1000,null);
        return;
    }
    if(newPwdAgain.value==""){
        showToolTip(".new-pwd-again",'show',1000,"再次输入不能为空");
        return;
    }
    if(newPwdAgain.value!=newPwd.value){
        showToolTip(".new-pwd-again",'show',1000,"两次输入不一致");
        return;
    }
    var user = {};
    user.pwd = newPwd.value;
    $.ajax({
        type:"post",
        url:"/cars-sale/user/alter-pwd",
        data:user,
        success:function (data) {
            if(data.code==200){
                alert("修改成功");
            }else{
                alert("请重新登陆");
            }
        }
    })
    newPwd.value="";
    newPwdAgain.value="";
    $(".alter-pwd-modal").modal("hide");
}