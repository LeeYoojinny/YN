<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" href="css/user/qna/boardWrite_style.css">
<title>글쓰기</title>
<script>
	function check(){
		var secretY = document.getElementById("secret_Y");
		
		if(!f.user_id.value.trim()){
			alert("이름을 입력해주세요.");
			f.user_id.focus();
			return false;
		}
		if(!f.qna_title.value.trim()){
			alert("제목을 입력해주세요.");
			f.qna_title.focus();
			return false;
		}
		if(!f.qna_content.value.trim()){
			alert("내용을 입력해주세요.");
			f.qna_content.focus();
			return false;
		}
		f.submit();
	}
</script>
<style>
	#command{
		margin:0 auto;
		text-align:center;
	}
</style>
</head>
<body>
	<div class="container">
		<h2 class="mt-4">공지사항 작성</h2>
		<form action="notice_BoardWriteAction.bo" method="post" name="f" enctype="multipart/form-data">
			<div class="mb-3 mt-3">
				<label class="form-label">작성자</label>
				<input class="form-control" type="text" name="user_id" value="${user_id}" readonly>
			</div>
			<div class="mb-3">
				<label class="form-label">제목</label>
				<input class="form-control" type="text" name="notice_title">				
			</div>		
			<div class="mb-3">
				<label class="form-label">내용</label>
				<textarea class="form-control" style="resize:none;" cols="40" rows="20" name="notice_content" ></textarea>
			</div>
			<div class="mb-3">
				<label class="form-label">파일첨부</label>
				<input class="form-control" type="file" name="notice_file" id="notice_file" >
			</div>
			<div id="command">
				<input class="btn btn-outline-secondary" type="submit" value="등록" onclick="check(); return false;"/>&nbsp;&nbsp;
				<input class="btn btn-outline-secondary" type="reset" value="다시쓰기">
			</div>
		</form>
	</div>
</body>
</html>