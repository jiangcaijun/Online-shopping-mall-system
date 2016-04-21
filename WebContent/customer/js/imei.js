/**
 * 设备条形码js
 */
/*查找用户的设备*/
function getDevice(){
	var device = "";
	$.getJSON("/user",{action:"findDevice",date:new Date()},function(data){
		$.each(data,function(i,n){
			//alert(n.device);
			device += "<li><p class=\"pr-con-bt f-l\">"+n.device+"</p><p class=\"pr-con-sl f-l\">"+n.imei+"</p><p class=\"pr-con-yf f-l\">"+ formatterDate(n.time) +"</p><div style=\"clear: both;\"></div></li>";
		});
		device += "<div style=\"clear: both;\"></div>";
		$("#devices").html(device);
	});
}

/*时间格式转换*/
function formatterDate(val) {
	//alert(val);//val是时间
    var date = new Date(val);
    var m = date.getMonth() + 1;
    var d = date.getDate();
    var h = date.getHours();
    var mi = date.getMinutes();
    var s = date.getSeconds();
    return date.getFullYear() + '-' + (m<10?('0'+m):m) + '-' + (d<10?('0'+d):d) + ' ' + (h<10?('0'+h):h) + ':' + (mi<10?('0'+mi):mi + ":" + (s<10?('0'+s):s));
}

/*将用户绑定的设备初始出来*/
$(document).ready(function(){
	getDevice();
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
/*设备绑定*/
function getBd(){
	var imei = document.getElementById("imei").value;
	if(imei != ""){
		document.getElementById("ii").style.display="none";
		$.get("/user",{action:"device",imei:imei},function(data){
			alert(data);
			/*绑定成功后将用户绑定好的设备信息展示出来*/
			getDevice();
		});
	}else{
		document.getElementById("ii").style.display="";
	}
}