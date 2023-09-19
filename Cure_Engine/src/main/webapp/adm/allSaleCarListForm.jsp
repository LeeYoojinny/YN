<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	
	function removeCheck_1() {
		var checkboxes = document.getElementsByName('remove');
	    var selectedCount = 0;
	    var selectedCarIds = [];

	    for (var i = 0; i < checkboxes.length; i++) {
	        if (checkboxes[i].checked) {
	            selectedCount++;
	            selectedCarIds.push(checkboxes[i].value);
	        }
	    }

	    if (selectedCount === 0) {
	        // 체크된 항목이 없으면 경고 메시지 표시 후 작업 취소
	        alert('선택된 항목이 없습니다.');
	        return false;
	    } else {
	        // 체크된 항목이 있으면 삭제 여부를 물어보고 삭제 작업 실행
	        var confirmMessage = '선택된 ' + selectedCount + '개 항목을 삭제하시겠습니까?';

	        if (confirm(confirmMessage)) {
	            // 사용자가 확인을 누르면 선택된 항목 삭제
	            var param = 'car_ids=' + selectedCarIds.join(',');
	            location.href = 'myCarRemove.adm?' + param;
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
	
</script>
<body>
	<div class="wrap_allCarList">
		<div class="subject"></div>
		<c:if test="${allSaleCarList != null }">
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
					<td colspan="2"><button onclick="removeCheck_1(); return false;">선택삭제</button></td>
					<td colspan="3"></td>
				</tr>
				<tr id="firstLine">
					<th><input type="checkbox" name="allCheck" onclick="checkAll(this.form)"></th>
					<th>No.</th>
					<th>딜러</th>
					<th></th>
					<th>판매목록</th>
					<th></th>
				</tr>
				<c:set var="startNo" value="${(pageInfo.page - 1) * 5 + 1}" />
				<c:forEach var="allCar" items="${allSaleCarList}" varStatus="status">
					<tr class="contents">
						<td rowspan="3" id="check_remove"><input type="checkbox" name="remove" value="${allCar.car_id}"></td>
						<td rowspan="3" id="item_no">${startNo + status.count-1}</td>
						<td rowspan="3">${allCar.dealer_id}&nbsp;&nbsp;</td>
						<td rowspan="3" id="main_img">
							<a href="carDetailView.usr?car_id=${allCar.car_id}" class="image-link">
							<img src="upload/carRegist_images/${allCar.car_image1}" >
							<c:if test="${allCar.sale_YN eq 'W'}">
						        <div class="sold-out-overlay">
						            <p class="sold-out-text">예약중</p>
						        </div>
						    </c:if>
						    <c:if test="${allCar.sale_YN eq 'N'}">
						        <div class="sold-out-overlay">
						            <p class="sold-out-text">판매완료</p>
						        </div>
						    </c:if>
							</a>
						</td>
						
						<td id="explain1">
						<c:forEach var="code" items="${allCode}">
							<c:if test="${code.code_category == 'car_brand'}">
								<c:if test="${allCar.car_brand == code.code_name}">${code.code_value}</c:if>
							</c:if>
						</c:forEach>					
						&nbsp;${allCar.car_year}연식 ${allCar.car_name}</td>
						<td rowspan="3" id="bt">							
							<c:if test="${allCar.sale_YN eq 'Y'}">
								<button onclick="location.href='carUpdateForm.adm?car_id=${allCar.car_id}'; return false;">수정하기</button>
							<button onclick="removeCheck_2('${allCar.car_id}');return false;">삭제하기</button>
							</c:if>
							<c:if test="${allCar.sale_YN eq 'W' || allCar.sale_YN eq 'N'}">
								<button disabled>시승예약</button>
								<button disabled>주문하기</button>
							</c:if>	
						</td>
					</tr>
					<tr class="contents">
						<td id="explain2"><fmt:formatNumber value="${allCar.car_price}" pattern="#,###" />만원</td>
					</tr>
					<tr class="contents">
						<td id="explain3"><img src="image/carList/red_like_icon.png">${allCar.car_like}</td>
					</tr>
					<tr id="space"><td colspan="5"></td></tr>
				</c:forEach>
			</table>
			</form>
			<div class="page">
				<c:choose> 
					<c:when test="${pageInfo.page <= 1}">[이전]&nbsp;</c:when>
					<c:otherwise><a style="text-decoration:none" href="allSaleCar.adm?page=${pageInfo.page-1}">[이전]&nbsp;</a></c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1" varStatus="loop">
					<c:choose>
						<c:when test="${i == pageInfo.page}">${i}&nbsp;</c:when>
						<c:otherwise><a style="text-decoration:none" href="allSaleCar.adm?page=${i}">${i}</a>&nbsp;</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${pageInfo.page >= pageInfo.maxPage}">[다음]&nbsp;</c:when>
					<c:otherwise><a style="text-decoration:none" href="allSaleCar.adm?page=${pageInfo.page+1}">[다음]&nbsp;</a></c:otherwise>
				</c:choose>
			</div>
		</c:if>
		<c:if test="${allSaleCarList == null }">
			<div class="nothing">${user_id}님의 판매차량이 없습니다.</div>
		</c:if>
	</div>
</body>
</html>