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
		if(!f.review_content.value.trim()){
			alert("내용을 입력해주세요.");
			f.review_content.focus();
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
		<h2 class="mt-4">구매리뷰 작성</h2>
		<form action="review_BoardWriteAction.bo" method="post" name="f" enctype="multipart/form-data">
		<input type="hidden" name="car_id" value="${param.car_id}">
		<input type="hidden" name="ordernum" value="${param.ordernum}">		
			<div class="mb-3 mt-3">
				<label class="form-label">작성자</label>
				<input class="form-control" type="text" name="user_id" value="${user_id}" readonly>
			</div>
			<div class="mb-3">
				<label class="form-label">제목</label>
				<input class="form-control" type="text" name="review_title" value="${param.car_id} 구매리뷰" readonly>				
			</div>		
			<div class="mb-3">
				<label class="form-label">내용</label>
				<textarea class="form-control" style="resize:none;" cols="40" rows="20" name="review_content" 
				placeholder="====== 구매해주신 차량에 대한 솔직한 평을 남겨주시길 바랍니다. (악의적인 비방은 통보없이 삭제 될 수 있습니다.)====="></textarea>
			</div>
			<div class="mb-3">
				<label class="form-label">이미지 파일첨부(최대 3개)</label>
				<input class="form-control" type="file" name="review_file1" id="review_file"  accept="image/*">
				<input class="form-control" type="file" name="review_file2" id="review_file"  accept="image/*">
				<input class="form-control" type="file" name="review_file3" id="review_file"  accept="image/*">
			</div>
			<div id="command">
				<input class="btn btn-outline-secondary" type="submit" value="등록" onclick="check(); return false;"/>&nbsp;&nbsp;
				<input class="btn btn-outline-secondary" type="reset" value="다시쓰기">
			</div>
		</form>
	</div>
</body>
</html>