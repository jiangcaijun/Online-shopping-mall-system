<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />

<link rel="icon" href="/favicon.ico" sizes="16x16 32x32">
<link rel="stylesheet" type="text/css"
	href="https://verify.meituan.com/static/sass/normalize_30583ed.css" />
<link rel="stylesheet" type="text/css"
	href="https://verify.meituan.com/static/pkg/global_a72d4b5.css" />
<link rel="stylesheet" type="text/css"
	href="https://verify.meituan.com/static/sass/page/info_9166934.css" />

<title>通过绑定的邮箱</title>

<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/shopping-mall-index.css" />
<script type="text/javascript" src="js/zhonglin.js"></script>
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="//s0.meituan.net/bs/css/?f=fs:fe-sso-fs/build/page/forget/index.be495f4.css">
<link rel="stylesheet" type="text/css" href="css/jcj_findPw_3.css" />
<script type="text/javascript">
	
</script>
</head>

<body class="pg-retrieve theme--www">

	<header class="header--mini">
	<div class="wrapper cf">
		<h1>
			<a href="#" title="宅客微购"><img src="img/logo.jpg" /></a>
		</h1>
		<div class="login-block">
			<span class="tip">已有宅客微购账号？</span> <a class="btn btn-small login"
				href="/account/unitivelogin?service&#x3D;www&amp;continue&#x3D;http%3A%2F%2Fwww.meituan.com%2Faccount%2Fsettoken">登录</a>
		</div>
	</div>
	</header>

	<div class="content">
		<h3 class="headline">
			<span class="headline__content">找回登录密码</span>
		</h3>
		<ul class="steps-bar steps-bar--dark cf">
			<li class="step step--post" style="z-index: 3"><span
				class="step__num">1.</span> <span>确认账号</span> <span
				class="arrow__background"></span><span class="arrow__foreground"></span>
			</li>
			<li class="step step--post" style="z-index: 2"><span
				class="step__num">2.</span> <span>选择验证方式</span> <span
				class="arrow__background"></span><span class="arrow__foreground"></span>
			</li>
			<li class="step step--first step--current" style="z-index: 1"><span
				class="step__num">3.</span> <span>验证/修改</span> <span
				class="arrow__background"></span><span class="arrow__foreground"></span>
			</li>
			<li class="step step--last step--post" style="z-index: 0"><span
				class="step__num">4.</span> <span>完成</span></li>
		</ul>
		</ul>
		<div class="verify-help-title cf">
			<span class="tip-status tip-status--large tip-status--large--info"></span>
			<h3 class="title">邮件已发送</h3>
			<p class="sub-title">
				请到您的邮箱查阅来自宅客微购的邮件
			</p>
		</div>		
		<div class="form-field">
			<a class="btn next-step"  onclick="window.open('http://mail.qq.com/')">去邮箱收信</a> 
		</div>
		<div class="common-bubble resend-tip">
			<div class="form__wrapper">
				<form class="form__content" method="POST" action="/findpwd">
					<div class="form-field">
						<label>邮箱动态码</label> <input class="f-text account" name="newCode"
							id="newCode" type="text" /> <span class="tip" style="display: none;"><i
							class="icon"></i><span></span></span>
					</div>
					<div class="form-field">
						<input type="hidden" name="action" value="verify" /> <input type="submit"
							class="btn" value="下一步" />
					</div>
				</form>
			</div>

			<h4 class="resend-tip__head">没有收到邮件？</h4>
			<ul class="resend-tip__list">
				<li>请先检查是否在垃圾邮件中。如果还未收到，请重新发送邮件<br>
					<div class="J-resend-error-tip resend-error-tip"
						style="display: none;">
						<span class="tip-status tip-status--error"></span><span
							class="J-content"></span>
					</div> <input type="button" value="重新发送邮件"
					class="btn-normal btn-small resend-email-button J-resend-email-button"
					href="javascript:void(0);" data-mtevent="resend/mail" />
			</ul>

		</div>
		</form>
	</div>
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


	</footer>

	<script>
		window.onunload = function() {
		};
	</script>
	<script type="text/javascript" src="/js/login.js"></script>
</body>
</html>
