<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>确认订单</title>
<link rel="stylesheet" type="text/css" href="/customer/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="/customer/css/shopping-mall-index2.css" />
<script type="text/javascript" src="/customer/js/jquery.js"></script>
<script type="text/javascript" src="/customer/js/zhonglin.js"></script>
<script type="text/javascript" src="/customer/js/confirm.js"></script>
</head>

<body style="position: relative;">

	<!--top 开始-->
	<div class="top">
		<div class="top-con w1200">
			<p class="t-con-l f-l">您好，欢迎来到赫马积分商城</p>
			<ul class="t-con-r f-r">
				<li><a href="#">我 (个人中心)</a></li>
				<li><a href="#">我的订单</a></li>
				<li class="erweima"><a href="#">扫描二维码</a>
					<div class="ewm-tu">
						<a href="#"><img src="/img/erweima-tu.jpg" /></a>
					</div></li>
					<li><a href="/user?action=logout" id ="logout"></a></li>  
				<div style="clear: both;"></div>
			</ul>
			<div style="clear: both;"></div>
		</div>
	</div>

	<!--logo search 开始-->
	<div class="hd-info1 w1200">
		<div class="logo f-l">
			<h1>
				<a href="#" title="中林网站"><img src="/img/logo.jpg" /></a>
			</h1>
		</div>
		<div style="clear: both;"></div>
	</div>
	<!--切换城市-->

	<!--内容开始-->
	<div class="payment-interface w1200">
		<div class="pay-info">		
			</br>
			<button class="pay-xdz-btn">使用新地址</button>
		</div>

	</div>
	<!-- <input type="text" id="province" style="display: none"> -->
	<input type="text" id="address" style="display: none">
	<!-- <input type="text" id="userName" style="display: none">
				<input type="text" id="telphone" style="display: none">
				<input type="text" id="postCode" style="display: none"> -->
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

	<!--确认订单（新增地址）-->
	<div class="confirmation-tanchuang" xgdz1="">
		<div class="tanchuang-bg"></div>
		<div class="tanchuang-con">
			<div class="tc-title">
				<h3>信息</h3>
				<a href="JavaScript:;" dz-guan=""><img
					src="/img/close-select-city.gif" /></a>
				<div style="clear: both;"></div>
			</div>
			<ul class="tc-con2">
				</br>
				<li class="tc-li1">
					<p class="l-p">操作失败</p>
				</li>
				</br>
				</br>
			</ul>

		</div>
	</div>



</body>
</html>
