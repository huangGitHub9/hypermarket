$(function() {
	layui.use('laypage', function() {
		var laypage = layui.laypage;
		//执行一个laypage实例
		laypage.render({
			elem: 'fenye' //注意，这里的 test1 是 ID，不用加 # 号
			,count: 100 //数据总数，从服务端得到
			,limit: 3
			,groups: 5 //连续出现的页码的个数
			,layout: ['count', 'prev', 'page', 'next', 'skip'],
			jump: function(obj, first) {//分页切换时候触发
				if(!first) {//首次不会执行

				}
			}
		});
	});

});
