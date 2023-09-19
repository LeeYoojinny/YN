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
<link href="css/customer/orderForm3_style.css" rel="stylesheet">
</head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<body>
	<div class="container">
		<main>
			<div class="py-5 text-center">
				<h2>${car_id} 주문 접수 되었습니다.</h2>

			</div>
			<%-- 진행상황 --%>
			<div class="progressBar">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item step1">주문서 작성</li>
			    <li class="breadcrumb-item active step2" aria-current="page">금액확인 및 결제</li>
			    <li class="breadcrumb-item active step3" aria-current="page">접수완료</li>
			  </ol>
			</div>			
				<div class="col-md-7 col-lg-8 mx-auto" style="margin-top: 1.5rem;">
				<h4 class="mb-3">신청 정보</h4>
				<div id="wrap_info">
					<div id="colunm1">
						탁송주소 : ${order.user_address1} ${order.user_address2}
					</div>
					결제방법 :					
					<c:if test="${order.payment eq 1}"> 계좌이체 </c:if>
					<c:if test="${order.payment eq 2}"> 신용카드 </c:if>
					<div id="wrap_table">
						<table>
							<tr>
								<th>구분</th>
								<th>금액</th>
								<th>총금액</th>					
							</tr>
							<tr>
								<td>차량가격</td>
								<td><fmt:formatNumber value="${order.car_price}" pattern="#,###" />원</td>
								<td rowspan="5"><fmt:formatNumber value="${payment.pay_price}" pattern="#,###" />원</td>							
							</tr>
							<tr>
								<td>취득세</td>
								<td><fmt:formatNumber value="${order.car_tax}" pattern="#,###" />원</td>						
							</tr>
							<tr>
								<td>관리비(매도비)</td>
								<td>300,000원</td>						
							</tr>
							<tr>
								<td>탁송료</td>
								<td><fmt:formatNumber value="${order.deliveryfee}" pattern="#,###" />원</td>						
							</tr>
							<tr>
								<td>쿠폰할인</td>
								<td>-<fmt:formatNumber value="${order.discount_price}" pattern="#,###" />원</td>						
							</tr>						
						</table>
					</div>
					
					<div class="form-check text-center">
						<button class="btn" id="prev" onclick="location.href='index.jsp';">메인으로</button>
						<button class="btn" id="payButton" onclick="location.href='myOrder.cust?user_id=${order.user_id}'">나의주문내역</button>
					</div>					
				</div>				
			</div>
		</main>
	</div>	
</body>
</html>