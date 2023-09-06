<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/user/otherCarList_style.css">
</head>
<script type="text/javascript">
	function likeThis(like,car_id,car_price,car_image1,dealer_id,dealer_name) {
		var user_id = '<c:out value="${user_id}" />'
		
		if(!user_id) {
			alert("로그인 후 관심상품 등록이 가능합니다.");
			location.href="userLogin.usr";
			return false;
		}else {
			if(like == 0){
				location.href="userLikeThis.cust?car_id="+car_id+"&car_price="+car_price+
						"&car_image1="+car_image1+"&dealer_id="+dealer_id+"&dealer_name="+dealer_name+"&displayNum=3";
			}else {
				location.href="userUnlikeThis.cust?car_id="+car_id+"&dealer_id="+dealer_id+"&dealer_name="+dealer_name+"&displayNum=3";
			}
		}
	}
</script>
<body>
	<div class="wrap_allCarList">
		<c:if test="${otherCarList != null }">
			<div class="bt" style="width:80%; margin:auto;"><button onclick="location.href='allCarListView.usr'; return false;">모든상품목록</button></div>
			<form method="post">
			<h3>${dealer_name}딜러의 다른 상품을 보고 계십니다.</h3>
			<table>		
				<tr id="firstLine">
					<th>No.</th>
					<th></th>
					<th>판매목록</th>
				</tr>
				<c:forEach var="other" items="${otherCarList}" varStatus="status">
					<tr class="contents">
						<td rowspan="3" id="item_no">${status.count}</td>
						<td rowspan="3" id="main_img"><a href="carDetailView.usr?car_id=${other.car_id}"><img src="upload/carRegist_images/${other.car_image1}"></a></td>
						<td id="explain1">
						<c:forEach var="code" items="${allCode}">
							<c:if test="${code.code_category == 'car_brand'}">
								<c:if test="${other.car_brand == code.code_name}">${code.code_value}</c:if>
							</c:if>
						</c:forEach>					
						&nbsp;${other.car_year}연식 ${other.car_name}</td>
					</tr>
					<tr class="contents">
						<td id="explain2">${other.car_price}만원</td>
					</tr>
					<tr class="contents">
						<c:if test="${user_category eq null || user_category eq 'customer'}">
							<td id="explain3">							
			       	 			<c:set var="found" value="false" />
		                    	<c:forEach var="wish" items="${wishlist}">
		                        	<c:if test="${other.car_id eq wish.car_id}">
		                            	<img src="image/carList/red_like_icon.png" id="likeImage" 
		                                 onclick="likeThis(1,'${other.car_id}','${other.car_price}','${other.car_image1}','${dealer_id}','${dealer_name}')">
		                                 <c:set var="found" value="true"/>
		                        	</c:if>
		                    	</c:forEach>
		                    	<c:if test="${not found}">
		                        	<img src="image/carList/black_like_icon.png" id="likeImage" 
		                             onclick="likeThis(0,'${other.car_id}','${other.car_price}','${other.car_image1}','${dealer_id}','${dealer_name}')">
		                   		</c:if>
		       	 				${other.car_like}
		       	 			</td>
	       	 			</c:if>	       	 			
	       	 			<c:if test="${user_category eq 'dealer' || user_category eq 'admin'}">
							<td id="explain3"><img src="image/carList/red_like_icon.png">${other.car_like}</td>
						</c:if>						
					</tr>
					<tr id="space"><td colspan="3"></td></tr>
				</c:forEach>
			</table>
			</form>
		</c:if>
		<c:if test="${otherCarList == null }">
			<div class="nothing">${dealer_name}딜러의 판매차량이 없습니다.</div>
		</c:if>
	</div>
</body>
</html>