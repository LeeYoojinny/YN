<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" 
integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="css/admin/myCarOrderList_style.css">
</head>
<script type="text/javascript">
	function cancelOrder(car_id) {
		if(confirm("주문 취소 하시겠습니까?")){
			location.href="cancelOrder.cust?car_id="+car_id;
		}else {
			return false;
		}
	}
</script>
<body>
	<div class="wrap_allCarList">
		<div class="subject"></div>
		<c:if test="${orderList != null }">		
			<form method="post">
			<table>				
				<tr id="firstLine">					
					<th>No.</th>
					<th></th>
					<th>주문목록</th>
					<th colspan="2">상태</th>					
				</tr>
				<c:set var="num" value="${pageInfo.listCount}"></c:set>				
				<c:forEach var="orderCar" items="${carList}" varStatus="status">
					<tr class="contents">						
						<td rowspan="3" id="item_no">${num}</td>
						<td rowspan="3" id="main_img">
							<a href="carDetailView.usr?car_id=${orderCar.car_id}" class="image-link">
								<img src="upload/carRegist_images/${orderCar.car_image1}" alt="상품상세보기">
							</a>
						</td>
						<td id="explain1">
						<c:forEach var="code" items="${allCode}">
							<c:if test="${code.code_category == 'car_brand'}">
								<c:if test="${orderCar.car_brand == code.code_name}">${code.code_value}</c:if>
							</c:if>
						</c:forEach>					
						&nbsp;${orderCar.car_year}연식 ${orderCar.car_name}</td>
						<c:set var="ordernum" />
						<c:forEach var="order" items="${orderList}">															
							<c:if test="${order.car_id == orderCar.car_id}">
							<c:set var="ordernum" value="${order.ordernum }" />
								<c:if test="${order.order_approve_YN eq 'W' && order.cancel_YN eq 'N'}">
								<td rowspan="3" id="orderStatus">승인대기</td>								
								<td rowspan="3" id="bt">
									<button onclick="cancelOrder('${orderCar.car_id}'); return false;">주문취소</button>
								</td>	
								<td>							
								</c:if>
								<c:if test="${order.order_approve_YN eq 'Y'}">
									<td rowspan="3" id="orderStatus">주문승인</td>	
									<td rowspan="3" id="bt">
									<c:set var="found" value="false" />
									<c:forEach var="rev_num" items="${reviewOrdernum}">
										<c:if test="${rev_num eq ordernum}">
											<button disabled style="width: 6.5rem;">리뷰작성완료</button>
											<c:set var="found" value="true"/>
										</c:if>
									</c:forEach>
									<c:if test="${not found}">
										<button onclick="location.href='review_boardWrite.bo?car_id=${order.car_id}&ordernum=${order.ordernum}'; return false;">리뷰작성</button>
									</c:if>
									</td>									
								</c:if>
								<c:if test="${order.order_approve_YN eq 'N'}">
									<td rowspan="3" id="orderStatus">주문거절</td>
									<td rowspan="3" id="bt"></td>																
								</c:if>
								<c:if test="${order.order_approve_YN eq 'W' && order.cancel_YN eq 'Y'}">
									<td rowspan="3" id="orderStatus">주문취소</td>
									<td rowspan="3" id="bt"></td>																
								</c:if>									
							</c:if>							
						</c:forEach>						
					</tr>
					<c:forEach var="pay" items="${allpayList}">
						<c:if test="${pay.ordernum == ordernum}">
							<tr class="contents">
								<td id="explain2">결제금액 : <fmt:formatNumber value="${pay.pay_price}" pattern="#,###" />원</td>
							</tr>
						</c:if>
					</c:forEach>		
					<tr class="contents">
						<c:forEach var="order" items="${orderList}">															
								<c:if test="${order.car_id == orderCar.car_id}">
									<td id="explain3">
										<span>주문일자 : <fmt:formatDate value="${order.order_date}" pattern="yyyy-MM-dd HH:mm"/></span>
										<a href="orderDetail.adm?ordernum=${order.ordernum}&car_id=${order.car_id}&display=1"><i class="fa-solid fa-magnifying-glass"></i>주문상세보기</a>
									</td>
								</c:if>
						</c:forEach>
					</tr>
					<tr id="space"><td colspan="6"></td></tr>
					<c:set var="num" value="${num-1}"></c:set>
				</c:forEach>
			</table>
			</form>

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
			<div class="nothing">${user_id}님의 주문내역이 없습니다.</div>
		</c:if>
	</div>
</body>
</html>