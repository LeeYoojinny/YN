<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cure Engine 상품등록</title>
<link rel="stylesheet" href="css/admin/productRegist_style.css">
</head>
<script type="text/javascript">
	
	//차량등록 중복체크 버튼 눌렀을 때 값 먼저 확인 후 체크보내기
	function carIdCheck() {
		const regCarIdPass = /^[0-9]{2,3}[가-힣]{1}[0-9]{4}$/;
		
		if(document.f.car_id.value.trim() =="") {
			alert("차량정보를 입력해주세요.");
			document.f.car_id.focus();
			return false;
		}
		
		if(!regCarIdPass.test(document.f.car_id.value.trim())){
			alert("형식에 맞는 차량정보를 입력해주세요.");
			document.f.car_id.select();
			return false;
		}
		
		var url = "adm/carIdCheck.jsp?car_id="+document.f.car_id.value;
		window.open(url, "check_carID", "width=500, height=300, left=450, top=200");
	}
	
	//차량등록 입력태그에서 키보드를 누를 때 호출되는 함수
	function carIdCheckInit() {
		if(document.f.carIdCheckYN.value == "Y") {
			document.f.carIdCheckYN.value = "N";
			document.f.car_id.select();
		}
		
	}
	
	//상품 등록 전 입력값 확인
	function carCheck(){
		
		//차량정보 정규식
		const regCarIdPass = /\d{2,3}[가-힣]{1}\d{4}/gm;
				
		//연식 정규식 (범위: 1900~2100)
		const regYearPass = /^(19[0-9][0-9]|20[0-9][0-9]|2100)$/;
		
		
		//차량정보 체크
		if(document.f.car_id.value.trim() =="") {
			alert("차량정보를 입력해주세요.");
			document.f.car_id.focus();
			return false;
		}else if(!regCarIdPass.test(document.f.car_id.value.trim())){
			alert("형식에 맞는 차량정보를 입력해주세요.");
			document.f.car_id.focus();
			return false;
		}
		
		//차량정보 중복체크 여부확인
		if(document.f.carIdCheckYN.value == "N"){
			alert("차량정보 중복확인 해주세요.");
			document.f.car_id.focus();
			return false;
		}
		
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
		if(!document.f.car_image1.value) {
			alert("메인이미지를 첨부 해주세요.");
			document.f.car_image1.focus();
			return false;
		}
		
		document.f.submit();
		
	}
