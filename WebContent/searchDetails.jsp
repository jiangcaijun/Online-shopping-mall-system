<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>搜索列表页(有品牌)</title>

<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css"
	href="css/shopping-mall-index.css" />
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<link rel="stylesheet" type="text/css" href="css/pagination.css" />
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="js/zhonglin.js"></script>
<script type="text/javascript" src="js/searchDetils.js"></script>
</head>

<body>

	<!--top 开始-->
	<div class="top">
    	<div class="top-con w1200">
        	<p class="t-con-l f-l">您好，欢迎来到赫马积分商城</p>
            <ul class="t-con-r f-r">
            	<li><a href="/customer/customer_homepage.jsp">我 (个人中心)</a></li>
            	<li class="erweima">
                	<a href="#">微信二维码</a>
                    <div class="ewm-tu">
                    	<a href="#"><img height="95" width="95" src="/img/wechatQR.png" /></a>
                    </div>
                </li>  
                <li><a href="/user?action=logout" id ="logout"></a></li>         	
            </ul>
            <div style="clear:both;"></div>
        </div>
    </div>

	<!--logo search 开始-->
	<div class="hd-info1 w1200">
		<div class="logo f-l">
			<h1>
				<a href="/homePage.jsp" title="中林网站"><img src="/img/logo.jpg" /></a>
			</h1>
		</div>
		<div class="dianji f-r">
			<div class="btn1">
				<button class="btn1-l">注册</button>
				<button class="btn1-r">登录</button>
				<div style="clear: both;"></div>
			</div>
		</div>
		<div class="search f-r">
			<ul class="sp">
				<li class="current" ss-search="sp"><a href="JavaScript:;">商品</a></li>
				<div style="clear: both;"></div>
			</ul>
			<div class="srh">
				<div class="ipt f-l">
					<input type="text" placeholder="搜索商品..." ss-search-show="sp" id="searchText"/> 
				</div>
				<button class="f-r">搜 索</button>
				<div style="clear: both;"></div>
			</div>
			<ul class="sp2">
				<li><a href="/searchDetails.jsp?search=%25E6%2589%258B%25E6%259C%25BA">手机</a></li>
                <li><a href="/searchDetails.jsp?search=%25E7%25A7%25BB%25E5%258A%25A8%25E7%2594%25B5%25E6%25BA%2590">移动电源</a></li>
                <li><a href="/searchDetails.jsp?search=%25E6%25B0%25B4%25E6%259D%25AF">水杯</a></li>
                <li><a href="/searchDetails.jsp?search=%25E5%2585%25AC%25E8%25B7%25AF%25E8%25BD%25A6">公路车</a></li>
                <li><a href="/searchDetails.jsp?search=%25E6%2589%258B%25E7%258E%25AF">手环</a></li>
                <li><a href="/searchDetails.jsp?search=%25E7%25AF%25AE%25E7%2590%2583">篮球</a></li>
                <li><a href="/searchDetails.jsp?search=%25E6%25BB%2591%25E9%259B%25AA">滑雪 </a></li>
                <li><a href="/searchDetails.jsp?search=mp3">mp3</a></li>
                <li><a href="/searchDetails.jsp?search=%25E8%25BF%2590%25E5%258A%25A8">赫马运动</a></li>
				<div style="clear: both;"></div>
			</ul>
		</div>

		<div style="clear: both;"></div>
	</div>
