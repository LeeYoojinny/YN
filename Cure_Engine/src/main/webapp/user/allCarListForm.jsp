<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/user/user_allCarView_style.css">
</head>
<c:set var="user_id" value="${sessionScope.user_id}" />
<script type="text/javascript">

	//상세검색 열고 닫을 수 있게 해주는 기능
	document.addEventListener("DOMContentLoaded", function () {
	    var showDetailElement = document.querySelector(".showDetail");
	
	    if (showDetailElement) {
	        showDetailElement.addEventListener("click", function () {
	            var wrap_detailItem = document.getElementById("wrap_detailItem");
	
	            if (wrap_detailItem.style.display === "none" || wrap_detailItem.style.display === "") {
	                wrap_detailItem.style.display = "block";
	            } else {
	                wrap_detailItem.style.display = "none";
	            }
	        });
	    }
	});
	
	
	//검색 하기 전 값 확인
	function searchCheck() {
		var search_input = document.getElementById("search_input");

		if(search_input.value == "" ) {
			alert("검색어를 입력해주세요.");
			search_input.focus();
			return false;
		}else {
			location.href="carSearch.usr";
		}

	}
	
	//초기화 버튼
	function allReset() {
	       // 브랜드, 차종, 색상 등의 input 요소를 선택하고 값을 초기화합니다.
	       const brandInputs = document.querySelectorAll('input[name="car_brand"]');
	       const typeInputs = document.querySelectorAll('input[name="car_type"]');
	       const colorInputs = document.querySelectorAll('input[name="car_color"]');
	       const carDistanceInput = document.querySelector('input[name="car_distance"]');
	
	       brandInputs.forEach(input => {
	           input.checked = false;
	       });
	
	       typeInputs.forEach(input => {
	           input.checked = false;
	       });
	
	       colorInputs.forEach(input => {
	           input.checked = false;
	       });
	
	       carDistanceInput.value = ''; // 주행거리 input 초기화
	}
	
	
	function likeThis(like,car_id,car_price,car_image1) {
		var user_id = '<c:out value="${user_id}" />'
		
		if(!user_id) {
			alert("로그인 후 관심상품 등록이 가능합니다.");
			location.href="userLogin.usr";
			return false;
		}else {
			if(like == 0){
				location.href="userLikeThis.cust?car_id="+car_id+"&car_price="+car_price+"&car_image1="+car_image1+"&displayNum=1";
			}else {
				location.href="userUnlikeThis.cust?car_id="+car_id+"&displayNum=1";
			}
		}
	}
	
</script>
<body>
	<div class="wrap_allCarList">
		<div class="subject"></div>
		<c:if test="${user_category eq 'dealer' || user_category eq 'admin'}">
			<div class="rgstButton"><button onclick="location.href='carRegistForm.adm'; return false;">상품등록</button></div>
		</c:if>
		<c:if test="${allCarList != null }">
		<form action="carSearch.usr" method="post" name="f">
			<div class="searchPart">
				<!-- <div class="searchBar">
					<div class="searchBar item">
						<input type="text" name="search_input" id="search_input" placeholder="&ensp;원하는 차량명으로 검색해보세요">
						<button onclick="searchCheck(); return false;">검색</button>
					</div>
				</div> -->
				<div class="searchDetail">
    				<div class="showDetail">
    				<img src="image/carList/under_icon_2.png"><span>상세검색</span><img src="image/carList/under_icon_2.png">
    				</div>
				</div>
				<div class="wrap_detailItem" id="wrap_detailItem">
					<div class="detailItem">
						<b>브랜드</b>
						<div>
							<c:forEach var="code" items="${allCode}">
	    						<c:if test="${code.code_category eq 'car_brand'}">
									<label>
										<input type="checkbox" name="car_brand" id="car_brand" value="${code.code_name}">${code.code_value}
									</label>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<div class="detailItem">
						<b>차종</b>
						<div>
							<label><input type="checkbox" name="car_type" id="car_type" value="regular">세단</label>
							<label><input type="checkbox" name="car_type" id="car_type" value="sports">스포츠카</label>
							<label><input type="checkbox" name="car_type" id="car_type" value="SUV">SUV</label>
							<label><input type="checkbox" name="car_type" id="car_type" value="RV">RV</label>
						</div>
					</div>
					<div class="detailItem">
						<b>색상</b>
						<div>
							<c:forEach var="code" items="${allCode}">
	    						<c:if test="${code.code_category eq 'car_color'}">
									<label>
										<input type="checkbox" name="car_color" id="car_color" value="${code.code_name}">${code.code_value}
									</label>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<div class="detailItem_row">
						<b>주행거리</b>
						<div>
							<input type="number" name="car_distance">km 이하
						</div>
					</div>
					<div class="detailItem_row">
						<b>금액</b>
						<div>
							<input type="number" name="start_price" placeholder=" 최소금액">
							<span id="won">만원 &nbsp;<b>~</b>&nbsp;</span>
							<input type="number" name="end_price" placeholder=" 최대금액">
							<span id=won>만원</span>
						</div>
					</div>
					<!-- <div class="detailItem_option">
						<b>옵션</b>
					</div> -->
					<div class="searchButton">
						<div class="searchButton bt">
							<button onclick="allReset(); return false;">검색조건초기화</button>
							<button type="submit">검색하기</button>
						</div>
					</div>
				</div>
			</div>
			</form>
			<div class="container">
				<c:forEach var="car" items="${allCarList}" varStatus="status">
					<div class="items">
						<div class="mainImg">
							<a href="carDetailView.usr?car_id=${car.car_id}">
							<img src="upload/carRegist_images/${car.car_image1}"></a>
						</div>
						<div class="title">
						<c:forEach var="code" items="${allCode}">
							<c:if test="${code.code_category == 'car_brand'}">
								<c:if test="${car.car_brand == code.code_name}">${code.code_value}</c:if>
							</c:if>
						</c:forEach>
						&nbsp;${car.car_year}연식 ${car.car_name}</div>
	       	 			<div class="price">
	       	 			<fmt:formatNumber value="${car.car_price}" pattern="#,###" />만원
	       	 			</div>
	       	 			<c:if test="${user_category eq null || user_category eq 'customer'}">
		       	 			<div class="likeQty">
		       	 			<c:set var="found" value="false" />
	                    	<c:forEach var="wish" items="${wishlist}">
	                        	<c:if test="${car.car_id eq wish.car_id}">
	                            	<img src="image/carList/red_like_icon.png" id="likeImage" 
	                                 onclick="likeThis(1,'${car.car_id}','${car.car_price}','${car.car_image1}')">
	                                 <c:set var="found" value="true"/>
	                        	</c:if>
	                    	</c:forEach>
	                    	<c:if test="${not found}">
	                        	<img src="image/carList/black_like_icon.png" id="likeImage" 
	                             onclick="likeThis(0,'${car.car_id}','${car.car_price}','${ car.car_image1}')">
	                   		</c:if>
		       	 				${car.car_like}
		       	 			</div>
	       	 			</c:if>
					</div>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${allCarList == null }">
			<div class="nothing">등록된 상품이 없습니다.</div>
		</c:if>
	</div>
</body>
</html>