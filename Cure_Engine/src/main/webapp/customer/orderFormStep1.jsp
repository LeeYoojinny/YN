<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/checkout/">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
<link href="css/customer/orderForm1_style.css" rel="stylesheet">
</head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
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
	
	function nextStepCheck() {
		//이름 정규화 공식
		const regName = /^[가-힣a-zA-Z]{2,}$/;
		//email 정규화 공식
		const regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			
		//휴대번호 정규화 공식
		const regPhone = /^\d{3}\d{3,4}\d{4}$/; //-제외
		
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
			alert("주소검색을 통해 주소를 입력해주세요.");
			document.f.user_zipcode.focus();
			return false;
		}else if(!document.f.user_address1.value.trim()){
			alert("주소검색을 통해 주소를 입력해주세요.");
			document.f.user_address1.focus();
			return false;
		}		
		
		document.f.submit();		
	}
</script>
<body>
	<div class="container">
		<main>
			<div class="py-5 text-center">
				<h2>${carInfo.car_id}주문서</h2>
				<p class="lead">
					<c:forEach var="code" items="${allCode}">
						<c:if test="${code.code_category == 'car_brand'}">
							<c:if test="${carInfo.car_brand == code.code_name}">
					${code.code_value} 
					<c:set var="brand" value="${code.code_value}" />
							</c:if>
						</c:if>
					</c:forEach>
					${carInfo.car_year}연식 ${carInfo.car_name}
				</p>
			</div>
			
			<%-- 진행상황 --%>
			<div class="progressBar">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item step1">주문서 작성</li>
			    <li class="breadcrumb-item active step2" aria-current="page">금액확인 및 결제</li>
			    <li class="breadcrumb-item active step3" aria-current="page">접수완료</li>
			  </ol>
			</div>
			
			<div class="col-md-7 col-lg-8 mx-auto" style="margin-top: 1.5rem;">
				<h4 class="mb-3">신청 정보작성</h4>
				<form class="needs-validation" action="orderFormStep2.cust" method="post" name="f">
					<input type="hidden" name="car_id" value="${carInfo.car_id}">
					<div class="row g-3">
						<div class="col-sm-6">
							<label for="firstName" class="form-label">아이디</label> <input
								type="text" class="form-control" id="user_id" name="user_id"
								value="${userInfo.user_id}" readonly>
						</div>
						<div class="col-sm-6">
							<label for="lastName" class="form-label">성함</label>
							<input type="text" class="form-control" id="user_name" name="user_name"
								value="${userInfo.user_name}" >
							<div class="invalid-feedback">성함을 입력해주세요.</div>
						</div>

						<div class="col-sm-6">
							<label for="lastName" class="form-label">휴대폰번호</label> <input
								type="text" class="form-control" placeholder="(-)없이 숫자만 입력해주세요."
								id="user_phone" name="user_phone" value="${userInfo.user_phone}"
								required>
							<div class="invalid-feedback">연락 가능한 전화번호를 입력해주세요.</div>
						</div>
						<div class="col-sm-6">
							<label for="lastName" class="form-label">이메일</label> <input
								type="text" class="form-control" id="user_email"
								name="user_email" value="${userInfo.user_email}" >
							<div class="invalid-feedback">이메일 주소를 입력해주세요.</div>
						</div>

						<div class="col-12">
							<label for="username" class="form-label">탁송받으실 주소</label>
							<div class="input-group has-validation">
								<input type="text" class="form-control" id="user_zipcode"
									name="user_zipcode" placeholder="우편번호만 입력"">
								<span
									class="input-group-text col-3 d-flex justify-content-center"
									onclick="findAddr(); return false;" style="cursor: pointer;">
									우편번호찾기 </span>
								<div class="invalid-feedback">우편번호찾기를 통해 우편번호 입력해주세요.</div>
							</div>
						</div>
						<div class="col-12">
							<input type="email" class="form-control" id="user_address1"
								name="user_address1" placeholder="주소" readonly>
						</div>
						<div class="col-12">
							<input type="text" class="form-control" id="user_address2"
								name="user_address2" placeholder="상세주소" ">
						</div>
						<hr class="my-4">
					</div>
					<div class="form-check text-center">
						<button class="btn" id="prev" disabled>이전</button>
						<button class="btn" id="next" onclick="nextStepCheck(); return false;">다음</button>
					</div>
				</form>
			</div>
		</main>
	</div>	
</body>
</html>