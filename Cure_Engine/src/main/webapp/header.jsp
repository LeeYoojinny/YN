<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<title>Cure Engine</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/header_style.css">
	
</head>
<body>
	<div class="loading-spinner">
		<img src="image/loading-icon.gif" width="50px">	
	</div>
	
		
	<nav>
		<ul class="gotoMain" style="margin-bottom: 0;">
			<li><a href="index.jsp" >Home</a></li>
		</ul>
		<ul class="shoppingMenu" style="margin-bottom: 0;">
		<c:choose>
			<%-- 로그인 하기 전 --%>
			<c:when test="${sessionScope.user_id eq null}">
			    <li><a href="allCarListView.usr">Cars</a></li>
			    <li><a href="#">Board</a>
				    <ul id="inside_menu" style="padding-left: 0;">
				     	<li><a href="<%=request.getContextPath()%>/qna_boardList.bo">문의게시판</a></li>
				     	<li><a href="#">리뷰게시판</a></li>
				    </ul>
				</li>
			    <li><a href="#">Contact</a>
			    	<ul id="inside_menu"  style="padding-left: 0;">
			        	<li><a href="<%=request.getContextPath()%>/notice_boardList.bo">공지사항</a></li>
			    	</ul>	
			    </li> 
			</c:when>
			<%-- 고객이 로그인을 했을 떄 --%>
			<c:when test="${sessionScope.user_category eq 'customer'}">
			    <li><a href="allCarListView.usr">Cars</a></li>
			    <li><a href="#">Board</a>
				    <ul id="inside_menu1" style="padding-left: 0;">
				     	<li><a href="<%=request.getContextPath()%>/qna_boardList.bo">문의게시판</a></li>
				     	<li><a href="#">리뷰게시판</a></li>
				    </ul>
				</li>
			    <li><a href="#">Contact</a>
			    	<ul id="inside_menu1" style="padding-left: 0;">
			        	<li><a href="<%=request.getContextPath()%>/notice_boardList.bo">공지사항</a></li>
			    	</ul>	
			    </li>
			</c:when>
			<%-- 딜러가 로그인 했을 떄 --%>
			<c:when test="${sessionScope.user_category eq 'dealer'}">
			    <li><a href="allCarListView.usr">Cars</a>
			    	<ul id="inside_menu2" style="padding-left: 0;">
				     	<li><a href="carRegistForm.adm">상품등록</a></li>
				     	<li><a href="mySaleCar.adm">나의판매차량</a></li>
				    </ul>		    
			    </li>
			    <li><a href="#">Status</a>
			    	<ul id="inside_menu1" style="padding-left: 0;">
				     	<li><a href="reservationView.adm">예약현황</a></li>
				     	<li><a href="orderView.adm">주문현황</a></li>
				    </ul>
			    </li>
			    <li><a href="#">Board</a>
			    	<ul id="inside_menu2" style="padding-left: 0;">
				     	<li><a href="<%=request.getContextPath()%>/notice_boardList.bo">공지사항</a></li>
				     	<li><a href="<%=request.getContextPath()%>/qna_boardList.bo">문의게시판관리</a></li>
				     	<li><a href="reviewManage.adm">리뷰게시판관리</a></li>
				    </ul>		    
			    </li>    
			</c:when>
			<%-- 관리자가 로그인 했을 떄 --%>
			<c:otherwise>
			    <li><a href="allCarListView.usr">Cars</a>
			    	<ul id="inside_menu2" style="padding-left: 0;">
				     	<li><a href="carRegistForm.adm">상품등록</a></li>
				     	<li><a href="allSaleCar.adm">전체상품관리</a></li>
				    </ul>		    
			    </li>
			    <li><a href="#">Status</a>
			    	<ul id="inside_menu1" style="padding-left: 0;">
				     	<li><a href="reservationView.adm">예약현황</a></li>
				     	<li><a href="orderView.adm">주문현황</a></li>
				    </ul>
			    </li>
			    <li><a href="#">Board</a>
			    	<ul id="inside_menu2" style="padding-left: 0;">
				     	<li><a href="<%=request.getContextPath()%>/notice_boardList.bo">공지사항</a></li>
				     	<li><a href="<%=request.getContextPath()%>/qna_boardList.bo">문의게시판관리</a></li>
				     	<li><a href="reviewManage.adm">리뷰게시판관리</a></li>
				    </ul>		    
			    </li>    
			</c:otherwise>
			</c:choose>
	  	</ul>
	  	<ul class="myInfoMenu" style="margin-bottom: 0;">
		  	<c:choose>
				<c:when test="${sessionScope.user_id eq null}">
		  			<li><a href="<%=request.getContextPath()%>/userLogin.usr">LogIn</a></li>
					<li><a href="<%=request.getContextPath()%>/userJoin.usr">Join</a></li>
		  		</c:when>
		  		<c:when test="${sessionScope.user_category eq 'customer'}">
		  			<%-- <div class="welcome">${sessionScope.user_name}님 환영합니다</div> --%>
		  			<li class="wish">
			  			<a href="<%=request.getContextPath()%>/myWishlist.cust">
			  			<img src="image/customer/icon_wishlist.png" alt="관심상품" id="wishIcon">
			  			</a>
		  			</li>
					<li><a href="#">My Info</a>
					 	<ul id="inside_menu2" style="padding-left: 0;">
					     	<li><a href="myReservation.cust">나의예약내역</a></li>
					     	<li><a href="myOrder.cust">나의주문내역</a></li>
					     	<li><a href="myInfoView.cust">회원정보수정</a></li>
					    </ul>
					</li>
					<li><a href="<%=request.getContextPath()%>/userLogout.usr">LogOut</a></li>
				</c:when>
				<c:when test="${sessionScope.user_category eq 'dealer'}">
		  			<%-- <div class="welcome">${sessionScope.user_name}(${sessionScope.user_category})님 환영합니다</div> --%>
					<li><a href="#">My Info</a>
					 	<ul id="inside_menu2" style="padding-left: 0;">
					     	<li><a href="#">딜러정보수정</a></li>
					    </ul>
					</li>
					<li><a href="<%=request.getContextPath()%>/userLogout.usr">LogOut</a></li>
				</c:when>
				<c:otherwise>
					<%-- <div class="welcome">${sessionScope.user_name}(${sessionScope.user_category})님 환영합니다</div>> --%>
					<li><a href="#">My Info</a>
						<ul id="inside_menu2" style="padding-left: 0;">
					     	<li><a href="#">회원관리</a></li>
					     	<li><a href="#">딜러등록</a></li>
					     	<li><a href="#">관리자정보수정</a></li>
					    </ul>
					</li>
					<li><a href="<%=request.getContextPath()%>/userLogout.usr">LogOut</a></li>
				</c:otherwise>
			</c:choose>
	  	</ul>
	</nav>
	<script src="js/spinner.js"></script>
</body>
</html>