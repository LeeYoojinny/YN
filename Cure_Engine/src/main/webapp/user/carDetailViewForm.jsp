<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.Car" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cure Engine 차량상세보기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/user/user_carDetailView_style.css">

</head>
<%
Car carDetail = (Car)request.getAttribute("carDetail");
String image1 = carDetail.getCar_image1();
String image2 = carDetail.getCar_image2();
String image3 = carDetail.getCar_image3();
String image4 = carDetail.getCar_image4();
String image5 = carDetail.getCar_image5();

%>
<script type="text/javascript">
	function likeThis(like,car_id,car_price,car_image1) {
		var user_id = '<c:out value="${user_id}" />'
		
		if(!user_id) {
			alert("로그인 후 관심상품 등록이 가능합니다.");
			location.href="userLogin.usr";
			return false;
		}else {
			if(like == 0){
				location.href="userLikeThis.cust?car_id="+car_id+"&car_price="+car_price+"&car_image1="+car_image1+"&displayNum=4";
			}else {
				location.href="userUnlikeThis.cust?car_id="+car_id+"&displayNum=4";
			}
		}
	}
	
	function removeCheck_2(car_id) {
		 var confirmMessage = car_id+ ' 차량을 삭제하시겠습니까?';
		 
		 if(confirm(confirmMessage)) {
			 var param = 'car_ids=' + car_id;
			 location.href = 'myCarRemove.adm?' + param;
		 }
	}
	
	function qnaRequest(car_id) {
		var user_id = '<c:out value="${user_id}" />'
		
		if(!user_id) {
			alert("로그인 후 문의 가능합니다.");
			location.href="userLogin.usr";
			return false;
		}else {
			location.href="qna_boardWrite.bo?car_id="+car_id;		
			
		}
	}
	
	function orderCheck(car_id){
		var user_id = '<c:out value="${user_id}" />'
		
		if(!user_id) {
			alert("로그인 후 주문 가능합니다.");
			location.href="userLogin.usr";
			return false;
		}
		
		location.href="orderFormStep1.cust?car_id="+car_id;
	}
	
	function reserCheck(user_id,car_id,dealer_id){
		var user_id = '<c:out value="${user_id}" />'
			
		if(!user_id) {
			alert("로그인 후 예약 가능합니다.");
			location.href="userLogin.usr";
			return false;
		}
		
		location.href="reservationForm.cust?user_id="+user_id+"&car_id="+car_id+"&dealer_id="+dealer_id;
	}
	
