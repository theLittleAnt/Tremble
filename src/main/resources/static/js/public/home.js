$(function () {
    getUserInfo();
});

//回到登陆页面
function toLogin() {
    sessionStorage.removeItem("authToken");
    window.location.href="/cars-sale";
}
//登出
function logout() {
    sessionStorage.removeItem("authToken");
    window.location.href="/cars-sale/logout";
}
//获取登录的信息
function getUserInfo() {
    $.ajax({
        type:"post",
        url:"/cars-sale/user/info",
        data:"authToken="+sessionStorage.getItem("authToken"),
        success:function (data) {
            var sItems = document.getElementsByClassName("checked-in");
            var hItems = document.getElementsByClassName("not-checked-in");
            if(data!=null && data != ""){
                var len = sItems.length;
                for(var i=0;i<len;i++){
                    sItems[i].style.display="inline-block";
                }
                len = hItems.length;
                for(var i=0;i<len;i++){
                    hItems[i].style.display="none";
                }
                var chekedUser = document.querySelector(".checked-user");
                chekedUser.innerHTML ="&nbsp;"+data.name;
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