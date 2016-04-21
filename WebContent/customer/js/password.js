
function checkPw1() {
	var pw1 = document.getElementById("RePassWord1").value;

	if (pw1 != null && pw1 != "") {
		document.getElementById("pw1").style.display = "none";
	} else {
		document.getElementById("pw1").style.display = "";
	}
}

function checkPw2() {
	var pw1 = document.getElementById("RePassWord1").value;
	var pw2 = document.getElementById("RePassWord2").value;
	if (pw1 != null && pw1 != "") {
		if (pw2 != null && pw2 != "") {
			if (pw2 != pw1) {
				document.getElementById("pw2").style.display = "";
			} else {
				document.getElementById("pw2").style.display = "none";
			}
		}
	}
}

function changePw() {
	var oldPw = $("#password").val();
	var pw1 = document.getElementById("RePassWord1").value;
	var pw2 = document.getElementById("RePassWord2").value;
	if(oldPw!=null&&oldPw!=""){
		document.getElementById("oldPw1").style.display = "none";
		$.getJSON("/user",{action:"checkOldPw",oldPw:oldPw,date:new Date()},function(data){
			if(data.flag=="false"){
				document.getElementById("oldPw2").style.display = "";
			}else{
				document.getElementById("oldPw1").style.display = "none";
				document.getElementById("oldPw2").style.display = "none";
			}
		});
		
		if (pw1 != null && pw1 != "") {
			if (pw2 != null && pw2 != "") {
				if (pw2 != pw1) {
					document.getElementById("pw2").style.display = "";
				} else {
					document.getElementById("pw2").style.display = "none";
					$.getJSON("/user", {
						action : "change",
						pw2 : pw2,
						date : new Date()
					}, function(data) {
						alert(data.message);
					});
				}
			} else {
				document.getElementById("pw2").style.display = "";
			}
		} else {
			document.getElementById("pw1").style.display = "";
		}
	}else{
		document.getElementById("oldPw1").style.display = "";
	}
	
}