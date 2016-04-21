$(document).ready(function() {
	$("#getCode").click(function(){
		var phone=$("#tel").val();
		var pattern = /^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
		if(pattern.test(phone)){
			
			var btn = $('#getCode');
			btn.attr('disabled', true);
			var time = 60;
		    btn.text(time <= 0 ? "获取手机验证码" : ("" + (time) + "秒后可发送"));
		    var hander = setInterval(function() {
		        if (time <= 0) {
		            clearInterval(hander); //清除倒计时
		            btn.text("获取手机验证码");
		            btn.attr("disabled", false);
		            return false;
		        } else {
		            btn.text("" + (time--) + "秒后可发送");
		        }
		    }, 1000);
			$.post("/user", {action:"sendSmsCodeBind", smsToBind:phone,date:new Date()}, function(data){
					
			});
		}
	});
	
	$.getJSON("/user",{action:"findOne"},function(data){
		$("#img").attr("src",data.url);
		$("#name").text(data.username);
		$("#credit").text(data.credit);
		$("#telephone").text(data.tel);
	});
	
	$("#tel").blur( function () {
		var phone=$("#tel").val();
		var pattern = /^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
		if(!pattern.test(phone)){
			$("#tel").css({ border: "1px solid #CE1620" });
			$("#error").text("手机号错误");
		}else{
			$.get("/user",{action:"telVerify",phoneNo:phone,date:new Date()},function(data){
				if(data==true){
					$("#tel").css({ border: "1px solid #D9D9D9" });
					$("#error").text("");
				}else{
					$("#tel").css({ border: "1px solid #CE1620" });
					$("#error").text("手机号存在");
				}
			});
			
		}
	});
	
	$("#updateTel").click(function(){
		$(".data-con").hide();
		$("#editTel").show();
	});
	
	$("#code").blur(function (){
		$.post("/user",{action:"verify",newCodeBind:$("#code").val(),date:new Date()},function(data){
			if(data=="ok"){
				$("#code").css({ border: "1px solid #D9D9D9" });
				$("#codeError").text("");
			}else{
				$("#code").css({ border: "1px solid #CE1620" });
				$("#codeError").text("验证码错误");
			}
		});
	});
	
	$(".btn-pst").click(function(){
		if($("#error").text()==""&&$("#codeError").text()==""){
			var phone=$("#tel").val();
			$.post("/user",{action:"tel",tel:phone,date:new Date()},function(data){
				if(data==true){
					location.href ="/personData.jsp";
				}else{
					alert("操作失败");
				}
			})
		}
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