<%@page import="vo.QnABoard"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.QnABoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>글목록 보기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" 
integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="css/user/qna/boardList_style.css">
<!-- <script src="js/jquery-3.1.1.js"></script>
<script src="js/bootstrap.js"></script> -->
</head>
<script type="text/javascript">
	function loginCheck(){
		var user_id = '<c:out value="${user_id}" />';
		
		if(!user_id) {
			alert("로그인 후 문의 가능합니다.");
			location.href="userLogin.usr";
			return false;
		}else {
			location.href="qna_boardWrite.bo";			
		}	
	}
	
	function secretCheck(secretYN,qna_num,user_category,list_num) {
		if(secretYN == 'Y' && (user_category == 'customer' || user_category == '')) {
			location.href = "qna_pwCheckForm.bo?qna_num="+qna_num+"&display_num=3"+"&list_num="+list_num;
		}else {
			location.href = "qna_boardView.bo?qna_num="+qna_num+"&list_num="+list_num;
		}	
	}
	
	function searchCheck(){
		if(document.f.keyword.value == ""){
			alert("검색어를 입력해주세요.");
			return false;
		}
		
		document.f.submit();
	}
</script>


<body class="sb-nav-fixed">
<h2>문의게시판</h2>
<div class="container">
	<div class="card-body">
		<table class="table table-striped table-hover text-center">
			<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<c:if test="${user_category == 'dealer' or user_category eq 'admin'}">
					<th>상태</th>
				</c:if>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			</thead>
			<tbody>
				<c:if test="${boardList ne null}">
				<c:set var="num" value="${pageInfo.listCount}"></c:set>
					<c:forEach var="board" items="${boardList}" >
			            <tr>
			                <td>${num}</td>
			                <td>
				                <c:if test="${board.qna_replyNum ne 0}">
									<c:forEach var="code" items="${allCode}">
										<c:if test="${code.code_category == 'user_name'}">
											<c:if test="${board.user_id == code.code_name}">
												${code.code_value}
											</c:if>
										</c:if>
									</c:forEach>	
								</c:if>	
			                	<c:if test="${board.qna_replyNum eq 0}">
			                		${board.user_id}
			                	</c:if>
			                
			                </td>
			                <td id="subject">
				                <c:if test="${board.qna_replyNum eq 0}">
				                	<c:if test="${!fn:contains(board.qna_title, 'q')}">
										<a onclick="secretCheck('${board.secret_YN}','${board.qna_num}','${user_category}','${num}')">${board.qna_title}</a>
									</c:if>			
									<c:if test="${fn:contains(board.qna_title, 'q')}">
										<c:forEach var="code" items="${allCode}">
											<c:if test="${code.code_category == 'qna_subject'}">
												<c:if test="${board.qna_title == code.code_name}">
												<a onclick="secretCheck('${board.secret_YN}','${board.qna_num}','${user_category}','${num}')">${code.code_value}</a>
												</c:if>
											</c:if>
										</c:forEach>		
									</c:if>
								</c:if>
								<c:if test="${board.qna_replyNum ne 0}">
									<i class="fa-solid fa-reply fa-rotate-180"></i>
									<a onclick="secretCheck('${board.secret_YN}','${board.qna_num}','${user_category}','${num}')">
									${board.qna_title}</a>
								</c:if>							
			                </td>
			                <c:if test="${user_category eq 'dealer' || user_category eq 'admin'}">
				                <td>
					                <c:if test="${board.reply_YN eq 'N'}">
					                	<c:if test="${not empty board.car_id}">
					                		<c:forEach var="car" items="${carList}">
					                			<c:if test="${car.car_id eq board.car_id}">
					                				<c:if test="${car.dealer_id eq user_id || user_category eq 'admin'}">
					                					<span id="aNeed">답변필요</span>
					                				</c:if>
					                			</c:if>
					                		</c:forEach>
					                	</c:if>
					                	<c:if test="${empty board.car_id}">
					                		<span id="aNeed">답변필요</span>
					                	</c:if>
				                	</c:if>
				                	<c:if test="${board.reply_YN eq 'Y'}">
				                		<span id="aOk">답변완료</span>
				                	</c:if>
			                	</td>
			                </c:if>
			                
			                <td><fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${board.qna_date}"/></td>
			                <td>${board.qna_hit}</td>
			            </tr>
			            <c:set var="num" value="${num-1}"></c:set>
			        </c:forEach>
			    </c:if>
				<c:if test="${empty boardList}">
		            <tr>
		                <td colspan="6">게시글이 없습니다.</td>
		            </tr>
		        </c:if>
			</tbody>
		</table>
		
		<form action="qna_searchAction.bo" method="post">
			<select name="option">
				<option value="titleCotent" selected>제목+내용</option>
				<option value="user_id">작성자</option>
			</select>
			<input type="text" name="keyword" placeholder="검색어를 입력하세요">
			<button onclick="searchCheck()">검색</button>
			<c:if test="${user_category eq 'customer' or user_category eq null}">
				<a class="btn btn-default float-end" style="border:1px solid #ccc;" onclick="loginCheck()">글쓰기</a>
			</c:if>	
		</form>
		
		<div class="pageNum">
			<c:choose> 
					<c:when test="${pageInfo.page <= 1}">[이전]&nbsp;</c:when>
					<c:otherwise><a style="text-decoration:none" href="qna_boardList.bo?page=${pageInfo.page-1}">[이전]&nbsp;</a></c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1" varStatus="loop">
					<c:choose>
						<c:when test="${i == pageInfo.page}">${i}&nbsp;</c:when>
						<c:otherwise><a style="text-decoration:none" href="qna_boardList.bo?page=${i}">${i}</a>&nbsp;</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${pageInfo.page >= pageInfo.maxPage}">[다음]&nbsp;</c:when>
					<c:otherwise><a style="text-decoration:none" href="qna_boardList.bo?page=${pageInfo.page+1}">[다음]&nbsp;</a></c:otherwise>
				</c:choose>
		</div>
			
	</div>
</div> 

</body>
</html>