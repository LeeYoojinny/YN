<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" 
integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="css/admin/myCarOrderList_style.css">
</head>
<script type="text/javascript">
	function checkAll(theForm){
		//체크박스가 하나인지 여러개인지 확인
		if(theForm.approve.length == undefined){//체크박스(remove)의 배열길이가 정의되어 있지 않다면(=장바구니에 상품이 1개라면)
			theForm.approve.checked = theForm.allCheck.checked;			
		}else {//체크박스(remove)의 배열길이가 정의되어 있다면(=장바구니에 상품이 2개이상이면)
			for(var i=0; i<theForm.approve.length; i++){
				theForm.approve[i].checked = theForm.allCheck.checked;
			}			
		}		
	}
	
	function approveCheck_1() {
		var checkboxes = document.getElementsByName('approve');
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
	        var confirmMessage = '선택된 ' + selectedCount + '개 항목을 승인하시겠습니까?';

	        if (confirm(confirmMessage)) {
	            // 사용자가 확인을 누르면 선택된 항목 삭제
	            var param = 'car_ids=' + selectedCarIds.join(',') + '&decision=Y';
	            location.href = 'orderDecision.adm?' + param;
	        }
	    }
	}

	function approveCheck_2(car_id) {
		 var confirmMessage = car_id+ ' 주문건을 승인하시겠습니까?';
		 
		 if(confirm(confirmMessage)) {
			 var param = 'car_ids=' + car_id + '&decision=Y';
			 location.href = 'orderDecision.adm?' + param;
		 }
	}
	
	function refuseCheck(car_id) {
		 var confirmMessage = car_id+ ' 주문건을 거절하시겠습니까?';
		 
		 if(confirm(confirmMessage)) {
			 var param = 'car_ids=' + car_id + '&decision=N';
			 location.href = 'orderDecision.adm?' + param;
		 }
	}
	
</script>
<body>
	<div class="wrap_allCarList">
		<div class="subject"></div>
		<c:if test="${orderList != null }">		
			<form method="post">
			<table>
				<tr id="allApprove">
					<c:if test="${user_category eq 'dealer' || user_category eq 'admin'}">					
						<td colspan="2" id="bt"><button onclick="approveCheck_1(); return false;">선택승인</button></td>
						<td colspan="3" id="bt"></td>
					</c:if>
				</tr>
				<tr id="firstLine">
					<th><input type="checkbox" name="allCheck" onclick="checkAll(this.form)"></th>
					<th>No.</th>
					<th></th>
					<th>주문목록</th>
					<th>상태</th>
					<th>처리</th>
				</tr>
				<c:set var="num" value="${pageInfo.listCount}"></c:set>
				<c:forEach var="order" items="${orderList}" varStatus="status">										
					<tr class="contents">
						<td rowspan="3" id="check_approve"><input type="checkbox" name="approve" value="${order.car_id}"></td>
						<td rowspan="3" id="item_no">${num}</td>
							<c:set var="foundCar" value="false" />
							<c:forEach var="car" items="${carList}">	
								<c:if test="${order.car_id eq car.car_id }">	
									<c:if test="${!foundCar}">	
										<td rowspan="3" id="main_img">
											<a href="carDetailView.usr?car_id=${car.car_id}" class="image-link">
											<img src="upload/carRegist_images/${car.car_image1}" alt="상품상세보기">
											</a>
										</td>
										<td id="explain1">
											<c:forEach var="code" items="${allCode}">
												<c:if test="${code.code_category == 'car_brand'}">
													<c:if test="${car.car_brand == code.code_name}">${code.code_value}</c:if>
												</c:if>
											</c:forEach>					
											&nbsp;${car.car_year}연식 ${car.car_name}
										</td>
										<c:choose>
					                   		<c:when test="${order.order_approve_YN eq 'W' && order.cancel_YN eq 'N'}">														
												<td rowspan="3" id="orderStatus">승인대기</td>
												<td rowspan="3" id="bt">
													<button onclick="approveCheck_2('${car.car_id}'); return false;">승인</button>
													<button onclick="refuseCheck('${car.car_id}');return false;">거절</button>
												</td>
											</c:when>								 
											<c:when test="${order.order_approve_YN eq 'Y'}">
													<td rowspan="3" id="orderStatus">주문승인</td>
													<td rowspan="3" id="bt">
														<button disabled>승인</button>
														<button disabled>거절</button>
													</td>
											</c:when>
											<c:when test="${order.order_approve_YN eq 'N'}">
												<td rowspan="3" id="orderStatus">주문거절</td>
												<td rowspan="3" id="bt"></td>								
											</c:when>
											<c:when test="${order.order_approve_YN eq 'W' && order.cancel_YN eq 'Y'}">
												<td rowspan="3" id="orderStatus">주문취소</td>
												<td rowspan="3" id="bt"></td>
																			
											</c:when>						
										</c:choose>
										</tr>				
										<c:forEach var="pay" items="${allpayList}">
											<c:if test="${pay.ordernum == order.ordernum}">
												<tr class="contents">
													<td id="explain2">결제금액 : <fmt:formatNumber value="${pay.pay_price}" pattern="#,###" />원</td>
												</tr>
											</c:if>
										</c:forEach>		
										<tr class="contents">						
											<td id="explain3">
												<span>주문일자 : <fmt:formatDate value="${order.order_date}" pattern="yyyy-MM-dd HH:mm"/></span>
												<a href="orderDetail.adm?ordernum=${order.ordernum}&car_id=${order.car_id}&display=2"><i class="fa-solid fa-magnifying-glass"></i>주문상세보기</a>
											</td>								
										</tr>
					
										<tr id="space"><td colspan="6"></td></tr>
									</c:if>									
									<c:set var="foundCar" value="true" />
								</c:if>
						</c:forEach>
						<c:set var="num" value="${num-1}"></c:set>
				</c:forEach>
				
			</table>
			</form>

			<div class="pageNum">
				<c:choose> 
					<c:when test="${pageInfo.page <= 1}">[이전]&nbsp;</c:when>
					<c:otherwise><a style="text-decoration:none" href="mySaleCar.adm?page=${pageInfo.page-1}">[이전]&nbsp;</a></c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1" varStatus="loop">
					<c:choose>
						<c:when test="${i == pageInfo.page}">${i}&nbsp;</c:when>
						<c:otherwise><a style="text-decoration:none" href="mySaleCar.adm?page=${i}">${i}</a>&nbsp;</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${pageInfo.page >= pageInfo.maxPage}">[다음]&nbsp;</c:when>
					<c:otherwise><a style="text-decoration:none" href="mySaleCar.adm?page=${pageInfo.page+1}">[다음]&nbsp;</a></c:otherwise>
				</c:choose>
			</div>
		</c:if>
		<c:if test="${orderList == null }">
			<div class="nothing">${user_id}님 판매차량은 주문내역이 없습니다.</div>
		</c:if>
	</div>
</body>
</html>