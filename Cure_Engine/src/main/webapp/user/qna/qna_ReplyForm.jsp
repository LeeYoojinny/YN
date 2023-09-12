<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글수정</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" 
integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="css/user/qna/updateForm_style.css">
</head>
<script type="text/javascript">
	function check(){
		/* var secretYN = '<c:out value="${board.secret_YN}" />'; */
		
		if(!f.qna_content.value.trim()){
			alert("내용을 입력해주세요.");
			f.qna_content.focus();
			return false;
		}
	
		f.submit();
	}
</script>
<body>
	<div class="container">
	<h2 class="mt-4">게시글 수정</h2>
		<form action="qna_boardUpdateAction.bo" method="post" name="f" enctype="multipart/form-data">
			<input type="hidden" name="qna_num" value="${board.qna_num}">
			<input type="hidden" name="car_id" value="${board.car_id}">
			<div class="mb-3 mt-3">
				<label class="form-label">작성자</label>
				<c:if test="${user_category == 'dealer'}">
					<input class="form-control" type="text" name="user_id" value="${user_name}딜러" readonly>
				</c:if>
				<c:if test="${user_category == 'admin'}">
					<input class="form-control" type="text" name="user_id" value="관리자" readonly>
				</c:if>
			</div>
			<div class="mb-3">
				<label class="form-label">제목</label>
				<c:if test="${!fn:contains(board.qna_title, 'q')}">
					<i class="fa-brands fa-replyd" style="color: #506d9f;"></i>					
					<input class="form-control" type="text" name="qna_title" 
					value="${board.qna_title}에 대한 답변입니다." readonly>
				</c:if>			
				<c:if test="${fn:contains(board.qna_title, 'q')}">
					<c:forEach var="code" items="${allCode}">
						<c:if test="${code.code_category == 'qna_subject'}">
							<c:if test="${board.qna_title eq code.code_name}">
								<i class="fa-brands fa-replyd" style="color: #506d9f;"></i>
								<input class="form-control" type="text" name="qna_title" 
								value="${code.code_value}에 대한 답변입니다." readonly>
							</c:if>
						</c:if>
					</c:forEach>	
				</c:if>	
			</div>		
			<div class="mb-3">
				<label class="form-label">내용</label>
				<textarea class="form-control" style="resize:none;" cols="40" rows="20" name="qna_content"></textarea>
			</div>
			<div class="mb-3">
				<label class="form-label">파일첨부</label>
				<input class="form-control" type="file" name="qna_file" id="qna_file" >
			</div>
			<div id="command">
				<input class="btn btn-outline-secondary" type="submit" value="등록" onclick="check(); return false;"/>&nbsp;&nbsp;
				<input class="btn btn-outline-secondary" type="reset" value="다시쓰기">
			</div>
		</form>
	</div>
</body>
</html>