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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" href="css/user/user_allCarView_style.css">
</head>
<c:set var="user_id" value="${sessionScope.user_id}" />
<script type="text/javascript">

	
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
	
		<div class="subject">${brand_name} 상품만 보기</div>
		<c:if test="${user_category eq 'dealer' || user_category eq 'admin'}">
			<div class="rgstButton"><button onclick="location.href='carRegistForm.adm'; return false;">상품등록</button></div>
		</c:if>
		<div class="rgstButton"><button onclick="location.href='allCarListView.usr'; return false;">모든차량보기</button></div>
		<c:if test="${allCarList != null}">
			<div class="album py-5" style="padding-top: 5rem!important;">
			<div class="container">
      				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
					<c:forEach var="car" items="${allCarList}" varStatus="status">
						<div class="col">
          					<div class="card shadow-sm" >
            					<a href="carDetailView.usr?car_id=${car.car_id}" class="image-link">
								<img src="upload/carRegist_images/${car.car_image1}" width="100%" height="280" />
								  <c:if test="${car.sale_YN eq 'W'}">
							          <div class="sold-out-overlay">
							              <p class="sold-out-text">예약중</p>
							          </div>
							       </c:if>
							       <c:if test="${car.sale_YN eq 'N'}">
							          <div class="sold-out-overlay">
							              <p class="sold-out-text">판매완료</p>
							          </div>
							       </c:if>
							       </a>
            						<div class="card-body" >
            							<p class="card-text">
            								<c:forEach var="code" items="${allCode}">
												<c:if test="${code.code_category == 'car_brand'}">
													<c:if test="${car.car_brand == code.code_name}">${code.code_value}</c:if>
												</c:if>
											</c:forEach>
											&nbsp;${car.car_year}연식 
										</p>
										<p class="card-text">
											${car.car_name}	
										</p>
										<p class="card-text">       	 							
       	 									<fmt:formatNumber value="${car.car_price}" pattern="#,###" />만원
           								</p>
           								<p class="card-text"></p>
            							<div class="d-flex justify-content-end align-items-center">
            								<c:if test="${user_category eq null || user_category eq 'customer'}">
		       	 								<div class="likeQty text-muted">
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
							                             onclick="likeThis(0,'${car.car_id}','${car.car_price}','${car.car_image1}')">
							                   		</c:if>
		       	 									${car.car_like}
		       	 								</div>
	       	 								</c:if>
	       	 							</div>
	       	 						</div>
	       	 					</div>
	       	 				</div>	       	 				
	       	 			</c:forEach>	       	 			
	       	 		</div>	
				</div>
			</div>
		</c:if>
		<c:if test="${allCarList == null }">
			<div class="nothing">${brand_name} 브랜드는 등록된 상품이 없습니다.</div>
		</c:if>
	
</body>
</html>