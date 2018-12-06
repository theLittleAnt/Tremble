function login() {
    var account = document.querySelector(".account").value;
    var pwd = document.querySelector(".pwd").value;
    if(account == ""){
        showToolTip(".account","show",1000);
        return;
    }
    if(pwd==""){
        showToolTip(".pwd","show",1000);
        return;
    }
    var user = new Object();
    user.account = account;
    user.pwd = pwd;
    $.ajax({
        type:'post',
        url:"/cars-sale/user/login",
        data:user,
        success:function (data) {
            if(data=="success"){
                window.location.href="/cars-sale/home";
            }else{
                alert("登陆失败");
            }
        }
    })
    // $.ajax({
    //     type:'post',
    //     url:"/cars-sale/user/login",
    //     data:"acount="+account+"&pwd="+pwd
    // })
}

function register() {
    
}