<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/admin/dealerApporveList_style.css">
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
	            var param = 'user_ids=' + selectedCarIds.join(',');
	            location.href = 'dealerApprove.adm?' + param;
	        }
	    }
	}
	
	function approveCheck_2(user_id) {
		 var confirmMessage = user_id+ ' 승인 하시겠습니까?';
		 
		 if(confirm(confirmMessage)) {
			 location.href = 'dealerApprove.adm?user_ids='+ user_id;
		 }
	}
	
	function removeCheck(user_id) {
		 var confirmMessage = user_id+ ' 승인 반려 하시겠습니까?';
		 
		 if(confirm(confirmMessage)) {
			 location.href = 'dealerDelete.adm?user_id=' + user_id;
		 }
	}
	
</script>
<body>
	<div class="wrap_allList">
		<div class="subject"></div>
		<c:if test="${disapprove_list != null }">
			<form method="post">
			<table>
				<tr id="allApprove">
					<td colspan="2" id="bt"><button onclick="approveCheck_1(); return false;">선택승인</button></td>
					<td colspan="3"></td>
				</tr>
				<tr id="firstLine">
					<th><input type="checkbox" name="allCheck" onclick="checkAll(this.form)"></th>
					<th>No.</th>
					<th></th>
					<th>요청딜러 상세정보</th>
					<th></th>
				</tr>
				<c:set var="startNo" value="${(pageInfo.page - 1) * 5 + 1}" />
				<c:forEach var="list" items="${disapprove_list}" varStatus="status">
					<tr class="contents">
						<td rowspan="3" id="check_approve"><input type="checkbox" name="approve" value="${list.user_id}"></td>
						<td rowspan="3" id="item_no">${startNo + status.count-1}</td>
						<td rowspan="3" id="main_img">
							<c:if test="${list.user_gender eq 'M'}">
								<img src="image/dealerJoin/joinImage_m.png">
							</c:if>
							<c:if test="${list.user_gender eq 'F'}">
								<img src="image/dealerJoin/joinImage_f.png">
							</c:if>						
						</td>
						<td id="explain1">
						ID : ${list.user_id} &emsp; 딜러명 : ${list.user_name} &emsp; 생년월일 : ${list.user_birth}
						<td rowspan="3" id="bt">
							<button onclick="approveCheck_2('${list.user_id}'); return false;">등록승인</button>
							<button onclick="removeCheck('${list.user_id}');return false;">승인반려</button>
						</td>
					</tr>
					<tr class="contents">
						<td id="explain2">
						전화번호 : ${list.user_phone} &emsp; 이메일 : ${list.user_email}						
						</td>
					</tr>
					<tr class="contents">
						<td id="explain3">
							사업장 주소 : ${list.user_zipcode} &nbsp; ${list.user_address1}&nbsp;${list.user_address2}
						</td>
					</tr>
					<tr id="space"><td colspan="5"></td></tr>
				</c:forEach>
			</table>
			</form>
			
			<div class="page">
				<c:choose> 
					<c:when test="${pageInfo.page <= 1}">[이전]&nbsp;</c:when>
					<c:otherwise><a style="text-decoration:none" href="dealerApproveList.adm?page=${pageInfo.page-1}">[이전]&nbsp;</a></c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1" varStatus="loop">
					<c:choose>
						<c:when test="${i == pageInfo.page}">${i}&nbsp;</c:when>
						<c:otherwise><a style="text-decoration:none" href="dealerApproveList.adm?page=${i}">${i}</a>&nbsp;</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${pageInfo.page >= pageInfo.maxPage}">[다음]&nbsp;</c:when>
					<c:otherwise><a style="text-decoration:none" href="dealerApproveList.adm?page=${pageInfo.page+1}">[다음]&nbsp;</a></c:otherwise>
				</c:choose>
			</div>
		</c:if>
		<c:if test="${disapprove_list == null }">
			<div class="nothing">딜러 승인 요청이 없습니다.</div>
		</c:if>
	</div>
</body>
</html>