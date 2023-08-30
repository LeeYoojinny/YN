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
		<c:choose>
			<c:when test="${sessionScope.user_id eq null}">
			    <li><a href="index.jsp">Home</a></li>
			    <li><a href="#">Cars</a></li>
			    <li><a href="#">Board</a>
				    <ul id="inside_menu">
				     	<li><a href="#">문의게시판</a></li>
				     	<li><a href="#">리뷰게시판</a></li>
				    </ul>
				</li>
			    <li><a href="#">Contact</a>
			    	<ul id="inside_menu">
			        	<li><a href="#">공지사항</a></li>
			    	</ul>	
			    </li>
			    <li><a href="<%=request.getContextPath()%>/userLogin.usr">LogIn</a></li>
				<li><a href="<%=request.getContextPath()%>/userJoin.usr">Join</a></li>
			</c:when>
			<c:when test="${sessionScope.user_category eq 'customer'}">
				<li><a href="index.jsp">Home</a></li>
			    <li><a href="#">Cars</a></li>
			    <li><a href="#">Board</a>
				    <ul id="inside_menu">
				     	<li><a href="#">문의게시판</a></li>
				     	<li><a href="#">리뷰게시판</a></li>
				    </ul>
				</li>
			    <li><a href="#">Contact</a>
			    	<ul id="inside_menu">
			        	<li><a href="#">공지사항</a></li>
			    	</ul>	
			    </li>
			    ${sessionScope.user_name} 환영합니다<br>
				<li><a href="<%=request.getContextPath()%>/userMyInfo.usr">My Info</a>
				 	<ul id="inside_menu">
				     	<li><a href="#">나의예약내역</a></li>
				     	<li><a href="#">나의주문내역</a></li>
				     	<li><a href="#">회원정보수정</a></li>
				    </ul>
				
				</li>
				<li><a href="<%=request.getContextPath()%>/userLogout.usr">LogOut</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="index.jsp">Home</a></li>
			    <li><a href="#">Cars</a>
			    	<ul id="inside_menu">
				     	<li><a href="#">상품보기/등록</a></li>
				    </ul>		    
			    </li>
			    <li><a href="#">Status</a>
			    	<ul id="inside_menu">
				     	<li><a href="#">예약현황</a></li>
				     	<li><a href="#">주문현황</a></li>
				    </ul>
			    </li>
			    <li><a href="#">Board</a>
			    	<ul id="inside_menu">
				     	<li><a href="#">공지사항</a></li>
				     	<li><a href="#">문의게시판관리</a></li>
				     	<li><a href="#">리뷰게시판관리</a></li>
				    </ul>		    
			    </li>
			    ${sessionScope.user_name}(${sessionScope.user_category})님 환영합니다<br>
				<li><a href="<%=request.getContextPath()%>/adminMyInfo.adm">My Info</a>
					<ul id="inside_menu">
				     	<li><a href="#">회원정보수정</a></li>
				    </ul>
				</li>
				<li><a href="<%=request.getContextPath()%>/userLogout.usr">LogOut</a></li>
			</c:otherwise>
			</c:choose>
	  	</ul>
	</nav>
</body>
</html>