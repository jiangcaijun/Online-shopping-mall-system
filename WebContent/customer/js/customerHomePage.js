$(document).ready(function() {
	
	url = "/item?action=like";
	$.getJSON(url,function(data){
		
		var item = "";
		$.each( data, function(i, n){
			 item = item +"<li><div class=\"li-top\"><a href=\"/itemDetils_2.jsp?id="+n.id+"\" class=\"li-top-tu\"><img src=\"/"+n.mainPic+"95x100.jpg\" /></a><p class=\"jiage\"><span class=\"sp1\">￥"+n.price+"</p></div><p class=\"miaoshu\" style=\"height:50px\">"+n.title+"</p><div class=\"li-md\" ><div class=\"md-l f-l\"><p class=\"md-l-l f-l\"ap=\"\" style=\"border:none\"></p><div class=\"md-l-r f-l\" style=\"border:none\"><a href=\"JavaScript:;\"class=\"md-xs\"at=\"\" style=\"border:none\"></a><a href=\"JavaScript:;\"class=\"md-xx\"ab=\"\" style=\"border:none\"></a></div><div style=\"clear:both;\"></div></div><div class=\"md-r f-l\"><button class=\"md-l-btn1\"><a href = \"/itemDetils_2.jsp?id="+n.id+"\" style=\"color:#fff\">立即购买</a></button></div><div style=\"clear:both;\"></div></div><p class=\"pingjia\"></p><p class=\"weike\">赫马商城自营</p>"
			});
		$("#like").html(item);
	});
	$("#search").click( function () {
		var itemSearch = $("#itemSearch").val();
		itemSearch = encodeURI(encodeURI(itemSearch));
		if(itemSearch!=""){
			location.href ="/searchDetails.jsp?search="+itemSearch;
		}
	});
	
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