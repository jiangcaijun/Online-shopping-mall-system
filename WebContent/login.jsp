<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/shopping-mall-index.css" />
<script type="text/javascript" src="js/zhonglin.js"></script>
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<script type="text/javascript">
//根据cookie名获取对应值
function getCookie(c_name){
	if (document.cookie.length>0){
		var arr = document.cookie.split("; ");
		for(var i=0; i<arr.length; i++){
			var cookie = arr[i].split("=");
			if(cookie[0]==c_name){
				return cookie[1];
				break;
			}
		}
	}
	return "";
}
//页面加载就执行，作用就是拿cookie显示在输入框
function autoLogin(){
	username = getCookie("username");
	if (username!=null && username!=""){
		document.getElementById("username").value = username;
	}
	password = getCookie("password");
	if (password!=null && password!=""){
		document.getElementById("password").value = password;
	}
	if(username!="" && password!="") {
		document.getElementById("autoLogin").checked = "checked";
	}
}
function checkForm(){
	return true;
}
</script>
</head>
<body onload="autoLogin()">

	<div class="sign-logo w1200">
		<h1>
			<a href="/homePage.jsp" title="宅客微购"><img src="img/logo.jpg" /></a>
		</h1>
	</div>
	<div class="sign-con w1200">
		<img src="img/login-tu.jpg" class="sign-contu f-l" />
		<div class="sign-ipt f-l">
			<form action="/user" method="post">
			<font size="5" color="red">${message} <input type="hidden" name="action" value="login"></font>
				<p>用户名</p>
				<input type="text" name="username" id="username"
					placeholder="手机号/邮箱" />
				<p>密码</p>
				<input type="password" name="password" id="password"
					placeholder="输入密码" /></br>
				<p>验证码：</p>
				<input name="identifyCode" type="text" /><img src="/identifycode"
					id="code" onclick="refresh()" /><span></span>
				<span>看不清？点击验证码图片刷新</span></br>
				<input type="checkbox" name="autoLogin" id="autologin" value="1" checked="checked" style=" width: 20px; height: 20px;"/> 	
 				
				<span>两周内自动登录</span>
				<span></span>
				<input type="submit" id="submit" class="slig-btn" value="登录">
				</br>
				
				<p>
					尚无账号？请<a href="/reg.jsp">注册 </a><a href="/findPw_1.jsp" class="wj">忘记密码？</a>
				</p>
			</form>
		</div>
		<div style="clear: both;"></div>	
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
</body>
</html>

