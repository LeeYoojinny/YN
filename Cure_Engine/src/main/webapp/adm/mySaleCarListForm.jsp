<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/admin/mySaleCarList_style.css">
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
	
	function checkAll(theForm){
		//체크박스가 하나인지 여러개인지 확인
		if(theForm.remove.length == undefined){//체크박스(remove)의 배열길이가 정의되어 있지 않다면(=장바구니에 상품이 1개라면)
			theForm.remove.checked = theForm.allCheck.checked;			
		}else {//체크박스(remove)의 배열길이가 정의되어 있다면(=장바구니에 상품이 2개이상이면)
			for(var i=0; i<theForm.remove.length; i++){
				theForm.remove[i].checked = theForm.allCheck.checked;
			}			
		}		
	}
</script>
<body>
	<div class="wrap_allCarList">
		<div class="subject"></div>
		<c:if test="${mySaleCarList != null }">
		<form action="carSearch.usr" method="post" name="f">
			<div class="searchPart">
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
							<input type="number" name="car_distance">&nbsp;km 이하
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
					<div class="searchButton">
						<div class="searchButton bt">
							<button onclick="allReset(); return false;">검색조건초기화</button>
							<button type="submit">검색하기</button>
						</div>
					</div>
				</div>
			</div>
			</form>
			<form method="post">
			<table>
				<tr id="allRemove">
					<td colspan="2"><button onclick="removeCheck(); return false;">선택삭제</button></td>
					<td colspan="3"></td>
				</tr>
				<tr id="firstLine">
					<th><input type="checkbox" name="allCheck" onclick="checkAll(this.form)"></th>
					<th>No.</th>
					<th></th>
					<th>판매목록</th>
					<th></th>
				</tr>
				<c:forEach var="myCar" items="${mySaleCarList}" varStatus="status">
					<tr class="contents">
						<td rowspan="3" id="check_remove"><input type="checkbox" name="remove" value="${myCar.car_id}"></td>
						<td rowspan="3" id="item_no">${status.count}</td>
						<td rowspan="3" id="main_img"><a href="carDetailView.usr?car_id=${myCar.car_id}"><img src="upload/carRegist_images/${myCar.car_image1}"></a></td>
						<td id="explain1">
						<c:choose>
							<c:when test="${myCar.car_brand == 'benz'}">벤츠</c:when>
							<c:when test="${myCar.car_brand == 'tesla'}">테슬라</c:when>
							<c:when test="${myCar.car_brand == 'ferrari'}">페라리</c:when>
							<c:when test="${myCar.car_brand == 'bmw'}">BMW</c:when>
							<c:when test="${myCar.car_brand == 'audi'}">아우디</c:when>
							<c:when test="${myCar.car_brand == 'maserati'}">마세라티</c:when>
							<c:when test="${myCar.car_brand == 'bentley'}">벤틀리</c:when>
							<c:when test="${myCar.car_brand == 'cadillac'}">캐딜락</c:when>
						</c:choose>						
						&nbsp;${myCar.car_year}연식 ${myCar.car_name}</td>
						<td rowspan="3" id="bt">
							<button onclick="location.href='carUpdate.adm'; return false;">수정하기</button>
							<button onclick="removeCheck(); return false;">삭제하기</button>
						</td>
					</tr>
					<tr class="contents">
						<td id="explain2">${myCar.car_price}만원</td>
					</tr>
					<tr class="contents">
						<td id="explain3"><img src="image/carList/red_like_icon.png">${myCar.car_like}</td>
					</tr>
					<tr id="space"><td colspan="5"></td></tr>
				</c:forEach>
			</table>
			</form>
		</c:if>
		<c:if test="${mySaleCarList == null }">
			<div class="nothing">${user_id}님의 판매차량이 없습니다.</div>
		</c:if>
	</div>
</body>
</html>