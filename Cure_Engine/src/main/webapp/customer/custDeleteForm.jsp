<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cure Engine 회원탈퇴</title>
<link rel="stylesheet" href="css/user/user_loginForm_style.css">
</head>
<script type="text/javascript">
	function check() {
		if(confirm("탈퇴하시겠습니까?")) {
			document.f.submit;
		}else {
			return false;
		}
	}

</script>
<body>
	<div class="wrap_login">
		<div class="subject">User Login</div>
		  <form action="customerDelete.cust" method="post" name="f" id="loginForm">
		    	<div class="field">
					<b>아이디</b> <input type="text" name="user_id" id="user_id" value="${user_id}" readonly="readonly">
				</div>
				<div class="field">
					<b>비밀번호</b> <input type="password" class="userpw" name="user_pw" required="required">
				</div>
				<div class="field login_submit"><input type="submit" value="탈퇴하기" onclick="check(); return false;"></div>
		  </form>
	  </div>
</body>
</html>