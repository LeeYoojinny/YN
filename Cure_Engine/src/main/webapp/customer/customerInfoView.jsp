<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cure Engine 회원수정</title>
<link rel="stylesheet" href="css/customer/infoModify_style.css">
</head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">    
    // 생년월일 입력에서 숫자를 다 적으면 자동으로 다음칸 넘어가게 해주는 함수
    document.addEventListener("DOMContentLoaded", function() {
        var birthInputs = document.querySelectorAll(".birth");

        birthInputs.forEach(function(input) {
            input.addEventListener("keyup", function() {
                if (this.value.length == parseInt(this.getAttribute("maxlength"))) {
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
        new daum.Postcode({
            oncomplete : function(data) {
                document.getElementById("user_zipcode").value = data.zonecode;
                let roadAddr = data.roadAddress;
                let jibunAddr = data.jibunAddress;
                
                if (roadAddr !== '') {
                    document.getElementById("user_address1").value = roadAddr;
                } else if (jibunAddr !== '') {
                    document.getElementById("user_address1").value = jibunAddr;
                }
                
                document.getElementById("user_address2").focus();
            }
        }).open();
    }

    /* 회원수정 submit 전 입력값 확인 */
    function check() {
        const regName = /^[가-힣a-zA-Z]{2,}$/;
        const regYear = /^(19[0-9][0-9]|20[0-2][0-3])$/;
        const regMonth = /^(0[1-9]|1[0-2])$/;
        const regDay = /^(0[1-9]|[12][0-9]|3[01])$/;
        const regPhone = /^\d{3}\d{3,4}\d{4}$/;
        const regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      
        if (!document.f.user_name.value.trim()) {
            alert("이름을 입력해주세요.");
            document.f.user_name.focus();
            return false;
        } else if (!regName.test(document.f.user_name.value.trim())) {
            alert("이름을 한글 또는 영문으로만 입력해주세요.");
            document.f.user_name.select();
            return false;
        }

        // 생년월일
        if (!document.f.user_birth_y.value.trim() || !document.f.user_birth_m.value.trim() || !document.f.user_birth_d.value.trim()) {
            alert("생년월일을 입력해주세요.");
            document.f.user_birth_y.focus();
            return false;
        } else if (!regYear.test(document.f.user_birth_y.value.trim()) || !regMonth.test(document.f.user_birth_m.value.trim()) || !regDay.test(document.f.user_birth_d.value.trim())) {
            alert("유효한 생년월일을 입력해주세요.");
            document.f.user_birth_y.select();
            return false;
        }

        // 휴대전화번호 체크
        if (!document.f.user_phone.value.trim()) {
            alert("휴대전화번호를 입력해주세요.");
            document.f.user_phone.focus();
            return false;
        } else if (!regPhone.test(document.f.user_phone.value.trim())) {
            alert("휴대전화번호를 (-) 없이 숫자만 입력해주세요.");
            document.f.user_phone.select();
            return false;
        }

        // 이메일주소 체크
        if (!document.f.user_email.value.trim()) {
            alert("이메일 주소를 입력해주세요.");
            document.f.user_email.focus();
            return false;
        } else if (!regEmail.test(document.f.user_email.value.trim())) {
            alert("유효한 이메일 주소를 입력해주세요.");
            document.f.user_email.select();
            return false;
        }

        // 주소 체크
        if (!document.f.user_zipcode.value.trim()) {
            alert("주소검색을 통해 주소를 입력해주세요.");
            document.f.user_zipcode.focus();
            return false;
        }   
        
        document.f.submit();
    }

</script>
<body>
    <div class="wrap_join">
        <div class="subject">회원정보 관리</div>
        <form action="myInfoUpdate.cust" name="f" method="post">
        <input type="hidden" name="user_category" value="customer">
            <div class="field">
                <b>이름</b>
                <input type="text" name="user_name" id="user_name" value="${userInfo.user_name}" placeholder="한글 또는 영문만 입력하세요.(특수문자 제외)">
            </div>
            <div class="field birth">
                <b>생년월일(YYYY-MM-DD)</b>
                <div>
                    <input type="text" class="birth" name="user_birth_y" id="user_birth_y" value="${user_birth_y}" maxlength="4"> 
                    <input type="text" class="birth" name="user_birth_m" id="user_birth_m" value="${user_birth_m}" maxlength="2">
                    <input type="text" class="birth" name="user_birth_d" id="user_birth_d" value="${user_birth_d}" maxlength="2">
                </div>
            </div>            
            <div class="field gender">
				<b>성별</b>
				<div>
					<label><input type="radio" name="user_gender" value="M" ${userInfo.user_gender =='M'? 'checked':''}>남자</label>
					<label><input type="radio" name="user_gender" value="F" ${userInfo.user_gender =='F'? 'checked':''}>여자</label>
				</div>
			</div>
			<div class="field">
                <b>휴대폰 번호</b> 
                <input type="text" name="user_phone" id="user_phone" value="${userInfo.user_phone}" placeholder="(-)없이 숫자만 입력하세요.">
            </div>
            <div class="field">
                <b>이메일 &ensp;<small>(고객정보 분실 시 필요)</small></b> 
                <input type="text" name="user_email" id="user_email" value="${userInfo.user_email}" placeholder="(예)test@test.com" >
            </div>
            <div class="field address">
                <b>주소</b>
                <div>
                    <input type="text" name="user_zipcode" id="user_zipcode" value="${userInfo.user_zipcode}" placeholder="우편번호만 입력" >
                    <input type="button" value="주소검색" onclick="findAddr(); return false;">
                </div>
                <input type="text" name="user_address1" id="user_address1" value="${userInfo.user_address1}" size="50" placeholder="주소" readonly><br>
                <input type="text" name="user_address2" id="user_address2" value="${userInfo.user_address2}" size="50" placeholder="상세주소">
            </div>
            <div class="field join_submit">
                <input type="submit" value="수정하기" onclick="check(); return false;">
            </div>
            <div class="field underBt">
    			<button onclick="location.href='pwChangeForm.usr?user_id=${userInfo.user_id}'; return false;">비밀번호수정</button>
    			<button onclick="location.href='customerDeleteForm.cust?user_id=${userInfo.user_id}'; return false;">회원 탈퇴</button>
    		</div>
        </form>
    </div>
</body>
</html>
