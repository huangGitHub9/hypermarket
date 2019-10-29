<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>网上商城</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="/js/jquery-1.11.1.min_044d0927.js"></script>
    <script type="text/javascript" src="/js/jquery.bxslider_e88acd1b.js"></script>
    <script type="text/javascript" src="/js/menu.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>
<%@ include file="searchBar.jsp" %>
<div class="menu_bg">
    <div class="menu">
        <!--Begin 商品分类详情 Begin-->
        <div class="nav">
            <div class="nav_t">全部商品分类</div>
            <div class="leftNav">
                <ul>
                    <%-----------------------------------type1开始-------------------------------------------%>
                    <c:forEach items="${type1List}" var="type1">
                        <li>
                            <div class="fj">
                                <span class="n_img"><img src="/images/nav1.png"/><span></span> </span> <span
                                    class="fl">${type1.name }</span>
                            </div>
                            <div class="zj">
                                <div class="zj_l">
                                        <%-----------------------------------type2开始-------------------------------------------%>
                                    <c:forEach items="${type1.type2 }" var="type2">
                                    <div class="zj_l_c">
                                        <h2>
                                            <a href="/main/getProductByType2.action?type2Id=${type2.id }&page=1">${type2.name}
                                            </a>
                                        </h2>
                                            <%-----------------------------------type2结束-------------------------------------------%>
                                        <c:forEach items="${type2.type3}" var="type3">
                                            <a href="/main/getProductByType3.action?type3Id=${type3.id }&page=1">${type3.name }</a>|
                                        </c:forEach>
                                            <%-----------------------------------type3结束-------------------------------------------%>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <ul class="menu_r">
            <li><a href="/user/main.action">首页</a></li>
            <c:forEach items="${type1List }" var="type1" begin="0" end="7">
                <li><a href="/main/getProductByType1.action?type1Id=${type1.id }&page=1">${type1.name}</a></li>
            </c:forEach>
        </ul>
        <!--End 商品分类详情 End-->
    </div>
