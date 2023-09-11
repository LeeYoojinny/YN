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
</head>
<script type="text/javascript">
	function secretCheck(secretYN,qna_num) {
		if(secretYN == 'Y') {
			location.href = "qna_pwCheck.bo?qna_num="+qna_num;
		}else {
			location.href = "qna_boardUpdate.bo?qna_num="+qna_num;
		}		
	}

</script>

<body>
<div class="container">
	<table class="table table-hover">
		<tr>
			<th>번호</th>
			<td>${board.qna_num}</td>
		</tr>
		<tr>
			<th>작성일자</th>
			<td>${board.qna_date}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.user_id}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>
			<c:if test="${!fn:contains(board.qna_title, 'q')}">
				${board.qna_title}
			</c:if>			
			<c:if test="${fn:contains(board.qna_title, 'q')}">
				<c:forEach var="code" items="${allCode}">
					<c:if test="${code.code_category == 'qna_subject'}">
						<c:if test="${board.qna_title == code.code_name}">
						${code.code_value}
						</c:if>
					</c:if>
				</c:forEach>		
			</c:if>	
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.qna_content}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<img src="upload/qna_file/${board.qna_file}" width="100px">
				<a href="fileDown.bo?file_name=${board.qna_file}">${board.qna_file_origin}</a>
			</td>
		</tr>
	</table>
	<a class="btn btn-outline-secondary" href="qna_boardList.bo">목록</a>
	<c:if test="${board.user_id eq sessionScope.user_id or user_category eq 'admin'}">
		<a class="btn btn-outline-secondary" onclick="secretCheck('${board.secret_YN}','${board.qna_num}')">수정</a>
	</c:if>
	<c:if test="${board.user_id eq sessionScope.user_id or user_category eq 'admin'}">
	<a class="btn btn-outline-secondary" href="qna_boardDelete.bo?qna_num=${board.qna_num}">삭제</a> 
	</c:if>
</div>
</body>
</html>