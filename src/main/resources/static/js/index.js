function login() {
    var account = document.querySelector(".account").value;
    var pwd = document.querySelector(".pwd").value;
    if(account == ""){
        showToolTip(".account","show",1000,null);
        return;
    }
    if(pwd==""){
        showToolTip(".pwd","show",1000,null);
        return;
    }
    var user = new Object();
    user.account = account;
    user.pwd = pwd;
    $.ajax({
        type:'post',
        url:"/cars-sale/user/login",
        data:user,
        xhrFields: {
            withCredentials: true//cookie访问
        },
        success:function (data) {
            if(data.msg=="success"){
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
    var account = document.querySelector(".reg-account");
    var pwd = document.querySelector(".reg-pwd");
    var pwd_a = document.querySelector(".reg-pwd-again");
    if(account.value==""){
        showToolTip(".reg-account","show",1000,null);
        return;
    }
    if(pwd.value==""){
        showToolTip(".reg-pwd","show",1000,null);
        return;
    }
    if(pwd_a.value==""){
        showToolTip(".reg-pwd-again","show",1000,"再次输入不能为空");
        return;
    }
    if(pwd_a.value!=pwd.value){
        showToolTip(".reg-pwd-again","show",1000,"两次输入不一致");
        return;
    }
    var user = new Object();
    user.account = account.value;
    user.pwd = pwd.value;
    $.ajax({
        type:"post",
        url:"/cars-sale/user/register",
        data:user,
        success:function (data) {
            if(data=="success"){
                alert("注册成功");
            }else if(data=="fail"){
                alert("注册失败");
            }else if(data.msg=="error"){
                alert("账号已存在");
            }

        },
        error:function (e){
            alert("账号已存在");
        }
    })

}