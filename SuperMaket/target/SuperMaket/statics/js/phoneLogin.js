$(function () {
    var show_num = [];
    draw(show_num);
    $("#canvas").on('click', function () {
        draw(show_num);
    });
    layui.use(['form', 'jquery', 'layer'], function () {
        var form = layui.form,
            $ = layui.jquery,
            layer = layui.layer;
        form.on('submit(submitBtn)', function () {
            var mobile = $("#mobileLoginForm").find("#phone").eq(0).val();
            var code = $("#mobileLoginForm").find("#code").eq(0).val();
            var vcode = $("#mobileLoginForm").find("#vcode").eq(0).val();
            if (mobile == null || mobile == '') {
                showError("请填写正确的手机号");
                return false;
            } else {
                if (code == null || code == "") {
                    showError("请填写校验码");
                    return false;
                } else {
                    if (vcode == null || vcode == "") {
                        showError("请填写验证码");
                        return false;
                    } else {
                        var val = $(".input-val").val().toLowerCase();
                        var num = show_num.join("");
                        if (val == num) {
                            $.ajax({
                                type: 'POST',
                                url: '/dophoneLogin',
                                data: $('.layui-form').serialize(),
                                dataType: "text",//预期服务器返回的数据类型   最常用的是  text以及json
                                success: function (data) {
                                    //alert(data+"data11111111111");
                                    if (data == "success") {

                                        window.location.href = '/sys/frame';
                                    } else if (data == "error") {

                                        window.location.href = '/noRegister';
                                    }
                                }
                            });
                        } else {
                            alert("验证码输入错误！");
                        }

                    }

                }
            }
        })
    })
})

function draw(show_num) {
    var canvas_width = $('#canvas').width();
    var canvas_height = $('#canvas').height();
    var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
    var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
    canvas.width = canvas_width;
    canvas.height = canvas_height;
    var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
    var aCode = sCode.split(",");
    var aLength = aCode.length;//获取到数组的长度

    for (var i = 0; i <= 3; i++) {
        var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
        var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
        var txt = aCode[j];//得到随机的一个内容
        show_num[i] = txt.toLowerCase();
        var x = 10 + i * 20;//文字在canvas上的x坐标
        var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
        context.font = "bold 23px 微软雅黑";

        context.translate(x, y);
        context.rotate(deg);

        context.fillStyle = randomColor();
        context.fillText(txt, 0, 0);

        context.rotate(-deg);
        context.translate(-x, -y);
    }
    for (var i = 0; i <= 5; i++) { //验证码上显示线条
        context.strokeStyle = randomColor();
        context.beginPath();
        context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.stroke();
    }
    for (var i = 0; i <= 30; i++) { //验证码上显示小点
        context.strokeStyle = randomColor();
        context.beginPath();
        var x = Math.random() * canvas_width;
        var y = Math.random() * canvas_height;
        context.moveTo(x, y);
        context.lineTo(x + 1, y + 1);
        context.stroke();
    }
}

function randomColor() {//得到随机的颜色值
    var r = Math.floor(Math.random() * 256);
    var g = Math.floor(Math.random() * 256);
    var b = Math.floor(Math.random() * 256);
    return "rgb(" + r + "," + g + "," + b + ")";
}