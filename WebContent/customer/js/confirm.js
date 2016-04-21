
$(document).ready(function(){
	var t = Math.random();
	var url="";
	function GetQueryString(name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return "";
	}
	
	url = "/user?action=findAddress&t="+t;
	$.getJSON(url,{date:new Date()},function(data){
		var add="";
		var j = 0;
		//alert(2);
		if(data.length>0){
			//alert(1);
			$.each(data,function(i,n){
				if(n.status==1){
					j = j+1;
					add = add+"<li class=\"current\"><h3><span class=\"sp1\">"+n.city+"</span>（<span class=\"sp3\">"+n.gainName+"</span> 收）</h3><p><span class=\"sp1\">"+n.address+"</span><br/><span class=\"sp2\">"+n.tel+"</span><br/><span class=\"sp2\">"+n.postcode+"</span></p> </li>";
				}else{
					add = add+"<li><h3><span class=\"sp1\">"+n.city+"</span>(<span class=\"sp3\">"+n.gainName+"</span>收)</h3><p><span class=\"sp1\">"+n.address+"</span><br/><span class=\"sp2\">"+n.tel+"</span><br/><span class=\"sp2\">"+n.postcode+"</span></p></li>";
				}
				
			});
			if(j==0){
				//alert(0);
				add =add+"<script type=\"text/javascript\">$(\"#gainAddress\").find(\"li\").eq(0).addClass(\"current\");</script>";
			}
			add =add+"<div style=\"clear: both;\"></div>";
			add =add+"<script type=\"text/javascript\">$(\".pay-dz li\").click(function(event) {$(this).addClass('current').siblings().removeClass(\"current\");$(\"#address\").html($(this).find(\"span\").eq(2).text());});</script>";
			add =add+"<script type=\"text/javascript\">$(\"#address\").html($(\"#gainAddress li[class=\'current\']\").find(\"span\").eq(2).text());</script>";
			$("#gainAddress").html(add);
		}
		
	});
	
	url ="/item?action=purChase&t="+t;
	
	$.getJSON(url,{itemId:GetQueryString("itemId")},function(data){
		var item = "";
		var num = GetQueryString("num");
		var itemId = GetQueryString("itemId");
		//alert(itemId);
		$.each(data,function(i,n){
			//alert(n.mainPic);
			item +="<div class=\"mid-tu f-l\"><a href=\"#\"><img src=\"/"+n.mainPic+"95x100.jpg\" /></a></div>";
			item += "<div class=\"mid-font f-l\" style=\"width: 300px;\"><a href=\"#\">"+n.title+"</a></div>";
			item += "<div class=\"mid-sl f-l\" style=\"margin: 10px 54px 0px 0px;\"><a href=\"JavaScript:;\"u  class=\"sl-left\" onclick = \"left()\">-</a> <input type=\"text\" id=\"nums\" value=\""+num+"\" /><a href=\"JavaScript:;\" class=\"sl-right\" onclick=\"rigth()\">+</a></div>";
			item +="<p class=\"mid-yunfei f-l\">0.00</p>";
			item +="<p class=\"mid-dj f-l\">"+n.price+"</p>";
			item +="<input type=\"number\" id=\"unit\" value="+n.price+" style=\"display:none\">";
			item +="<input type=\"text\" id=\"item1\" value=\""+itemId+"\"  style=\"display:none\">";
			item +="<p class=\"mid-je f-l\" style=\"margin: 10px 120px 0px 0px;\" id=\"amount\">"+num*(n.price)+"</p>";
			item +="<div class=\"mid-chaozuo f-l\"><select><option>送货上门</option><option>快递包邮</option></select></div>";
		});
		
		item +="<div style=\"clear: both;\"></div>";
		item +="<script type=\"text/javascript\">var nums = $(\"#nums\").val();$(\"#totalMoney\").html(nums*$(\"#unit\").attr(\"value\"));</script>";
		item +="<script type=\"text/javascript\">function left(){var vl = $(\".sl-left\").siblings(\"input\").val(); if (vl>1) {vl--;$(\".sl-left\").siblings(\"input\").val(vl);var nums = $(\"#nums\").val();$(\"#amount\").html(nums*$(\"#unit\").attr(\"value\"));$(\"#totalMoney\").html(nums*$(\"#unit\").attr(\"value\"));}}</script>";
		item +="<script type=\"text/javascript\">function rigth(){	var vl = $(\".sl-right\").siblings(\"input\").val();if (vl>0) {vl++;$(\".sl-right\").siblings(\"input\").val(vl);var nums = $(\"#nums\").val();$(\"#amount\").html(nums*$(\"#unit\").attr(\"value\"));$(\"#totalMoney\").html(nums*$(\"#unit\").attr(\"value\"));}}</script>";
		item +="<script type=\"text/javascript\">function accounts(){var nums = $(\"#nums\").val(); var address= $(\"#address\").text();itemId=$(\"#item1\").val();$.getJSON(\"/user\",{action:\"purChase\",nums:nums,itemId:itemId,address:address,date:new Date()},function(data){if(data.flag==\"false\"){location.href=\"/customer/payResult.jsp?result=false\";}else{location.href=\"/customer/payResult.jsp?result=\"+data.flag+\"&addressId=\"+data.addressId+\"&itemId=\"+data.itemId+\"\";}});}</script>";
		$("#item").html(item);
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

function saveAddress(){
	var t = Math.random();
	var url="";
	//alert($("#newPostCode").val());
	var newCity = $("#newCity").val();
	
	var newAdd =$("#newAdd").val();
	var newPostCode = $("#newPostCode").val();
	var newName = $("#newName").val();
	var newTel=$("#newTel").val();
	$.getJSON("/user",{action:"newAddress",newCity:newCity,newAdd:newAdd,newPostCode:newPostCode,newName:newName,newTel:newTel,date:new Date()},function(data){
		if(data.flag=="true"){
			url = "/user?action=findAddress&t="+t;
			$.getJSON(url,function(data){
				var add="";
				var j= 0;
				$.each(data,function(i,n){
					if(n.status==1){
						add = add+"<li class=\"current\"><h3><span class=\"sp1\">"+n.city+"</span>（<span class=\"sp3\">"+n.gainName+"</span> 收）</h3><p><span class=\"sp1\">"+n.address+"</span><br/><span class=\"sp2\">"+n.tel+"</span><br/><span class=\"sp2\">"+n.postcode+"</span></p> </li>";
					}else{
						add = add+"<li><h3><span class=\"sp1\">"+n.city+"</span>(<span class=\"sp3\">"+n.gainName+"</span>收)</h3><p><span class=\"sp1\">"+n.address+"</span><br/><span class=\"sp2\">"+n.tel+"</span><br/><span class=\"sp2\">"+n.postcode+"</span></p></li>";
					}
				});
				add =add+"<div style=\"clear: both;\"></div>";
				add =add+"<script type=\"text/javascript\">$(\".pay-dz li\").click(function(event) {$(this).addClass('current').siblings().removeClass(\"current\");});</script>";
				
				$("#gainAddress").html(add);
			});
		}
	});
}


