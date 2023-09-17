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
<link href="css/customer/orderForm_style.css" rel="stylesheet">
</head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	/* 주소 검색 함수 */
	function findAddr() {
	    new daum.Postcode({
	        oncomplete : function(data) {
	            document.getElementById("user_zipcode").value = data.zonecode;
	            let roadAddr = data.roadAddress;
	            let jibunAddr = data.jibunAddress;
	            
	            if (roadAddr !== '') {
	                document.getElementById("user_address1").value = roadAddr;
	            } else if (jibunAddr !== '') {
	                document.getElementById("user_address1").value = jibunAddr;
	            }
	            
	            document.getElementById("user_address2").focus();
	        }
	    }).open();
	}
</script>
<body>
	<div class="container">
  <main>
    <div class="py-5 text-center">
      <h2>${carInfo.car_id} 주문서</h2>
      <p class="lead">
	      <c:forEach var="code" items="${allCode}">
			<c:if test="${code.code_category == 'car_brand'}">
				<c:if test="${carInfo.car_brand == code.code_name}">
					${code.code_value} 
					<c:set var="brand" value="${code.code_value}" />
				</c:if>
			</c:if>
		  </c:forEach>
      ${carInfo.car_year}연식 ${carInfo.car_name}</p>
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
              <small class="text-body-secondary">${brand} ${carInfo.car_year}연식 ${carInfo.car_name}</small>
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
              <small class="text-body-secondary">고객님 명의로 변경할때 발생하는 세금입니다.</small>
            </div>
            <span class="text-body-secondary">
            	<fmt:formatNumber value="${carPriceWithZeros * 0.07}" pattern="#,###" />
            	<c:set var="total" value="${total + (carPriceWithZeros * 0.07)}" />
            </span>
          </li>
          <li class="list-group-item d-flex justify-content-between lh-sm">
            <div>
              <h6 class="my-0">관리비(매도비)</h6>
              <small class="text-body-secondary">
              자동차 관리법 시행 규칙 제 112조에 따른 차량 보관 및 유지보수 비용입니다.
              </small>
            </div>
            <span class="text-body-secondary">300,000</span>
            <c:set var="total" value="${total + 300000}" />
          </li>
          <li class="list-group-item d-flex justify-content-between lh-sm">
          <c:forEach var="fee" items="${allFee}">
          <c:set var="user_region" value="${fn:substring(userInfo.user_address1, 0, 2)}" />
          <c:set var="fee_region" value="${fn:substring(fee.region, 0, 2)}" />
            <c:if test="${fee_region == param.feeRegion}">
            	<c:set var="user_fee" value="${fee.fee}" />
	            <div>
	              <h6 class="my-0">탁송료(출발지역:대구)</h6>                          
	              <small class="text-body-secondary">
	              차량 받으실 지역 : ${fee.region} 
	              </small>
	            </div>
	            <span class="text-body-secondary">
	            	<fmt:formatNumber value="${user_fee}" pattern="#,###" />
	            	<c:set var="total" value="${total + user_fee}" />
	            </span>
	         </c:if>
	      </c:forEach>     
          </li>
          <li class="list-group-item d-flex justify-content-between bg-body-tertiary">
            <c:if test="${myCoupon eq null}">
            	<div class="text-success">            
              		<h6 class="my-0">쿠폰(자동추가)</h6>              
              		<small>사용 가능한 쿠폰 없습니다.</small>
              	</div>
            	<span class="text-success">0</span>
            </c:if>
            <c:if test="${myCoupon ne null}">
            	<div class="text-success">            
              		<h6 class="my-0">쿠폰(자동추가)</h6>   
              		<small>${myCoupon.coupon_name}</small>
              	</div>
              	<span class="text-success">
              		<c:set var="discount" value="${user_fee*(myCoupon.discount_rate/ 100)}" />
              		<fmt:formatNumber value="${-discount}" pattern="#,###" />
              		<c:set var="total" value="${total - discount}" />        	
              	</span>
             </c:if> 	
          </li>            
          <li class="list-group-item d-flex justify-content-between">
            <span>지불하실 금액(원)</span>
            <strong>
            	<fmt:formatNumber value="${total}" pattern="#,###" />
            </strong>
          </li>
        </ul>
      </div>
      <div class="col-md-7 col-lg-8">
        <h4 class="mb-3">신청 정보</h4>
        <form class="needs-validation" action="order.cust" method="post" name="f">
        <input type="hidden" name="car_id" value="${carInfo.car_id}">
        <input type="hidden" name="car_id" value="${carInfo.car_id}">
        <input type="hidden" name="coupon_id" value="${myCoupon.coupon_id}">
          <div class="row g-3">
            <div class="col-sm-6">
              <label for="firstName" class="form-label">아이디</label>
              <input type="text" class="form-control" id="user_id" name="user_id" value="${userInfo.user_id}" readonly>
            </div>
            <div class="col-sm-6">
              <label for="lastName" class="form-label">성함</label>
              <input type="text" class="form-control" id="user_name" name="user_name" value="${userInfo.user_name}" required>
              <div class="invalid-feedback">
                성함을 입력해주세요.
              </div>
            </div>
            
            <div class="col-sm-6">
              <label for="lastName" class="form-label">휴대폰번호</label>
              <input type="text" class="form-control" placeholder="(-)없이 숫자만 입력해주세요."
              id="user_phone" name="user_phone" value="${userInfo.user_phone}" required>
              <div class="invalid-feedback">
                연락 가능한 전화번호를 입력해주세요.
              </div>
            </div>
            <div class="col-sm-6">
              <label for="lastName" class="form-label">이메일</label>
              <input type="text" class="form-control" id="user_email" name="user_email" value="${userInfo.user_email}" required>
              <div class="invalid-feedback">
                이메일 주소를 입력해주세요.
              </div>
            </div>
			
            <div class="col-12">
              <label for="username" class="form-label">탁송받으실 주소</label>
              <div class="input-group has-validation">                
                <input type="text" class="form-control" id="user_zipcode" name="${userInfo.user_zipcode}" placeholder="우편번호만 입력"  required>
                <span class="input-group-text col-3 d-flex justify-content-center" 
                onclick="findAddr(); return false;"  style="cursor: pointer;">
                	우편번호찾기
                </span>
              <div class="invalid-feedback">
                  우편번호찾기를 통해 우편번호 입력해주세요.
                </div>
              </div>
            </div>
            <div class="col-12">             
              <input type="email" class="form-control" id="user_address1" name="user_address1" placeholder="주소" readonly>
            </div>
            <div class="col-12">              
              <input type="text" class="form-control" id="user_address2" name="user_address2" 
              placeholder="상세주소">
            </div>
          <hr class="my-4">
		</div>
          <div class="form-check">
            <input type="checkbox" class="form-check-input" id="order_agree">
            <label class="form-check-label" for="same-address">
            주문자 정보 및 탁송지역을 모두 확인하였습니다.
            </label>
          </div>

          <hr class="my-4">

          <h4 class="mb-3">결제방법</h4>

          <div class="my-3">
            <div class="form-check">
              <input id="cash" name="pay_by" value="1" type="radio" class="form-check-input" checked required>
              <label class="form-check-label" for="cash">계좌이체(현금)</label>
            </div>
            <div class="form-check">
              <input id="credit" name="pay_by" value="2" type="radio" class="form-check-input" required>
              <label class="form-check-label" for="credit">신용카드</label>
            </div>
          </div>

		<div class="row gy-3">
			<div class="col-md-6" id="accountInfo1"> <!-- 초기에 보이도록 설정 -->
				<div class="form-group">
					<label for="cc-name" class="form-label">입금계좌</label>
					<input type="text" class="form-control" id="cc-name" placeholder="" readonly disabled>
				</div>
			</div>
			<div class="col-md-6" id="accountInfo2">
				<div class="form-group">
					<label for="cc-number" class="form-label">예금주명</label>
					<input type="text" class="form-control" id="cc-number" placeholder="" >
				</div>
					<small class="text-body-secondary">Full name as displayed on card</small>
					<div class="invalid-feedback">
					Credit card number is required
				</div>
			</div>
		</div>
          <hr class="my-4">
          <button class="w-100 btn btn-primary btn-lg" id="payButton">결제하기</button>
        </form>
      </div>
    </div>
  </main>
