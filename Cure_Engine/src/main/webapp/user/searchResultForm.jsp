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
	       const maxDistanceInput = document.querySelector('input[name="max_distance"]');
	       const contentInputs = document.querySelectorAll('input[name="car_content"]');
	
	       brandInputs.forEach(input => {
	           input.checked = false;
	       });
	
	       typeInputs.forEach(input => {
	           input.checked = false;
	       });
	
	       colorInputs.forEach(input => {
	           input.checked = false;
	       });
	
	       maxDistanceInput.value = ''; // 주행거리 input 초기화
	
	       contentInputs.forEach(input => {
	           input.checked = false;
	       });
	   }
</script>
<body>
	<div class="wrap_allCarList">
		<div class="subject"></div>
		<c:if test="${user_category eq 'dealer' || user_category eq 'admin'}">
			<div class="rgstButton"><button onclick="location.href='allCarListView.usr'; return false;">검색초기화</button></div>
			<div class="rgstButton"><button onclick="location.href='carRegistForm.adm'; return false;">상품등록</button></div>
		</c:if>
		<c:if test="${user_category eq null || user_category eq 'customer'}">
			<div class="rgstButton"><button onclick="location.href='allCarListView.usr'; return false;">검색초기화</button></div>
		</c:if>
		<c:if test="${resultCar != null }">
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
							<label><input type="checkbox" name="car_brand" id="car_brand" value="benz">벤츠</label>
							<label><input type="checkbox" name="car_brand" id="car_brand" value="tesla">테슬라</label>
							<label><input type="checkbox" name="car_brand" id="car_brand" value="ferrari">페라리</label>
							<label><input type="checkbox" name="car_brand" id="car_brand" value="bmw">BMW</label>
							<label><input type="checkbox" name="car_brand" id="car_brand" value="audi">아우디</label>
							<label><input type="checkbox" name="car_brand" id="car_brand" value="maserati">마세라티</label>
							<label><input type="checkbox" name="car_brand" id="car_brand" value="bentley">벤틀리</label>
							<label><input type="checkbox" name="car_brand" id="car_brand" value="cadillac">캐딜락</label>
						</div>
					</div>
					<div class="detailItem">
						<b>차종</b>
						<div>
							<label><input type="checkbox" name="car_type" id="car_type" value="Car">세단</label>
							<label><input type="checkbox" name="car_type" id="car_type" value="sports">스포츠카</label>
							<label><input type="checkbox" name="car_type" id="car_type" value="SUV">SUV</label>
							<label><input type="checkbox" name="car_type" id="car_type" value="RV">RV</label>
						</div>
					</div>
					<div class="detailItem">
						<b>색상</b>
						<div>
							<label><input type="checkbox" name="car_color" id="car_color" value="black">검정색</label>
							<label><input type="checkbox" name="car_color" id="car_color" value="white">흰색</label>
							<label><input type="checkbox" name="car_color" id="car_color" value="silver">회색</label>
							<label><input type="checkbox" name="car_color" id="car_color" value="red">빨간색</label>
							<label><input type="checkbox" name="car_color" id="car_color" value="blue">파란색</label>
							<label><input type="checkbox" name="car_color" id="car_color" value="yellow">노란색</label>
							<label><input type="checkbox" name="car_color" id="car_color" value="etc">기타</label>
						</div>
					</div>
					<div class="detailItem_row">
						<b>주행거리</b>
						<div>
							<input type="number" name="max_distance">&nbsp;km 이하
						</div>
					</div>
					<!-- <div class="detailItem_option">
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
				<c:forEach var="result" items="${resultCar}" varStatus="status">
					<div class="items">
						<div class="mainImg"><a href="carView.usr?car_id=${result.car_id}"><img src="upload/carRegist_images/${result.car_image1}"></a></div>
						<div class="title">${result.car_brand}&nbsp;${result.car_year}연식 ${result.car_name}</div>
	       	 			<div class="price">${result.car_price}만원</div>
	       	 			<div class="likeQty"><img src="image/carList/like_icon.png">&ensp;${result.car_like}</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${resultCar == null }">
			<div>조회된 상품이 없습니다.</div>
		</c:if>
	</div>
</body>
</html>