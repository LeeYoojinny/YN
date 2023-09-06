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
				location.href="userLikeThis.cust?car_id="+car_id+"&car_price="+car_price+"&car_image1="+car_image1;
			}else {
				location.href="userUnlikeThis.cust?car_id="+car_id;
			}
		}
	}

</script>
<body>
<h2>${carDetail.car_id} 상세보기</h2>
	<div class="wrap_allCarList">
		<div class="slider__wrap">
           <div class="slider__img">
               <div class="slider__inner">
	            <div class="slider s1"><img src="upload/carRegist_images/${carDetail.car_image1}"></div>
	            <c:if test="${carDetail.car_image2 ne 'X'}">
	            	<div class="slider s2"><img src="upload/carRegist_images/${carDetail.car_image2}"></div>
				</c:if>
	            <c:if test="${carDetail.car_image3 ne 'X'}">
	            	 <div class="slider s3"><img src="upload/carRegist_images/${carDetail.car_image3}"></div>
				</c:if>
	            <c:if test="${carDetail.car_image4 ne 'X'}">
	            	  <div class="slider s4"><img src="upload/carRegist_images/${carDetail.car_image4}"></div>
				</c:if>
	            <c:if test="${carDetail.car_image5 ne 'X'}">
	            	 <div class="slider s5"><img src="upload/carRegist_images/${carDetail.car_image5}"></div>
				</c:if>
        	</div>
        	<c:if test="${carDetail.car_image2 ne 'X' and carDetail.car_image3 ne 'X' and
     					carDetail.car_image4 ne 'X' and carDetail.car_image5 ne 'X'}">
	      		<div class="slider__btn">
	      			<a href="#" class="prev"><img src="image/carDetailView/left_arrow.png"></a>
	      			<a href="#" class="next"><img src="image/carDetailView/right_arrow.png"></a>
	      		</div>
      		</c:if>
      		<div class="slider__dot"></div>
	      </div>
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
								<button onclick="#">시승예약</button>
								<button onclick="#">주문하기</button>
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
								<c:if test="${user_id eq carDetail.dealer_id || user_id eq 'admin'}">
									<button onclick="#">수정하기</button>
									<button onclick="#">삭제하기</button>
								</c:if>	
							</div>
		  	 				<div class="basic_info likeQty">
								<img src="image/carList/red_like_icon.png" id="likeImage" 
				                 onclick="likeThis(1,'${carDetail.car_id}','${carDetail.car_price}','${carDetail.car_image1}')">
				                ${carDetail.car_like}
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
						<button onclick="qnaThisCar.cust?car_id=${carDetail.car_id}">
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
        // 선택자
        const sliderWrap = document.querySelector(".slider__wrap");
        const sliderImg = sliderWrap.querySelector(".slider__img");       // 보여지는 영역
        const sliderInner = sliderWrap.querySelector(".slider__inner");   // 움직이는 영역
        const slider = sliderWrap.querySelectorAll(".slider");            // 개별 이미지
        const sliderDot = sliderWrap.querySelector(".slider__dot");       // 닷메뉴
        const sliderBtn = sliderWrap.querySelectorAll(".slider__btn a");  // 버튼

        let currentIndex = 0;                           //현재 보이는 이미지
        let sliderCount = slider.length;                //이미지 갯수
        let sliderInterval = 3000;                      //이미지 변경 간격 시간
        let sliderWidth = slider[0].offsetWidth;        //이미지의 가로값
        let dotIndex = "";

        //닷 메뉴 생성하기
        function init(){
        	if (sliderCount >= 2) {
	            //이미지 갯수만큼 닷 메뉴 생성
	            slider.forEach(() => dotIndex += "<a href='#' class='dot'><img src='image/carDetailView/dot.png'></a>");
	            sliderDot.innerHTML = dotIndex;
	
	            //첫 번째 닷 메뉴한테 활성화 표시하기
	            sliderDot.firstChild.classList.add("active");
        	}
        }
        init();
        
        //이미지 이동시키기
        function gotoSlider(num){
            sliderInner.style.transition = "all 400ms";
            sliderInner.style.transform = "translateX("+ -sliderWidth * num +"px)";
            currentIndex = num

            //닷 메뉴 활성화 하기
            let dotActive = document.querySelectorAll(".slider__dot .dot");
            dotActive.forEach((active) => active.classList.remove("active"));
            dotActive[num].classList.add("active");
        };

        //버튼을 클릭했을 때
        sliderBtn.forEach((btn,index) => {
            btn.addEventListener("click", () => {
                let prevIndex = (currentIndex + (sliderCount-1)) % sliderCount; //432104321043210
                let nextIndex = (currentIndex + 1) % sliderCount; //123401234012340

                if(btn.classList.contains("prev")){
                    gotoSlider(prevIndex);
                } else {
                    gotoSlider(nextIndex);
                }
            });
        });
        
        //닷버튼 눌렀을 때 그값 가져오기
        let dotClick = document.querySelectorAll(".slider__dot .dot");
        dotClick.forEach((active, i) => {
            active.addEventListener("click",() =>{
                gotoSlider(i);
            });
        });
        
    </script>
	
</body>
</html>