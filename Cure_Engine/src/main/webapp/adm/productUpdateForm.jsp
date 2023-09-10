<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cure Engine 상품수정</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" 
integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="css/admin/productUpdate_style.css">
</head>
<script type="text/javascript">
	
	//상품 등록 전 입력값 확인
	function carCheck(){
			
		//연식 정규식 (범위: 1900~2100)
		const regYearPass = /^(19[0-9][0-9]|20[0-9][0-9]|2100)$/;

		//판매금액 체크
		if(!document.f.car_price.value.trim()) {
			alert("판매금액을 입력해주세요.");
			document.f.car_price.focus();
			return false;
		}else if(document.f.car_price.value.trim() < 1){
			alert("판매금액은 최소 1만원 이상입니다.");
			document.f.car_price.focus();
			return false;
		}	
		
		//차량명 체크
		if(!document.f.car_name.value.trim()){
			alert("차량명을 입력해주세요.");
			document.f.car_name.focus();
			return false;
		}
				
		//배기량 체크
		if(!document.f.car_capacity.value.trim()) {
			alert("배기량 입력해주세요.");
			document.f.car_capacity.focus();
			return false;
		}else if(document.f.car_capacity.value.trim() < 330 && 27000 < document.f.car_capacity.value.trim()){
			alert("범위에 벗어난 배기량입니다. 다시 확인 해주세요.");
			document.f.car_capacity.focus();
			return false;
		}
		
		//연식 체크
		if(!document.f.car_year.value.trim()) {
			alert("연식을 입력해주세요.");
			document.f.car_year.focus();
			return false;
		}else if(!regYearPass.test(document.f.car_year.value.trim())){
			alert("범위에 벗어난 연식입니다. 다시 확인 해주세요.");
			document.f.car_year.focus();
			return false;
		}
		
		//주행거리 체크
		if(!document.f.car_distance.value.trim()) {
			alert("주행거리를 입력해주세요.");
			document.f.car_distance.focus();
			return false;
		}else if(document.f.car_distance.value.trim() < 1){
			alert("범위에 벗어난 주행거리 입니다. 다시 확인 해주세요.");
			document.f.car_distance.focus();
			return false;
		}
		
		//메인사진 첨부 체크
		if(document.f.update_img1.value == 'change') {
			if(!document.f.car_image1.value) {
				alert("메인이미지를 첨부 해주세요.");
				document.f.car_image1.focus();
				return false;
			}
		}
		document.f.submit();
		
	}

