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
            <c:if test="${fee.region == user_region}">
            	<c:set var="user_fee" value="${fee.fee}" />
	            <div>
	              <h6 class="my-0">탁송료</h6>                          
	              <small class="text-body-secondary">
	              대구 ~ ${fee.region} 
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
        <form class="needs-validation" novalidate>
          <div class="row g-3">
            <div class="col-sm-6">
              <label for="firstName" class="form-label">First name</label>
              <input type="text" class="form-control" id="firstName" placeholder="" value="" required>
              <div class="invalid-feedback">
                Valid first name is required.
              </div>
            </div>

            <div class="col-sm-6">
              <label for="lastName" class="form-label">Last name</label>
              <input type="text" class="form-control" id="lastName" placeholder="" value="" required>
              <div class="invalid-feedback">
                Valid last name is required.
              </div>
            </div>

            <div class="col-12">
              <label for="username" class="form-label">Username</label>
              <div class="input-group has-validation">
                <span class="input-group-text">@</span>
                <input type="text" class="form-control" id="username" placeholder="Username" required>
              <div class="invalid-feedback">
                  Your username is required.
                </div>
              </div>
            </div>

            <div class="col-12">
              <label for="email" class="form-label">Email <span class="text-body-secondary">(Optional)</span></label>
              <input type="email" class="form-control" id="email" placeholder="you@example.com">
              <div class="invalid-feedback">
                Please enter a valid email address for shipping updates.
              </div>
            </div>

            <div class="col-12">
              <label for="address" class="form-label">Address</label>
              <input type="text" class="form-control" id="address" placeholder="1234 Main St" required>
              <div class="invalid-feedback">
                Please enter your shipping address.
              </div>
            </div>

            <div class="col-12">
              <label for="address2" class="form-label">Address 2 <span class="text-body-secondary">(Optional)</span></label>
              <input type="text" class="form-control" id="address2" placeholder="Apartment or suite">
            </div>

            <div class="col-md-5">
              <label for="country" class="form-label">Country</label>
              <select class="form-select" id="country" required>
                <option value="">Choose...</option>
                <option>United States</option>
              </select>
              <div class="invalid-feedback">
                Please select a valid country.
              </div>
            </div>

            <div class="col-md-4">
              <label for="state" class="form-label">State</label>
              <select class="form-select" id="state" required>
                <option value="">Choose...</option>
                <option>California</option>
              </select>
              <div class="invalid-feedback">
                Please provide a valid state.
              </div>
            </div>

            <div class="col-md-3">
              <label for="zip" class="form-label">Zip</label>
              <input type="text" class="form-control" id="zip" placeholder="" required>
              <div class="invalid-feedback">
                Zip code required.
              </div>
            </div>
          </div>

          <hr class="my-4">

          <div class="form-check">
            <input type="checkbox" class="form-check-input" id="same-address">
            <label class="form-check-label" for="same-address">Shipping address is the same as my billing address</label>
          </div>

          <div class="form-check">
            <input type="checkbox" class="form-check-input" id="save-info">
            <label class="form-check-label" for="save-info">Save this information for next time</label>
          </div>

          <hr class="my-4">

          <h4 class="mb-3">Payment</h4>

          <div class="my-3">
            <div class="form-check">
              <input id="credit" name="paymentMethod" type="radio" class="form-check-input" checked required>
              <label class="form-check-label" for="credit">Credit card</label>
            </div>
            <div class="form-check">
              <input id="debit" name="paymentMethod" type="radio" class="form-check-input" required>
              <label class="form-check-label" for="debit">Debit card</label>
            </div>
            <div class="form-check">
              <input id="paypal" name="paymentMethod" type="radio" class="form-check-input" required>
              <label class="form-check-label" for="paypal">PayPal</label>
            </div>
          </div>

          <div class="row gy-3">
            <div class="col-md-6">
              <label for="cc-name" class="form-label">Name on card</label>
              <input type="text" class="form-control" id="cc-name" placeholder="" required>
              <small class="text-body-secondary">Full name as displayed on card</small>
              <div class="invalid-feedback">
                Name on card is required
              </div>
            </div>

            <div class="col-md-6">
              <label for="cc-number" class="form-label">Credit card number</label>
              <input type="text" class="form-control" id="cc-number" placeholder="" required>
              <div class="invalid-feedback">
                Credit card number is required
              </div>
            </div>

            <div class="col-md-3">
              <label for="cc-expiration" class="form-label">Expiration</label>
              <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
              <div class="invalid-feedback">
                Expiration date required
              </div>
            </div>

            <div class="col-md-3">
              <label for="cc-cvv" class="form-label">CVV</label>
              <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
              <div class="invalid-feedback">
                Security code required
              </div>
            </div>
          </div>

          <hr class="my-4">

          <button class="w-100 btn btn-primary btn-lg" type="submit">Continue to checkout</button>
        </form>
      </div>
    </div>
  </main>
</div>
</body>
</html>