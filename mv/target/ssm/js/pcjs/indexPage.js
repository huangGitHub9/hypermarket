$(function() {




	layui.use('laypage', function() {
		var laypage = layui.laypage;

		//执行一个laypage实例
		laypage.render({
			elem: 'fenye' //注意，这里的 test1 是 ID，不用加 # 号
			,count: 100 //数据总数，从服务端得到
			,limit: 5,
			groups: 5 //连续出现的页码的个数
				//,curr:location.hash.replace('#fenye','')//获取起始页
				//,hash:'fenye'//自定义hash值
			,layout: ['count', 'prev', 'page', 'next', 'skip'],
			jump: function(obj, first) {//jump课余得到任何参数
				console.log(obj.curr); //得到当前页
				console.log(obj.limit); //得带每页显示的条目数

				if(!first) {//首次不会执行

                    var f1 = document.forms[0];
                    console.log("打印表单");
                    console.log(f1);
					console.log("do something");
				}
			}
		});
	});

});