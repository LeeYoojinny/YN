<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글보기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" href="css/user/qna/boardView_style.css">
</head>
<script type="text/javascript">
	
	function deleteCheck(notice_num) {
		if(confirm("게시물을 삭제하시겠습니까?")){
			location.href = "notice_boardDelete.bo?notice_num="+notice_num;				
		}else {
			return false;
		}
	}

</script>

<body>
<div class="container">
	<table class="table table-hover">
		<tr>
			<th>번호</th>
			<td>${list_num}</td>
		</tr>
		<tr>
			<th>작성일자</th>
			<td>${board.notice_date}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.user_id}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.notice_title}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.notice_content}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:if test="${board.notice_file ne null }">
				<img src="upload/notice_file/${board.notice_file}" width="100px">
				<a href="fileDown.bo?file_name=${board.notice_file}&display_num=2">${board.notice_file_origin}</a>
				</c:if>
			</td>
		</tr>
	</table>
	<a class="btn btn-outline-secondary" href="notice_boardList.bo">목록</a>
	<c:if test="${user_category eq 'admin'}">
		<a class="btn btn-outline-secondary" href="notice_boardUpdateForm.bo?notice_num=${board.notice_num}">수정</a>
	</c:if>
	<c:if test="${user_category eq 'admin'}">
	<a class="btn btn-outline-secondary" onclick="deleteCheck('${board.notice_num}')">삭제</a> 
	</c:if>
</div>
</body>
</html>