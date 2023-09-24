<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 비밀번호 확인</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user/qna/pwCheck_style.css">
</head>
<script type="text/javascript">
	function pwCheck(){
		if(!document.f.input_pw.value.trim()){
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		document.f.submit();
	}
</script>
<body>
<form action="qna_pwCheck.bo" method="post" name="f">
<input type="hidden" name="qna_num" value="${param.qna_num}">
<input type="hidden" name="display_num" value="${param.display_num}">
	<div class="field">
	    <div class="field subject">
	        <span>게시글 비밀번호 확인</span>
	    </div>
	    <div class="field show">
	        <input type="password" name="input_pw">
	    </div>
	    <div class="field show">
	        <button onclick="history.back();return false;">뒤로</button>
	        <button onclick="pwCheck(); return false;">확인</button>
	    </div>   
	</div>
</form>
</body>
</html>