</script>
<body>
<h2>${carDetail.car_id} 상세보기</h2>
	<div class="wrap_allCarList">
	   	<div id="carouselExampleIndicators" class="carousel slide">
		  <div class="carousel-indicators"  id="carouselButtons">

		  </div>
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img src="upload/carRegist_images/${carDetail.car_image1}" class="d-block w-100 "  alt="${carDetail.car_id}이미지1">
		    </div>
		    <c:if test="${carDetail.car_image2 ne 'X'}">
			    <div class="carousel-item">
			      <img src="upload/carRegist_images/${carDetail.car_image2}" class="d-block w-100 img-fluid" alt="${carDetail.car_id}이미지2">
			    </div>
			</c:if>
			<c:if test="${carDetail.car_image3 ne 'X'}">
			    <div class="carousel-item">
			      <img src="upload/carRegist_images/${carDetail.car_image3}" class="d-block w-100 " alt="${carDetail.car_id}이미지3">
			    </div>
		    </c:if>
			<c:if test="${carDetail.car_image4 ne 'X'}" >
			    <div class="carousel-item" >
			      <img src="upload/carRegist_images/${carDetail.car_image4}" class="d-block w-100 " alt="${carDetail.car_id}이미지4">
			    </div>
		    </c:if>
			<c:if test="${carDetail.car_image5 ne 'X'}" >
			    <div class="carousel-item">
			      <img src="upload/carRegist_images/${carDetail.car_image5}" class="d-block w-100 " alt="${carDetail.car_id}이미지5">
			    </div>
		    </c:if>
		  </div>
		  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Previous</span>
		  </button>
		  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Next</span>
		  </button>
		</div>
	   	<div class="wrap_secLine">
			<div class="part explain">
				<div class="basic_info">
					<div><span id="subject4">차량번호 | </span>${carDetail.car_id}</div>
					<div>
					<span id="subject4">차량명&ensp;&nbsp; | </span>
						<c:forEach var="code" items="${allCode}">
							<c:if test="${code.code_category == 'car_brand'}">
								<c:if test="${carDetail.car_brand == code.code_name}">${code.code_value}</c:if>
							</c:if>
						</c:forEach>
						
						${carDetail.car_year}연식 
						${carDetail.car_name}
					</div>
				</div>
					<div class="basic_info detail">
						<table>
							<tr>
								<td><span id="subject1">색상 |</span></td>
								<td>
									<span id="contents">
									<c:forEach var="code" items="${allCode}">
										<c:if test="${code.code_category == 'car_color'}">
											<c:if test="${carDetail.car_color == code.code_name}">${code.code_value}</c:if>
										</c:if>
									</c:forEach>	
									</span>
								</td>
								<td><span id="subject2">배기량 | </span></td>
								<td>
									<span id="contents">
									<fmt:formatNumber value="${carDetail.car_capacity}" pattern="#,###" />cc</span>
								</td>
								<td><span id="subject3">주행거리 | </span></td>
								<td>
									<span id="contents">
									<fmt:formatNumber value="${carDetail.car_distance}" pattern="#,###" />km</span>
								</td>							
							</tr>
							<tr>
								<td><span id="subject4">차종 |</span></td>
								<td>
									<span id="contents">
									<c:forEach var="code" items="${allCode}">
										<c:if test="${code.code_category == 'car_type'}">
											<c:if test="${carDetail.car_type == code.code_name}">${code.code_value}</c:if>
										</c:if>
									</c:forEach>	
									</span>
								</td>
								<td><span id="subject5">연료&ensp;&nbsp; | </span></td>
								<td>
									<span id="contents">
									<c:forEach var="code" items="${allCode}">
										<c:if test="${code.code_category == 'car_fuel'}">
											<c:if test="${carDetail.car_fuel == code.code_name}">${code.code_value}</c:if>
										</c:if>
									</c:forEach>	
									</span>
								</td>
								<td><span id="subject6">변속기&ensp;&nbsp; | </span></td>
								<td>
									<span id="contents">
									<c:forEach var="code" items="${allCode}">
										<c:if test="${code.code_category == 'car_transmission'}">
											<c:if test="${carDetail.car_transmission == code.code_name}">${code.code_value}</c:if>
										</c:if>
									</c:forEach>	
									</span>
								</td>
							</tr>					
						</table>
					</div>
					<hr>
					<div class="basic_info price">
						<span id="sale"><b>판매가격</b></span>
						<span id="car_price"><fmt:formatNumber value="${carDetail.car_price}" pattern="#,###" />만원</span>
					</div>
					<c:if test="${user_category eq null || user_category eq 'customer'}">
						<div class="basic_info button">
							<div id="resv_order">
								<c:if test="${carDetail.sale_YN eq 'Y'}">
									<button onclick="reserCheck('${user_id}','${carDetail.car_id}','${carDetail.dealer_id}'); return false;">시승예약</button>
									<button onclick="orderCheck('${carDetail.car_id}')">주문하기</button>
								</c:if>
								<c:if test="${carDetail.sale_YN eq 'W' || carDetail.sale_YN eq 'N'}">
									<button disabled>시승예약</button>
									<button disabled>주문하기</button>
								</c:if>
							</div>
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
						</div>
					</c:if>
					<c:if test="${user_category eq 'dealer' || user_category eq 'admin'}">
						<div class="basic_info button">
							<div id="resv_order">
								<c:if test="${carDetail.sale_YN eq 'Y'}">
									<button onclick="location.href='carUpdateForm.adm?car_id=${carDetail.car_id}'; return false;">수정하기</button>
									<button onclick="removeCheck_2('${carDetail.car_id}');return false;">삭제하기</button>
								</c:if>	
								<c:if test="${carDetail.sale_YN eq 'W' || carDetail.sale_YN eq 'N'}">
									<button disabled>수정하기</button>
									<button disabled>삭제하기</button>
								</c:if>	
							</div>
		  	 				<div class="basic_info likeQty">
								<img src="image/carList/red_like_icon.png" id="likeImage" >${carDetail.car_like}
			   	 			</div>
						</div>
					</c:if>
				</div>
			<div class="part dealer">
				<table>
					<tr>
						<td rowspan="6">
							<c:if test="${dealerInfo.user_gender eq 'M'}">
								<img src="image/carDetailView/dealer_man.png">
							</c:if>
							<c:if test="${dealerInfo.user_gender eq 'F'}">
								<img src="image/carDetailView/dealer_f.png">
							</c:if>
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td><span id="subject1">딜러명 |</span></td>
						<td><span id="contents">${dealerInfo.user_name}</span></td>
					</tr>
					<tr>
						<td><span id="subject1">연락처 |</span></td>
						<td>
							<span id="contents">
								<c:set var="userPhone" value="${dealerInfo.user_phone}" />
								<c:set var="part1" value="${fn:substring(userPhone, 0, 3)}" />
								<c:set var="part2" value="${fn:substring(userPhone, 3, 7)}" />
								<c:set var="part3" value="${fn:substring(userPhone, 7, 11)}" />
								<c:out value="${part1}-${part2}-${part3}" />
							</span>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						<button onclick="qnaRequest('${carDetail.car_id}')">
							'${carDetail.car_id}' 상품 문의글 남기기
						</button></td>
					</tr>
					<tr>
						<td colspan="2" id="anotherCar">
							<a href="otherCarView.usr?dealer_id=${carDetail.dealer_id}&dealer_name=${dealerInfo.user_name}">
							[<span id="bigFont">${dealerInfo.user_name}</span>딜러]의 다른상품 보러가기</a>
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="wrap_thirdLine">
			<h3>해당 차량의 옵션과 사고이력을 알려드립니다!</h3>
			<c:set var="carContent" value="${carDetail.car_content}" />
			<c:set var="carContentArray" value="${fn:split(carContent, ',')}" />
			<div class="part options">
				<b>옵션</b>
				<div>
					<span>외관/내장</span>
					<c:forEach var="code" items="${allCode}">
					    <c:if test="${code.code_category eq 'car_option_outIn'}">
					        <label>
					            <c:set var="isChecked" value="false" />
					            <c:forEach var="item" items="${carContentArray}">
					                <c:if test="${item eq code.code_name}">
					                    <c:set var="isChecked" value="true" />
					                </c:if>
					            </c:forEach>
					            <input type="checkbox" name="car_content" id="car_content" value="${code.code_name}" ${isChecked ? 'checked' : ''} disabled>
					            <c:if test="${isChecked}"><span class="option_Y">${code.code_value}</span></c:if>
					            <c:if test="${not isChecked}"><span class="option_N">${code.code_value}</span></c:if>
					        </label>
					    </c:if>
					</c:forEach>
				</div>
				<div>
					<span>안전</span>
					<c:forEach var="code" items="${allCode}">
					    <c:if test="${code.code_category eq 'car_option_safety'}">
					        <label>
					            <c:set var="isChecked" value="false" />
					            <c:forEach var="item" items="${carContentArray}">
					                <c:if test="${item eq code.code_name}">
					                    <c:set var="isChecked" value="true" />
					                </c:if>
					            </c:forEach>
					            <input type="checkbox" name="car_content" id="car_content" value="${code.code_name}" ${isChecked ? 'checked' : ''} disabled>
					            <c:if test="${isChecked}"><span class="option_Y">${code.code_value}</span></c:if>
					            <c:if test="${not isChecked}"><span class="option_N">${code.code_value}</span></c:if>
					        </label>
					    </c:if>
					</c:forEach>				
				</div>
				<div>
					<span>편의/멀티미디어</span>
					<c:forEach var="code" items="${allCode}">
					    <c:if test="${code.code_category eq 'car_option_convi'}">
					        <label>
					            <c:set var="isChecked" value="false" />
					            <c:forEach var="item" items="${carContentArray}">
					                <c:if test="${item eq code.code_name}">
					                    <c:set var="isChecked" value="true" />
					                </c:if>
					            </c:forEach>
					            <input type="checkbox" name="car_content" id="car_content" value="${code.code_name}" ${isChecked ? 'checked' : ''} disabled>
					            <c:if test="${isChecked}"><span class="option_Y">${code.code_value}</span></c:if>
					            <c:if test="${not isChecked}"><span class="option_N">${code.code_value}</span></c:if>
					        </label>
					    </c:if>
					</c:forEach>				
				</div>
				<div>
					<span>시트</span>
					<c:forEach var="code" items="${allCode}">
					    <c:if test="${code.code_category eq 'car_option_seat'}">
					        <label>
					            <c:set var="isChecked" value="false" />
					            <c:forEach var="item" items="${carContentArray}">
					                <c:if test="${item eq code.code_name}">
					                    <c:set var="isChecked" value="true" />
					                </c:if>
					            </c:forEach>
					            <input type="checkbox" name="car_content" id="car_content" value="${code.code_name}" ${isChecked ? 'checked' : ''} disabled>
					            <c:if test="${isChecked}"><span class="option_Y">${code.code_value}</span></c:if>
					            <c:if test="${not isChecked}"><span class="option_N">${code.code_value}</span></c:if>
					        </label>
					    </c:if>
					</c:forEach>				
				</div>				
			</div>
			<div class="part options">
				<b>사고이력</b>
				<div id="acci_YN">
					<span>사고유무 | 
						<c:if test="${carDetail.car_accident eq 'N' }"><b style="display:inline;">무사고</b></c:if>
						<c:if test="${carDetail.car_accident eq 'Y' }"><b style="display:inline;">사고있음</b></c:if>
					</span>
				</div>
				<c:set var="accidentDetail" value="${carDetail.car_accident_detail}" />
				<c:set var="accidentDetailArr" value="${fn:split(accidentDetail, ',')}" />
				<c:if test="${carDetail.car_accident eq 'Y'}">
					<div>
						<span>구분</span>
						<c:forEach var="code" items="${allCode}">
						    <c:if test="${code.code_category eq 'car_accident_dv'}">
						        <label>
						            <c:set var="isChecked" value="false" />
						            <c:forEach var="item" items="${accidentDetailArr}">
						                <c:if test="${item eq code.code_name}">
						                    <c:set var="isChecked" value="true" />
						                </c:if>
						            </c:forEach>
						            <input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="${code.code_name}" ${isChecked ? 'checked' : ''} disabled>
						            <c:if test="${isChecked}"><span class="option_Y">${code.code_value}</span></c:if>
						            <c:if test="${not isChecked}"><span class="option_N">${code.code_value}</span></c:if>
						        </label>
						    </c:if>
						</c:forEach>	
					</div>
					<div>
						<span>특수사고</span>
						<c:forEach var="code" items="${allCode}">
						    <c:if test="${code.code_category eq 'car_accident_sp'}">
						        <label>
						            <c:set var="isChecked" value="false" />
						            <c:forEach var="item" items="${accidentDetailArr}">
						                <c:if test="${item eq code.code_name}">
						                    <c:set var="isChecked" value="true" />
						                </c:if>
						            </c:forEach>
						            <input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="${code.code_name}" ${isChecked ? 'checked' : ''} disabled>
						            <c:if test="${isChecked}"><span class="option_Y">${code.code_value}</span></c:if>
						            <c:if test="${not isChecked}"><span class="option_N">${code.code_value}</span></c:if>
						        </label>
						    </c:if>
						</c:forEach>	
					</div>
					<div>
						<span>수리이력</span>
						<c:forEach var="code" items="${allCode}">
						    <c:if test="${code.code_category eq 'car_accident_rp'}">
						        <label>
						            <c:set var="isChecked" value="false" />
						            <c:forEach var="item" items="${accidentDetailArr}">
						                <c:if test="${item eq code.code_name}">
						                    <c:set var="isChecked" value="true" />
						                </c:if>
						            </c:forEach>
						            <input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="${code.code_name}" ${isChecked ? 'checked' : ''} disabled>
						            <c:if test="${isChecked}"><span class="option_Y">${code.code_value}</span></c:if>
						            <c:if test="${not isChecked}"><span class="option_N">${code.code_value}</span></c:if>
						        </label>
						    </c:if>
						</c:forEach>	
					</div>
				</c:if>
			</div>
		</div>
	</div>
	
	<script>
	  // 이미지 개수를 파악합니다.
	  const imageCount = document.querySelectorAll('.carousel-item').length;
	
	  // 버튼을 생성합니다.
	  const carouselButtons = document.getElementById('carouselButtons');
	  for (let i = 0; i < imageCount; i++) {
	    const button = document.createElement('button');
	    button.type = 'button';
	    button.setAttribute('data-bs-target', '#carouselExampl3reIndicators');
	    button.setAttribute('data-bs-slide-to', i);
	    if (i === 0) {
	      button.classList.add('active');
	      button.setAttribute('aria-current', 'true');
	    }
	    button.setAttribute('aria-label', `Slide ${i + 1}`);
	    carouselButtons.appendChild(button);
	  }
	</script>
	<script>
	  // 첫 번째 이미지 요소를 가져옵니다.
	  const firstImage = document.querySelector('.carousel-item:first-child img');
	
	  // 첫 번째 이미지의 높이를 700px로 설정하고 최대 가로 너비를 100%로 설정합니다.
	  firstImage.style.height = '700px';
	  firstImage.style.maxWidth = '100%';
	
	  // 모든 이미지에 첫 번째 이미지와 동일한 스타일을 적용합니다.
	  const allImages = document.querySelectorAll('.carousel-item img');
	  allImages.forEach((image) => {
	    image.style.height = '700px';
	    image.style.maxWidth = '100%';
	  });
	</script>
	
</body>
</html>