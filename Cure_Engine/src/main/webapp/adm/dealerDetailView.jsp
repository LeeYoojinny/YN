<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" 
integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="css/admin/custDetailView_style.css">
</head>
<script type="text/javascript">

	function deleteCheck(user_id) {
		if(!confirm("탈퇴 처리 하시겠습니까?")){
			return false;
		}else {
			location.href="adminDealerDelete.adm?user_id="+user_id;
		}
		
	}
	
	function rejoinCheck(user_id) {
		if(!confirm("재가입 처리 하시겠습니까?")){
			return false;
		}else {
			location.href="dealerRejoin.adm?user_id="+user_id;
		}
		
	}
</script>
<body>
	<h2>${dealerInfo.user_id} 회원 정보</h2>
	<div class="accordion" id="accordionPanelsStayOpenExample">
    <div class="accordion-item">
        <h2 class="accordion-header">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" 
            aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
                <i class="fa-solid fa-circle-user" style="padding-right:5px;"></i>기본정보
            </button>
        </h2>
        <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show">
            <div class="accordion-body">
            	<table>
            		<tr>
            			<td class="subject date">가입일</td>
            			<td><fmt:formatDate pattern="yyyy/MM/dd" value="${dealerInfo.user_joindate}"/></td>
            		</tr>
            		<tr>
            			<td class="subject class">생년월일</td>
            			<td>${dealerInfo.user_birth}</td>
            		</tr>
            		<tr>
            			<td class="subject gender">성별</td>
            			<td>${dealerInfo.user_gender}</td>
            		</tr>
            		<tr>
            			<td class="subject email">이메일</td>
            			<td>${dealerInfo.user_email}</td>
            		</tr>
            		<tr>
            			<td class="subject phone">전화번호</td>
            			<td>${dealerInfo.user_phone}</td>
            		</tr>
            		<tr>
            			<td class="subject addr">주소</td>
            			<td>(${dealerInfo.user_zipcode})&nbsp; ${dealerInfo.user_address1}&nbsp; ${dealerInfo.user_address2}</td>
            		</tr>
            	</table>
            </div>
        </div>
    </div>
    <div class="accordion-item">
        <h2 class="accordion-header">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" 
            aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">
                <i class="fa-solid fa-cart-arrow-down" style="padding-right:5px;"></i>판매 차량
            </button>
        </h2>
        <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse">
            <div class="accordion-body">
                <strong>This is the third item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
            </div>
        </div>
    </div>
    <div class="accordion-item">
        <h2 class="accordion-header">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseThree" 
            aria-expanded="false" aria-controls="panelsStayOpen-collapseThree">
                <i class="fa-solid fa-car" style="padding-right:5px;"></i>시승 예약 내역
            </button>
        </h2>
        <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse">
            <div class="accordion-body">
                <strong>This is the third item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
            </div>
        </div>
    </div>
    <div class="accordion-item">
        <h2 class="accordion-header">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseFour" 
            aria-expanded="false" aria-controls="panelsStayOpen-collapseFour">
                <i class="fa-regular fa-credit-card" style="padding-right:5px;"></i>주문내역
            </button>
        </h2>
        <div id="panelsStayOpen-collapseFour" class="accordion-collapse collapse">
            <div class="accordion-body">
                <strong>This is the fourth item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
            </div>
        </div>
    </div>
</div>
<div id="underBt">
	<c:if test="${display_num eq 1}">
		<button onclick="location.href='dealerList.adm'">딜러목록</button>
	</c:if>
	<c:if test="${display_num eq 2}">
		<button onclick="location.href='custExpireList.adm'">딜러목록</button>
	</c:if>
	<c:if test="${display_num eq 1}">
		<button onclick="deleteCheck('${dealerInfo.user_id}')">탈퇴처리</button>
	</c:if>
	<c:if test="${display_num eq 2}">
		<button onclick="rejoinCheck('${dealerInfo.user_id}')">재가입처리</button>
	</c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>