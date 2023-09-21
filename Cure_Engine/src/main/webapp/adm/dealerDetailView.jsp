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
<link rel="stylesheet" href="css/admin/mySaleCarList_style.css">
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
	<h2>${dealerInfo.user_id} 딜러 정보</h2>
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
            <c:if test="${mySaleCarList ne null }">
            	<form method="post">
					<table>
						<tr id="firstLine">
							<th>No.</th>
							<th></th>
							<th>판매목록</th>							
						</tr>
						<c:set var="startNo" value="${(pageInfo.page - 1) * 5 + 1}" />
						<c:forEach var="myCar" items="${mySaleCarList}" varStatus="status">
							<tr class="contents">
								<td rowspan="3" id="item_no">${startNo + status.count-1}</td>
								<td rowspan="3" id="main_img">
									<a href="carDetailView.usr?car_id=${myCar.car_id}" class="image-link">
										<img src="upload/carRegist_images/${myCar.car_image1}">
										<c:if test="${myCar.sale_YN eq 'W'}">
									        <div class="sold-out-overlay">
									            <p class="sold-out-text">예약중</p>
									        </div>
									    </c:if>
									    <c:if test="${myCar.sale_YN eq 'N'}">
									        <div class="sold-out-overlay">
									            <p class="sold-out-text">판매완료</p>
									        </div>
									    </c:if>
									</a>
								</td>
								<td id="explain1">
								<c:forEach var="code" items="${allCode}">
									<c:if test="${code.code_category == 'car_brand'}">
										<c:if test="${myCar.car_brand == code.code_name}">${code.code_value}</c:if>
									</c:if>
								</c:forEach>					
								&nbsp;${myCar.car_year}연식 ${myCar.car_name}</td>
							</tr>
							<tr class="contents">
								<td id="explain2"><fmt:formatNumber value="${myCar.car_price}" pattern="#,###" />만원</td>
							</tr>
							<tr class="contents">
								<td id="explain3"><img src="image/carList/red_like_icon.png">${myCar.car_like}</td>
							</tr>
							<tr id="space"><td colspan="5"></td></tr>
						</c:forEach>
					</table>
				</form>
				<div class="pageNum">
					<c:choose> 
						<c:when test="${pageInfo.page <= 1}">[이전]&nbsp;</c:when>
						<c:otherwise><a style="text-decoration:none" href="dealerDetailView.adm?user_id=${dealerInfo.user_id}&display_num=1&page=${pageInfo.page-1}">[이전]&nbsp;</a></c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1" varStatus="loop">
						<c:choose>
							<c:when test="${i == pageInfo.page}">${i}&nbsp;</c:when>
							<c:otherwise><a style="text-decoration:none" href="dealerDetailView.adm?user_id=${dealerInfo.user_id}&display_num=1&page=${i}">${i}</a>&nbsp;</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${pageInfo.page >= pageInfo.maxPage}">[다음]&nbsp;</c:when>
						<c:otherwise><a style="text-decoration:none" href="dealerDetailView.adm?user_id=${dealerInfo.user_id}&display_num=1&page=${pageInfo.page+1}">[다음]&nbsp;</a></c:otherwise>
					</c:choose>
				</div>
				</c:if>
				<c:if test="${mySaleCarList == null }">
					<div class="nothing">${dealerInfo.user_id}님의 판매차량이 없습니다.</div>
				</c:if>            
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
            <c:if test="${reservation ne null}">
            	<table>
					<tr id="firstLine">
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
							<td id="item_no">${startNo + status.count-1}</td>
							<td>${rev.car_id}</td>
							<td>${rev.user_id}</td>
							<td>${rev.rev_date}</td>
							<td>${rev.rev_time}</td>
							<c:if test="${rev.approve_YN eq 'W'}">
								<td style="line-height:2.5rem;">대기중</td>
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
				</c:if>
				<c:if test="${reservation == null}">
					<div class="nothing">${dealerInfo.user_id}님의 예약 신청건이 없습니다.</div>
				</c:if>
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
	           <c:if test="${orderList != null }">				
				<table style="width:90%;">					
					<tr id="firstLine">						
						<th>No.</th>
						<th>차량정보</th>
						<th>주문내용</th>
						<th>상태</th>						
					</tr>					
					<c:forEach var="order" items="${orderList}" varStatus="status">										
						<tr class="contents">								
							<td >${status.count}</td>
							<td>
								<a href="carDetailView.usr?car_id=${order.car_id}" class="image-link">
									${order.car_id}												
								</a>
							</td>
							<td>
								<a href="orderDetail.adm?ordernum=${order.ordernum}&car_id=${order.car_id}&display=2">
								<i class="fa-solid fa-magnifying-glass"></i>주문상세보기</a>
							</td>							
							<c:choose>
		                   		<c:when test="${order.order_approve_YN eq 'W' && order.cancel_YN eq 'N'}">														
									<td >승인대기</td>													
								</c:when>								 
								<c:when test="${order.order_approve_YN eq 'Y'}">
										<td>주문승인</td>														
								</c:when>
								<c:when test="${order.order_approve_YN eq 'N'}">
									<td>주문거절</td>																					
								</c:when>
								<c:when test="${order.order_approve_YN eq 'W' && order.cancel_YN eq 'Y'}">
									<td>주문취소</td>																			
								</c:when>						
							</c:choose>							
						</tr>																
					</c:forEach>				
				</table>	
				<div class="pageNum">
					<c:choose> 
						<c:when test="${pageInfo.page <= 1}">[이전]&nbsp;</c:when>
						<c:otherwise><a style="text-decoration:none" href="mySaleCar.adm?page=${pageInfo.page-1}">[이전]&nbsp;</a></c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1" varStatus="loop">
						<c:choose>
							<c:when test="${i == pageInfo.page}">${i}&nbsp;</c:when>
							<c:otherwise><a style="text-decoration:none" href="mySaleCar.adm?page=${i}">${i}</a>&nbsp;</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${pageInfo.page >= pageInfo.maxPage}">[다음]&nbsp;</c:when>
						<c:otherwise><a style="text-decoration:none" href="mySaleCar.adm?page=${pageInfo.page+1}">[다음]&nbsp;</a></c:otherwise>
					</c:choose>
				</div>
			</c:if>
			<c:if test="${orderList == null }">
				<div class="nothing">주문내역이 없습니다.</div>
			</c:if>
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