</div>
<!--End Menu End-->
<div class="i_bg bg_color">
    <div class="i_ban_bg">
        <!--Begin Banner Begin-->
        <div class="banner">
            <div class="top_slide_wrap">
                <ul class="slide_box bxslider">
                    <li><img src="/images/ban1.jpg" width="740" height="401"/></li>
                    <li><img src="/images/de2.jpg" width="740" height="401"/></li>
                    <li><img src="/images/de3.jpg" width="740" height="401"/></li>
                </ul>
                <div class="op_btns clearfix">
                    <a href="#" class="op_btn op_prev"><span></span></a>
                    <a href="#" class="op_btn op_next"><span></span></a>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            var jq2 = jQuery.noConflict();
            (function () {
                $(".bxslider").bxSlider({
                    auto: true,
                    prevSelector: jq2(".top_slide_wrap .op_prev")[0],
                    nextSelector: jq2(".top_slide_wrap .op_next")[0]
                });
            })();
        </script>
        <!--End Banner End-->
        <div class="inews">
            <div class="news_t">
					<span class="fr"><a
                            href="/goodsList_getAllNews?page=1">更多 ></a></span>新闻资讯
            </div>
            <ul>
                <c:forEach items="${newsList }" var="news">
                    <li><span>[ 公告 ]</span> <a
                            href="javascript:void(0);">${news.title }</a></li>
                </c:forEach>
            </ul>
            <div class="charge_t">
                话费充值
                <div class="ch_t_icon"></div>
            </div>
            <form>
                <table border="0" style="width: 205px; margin-top: 10px;"
                       cellspacing="0" cellpadding="0">
                    <tr height="35">
                        <td width="33">号码</td>
                        <td><input type="text" value="" class="c_ipt"/></td>
                    </tr>
                    <tr height="35">
                        <td>面值</td>
                        <td><select class="jj" name="city">
                            <option value="0" selected="selected">100元</option>
                            <option value="1">50元</option>
                            <option value="2">30元</option>
                            <option value="3">20元</option>
                            <option value="4">10元</option>
                        </select> <span style="color: #ff4e00; font-size: 14px;">￥99.5</span></td>
                    </tr>
                    <tr height="35">
                        <td colspan="2"><input type="submit" value="立即充值"
                                               class="c_btn"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <!--Begin 热门商品 Begin-->
    <div class="content mar_10">
        <div class="h_l_img">
            <div class="img"><img src="/images/l_img.jpg" width="188" height="188"/></div>
            <div class="pri_bg">
                <span class="price fl">￥53.00</span>
                <span class="fr">16R</span>
            </div>
        </div>
        <div class="hot_pro">
            <div id="featureContainer">
                <div id="feature">
                    <div id="block">
                        <div id="botton-scroll">
                            <ul class="featureUL">
                                <li class="featureBox">
                                    <div class="box">
                                        <div class="h_icon"><img src="/images/hot.png" width="50" height="50"/>
                                        </div>
                                        <div class="imgbg">
                                            <a href="#"><img src="/images/hot1.jpg" width="160" height="136"/></a>
                                        </div>
                                        <div class="name">
                                            <a href="#">
                                                <h2>德国进口</h2>
                                                德亚全脂纯牛奶200ml*48盒
                                            </a>
                                        </div>
                                        <div class="price">
                                            <font>￥<span>189</span></font> &nbsp; 26R
                                        </div>
                                    </div>
                                </li>
                                <li class="featureBox">
                                    <div class="box">
                                        <div class="h_icon"><img src="/images/hot.png" width="50" height="50"/>
                                        </div>
                                        <div class="imgbg">
                                            <a href="#"><img src="/images/hot2.jpg" width="160" height="136"/></a>
                                        </div>
                                        <div class="name">
                                            <a href="#">
                                                <h2>iphone 6S</h2>
                                                Apple/苹果 iPhone 6s Plus公开版
                                            </a>
                                        </div>
                                        <div class="price">
                                            <font>￥<span>5288</span></font> &nbsp; 25R
                                        </div>
                                    </div>
                                </li>
                                <li class="featureBox">
                                    <div class="box">
                                        <div class="h_icon"><img src="/images/hot.png" width="50" height="50"/>
                                        </div>
                                        <div class="imgbg">
                                            <a href="#"><img src="/images/hot3.jpg" width="160" height="136"/></a>
                                        </div>
                                        <div class="name">
                                            <a href="#">
                                                <h2>倩碧特惠组合套装</h2>
                                                倩碧补水组合套装8折促销
                                            </a>
                                        </div>
                                        <div class="price">
                                            <font>￥<span>368</span></font> &nbsp; 18R
                                        </div>
                                    </div>
                                </li>
                                <li class="featureBox">
                                    <div class="box">
                                        <div class="h_icon"><img src="/images/hot.png" width="50" height="50"/>
                                        </div>
                                        <div class="imgbg">
                                            <a href="#"><img src="/images/hot4.jpg" width="160" height="136"/></a>
                                        </div>
                                        <div class="name">
                                            <a href="#">
                                                <h2>品利特级橄榄油</h2>
                                                750ml*4瓶装组合 西班牙原装进口
                                            </a>
                                        </div>
                                        <div class="price">
                                            <font>￥<span>280</span></font> &nbsp; 30R
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <a class="h_prev" href="javascript:void(0);">Previous</a>
                    <a class="h_next" href="javascript:void(0);">Next</a>
                </div>
            </div>
        </div>
    </div>
    <!--Begin 限时特卖 Begin-->
    <div class="i_t mar_10">
        <span class="fl">限时特卖</span>
        <span class="i_mores fr"><a href="#">更多</a></span>
    </div>
    <div class="content">
        <div class="i_sell">
            <div id="imgPlay">
                <ul class="imgs" id="actor">
                    <li><a href="#"><img src="/images/tm_r.jpg" width="211" height="357"/></a></li>
                    <li><a href="#"><img src="/images/tm_r.jpg" width="211" height="357"/></a></li>
                    <li><a href="#"><img src="/images/tm_r.jpg" width="211" height="357"/></a></li>
                </ul>
                <div class="previ">上一张</div>
                <div class="nexti">下一张</div>
            </div>
        </div>
        <div class="sell_right">
            <div class="sell_1">
                <div class="s_img"><a href="#"><img src="/images/tm_1.jpg" width="185" height="155"/></a></div>
                <div class="s_price">￥<span>89</span></div>
                <div class="s_name">
                    <h2><a href="#">沙宣洗发水</a></h2>
                    倒计时：<span>1200</span> 时 <span>30</span> 分 <span>28</span> 秒
                </div>
            </div>
            <div class="sell_2">
                <div class="s_img"><a href="#"><img src="/images/tm_2.jpg" width="185" height="155"/></a></div>
                <div class="s_price">￥<span>289</span></div>
                <div class="s_name">
                    <h2><a href="#">德芙巧克力</a></h2>
                    倒计时：<span>1200</span> 时 <span>30</span> 分 <span>28</span> 秒
                </div>
            </div>
            <div class="sell_b1">
                <div class="sb_img"><a href="#"><img src="/images/tm_b1.jpg" width="242" height="356"/></a></div>
                <div class="s_price">￥<span>289</span></div>
                <div class="s_name">
                    <h2><a href="#">东北大米</a></h2>
                    倒计时：<span>1200</span> 时 <span>30</span> 分 <span>28</span> 秒
                </div>
            </div>
            <div class="sell_3">
                <div class="s_img"><a href="#"><img src="/images/tm_3.jpg" width="185" height="155"/></a></div>
                <div class="s_price">￥<span>289</span></div>
                <div class="s_name">
                    <h2><a href="#">迪奥香水</a></h2>
                    倒计时：<span>1200</span> 时 <span>30</span> 分 <span>28</span> 秒
                </div>
            </div>
            <div class="sell_4">
                <div class="s_img"><a href="#"><img src="/images/tm_4.jpg" width="185" height="155"/></a></div>
                <div class="s_price">￥<span>289</span></div>
                <div class="s_name">
                    <h2><a href="#">美妆</a></h2>
                    倒计时：<span>1200</span> 时 <span>30</span> 分 <span>28</span> 秒
                </div>
            </div>
            <div class="sell_b2">
                <div class="sb_img"><a href="#"><img src="/images/tm_b2.jpg" width="242" height="356"/></a></div>
                <div class="s_price">￥<span>289</span></div>
                <div class="s_name">
                    <h2><a href="#">美妆</a></h2>
                    倒计时：<span>1200</span> 时 <span>30</span> 分 <span>28</span> 秒
                </div>
            </div>
        </div>
    </div>
    <!--End 限时特卖 End-->
    <br />
    <br />
</div>
<!--保障 -->
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>