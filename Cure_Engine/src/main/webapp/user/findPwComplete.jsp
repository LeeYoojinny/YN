<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 찾기</title>
<link rel="stylesheet" href="css/user/user_findPwComplete_style.css">
</head>
<script type="text/javascript">
	function gotologin() {
		window.location.href="userLogin.usr";		
	}
</script>
<body>
<div class="wrap_findPw">
	<div class="field">
    	<div class="field show">
			'${input_email}'으로 임시비밀번호가 발행되었습니다.<br>
			로그인 후 비밀번호 수정 해주시길 바랍니다.			
		</div>
		<div class="field button">
			<button onclick="gotologin(); return false;">로그인</button>
			<button onclick="location.href='index.jsp'; return false;">메인으로</button>
		</div>
	</div>	
</div>		
</body>
</html>