$(function(){

    if(selectMovieTypeId !=""){
        $("#fenglei span a[name='"+selectMovieTypeId+"']").addClass("greenKuang");
        $("#fenglei span a[name='"+selectMovieTypeId+"']").css({color:"white"});
    }

    //提供一个帮助函数
    //给类型链接注册个点击事件
    $("#fenglei span a").click(function(){
        //清除greenkuang class 设置黑色
        $("#fenglei span a").removeClass("greenKuang");
        $("#fenglei span a").css({color:"black"});


        //得到当前对象的name属性的值
        var nameValue = $(this).attr("name");
        if(nameValue == "SearchAll"){
            //清空类型搜索条件
            $("#movieTypeId").val("");
            console.log("SearchAll");
        }
        doGoPageIndexPage(nameValue);

    });
});

//这是为首页提供的
function doGoPageIndexPage(movieTypeIdValue){
    var pCurId = document.getElementById("pCurId");
    var movieTypeId = document.getElementById("movieTypeId");

    movieTypeId.setAttribute("value",movieTypeIdValue);
    pCurId.setAttribute("value",1);
    document.forms[0];
    document.forms[0].action=url;
    document.forms[0].submit();
}