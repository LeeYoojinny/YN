<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/admin/reservationView_style.css">
</head>
<script type="text/javascript">
	function checkAll(theForm){
		//체크박스가 하나인지 여러개인지 확인
		if(theForm.deleteRsv.length == undefined){//체크박스(remove)의 배열길이가 정의되어 있지 않다면(=장바구니에 상품이 1개라면)
			theForm.deleteRsv.checked = theForm.allCheck.checked;			
		}else {//체크박스(remove)의 배열길이가 정의되어 있다면(=장바구니에 상품이 2개이상이면)
			for(var i=0; i<theForm.deleteRsv.length; i++){
				theForm.deleteRsv[i].checked = theForm.allCheck.checked;
			}			
		}		
	}
	
	function deleteCheck() {
		var checkboxes = document.getElementsByName('deleteRsv');
	    var selectedCount = 0;
	    var selectedResernum = [];

	    for (var i = 0; i < checkboxes.length; i++) {
	        if (checkboxes[i].checked) {
	            selectedCount++;
	            selectedResernum.push(checkboxes[i].value);
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
	            var param = 'reserNums=' + selectedResernum.join(',');
	            location.href = 'deleteReservation.adm?' + param;
	        }
	    }
	}
	
	function submitForm(checkValue, resernumValue) {
		//폼 데이터 설정
	    document.forms["reservationForm"].elements["check"].value = checkValue;
	    document.forms["reservationForm"].elements["resernum"].value = resernumValue;
	    
	    //폼 제출
	    document.forms["reservationForm"].submit();
	}
</script>
<body>
<h2>시승예약 현황</h2>
	<table>
		<c:if test="${user_category eq 'admin'}">
		<tr id="allApprove">
			<td colspan="2" id="bt"><button onclick="deleteCheck(); return false;">선택삭제</button></td>
			<td colspan="5"></td>
		</tr>
		</c:if>
		<tr id="firstLine">
			<c:if test="${user_category eq 'admin'}">
				<th><input type="checkbox" name="allCheck" onclick="checkAll(this.form)"></th>	
			</c:if>
			<th>No.</th>
			<th>신청차량</th>
			<th>신청자</th>
			<th>날짜</th>
			<th>시간</th>
			<th>처리내용</th>
		</tr>
		<c:set var="startNo" value="${(pageInfo.page - 1) * 10 + 1}" />
		<c:forEach var="rev" items="${reservation}" varStatus="status">
			<tr class="contents">
				<c:if test="${user_category eq 'admin'}">
					<td><input type="checkbox" name="deleteRsv" value="${rev.resernum}"></td>	
				</c:if>
				<td id="item_no">${startNo + status.count-1}</td>
				<td>${rev.car_id}</td>
				<td>${rev.user_id}</td>
				<td>${rev.rev_date}</td>
				<td>${rev.rev_time}</td>
				<c:if test="${rev.approve_YN eq 'W'}">
				<td id="bt">
					<form id="reservationForm" method="post" action="reservationCheck.adm">
						<input type="hidden" name="resernum" value="${rev.resernum}">
						<input type="hidden" name="check" value="">
						<button type="button" onclick="submitForm('Y', '${rev.resernum}')">승인</button>
                        <button type="button" onclick="submitForm('N', '${rev.resernum}')">거절</button>
                    </form>
				</td>
				</c:if>
				<c:if test="${rev.approve_YN eq 'Y'}">
					<td style="line-height:2.5rem;">승인</td>
				</c:if>
				<c:if test="${rev.approve_YN eq 'N'}">
					<td style="line-height:2.5rem;">거절</td>
				</c:if>
			</tr>
			<tr id="space"><td colspan="6"></td></tr>
		</c:forEach>
	</table>
	<div class="page">
		<c:choose> 
			<c:when test="${pageInfo.page <= 1}">[이전]&nbsp;</c:when>
			<c:otherwise><a style="text-decoration:none" href="reservationView.adm?page=${pageInfo.page-1}">[이전]&nbsp;</a></c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1" varStatus="loop">
			<c:choose>
				<c:when test="${i == pageInfo.page}">${i}&nbsp;</c:when>
				<c:otherwise><a style="text-decoration:none" href="reservationView.adm?page=${i}">${i}</a>&nbsp;</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${pageInfo.page >= pageInfo.maxPage}">[다음]&nbsp;</c:when>
			<c:otherwise><a style="text-decoration:none" href="reservationView.adm?page=${pageInfo.page+1}">[다음]&nbsp;</a></c:otherwise>
		</c:choose>
	</div>
	<c:if test="${reservation == null}">
		<div class="nothing">${user_id}님의 예약 신청건이 없습니다.</div>
	</c:if>
</body>
</html>