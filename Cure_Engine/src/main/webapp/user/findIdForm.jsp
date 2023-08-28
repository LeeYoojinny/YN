<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cure Engine Find ID</title>
<link rel="stylesheet" href="css/user/user_findIdForm_style.css">
</head>
<script type="text/javascript">

/* 생년월일 입력에서 숫자를 다 적으면 자동으로 다음칸 넘어가게 해주는 함수 */
document.addEventListener("DOMContentLoaded", function() {
    var birthInputs = document.querySelectorAll(".birth");

    birthInputs.forEach(function(input) {
        input.addEventListener("keyup", function() {
            if (this.value.length === parseInt(this.getAttribute("maxlength"))) {
                var nextInput = this.nextElementSibling;
                if (nextInput && nextInput.classList.contains("birth")) {
                    nextInput.focus();
                }
            }
        });
    });
});

/* 폼 넘기기 전 입력값 확인 */
function check() {
	//이름 정규화 공식
	const regName = /^[가-힣a-zA-Z]{2,}$/;

	//생년월일
	const regYear = /^(19[0-9][0-9]|20[0-2][0-3])$/;
	const regMonth = /^(0[1-9]|1[0-2])$/;
	const regDay = /^(0[1-9]|[12][0-9]|3[01])$/;
	
	
	//email 정규화 공식
	const regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	
	//이름 체크
	if(!document.f.user_name.value.trim()){
		alert("이름을 입력해주세요.");
		document.f.user_name.focus();
		return false;
	}else if(!regName.test(document.f.user_name.value.trim())){
		alert("이름을 한글 또는 영문으로만 입력해주세요.");
		document.f.user_name.select();
		return false;
	}
	
	//생년월일
	if(!document.f.user_birth_y.value.trim()){
		alert("생년월일을 입력해주세요.");
		document.f.user_birth_y.focus();
		return false;
	}else if(!regYear.test(document.f.user_birth_y.value.trim())){
		alert("1900~2023년 사이의 년을 입력해주세요.");
		document.f.user_birth_y.select();
		return false;
	}
	
	if(!document.f.user_birth_m.value.trim()){
		alert("생년월일을 입력해주세요.");
		document.f.user_birth_m.focus();
		return false;
	}else if(!regMonth.test(document.f.user_birth_m.value.trim())){
		alert("2자리로 월을 입력해주세요. (01 ~ 12월)");
		document.f.user_birth_m.select();
		return false;
	}
	
	if(!document.f.user_birth_d.value.trim()){
		alert("생년월일을 입력해주세요.");
		document.f.user_birth_d.focus();
		return false;
	}else if(!regDay.test(document.f.user_birth_d.value.trim())){
		alert("2자리로 일을 입력해주세요. (01 ~ 31일)");
		document.f.user_birth_d.select();
		return false;
	}
	
	//이메일주소 체크
	if(!document.f.user_email.value.trim()){
		alert("이메일 주소를 입력해주세요.");
		document.f.user_email.focus();
		return false;
	}else if(!regEmail.test(document.f.user_email.value.trim())){
		alert("유효하지 않은 이메일주소 입니다.");
		document.f.user_email.select();
		return false;
	}
	
	document.f.submit();
}
</script>
<body>
	<div class="wrap_findId">
	  <form action="userFindIdAction.usr" method="post" name="f" id="findIdForm">
	    	<div class="field">
				<b>이름</b> <input type="text" name="user_name" >
			</div>
			<div class="field birth">
				<b>생년월일(YYYY-MM-DD)</b>
				<div>
					<input type="text" class="birth" name="user_birth_y" id="user_birth_y" placeholder="YYYY년" maxlength="4"> 
					<input type="text" class="birth" name="user_birth_m" id="user_birth_m" placeholder="MM월" maxlength="2">
					<input type="text" class="birth" name="user_birth_d" id="user_birth_d" placeholder="DD일" maxlength="2">
				</div>
			</div>
			<div class="field">
				<b>이메일</b> <input type="text"  name="user_email" placeholder="(예)test@cureengine.com">
			</div>
			<div class="field login_submit"><input type="submit" value="아이디찾기" onclick="check(); return false;"></div>   
	  		<!-- <div class="field login_idSave">
	  			<input type="checkbox" name="login_idSave" value="save"><label for="login_idSave">아이디기억하기</label>
	  		</div> -->
	  </form>
	  	 <div class="findId_another"><a href="userFindPwForm.usr">비밀번호 찾기</a> | <a href="userJoin.usr">회원가입</a></div>
	</div>
</body>
</html>