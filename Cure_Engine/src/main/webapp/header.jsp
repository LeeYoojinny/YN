<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <!-- Font Awesome 아이콘 라이브러리 추가 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" 
	integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" 
	crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- 추가된 부트스트랩 CSS -->
    <link rel="stylesheet" href="css/header_style.css">
    <title>Cure Engine</title>
</head>
<body>
	<div class="loading-spinner">
		<img src="image/loading-icon.gif" width="50px">	
	</div>
	
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
    	<a class="navbar-brand" href="index.jsp">Cure Engine</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
	        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	    </button>
		    <div class="collapse navbar-collapse" id="navbarNav">
		        <ul class="navbar-nav">
		            <li class="nav-item">
		                <a class="nav-link" href="index.jsp"><i class="fas fa-home"></i> Home</a>
		            </li>
					<c:choose>
					<%-- 로그인 하기 전 --%>
					<c:when test="${sessionScope.user_id eq null}">
				    	<li class="nav-item">
		                   <a class="nav-link" href="allCarListView.usr">Cars</a>
		                </li>
					    <li class="nav-item dropdown">
	                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
	                            data-bs-toggle="dropdown" aria-expanded="false">
	                            Board
	                        </a>
	                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	                            <li><a class="dropdown-item" href="qna_boardList.bo">문의게시판</a></li>
	                            <li><a class="dropdown-item" href="review_boardList.bo">리뷰게시판</a></li>
	                        </ul>
	                    </li>
					    <li class="nav-item dropdown">
	                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
	                            data-bs-toggle="dropdown" aria-expanded="false">
	                            Contact
	                        </a>
	                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	                            <li><a class="dropdown-item" href="notice_boardList.bo">공지사항</a></li>
	                        </ul>
	                    </li>
	                </c:when>  
					<%-- 고객이 로그인을 했을 떄 --%>
					<c:when test="${sessionScope.user_category eq 'customer'}">
				    	<li class="nav-item">
	                        <a class="nav-link" href="allCarListView.usr">Cars</a>
	                    </li>
	                    <li class="nav-item dropdown">
	                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
	                            data-bs-toggle="dropdown" aria-expanded="false">
	                            Board
	                        </a>
	                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	                            <li><a class="dropdown-item" href="qna_boardList.bo">문의게시판</a></li>
	                            <li><a class="dropdown-item" href="review_boardList.bo">리뷰게시판</a></li>
	                        </ul>
	                    </li>
	                    <li class="nav-item dropdown">
	                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
	                            data-bs-toggle="dropdown" aria-expanded="false">
	                            Contact
	                        </a>
	                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	                            <li><a class="dropdown-item" href="notice_boardList.bo">공지사항</a></li>
	                        </ul>
	                    </li>
	                </c:when>
					<%-- 딜러가 로그인 했을 때 --%>
					<c:when test="${sessionScope.user_category eq 'dealer'}">
	                    <li class="nav-item dropdown">
	                        <a class="nav-link dropdown-toggle" href="allCarListView.usr" id="navbarDropdownMenuLink" role="button"
	                            data-bs-toggle="dropdown" aria-expanded="false">
	                            Cars
	                        </a>
	                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	                            <li><a class="dropdown-item" href="carRegistForm.adm">상품등록</a></li>
	                            <li><a class="dropdown-item" href="mySaleCar.adm">나의판매차량</a></li>
	                        </ul>
	                    </li>
	                    <li class="nav-item dropdown">
	                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
	                            data-bs-toggle="dropdown" aria-expanded="false">
	                            Status
	                        </a>
	                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	                            <li><a class="dropdown-item" href="reservationView.adm">예약현황</a></li>
	                            <li><a class="dropdown-item" href="orderListView.adm">주문현황</a></li>
	                        </ul>
	                    </li>
	                    <li class="nav-item dropdown">
	                         <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
	                            data-bs-toggle="dropdown" aria-expanded="false">
	                            Board
	                        </a>
	                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	                            <li><a class="dropdown-item" href="notice_boardList.bo">공지사항</a></li>
	                            <li><a class="dropdown-item" href="qna_boardList.bo">문의게시판관리</a></li>
	                            <li><a class="dropdown-item" href="review_boardList.bo">리뷰게시판관리</a></li>
	                        </ul>		    
	                    </li>    
	                </c:when>
					<%-- 관리자가 로그인 했을 떄 --%>
					<c:otherwise>
	                    <li class="nav-item dropdown">
	                        <a class="nav-link dropdown-toggle" href="allCarListView.usr" id="navbarDropdownMenuLink" role="button"
	                            data-bs-toggle="dropdown" aria-expanded="false">
	                            Cars
	                        </a>
	                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	                            <li><a class="dropdown-item" href="carRegistForm.adm">상품등록</a></li>
	                            <li><a class="dropdown-item" href="allSaleCar.adm">전체상품관리</a></li>
	                        </ul>		    
	                    </li>
	                    <li class="nav-item dropdown">
	                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
	                            data-bs-toggle="dropdown" aria-expanded="false">
	                            Status
	                        </a>
	                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	                            <li><a class="dropdown-item" href="reservationView.adm">예약현황</a></li>
	                            <li><a class="dropdown-item" href="orderListView.adm">주문현황</a></li>
	                        </ul>
	                    </li>
	                    <li class="nav-item dropdown">
	                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
	                            data-bs-toggle="dropdown" aria-expanded="false">
	                            Board
	                        </a>
	                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	                            <li><a class="dropdown-item" href="notice_boardList.bo">공지사항</a></li>
	                            <li><a class="dropdown-item" href="qna_boardList.bo">문의게시판관리</a></li>
	                            <li><a class="dropdown-item" href="review_boardList.bo">리뷰게시판관리</a></li>
	                        </ul>		    
	                    </li>    
	                </c:otherwise>
	            </c:choose>
		  	</ul>
		  	<ul class="navbar-nav ms-auto">
	            <c:choose>
	                <c:when test="${sessionScope.user_id eq null}">
		  				<li><a href="userLogin.usr">LogIn</a></li>
						<li><a href="userJoinChoice.usr">Join</a></li>
		  			</c:when>
			  		<c:when test="${sessionScope.user_category eq 'customer'}">
	                    <li class="wish"><a href="myWishlist.cust">Wishlist</a></li>                   
	                    <li class="nav-item">
	                        <a class="nav-link" href="#">My Info</a>
	                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	                            <li><a class="dropdown-item" href="myReservation.cust">나의예약내역</a></li>
	                            <li><a class="dropdown-item" href="myOrderList.cust">나의주문내역</a></li>
	                            <li><a class="dropdown-item" href="myInfoView.cust">회원정보수정</a></li>
	                        </ul>
	                    </li>
	                    <li class="nav-item">
	                        <a class="nav-link" href="userLogout.usr">LogOut</a>
	                    </li>
	                </c:when>
					<c:when test="${sessionScope.user_category eq 'dealer'}">	                    
	                    <li class="nav-item">
	                        <a class="nav-link" href="#">My Info</a>
	                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	                            <li><a class="dropdown-item" href="dealerInfoView.adm">딜러정보수정</a></li>
	                        </ul>
	                    </li>
	                    <li class="nav-item">
	                        <a class="nav-link" href="userLogout.usr">LogOut</a>
	                    </li>
	                </c:when>
					<c:otherwise>					
						<li class="nav-item">
		                   <a class="nav-link" href="#">Manage</a>
		                     <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	                         	<li><a class="dropdown-item" href="customerList.adm">회원관리</a></li>
	                         	<li><a class="dropdown-item" href="dealerApproveList.adm">딜러등록</a></li>
	                         	<li><a class="dropdown-item" href="dealerList.adm">딜러관리</a></li>
						  	 	<li><a class="dropdown-item" href="pwChangeForm.usr?user_id=${user_id}">비밀번호변경</a></li>
		                     </ul>
		                 </li>
		                 <li class="nav-item">
		                     <a class="nav-link" href="userLogout.usr">LogOut</a>
		                 </li>
					</c:otherwise>
				</c:choose>
	  		</ul>
	    </div>
	</nav>


    <!-- 네비바 -->    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Bootstrap JS 및 Popper.js 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"></script>
    

     <!-- 스피너 -->
	<script src="js/spinner.js"></script>
</body>
</html>