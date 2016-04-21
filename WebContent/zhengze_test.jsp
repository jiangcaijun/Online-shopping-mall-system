<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>测试用——用户注册</title>
<script language="javascript"
	src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script language="javascript" src="js/functions.js"></script>
<script language="javascript">
//校验表单的合法性
function checkForm(){
	var mobile = $("#mobile").val();
	if(mobile.length<11){
		document.getElementById("msg_mobile").innerHTML = "手机号必须为11位";
		return false;
	}
	var username = document.getElementById("username").value;alert(username.trim().length);
	if(username.length<6 || username.length>12){
		document.getElementById("msg_username").innerHTML = "用户名的长度须在6-12位之间";
		return false;
	}
	var passwd = document.getElementById("passwd").value;
	var repasswd = document.getElementById("repasswd").value;
	if(passwd.length<6 || passwd.length>20){
		document.getElementById("msg_passwd").innerHTML = "密码的长度须在6-20位之间";
		return false;
	}
	if(passwd!=repasswd){
		document.getElementById("msg_repasswd").innerHTML = "两次输入的密码不一致";
		return false;
	}
	var astro = document.getElementById("astro").value;
	if(astro==""){
		document.getElementById("msg_astro").innerHTML = "请选择星座，你想保密吗？";
		return false;
	}
	var arrIntrest = document.getElementsByName("intrest");
	var checkedNums = 0; //复选框勾选的个数
	var checkedIntrest = ""; //一个字符串，保存已勾选的爱好
	for(var i=0; i<arrIntrest.length; i++){
		if(arrIntrest[i].checked) {
			checkedNums++;
			checkedIntrest += arrIntrest[i].value + ",";
		}
	}
	checkedIntrest = checkedIntrest.substring(0, checkedIntrest.length-1);
	if(checkedNums==0) {
		document.getElementById("msg_intrest").innerHTML = "请选择至少一个爱好";
		return false;
	}
	document.getElementById("btnReg").disabled = "disabled";
	document.form1.submit();
	return true;
}
//清空所有提示信息
function resetMsg(){
	
	
}
//检测是否为数字
function checkNumber(){
	var money = document.getElementById("money").value;
	if(!isNaN(money)) {//如果对方是数字和小数点的组合，返回false，其余为true
		alert("你输入的是数字");
	} else {
		alert("请输入数字和小数点的组合");
	}
}

//正则检测手机号
function checkMobile(){
	var mobile2 = document.getElementById("mobile2").value;
	var pattern = /^\d{11}$/; 
	//alert(pattern.exec(mobile2)[0]);
	if(!pattern.test(mobile2)) {
		alert("请输入正确的手机号");
	}
}

//正则检测Email
function checkEmail(){
	var email2 = document.getElementById("email2").value;
	var pattern = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/; //邮箱的正则表达式  
	///^[a-zA-Z]\w{6,15}$/ig;  用户名的正则：由字母a～z(不区分大小写)、数字0～9、点、减号或下划线组成，长度为6～15个字符
	if(!pattern.test(email2)) {
		alert("请输入正确的Email");
	}
}
</script>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<style>
.msgError {
	color: red;
	margin-left: 10px;
}
</style>
</head>

<body>
	<form method="get" action="reg.jsp" name="form1">
		<table>
			<tr>
				<td colspan="2"><h1>用户注册</h1></td>
			</tr>
			<tr>
				<td>手机号：</td>
				<td><input type="text" name="mobile" id="mobile" size="20"
					maxlength="11" value="13696586986" /><span id="msg_mobile"
					class="msgError"></span></td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><input name="username" type="text" id="username"
					value="davidgyg" size="20" maxlength="20" /> <input type="button"
					value="检测用户名" /><span id="msg_username" class="msgError"></span></td>
			</tr>
			<tr>
				<td>Email：</td>
				<td><input name="email" type="text" id="email"
					value="davidgyg@163.com" size="20" maxlength="20" /></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input name="passwd" type="password" id="passwd"
					value="111111" size="20" maxlength="20" /><span id="msg_passwd"
					class="msgError"></span></td>
			</tr>
			<tr>
				<td>再次输入密码：</td>
				<td><input name="repasswd" type="password" id="repasswd"
					value="111111" size="20" maxlength="20" /><span id="msg_repasswd"
					class="msgError"></span></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td><input type="radio" name="sex" value="布拉德皮特"
					checked="checked" />男<input type="radio" name="sex" value="安吉丽娜朱莉" />
					女 <span id="msg_sex" class="msgError"></span></td>
			</tr>
			<tr>
				<td>爱好：</td>
				<td><input type="checkbox" name="intrest" value="音乐"
					id="intrest" /> 音乐 <input type="checkbox" name="intrest"
					value="滑雪" /> 滑雪 <input type="checkbox" name="intrest" value="打游戏" />
					打游戏 <span id="msg_intrest" class="msgError"></span></td>
			</tr>
			<tr>
				<td>星座：</td>
				<td><select name="astro" id="astro">
						<option value="">--请选择星座--</option>
						<option value="白羊座">白羊座</option>
						<option value="双鱼座" selected="selected">白羊座</option>
						<option value="处女座">处女座</option>
				</select><span id="msg_astro" class="msgError"></span></td>
			</tr>
			<tr>
				<td>上传文件：</td>
				<td><input type="file" /></td>
			</tr>
			<tr>
				<td valign="top">个人签名：</td>
				<td><textarea name="astro" rows="3" cols="70">这个家伙很懒，什么都没写</textarea></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" name="btnReg" id="btnReg"
					value="OK，提交注册" onclick="return checkForm()" /> <input type="reset"
					name="reset" value="重置" /></td>
			</tr>
		</table>
		<br /> <br />
	</form>
	<p>&nbsp;</p>
	<p>
		<input type="text" name="money" id="money" /> <input type="button"
			name="button" id="button" value="检测输入是否为数字" onclick="checkNumber()" />
	</p>
	<p>
		<input type="text" name="mobile2" id="mobile2" /> <input
			type="button" name="button2" id="button2" value="正则校验手机号"
			onclick="checkMobile()" />
	</p>
	<p>
		<input type="text" name="email2" id="email2" /> <input type="button"
			name="button2" id="button2" value="正则校验Email" onclick="checkEmail()" />
	</p>
	<p>&nbsp;</p>
	<p>作业：</p>
	<p>1、弹出窗，替换JS默认的alert 、confirm、prompt函数，推荐layui.com</p>
	<p>2、富文本编辑器 ，推荐百度UEDITOR</p>
	<p>3、超级管理员后台框架</p>
</body>
</html>
