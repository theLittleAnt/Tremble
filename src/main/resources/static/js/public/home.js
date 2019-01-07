//进入页面获取用户信息
$(function () {
    getUserInfo();
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