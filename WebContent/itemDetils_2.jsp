<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品详情</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css"
	href="css/shopping-mall-index.css" />
<link rel="stylesheet" type="text/css" href="css/pagination.css" />
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/zhonglin.js"></script>
<script type="text/javascript" src="js/itemDetils.js"></script>
</head>

<body style="position: relative;">
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
			<div class="btn1" id="login">
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
					<input type="text" placeholder="搜索商品..." ss-search-show="sp" id="itemSearch"/>
				</div>
				<button class="f-r" id="search">搜 索</button>
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
	</br>
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
	<div class="details w1200">
		<div class="deta-info1">
			<div class="dt-if1-l f-l">
				<div class="dt-if1-datu">
					<ul ul qie-da="" id="mainPic">
						
						<div style="clear: both;"></div>
					</ul>
				</div>
				<!-- <div class="dt-if1-qietu">
					<a class="dt-qie-left f-l" href="JavaScript:;"><img
						src="/img/dt-if1-qietu-left.gif" /></a>
					<div class="dt-qie-con f-l">
						<ul qie-xiao="">
							<li class="current"><a href="#"><img
									src="/img/dt-if1-qietu1.gif" /></a></li>
							<li><a href="#"><img src="/img/dt-if1-qietu2.gif" /></a></li>
							<li><a href="#"><img src="/img/dt-if1-qietu3.gif" /></a></li>
							<li><a href="#"><img src="/img/dt-if1-qietu4.gif" /></a></li>
							<li><a href="#"><img src="/img/dt-if1-qietu5.gif" /></a></li>
							<li><a href="#"><img src="/img/dt-if1-qietu6.gif" /></a></li>
							<li><a href="#"><img src="/img/dt-if1-qietu7.gif" /></a></li>
							<div style="clear: both;"></div>
						</ul>
					</div>
					<a class="dt-qie-right f-r" href="JavaScript:;"><img
						src="/img/dt-if1-qietu-right.gif" /></a>
				</div> -->
				<!-- <div class="dt-if1-fx">
					<span>商品编码:128618586</span>
					<p>
						分享到：<a href="#"><img src="/img/dt-xl.gif" /></a><a href="#"><img
							src="/img/dt-kj.gif" /></a><a href="#"><img src="/img/dt-wx.gif" /></a>
					</p>
				</div> -->
			</div>

			<div class="dt-if1-m f-l">
				<div class="dt-ifm-hd">
					<h3 id="title"><a href="#"></a></h3>
					
				</div>
				<div class="dt-ifm-gojia">
					<dl>
						<dt>积分</dt>
						<dd>
							<p class="p1">
                               <span class="sp1" id="price"></span>
                            </p>
							<!-- <p class="p2">
								<span xclass="sp2">共有 2 条评价</span>
							</p> -->
						</dd>
						<div style="clear: both;"></div>
					</dl>
				</div>
				<dl class="dt-ifm-spot">
					<dt>活动</dt>
					<dd>
						<P>
							<span>满赠</span>本店满500.00元赠300.00元礼品（随机赠送）
						</P>
					</dd>
					<div style="clear: both;"></div>
				</dl>
				<dl class="dt-ifm-box1">
					<dt>送时</dt>

						<p>每天会有4个时间段统一配送，还有一个加急送，如果提前两天预定，还可以享受折扣哦！</p>

					<div style="clear: both;"></div>
				</dl>
				</br>
				<dl class="dt-ifm-box2">
					<dt>邮费</dt>
					<dd>
						<span>包邮</span>
					</dd>
					<div style="clear: both;"></div>
				</dl>
				</br>
				<dl class="dt-ifm-box3">
					<dt>数量</dt>
                    <dd>
                    	<a class="box3-left" href="JavaScript:;">-</a>
                        <input type="text" id="num" value="1">
                    	<a class="box3-right" href="JavaScript:;">+</a>
                        <em>(库存<span id="J_SpanStock" class="tb-count"></span>包)</em>
                    </dd>
					<div style="clear: both;"></div>
				</dl>
				<div class="dt-ifm-box4">
					<button class="btn1" onclick="purchase()">立即兑换</button>
				</div>
			</div>

			<div class="dt-if1-r f-r">
				<div class="dt-ifr-hd">
					<div class="dt-ifr-tit">
						<h3>赫马积分商城</h3>
					</div>
					</br></br>
					<div class="dt-ifr-tel">
						<p>专门从事家庭能源管理的智能家居创新</p>
						<p>产品研发和销售</p>
					</div>
					
					<div class="dt-ifr-tel">
						<p>地址：浙江省杭州市西湖区</p>
						<p>TEL：18616854445</p>
					</div>
					</br></br>
					<button class="dt-r-btn1"><a href="http://www.telehems.com/" style="color:#fff">查看详情</a></button>
					
				</div>

			</div>
			<div style="clear: both;"></div>
		</div>

		<div class="deta-info2">
			<div class="dt-if2-l f-l">
				<div class="if2-l-box1">
					<div class="if2-tit">
						<h3>同类产品推荐</h3>
					</div>
					<ul id="recommend">
						<!-- <li><a href="#"><img src="/img/if2-l-box1-tu1.gif" /></a> <a
							class="if2-li-tit" href="#">乐事Lay's 无限薯片（翡翠黄瓜味）104g/罐</a>
							<p>¥6.90</p></li>
						<li><a href="#"><img src="/img/if2-l-box1-tu1.gif" /></a> <a
							class="if2-li-tit" href="#">乐事Lay's 无限薯片（翡翠黄瓜味）104g/罐</a>
							<p>¥6.90</p></li>
						<li style="border-bottom: 0;"><a href="#"><img
								src="/img/if2-l-box1-tu1.gif" /></a> <a class="if2-li-tit" href="#">乐事Lay's
								无限薯片（翡翠黄瓜味）104g/罐</a>
							<p>¥6.90</p></li> -->
					</ul>
				</div>
				
			</div>
			<div class="dt-if2-r f-r">
				<ul class="if2-tit2">
					<li class="current" com-det="dt1"><a href="JavaScript:;">产品信息</a></li>
					<li com-det="dt2"><a href="JavaScript:;">商品评论</a></li>
				
					<div style="clear: both;"></div>
				</ul>
				<div style="border: 1px solid #DBDBDB;" com-det-show="dt1" id="info1">
				</div>
				<div style="display: none;" com-det-show="dt2">
					
					<div class="if2-r-box3" id="commmentAll">
						<!--分页-->
						<div>
						<div style="clear: both;"></div>
						<div class="pages">
							<div id="Pagination"></div>
							<div class="searchPage">
								<span class="page-sum">共<strong class="allPage"></strong>页
							</div>
						</div>
						<div style="clear: both;"></div>
					</div>
					</div>
				</div>
				
			</div>
			<div style="clear: both;"></div>
		</div>
	</div>

	<!--底部服务-->
	<div class="ft-service">
		<div class="w1200">
			<div class="sv-con-l2 f-l">
				<dl>
					<dt>
						<a href="#">新手上路</a>
					</dt>
					<dd>
						<a href="#">购物流程</a> <a href="#">在线支付</a>
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
					</dd>
				</dl>
				<div style="clear: both;"></div>
			</div>
			<div class="sv-con-r2 f-r">
				<p class="sv-r-tle">187-8660-5539</p>
				<p>周一至周五9:00-17:30</p>
				<p>（仅收市话费）</p>
				<a href="#" class="zxkf">24小时在线客服</a>
			</div>
			<div style="clear: both;"></div>
		</div>
	</div>

	<!--底部 版权-->
	<div class="footer w1200">
		<p>
			<a href="#">关于我们</a><span>|</span> <a href="#">友情链接</a><span>|</span>
			<a href="#">宅客微购社区</a><span>|</span> <a href="#">诚征英才</a><span>|</span>
			<a href="#">商家登录</a><span>|</span> <a href="#">供应商登录</a><span>|</span>
			<a href="#">热门搜索</a><span>|</span> <a href="#">宅客微购新品</a><span>|</span>
			<a href="#">开放平台</a>
		</p>
		<p>
			沪ICP备13044278号<span>|</span>合字B1.B2-20130004<span>|</span>营业执照<span>|</span>互联网药品信息服务资格证<span>|</span>互联网药品交易服务资格证
		</p>
	</div>
</body>
</html>

