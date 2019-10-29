$(function () {
    layui.use(['form', 'jquery', 'layer'], function () {
        var form = layui.form,
            $ = layui.jquery,
            layer = layui.layer;
        form.on('submit(submitBtn)', function (obj) {
            $.ajax({
                url: '/RegisterServlet/register',
                type: 'POST',
                data: $('.layui-form').serialize(),
                success: function (data) {
                    alert(data);
                    if (data == "success") {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg('增加成功！', {
                                time: 1000, icon: 1, end: function () {
                                    location.reload();
                                }
                            })
                        })
                    } else {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg('增加失败！', {time: 1000, icon: 2})
                        })
                    }
                }
            });
        })
    })
})