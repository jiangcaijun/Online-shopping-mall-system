$(document).ready(function(){
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
	function GetQueryString(name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return "";
	}
	
	var flag = GetQueryString("result");
	if(flag =="false"){
		document.getElementById("false").style.display="";
		document.getElementById("thing").style.display="none";
	}else{
		document.getElementById("success").style.display="";
		document.getElementById("thing").style.display="";
		var itemId = GetQueryString("itemId");
		$.getJSON("/user",{action:"findPurItem",itemId:itemId},function(data){
			$.each(data,function(i,n){
				var pic =document.getElementById("mainPic");
				pic.src ="/"+n.mainPic+"95x100.jpg";
				$("#title").html(n.title);

				//$("#info").html(n.info);

				/*$("#info").html(n.info);*/

			});
		});
		var addressId = GetQueryString("addressId");
		$.getJSON("/user",{action:"findPurAdd",addressId:addressId},function(data){
			$.each(data,function(i,n){
				alert(n.city);
				$("#city").html(n.city);
				$("#address").html(n.address);
				$("#name").html(n.gainName);
				$("#tel").html(n.tel);
				
			});
		});
		
		$.getJSON("/user",{action:"findEx",itemId:itemId},function(data){
			$.each(data,function(i,n){
				$("#credit").html((n.num)*(n.price));
				$("#time").html(formatterDate(n.time));
			});
		});
	}
});

function formatterDate(val) {
	//alert(val);
    var date = new Date(val);
    var m = date.getMonth() + 1;
    var d = date.getDate();
    var h = date.getHours();
    var mi = date.getMinutes();
    var s = date.getSeconds();
    return date.getFullYear() + '-' + (m<10?('0'+m):m) + '-' + (d<10?('0'+d):d) + ' ' + (h<10?('0'+h):h) + ':' + (mi<10?('0'+mi):mi + ":" + (s<10?('0'+s):s));
}