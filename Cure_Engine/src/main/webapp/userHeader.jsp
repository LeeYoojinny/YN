<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cure Engine</title>
	<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css'>
	<link rel="stylesheet" href="css/user/user_header_style.css">
</head>
<body>
<nav>
	<ul>
	    <li><a href="index.jsp">Home</a></li>
	    <li><a href="#">Cars</a></li>
	    <li><a href="#">Services</a></li>
	    <li><a href="#">Contact</a></li>
	    <c:choose>
			<c:when test="${sessionScope.u_id eq null}">
				<li><a href="<%=request.getContextPath()%>/userLogin.usr">LogIn</a></li>
				 | 
				<li><a href="<%=request.getContextPath()%>/userJoin.usr">Join</a></li>
			</c:when>
			<c:otherwise>
				${sessionScope.u_name}님 환영합니다<br>
				<li><a href="<%=request.getContextPath()%>/userMyInfo.usr">나의정보</a></li>
				| 
				<li><a href="<%=request.getContextPath()%>/myOrder.kiosk">로그아웃</a></li>
			</c:otherwise>
		</c:choose>
  	</ul>
</nav>
</body>
</html>