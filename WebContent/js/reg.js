function checkPw1(){
	var pw1 = document.getElementById("password").value;
	var pattern = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/; 
	if(pw1!=null&&pw1!=""){
		document.getElementById("pass").style.display="none";
		if(!pattern.test(pw1)) {
			document.getElementById("passC").style.display="";
			document.getElementById("passWd").style.display="none";
		}else{
			document.getElementById("passWd").style.display="none";
			document.getElementById("passC").style.display="none";
			document.getElementById("passC").style.display="none";
		}
		
	}else{
		document.getElementById("passWd").style.display="";
		document.getElementById("passC").style.display="none";
	}
}

function checkPw2(){
	var pw1 = document.getElementById("password").value;
	var pw2 = document.getElementById("password1").value;
	if(pw1!=null&&pw1!=""){
		if(pw2!=null&&pw2!=""){
			if(pw2!=pw1){
				document.getElementById("passWd1").style.display="";
		  }else{
			  document.getElementById("passWd1").style.display="none";
			}
		}
	}
}

function checkName(){
	var username = $("#username").val();
	if(username!=null&&username!=""){
		$.post("/user",{action:"findName",username:username,date:new Date()},function(data){
			$("#message").html(data);
		});
		document.getElementById("name").style.display="none";
	}else{
		document.getElementById("name").style.display="";
	}
}

function getCode(){
	var email = $("#email").val();
	if(email!=null&&email!=""){
		//alert("点发邮件");
		$.getJSON("/reg",{action:"sendRegCode",email:email,date:new Date()},function(data){
			alert(data.message);
		});
	}
}

function saveMail(){
	//alert("验证");
	var recode = document.getElementById("reCode").value;
	var email = document.getElementById("email").value;
	//alert(email);
	//alert(recode);
	$.getJSON("/reg",{action:"regVerify",recode:recode,email:email,date:new Date()},function(data){
		if(data.flag=="true"){
			//var email = document.getElementById("email").value;
			$.getJSON("/user",{action:"mailBox",email:email,date:new Date()},function(data){
				if(data.flag=="true"){
					location.href ="/login.jsp";
				}
			});
		}else{
			$("#checkCode").html(data.message);
		}	
	});
}

//正则检测Email
function checkEmail(){
	var email2 = document.getElementById("email").value;
	var pattern = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/; //邮箱的正则表达式  
	///^[a-zA-Z]\w{6,15}$/ig;  用户名的正则：由字母a～z(不区分大小写)、数字0～9、点、减号或下划线组成，长度为6～15个字符
	if(!pattern.test(email2)) {
		document.getElementById("emailC").style.display="";
	}else{
		document.getElementById("emailC").style.display="none";
	}
}

function zhuce(){
	var username = $("#username").val();
	var pw1 = document.getElementById("password").value;
	var pw2 = document.getElementById("password1").value;
	var identifyCode = document.getElementById("identifyCode").value;
	if(username!=null&&username!=""){
		document.getElementById("name").style.display="none";
		if(pw1!=null&&pw1!=""){
			document.getElementById("pass").style.display="none";
			if(pw2!=null&&pw2!=""){
				if(identifyCode!=null&&identifyCode!=""){
					document.getElementById("identifyCode1").style.display="none";
					$.getJSON("/user",{action:"reg",identifyCode:identifyCode,username:username,password1:pw1,date:new Date()},function(data){
						if(data.message=="false"){
							document.getElementById("identifyCode2").style.display="";
						}
						if(data.flag=="true"){
							document.getElementById("identifyCode2").style.display="none";
							location.href ="/reg_2.jsp";
						}
					});
				}else{
					document.getElementById("identifyCode1").style.display="";
				}
			}
		}else{
			document.getElementById("pass").style.display="";
			document.getElementById("passWd").style.display="none";
		}
	}else{
		document.getElementById("name").style.display="";
	}
	
}
