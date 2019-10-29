DANMU__TOP_TOP = 20;
DANMU_BOTTOM_TOP = 540;
MINX = 1100;
MAXX = 1500;
CUR_COLOR="red";
DEFAULT_COLOR="white";
STATE_START = "TRUE";
STATE_STOP = "FALSE";
VIDEO_PLAY_STATE = STATE_START;
DANMU_INDEX = 0;
DANMU_JIAN_NUMBER = 2;
CLOSE_DANMU = 1;
START_DANMU = 0;
DAMU_COLOR = ['green','red','grey','yellow','purple','brown','pink'];
SEND_DANMU_CENTER_TOP =260;
WEBSOCKET = null;//websocket连接
CLIENT_MSG_ID="";//客户端消息
CLIENT_MOVIE_ID="";//客户端视频的id
$(function(){

    //建立与服务器的之间的链接
   // var address = "ws://"+document.location.host+"/dyglxt/webSocket";
    var address = "ws://10.1.29.6:8080/dyglxt/webSocket";
    console.log(document.location.host);
    WEBSOCKET = CreateWebSocketConnection(address);
    //接受服务器的发过来的消息
    receiveMsg();


	var media  = document.getElementById("media"); 
	media.addEventListener("play",function(){
		console.log("视频播放了");
		DANMU_JIAN_NUMBER = 1;
		VIDEO_PLAY_STATE = STATE_START;
        //manyPostBarrage();
	},false);
	media.addEventListener("pause",function(){
		console.log("视频暂停了");
		DANMU_JIAN_NUMBER = 0;
		VIDEO_PLAY_STATE = STATE_STOP;
		//playVideo(danmu);
		//变量该弹幕库的
	},false);

	//判读该电影你是否能观看
	if(vipMem =="NOT"){
		controlMoviePlay(media,1);
	}

	//弹幕的开闭
	var click_state = START_DANMU;
	$("#colseAndStartDanmu").click(function(){
		if(click_state == START_DANMU){
			//说明你想关闭弹幕
			click_state = CLOSE_DANMU;
			$(this).text("关闭弹幕");
			console.log("关闭弹幕");
			//在屏幕上显示的弹幕给隐藏了 即 他们的 display=null
			$(".danmuNode").css({"display":"none"});
		}else if(click_state == CLOSE_DANMU){
			//说明你想开启弹幕
			click_state = START_DANMU;
			$(this).text("开启弹幕");
			console.log("开启弹幕");
			DANMU_INDEX=0;
			//弹幕的 样式 display
            $(".danmuNode").css({"display":"block"});
		}
	});
	
	
});

//控制视频播放的进度
function controlMoviePlay(media,playMinute){
    media.addEventListener("timeupdate",function(){
        var curTimeM = media.currentTime/60;
        console.log(media.currentTime);
        if(curTimeM >=playMinute){
            console.log(curTimeM);
            media.pause();
            $('#myModal').modal('show');
        }
    });
}


//删除该弹幕库下的所有的子节点
function removeAllDanmu(){
	var $danmuku = $("#player-viedo-danmuku");
	var danmuku = $danmuku[0];
	var childs = danmuku.childNodes;
	for(var i=childs.length-1;i>=0;i--){
		danmuku.removeChild(childs[i]);
	}
}

//在弹幕库下创建弹幕节点
function createDanMuNode($danmukuID,danmuR,danmuT,danmuColor,danmuContent){
	
	var danmukuID = $danmukuID[0];
	var div = document.createElement("div");
	
	var $div = $(div);
	$div.addClass("video-danmo");
	$div.css({transform:"translateX("+danmuR+"px)"});
	$div.css({top:danmuT+"px",color:danmuColor});
	$div.addClass("danmuNode");
	$div.text(danmuContent);
	
	//将jq转为js
	div = $div[0];
	danmukuID.appendChild(div);
	
	$div = $(div);
	var clockTime = setInterval(function(){
		var  translateX= $div.css("transform");
		var translateXs = translateX.split(",")
		var danmuR = translateXs[4]-DANMU_JIAN_NUMBER;
		$div.css({transform:"translateX("+danmuR+"px)"});
		if(danmuR ==-1000 ){
			clearInterval(clockTime);
			//删除该节点避免造成内存溢出
			danmukuID.removeChild(div);
		}
	},10);
	
}

//收藏视频和取消视频
function collectionMovie(movieId){
    $.ajax({
        type:"post",
        url:urlCollectPath,
        data:{"movieId":movieId},
        dataType:"json",
        success:function(msg){
            var obj = msg;
            if(obj.msg == "successCollect"){
                alert("收藏成功！");
                $("#collectId").text("取消收藏");
            }else{
                alert("取消收藏成功！");
                $("#collectId").text("收藏");
            }
        },
        error:function(){
            alert("收藏失败！");
        }
    });
}

