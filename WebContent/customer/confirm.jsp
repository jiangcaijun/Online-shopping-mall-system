<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>确认订单</title>
<link rel="stylesheet" type="text/css" href="/customer/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="/customer/css/shopping-mall-index.css" />
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
				<a href="/homePage.jsp"><img src="/img/logo.jpg" /></a>
			</h1>
		</div>
		<div style="clear: both;"></div>
	</div>
	<!--切换城市-->

	<!--内容开始-->
	<div class="payment-interface w1200">
		<div class="pay-info">
			<div class="info-tit">
				<h3>选择收货地址</h3>
			</div>
			<ul class="pay-dz" id="gainAddress">
				
			<div style="clear: both;"></div>
			</ul>
			</br>
			<button class="pay-xdz-btn">使用新地址</button>
		</div>
		<div class="pay-info">
			<div class="info-tit" style="border-bottom: 0;">
				<h3>订单信息</h3>
			</div>
		</div>
		<div class="cart-con-info">
			<div class="cart-con-tit" style="margin: 20px 0 5px;">
				<p class="p1" style="width: 165px;">宝贝图</p>
				<p class="p3" style="width:350px;">宝贝名</p>
				<p class="p4" style="width: 110px;">数量</p>
				<p class="p8" style="width: 75px;">运费</p>
				<p class="p5" style="width: 133px;">单价（元）</p>
				<p class="p6" style="width: 163px;">金额（元）</p>
				<p class="p7">配送方式</p>
			</div>
			<div class="info-mid" id="item">			
			</div>
			<div class="info-tijiao" >
				<p class="p1">
					实付积分：<span id="totalMoney"></span>
				</p>
				<button class="btn" onclick="accounts()">提交订单</button>
			</div>
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

	<!--确认订单（新增地址）-->
	<div class="confirmation-tanchuang" xgdz1="">
		<div class="tanchuang-bg"></div>
		<div class="tanchuang-con">
			<div class="tc-title">
				<h3>新增收货地址</h3>
				<a href="JavaScript:;" dz-guan=""><img
					src="/img/close-select-city.gif" /></a>
				<div style="clear: both;"></div>
			</div>
			<ul class="tc-con2">
				<li class="tc-li1">
					<p class="l-p">
						所在地区<span>*</span>
					</p>
					<div class="xl-dz">
						<div class="dz-left f-l">
							<p>北京市</p>
							<ul>
								<li class="current"><a href="#">北京市</a></li>
								<li><a href="#">天津市</a></li>
								<li><a href="#">上海市</a></li>
								<li><a href="#">重庆市</a></li>
								<li><a href="#">河北省</a></li>
								<li><a href="#">河南省</a></li>
								<li><a href="#">云南省</a></li>
								<li><a href="#">辽宁省</a></li>
								<li><a href="#">黑龙江省</a></li>
								<li><a href="#">湖南省</a></li>
								<li><a href="#">安徽省</a></li>
								<li><a href="#">山东省</a></li>
								<li><a href="#">江苏省</a></li>
								<li><a href="#">浙江省</a></li>
								<li><a href="#">江西省</a></li>
								<li><a href="#">湖北省</a></li>
								<li><a href="#">海南省</a></li>
								<li><a href="#">甘肃省</a></li>
								<li><a href="#">山西省</a></li>
								<li><a href="#">内蒙古</a></li>
								<li><a href="#">陕西省</a></li>
								<li><a href="#">吉林省</a></li>
								<li><a href="#">贵州省</a></li>
								<li><a href="#">广东省</a></li>
								<li><a href="#">青海省</a></li>
								<li><a href="#">四川省</a></li>
								<li><a href="#">宁夏回族自治区</a></li>
								<li><a href="#">广西壮族自治区</a></li>
								<li><a href="#">新疆维吾尔自治区</a></li>
								<li><a href="#">西藏自治区</a></li>
								<li><a href="#">澳门特别行政区</a></li>
								<li><a href="#">香港特别行政区</a></li>
								<li><a href="#">台湾省</a></li>
							</ul>
						</div>

						<div style="clear: both;"></div>
					</div>
					<input type="hidden" name="newCity" id="newCity">
					<div style="clear: both;"></div>
				</li>
				<li class="tc-li1">
					<p class="l-p">
						详细地址<span>*</span>
					</p> <textarea class="textarea1" id="newAdd"
						placeholder="请如实填写您的详细信息，如街道名称、门牌号、楼层号和房间号。"></textarea>
					<div style="clear: both;"></div>
				</li>
				<li class="tc-li1">
					<p class="l-p">
						邮政编码<span></span>
					</p> <input type="text" id="newPostCode"/>
					<div style="clear: both;"></div>
				</li>
				<li class="tc-li1">
					<p class="l-p">
						收货人姓名<span>*</span>
					</p> <input type="text" id="newName"/>
					<div style="clear: both;"></div>
				</li>
				<li class="tc-li1">
					<p class="l-p">
						联系电话<span>*</span>
					</p> <input type="text" id="newTel"/>
					<div style="clear: both;"></div>
				</li>
			</ul>
			<button class="btn-pst2" onclick="saveAddress()">保存</button>
		</div>
	</div>

	
	
</body>
</html>
