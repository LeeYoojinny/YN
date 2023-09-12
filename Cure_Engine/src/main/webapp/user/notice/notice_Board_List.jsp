<%@page import="vo.QnABoard"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.QnABoardDAO"%>
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
<body class="sb-nav-fixed">
<h2>공지사항</h2>
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
				<c:set var="num" value="${pageInfo.listCount}"></c:set>
					<c:forEach var="board" items="${boardList}">
			            <tr>
			                <td>${num}</td>
			                <td>${board.user_id}</td>
			                <td id="subject">
								<a href="notice_boardView.bo?notice_num=${board.notice_num}&list_num=${num}">${board.notice_title}</a>					
			                </td>
			                <td>${board.notice_date}</td>
			                <td>${board.notice_hit}</td>
			            </tr>
			            <c:set var="num" value="${num-1}"></c:set>
			        </c:forEach>
			    </c:if>
				<c:if test="${empty boardList}">
		            <tr>
		                <td colspan="5">게시글이 없습니다.</td>
		            </tr>
		        </c:if>
			</tbody>
		</table>
		
		<form action="noticeSearchAction.bo" method="post">
			<select name="option">
				<option value="notice_title" selected>제목</option>
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
		<c:if test="${user_category eq 'admin'}">
			<a class="btn btn-default float-end" style="border:1px solid #ccc;" href="notice_boardWrite.bo">글쓰기</a>
		</c:if>
	</div>
</div> 

</body>
</html>