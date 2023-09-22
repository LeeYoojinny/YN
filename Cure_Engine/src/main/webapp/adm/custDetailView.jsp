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
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous"> -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" 
integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="css/admin/custDetailView_style.css">
<link rel="stylesheet" href="css/customer/reservationView_style.css">
</head>
<script type="text/javascript">
	function secretCheck(qna_num) {
		location.href = "qna_boardView.bo?qna_num="+qna_num;
	}
	
	function deleteCheck(user_id) {
		if(!confirm("탈퇴 처리 하시겠습니까?")){
			return false;
		}else {
			location.href="custDelete.adm?user_id="+user_id;
		}
		
	}
	
	function rejoinCheck(user_id) {
		if(!confirm("재가입 처리 하시겠습니까?")){
			return false;
		}else {
			location.href="custRejoin.adm?user_id="+user_id;
		}
		
	}
</script>
<body>
	<h2>${custInfo.user_id} 회원 정보</h2>
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
            			<td><fmt:formatDate pattern="yyyy/MM/dd" value="${custInfo.user_joindate}"/></td>
            		</tr>
            		<tr>
            			<td class="subject class">생년월일</td>
            			<td>${custInfo.user_birth}</td>
            		</tr>
            		<tr>
            			<td class="subject gender">성별</td>
            			<td>${custInfo.user_gender}</td>
            		</tr>
            		<tr>
            			<td class="subject email">이메일</td>
            			<td>${custInfo.user_email}</td>
            		</tr>
            		<tr>
            			<td class="subject phone">전화번호</td>
            			<td>${custInfo.user_phone}</td>
            		</tr>
            		<tr>
            			<td class="subject addr">주소</td>
            			<td>(${custInfo.user_zipcode})&nbsp; ${custInfo.user_address1}&nbsp; ${custInfo.user_address2}</td>
            		</tr>
            	</table>
            </div>
        </div>
    </div>
    <div class="accordion-item">
        <h2 class="accordion-header">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" 
            aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">
                <i class="fa-solid fa-chalkboard-user" style="padding-right:5px;"></i>게시글
            </button>
        </h2>
        <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse">
            <div class="accordion-body">
               <table class="table table-striped table-hover text-center">
					<thead>
					<tr>
						<th>번호</th>	
						<th>게시판</th>				
						<th>제목</th>				
						<th>작성일</th>				
					</tr>
					</thead>
					<tbody>
						<c:if test="${boardList ne null}">
							<c:set var="num" />
							<c:forEach var="board" items="${boardList}" varStatus="loop">
					            <tr>
					                <td>${loop.index + 1}</td>
					                <td>문의게시판</td>			                
					                <td class="subject title">						               
					                	<c:if test="${!fn:contains(board.qna_title, 'q')}">
											<a onclick="secretCheck('${board.qna_num}')">${board.qna_title}</a>
										</c:if>			
										<c:if test="${fn:contains(board.qna_title, 'q')}">
											<c:forEach var="code" items="${allCode}">
												<c:if test="${code.code_category == 'qna_subject'}">
													<c:if test="${board.qna_title == code.code_name}">
													<a onclick="secretCheck('${board.qna_num}')">${code.code_value}</a>
													</c:if>
												</c:if>
											</c:forEach>		
										</c:if>															
					                </td>			              
					                <td><fmt:formatDate pattern="yyyy/MM/dd hh:mm" value="${board.qna_date}"/></td>			                
					            </tr>
					            <c:set var="num" value="${loop.index + 1}" />
					        </c:forEach>
					        <c:forEach var="review" items="${custReview}" varStatus="loop">
					            <tr>
					                <td>${num + loop.index + 1}</td>
					                <td>리뷰게시판</td>			                
					                <td class="subject title">				                	
										<a href="review_boardView.bo?review_num=${review.review_num}">${review.review_title}</a>														
					                </td>			              
					                <td><fmt:formatDate pattern="yyyy/MM/dd hh:mm" value="${review.review_date}"/></td>			                
					            </tr>
					        </c:forEach>
					    </c:if>
						<c:if test="${empty boardList and empty custReview}">
				            <tr>
				                <td colspan="4">게시글이 없습니다.</td>
				            </tr>
				        </c:if>
					</tbody>
				</table>
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
            <c:if test="${myReservation ne null }">
                <form method="post">
					<table>
						<tr id="firstLine">
							<th>No.</th>
							<th>신청차량</th>
							<th>딜러</th>
							<th>날짜</th>
							<th>시간</th>
							<th>예약상태</th>
						</tr>
						<c:forEach var="mrev" items="${myReservation}" varStatus="status">
							<tr class="contents">
								<td id="item_no" >${status.count}</td>
								<td>
									<a href="carDetailView.usr?car_id=${mrev.car_id}">${mrev.car_id}</a>
								</td>
								<c:forEach var="code" items="${allCode}">
				    				<c:if test="${code.code_category == 'user_name'}">
				    					<c:if test="${mrev.dealer_id == code.code_name}">
											<td>${code.code_value}</td>
										</c:if>
									</c:if>
								</c:forEach>			
								<td>${mrev.rev_date}</td>
								<td>${mrev.rev_time}</td>
								<c:choose>
									<c:when test="${mrev.approve_YN eq 'Y'}"><td>예약승인</td></c:when>
									<c:when test="${mrev.approve_YN eq 'W'}"><td>예약대기중</td></c:when>
									<c:when test="${mrev.approve_YN eq 'N'}"><td>예약거절</td></c:when>
								</c:choose>
							</tr>
							<tr id="space"><td colspan="6"></td></tr>
						</c:forEach>
					</table>
				</form>
			</c:if>
				<c:if test="${myReservation == null }">
					<div class="nothing">${custInfo.user_id}님의 신청한 예약이 없습니다.</div>
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
			</c:if>
			<c:if test="${orderList == null }">
				<div class="nothing">주문내역이 없습니다.</div>
			</c:if>
			</div>
        </div>
    </div>
</div>
<div id="underBt">
	<c:if test="${display_num eq 1}">
		<button onclick="location.href='customerList.adm'">회원목록</button>
	</c:if>
	<c:if test="${display_num eq 2}">
		<button onclick="location.href='custExpireList.adm'">회원목록</button>
	</c:if>
	<c:if test="${display_num eq 1}">
		<button onclick="deleteCheck('${custInfo.user_id}')">탈퇴처리</button>
	</c:if>
	<c:if test="${display_num eq 2}">
		<button onclick="rejoinCheck('${custInfo.user_id}')">재가입처리</button>
	</c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>