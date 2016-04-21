$(document).ready(function(){
	var t = Math.random();
	var url="";
	url ="/user?action=findAddress&t="+t;
	$.getJSON(url,{date:new Date()},function(data){
		var add="";
		$("#total").html(data.length);
		$.each(data,function(i,n){
			if(n.status==1){
				add +="<li><p class=\"p1\"><font  color=\"red\" >"+n.gainName+"</font></p>";
				add +="<p class=\"p2\"><font  color=\"red\" >"+n.city+"</font></p>";
				add +="<p class=\"p3\" id=\""+i+"\"><font  color=\"red\" >"+n.address+"</font></p>";
				add +="<p class=\"p4\"><font  color=\"red\" >"+n.postcode+"</font></p>";
				add +="<p class=\"p5\"><font  color=\"red\" >"+n.tel+"</font></p>";
				add +="<input type=\"hidden\" id=\""+i+"\" value=\""+n.id+"\">";
				add +="<p class=\"p6\"><a href=\"JavaScript:;\" id=\"xiugai\">修改</a> | <a href=\"#\" id=\"delAdd\">删除</a></p>";
				add +=" <p class=\"p7\" ><a href=\"#\" id=\"setMainAdd\" value=\"11\">默认地址</a></p></li>";
				add +="<div style=\"clear:both;\"></div>";
			}else{
				add +="<li  ><p class=\"p1\">"+n.gainName+"</p>";
				add +="<p class=\"p2\">"+n.city+"</p>";
				add +="<p class=\"p3\" id=\""+i+"\" >"+n.address+"</p>";
				add +="<p class=\"p4\">"+n.postcode+"</p>";
				add +="<p class=\"p5\">"+n.tel+"</p>";
				add +="<input type=\"hidden\" id=\""+i+"\" value=\""+n.id+"\">";
				add +="<p class=\"p6\"><a href=\"JavaScript:;\" id=\"xiugai\">修改</a> | <a href=\"#\" id=\"delAdd\">删除</a></p>";
				add +=" <p class=\"p7\" ><a href=\"#\" id=\"setMainAdd\">默认地址</a></p></li>";
				add +="<div style=\"clear:both;\"></div>";
			}	
		});
		add += "<script type=\"text/javascript\">$(\"#allAddress li a[id=\'setMainAdd\']\").click(function(e){$(\"#allAddress li\").click(function(){var addId = $(this).find(\"input[type=\'hidden\']\").val();$.getJSON(\"/user\",{action:\"setMainAdd\",addId:addId,date:new Date()},function(data){if(data.message==\"true\"){alert(\"设置成功\");location.href=\"/customer/addressManagment.jsp\";}});});});</script>";
		add +=	"<script type=\"text/javascript\">$(\"#allAddress li a[id=\'delAdd\']\").click(function(e){$(\"#allAddress li\").click(function(){if(window.confirm(\"是否确定删除?\")){var addId = $(this).find(\"input[type=\'hidden\']\").val();$.getJSON(\"/user\",{action:\"delAdd\",addId:addId,date:new Date()},function(data){if(data.message==\"true\"){alert(\"删除成功\");location.href=\"/customer/addressManagment.jsp\";}});}});});</script>";
		add +="<script type=\"text/javascript\">$(\"#allAddress li a[id=\'xiugai\']\").click(function(e){$(\"#allAddress li\").click(function(){$(\"[xgdz2]\").show();var addId = $(this).find(\"input[type=\'hidden\']\").val();var username =$(this).find(\"p[class=\'p1\']\").text();var city =$(this).find(\"p[class=\'p2\']\").text();var address =$(this).find(\"p[class=\'p3\']\").text();var postcode =$(this).find(\"p[class=\'p4\']\").text();var tel =$(this).find(\"p[class=\'p5\']\").text();$(\"[xgdz2] .dz-left p\").text(city);$(\"[xgdz2] .tc-con2 li .textarea1\").val(address);$(\"[xgdz2] .tc-con2 li .shxm\").val(username);$(\"[xgdz2] .tc-con2 li .lxdh\").val(tel);$(\"[xgdz2] .tc-con2 li .addrId\").val(addId);$(\"[xgdz2] .tc-con2 li .yb\").val(postcode);});});</script>";
		$("#allAddress").html(add);
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
function newAddress() {
	var gainName = $("#gainName").val();
	var newCity = $("#newCity").val();
	//alert($("#newCity").val());
	var postcode = $("#postcode").val();
	var tel = $("#tel").val();
	var address = $("#address").val();
	if((gainName!=null&&gainName!="")&&(tel!=null&&tel!="")&&(address!=null&&address!="")){
		if(postcode!=null&&postcode!=""){
			$.getJSON("/user",{action:"newAddress",newCity:newCity,newPostCode:postcode,newName:gainName,newTel:tel,newAdd:address,date:new Date()},function(data){
				//alert(data.message);
				if(data.flag=="true"){
					$("#address").val("");
					$("#gainName").val("");
					$("#postcode").val("");
					$("#tel").val("");
					alert("保存成功");
					location.href="/customer/addressManagment.jsp";
				}
				
			});
			document.getElementById("message").style.display="none";
		}	
	}else{
		document.getElementById("message").style.display="";
	}
}

function changeAdd(){
		var t = Math.random();
		var url="";
		//alert($("#newPostCode").val());
		var newCity = $("#newCity").val();
		var addrId = $("#addrId").val();
		//alert(addrId);
		var newAdd =$("#newAdd").val();
		var newPostCode = $("#newPostCode").val();
		var newName = $("#newName").val();
		var newTel=$("#newTel").val();
		$.getJSON("/user",{action:"changeAdd",addrId:addrId,newCity:newCity,newAdd:newAdd,newPostCode:newPostCode,newName:newName,newTel:newTel,date:new Date()},function(data){
			if(data.flag=="true"){
				location.href="/customer/addressManagment.jsp";
			}
		});
}
			