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
<h2>회원 검색 결과</h2>
<div class="container">
	<div class="card-body">
	<div class="topBt">
		<c:if test="${display_num eq 1}">
			<button onclick="location.href='customerList.adm'">회원목록</button>
		</c:if>
		<c:if test="${display_num eq 2}">
			<button onclick="location.href='custExpireList.adm'">회원목록</button>
		</c:if>
	</div>
		<table class="table table-striped table-hover text-center">
			<thead>
			<tr>
				<th>번호</th>
				<th>아이디</th>
				<c:if test="${display_num eq 1}">
					<th>가입일</th>
				</c:if>
				<c:if test="${display_num eq 2}">
					<th>탈퇴일</th>
				</c:if>
			</tr>
			</thead>
			<tbody>
				<c:if test="${searchUser ne null}">					
		            <tr>
		                <td id="item_no">1</td>
		                <td id="item_id">
		                <c:if test="${display_num eq 1}">
		                	<a href="custDetailView.adm?user_id=${searchUser.user_id}&display_num=1">${searchUser.user_id}</a>
		                </c:if>
		                <c:if test="${display_num eq 2}">
		                	<a href="custDetailView.adm?user_id=${searchUser.user_id}&display_num=2">${searchUser.user_id}</a>
		                </c:if>
		                </td>
		                <c:if test="${display_num eq 1}">
		                	<td id="item_date"><fmt:formatDate pattern="yyyy/MM/dd" value="${searchUser.user_joindate}"/></td>			                		                
		            	</c:if>
		                <c:if test="${display_num eq 2}">
		                	<td id="item_date"><fmt:formatDate pattern="yyyy/MM/dd" value="${searchUser.user_expiredate}"/></td>			                		                
		            	</c:if>
		            </tr>			    
			    </c:if>
				<c:if test="${empty searchUser}">
		            <tr>
		                <td colspan="3">검색결과가 없습니다.</td>
		            </tr>
		        </c:if>
			</tbody>
		</table>
		
		<form action="custSearchAction.adm" method="post" name="f">
			<input type="text" name="keyword" placeholder="아이디 검색">
			<c:if test="${display_num eq 1}">
				<input type="hidden" name="display_num" value="1">
			</c:if>
			<c:if test="${display_num eq 2}">
				<input type="hidden" name="display_num" value="2">
			</c:if>
			<input type="submit" onclick="searchCheck()" value="검색">
		</form>
	</div>
</div> 

</body>
</html>