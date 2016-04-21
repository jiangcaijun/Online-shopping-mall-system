<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>我要评价</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/shopping-mall-index.css" />
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/star-rating.css" media="all"/>
<link rel="stylesheet" type="text/css"
	href="/customer/js/webupload/webuploader.css" />
<link rel="stylesheet" type="text/css"
	href="/customer/js/webupload/style.css" />
	
	
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/zhonglin.js"></script>
<script type="text/javascript" src="/customer/js/myComment.js"></script>

<style type="text/css">
h1, .h1, h2, .h2, h3, .h3{ margin-top:0; margin-bottom:0;}
</style>


</head>

<body>
	<!--top 开始-->
    <div class="top">
    	<div class="top-con w1200">
        	<p class="t-con-l f-l">您好，欢迎来到赫马积分商城</p>
            <ul class="t-con-r f-r">
            	<li><a href="#">我 (个人中心)</a></li>
            	<li><a href="#">我的购物车</a></li>
            	<li><a href="#">我的订单</a></li>
            	<li class="erweima">
                	<a href="#">扫描二维码</a>
                    <div class="ewm-tu">
                    	<a href="#"><img src="/img/erweima-tu.jpg" /></a>
                    </div>
                </li>
                <li><a href="/user?action=logout" id ="logout"></a></li>   
			</ul>
			<div style="clear: both;"></div>
		</div>
	</div>

	<!--logo search 开始-->

	<div class="hd-info1 w1200">
		<div class="logo f-l">
			<h1>
				<a href="/homePage.jsp"><img src="/img/logo.jpg" /></a>
			</h1>
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
        <div style="clear:both;"></div>
    </div>
    
    <!--切换城市-->
   
    
    <!--内容开始-->
    <div class="evaluate w1200">
    	<dl class="eva-info1">
        	<dt><a href="#" ><img  id="mainPic" src="" /></a></dt>
            <dd>
            	<h3><a href="#" id="title"></a></h3>
                <ul>
                	<li>
                    	<span>价格</span><p class="p1" id="price"></p>
                    </li>
                	
                	<li>
                    	<span>评价</span><p id="stars"></p>分(累计评价<p id="total" ></p>条 )
                    
                	<li>
                    	<span>订单号</span><p id="exId"></p>
                    </li>
                </ul>
            </dd>
            <div style="clear:both;"></div>
        </dl>
        
        <div class="eva-info3">
        	<div class="eva-if3-l f-l">
            	<dl class="if3-l-dl1">
                	<dt>商品评价</dt>
                    <dd><textarea id="myText"></textarea></dd>
            		<div style="clear:both;"></div>
                </dl>
                <input type="hidden" id="myStar" value="">
                <dt>上传图片</dt>
                    <div id="wrapper" style="position: relative;left:80px;">
					<div id="container"  >
						<!--头部，相册选择和格式选择-->
						<div id="uploader">
							<div class="queueList">
								<div id="dndArea" class="placeholder">
									<div id="filePicker"></div>
									<p>或将照片拖到这里，单次最多可选1张</p>
								</div>
							</div>
							<div class="statusBar" style="display: none;">
								<div class="progress">
									<span class="text">0%</span> <span class="percentage"></span>
								</div>
								<div class="info"></div>
								<div class="btns">
									<div id="filePicker2"></div>
									<div class="uploadBtn" >开始上传</div>
								</div>
							</div>
						</div>
					</div>
				</div>
            	
            	<dl class="if3-l-dl3" style="">
            		
                	
                </dl>
                <button class="eva-btn" onclick="saveMyComment()">提交评价</button>
            </div>
        	<div class="eva-if3-r f-l">
           		<ul>
                	<li>
                    	<p class="p1"><span>*</span> 描述相符</p>
                        <form style="display:inline-block;">
                            <input id="input-21e" value="0" type="number" class="rating" min=0 max=5 step=0.5 data-size="xs" >
                        </form>
                    </li>
                	
                </ul>
            </div>
            <div style="clear:both;"></div>
        </div>
    </div>
    
    <!--底部服务-->
    <div class="ft-service">
    	<div class="w1200">
        	<div class="sv-con-l2 f-l" style="box-sizing:none;">
            	<dl style="width:184px;">
                	<dt><a href="#">新手上路</a></dt>
                    <dd>
                    	<a href="#">购物流程</a>
                    	<a href="#">在线支付</a>
                    </dd>
                </dl>
                <dl style="width:184px;">
                	<dt><a href="#">配送方式</a></dt>
                    <dd>
                    	<a href="#">货到付款区域</a>
                    	<a href="#">配送费标准</a>
                    </dd>
                </dl>
                <dl style="width:184px;">
                	<dt><a href="#">购物指南</a></dt>
                    <dd>
                    	<a href="#">常见问题</a>
                    	<a href="#">订购流程</a>
                    </dd>
                </dl>
                <dl style="width:184px;">
                	<dt><a href="#">售后服务</a></dt>
                    <dd>
                    	<a href="#">售后服务保障</a>
                    	<a href="#">退款说明</a>
                    	<a href="#">新手选购商品总则</a>
                    </dd>
                </dl>
                <dl style="width:184px;">
                	<dt><a href="#">关于我们</a></dt>
                    <dd>
                    	<a href="#">投诉与建议</a>
                    </dd>
                </dl>
                <div style="clear:both;"></div>
            </div>
        	<div class="sv-con-r2 f-r">
            	<p class="sv-r-tle">187-8660-5539</p>
            	<p>周一至周五9:00-17:30</p>
            	<p>（仅收市话费）</p>
            	<a href="#" class="zxkf">24小时在线客服</a>
            </div>
            <div style="clear:both;"></div>
        </div>
    </div>
    
    <!--底部 版权-->
    <div class="footer w1200">
    	<p>
        	<a href="#">关于我们</a><span>|</span>
        	<a href="#">友情链接</a><span>|</span>
        	<a href="#">宅客微购社区</a><span>|</span>
        	<a href="#">诚征英才</a><span>|</span>
        	<a href="#">商家登录</a><span>|</span>
        	<a href="#">供应商登录</a><span>|</span>
        	<a href="#">热门搜索</a><span>|</span>
        	<a href="#">宅客微购新品</a><span>|</span>
        	<a href="#">开放平台</a>
        </p>
        <p>
        	沪ICP备13044278号<span>|</span>合字B1.B2-20130004<span>|</span>营业执照<span>|</span>互联网药品信息服务资格证<span>|</span>互联网药品交易服务资格证
        </p>
    </div>
<script type="text/javascript"
		src="/customer/js/webupload/webuploader.js"></script>
<script type="text/javascript" src="/customer/js/webupload/upload_myCommment.js"></script>

<script type="text/javascript" src="/customer/js/myCommment.js"></script>
</body>
</html>
