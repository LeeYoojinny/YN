<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 찾기</title>
<link rel="stylesheet" href="css/user/user_findIdComplete_style.css">
</head>
<%
	//아이디 일부분만 보여주기
	String user_id = (String)request.getAttribute("user_id");
	user_id = user_id.substring(0,4)+("*".repeat(user_id.length()-4));

%>

<script type="text/javascript">
	function findPw() {
		window.location.href="userFindPwForm.usr";		
	}

</script>
<body>
<div class="wrap_findId">
	<div class="field">
    	<div class="field show">
			고객님의 ID는 <%=user_id%> 입니다.
		</div>
		<div class="field button">
			<button onclick="findPw(); return false; return false;">비밀번호찾기</button>
			<button onclick="location.href='index.jsp'; return false;">메인으로</button>
		</div>
	</div>	
</div>		
</body>
</html>