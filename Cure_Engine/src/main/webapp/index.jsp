<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cure Engine</title>
	<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css'>
	<link rel="stylesheet" href="css/index_style.css">
</head>
<body>
<div class="video-container">
    <video autoplay muted loop id="video-bg">
      <source src="videos/Network1.mp4" type="video/mp4">
      <!-- Your browser does not support the video tag. -->
    </video>
  </div>
<nav>
	<ul class="gotoMain">
		<li><a href="index.jsp">Home</a></li>
	</ul>
	<ul class="shoppingMenu">
	<c:choose>
		<%-- 로그인 하기 전 --%>
		<c:when test="${sessionScope.user_id eq null}">
		    <li><a href="allCarListView.usr">Cars</a></li>
		    <li><a href="#">Board</a>
			    <ul id="inside_menu">
			     	<li><a href="qnaView.usr">문의게시판</a></li>
			     	<li><a href="reviewView.usr">리뷰게시판</a></li>
			    </ul>
			</li>
		    <li><a href="#">Contact</a>
		    	<ul id="inside_menu">
		        	<li><a href="noticeView.usr">공지사항</a></li>
		    	</ul>	
		    </li> 
		</c:when>
		<%-- 고객이 로그인을 했을 떄 --%>
		<c:when test="${sessionScope.user_category eq 'customer'}">
		    <li><a href="allCarListView.usr">Cars</a></li>
		    <li><a href="#">Board</a>
			    <ul id="inside_menu1">
			     	<li><a href="qnaView.cust">문의게시판</a></li>
			     	<li><a href="reviewView.cust">리뷰게시판</a></li>
			    </ul>
			</li>
		    <li><a href="#">Contact</a>
		    	<ul id="inside_menu1">
		        	<li><a href="noticeView.usr">공지사항</a></li>
		    	</ul>	
		    </li>
		</c:when>
		<%-- 딜러 혹은 관리자가 로그인 했을 떄 --%>
		<c:otherwise>
		    <li><a href="allCarListView.usr">Cars</a>
		    	<ul id="inside_menu2">
			     	<li><a href="carRegistForm.adm">상품등록</a></li>
			     	<li><a href="mySaleCar.adm">나의판매차량</a></li>
			    </ul>		    
		    </li>
		    <li><a href="#">Status</a>
		    	<ul id="inside_menu1">
			     	<li><a href="reservationView.adm">예약현황</a></li>
			     	<li><a href="orderView.adm">주문현황</a></li>
			    </ul>
		    </li>
		    <li><a href="#">Board</a>
		    	<ul id="inside_menu2">
			     	<li><a href="noticeView.adm">공지사항</a></li>
			     	<li><a href="qnaManage.adm">문의게시판관리</a></li>
			     	<li><a href="reviewManage.adm">리뷰게시판관리</a></li>
			    </ul>		    
		    </li>    
		</c:otherwise>
		</c:choose>
  	</ul>
  	<ul class="myInfoMenu">
	  	<c:choose>
			<c:when test="${sessionScope.user_id eq null}">
	  			<li><a href="<%=request.getContextPath()%>/userLogin.usr">LogIn</a></li>
				<li><a href="<%=request.getContextPath()%>/userJoin.usr">Join</a></li>
	  		</c:when>
	  		<c:when test="${sessionScope.user_category eq 'customer'}">
	  			<%-- <div class="welcome">${sessionScope.user_name}님 환영합니다</div> --%>
	  			<li class="wish">
		  			<a href="<%=request.getContextPath()%>/myWishList.cust">
		  			<img src="image/customer/icon_wishlist.png" alt="관심상품" id="wishIcon">
		  			</a>
	  			</li>
				<li><a href="#">My Info</a>
				 	<ul id="inside_menu2">
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
				 	<ul id="inside_menu2">
				     	<li><a href="#">딜러정보수정</a></li>
				    </ul>
				</li>
				<li><a href="<%=request.getContextPath()%>/userLogout.usr">LogOut</a></li>
			</c:when>
			<c:otherwise>
				<%-- <div class="welcome">${sessionScope.user_name}(${sessionScope.user_category})님 환영합니다</div>> --%>
				<li><a href="#">My Info</a>
					<ul id="inside_menu2">
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

