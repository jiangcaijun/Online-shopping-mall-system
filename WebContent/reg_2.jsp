<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css"
	href="css/shopping-mall-index.css" />
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="js/zhonglin.js"></script>
<link rel="stylesheet" type="text/css"
	href="//s0.meituan.net/bs/css/?f=fs:fe-sso-fs/build/page/forget/index.be495f4.css">
<link rel="stylesheet" type="text/css" href="/css/jcj_findPw_3.css" />


</head>

<body>



	<!--top 开始-->
	<div class="top">
		<div class="top-con w1200">
			<p class="t-con-l f-l">您好，欢迎来到宅客微购</p>
			<ul class="t-con-r f-r">
				<li><a href="#">我 (个人中心)</a></li>
				<li><a href="#">我的购物车</a></li>
				<li><a href="#">我的订单</a></li>
				<li class="erweima"><a href="#">扫描二维码</a>
					<div class="ewm-tu">
						<a href="#"><img src="img/erweima-tu.jpg" /></a>
					</div></li>
				<div style="clear: both;"></div>
			</ul>
			<div style="clear: both;"></div>
		</div>
	</div>

	<!--logo search 开始-->
	<div class="hd-info1 w1200">
		<div class="logo f-l">
			<h1>
				<a href="#" title="中林网站"><img src="img/logo.jpg" /></a>
			</h1>
		</div>
		<div class="search f-r">
			<ul class="sp">
				<li class="current" ss-search="sp"><a href="JavaScript:;">商品</a></li>
				<li ss-search="dp"><a href="JavaScript:;">店铺</a></li>
				<div style="clear: both;"></div>
			</ul>
			<div class="srh">
				<div class="ipt f-l">
					<input type="text" placeholder="搜索商品..." ss-search-show="sp" /> <input
						type="text" placeholder="搜索店铺..." ss-search-show="dp"
						style="display: none;" />
				</div>
				<button class="f-r">搜 索</button>
				<div style="clear: both;"></div>
			</div>
			<ul class="sp2">
				 <li><a href="#">手机</a></li>
                <li><a href="#">移动电源</a></li>
                <li><a href="#">水杯</a></li>
                <li><a href="#">公路车</a></li>
                <li><a href="#">手环</a></li>
                <li><a href="#">篮球</a></li>
                <li><a href="#">滑雪 </a></li>
                <li><a href="#">mp3</a></li>
                <li><a href="#">赫马运动</a></li>
				<div style="clear: both;"></div>
			</ul>
		</div>
		<div style="clear: both;"></div>
	</div>

	<!--内容开始-->
	<div class="password-con registered">
	<h1 style="position: absolute; left: 328px; top: 205px;">还剩最后一步</h1>
			<div class="psw psw2">
				<p class="psw-p1">邮箱</p>
				<input type="text" name="email" id="email" placeholder="请输入邮箱"  onblur="checkEmail()"/>
				<button id="btn1" onclick="getCode()">获取邮箱验证码</button>
			</div>
			<div class="psw psw2">
			<p class="psw-p1">  </p>
			<span>  </span>
			<span id="emailC" style="display: none">请输入正确的Email</span>
			</div>
			<div class="psw psw3">
				<p class="psw-p1">邮箱验证码</p>
				<input type="text" name="reCode" id="reCode" placeholder="请输入邮箱验证码" /><span id="checkCode" value=""></span>
			</div>
			<button class="psw-btn" id="btn2" onclick="saveMail()">绑定邮箱</button>
			
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
	<script type="text/javascript" src="/js/login.js"></script>

	<script type="text/javascript" src="/js/reg.js"></script>
	<!-- <script src="//s0.meituan.net/bs/js/?f=fs:fe-sso-fs/build/page/common.be495f4.js,fe-sso-fs/build/page/signup/index.be495f4.js"></script>
 -->
 
</body>
<!-- <script type="text/javascript">
 window.onbeforeunload = function(){
		$.get("/user",{action:"clear"},function(date){
		});
		return "Are you sure?";
	}
</script> -->
</html>