<!--切换城市-->
    <div class="switch-city w1200">
    	<a href="#" class="dianji-qh">切换城市</a>
        <span class="dqm">重庆市</span>
        <div class="select-city">
        	<div class="sl-city-top">
            	<p class="f-l">切换城市</p>
                <a class="close-select-city f-r" href="#">
                	<img src="/img/close-select-city.gif" />
                </a>
            </div>
            <div class="sl-city-con">
            	<p>西北</p>
                <dl>
                	<dt>重庆市</dt>
                    <dd>
                    	<a href="#">长寿区</a>
                        <a href="#">巴南区</a>
                        <a href="#">南岸区</a>
                        <a href="#">九龙坡区</a>
                        <a href="#">沙坪坝区</a>
                        <a href="#">北碚</a>
                        <a href="#">江北区</a>
                        <a href="#">渝北区</a>
                        <a href="#">大渡口区</a>
                        <a href="#">渝中区</a>
                        <a href="#">万州</a>
                        <a href="#">武隆</a>
                        <a href="#">晏家</a>
                        <a href="#">长寿湖</a>
                        <a href="#">云集</a>
                        <a href="#">华中</a>
                        <a href="#">林封</a>
                        <a href="#">双龙</a>
                        <a href="#">石回</a>
                        <a href="#">龙凤呈祥</a>
                        <a href="#">朝天门</a>
                        <a href="#">中华</a>
                        <a href="#">玉溪</a>
                        <a href="#">云烟</a>
                        <a href="#">红塔山</a>
                        <a href="#">真龙</a>
                        <a href="#">天子</a>
                        <a href="#">娇子</a>
                    </dd>

                    <div style="clear:both;"></div>
                </dl>
                <dl>
                	<dt>新疆</dt>
                    <dd>
                    	<a href="#">齐乌鲁木</a>
                        <a href="#">昌吉</a>
                        <a href="#">巴音</a>
                        <a href="#">郭楞</a>
                        <a href="#">伊犁</a>
                        <a href="#">阿克苏</a>
                        <a href="#">喀什</a>
                        <a href="#">哈密</a>
                        <a href="#">克拉玛依</a>
                        <a href="#">博尔塔拉</a>
                        <a href="#">吐鲁番</a>
                        <a href="#">和田</a>
                        <a href="#">石河子</a>
                        <a href="#">克孜勒苏</a>
                        <a href="#">阿拉尔</a>
                        <a href="#">五家渠</a>
                        <a href="#">图木舒克</a>
                        <a href="#">库尔勒</a>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                <dl>
                	<dt>甘肃</dt>
                    <dd>
                    	<a href="#">兰州</a>
                        <a href="#">天水</a>
                        <a href="#">巴音</a>
                        <a href="#">白银</a>
                        <a href="#">庆阳</a>
                        <a href="#">平凉</a>
                        <a href="#">酒泉</a>
                        <a href="#">张掖</a>
                        <a href="#">武威</a>
                        <a href="#">定西</a>
                        <a href="#">金昌</a>
                        <a href="#">陇南</a>
                        <a href="#">临夏</a>
                        <a href="#">嘉峪关</a>
                        <a href="#">甘南</a>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                <dl>
                	<dt>宁夏</dt>
                    <dd>
                    	<a href="#">银川</a>
                        <a href="#">吴忠</a>
                        <a href="#">石嘴山</a>
                        <a href="#">中卫</a>
                        <a href="#">固原</a>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
                <dl>
                	<dt>青海</dt>
                    <dd>
                    	<a href="#">西宁</a>
                        <a href="#">海西</a>
                        <a href="#">海北</a>
                        <a href="#">果洛</a>
                        <a href="#">海东</a>
                        <a href="#">黄南</a>
                        <a href="#">玉树</a>
                        <a href="#">海南</a>
                    </dd>
                    <div style="clear:both;"></div>
                </dl>
            </div>
        </div>
    </div>



	<!--nav 开始-->
	<div style="border-bottom: 2px solid #F09E0B;">

		<div class="nav w1200">
			<a href="JavaScript:;" class="sp-kj" kj=""> 商品分类快捷 </a>
			
			<ul>
			<li><a href="/searchDetails.jsp?type=%25E8%25BF%2590%25E5%258A%25A8%25E7%2594%25A8%25E5%2593%2581">运动装备</a></li>
        	<li><a href="/searchDetails.jsp?type=%25E6%2595%25B0%25E7%25A0%25813C">数码3C</a></li>
        	<li><a href="/searchDetails.jsp?type=%25E7%2594%259F%25E6%25B4%25BB%25E7%2594%25A8%25E5%2593%2581">生活用品</a></li>
				<div style="clear: both;"></div>
			</ul>
			<div style="clear: both;"></div>
		</div>
	</div>
	<!--内容开始-->
	<!--筛选结果-->
	<div class="screening-results w1200">
		<p class="tiao" id = "totle">
		
		</p>
			<div style="clear: both;"></div>
		</div>




		<!--品牌热销-->

		<!--内容↓↑-->
		<div class="shop-page-con w1200">
			<div class="shop-pg-left f-l" style="width: 215px">
				<div class="shop-left-buttom" style="margin-top: 0;">
					<div class="sp-tit1">
						<h3>商品推荐</h3>
					</div>
					<ul class="shop-left-ul" id="recommend">
					</ul>
				</div>
			</div>
			<div class="shop-pg-right f-r">
				<div class="shop-right-cmp" style="margin-top: 0;">
					<ul class="shop-cmp-l f-l">
						<li class="current"><a href="#">综合 ↓</a></li>
						<li id = "sale"><a href="#">销量 ↓</a></li>
						<li id = "price"><a href="#">价格 ↓</a></li>
						<div style="clear: both;"></div>
					</ul>
					<div class="shop-cmp-m f-l">
						<span>价格</span><input type="number" id="low"/><span>-</span><input
							type="number" id="high"/>
					</div>
					<div class="shop-cmp-r f-l">
						<ul class="f-l">


							<div style="clear: both;"></div>
						</ul>
						<button id="sure">确定</button>
					</div>
					<div style="clear: both;"></div>
				</div>
				<div class="shop-right-con">
					<ul class="shop-ul-tu shop-ul-tu1" id="itemList">
						<div align="center"><img src="image/loading.gif" /></div>
						<div style="clear: both;"></div>
					</ul>

					<!--分页-->
					<div>
						<div style="clear: both;"></div>
						<div class="pages">
							<div id="Pagination"><div class = "pagination"></div></div>
							<div class="searchPage">
								<span class="page-sum">共<strong class="allPage">15</strong>页
							</div>
						</div>
						<div style="clear: both;"></div>
					</div>
				</div>
			</div>
			<div style="clear: both;"></div>
		</div>

		<!--底部服务-->
		<!--底部服务-->
	<div class="ft-service">
		<div class="w1200">
			<div class="sv-con-l2 f-l">
				<dl>
					<dt>
						<a href="#">新手上路</a>
					</dt>
					<dd>
						<a href="/introduce.jsp">兑换流程</a> <a href="#">在线支付</a>
					</dd>
				</dl>
				<dl>
					<dt>
						<a href="#">配送方式</a>
					</dt>
					<dd>
						<a href="#">货到付款区域</a> <a href="#">配送费标准</a>
					</dd>
				</dl>
				<dl>
					<dt>
						<a href="#">购物指南</a>
					</dt>
					<dd>
						<a href="#">常见问题</a> <a href="#">订购流程</a>
					</dd>
				</dl>
				<dl>
					<dt>
						<a href="#">售后服务</a>
					</dt>
					<dd>
						<a href="#">售后服务保障</a> <a href="#">退款说明</a> <a href="#">新手选购商品总则</a>
					</dd>
				</dl>
				<dl>
					<dt>
						<a href="#">关于我们</a>
					</dt>
					<dd>
						<a href="#">投诉与建议</a>
						<a></a>
					</dd>
				</dl>
				<div style="clear: both;"></div>
			</div>
			<div class="sv-con-r2 f-r">
				<p class="sv-r-tle">187-8660-5539</p>
				<p>7*24h（仅收市话费）</p>
				<p>地址：浙江省杭州市滨江区</p>
				<a href="#" class="zxkf">24小时在线客服</a>
			</div>
			<div style="clear: both;"></div>
		</div>
	</div>

	<!--底部 版权-->
	<div class="footer w1200">
		<p>
			<a href="#">关于我们</a><span>|</span> <a href="#">友情链接</a><span>|</span>
			<a href="#">赫马社区</a><span>|</span> <a href="#">诚征英才</a><span>|</span>
			<a href="#">商家登录</a><span>|</span> <a href="#">供应商登录</a><span>|</span>
			<a href="#">热门搜索</a><span>|</span> <a href="#">赫马商城新品</a><span>|</span>
			<a href="#">开放平台</a>
		</p>
		<p>
			沪ICP备13044278号<span>|</span>合字B1.B2-20130004<span>|</span>营业执照<span>|</span>互联网药品信息服务资格证<span>|</span>互联网药品交易服务资格证
		</p>
	</div>
	</div>

</body>
</html>