<main>
  <div>
    <span>국내 1위 수입중고차 쇼핑몰</span>
    <h1>Cure Engine</h1>
    <hr>
    <p>다양한 브랜드와 신뢰할 수 있는 판매자 중에서 귀하에게 딱 맞는 중고차를 찾아보세요. 등록된 다양한 모델과 가격을 살펴보세요.</p>
    <a href="allCarListView.usr">자동차 찾아보기</a>
  </div>
  <div class="swiper">
  
    <div class="swiper-wrapper">
    
      <div class="swiper-slide swiper-slide--one">
        <div>
          <h2>Mercedes-Benz</h2>
          <p>메르세데스-벤츠는 프리미엄 차량, 혁신적인 기술, 우아한 디자인으로 유명한 글로벌 럭셔리 자동차 브랜드입니다.</p>
		  <a href="#">알아보기</a> 
        </div>
      </div>
      
      <div class="swiper-slide swiper-slide--two">
        <div>
          <h2>Tesla</h2>
          <p>Tasla, Inc.는 전기 자동차, 베터리 저장 솔루션 및 재생 에너지 제품으로 유명한 전기 자동차 및 청정 에너지 회사입니다.</p>
		  <a href="#">알아보기</a>
        </div>
      </div>

      <div class="swiper-slide swiper-slide--three">
        <div>
          <h2>Ferrari</h2>
          <p>페라리는 날렵한 디자인과 공기역학적 차체 디자인럭셔리 스포츠카, 강력한 엔진으로 잘 알려져 있습니다, 모든 면에서 세련미와 우아함을 발산합니다.</p>
          <a href="#">알아보기</a>
        </div>
      </div>

      <div class="swiper-slide swiper-slide--four">
        <div>
          <h2>BMW</h2>
          <p>BMW는 고성능 차량, 럭셔리 기능, 스포티한 미학으로 유명한 선도적인 럭셔리 자동차 제조업체입니다.</p>
          <a href="#">알아보기</a>
        </div>
      </div>

      <div class="swiper-slide swiper-slide--five">
        <div>
          <h2>Audi</h2>
          <p>아우디는 세련된 디자인, 앞선 기술, 프리미엄 드라이빙 경험으로 인정받는 독일의 자동차 브랜드입니다.</p>
          <a href="#">알아보기</a>
        </div>
      </div>
      
      <div class="swiper-slide swiper-slide--Six">
        <div>
          <h2>Maserati</h2>
          <p>마세라티는 우아한 디자인, 탁월한 주행 성능, 고급스러운 인테리어 및 최신 기술을 갖추고 있습니다. 이탈리아 역사적인 가치를 대표하는 브랜드 중 하나 입니다.</p>
          <a href="#">알아보기</a>
        </div>
      </div>
      
      <div class="swiper-slide swiper-slide--Seven">
        <div>
          <h2>Bentley</h2>
          <p>벤틀리는 고급 승용차 브랜드로 우아한 디자인과 탁월한 주행성능, 강력한 엔진을 자랑하는 영국의 자동차 브랜드입니다.</p>
          <a href="#">알아보기</a>
        </div>
      </div>
      
      <div class="swiper-slide swiper-slide--Eight">
        <div>
          <h2>Cadillac</h2>
          <p>캐딜락은 변속 기어에 뛰어난 성능과 기술력을 선보이며, 웅장한 모습과 고급스러운 세련된 미국의 자동차브랜드이다.</p>
          <a href="#">알아보기</a>
        </div>
      </div>
      
    </div>
    
    <!-- Add Pagination -->
    <div class="swiper-pagination"></div>
  </div>
  <img src="https://cdn.example.com/car-image1.png" alt="" class="bg">
  <img src="https://cdn.example.com/car-image2.png" alt="" class="bg2">
</main>
<footer>
  <div>
    <p>&copy; 2023 Cure Engine. All rights reserved.</p>
    <ul>
		<li><a href="#">개인 정보 정책</a></li>
		<li><a href="#">서비스 약관</a></li>
	    <li><a href="#">문의하기</a></li>
    </ul>
  </div>
</footer>
<script src='https://cdnjs.cloudflare.com/ajax/libs/Swiper/8.4.5/swiper-bundle.min.js'></script>
<script src="js/index_script.js"></script>
</body>
</html>