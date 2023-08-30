<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cure Engine Login</title>
<link rel="stylesheet" href="css/user/user_loginForm_style.css">
</head>
<script type="text/javascript">
/* 회원가입 sumit 전 정규화로 입력값 확인 */
function check() {
	//아이디와 비밀번호 값 데이터 정규화 공식
	const regIdPass = /^[a-zA-Z0-9]{8,12}$/;	
	
	//아이디 체크
	if(!document.f.user_id.value.trim()){
		alert("아이디를 입력해주세요.");
		document.f.user_id.focus();
		return false;
	}
	//비밀번호 체크
	if(!document.f.user_pw.value.trim()){
		alert("비밀번호를 입력해주세요.");
		document.f.user_pw.focus();
		return false;
	}
	
	document.f.submit();
}
</script>
<body>
	<div class="wrap_login">
	<div class="subject">User Login</div>
	  <form action="userLoginAction.usr" method="post" name="f" id="loginForm">
	    	<div class="field">
				<b>아이디</b> <input type="text" name="user_id" id="user_id">
			</div>
			<div class="field">
				<b>비밀번호</b> <input type="password" class="userpw" name="user_pw" >
			</div>
			<div class="field login_submit"><input type="submit" value="로그인" onclick="check(); return false;"></div>   
	  		<!-- <div class="field login_idSave">
	  			<input type="checkbox" name="login_idSave" value="save"><label for="login_idSave">아이디기억하기</label>
	  		</div> -->
	  </form>
	  	 <div class="login_another"><a href="userFindIdForm.usr">아이디 찾기</a> | <a href="userFindPwForm.usr">비밀번호 찾기</a> | <a href="userJoin.usr">회원가입</a></div>
	</div>
</body>
</html>