<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/user/user_joinIdCheck_style.css">
</head>
<script type="text/javascript" src="경로"></script>
<script type="text/javascript">
	function inputIdCheck() {
		const regIdPass = /^[a-zA-Z0-9]{8,12}$/;
		
		if(!document.f.inputId.value.trim()) {
			alert("아이디를 입력해주세요.");
			document.f.inputId.focus();
			return false;
		}else if(!regIdPass.test(document.f.inputId.value.trim())){
			alert("아이디를 8~12자 사이의 영문과 숫자로 조합해주세요");
			document.f.inputId.select();
			return false;
		}
			
		document.f.submit();
	}

    function useThisId(okId){ 
    	
		opener.document.f.user_id.value = okId;
    	opener.document.f.idCheckYN.value = "Y";
    	
    	if (opener != null) {
            opener.check_ID = null;
            opener.document.f.user_pw.focus();
            self.close();
        }    
     }

</script>
<body>
	<div class="wrap_checkId">
		<div class="subject" style="text-align: center;font-weight: bold;">ID 중복 확인</div>
		<hr size="1" width="480">
		<br>
		<c:choose>
			<c:when test="${idCheckResult == null}">
				<form action="userJoinIdCheck.usr" method="post" name="f">
					<input type="text" name="inputId" id="inputId" value="${param.user_id}" size="50" placeholder="중복 체크할 ID를 입력하세요">
					<input type="submit" value="중복 체크" onclick="inputIdCheck(); return false;">
				</form>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${idCheckResult == false}">
						<span style="color: red"> 입력하신 [${checkOkId}] 은(는) 이미 사용 중입니다.</span>
						<form action="userJoinIdCheck.usr" method="post" name="f">
							<input type="text" name="inputId" id="inputId" size="50" placeholder="중복 체크할 ID를 입력하세요">
							<input type="button" value="중복 체크" onclick="inputIdCheck()">
						</form>
					</c:when>
					<c:when test="${idCheckResult == true}">
						<div class="usedID">
							<span style="color: blue">입력하신 [${checkOkId}]은(는) 사용 가능합니다.</span>
							<input type="button" value="사용하기"	onclick="useThisId('${checkOkId}')">
						</div>
						<form action="userJoinIdCheck.usr" method="post" name="f">
							<input type="text" name="inputId" id="inputId" size="50" placeholder="중복 체크할 ID를 입력하세요">
							<input type="button" value="중복 체크" onclick="inputIdCheck()">
						</form>
					</c:when>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>