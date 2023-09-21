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

	function checkAll(theForm){
		//체크박스가 하나인지 여러개인지 확인
		if(theForm.re_regist.length == undefined){//체크박스(remove)의 배열길이가 정의되어 있지 않다면(=장바구니에 상품이 1개라면)
			theForm.re_regist.checked = theForm.allCheck.checked;			
		}else {//체크박스(remove)의 배열길이가 정의되어 있다면(=장바구니에 상품이 2개이상이면)
			for(var i=0; i<theForm.re_regist.length; i++){
				theForm.re_regist[i].checked = theForm.allCheck.checked;
			}			
		}		
	}
	
	function re_registCheck_1() {
		var checkboxes = document.getElementsByName('re_regist');
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
	        var confirmMessage = '선택된 ' + selectedCount + '개 항목을 재등록하시겠습니까?';

	        if (confirm(confirmMessage)) {
	            // 사용자가 확인을 누르면 선택된 항목 삭제
	            var param = 'car_ids=' + selectedCarIds.join(',');
	            location.href = 'carReRegist.adm?' + param;
	        }
	    }
	}
	
	function re_registCheck_2(car_id) {
		 var confirmMessage = car_id+ ' 재등록 처리 하시겠습니까?';
		 
		 if(confirm(confirmMessage)) {
			 var param = 'car_ids=' + car_id;
			 location.href="carReRegist.adm?"+car_id;
		 }
	}

</script>
<body>
	<div class="wrap_allCarList">
		<div class="subject"></div>
		<c:if test="${removeCarList != null }">		
			<form method="post">
			<table>
				<tr id="allRemove">
					<td id="bt" colspan="2"><button onclick="re_registCheck_1(); return false;">선택재등록</button></td>					
					<td colspan="3"></td>
					<td id="bt"><button onclick="location.href='allSaleCar.adm'; return false;">판매차량목록</button></td>
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
				<c:forEach var="revCar" items="${removeCarList}" varStatus="status">
					<tr class="contents">
						<td rowspan="3" id="check_remove"><input type="checkbox" name="re_regist" value="${revCar.car_id}"></td>
						<td rowspan="3" id="item_no">${startNo + status.count-1}</td>
						<td rowspan="3">${revCar.dealer_id}&nbsp;&nbsp;</td>
						<td rowspan="3" id="main_img"><a href="carDetailView.usr?car_id=${revCar.car_id}"><img src="upload/carRegist_images/${revCar.car_image1}"></a></td>
						
						<td id="explain1">
						<c:forEach var="code" items="${allCode}">
							<c:if test="${code.code_category == 'car_brand'}">
								<c:if test="${revCar.car_brand == code.code_name}">${code.code_value}</c:if>
							</c:if>
						</c:forEach>					
						&nbsp;${revCar.car_year}연식 ${revCar.car_name}</td>
						<td rowspan="3" id="bt">
							<button onclick="re_registCheck('${revCar.car_id}')">재등록</button>
							
						</td>
					</tr>
					<tr class="contents">
						<td id="explain2"><fmt:formatNumber value="${revCar.car_price}" pattern="#,###" />만원</td>
					</tr>
					<tr class="contents">
						<td id="explain3"><img src="image/carList/red_like_icon.png">${revCar.car_like}</td>
					</tr>
					<tr id="space"><td colspan="5"></td></tr>
				</c:forEach>
			</table>
			</form>
			<div class="page">
				<c:choose> 
					<c:when test="${pageInfo.page <= 1}">[이전]&nbsp;</c:when>
					<c:otherwise><a style="text-decoration:none" href="removeCarList.adm?page=${pageInfo.page-1}">[이전]&nbsp;</a></c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1" varStatus="loop">
					<c:choose>
						<c:when test="${i == pageInfo.page}">${i}&nbsp;</c:when>
						<c:otherwise><a style="text-decoration:none" href="removeCarList.adm?page=${i}">${i}</a>&nbsp;</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${pageInfo.page >= pageInfo.maxPage}">[다음]&nbsp;</c:when>
					<c:otherwise><a style="text-decoration:none" href="removeCarList.adm?page=${pageInfo.page+1}">[다음]&nbsp;</a></c:otherwise>
				</c:choose>
			</div>
		</c:if>
		<c:if test="${removeCarList == null }">
			<div class="nothing">${user_id}님의 판매차량이 없습니다.</div>
		</c:if>
	</div>
</body>
</html>