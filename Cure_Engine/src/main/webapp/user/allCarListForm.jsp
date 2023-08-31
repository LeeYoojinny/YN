<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/user/user_allCarView_style.css">
</head>
<body>
	<div class="wrap_allCarList">
		<div class="subject"></div>
		<c:if test="${user_category eq 'dealer' || user_category eq 'admin'}">
			<div class="rgstButton"><button onclick="location.href='carRegistForm.adm'; return false;">상품등록</button></div>
		</c:if>
		<c:if test="${allCarList != null }">
			<c:forEach var="car" items="${allCarList}" varStatus="status">
				<div class="container">
					<div class="mainImg"><a href="carView.usr?car_id=${car.car_id}"><img src="upload/carRegist_images/${car.car_image1}"></a></div>
					<div class="title">${car.car_year}연식 ${car.car_name}</div>
       	 			<div class="price">${car.car_price}만원</div>
       	 			<div class="likeQty"><img src="image/carList/like_icon.png">&emsp;${car.car_like}</div>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${allCarList == null }">
			<div>등록된 상품이 없습니다.</div>
		</c:if>
	</div>
</body>
</html>