<%@page import="vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>글목록 보기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" href="css/user/qna/boardList_style.css">
<!-- <script src="js/jquery-3.1.1.js"></script>
<script src="js/bootstrap.js"></script> -->
</head>
<script type="text/javascript">
	function loginCheck(){
		var user_id = '<c:out value="${user_id}" />';
		
		if(!user_id) {
			alert("로그인 후 문의 가능합니다.");
			location.href="userLogin.usr";
			return false;
		}else {
			location.href="qna_boardWrite.bo";			
		}	
	}
</script>

<body class="sb-nav-fixed">
<h2>문의게시판</h2>
<div class="container">
	<div class="card-body">
		<table class="table table-striped table-hover text-center">
			<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			</thead>
			<tbody>
				<c:if test="${boardList ne null}">
					<c:forEach var="board" items="${boardList}">
			            <tr>
			                <td>${board.qna_num}</td>
			                <td>${board.user_id}</td>
			                <td id="subject">
			                	<c:if test="${!fn:contains(board.qna_title, 'q')}">
									<a href="qna_boardView.bo?qna_num=${board.qna_num}">${board.qna_title}</a>
								</c:if>			
								<c:if test="${fn:contains(board.qna_title, 'q')}">
									<c:forEach var="code" items="${allCode}">
										<c:if test="${code.code_category == 'qna_subject'}">
											<c:if test="${board.qna_title == code.code_name}">
											<a href="qna_boardView.bo?qna_num=${board.qna_num}">${code.code_value}</a>
											</c:if>
										</c:if>
									</c:forEach>		
								</c:if>	
			                </td>
			                <td>${board.qna_date}</td>
			                <td>${board.qna_hit}</td>
			            </tr>
			        </c:forEach>
			    </c:if>
				<c:if test="${empty boardList}">
		            <tr>
		                <td colspan="6">게시글이 없습니다.</td>
		            </tr>
		        </c:if>
			</tbody>
		</table>
		
		<form action="BoardSearchAction.bo" method="post">
			<select name="option">
				<option value="qna_title" selected>제목</option>
				<option value="user_id">작성자</option>
			</select>
			<input type="text" name="keyword" placeholder="검색어를 입력하세요">&nbsp;<input type="submit" value="검색">
		</form>
		
		<c:choose> 
				<c:when test="${pageInfo.page <= 1}">[이전]&nbsp;</c:when>
				<c:otherwise><a style="text-decoration:none" href="boardList.bo?page=${pageInfo.page-1}">[이전]&nbsp;</a></c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1" varStatus="loop">
				<c:choose>
					<c:when test="${i == pageInfo.page}">${i}&nbsp;</c:when>
					<c:otherwise><a style="text-decoration:none" href="boardList.bo?page=${i}">${i}</a>&nbsp;</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pageInfo.page >= pageInfo.maxPage}">[다음]&nbsp;</c:when>
				<c:otherwise><a style="text-decoration:none" href="boardList.bo?page=${pageInfo.page+1}">[다음]&nbsp;</a></c:otherwise>
			</c:choose>
		<c:if test="${user_category eq 'customer' or user_category eq null}">
			<a class="btn btn-default float-end" style="border:1px solid #ccc;" onclick="loginCheck()">글쓰기</a>
		</c:if>
	</div>
</div> 

</body>
</html>