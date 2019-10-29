var countTime = 60; //设置倒计时60s
function sendSms() {
    var phone = $('#phone').val(); // 取得用户输入的手机号码
    //alert(phone+"11111111111");
    if (/^1[3456789]\d{9}$/.test(phone)) { // 验证通过
        $.post("/phoneLogin", {"phone": phone}, function (obj) {
            //alert("------------"+obj);
            if (obj == "success") {
                // 调用倒计时方法
                // 信息提示
                showTime();
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg('验证码发送成功！', {time: 1000, icon: 1})
                })
            }
        }, 'text');
    } else { //手机号码有误
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.msg('你输入的手机号码有误！', {time: 1000, icon: 2})
        })
    }
};

function showTime() {
    if (countTime == 0) {
        $("#send").attr('disabled', false);
        $("#send").val('发送验证码');
        countTime = 60;
        return false;
    } else {
        $("#send").attr('disabled', true);
        $("#send").val('正在发送' + countTime + "s");
        countTime--;
    }
    setTimeout(function () {
        showTime()
    }, 1000)
}