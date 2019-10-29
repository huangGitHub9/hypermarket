function movieUpload(){
    var movieFile = document.getElementById("movieFile").files[0];
    var fm = new FormData();
    fm.append("movieFile", movieFile);
    console.log(fm);
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status==200){
            //var result =xhr.responseText;
            var obj = JSON.parse(xhr.responseText);
           console.log(obj);
           //对该对象进行处理
            var msg =obj.msg;
            console.log(msg);
            if(msg == "上传成功"){
                alert("视频上传成功");
                $("#moviePath").val(obj.moviePath);
            }else{
                //上传失败
                var son = document.getElementById("son");
                son.style.width = "0";
                son.innerHTML = "0%";
                alert(obj.msg);
            }
        }
    };

    var son = document.getElementById("son");
    //var content = document.getElementById("content");

    //在表单提交h后 onprogress:数据传输进行中
    xhr.upload.onprogress = function(evt){
        //获得文件的总大小
        var total = evt.total;
        //正在加载的文件的大小
        var loaded = evt.loaded;
        var per = Math.floor((loaded/total)*100);
        son.style.width = per+"%";
        son.innerHTML = per+"%";
    };
    xhr.open("POST",proName+"/admin/movieFileAjaxUpload.action",true);
    xhr.send(fm);
    //return false;
}













