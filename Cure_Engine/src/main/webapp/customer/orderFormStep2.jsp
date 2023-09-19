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
<link href="css/customer/orderForm2_style.css" rel="stylesheet">
</head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">

//결제 방법에 따라 값 확인
function orderCheck() {
	//이름 정규화 공식
	const regName = /^[가-힣a-zA-Z]{2,}$/;
	
	//신용카드 명의자명 정규식 공식
	const regCreditName = /^[A-Za-z\s_-]+$/;
	
	//신용카드 번호 정규화 공식
	const regCreditNum = /^\d{16}$/;
	
	//신용카드 유효기간 정규화 공식
	const regCreditExpire = /^(0[1-9]|1[0-2])\/(2[2-9]|3[0-9])$/;
	
	//신용카드 cvv 정규화 공식
	const regCvv = /^\d{3}$/;
	
	const cashRadio = document.getElementById('cash');
	const creditRadio = document.getElementById('credit');
	
	if (cashRadio.checked) {		
		if(!document.f.pay_depositor_name.value.trim()) {
			alert("계좌이체 예금주명 입력해주세요.");
			document.f.pay_depositor_name.focus();
			return false;
		}else if(!regName.test(document.f.pay_depositor_name.value)) {
			alert("이름을 한글 또는 영문으로만 입력해주세요.");
			document.f.pay_depositor_name.select();
			return false;
		}
	}else if(creditRadio.checked){
		if(!document.f.pay_creditcard_name.value.trim()){
			alert("카드 명의자명을 입력해주세요.");
			document.f.pay_depositor_name.focus();
			return false;
		}else if(!regCreditName.test(document.f.pay_creditcard_name.value)) {
			alert('유효한 명의자명을 입력해주세요.');
			document.f.pay_depositor_name.select();
            return false; 
		}
		
		if(!document.f.pay_creditcard_num.value.trim()){
			alert("카드 번호를 입력해주세요.");
			document.f.pay_creditcard_num.focus();
			return false;
		}else if(!regCreditNum.test(document.f.pay_creditcard_num.value)) {
			alert('유효한 신용카드 번호를 입력해주세요.');
			document.f.pay_creditcard_num.select();
            return false; 
		}
		
		if(!document.f.pay_creditcard_date.value.trim()){
			alert("유효기간을 입력해주세요.");
			document.f.pay_creditcard_date.focus();
			return false;
		}else if(!regCreditExpire.test(document.f.pay_creditcard_date.value)) {
			alert('유효한 유효기간을 입력해주세요 (MM/YY 형식).');
			document.f.pay_creditcard_date.select();
            return false; 
		}
		
		if(!document.f.pay_creditcard_cvc.value.trim()){
			alert("CVV 번호를 입력해주세요.");
			document.f.pay_creditcard_cvc.focus();
			return false;
		}else if(!regCvv.test(document.f.pay_creditcard_cvc.value)) {
			alert('유효한 CVV 번호를 입력해주세요 (숫자3자리).');
			document.f.pay_creditcard_cvc.select();
            return false; 
		}
		
	}	
	
	document.f.submit();		
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
			
			<%-- 진행상황 --%>
			<div class="progressBar">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item step1">주문서 작성</li>
			    <li class="breadcrumb-item active step2" aria-current="page">금액확인 및 결제</li>
			    <li class="breadcrumb-item active step3" aria-current="page">접수완료</li>
			  </ol>
			</div>

			<div class="row g-5">
				<div class="col-md-5 col-lg-4 order-md-last">
					<h4 class="d-flex justify-content-between align-items-center mb-3">
						<span class="text-primary">결제항목</span>
						<span class="badge bg-primary rounded-pill">4</span>
					</h4>
					<ul class="list-group mb-3">
						<c:set var="total" value="0" />
						<li class="list-group-item d-flex justify-content-between lh-sm">
							<div>
								<h6 class="my-0">상품명</h6>
								<small class="text-body-secondary">${brand}	${carInfo.car_year}연식 ${carInfo.car_name}</small>
							</div> 
							<c:set var="carPriceWithZeros" value="${carInfo.car_price * 10000}" />
							<span class="text-body-secondary"> 
							<fmt:formatNumber value="${carPriceWithZeros}" pattern="#,###" />
							<c:set var="total" value="${total + carPriceWithZeros}" />
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
								<c:set var="total" value="${total + (carPriceWithZeros * 0.07)}" />
							</span>
						</li>
						<li class="list-group-item d-flex justify-content-between lh-sm">
							<div>
								<h6 class="my-0">관리비(매도비)</h6>
								<small class="text-body-secondary"> 자동차 관리법 시행 규칙 제112조에 따른 차량 보관 및 유지보수 비용입니다. </small>
							</div>
							<span class="text-body-secondary">300,000</span>
							<c:set var="total" value="${total + 300000}" />
						</li>
						<li class="list-group-item d-flex justify-content-between lh-sm">
							<c:forEach var="fee" items="${allFee}">
								<c:set var="user_region" value="${fn:substring(param.user_address1, 0, 2)}" />
								<c:set var="fee_region" value="${fn:substring(fee.region, 0, 2)}" />
								<c:if test="${fee_region == user_region}">
									<c:set var="user_fee" value="${fee.fee}" />
									<div>
										<h6 class="my-0">탁송료(출발지역:대구)</h6>
										<small class="text-body-secondary"> 차량 받으실 지역 : ${fee.region} </small>
										<c:set var="region" value="${fee.region}" />
									</div>
									<span class="text-body-secondary"> 
										<fmt:formatNumber value="${user_fee}" pattern="#,###" /> 
										<c:set var="total" value="${total + user_fee}" />
									</span>
								</c:if>
							</c:forEach>
						</li>
						<li
							class="list-group-item d-flex justify-content-between bg-body-tertiary">
							<c:if test="${myCoupon eq null}">
								<div class="text-success">
									<h6 class="my-0">쿠폰(자동추가)</h6>
									<small>사용 가능한 쿠폰 없습니다.</small>
								</div>
								<span class="text-success">0</span>
							</c:if> <c:if test="${myCoupon ne null}">
								<div class="text-success">
									<h6 class="my-0">쿠폰(자동추가)</h6>
									<small>${myCoupon.coupon_name}</small>
								</div>
								<span class="text-success"> 
									<c:set var="discount" value="${user_fee*(myCoupon.discount_rate/ 100)}" />
									<fmt:formatNumber value="${-discount}" pattern="#,###" />
									<c:set var="discount_price" value="${discount}" />
									<c:set var="total" value="${total - discount}" />
								</span>
							</c:if>
						</li>
						<li class="list-group-item d-flex justify-content-between">
							<span>지불하실	금액(원)</span> 
							<strong> <fmt:formatNumber value="${total}" pattern="#,###" /></strong>
						</li>
					</ul>
				</div>
				<div class="col-md-7 col-lg-8">
					<h4 class="mb-3">신청 정보 확인</h4>
					<form class="needs-validation"  action="order.cust" method="post" name="f">
						<input type="hidden" name="car_price" value="${carInfo.car_price * 10000}">
						<input type="hidden" name="car_id" value="${carInfo.car_id}">
						<input type="hidden" name="dealer_id" value="${carInfo.dealer_id}">						
						<input type="hidden" name="discount_price" value="${discount_price}">
						<input type="hidden" name="coupon_id" value="${myCoupon.coupon_id}">
						<input type="hidden" name="region" value="${region}">
						<input type="hidden" name="deliveryfee" value="${user_fee}">
						<input type="hidden" name="total_price" value="${total}">
						<div class="row g-3">
							<div class="col-sm-6">
								<label for="firstName" class="form-label">아이디</label> 
								<input type="text" class="form-control" id="user_id" name="user_id" value="${userInfo.user_id}" readonly>
							</div>
							<div class="col-sm-6">
								<label for="lastName" class="form-label">성함</label>
								<input type="text" class="form-control" id="user_name" name="user_name" value="${param.user_name}" readonly>
								<div class="invalid-feedback">성함을 입력해주세요.</div>
							</div>

							<div class="col-sm-6">
								<label for="lastName" class="form-label">휴대폰번호</label>
								<input type="text" class="form-control" placeholder="(-)없이 숫자만 입력해주세요." id="user_phone"
									name="user_phone" value="${param.user_phone}" readonly>
								<div class="invalid-feedback">연락 가능한 전화번호를 입력해주세요.</div>
							</div>
							<div class="col-sm-6">
								<label for="lastName" class="form-label">이메일</label>
								<input type="text" class="form-control" id="user_email" name="user_email" value="${param.user_email}" readonly>
								<div class="invalid-feedback">이메일 주소를 입력해주세요.</div>
							</div>

							<div class="col-12">
								<label for="username" class="form-label">탁송받으실 주소</label>
								<div class="input-group has-validation">
									<input type="text" class="form-control" id="user_zipcode"
										name="user_zipcode" value="${param.user_zipcode}" readonly>
									<span class="input-group-text col-3 d-flex justify-content-center"
										onclick="findAddr(); return false;">우편번호찾기</span>
								</div>
							</div>
							<div class="col-12">
								<input type="email" class="form-control" id="user_address1"
									name="user_address1" value="${param.user_address1}" readonly>
							</div>
							<div class="col-12">
								<input type="text" class="form-control" id="user_address2"
									name="user_address2" value="${param.user_address2}" readonly>
							</div>							
						</div>

						<hr class="my-4">

						<h4 class="mb-3">결제방법</h4>
						<div class="my-3">
							<div class="form-check">
								<input id="cash" name="pay_by" value="1" type="radio"
									class="form-check-input" checked required> 
								<label class="form-check-label" for="cash">계좌이체(현금)</label>
							</div>
							<div class="form-check">
								<input id="credit" name="pay_by" value="2" type="radio" class="form-check-input" required>
								<label class="form-check-label" for="credit">신용카드</label>
							</div>
						</div>

						<div class="row gy-3" id="account">
							<div class="col-md-6" id="accountInfo1">
								<!-- 초기에 보이도록 설정 -->
								<div class="form-group">
									<label for="cc-name" class="form-label">입금계좌</label>
									<input type="text" class="form-control" id="cc-name" value="신한 123456-56-789000 (큐어엔진)" readonly disabled>
								</div>
							</div>
							<div class="col-md-6" id="accountInfo2">
								<div class="form-group">
									<label for="cc-number" class="form-label">예금주명</label>
									<input type="text" class="form-control" id="pay_depositor_name" name="pay_depositor_name" placeholder="">
								</div>
								<small class="text-body-secondary">입금자명을 정확히 입력해주세요.</small>
							</div>
						</div>
						<div class="row gy-3" id="creditCard">
				            <div class="col-md-6" id="creditInfo1" style="display: none;">
				              <label for="cc-name" class="form-label">이름</label>
				              <input type="text" class="form-control" id="pay_creditcard_name" name="pay_creditcard_name">
				              <small class="text-body-secondary">카드에 적힌 명의자 영문명</small>
				            </div>
				
				            <div class="col-md-6" id="creditInfo2" style="display: none;">
				              <label for="cc-number" class="form-label">카드번호</label>
				              <input type="text" class="form-control" id="pay_creditcard_num" name="pay_creditcard_num" placeholder="공백없이 숫자 16자리 입력해주세요." >
				            </div>
				
				            <div class="col-md-3" id="creditInfo3" style="display: none;">
				              <label for="cc-expiration" class="form-label">유효기간</label>
				              <input type="text" class="form-control" id="pay_creditcard_date" name="pay_creditcard_date" placeholder="MM/YY" >
				            </div>
				
				            <div class="col-md-3" id="creditInfo4" style="display: none;">
				              <label for="cc-cvv" class="form-label">CVV</label>
				              <input type="text" class="form-control" id="pay_creditcard_cvc" name="pay_creditcard_cvc" placeholder="숫자 3자리" >
				            </div>
				          </div>
						<hr class="my-4">
						<div class="form-check text-center">
							<button class="btn" id="prev" onclick="history.back(); return false;">
							이전</button>
							<button class="btn" id="payButton" onclick="orderCheck();return false;">결제</button>
						</div>
					</form>
				</div>
			</div>
		</main>
	</div>
	<script src="https://cdn.portone.io/v2/browser-sdk.js"></script>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
	<script>
	//pay_by 값이 변경될 때 이를 감지하는 이벤트 핸들러 등록
	const cashRadio = document.getElementById('cash');
	const creditRadio = document.getElementById('credit');
	const accountInfo1 = document.getElementById('accountInfo1');
	const accountInfo2 = document.getElementById('accountInfo2');
	const creditInfo1 = document.getElementById('creditInfo1');
	const creditInfo2 = document.getElementById('creditInfo2');
	const creditInfo3 = document.getElementById('creditInfo3');
	const creditInfo4 = document.getElementById('creditInfo4');

	cashRadio.addEventListener('change', function() {
		if (this.checked) {
			accountInfo1.style.display = 'block'; // 계좌이체 선택 시 보이게 함
			accountInfo1.disabled = false;
			accountInfo2.style.display = 'block'; // 계좌이체 선택 시 보이게 함
			accountInfo2.disabled = false;
			creditInfo1.style.display = 'none'; // 계좌이체 선택 시 숨김
			creditInfo1.disabled = true;
			creditInfo2.style.display = 'none'; // 계좌이체 선택 시 숨김
			creditInfo2.disabled = true;
			creditInfo3.style.display = 'none'; // 계좌이체 선택 시 숨김
			creditInfo3.disabled = true;
			creditInfo4.style.display = 'none'; // 계좌이체 선택 시 숨김
			creditInfo4.disabled = true;
		}
	});

	creditRadio.addEventListener('change', function() {
		if (this.checked) {
			accountInfo1.style.display = 'none'; // 신용카드 선택 시 숨김
			accountInfo1.disabled = true;
			accountInfo2.style.display = 'none'; // 신용카드 선택 시 숨김
			accountInfo2.disabled = true;
			creditInfo1.style.display = 'block'; // 신용카드 선택 시 보이게 함
			creditInfo1.disabled = false;
			creditInfo2.style.display = 'block'; // 신용카드 선택 시 보이게 함
			creditInfo2.disabled = false;
			creditInfo3.style.display = 'block'; // 신용카드 선택 시 보이게 함
			creditInfo3.disabled = false;
			creditInfo4.style.display = 'block'; // 신용카드 선택 시 보이게 함
			creditInfo4.disabled = false;
		}
	});
	
	
	
	
	/* 결제 API를 이용한 결제처리 (구현중....) */
/* 	var IMP = window.IMP; 
    IMP.init("imp13627713"); 
  
    var today = new Date();   
    var hours = today.getHours(); // 시
    var minutes = today.getMinutes();  // 분
    var seconds = today.getSeconds();  // 초
    var milliseconds = today.getMilliseconds();
    var makeMerchantUid = hours +  minutes + seconds + milliseconds;
    
    function requestPay(carName, totalAmount,email) {
        IMP.request_pay({
            pg : 'kcp.A52CY',
            pay_method : 'card',
            merchant_uid: "IMP"+makeMerchantUid, 
            name : carName,
            amount : totalAmount,
            buyer_email : email,
            buyer_name : '아임포트 기술지원팀',
            buyer_tel : '010-1234-5678',
            buyer_addr : '서울특별시 강남구 삼성동',
            buyer_postcode : '123-456',
            display: {
                card_quota: [48]  // 할부개월 3개월까지 활성화
            }
        }, function (rsp) { // callback
            if (rsp.success) {
                console.log(rsp);
            } else {
                console.log(rsp);
            }
        });
    }
	 */
/* 	//결제 api를 사용하기 위해 db 값 받아오기
	document
			.getElementById("payButton")
			.addEventListener(
					"click",
					function() {
						// 필요한 매개변수 값을 가져와서 함수에 전달합니다.
						var carName = '<c:out value="${carInfo.car_name}" />';
  						var totalAmount = '<c:out value="${total}" />';
						var userEmail = document.getElementById("user_email").value;
						var userName = document.getElementById("user_name").value;
						var userPhone = document.getElementById("user_phone").value;
						var userAddr = document.getElementById("user_address1").value;
						var userZipcode = document
								.getElementById("user_zipcode").value;

						requestPayment(carName, totalAmount, userEmail, userName,
								userPhone, userAddr, userZipcode);
					}); */

	/* function requestPayment(carName, totalAmount, userEmail, userName, userPhone,
			userAddr, userZipcode) {
		var IMP = window.IMP;

		IMP.init('imp13627713');
		IMP.request_pay({
			pg : "html5_inicis",
			pay_method : 'card',
			merchant_uid : userName + new Date().getTime(),
			name : carName,
			amount : totalAmount,
			buyer_email : userEmail,
			buyer_name : userName,
			buyer_tel : userPhone,
			buyer_addr : userAddr,
			buyer_postcode : userZipcode
		m_redirect_url : 'redirect url'		
		}, function(rsp) {
			if (rsp.success) {
				document.forms['f'].submit();
			} else {
				var msg = '결제에 실패하였습니다.';
				rsp.error_msg;
			}
		});
	} */
	</script>

</body>
</html>