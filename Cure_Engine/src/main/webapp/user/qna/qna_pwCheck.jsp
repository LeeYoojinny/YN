<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 비밀번호 확인</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user/qna/pwCheck_style.css">
</head>
<body>
	<div class="field">
    <div class="field subject">
        <span>게시글 비밀번호 확인</span>
    </div>
    <div class="field show">
        <input type="password" name="input_pw">
    </div>
    <div class="field show">
        <button>확인</button>
    </div>
</div>
</body>
</html>