<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付结果</title>
<link rel="stylesheet" type="text/css" href="/customer/css/style.css" />
<link rel="stylesheet" type="text/css" href="/customer/css/shopping-mall-index.css" />
<script type="text/javascript" src="/customer/js/jquery.js"></script>
<script type="text/javascript" src="/customer/js/zhonglin.js"></script>
<script type="text/javascript" src="/customer/js/payResult.js"></script>
</head>

<body style="position:relative;">

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
        	<h1><a href="#" title="中林网站"><img src="/img/logo.jpg" /></a></h1>
        </div>
        <div style="clear:both;"></div>
    </div>
    
    <!--切换城市-->
    </br></br>
    <!--内容开始-->
    <div class="payment w1200">
    	<div class="payment-hd">
    		<!-- 下面分别代表支付成功与支付失败，图片已自动调用————写给大斌哥 或 大飞哥 -->
        	<h3 class="success" id="success" style="display:none">支付成功</h3>
        	<h3 id="false" style="display:none">支付失败</h3>                   
        </div>
        <div class="payment-bd"  id="thing" style ="display:none">
        	<dl class="dl-hd">
            	<dt ><img id="mainPic" src="" /></dt>
                <dd>
                	<h3 id="title"></h3>
                    <P id="info"></P>
                </dd>
                <div style="clear:both;"></div>
            </dl>
            <ul class="ul-bd">           	
            	<li><span>送货至</span><p id="city"></p>（包邮）</li>
            	<li><span>兑换积分</span><p><i id="credit"></i></p></li>
            	<li><span>收货信息</span><p id="address"></p>&nbsp;&nbsp;&nbsp;<p id="name"></p>&nbsp;&nbsp;&nbsp;<p id="tel"></p></li>
            	<li><span>成交时间</span><p id="time"></p></li>
            </ul>
        </div>
        <div class="payment-ft">
        	<button class="btn1" onclick="window.location.href='/customer/orderList.jsp'">兑换记录</button>
        	<button class="btn2" onclick="window.location.href='/customer/memberPoints.jsp'">我的积分</button>
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
</body>
</html>
