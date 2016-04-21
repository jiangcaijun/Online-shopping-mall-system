<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>后台管理模板</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="css/matrix-style.css" />
    <link rel="stylesheet" href="css/matrix-media.css" />
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
</head>
<body>
    <!--Header-part-->
    <div id="header">
      <h1><a href="dashboard.html">赫马商城后台管理</a></h1>
    </div>
    <!--close-Header-part--> 

    <!--top-Header-menu-->
    <div id="user-nav" class="navbar navbar-inverse">
        <ul class="nav">
            <li  class="dropdown" id="profile-messages" >
                <a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle">
                    <i class="icon icon-user"></i>&nbsp;
                    <span class="text">欢迎你，${sessionScope.username}</span>&nbsp;
                </a>
            </li>
            <li class=""><a title="" href="/homePage.jsp"><i class="icon icon-share-alt"></i> <span class="text">&nbsp;退出系统</span></a></li>
        </ul>
    </div>

    <div id="sidebar" style="OVERFLOW-Y: auto; OVERFLOW-X:hidden;">
        <ul>
            <li class="submenu active">
                <a class="menu_a" link="index2.html"><i class="icon icon-home"></i> <span>首页</span></a> 
            </li>
            <li class="submenu"> 
                <a href="#">
                    <i class="icon icon-table"></i> 
                    <span>商品管理</span> 
                </a>
                <ul>
                    <li hidden="hidden"><a class="menu_a" link="items.html" id="items"><i class="icon icon-caret-right"></i>商品信息</a></li>
                    <li hidden="hidden"><a class="menu_a" link="additem.html"><i class="icon icon-caret-right"></i>商品发布</a></li>
                </ul>
            </li>
            <li class="submenu">
				<a href="#">
                    <i class="icon icon-th"></i> 
                    <span>系统管理</span> 
                </a>
				<ul>
                    <li hidden="hidden"><a class="menu_a" link="auth.html"><i class="icon icon-caret-right"></i>权限管理</a></li>
                    <li hidden="hidden"><a class="menu_a" link="staff.html"><i class="icon icon-caret-right"></i>员工管理</a></li>
                    <li hidden="hidden"><a class="menu_a" link="customer.html"><i class="icon icon-caret-right"></i>客户管理</a></li>
                    <li hidden="hidden"><a class="menu_a" link="role.html"><i class="icon icon-caret-right"></i>权限分配</a></li>
                </ul>
            </li>
            <li class="submenu"> 
                <a href="#">
                    <i class="icon icon-stop"></i> 
                    <span>通用功能</span> 
                </a>
                <ul>
                    <li hidden="hidden"><a class="menu_a" link="log.html"><i class="icon icon-caret-right"></i>日志管理</a></li>
                </ul>
            </li>
            <li class="submenu"> 
                <a href="#">
                    <i class="icon icon-info-sign"></i> 
                    <span>报表分析</span>
                </a>
                <ul>
                    <li hidden="hidden"><a class="menu_a" link="exchange.html"><i class="icon icon-caret-right"></i>兑换记录</a></li>
					<li hidden="hidden"><a class="menu_a" link="likeform.html"><i class="icon icon-caret-right"></i>顾客喜好分析</a></li>
					<li hidden="hidden"><a class="menu_a" link="classifyform.html"><i class="icon icon-caret-right"></i>商品分类统计</a></li>
                </ul>
            </li>
        </ul>
    </div>

    <div id="content">
        <div id="content-header">
          <div id="breadcrumb"> <a href="index.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
        </div>
        <iframe src="index2.html" id="iframe-main" frameborder='0' style="width:100%;"></iframe>
    </div>
 
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script> 
    <script src="js/nicescroll/jquery.nicescroll.min.js"></script> 
    <script src="js/matrix.js"></script> 
    <script src="js/index.js"></script>
</body>
</html>