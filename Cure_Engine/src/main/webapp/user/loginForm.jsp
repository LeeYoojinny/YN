<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cure Engine Login</title>
<link rel="stylesheet" href="css/user/user_loginForm_style.css">
</head>
<body>
	<div class="wrap_login">
	  <form action="userLoginAction.usr" method="post" name="f">
	    	<div class="field">
				<b>아이디</b> <input type="text" name="user_id" id="user_id">
			</div>
			<div class="field">
				<b>비밀번호</b> <input type="password" class="userpw" name="user_pw" >
			</div>
			<div class="field login_submit"><input type="submit" value="로그인" onclick="check(); return false;"></div>   
	  </form>
	  	 <div class="login_another"><a href="userIdFindForm.usr">아이디 찾기</a> | <a href="userPwFindForm.usr">비밀번호 찾기</a> | <a href="userJoin.usr">회원가입</a></div>
	</div>
</body>
</html>