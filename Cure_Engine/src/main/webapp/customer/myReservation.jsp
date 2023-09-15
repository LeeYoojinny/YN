<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/customer/reservationView_style.css">
</head>
<script type="text/javascript">

</script>
<body>
	<form method="post">
		<table>
			<tr id="firstLine">
				<th>No.</th>
				<th>신청차량</th>
				<th>딜러</th>
				<th>날짜</th>
				<th>시간</th>
				<th>예약상태</th>
			</tr>
			<c:forEach var="mrev" items="${myReservation}" varStatus="status">
				<tr class="contents">
					<td id="item_no">${status.count}</td>
					<td>
						<a href="carDetailView.usr?car_id=${mrev.car_id}">${mrev.car_id}</a>
					</td>
					<c:forEach var="code" items="${allCode}">
	    				<c:if test="${code.code_category == 'user_name'}">
	    					<c:if test="${mrev.dealer_id == code.code_name}">
								<td>${code.code_value}</td>
							</c:if>
						</c:if>
					</c:forEach>			
					<td>${mrev.rev_date}</td>
					<td>${mrev.rev_time}</td>
					<c:choose>
						<c:when test="${mrev.approve_YN eq 'Y'}"><td>예약승인</td></c:when>
						<c:when test="${mrev.approve_YN eq 'W'}"><td>예약대기중</td></c:when>
						<c:when test="${mrev.approve_YN eq 'N'}"><td>예약거절</td></c:when>
					</c:choose>
				</tr>
				<tr id="space"><td colspan="6"></td></tr>
			</c:forEach>
		</table>
		</form>
	
	<c:if test="${myReservation == null }">
		<div class="nothing">${user_id}님의 신청한 예약이 없습니다.</div>
	</c:if>
</body>
</html>