</script>
<body>
	<div class="wrap_regist">
	<div class="subject">상품등록</div>
		<form action="carRegist.adm" name="f" method="post" enctype="multipart/form-data">
			<input type="hidden" name="dealer_id" value="${user_id}">
			<div class="field_row">
				<div class="field_row item">
					<b>차량정보</b>
					<input type="text" name="car_id" id="car_id" onkeyup="carIdCheckInit();" placeholder="(예)123허4567">
					<input type="button" onclick="carIdCheck();" value="등록중복확인">
					<input type="hidden" name="carIdCheckYN" id="carIdCheckYN" value="N">
				</div>
				<div class="field_row item">
					<b>판매금액</b>
					<input type="number" name="car_price" id="car_price" placeholder="숫자만입력"><b>&nbsp;만원</b>
				</div>
			</div>
			<div class="field_row">
				<div class="field_row item">
					<b>차량명</b>
					<input type="text" name="car_name" id="car_name" placeholder="자동차명+트림">
				</div>
				<div class="field_row item">
					<b>배기량</b>
					<input type="number" name="car_capacity" id="car_capacity" placeholder="숫자만입력"><b>&nbsp;cc</b>
				</div>
			</div>
			<div class="field_row">
				<div class="field_row item">
					<b>제조사</b>
					<select name="car_brand" id="car_brand" required>
						<option value="" disabled selected>&nbsp;==== 선택하세요 ====&nbsp;</option>
						<c:forEach var="code" items="${allCode}">
    						<c:if test="${code.code_category eq 'car_brand'}">
								<option value="${code.code_name}">${code.code_value}</option>
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
								<option value="${code.code_name}">${code.code_value}</option>
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
								<option value="${code.code_name}">${code.code_value}</option>
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
								<option value="${code.code_name}">${code.code_value}</option>
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
								<option value="${code.code_name}">${code.code_value}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="field_row item">
					<b>사고유무</b>
					<label id="radio_accident"><input type="radio" name="car_accident" id="car_accident" value="N" checked="checked">N</label>
					<label id="radio_accident"><input type="radio" name="car_accident" id="car_accident" value="Y">Y</label>
				</div>
			</div>
			<div class="field_row">
				<div class="field_row item">
					<b>연식</b>
					<input type="number" name="car_year" id="car_year" placeholder="숫자만입력">
				</div>
				<div class="field_row item">
					<b>주행거리</b>
					<input type="number" name="car_distance" id="car_distance" placeholder="숫자만입력"><b>&nbsp;km</b>
				</div>
			</div>
			<div class="field_column">
				<div class="field_column item">
					<b>옵션</b>
					<div>
						<span>외관/내장</span>
						<c:forEach var="code" items="${allCode}">
    						<c:if test="${code.code_category eq 'car_option_outIn'}">
								<label>
									<input type="checkbox" name="car_content" id="car_content" value="${code.code_name}">${code.code_value}
								</label>
							</c:if>
						</c:forEach>
					</div>
					<div>
						<span>안전</span>
						<c:forEach var="code" items="${allCode}">
    						<c:if test="${code.code_category eq 'car_option_safety'}">
								<label>
									<input type="checkbox" name="car_content" id="car_content" value="${code.code_name}">${code.code_value}
								</label>
							</c:if>
						</c:forEach>
					</div>
					<div>
						<span>편의/멀티미디어</span>
						<c:forEach var="code" items="${allCode}">
    						<c:if test="${code.code_category eq 'car_option_convi'}">
								<label>
									<input type="checkbox" name="car_content" id="car_content" value="${code.code_name}">${code.code_value}
								</label>
							</c:if>
						</c:forEach>
					</div>
					<div>
						<span>시트</span>
						<c:forEach var="code" items="${allCode}">
    						<c:if test="${code.code_category eq 'car_option_seat'}">
								<label>
									<input type="checkbox" name="car_content" id="car_content" value="${code.code_name}">${code.code_value}
								</label>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="field_column">
				<div class="field_column item">
					<b>사고이력(선택)</b>
					<div>
						<span>구분</span>
						<c:forEach var="code" items="${allCode}">
    						<c:if test="${code.code_category eq 'car_accident_dv'}">
								<label>
									<input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="${code.code_name}">${code.code_value}
								</label>
							</c:if>
						</c:forEach>
					</div>
					<div>
						<span>특수사고</span>	
						<c:forEach var="code" items="${allCode}">
    						<c:if test="${code.code_category eq 'car_accident_sp'}">
								<label>
									<input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="${code.code_name}">${code.code_value}
								</label>
							</c:if>
						</c:forEach>
					</div>
					<div>
						<span>수리이력</span>
						<c:forEach var="code" items="${allCode}">
    						<c:if test="${code.code_category eq 'car_accident_rp'}">
								<label>
									<input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="${code.code_name}">${code.code_value}
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
				</div>
			</div>
			<div class="field_row">
				<div class="field_row item_file">
					<b>상세이미지1(선택)</b>
					<input type="file" name="car_image2" id="car_image2">
				</div>
				<div class="field_row item_file">
					<b>상세이미지2(선택)</b>
					<input type="file" name="car_image3" id="car_image3">
				</div>
			</div>
			<div class="field_row">
				<div class="field_row item_file">
					<b>상세이미지3(선택)</b>
					<input type="file" name="car_image4" id="car_image4">
				</div>
				<div class="field_row item_file">
					<b>상세이미지4(선택)</b>
					<input type="file" name="car_image5" id="car_image5">
				</div>
			</div>
			<div class="productSubmit"><input type="submit" value="상품등록" onclick="carCheck(); return false;"></div>
		</form>
	</div>
</body>
</html>