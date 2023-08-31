<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function inputIdCheck() {
		const regCarIdPass = /\d{2,3}[가-힣]{1}\d{4}/gm;
		
		if(document.f.car_id.value.trim() =="") {
			alert("차량정보를 입력해주세요.");
			document.f.car_id.focus();
			return false;
		}
		
		if(!regCarIdPass.test(document.f.car_id.value.trim())){
			alert("형식에 맞는 차량정보를 입력해주세요.");
			document.f.car_id.select();
			return false;
		}
			
		document.f.submit();
	}
	
	function useThisId(okId){ 
		
		opener.document.f.car_id.value = okId;
		opener.document.f.carIdCheckYN.value = "Y";
		
		if (opener != null) {
	        opener.check_carID = null;
	        opener.document.f.car_price.focus();
	        self.close();
	    }    
	 }
</script>
<body>
	<div class="wrap_checkId">
		<div class="subject" style="text-align: center;font-weight: bold;">차량정보 중복 확인</div>
		<hr size="1" width="480">
		<br>
		<c:choose>
			<c:when test="${CarIdCheckResult == null}">
				<form action="carIdCheck.adm" method="post" name="f">
					<input type="text" name="inputCarId" id="inputCarId" value="${param.car_id}" size="50" placeholder="중복 체크할 차량정보를 입력하세요">
					<input type="submit" value="중복 체크" onclick="inputIdCheck(); return false;">
				</form>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${CarIdCheckResult == false}">
						<span style="color: red"> 입력하신 [${checkOkCarId}] 은(는) 이미 등록된 차량입니다.</span>
						<form action="carIdCheck.adm" method="post" name="f">
							<input type="text" name="inputCarId" id="inputCarId" size="50" placeholder="중복 체크할 차량정보를 입력하세요">
							<input type="submit" value="중복 체크" onclick="inputIdCheck(); return false;">
						</form>
					</c:when>
					<c:when test="${CarIdCheckResult == true}">
						<div class="usedID">
							<span style="color: blue">입력하신 [${checkOkCarId}]은(는) 등록 가능합니다.</span>
							<input type="button" value="등록하기"	onclick="useThisId('${checkOkCarId}')">
						</div>
						<form action="carIdCheck.adm" method="post" name="f">
							<input type="text" name="inputCarId" id="inputCarId" size="50" placeholder="중복 체크할 차량정보를 입력하세요">
							<input type="submit" value="중복 체크" onclick="inputIdCheck(); return false;">
						</form>
					</c:when>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>