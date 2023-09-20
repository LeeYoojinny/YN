<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/checkout/">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" 
integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link href="css/admin/orderDetail_style.css" rel="stylesheet">
</head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">

function approveCheck(car_id) {
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
	<div class="container">
		<main>
			<div class="py-5 text-center">
				<h2>${carInfo.car_id}주문서</h2>
				<p class="lead">
					<c:forEach var="code" items="${allCode}">
						<c:if test="${code.code_category == 'car_brand'}">
							<c:if test="${carInfo.car_brand == code.code_name}">
					${code.code_value} 
					<c:set var="brand" value="${code.code_value}" />
							</c:if>
						</c:if>
					</c:forEach>
					${carInfo.car_year}연식 ${carInfo.car_name}
				</p>
			</div>
			<div class="row g-5">
				<div class="col-md-5 col-lg-4 order-md-last">
					<h4 class="d-flex justify-content-between align-items-center mb-3">
						<span class="text-primary">결제항목</span>
						<span class="badge bg-primary rounded-pill">4</span>
					</h4>
					<ul class="list-group mb-3">						
						<li class="list-group-item d-flex justify-content-between lh-sm">
							<div>
								<h6 class="my-0">상품명</h6>
								<small class="text-body-secondary">${brand}	${carInfo.car_year}연식 ${carInfo.car_name}</small>
							</div> 
							<c:set var="carPriceWithZeros" value="${carInfo.car_price * 10000}" />
							<span class="text-body-secondary"> 
							<fmt:formatNumber value="${carPriceWithZeros}" pattern="#,###" />							
						</span>
						</li>
						<li class="list-group-item d-flex justify-content-between lh-sm">
							<div>
								<h6 class="my-0">취득세</h6>
								<small class="text-body-secondary">고객님 명의로 변경할때 발생하는
									세금입니다.</small>
							</div>
							<span class="text-body-secondary">
								<fmt:formatNumber value="${carPriceWithZeros * 0.07}" pattern="#,###" />								
							</span>
						</li>
						<li class="list-group-item d-flex justify-content-between lh-sm">
							<div>
								<h6 class="my-0">관리비(매도비)</h6>
								<small class="text-body-secondary"> 자동차 관리법 시행 규칙 제112조에 따른 차량 보관 및 유지보수 비용입니다. </small>
							</div>
							<span class="text-body-secondary">300,000</span>						
						</li>
						<li class="list-group-item d-flex justify-content-between lh-sm">
							<div>
								<h6 class="my-0">탁송료(출발지역:대구)</h6>
								<small class="text-body-secondary"> 차량 받으실 지역 : ${orderInfo.region} </small>
							</div>
							<span class="text-body-secondary"> 
								<fmt:formatNumber value="${orderInfo.deliveryfee}" pattern="#,###" />								
							</span>
						</li>
						<li
							class="list-group-item d-flex justify-content-between bg-body-tertiary">
							<c:if test="${empty orderInfo.coupon_id}">
								<div class="text-success">
									<h6 class="my-0">쿠폰(자동추가)</h6>
									<small>사용 가능한 쿠폰 없습니다.</small>
								</div>
								<span class="text-success">0</span>
							</c:if> 
							<c:if test="${not empty orderInfo.coupon_id}">
								<div class="text-success">
									<h6 class="my-0">쿠폰(자동추가)</h6>									
								</div>
								<span class="text-success">
									<fmt:formatNumber value="${-orderInfo.discount_price}" pattern="#,###" />																
								</span>
							</c:if>
						</li>
						<li class="list-group-item d-flex justify-content-between">
							<span>지불금액(원)</span> 
							<strong> <fmt:formatNumber value="${payInfo.pay_price}" pattern="#,###" /></strong>
						</li>
					</ul>
					<c:if test="${user_category eq 'admin' || user_category eq 'dealer'}">
						<div style="text-align:center;">
							<c:if test="${orderInfo.order_approve_YN eq 'W'}">
								<button class="btn" onclick="approveCheck('${orderInfo.car_id}'); return false;">승인</button>
								<button class="btn" onclick="refuseCheck('${orderInfo.car_id}');return false;">거절</button>						
							</c:if>						
						</div>	
					</c:if>	
					<div style="text-align:center;">
						<c:if test="${param.display eq 1}">						
							<button class="btn" onclick="location.href='myOrderList.cust'">목록보기</button>
						</c:if>
						<c:if test="${param.display eq 2}">						
							<button class="btn" onclick="location.href='orderListView.adm'">목록보기</button>
						</c:if>
					</div>					
				</div>
				<div class="col-md-7 col-lg-8">
					<h4 class="mb-3">신청 정보 확인</h4>
					<form class="needs-validation"  action="order.cust" method="post" name="f">
						<div class="row g-3">
							<div class="col-sm-6">
								<label for="firstName" class="form-label">아이디</label> 
								<input type="text" class="form-control" id="user_id" name="user_id" value="${orderInfo.user_id}" readonly>
							</div>
							<div class="col-sm-6">
								<label for="lastName" class="form-label">성함</label>
								<input type="text" class="form-control" id="user_name" name="user_name" value="${orderInfo.user_name}" readonly>								
							</div>
							<div class="col-sm-6">
								<label for="lastName" class="form-label">휴대폰번호</label>
								<input type="text" class="form-control" id="user_phone"	name="user_phone" value="${orderInfo.user_phone}" readonly>								
							</div>
							<div class="col-sm-6">
								<label for="lastName" class="form-label">이메일</label>
								<input type="text" class="form-control" id="user_email" name="user_email" value="${orderInfo.user_email}" readonly>								
							</div>
							<div class="col-12">
								<label for="lastName" class="form-label">탁송 주소</label>
								<input type="email" class="form-control" id="user_address1"
									name="user_address1" value="(${orderInfo.user_zipcode}) ${orderInfo.user_address1} ${orderInfo.user_address2}" readonly>
							</div>
						</div>
						<hr class="my-4">
						<h4 class="mb-3">결제수단</h4>
						<div class="my-3">
							<c:if test="${orderInfo.payment eq 1}">
								<div class="form-check" style="padding-left: 0;">
									<i class="fa-regular fa-credit-card" style="padding-right: 10px;"></i>계좌이체(현금)
								</div>
							</c:if>
							<c:if test="${orderInfo.payment eq 2}">
								<div class="form-check" style="padding-left: 0;">
									<i class="fa-regular fa-credit-card" style="padding-right: 10px;"></i>신용카드
								</div>						
							</c:if>
						</div>
						<c:if test="${orderInfo.payment eq 1}">
						<div class="row gy-3" id="account">						
							<div class="col-md-6" id="accountInfo1">
								<!-- 초기에 보이도록 설정 -->
								<div class="form-group">
									<label for="cc-name" class="form-label">입금계좌</label>
									<input type="text" class="form-control" value="신한 123456-56-789000 (큐어엔진)" readonly disabled>
								</div>
							</div>						
							<div class="col-md-6" id="accountInfo2">
								<div class="form-group">
									<label for="cc-number" class="form-label">예금주명</label>
									<input type="text" class="form-control" name="pay_depositor_name" value="${payInfo.pay_depositor_name}" readonly>
								</div>								
							</div>
						</div>
						</c:if>
						<c:if test="${orderInfo.payment eq 2}">	
							<div class="row gy-3" id="creditCard">
					            <div class="col-md-6" id="creditInfo1" >
					              <label for="cc-name" class="form-label">이름</label>
					              <input type="text" class="form-control" name="pay_creditcard_name" value="${payInfo.pay_creditcard_name}" readonly>					              
					            </div>
					
					            <div class="col-md-6" id="creditInfo2" >
					              <label for="cc-number" class="form-label">카드번호</label>
					              <input type="text" class="form-control" name="pay_creditcard_num" value="${payInfo.pay_creditcard_num}" readonly>
					            </div>
					
					            <div class="col-md-6" id="creditInfo3" >
					              <label for="cc-expiration" class="form-label">유효기간</label>
					              <input type="text" class="form-control" name="pay_creditcard_date" value="${payInfo.pay_creditcard_date}" readonly>
					            </div>
					
					            <div class="col-md-6" id="creditInfo4" >
					              <label for="cc-cvv" class="form-label">CVV</label>
					              <input type="text" class="form-control" name="pay_creditcard_cvc" value="${payInfo.pay_creditcard_cvc}" readonly>
					            </div>
					          </div>
				          </c:if>
						<hr class="my-4">						
					</form>
				</div>
			</div>
		</main>
	</div>
</body>
</html>