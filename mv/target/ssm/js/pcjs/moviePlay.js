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
$(function(){



	//给发送弹幕的输入框注册按键事件
/*
    $("#barrageContent").keydown(function(event){
        var value = $("#barrageContent").val();
        console.log(event.which);
        if(event.which == 13 && value !=""){

            //addBarrage(movieId);
        }

	});*/

    //如何实现相同视频的弹幕内容能同步


	var media  = document.getElementById("media"); 
	media.addEventListener("play",function(){
		console.log("视频播放了");
		DANMU_JIAN_NUMBER = 1;
		VIDEO_PLAY_STATE = STATE_START;
        manyPostBarrage();
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
			removeAllDanmu();
			
		}else if(click_state == CLOSE_DANMU){
			//说明你想开启弹幕
			click_state = START_DANMU;
			$(this).text("开启弹幕");
			console.log("开启弹幕");
			DANMU_INDEX=0;
			playVideo(danmu);
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



//开始播放视频
function playVideo(danmu){
	console.log('我被执行了');
	var $danmuku = $("#player-viedo-danmuku");
	if(VIDEO_PLAY_STATE == STATE_START){
		for(;DANMU_INDEX<danmu.length;DANMU_INDEX++){
			var danmuR = parseInt(Math.random()*(MAXX-MINX+1)+MINX,10);
			var danmuT = parseInt(Math.random()*(DANMU_BOTTOM_TOP-DANMU__TOP_TOP+1)+DANMU__TOP_TOP,10);
			//var color_index = parseInt(Math.random()*((DAMU_COLOR.length-1)-0+1)+0,10);
			//var danmuColor = DAMU_COLOR[color_index];
			var danmuColor = "white";
			var danmuContent = danmu[DANMU_INDEX].content;
			createDanMuNode($danmuku,danmuR,danmuT,danmuColor,danmuContent);
		}
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
			//删除该节点造成内存溢出                                                                                          
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



//异步添加弹幕消息  添加完毕后 要在屏幕上显示用户的弹幕的信息
function addBarrage(movieId,userMemEndTime){
	var Barvalue = $("#barrageContent").val();
	//如果为空则不发送 并提示用户
	//否则发送消息
	if(Barvalue !=""){
        $.ajax({
            type:"get",
            url:urlAddBarragePath,
			dataType:"josn",
            data:{"movieId":movieId,"barrageContent":Barvalue},
			success:function(msg){
            	console.log(msg.success);
		}
        });
        //根据用户的身份来确定该弹幕的位置
        var barrageTop = getBarrageTop(userMemEndTime);

        showUserBarrage(Barvalue,barrageTop);
        //异步请求完毕清空发送输入空
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

//显示用户的弹幕的消息
function showUserBarrage(barageContent,barrageTop){
    var $danmuku = $("#player-viedo-danmuku");
    var danmuR = 1100;
    var danmuT = barrageTop;//弹幕的高度位置
    var color_index = parseInt(Math.random()*((DAMU_COLOR.length-1)-0+1)+0,10);
    var danmuColor = DAMU_COLOR[color_index];
    var danmuContent = barageContent;

    createDanMuNode($danmuku,danmuR,danmuT,danmuColor,danmuContent);
}

//请求一的弹幕数据
function postOneBarrage(){
    $.ajax({
        type:"post",
        url:urlFindBarragePath,
        data:{"movieId":movieId},
        dataType:"json",
        success:function(msg){
            var barrages = msg;
            danmu = barrages;
            playVideo(danmu);
            console.log(danmu);
            console.log(danmu.length);
        }
    });
}

function manyPostBarrage(){
    var pCur = 1;
    var pSize = 10;
    var postBarrageTime = setInterval(function(){
        $.ajax({
            type:"post",
            url:urlFindBarragePath,
            data:{"movieId":movieId,"pCur":pCur,"pSize":pSize},
            dataType:"json",
            success:function(msg){
                var barrages = msg;
                if(barrages.length != 0){
                  var  danmu = barrages;
                    playVideo(danmu);
                    DANMU_INDEX=0;
                    console.log(danmu);
                   // console.log(danmu.length);
                    //请求下一页
                    pCur = pCur+1;
                }else{
                     clearInterval(postBarrageTime);
                   // pCur = pCur-1;
                }
            }
        });

    },5000);
}

//vip的充值链接
function payVip(){
	//这个时候视频要暂停
    var media  = document.getElementById("media");
    media.pause();
    $('#myModal').modal('show');
}




















