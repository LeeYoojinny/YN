<%@page import="vo.Car"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<title>Cure Engine Reserve</title>
<link rel="stylesheet" href="css/customer/reservation_style.css">
</head>
<script type="text/javascript">	
	function check() {
		if(!document.f.rev_date.value) {
			alert("예약 날짜를 선택해주세요.");
			document.f.rev_date.focus();
		}
		
		if(!document.f.rev_time.value) {
			alert("예약 시간을 선택해주세요.");
			document.f.rev_time.focus();
		}
		
		document.f.submit();
	}

</script>
<body>
<div class="wrap_join">
	<div class="subject">시승예약</div>
		<form action="reservation.cust" name="f" method="post">
		<input type="hidden" name="user_id" id="user_id" value="${user_id}" readonly>	
			<div class="field">
				<b>차량번호</b> <input type="text" name="car_id" value="${param.car_id}" readonly>
			</div>
			<div class="field">
				<b>딜러</b> <input type="text" name="dealer_id" value="${param.dealer_id}" readonly>
				
			</div>

			<div class="field">
				<b>날짜</b>
				<input type="date" id="rev_date" name="rev_date" >
			</div>

			<div class="field">
				<b>시간</b>
				<select id="ampm" name="ampm">
			        <option value="AM">오전</option>
			        <option value="PM">오후</option>
			    </select>
			    <select id="hour" name="hour">
			        <option value="01">01</option>
			        <option value="02">02</option>
			        <option value="03">03</option>
			        <option value="04">04</option>
			        <option value="05">05</option>
			        <option value="06">06</option>
			        <option value="07">07</option>
			        <option value="08">08</option>
			        <option value="09">09</option>
			        <option value="10">10</option>
			        <option value="11">11</option>
			        <option value="12">12</option>
			    </select>
			    <select id="minute" name="minute">
			        <option value="00">00</option>
			        <option value="30">30</option>
			    </select>
			</div>
			
			<div class="field join_submit" style="text-align:center;">
				<input type="submit" value="예약하기" onclick="check(); return false;" 
				style="width:150px; margin-top:1.5rem;">
			</div>
		</form>
	</div>
<script>
    // 현재 날짜 가져오기
    var currentDate = new Date();

    // 이전 날짜 설정 (하루 전)
    currentDate.setDate(currentDate.getDate() + 1);

    // 현재 날짜를 문자열로 변환
    var currentDateStr = currentDate.toISOString().split('T')[0];

    // 날짜 입력 필드에 이전 날짜 설정
    var revDateInput = document.getElementById('rev_date');
    revDateInput.min = currentDateStr; // 이전 날짜를 최소 선택 가능한 날짜로 설정
    revDateInput.value = currentDateStr; //오늘 날짜를 기본값으로 선택
</script>
</body>
</html>