function refresh(){
	var date = new Date();
	$("#code").attr({ src: "/identifycode?date="+date});
}

function userCheck(){
	var userid = $("#userid").val();
	if(userid!=null&&userid!=""){
		document.getElementById("userId").style.display="none";
	}else{
		document.getElementById("userId").style.display="";
	}
}

function codeCheck(){
	var code = $("#identifyCode").val();
	if(code!=null&&code!=""){
		document.getElementById("identify").style.display="none";
	}else{
		document.getElementById("identify").style.display="";
	}
}