<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>file upload test</title>
</head>
<body>

    文件上传<input type="file" class="file">
    <input type="text" class="text">

    <button onclick="upload()">上传</button>


    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script>
        function upload() {
            var formdata = new FormData();
            var carInfo = new Object();
            var file = document.querySelector(".file");
            var text = document.querySelector(".text");
            carInfo.carName="2";
            carInfo.carId="1";
            formdata.append("file",file.files[0]);
            formdata.append("text",text.value);
            formdata.append("carId","1111");
            formdata.append("carName","1111");
            $.ajax({
                type:"post",
                url:"/cars-sale/upload",
                cache:false,
                processData: false,
                contentType: false,
                data:formdata,
                success:function(data){
                    alert("ok");
                }
            })
        }
    </script>
</body>
</html>
