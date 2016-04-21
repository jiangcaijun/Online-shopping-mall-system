$(document).ready(function() {
	var t = Math.random();
	var url="";
	url = "/item?action=recommen&t="+t;
	//手动上热门
	 $.getJSON(url,function(data){
		 var pic = "<li><a href=\"/itemDetils_2.jsp?id="+data[0].id+"\"><img src=\""+data[0].mainPic+"\" /></a></li>";
		 pic= pic+"<li><a href=\"/itemDetils_2.jsp?id="+data[1].id+"\"><img src=\""+data[1].mainPic+"\" /></a></li>";
		 pic= pic+"<li><a href=\"/itemDetils_2.jsp?id="+data[2].id+"\"><img src=\""+data[2].mainPic+"\" /></a></li>";
		 pic= pic+"<li><a href=\"/itemDetils_2.jsp?id="+data[3].id+"\"><img src=\""+data[3].mainPic+"\" /></a></li>";
		 pic= pic+"<li><a href=\"/itemDetils_2.jsp?id="+data[4].id+"\"><img src=\""+data[4].mainPic+"\" /></a></li>";
		 pic= pic+"<li><a href=\"/itemDetils_2.jsp?id="+data[5].id+"\"><img src=\""+data[0].mainPic+"\" /></a></li>";
		 pic= pic+"<div style=\"clear:both;\"></div>";
		 $("#recommen").html(pic);
	});
	 //热门推荐
	url = "/item?action=hotItem&t="+t;
	$.getJSON(url,function(data){
		var pic = "<li class=\"ys2\">\<a href=\"/itemDetils_2.jsp?id="+data[0].id+"\"><img  src=\""+data[0].mainPic+"191x157.jpg\" /></a><p><a href=\"/itemDetils_2.jsp?id="+data[0].id+"\"><font size=2px>"+data[0].title+"</font></a></p></li>";
		pic = pic+" <li class=\"ys2\">\<a  href=\"/itemDetils_2.jsp?id="+data[1].id+"\"><img src=\""+data[1].mainPic+"191x157.jpg\" /></a><p><a href=\"/itemDetils_2.jsp?id="+data[1].id+"\"><font size=2px>"+data[1].title+"</font></a></p></li>";
		pic = pic +" <li class=\"ys2\">\<a href=\"/itemDetils_2.jsp?id="+data[2].id+"\"><img  src=\""+data[2].mainPic+"191x157.jpg\"/></a><p><a href=\"/itemDetils_2.jsp?id="+data[2].id+"\"><font size=2px>"+data[2].title+"</font></a></p></li>";
		pic = pic +" <li class=\"ys2\"style=\" width:298px;\">\<a margin-bottom:10 href=\"/itemDetils_2.jsp?id="+data[3].id+"\"><img  src=\""+data[3].mainPic+"191x157.jpg\"/></a><p><a href=\"/itemDetils_2.jsp?id="+data[3].id+"\"><font size=2px>"+data[3].title+"</font></a></p></li>";
		$("#hot").html(pic);
	});
	//运动
	url="/item?action=drink&t="+t;
	$.getJSON(url,function(data){
		var drink = data.rows;
		var item = "";
		$.each( data.rows, function(i, n){
			 item = item +"<li><div class=\"li-top\"><a href=\"/itemDetils_2.jsp?id="+n.id+"\" class=\"li-top-tu\"><img src=\""+n.mainPic+"95x100.jpg\" /></a><p class=\"jiage\"><span class=\"sp1\">￥"+n.price+"</p></div><p class=\"miaoshu\">"+n.title+"</p><div class=\"li-md\" ><div class=\"md-l f-l\"><p class=\"md-l-l f-l\"ap=\"\"></p><div class=\"md-l-r f-l\"><a href=\"JavaScript:;\"class=\"md-xs\"at=\"\"></a><a href=\"JavaScript:;\"class=\"md-xx\"ab=\"\"></a></div><div style=\"clear:both;\"></div></div><div class=\"md-r f-l\"><button class=\"md-l-btn1\"><a href = \"/itemDetils_2.jsp?id="+n.id+"\" style=\"color:#fff\">立即购买</a></button></div><div style=\"clear:both;\"></div></div><p class=\"pingjia\"></p><p class=\"weike\">赫马商城自营</p>"
			});
		$("#drink").html(item);
	});
	//3c
	url="/item?action=Digital&t="+t;
	$.getJSON(url,function(data){
		var drink = data.rows;
		var item = "";
		$.each( data.rows, function(i, n){
			 item = item +"<li><div class=\"li-top\"><a href=\"/itemDetils_2.jsp?id="+n.id+"\" class=\"li-top-tu\"><img src=\""+n.mainPic+"95x100.jpg\" /></a><p class=\"jiage\"><span class=\"sp1\">￥"+n.price+"</p></div><p class=\"miaoshu\">"+n.title+"</p><div class=\"li-md\" ><div class=\"md-l f-l\"><p class=\"md-l-l f-l\"ap=\"\"></p><div class=\"md-l-r f-l\"><a href=\"JavaScript:;\"class=\"md-xs\"at=\"\"></a><a href=\"JavaScript:;\"class=\"md-xx\"ab=\"\"></a></div><div style=\"clear:both;\"></div></div><div class=\"md-r f-l\"><button class=\"md-l-btn1\"><a href = \"/itemDetils_2.jsp?id="+n.id+"\" style=\"color:#fff\">立即购买</a></button></div><div style=\"clear:both;\"></div></div><p class=\"pingjia\"></p><p class=\"weike\">赫马商城自营</p>"
			});
		$("#Digital").html(item);
	});
	//日常
	url = "/item?action=goods&t="+t;
	$.getJSON(url,function(data){
		var drink = data.rows;
		var item = "";
		$.each( data.rows, function(i, n){
			 item = item +"<li><div class=\"li-top\"><a href=\"/itemDetils_2.jsp?id="+n.id+"\" class=\"li-top-tu\"><img src=\""+n.mainPic+"95x100.jpg\" /></a><p class=\"jiage\"><span class=\"sp1\">￥"+n.price+"</p></div><p class=\"miaoshu\">"+n.title+"</p><div class=\"li-md\" ><div class=\"md-l f-l\"><p class=\"md-l-l f-l\"ap=\"\"></p><div class=\"md-l-r f-l\"><a href=\"JavaScript:;\"class=\"md-xs\"at=\"\"></a><a href=\"JavaScript:;\"class=\"md-xx\"ab=\"\"></a></div><div style=\"clear:both;\"></div></div><div class=\"md-r f-l\"><button class=\"md-l-btn1\"><a href = \"/itemDetils_2.jsp?id="+n.id+"\" style=\"color:#fff\">立即购买</a></button></div><div style=\"clear:both;\"></div></div><p class=\"pingjia\"></p><p class=\"weike\">赫马商城自营</p>"
			});
		$("#goods").html(item);
	});
	$("#search").click( function () {
		var itemSearch = $("#itemSearch").val();
		itemSearch = encodeURI(encodeURI(itemSearch));
		if(itemSearch!=""){
			location.href ="/searchDetails.jsp?search="+itemSearch;
		}
	});
	
	$(".btn1-r").mouseenter(function(){
		  $(".btn1-r").css("background-color","#C91623");
	});
	
	$(".btn1-r").mouseleave(function(){
		  $(".btn1-r").css("background-color","#fff");
		  $(".btn1-r").css("color","#AB5100");
	});
	
	$(".btn1-l").mouseenter(function(){
		  $(".btn1-l").css("background-color","#C91623");
	});
	
	$(".btn1-l").mouseleave(function(){
		  $(".btn1-l").css("background-color","#fff");
		  $(".btn1-l").css("color","#AB5100");
	});
	
	$(".btn1-r").click(function(){
		location.href ="/login.jsp";
	});
	$(".btn1-l").click(function(){
		location.href ="/reg.jsp";
	})
	
	$.post("/user", {action:"checkLogin"},function(data){
		  if(data=="true"){
			  $(".btn1").html("");
			  $(".btn1").css("background-color","#fff");
			  $(".btn1").css("border","none");
			  $("#logout").text("退出");
		  }
		  	
	});
	
	$.post("/user", {action:"checkAdmin"},function(data){
		  if(data=="true")
		  $(".btn1").after("<button class=\"btn2\">商家入口    ></button><script type=\"text/javascript\">$(\".btn2\").click(function(){location.href =\"/admin/index.jsp\";})</script>");
	});
});
