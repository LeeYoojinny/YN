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
		const regCarIdPass = /\d{2,3}[가-힣]{1}\d{4}/gm;
		
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
						<option value="Benz">벤츠</option>
						<option value="Tesla">테슬라</option>
						<option value="Ferrari">페라리</option>
						<option value="BMW">BMW</option>
						<option value="Audi">아우디</option>
						<option value="Maserati">마세라티</option>
						<option value="Bentley">벤틀리</option>
						<option value="Cadillac">캐딜락</option>
					</select>
				</div>
				<div class="field_row item">
					<b>색상</b>
					<select name="car_color" id="car_color" required>
						<option value="" disabled selected>&nbsp;==== 선택하세요 ====&nbsp;</option>
						<option value="black">검정색</option>
						<option value="white">흰색</option>
						<option value="silver">회색</option>
						<option value="red">빨간색</option>
						<option value="blue">파란색</option>
						<option value="yellow">노란색</option>
						<option value="etc">기타</option>
					</select>
				</div>
			</div>
			<div class="field_row">
				<div class="field_row item">
					<b>연료</b>
					<select name="car_fuel" id="car_fuel" required>
						<option value="" disabled selected>&nbsp;==== 선택하세요 ====&nbsp;</option>
						<option value="gasoline">가솔린</option>
						<option value="diesel">디젤</option>
						<option value="hybrid">하이브리드</option>
						<option value="electric">전기</option>
						<option value="hydrogen">수소</option>
					</select>
				</div>
				<div class="field_row item">
					<b>변속기</b>
					<select name="car_transmission" id="car_transmission" required>
						<option value="" disabled selected>&nbsp;==== 선택하세요 ====&nbsp;</option>
						<option value="auto">오토</option>
						<option value="manual">수동</option>
					</select>
				</div>
			</div>
			<div class="field_row">
				<div class="field_row item">
					<b>차종</b>
					<select name="car_type" id="car_type" required>
						<option value="" disabled selected>&nbsp;==== 선택하세요 ====&nbsp;</option>
							<optgroup label="일반">
								<option value="compact">경차</option>
								<option value="semimid">소/준중형</option>
								<option value="mid">중형</option>
								<option value="large">대형</option>
								<option value="sports">스포츠카</option>
							</optgroup>
							<optgroup label="SUV">
								<option value="smallSUV">소형SUV</option>
								<option value="midSUV">중형SUV</option>
								<option value="largeSUV">대형SUV</option>
							</optgroup>
							<optgroup label="RV">
								<option value="smallRV">소형RV</option>
								<option value="largeRV">대형RV</option>
							</optgroup>
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
						<label><input type="checkbox" name="car_content" id="car_content" value="option1">선루프</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option2">HID헤드램프</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option3">전동 트렁크</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option4">전동 사이드미러</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option5">알루미늄휠</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option6">열선핸들</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option7">하이패스	</label>
					</div>
					<div>
						<span>안전</span>
						<label><input type="checkbox" name="car_content" id="car_content" value="option8">에어백(운전석,동승석)</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option9">ABS</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option10">차선이탈경보</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option11">후방카메라</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option12">전방센서</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option13">측면센서</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option14">후방센서</label>
					</div>
					<div>
						<span>편의/멀티미디어</span>
						<label><input type="checkbox" name="car_content" id="car_content" value="option15">크루즈 컨트롤</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option16">전자식 주차브레이크</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option17">HUD</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option18">스마트키</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option19">내비게이션</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option20">자동에어컨</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option21">블루투스</label>
					</div>
					<div>
						<span>시트</span>
						<label><input type="checkbox" name="car_content" id="car_content" value="option22">가죽시트</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option23">전동시트</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option24">열선시트</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option25">통풍시트</label>
						<label><input type="checkbox" name="car_content" id="car_content" value="option26">메모리시트</label>
					</div>
				</div>
			</div>
			<div class="field_column">
				<div class="field_column item">
					<b>사고이력(선택)</b>
					<div>
						<span>구분</span>
						<label><input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="detail1">내차 피해</label>
						<label><input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="detail2">타차 가해</label>
					</div>
					<div>
						<span>특수사고</span>	
						<label><input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="detail3">전손</label>
						<label><input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="detail4">침수(전손,분손)</label>
						<label><input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="detail5">도난</label>
					</div>
					<div>
						<span>수리이력</span>
						<label><input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="detail7">전방</label>
						<label><input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="detail8">도어(운전석측)</label>
						<label><input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="detail9">도어(조수석측)</label>
						<label><input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="detail10">후방</label>
						<label><input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="detail11">루프</label>
						<label><input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="detail12">플로워</label>
						<label><input type="checkbox" name="car_accident_detail" id="car_accident_detail" value="detail13">휠하우스</label>
					</div>
				</div>
			</div>
			<div class="field_row_file">
				<div class="field_row_file item_file">
					<b>메인이미지(필수)</b>
					<input type="file" name="car_image1" id="car_image1">
				</div>
				<div class="field_row_file item_file">
					<b>상세이미지(선택)</b>
					<input type="file" name="car_image2" id="car_image2" multiple/>
				</div>
			</div>
			<div class="productSubmit"><input type="submit" value="상품등록" onclick="carCheck(); return false;"></div>
		</form>
	</div>
</body>
</html>