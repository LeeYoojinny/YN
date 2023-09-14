<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cure Engine 회원탈퇴</title>
<link rel="stylesheet" href="css/customer/deleteForm_style.css">
</head>
<script type="text/javascript">
	function check() {
         	// 현재 비밀번호 필드 확인
            if (document.f.user_pw.value === "") {
                alert("현재 비밀번호를 입력하세요.");
                return false; // submit을 중단
            }

            // 변경 비밀번호 필드 확인
            if (document.f.change_pw.value === "") {
                alert("변경 비밀번호를 입력하세요.");
                return false; // submit을 중단
            }

            // 변경 비밀번호 재입력 필드 확인
            if (document.f.doubleCheck_pw.value === "") {
                alert("변경 비밀번호를 다시 입력하세요.");
                return false; // submit을 중단
            }

            // 변경 비밀번호 일치 여부 확인
            if (document.f.change_pw.value !== document.f.doubleCheck_pw.value) {
                alert("변경 비밀번호가 일치하지 않습니다.");
                return false; // submit을 중단
            }

            // 수정 여부 묻기
            var confirmMessage = "비밀번호를 수정하시겠습니까?";
            if (!confirm(confirmMessage)) {
                return false; // submit을 중단
            }

            // 모든 검증을 통과한 경우 폼 제출
           document.f.submit();
        }


</script>
<body>
	<div class="wrap_login">
		<div class="subject">비밀번호 수정</div>
		  <form action="userPwChange.usr" method="post" name="f" id="pwChangeForm">
		    	<div class="field">
					<b>아이디</b> <input type="text" name="user_id" id="user_id" value="${user_id}" readonly="readonly">
				</div>
				<div class="field">
					<b>현재 비밀번호</b> <input type="password" name="user_pw">
				</div>
				<div class="field">
					<b>변경 비밀번호</b> 
					<input type="password" name="change_pw">
					<input type="password" name="doubleCheck_pw" placeholder="변경비밀번호 재입력">
				</div>
				<div class="field login_submit"><input type="submit" value="변경하기" onclick="check(); return false;"></div>
		  </form>
	  </div>
</body>
</html>