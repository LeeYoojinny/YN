<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cure Engine Join</title>
<link rel="stylesheet" href="css/user/user_joinForm_style.css">
</head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
	
	/* 주소 검색 함수 */
	function findAddr() {
		//카카오 지도 발생 → 주소입력 후 [검색] → 찾는 주소 [선택]
		new daum.Postcode({
			oncomplete : function(data){//[선택]한 데이터를 입력값으로 셋팅
				console.log(data); //콘솔에 확인
				
				document.getElementById("user_zipcode").value = data.zonecode; //[선택]한 데이터의 정보를 가져옴
				
				let roadAddr = data.roadAddress; //도로명주소
				let jibunAddr = data.jibunAddress; //도로명주소
				
				if(roadAddr != ''){//도로명주소가 있으면 도로명주소가 등록됨
					document.getElementById("user_address1").value = roadAddr;
				}else if(jibunAddr != ''){//도로명주소가 없으면 지번주소 등록
					document.getElementById("user_address1").value = jibunAddr;
				}
				
				document.getElementById("user_address2").focus; //상세주소 칸에 커서두기
				
			}
		}).open();
	
	}
	
	/* 회원가입 sumit 전 정규화로 입력값 확인 */
	function check() {

		//아이디와 비밀번호 값 데이터 정규화 공식
		const regIdPass = /^[a-zA-Z0-9]{8,12}$/;
			
		//이름 정규화 공식
		const regName = /^[가-힣a-zA-Z]{2,}$/;

		//생년월일
		const regYear = /^(19[0-9][0-9]|20[0-2][0-3])$/;
		const regMonth = /^(0[1-9]|1[0-2])$/;
		const regDay = /^(0[1-9]|[12][0-9]|3[01])$/;
		
		
		//email 정규화 공식
		const regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			
		//휴대번호 정규화 공식
		const regPhone = /^\d{3}\d{3,4}\d{4}$/; //-제외
		
		
		
		
		//아이디 체크
		if(!document.f.user_id.value.trim()){
			alert("아이디를 입력해주세요.");
			document.f.user_id.focus();
			return false;
		}else if(!regIdPass.test(document.f.user_id.value.trim())){
			alert("아이디를 8~12자 사이의 영문과 숫자로 조합해주세요");
			document.f.user_id.select();
			return false;
		}
		
		//아이디 중복체크 여부확인
		
		
		//비밀번호 체크
		if(!document.f.user_pw.value.trim()){
			alert("비밀번호를 입력해주세요.");
			document.f.user_pw.focus();
			return false;
		}else if(!regIdPass.test(document.f.user_pw.value.trim())){
			alert("비밀번호를 8~12자 사이의 영문과 숫자로 조합해주세요");
			document.f.user_pw.select();
			return false;
		}
		
		//비밀번호 확인 체크
		if(!document.f.check_pw.value.trim()) {
			alert("비밀번호 재확인 헤주세요.");
			document.f.check_pw.focus();
			return false;
		}else if(document.f.user_pw.value != document.f.check_pw.value) {
		       alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
		       f.check_pw.value = "";
		       f.user_pw.focus();
		       return false;
		}

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
		
		
		//휴대전화번호 체크
		if(!document.f.user_phone.value.trim()){
			alert("휴대전화번호를 입력해주세요.");
			document.f.user_phone.focus();
			return false;
		}else if(!regPhone.test(document.f.user_phone.value.trim())){
			alert("휴대전화번호를 (-)없이 숫자만 입력해주세요.");
			document.f.user_phone.select();
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
		
		//주소체크
		if(!document.f.user_zipcode.value.trim()){
			alert("주소검색을 통해 주소를 입력해주세요..");
			document.f.user_zipcode.focus();
			return false;
		}			
		
		document.f.submit();
	}

</script>
<body>
	<div class="wrap_join">
	<div class="field subject"></div>
		<form action="userJoinAction.usr" name="f" method="post">
		<input type="hidden" name="user_category" id="user_category" value="customer">
			<div class="field id">
				<b>아이디<small>(8~12자 이내 영어 대소문자 및 숫자로 조합) </small></b>
				<div>
				<input type="text" name="user_id" id="user_id">
				<input type="button" onclick="return false;" value="중복확인">
				</div>
			</div>
			<div class="field">
				<b>비밀번호</b> <input type="password" class="userpw" name="user_pw" >
			</div>
			<div class="field">
				<b>비밀번호 재확인</b> <input class="userpw-confirm" name="check_pw"type="password">
			</div>
			<div class="field">
				<b>이름</b> <input type="text" name="user_name" id="user_name">
			</div>

			<div class="field birth">
				<b>생년월일(YYYY-MM-DD)</b>
				<div>
					<input type="text" class="birth" name="user_birth_y" id="user_birth_y" placeholder="YYYY년" maxlength="4"> 
					<input type="text" class="birth" name="user_birth_m" id="user_birth_m" placeholder="MM월" maxlength="2">
					<input type="text" class="birth" name="user_birth_d" id="user_birth_d" placeholder="DD일" maxlength="2">
				</div>
			</div>

			<div class="field gender">
				<b>성별</b>
				<div>
					<label><input type="radio" name="user_gender" value="M" checked="checked">남자</label>
					<label><input type="radio" name="user_gender" value="F">여자</label>
				</div>
			</div>
			
			<div class="field">
				<b>휴대폰 번호</b> 
				<input type="text" name="user_phone" id="user_phone" placeholder="(-)없이 숫자만 입력하세요.">
			</div>
			

			<div class="field">
				<b>이메일 &ensp;<small>(비밀번호 분실 시 필요)</small></b> 
				<input type="text" name="user_email" id="user_email" placeholder="(예)test@cureengine.com">
			</div>

			<div class="field address">
				<b>주소</b>
				<div>
					<input type="text" name="user_zipcode" id="user_zipcode" placeholder="우편번호"> 
					<input type="button" value="주소검색" onclick="findAddr(); return false;">
				</div>
				<input type="text" name="user_address1" id="user_address1" placeholder="주소" readonly> 
				<input type="text" name="user_address2" id="user_address2" placeholder="상세주소">
			</div>


			<div class="field join_submit"><input type="submit" value="가입하기" onclick="check(); return false;"></div>
		</form>
	</div>
</body>
</html>