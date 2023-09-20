<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
	function delete_Check(review_num) {
		if(confirm("게시물을 삭제하시겠습니까?")){
			location.href = "review_boardDelete.bo?review_num="+review_num;
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
			<td>${param.list_num}</td>
		</tr>
		<tr>
			<th>작성일자</th>
			<td><fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${board.review_date}"/></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.user_id}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>
				${board.review_title}			
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.review_content}</td>
		</tr>
		<c:if test="${fileCount ne 0}">
			<c:if test="${not empty board.review_file1}">
				<tr>	
					<th rowspan="${fileCount}">첨부파일</th>				
					<td>							
						<img src="upload/review_file/${board.review_file1}" style="max-width:300px;">
						<a href="fileDown.bo?file_name=${board.review_file1}&display_num=3">${board.review_file1_origin}</a>
					</td>					
				</tr>
			</c:if>
			<c:if test="${not empty board.review_file2 }">
			<tr>				
				<td>					
					<img src="upload/review_file/${board.review_file2}" style="max-width:300px;">
					<a href="fileDown.bo?file_name=${board.review_file2}&display_num=3">${board.review_file2_origin}</a>					
				</td>				
			</tr>
			</c:if>
			<c:if test="${not empty board.review_file3}">
				<tr>					
					<td>						
						<img src="upload/review_file/${board.review_file3}" style="max-width:300px;">
						<a href="fileDown.bo?file_name=${board.review_file3}&display_num=3">${board.review_file3_origin}</a>						
					</td>					
				</tr>
			</c:if>
		</c:if>	
	</table>
	<a class="btn btn-outline-secondary" href="review_boardList.bo">목록</a>
	<c:if test="${board.user_id eq sessionScope.user_id or user_category eq 'admin'}">
		<a class="btn btn-outline-secondary" onclick="location.href='review_boardUpdateForm.bo?review_num=+${board.review_num}'; return false;">수정</a>
	</c:if>
	<c:if test="${board.user_id eq sessionScope.user_id or user_category eq 'admin'}">
	<a class="btn btn-outline-secondary" onclick="delete_Check('${board.review_num}'); return false;">삭제</a> 
	</c:if>
</div>
</body>
</html>