$(function(){

	//注册鼠标切换事件
	$(".movie_li").mouseover(function(){
		//显示关闭按钮
		var value = $(this).children()[2];
		$(value).css({display:"block"});
	});
	$(".movie_li").mouseout(function(){
		var value = $(this).children()[2];
		$(value).css({display:"none"});
	});

	//
    $(".remove_item_remove2").mouseover(function(){
        $(this).css({color:"white"});
    });
    $(".remove_item_remove2").mouseout(function(){
        $(this).css({color:"orange"});
    });



    $(".layui-tab-title li").each(function(){
        $(this).removeClass("layui-this");
    });
    $(".layui-tab-item").each(function(){
        $(this).removeClass("layui-show");
    });
    var divs =  $(".layui-tab-item");
    var lis = $(".layui-tab-title li");

    $(divs[showIndex]).addClass("layui-show");
    $(lis[showIndex]).addClass("layui-this");
    console.log(showIndex);
    console.log("执行了");
});