//添加弹幕消息 并向服务器端发送弹幕信息  添加完毕后 要在屏幕上显示用户的弹幕的信息
function addBarrage(movieId,userMemEndTime){
	var barrageValue = $("#barrageContent").val();
	//如果为空则不发送 并提示用户
	//否则发送消息
	if(barrageValue !=""){
        //根据用户的身份来确定该弹幕的位置
        var barrageTop = getBarrageTop(userMemEndTime);
        showUserBarrage(barrageValue,barrageTop);
        CLIENT_MOVIE_ID = movieId;
        //向服务器房发送数据
        var barrageValueId = new Date().getTime().toString()+new Date().getTime().toString();
        CLIENT_MSG_ID=barrageValueId;
        sendMsg(movieId,barrageValueId,barrageValue);
        //请求完毕清空发送输入空
        $("#barrageContent").val("");
	}else{
		alert("对不起，不能发送空消息");
	}
}

//根据用户的身份来确定该弹幕的发送的位置
function getBarrageTop(userMemEndTime){
    if(userMemEndTime ==null || userMemEndTime ==""){
        console.log("我不是会员");
        return  SEND_DANMU_CENTER_TOP+140;
    }
    console.log("我是会员");
    return SEND_DANMU_CENTER_TOP;

}

//创建webSocket连接
function CreateWebSocketConnection(address){
    var websocket = null;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket(address);
    } else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        console.log("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
       // setMessageInnerHTML("WebSocket连接成功");
        console.log("WebSocket连接成功");
    }
/*
    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }*/
/*
    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }*/

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

/*    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }*/

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
/*    function send() {
        var message = document.getElementById('text').value;
        websocket.send(message);
    }*/

    return websocket;

}

//向服务器发送弹幕和该视频的id
function sendMsg(movieId,contentId,content){
    var message = "content="+content+"&contentId="+contentId+"&movieId="+movieId;
    WEBSOCKET.send(message);
    console.log("sendMsg:"+message);
}

//接收服务器发发送过来的消息跟当前发送的弹幕不一样则显示 否则不显示
function receiveMsg(){
    //接收到消息的回调方法
    WEBSOCKET.onmessage = function (event) {
        var message = event.data;
        //var message  = "content=666&contentId=15199509696571519950969657&movieId=1a1f86c0891e42849fb8ff0a8ffb7149"
        var content = message.split("&")[0];
        var contentId = message.split("&")[1];
        var movieId = message.split("&")[2];
        var contentValue =content.split("=")[1];
        var contentIdValue =contentId.split("=")[1];
        var movieIdValue =movieId.split("=")[1];
       /* console.log(contentValue);
        console.log(contentIdValue);
        console.log(movieIdValue);*/
       showOtherClientMsg(contentIdValue,contentValue,movieIdValue);
    }
}

//显示其他客户端的消息
function showOtherClientMsg(contentIdValue,contentValue,movieIdValue){
    //发送其他用户发送的弹幕，并且保证是观看的是同一个视频的
    if((contentIdValue !=CLIENT_MSG_ID)&& (movieIdValue == CLIENT_MOVIE_ID) ){
        console.log("contentValue:"+contentValue+"_"+CLIENT_MSG_ID);
        console.log("contentValue:"+contentIdValue);
        console.log("contentValue:"+movieIdValue);
        var $danmuku = $("#player-viedo-danmuku");
        var danmuR = parseInt(Math.random()*(MAXX-MINX+1)+MINX,10);
        var danmuT =parseInt(Math.random()*(DANMU_BOTTOM_TOP-DANMU__TOP_TOP+1)+DANMU__TOP_TOP,10);//弹幕的高度位置
        var danmuColor = "white";
        var danmuContent = contentValue;
        createDanMuNode($danmuku,danmuR,danmuT,danmuColor,danmuContent);
    }
}

//显示用户的弹幕的消息
function showUserBarrage(barageContent,barrageTop){
    var $danmuku = $("#player-viedo-danmuku");
    var danmuR = 900;
    var danmuT = barrageTop;//弹幕的高度位置
    var color_index = parseInt(Math.random()*((DAMU_COLOR.length-1)-0+1)+0,10);
    var danmuColor = DAMU_COLOR[color_index];
    var danmuContent = barageContent;

    createDanMuNode($danmuku,danmuR,danmuT,danmuColor,danmuContent);
}

//vip的充值链接
function payVip(){
	//这个时候视频要暂停
    var media  = document.getElementById("media");
    media.pause();
    $('#myModal').modal('show');
}

//回车键发送弹幕
function enterSendMsg(){
    if(event.keyCode == 13){
        event.returnValue=false;
        event.cancel = true;
        addBarrage(movieId,userMemEndTime);
    }
}




