</div>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script>

//pay_by 값이 변경될 때 이를 감지하는 이벤트 핸들러 등록
const cashRadio = document.getElementById('cash');
const creditRadio = document.getElementById('credit');
const accountInfo1 = document.getElementById('accountInfo1');
const accountInfo2 = document.getElementById('accountInfo2');

cashRadio.addEventListener('change', function () {
    if (this.checked) {
        accountInfo1.style.display = 'block'; // 계좌이체 선택 시 보이게 함
        accountInfo1.disabled = false;
        accountInfo2.style.display = 'block'; // 계좌이체 선택 시 보이게 함
        accountInfo2.disabled = false;
    }
});

creditRadio.addEventListener('change', function () {
    if (this.checked) {
        accountInfo1.style.display = 'none'; // 신용카드 선택 시 숨김
        accountInfo1.disabled = true;
        accountInfo2.style.display = 'none'; // 신용카드 선택 시 숨김
        accountInfo2.disabled = true;
    }
});
//결제 api를 사용하기 위해 db 값 받아오기
document.getElementById("payButton").addEventListener("click", function() {
    // 필요한 매개변수 값을 가져와서 함수에 전달합니다.
    var carName = '${carInfo.car_name}';
    var totalAmount = '${total}';
    var userEmail = document.getElementById("user_email").value;
    var userName = document.getElementById("user_name").value;
    var userPhone = document.getElementById("user_phone").value;
    var userAddr = document.getElementById("user_address1").value;
    var userZipcode = document.getElementById("user_zipcode").value;

    requestPay(carName, totalAmount, userEmail,userName,userPhone,userAddr,userZipcode);
});
  
  
  
function requestPay(carName,totalAmount,userEmail,userName,userPhone,userAddr,userZipcode) {	
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
        /* m_redirect_url : 'redirect url' */
    }, function(rsp) {
        if ( rsp.success ) {
            document.forms['f'].submit();
        } else {
            var msg = '결제에 실패하였습니다.';
            rsp.error_msg;            
        }
    });
}
</script>

</body>
</html>