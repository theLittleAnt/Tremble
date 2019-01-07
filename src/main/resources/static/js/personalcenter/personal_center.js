//进入页面获取用户信息
$(function () {
    getUserInfo();
});

//获取登录的信息
function getUserInfo() {
    var type = sessionStorage.getItem("type");
    var items = document.getElementsByClassName("dropdown");
    var len = items.length;
    if(type==null || type ==""){
        for(var i = 0 ;i<len;i++){
            items[i].style.display="none";
        }
    }else{
        switch (type){
            case "0"://普通用户
                for(var i = 1;i<len;i++){
                    items[i].style.display="none";
                }
                break;
            case "1"://卖家
                items[2].style.display="none";
                break;
            case "2"://管理员
                items[1].style.display="none";
                break;
        }
    }
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