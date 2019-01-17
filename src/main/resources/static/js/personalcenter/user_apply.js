$(function () {
    getQualification();
})
//获取资质图片
function getQualification() {
    var btn = document.querySelector(".btn-upload-qualification");
    var img = document.querySelector(".img-qualification");
    var file = document.querySelector(".qualification-file-upload");
    $.ajax({
        url:"/cars-sale/user/info",
        type:"post",
        success:function (data) {
            if(data.code==200){
                var infoData = data.data;
                if(infoData.qualifications!=null && infoData.qualifications!=""){
                    btn.disabled=true;
                    file.disabled=true;
                    img.src=infoData.qualifications;
                }else{
                    btn.disabled=false;
                    file.disabled=false;
                    img.src="";
                }
            }else if(data.code==401){
                alert("请重新登陆");
            }else{
                alert("请求出错");
            }
        }
    })
}
//上传文件
function uploadQualification() {
   var obj =document.querySelector(".qualification-file-upload");
   var formData = new FormData();
   if(obj.files.length>0){
       formData.append("file",obj.files[0]);
   }else{
       alert("没有选择文件");
       return;
   }
   $.ajax({
       url:"/cars-sale/user/upload-qualification",
       type:"post",
       data:formData,
       cache:false,
       processData:false,
       contentType:false,
       success:function (data) {
           if(data.code==200){
               getQualification();
               alert("上传成功");
           }else if(data.code==401){
               alert("请重新登陆");
           }else{
               alert("请求出错");
           }
       }
   })
}
