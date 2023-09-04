<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/user/user_carDetailView_style.css">
</head>
<script type="text/javascript">
	function likeThis(like,car_id,car_price,car_image1) {
		var user_id = '<c:out value="${user_id}" />'
		
		if(!user_id) {
			alert("로그인 후 관심상품 등록이 가능합니다.");
			location.href="userLogin.usr";
			return false;
		}else {
			if(like == 0){
				location.href="userLikeThis.cust?car_id="+car_id+"&car_price="+car_price+"&car_image1="+car_image1;
			}else {
				location.href="userUnlikeThis.cust?car_id="+car_id;
			}
		}
	}

</script>
<body>
<h2>상세보기</h2>
	<div class="wrap_allCarList">
	    <div class="part">
	      <div class="button_img">
	        <div class="images">
	            <img src="upload/carRegist_images/${carDetail.car_image1}">
	            <c:if test="${carDetail.car_image2 ne 'X'}">
	            	<img src="upload/carRegist_images/${carDetail.car_image2}">
				</c:if>
	            <c:if test="${carDetail.car_image3 ne 'X'}">
	            	<img src="upload/carRegist_images/${carDetail.car_image3}">
				</c:if>
	            <c:if test="${carDetail.car_image4 ne 'X'}">
	            	<img src="upload/carRegist_images/${carDetail.car_image4}">
				</c:if>
	            <c:if test="${carDetail.car_image5 ne 'X'}">
	            	<img src="upload/carRegist_images/${carDetail.car_image5}">
				</c:if>
	        </div>
	        <c:if test="${carDetail.car_image2 ne 'X' and carDetail.car_image3 ne 'X' and
      					carDetail.car_image4 ne 'X' and carDetail.car_image5 ne 'X'}">
      			<button class="back"><img src="image/carDetailView/left_arrow.png" id="back_img"></button>
      			<button class="next"><img src="image/carDetailView/right_arrow.png" id="next_img"></button>
      		</c:if>
	      </div>
	    </div>
		<div class="part">
			<div class="basic_info">
				<div>${carDetail.car_id}</div>
				<div>
					<c:choose>
						<c:when test="${carDetail.car_brand == 'benz'}">벤츠</c:when>
						<c:when test="${carDetail.car_brand == 'tesla'}">테슬라</c:when>
						<c:when test="${carDetail.car_brand == 'ferrari'}">페라리</c:when>
						<c:when test="${carDetail.car_brand == 'bmw'}">BMW</c:when>
						<c:when test="${carDetail.car_brand == 'audi'}">아우디</c:when>
						<c:when test="${carDetail.car_brand == 'maserati'}">마세라티	</c:when>
						<c:when test="${carDetail.car_brand == 'bentley'}">벤틀리</c:when>
						<c:when test="${carDetail.car_brand == 'cadillac'}">캐딜락</c:when>
					</c:choose>
					${carDetail.car_year}연식 
					${carDetail.car_name}
				</div>
			</div>
				<div class="basic_info detail">
					<div>색상 |
						<c:choose>
							<c:when test="${carDetail.car_color == 'black'}">검정색</c:when>
							<c:when test="${carDetail.car_color == 'white'}">흰색</c:when>
							<c:when test="${carDetail.car_color == 'silver'}">회색</c:when>
							<c:when test="${carDetail.car_color == 'red'}">빨간색</c:when>
							<c:when test="${carDetail.car_color == 'blue'}">파란색</c:when>
							<c:when test="${carDetail.car_color == 'yellow'}">노란색</c:when>
							<c:when test="${carDetail.car_color == 'etc'}">기타</c:when>
						</c:choose>
					</div>
					<div>
						배기량 | ${carDetail.car_capacity}cc
					</div>
					<div>
						주행거리 | ${carDetail.car_distance}
					</div>
				</div>
				<div class="basic_info detail">
					<div>
						차종 |
						<c:choose>
							<c:when test="${carDetail.car_type == 'compactCar'}">경차</c:when>
							<c:when test="${carDetail.car_type == 'semimidCar'}">소/준중형</c:when>
							<c:when test="${carDetail.car_type == 'midCar'}">중형</c:when>
							<c:when test="${carDetail.car_type == 'largeCar'}">대형</c:when>
							<c:when test="${carDetail.car_type == 'sports'}">스포츠카</c:when>
							<c:when test="${carDetail.car_type == 'smallSUV'}">소형SUV</c:when>
							<c:when test="${carDetail.car_type == 'midSUV'}">중형SUV</c:when>
							<c:when test="${carDetail.car_type == 'largeSUV'}">대형SUV</c:when>
							<c:when test="${carDetail.car_type == 'smallRV'}">소형RV</c:when>
							<c:when test="${carDetail.car_type == 'largeRV'}">대형RV</c:when>
						</c:choose>
					</div>
					<div>
						연료 | 
						<c:choose>
							<c:when test="${carDetail.car_fuel == 'gasoline'}">가솔린</c:when>
							<c:when test="${carDetail.car_fuel == 'diesel'}">디젤</c:when>
							<c:when test="${carDetail.car_fuel == 'hybrid'}">하이브리드</c:when>
							<c:when test="${carDetail.car_fuel == 'electric'}">전기</c:when>
							<c:when test="${carDetail.car_fuel == 'hydrogen'}">수소</c:when>
						</c:choose>
					</div>
					<div>
						변속기 | 
						<c:choose>
							<c:when test="${carDetail.car_transmission == 'auto'}">오토</c:when>
							<c:when test="${carDetail.car_transmission == 'manual'}">수동</c:when>
						</c:choose>
					</div>
				</div>
				<hr>
				<div class="basic_info price">
					<span id="sale"><b>판매가격</b></span>
					<span id="car_price"><fmt:formatNumber value="${carDetail.car_price}" pattern="#,###" />만원</span>
				</div>
				<c:if test="${user_category eq null || user_category eq 'customer'}">
  	 				<div class="basic_info likeQty">
		   	 			<c:set var="found" value="false" />
		               	<c:forEach var="wish" items="${wishlist}">
		                   	<c:if test="${carDetail.car_id eq wish.car_id}">
		                       	<img src="image/carList/red_like_icon.png" id="likeImage" 
		                            onclick="likeThis(1,'${carDetail.car_id}','${carDetail.car_price}','${carDetail.car_image1}')">
		                        <c:set var="found" value="true"/>
		                   	</c:if>
		               	</c:forEach>
		               	<c:if test="${not found}">
		                   	<img src="image/carList/black_like_icon.png" id="likeImage" 
		                        onclick="likeThis(0,'${carDetail.car_id}','${carDetail.car_price}','${carDetail.car_image1}')">
		              	</c:if>
		   	 			${carDetail.car_like}
	   	 			</div>
	  	 		</c:if>				
				<div class="basic_info button">
					<button onclick="#">시승예약</button>
					<button onclick="#">주문하기</button>
				</div>
				
			</div>
	</div>
</body>
</html>