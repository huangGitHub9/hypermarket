$(function(){
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    //异步加载该数据
    var result = ajaxLoadMovieClickCounts();

    //[
    // {movieTypeName=动作, clickCounts=172},
    // {movieTypeName=喜剧, clickCounts=253},
    // {movieTypeName=恐怖, clickCounts=20},
    // {movieTypeName=爱情, clickCounts=50},
    // {movieTypeName=科幻, clickCounts=155}
    // ]
    var XYData = getXYData(result);
    console.log("得到xy的数据");
    console.log(XYData);
    movieClickCountsDataFill(myChart,XYData);
});


//处理后端返回的json数据 使其变为echarts要求的数据
function getXYData(result){
    console.log(result);
    var xData =new Array();
    var yData = new Array();
    for(var i=0;i<result.length;i++){
        xData[i] = result[i].movieTypeName;
        yData[i] = result[i].clickCounts;
    }
    var XYData ={
        "xData":xData,
        "yData":yData
    }
    return XYData;
}

//异步请器电影类型的点击量并返回json数据
function ajaxLoadMovieClickCounts(){
    var result;
    $.ajax({
        type:"POST",
        url:movieTypeClickCountsUrl,
        dataType:"JSON",
        async:false,
        success:function(data){
           result = data;
        }
    });

    return result;
}

function movieClickCountsDataFill(myChart,XYData){
// 指定图表的配置项和数据
    var option = {
        title: {
            text: '电影类型点击量'
        },
        tooltip: {},
        legend: {
            data:['点击量']
        },
        xAxis: {
            data: XYData.xData
        },
        yAxis: {},
        series: [{
            name: '点击量',
            type: 'bar',
            data: XYData.yData
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}