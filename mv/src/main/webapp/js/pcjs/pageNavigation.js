$(function() {

    console.log("js中的内容counts:--"+counts);
    console.log("js中的内容:pSize--"+pSize);
    console.log("js中的内容:pCur--"+pCur);

	layui.use('laypage', function() {
		var laypage = layui.laypage;
		//执行一个laypage实例
		laypage.render({
			elem: 'fenye' //注意，这里的 test1 是 ID，不用加 # 号
			,count: counts //数据总数，从服务端得到
			,limit: pSize  //每一页的大小
			,curr:pCur
			,groups: 5 //连续出现的页码的个数
				//,curr:location.hash.replace('#fenye','')//获取起始页
				//,hash:'fenye'//自定义hash值
			,layout: ['count', 'prev', 'page', 'next', 'skip'],
			jump: function(obj, first) {//分页切换时候触发
				if(!first) {//首次不会执行
                    doGoPage(obj.curr);
				}
			}
		});
	});
});

//当你切换分页是触发
function doGoPage(pCur){
    var pCurId = document.getElementById("pCurId");
    pCurId.setAttribute("value",pCur);
    document.forms[0];
    document.forms[0].action=url;
    document.forms[0].submit();
}



