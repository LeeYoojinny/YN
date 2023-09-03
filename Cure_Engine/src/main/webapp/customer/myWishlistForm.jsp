<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/customer/myWishlist_style.css">
</head>
<script type="text/javascript">
	function checkAll(theForm){
		//체크박스가 하나인지 여러개인지 확인
		if(theForm.remove.length == undefined){//체크박스(remove)의 배열길이가 정의되어 있지 않다면(=장바구니에 상품이 1개라면)
			theForm.remove.checked = theForm.allCheck.checked;			
		}else {//체크박스(remove)의 배열길이가 정의되어 있다면(=장바구니에 상품이 2개이상이면)
			for(var i=0; i<theForm.remove.length; i++){
				theForm.remove[i].checked = theForm.allCheck.checked;
			}			
		}		
	}
	
	function removeCheck() {
		if
	}
</script>
<body>
<div class="wrap_allWish">
	<c:if test="${wishlist != null }">
		<form method="post">
			<table>
				<tr id="allRemove">
					<td colspan="2"><button onclick="removeCheck(); return false;">선택삭제</button></td>
					<td colspan="3"></td>
				</tr>
				<tr id="firstLine">
					<th><input type="checkbox" name="allCheck" onclick="checkAll(this.form)"></th>
					<th>No.</th>
					<th></th>
					<th>관심상품</th>
					<th></th>
				</tr>
				<c:forEach var="wish" items="${myWishCar}" varStatus="status">
					<tr class="contents">
						<td rowspan="3" id="check_remove"><input type="checkbox" name="remove" value="${wish.car_id}"></td>
						<td rowspan="3" id="item_no">${status.count}</td>
						<td rowspan="3" id="main_img"><a href="carView.usr?car_id=${wish.car_id}"><img src="upload/carRegist_images/${wish.car_image1}"></a></td>
						<td id="explain1">
						<c:choose>
							<c:when test="${wish.car_brand == 'benz'}">벤츠</c:when>
							<c:when test="${wish.car_brand == 'tesla'}">테슬라</c:when>
							<c:when test="${wish.car_brand == 'ferrari'}">페라리</c:when>
							<c:when test="${wish.car_brand == 'bmw'}">BMW</c:when>
							<c:when test="${wish.car_brand == 'audi'}">아우디</c:when>
							<c:when test="${wish.car_brand == 'maserati'}">마세라티</c:when>
							<c:when test="${wish.car_brand == 'bentley'}">벤틀리</c:when>
							<c:when test="${wish.car_brand == 'cadillac'}">캐딜락</c:when>
						</c:choose>						
						&nbsp;${wish.car_year}연식 ${wish.car_name}</td>
						<td rowspan="3" id="bt">
							<button onclick="#">예약하기</button>
							<button onclick="#">주문하기</button>
						</td>
					</tr>
					<tr class="contents">
						<td id="explain2">
							<fmt:formatNumber value="${wish.car_price}" pattern="#,###" />만원
						</td>
					</tr>
					<tr class="contents">
						<td id="explain3"><img src="image/carList/red_like_icon.png">${wish.car_like}</td>
					</tr>
					<tr id="space"><td colspan="5"></td></tr>
				</c:forEach>
			</table>
		</form>
	</c:if>
	<c:if test="${wishlist == null }">
		<div>${user_id}님의 관심상품이 없습니다.</div>
	</c:if>
</div>

</body>
</html>