</script>
<body>
	<div class="wrap_regist">
	<div class="subject">상품수정</div>
		<form action="carUpdate.adm" name="f" method="post" enctype="multipart/form-data">
			<input type="hidden" name="dealer_id" value="${user_id}">
			<div class="field_row">
				<div class="field_row item">
					<b>차량정보</b>
					<input type="text" name="car_id" id="car_id" value="${carInfo.car_id}" style="background-color:#E6E6E6; boder:none;" readonly>
					<input type="hidden" name="carIdCheckYN" id="carIdCheckYN" value="Y">
				</div>
				<div class="field_row item">
					<b>판매금액</b>
					<input type="number" name="car_price" id="car_price" value="${carInfo.car_price}" placeholder="숫자만입력"><b>&nbsp;만원</b>
				</div>
			</div>
			<div class="field_row">
				<div class="field_row item">
					<b>차량명</b>
					<input type="text" name="car_name" id="car_name" value="${carInfo.car_name}" placeholder="자동차명+트림">
				</div>
				<div class="field_row item">
					<b>배기량</b>
					<input type="number" name="car_capacity" id="car_capacity" value="${carInfo.car_capacity}"placeholder="숫자만입력"><b>&nbsp;cc</b>
				</div>
			</div>
			<div class="field_row">
				<div class="field_row item">
					<b>제조사</b>
					<select name="car_brand" id="car_brand" required>
						<option value="" disabled selected>&nbsp;==== 선택하세요 ====&nbsp;</option>
						<c:forEach var="code" items="${allCode}">
    						<c:if test="${code.code_category eq 'car_brand'}">
    							<c:if test="${code.code_name eq carInfo.car_brand }">
									<option value="${code.code_name}" selected>${code.code_value}</option>
								</c:if>
								<c:if test="${code.code_name ne carInfo.car_brand }">
									<option value="${code.code_name}">${code.code_value}</option>
								</c:if>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="field_row item">
					<b>색상</b>
					<select name="car_color" id="car_color" required>
						<option value="" disabled selected>&nbsp;==== 선택하세요 ====&nbsp;</option>
						<c:forEach var="code" items="${allCode}">
    						<c:if test="${code.code_category eq 'car_color'}">
								<c:if test="${code.code_name eq carInfo.car_color }">
									<option value="${code.code_name}" selected>${code.code_value}</option>
								</c:if>
								<c:if test="${code.code_name ne carInfo.car_brand }">
									<option value="${code.code_name}">${code.code_value}</option>
								</c:if>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="field_row">
				<div class="field_row item">
					<b>연료</b>
					<select name="car_fuel" id="car_fuel" required>
						<option value="" disabled selected>&nbsp;==== 선택하세요 ====&nbsp;</option>
						<c:forEach var="code" items="${allCode}">
    						<c:if test="${code.code_category eq 'car_fuel'}">
								<c:if test="${code.code_name eq carInfo.car_fuel }">
									<option value="${code.code_name}" selected>${code.code_value}</option>
								</c:if>
								<c:if test="${code.code_name ne carInfo.car_fuel }">
									<option value="${code.code_name}">${code.code_value}</option>
								</c:if>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="field_row item">
					<b>변속기</b>
					<select name="car_transmission" id="car_transmission" required>
						<option value="" disabled selected>&nbsp;==== 선택하세요 ====&nbsp;</option>
						<c:forEach var="code" items="${allCode}">
    						<c:if test="${code.code_category eq 'car_transmission'}">
								<c:if test="${code.code_name eq carInfo.car_transmission }">
									<option value="${code.code_name}" selected>${code.code_value}</option>
								</c:if>
								<c:if test="${code.code_name ne carInfo.car_transmission }">
									<option value="${code.code_name}">${code.code_value}</option>
								</c:if>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="field_row">
				<div class="field_row item">
					<b>차종</b>
					<select name="car_type" id="car_type" required>
						<option value="" disabled selected>&nbsp;==== 선택하세요 ====&nbsp;</option>
							<c:forEach var="code" items="${allCode}">
    						<c:if test="${code.code_category eq 'car_type'}">
								<c:if test="${code.code_name eq carInfo.car_type }">
									<option value="${code.code_name}" selected>${code.code_value}</option>
								</c:if>
								<c:if test="${code.code_name ne carInfo.car_type }">
									<option value="${code.code_name}">${code.code_value}</option>
								</c:if>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="field_row item">
					<b>사고유무</b>
						<label id="radio_accident"><input type="radio" name="car_accident" id="car_accident" value="N" ${carInfo.car_accident=='N'? 'checked' : ''}>N</label>
						<label id="radio_accident"><input type="radio" name="car_accident" id="car_accident" value="Y" ${carInfo.car_accident=='Y'? 'checked' : ''}>Y</label>
				</div>
			</div>
			<div class="field_row">
				<div class="field_row item">
					<b>연식</b>
					<input type="number" name="car_year" id="car_year" value="${carInfo.car_year}" placeholder="숫자만입력">
				</div>
				<div class="field_row item">
					<b>주행거리</b>
					<input type="number" name="car_distance" id="car_distance" value="${carInfo.car_distance}" placeholder="숫자만입력"><b>&nbsp;km</b>
				</div>
			</div>
			<div class="field_column">
			<c:set var="carContent" value="${carInfo.car_content}" />
			<c:set var="carContentArray" value="${fn:split(carContent, ',')}" />
				<div class="field_column item">
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
						            <input type="checkbox" name="car_content" id="car_content" value="${code.code_name}" ${isChecked ? 'checked' : ''}>
						            ${code.code_value}					            
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
						            <input type="checkbox" name="car_content" id="car_content" value="${code.code_name}" ${isChecked ? 'checked' : ''} >
						            ${code.code_value}					   
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
						            <input type="checkbox" name="car_content" id="car_content" value="${code.code_name}" ${isChecked ? 'checked' : ''}>
						            ${code.code_value}					           
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
						            <input type="checkbox" name="car_content" id="car_content" value="${code.code_name}" ${isChecked ? 'checked' : ''}>
						            ${code.code_value}					            
						        </label>
						    </c:if>
						</c:forEach>				
					</div>	
				</div>
			</div>
			<div class="field_column">
				<div class="field_column item">
					<b>사고이력(선택)</b>
					<c:set var="accidentDetail" value="${carInfo.car_accident_detail}" />
					<c:set var="accidentDetailArr" value="${fn:split(accidentDetail, ',')}" />
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
						            <input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="${code.code_name}" ${isChecked ? 'checked' : ''}>
						            ${code.code_value}						            
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
						            <input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="${code.code_name}" ${isChecked ? 'checked' : ''}>
						            ${code.code_value}						            
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
						            <input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="${code.code_name}" ${isChecked ? 'checked' : ''}>
						            ${code.code_value}						            
						        </label>
						    </c:if>
						</c:forEach>	
					</div>
				</div>
			</div>
			<div class="field_row_file">
				<div class="field_row_file item_file">
					<b>메인이미지(필수)</b>
					<input type="file" name="car_image1" id="car_image1">
					<img src="upload/carRegist_images/${carInfo.car_image1}" id="${carInfo.car_image1}" width="50px;">
					<i class="fa-regular fa-trash-can delete-img" data-image-name="${carInfo.car_image1}" data-update-field="update_img1"></i>					
					<input type="hidden" name="update_img1" value="noChange">
					<input type="hidden" name="origin_img1" value="${carInfo.car_image1}">				
				</div>
			</div>
			<div class="field_row_file">
				<div class="field_row_file item_file">
					<b>상세이미지1(선택)</b>
					<input type="file" name="car_image2" id="car_image2">
					<c:if test="${carInfo.car_image2 ne 'X' }">
						<img src="upload/carRegist_images/${carInfo.car_image2}" id="${carInfo.car_image2}" width="50px;">
						<i class="fa-regular fa-trash-can delete-img" data-image-name="${carInfo.car_image2}" data-update-field="update_img2"></i>
					</c:if>
					<input type="hidden" name="update_img2" value="noChange">
					<input type="hidden" name="origin_img2" value="${carInfo.car_image2}">
				</div>
				<div class="field_row_file item_file">
					<b>상세이미지2(선택)</b>
					<input type="file" name="car_image3" id="car_image3">
					<c:if test="${carInfo.car_image3 ne 'X' }">
						<img src="upload/carRegist_images/${carInfo.car_image3}" id="${carInfo.car_image3}" width="50px;">
						<i class="fa-regular fa-trash-can delete-img" data-image-name="${carInfo.car_image3}" data-update-field="update_img3"></i>						
					</c:if>
					<input type="hidden" name="update_img3" value="noChange">
					<input type="hidden" name="origin_img3" value="${carInfo.car_image3}">
				</div>
			</div>
			<div class="field_row_file">
				<div class="field_row_file item_file">
					<b>상세이미지3(선택)</b>					
					<input type="file" name="car_image4" id="car_image4">
					<c:if test="${carInfo.car_image4 ne 'X' }">						
						<img src="upload/carRegist_images/${carInfo.car_image4}" id="${carInfo.car_image4}" width="50px;">
						<i class="fa-regular fa-trash-can delete-img" data-image-name="${carInfo.car_image4}" data-update-field="update_img4"></i>						
					</c:if>
					<input type="hidden" name="update_img4" value="noChange">
					<input type="hidden" name="origin_img4" value="${carInfo.car_image4}">
				</div>
				<div class="field_row_file item_file">
					<b>상세이미지4(선택)</b>
					<input type="file" name="car_image5" id="car_image5">
					<c:if test="${carInfo.car_image5 ne 'X' }">
						<img src="upload/carRegist_images/${carInfo.car_image5}" id="${carInfo.car_image5}" width="50px;">
						<i class="fa-regular fa-trash-can delete-img" data-image-name="${carInfo.car_image5}" data-update-field="update_img5"></i>						
					</c:if>
					<input type="hidden" name="update_img5" value="noChange">
					<input type="hidden" name="origin_img5" value="${carInfo.car_image5}">
				</div>
			</div>
			<div class="productSubmit"><input type="submit" value="수정완료" onclick="carCheck(); return false;"></div>
		</form>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<script>
	    // delete-img 클릭 시 처리
	    var deleteButtons = document.querySelectorAll(".delete-img");
	
	    deleteButtons.forEach(function(button) {
	        button.addEventListener("click", function() {
	            var imageName = button.getAttribute("data-image-name");
	            console.log(imageName);
	            var updateField = button.getAttribute("data-update-field");
				
	            /* var image = document.querySelector("img[name='" + imageName + "']");
	            image.src = "#"; */
	            
	            var image = document.getElementById(imageName);
	            image.style.display = "none";
	            
	            button.style.display = "none";
	
	            // update 필드 값을 "change"로 설정
	            var updateFieldInput = document.querySelector("input[name='" + updateField + "']");
	            updateFieldInput.value = "change";
	        });
	    });
	</script>

</body>
</html>