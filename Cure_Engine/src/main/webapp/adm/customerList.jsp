<%@page import="vo.QnABoard"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.QnABoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>글목록 보기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" 
integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="css/admin/customerList_style.css">
</head>
<script type="text/javascript">
	function searchCheck(){
		if(document.f.keyword.value == ""){
			alert("검색어를 입력해주세요.");
			return false;
		}
		
		document.f.submit();
	}
</script>

<body class="sb-nav-fixed">
<h2>회원관리</h2>
<div class="container">
	<div class="card-body">
		<table class="table table-striped table-hover text-center">
			<thead>
			<tr>
				<td colspan="3">
					<button onclick="location.href='custExpireList.adm'; return false;">탈퇴회원</button>
				</td>
			</tr>
			<tr>
				<th>번호</th>
				<th>아이디</th>
				<th>가입일</th>
			</tr>
			</thead>
			<tbody>
				<c:if test="${customerList ne null}">
				<c:set var="startNo" value="${(pageInfo.page - 1) * 10 + 1}" />
					<c:forEach var="cust" items="${customerList}" varStatus="status">
			            <tr>
			                <td id="item_no">${startNo + status.count-1}</td>
			                <td id="item_id">
			                	<a href="custDetailView.adm?user_id=${cust.user_id}">${cust.user_id}</a>
			                </td>
			                <td id="item_date"><fmt:formatDate pattern="yyyy/MM/dd" value="${cust.user_joindate}"/></td>			                		                
			            </tr>
			        </c:forEach>
			    </c:if>
				<c:if test="${empty customerList}">
		            <tr>
		                <td colspan="3">가입 고객이 없습니다.</td>
		            </tr>
		        </c:if>
			</tbody>
		</table>
		
		<form action="custSearchAction.adm" method="post" name="f">
			<input type="text" name="keyword" placeholder="아이디 검색">
			<input type="submit" onclick="searchCheck()" value="검색">
		</form>
		<div class="page">
			<c:choose> 
				<c:when test="${pageInfo.page <= 1}">[이전]&nbsp;</c:when>
				<c:otherwise><a style="text-decoration:none" href="customerList.adm?page=${pageInfo.page-1}">[이전]&nbsp;</a></c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1" varStatus="loop">
				<c:choose>
					<c:when test="${i == pageInfo.page}">${i}&nbsp;</c:when>
					<c:otherwise><a style="text-decoration:none" href="customerList.adm?page=${i}">${i}</a>&nbsp;</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pageInfo.page >= pageInfo.maxPage}">[다음]&nbsp;</c:when>
				<c:otherwise><a style="text-decoration:none" href="customerList.adm?page=${pageInfo.page+1}">[다음]&nbsp;</a></c:otherwise>
			</c:choose>
		</div>
	</div>
</div> 

</body>
</html>