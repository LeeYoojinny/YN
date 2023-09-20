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
		var checkboxes = document.getElementsByName('remove');		
		var selectedCount = 0;

        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                selectedCount++;
            }
        }

        if (selectedCount === 0) {
            // 체크된 항목이 없으면 경고 메시지 표시 후 작업 취소
            alert('선택된 항목이 없습니다.');
            return false;
        } else {
            // 체크된 항목이 있으면 userRemoveWish.usr로 이동
            // 이때 선택된 체크박스의 값을 param으로 넘겨줄 수 있습니다.
            // 예: userRemoveWish.usr?car_ids=1,2,3 (선택된 car_id 값들)
            var selectedCarIds = [];
            for (var i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].checked) {
                    selectedCarIds.push(checkboxes[i].value);
                }
            }
            var param = 'car_ids=' + selectedCarIds.join(',');
            location.href = 'userRemoveWish.cust?' + param;
        }
    }
	
	function likeThis(like,car_id,car_price,car_image1) {
		var user_id = '<c:out value="${user_id}" />'
		
		if(!user_id) {
			alert("로그인 후 관심상품 등록이 가능합니다.");
			location.href="userLogin.usr";
			return false;
		}else {
			if(like == 0){
				location.href="userLikeThis.cust?car_id="+car_id+"&car_price="+car_price+"&car_image1="+car_image1+"&displayNum=2";
			}else {
				location.href="userUnlikeThis.cust?car_id="+car_id+"&displayNum=2";
			}
		}
	}
</script>
<body>
<h3>관심상품</h3>
<div class="wrap_allWish">
	<c:if test="${myWishCar != null }">
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
				<c:set var="startNo" value="${(pageInfo.page - 1) * 5 + 1}" />
				<c:forEach var="wish" items="${myWishCar}" varStatus="status">
					<tr class="contents">
						<td rowspan="3" id="check_remove"><input type="checkbox" name="remove" value="${wish.car_id}"></td>
						<td rowspan="3" id="item_no">${startNo + status.count-1}</td>
						<td rowspan="3" id="main_img">						
							<a href="carDetailView.usr?car_id=${wish.car_id}"  class="image-link">
							<img src="upload/carRegist_images/${wish.car_image1}">
							<c:if test="${wish.sale_YN eq 'W'}">
							        <div class="sold-out-overlay">
							            <p class="sold-out-text">예약중</p>
							        </div>
							    </c:if>
							    <c:if test="${wish.sale_YN eq 'N'}">
							        <div class="sold-out-overlay">
							            <p class="sold-out-text">판매완료</p>
							        </div>
							    </c:if>
							</a>
						</td>
						<td id="explain1">
						<c:forEach var="code" items="${allCode}">
							<c:if test="${code.code_category == 'car_brand'}">
								<c:if test="${wish.car_brand == code.code_name}">${code.code_value}</c:if>
							</c:if>
						</c:forEach>					
						&nbsp;${wish.car_year}연식 ${wish.car_name}</td>
						<td rowspan="3" id="bt">
							<c:if test="${wish.sale_YN eq 'Y'}">
								<button onclick="location.href='reservationForm.cust?user_id=${user_id}&car_id=${wish.car_id}&dealer_id=${wish.dealer_id}'; return false;">시승예약</button>
								<button onclick="location.href='orderFormStep1.cust?car_id=${wish.car_id}'; return false;">주문하기</button>
							</c:if>
							<c:if test="${wish.sale_YN eq 'W' || wish.sale_YN eq 'N'}">
								<button disabled>시승예약</button>
								<button disabled>주문하기</button>
							</c:if>	
						</td>
						
					</tr>
					<tr class="contents">
						<td id="explain2">
							<fmt:formatNumber value="${wish.car_price}" pattern="#,###" />만원
						</td>
					</tr>
					<tr class="contents">
						<td id="explain3">
                           	<img src="image/carList/red_like_icon.png" id="likeImage" 
                                onclick="likeThis(1,'${wish.car_id}','${wish.car_price}','${wish.car_image1}')">${wish.car_like}
	       	 			</td>
					</tr>
					<tr id="space"><td colspan="5"></td></tr>
				</c:forEach>
			</table>
		</form>
		<div class="pageNum">
			<c:choose> 
					<c:when test="${pageInfo.page <= 1}">[이전]&nbsp;</c:when>
					<c:otherwise><a style="text-decoration:none" href="myWishlist.cust?page=${pageInfo.page-1}">[이전]&nbsp;</a></c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1" varStatus="loop">
					<c:choose>
						<c:when test="${i == pageInfo.page}">${i}&nbsp;</c:when>
						<c:otherwise><a style="text-decoration:none" href="myWishlist.cust?page=${i}">${i}</a>&nbsp;</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${pageInfo.page >= pageInfo.maxPage}">[다음]&nbsp;</c:when>
					<c:otherwise><a style="text-decoration:none" href="myWishlist.cust?page=${pageInfo.page+1}">[다음]&nbsp;</a></c:otherwise>
				</c:choose>
		</div>
	</c:if>
	<c:if test="${myWishCar == null }">
		<div class="nothing">${user_id}님의 관심상품이 없습니다.</div>
	</c:if>
</div>

</body>
</html>