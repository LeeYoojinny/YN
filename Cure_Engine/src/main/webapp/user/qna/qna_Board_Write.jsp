<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" href="css/user/qna/boardWrite_style.css">
<title>글쓰기</title>
<script>
	function check(){
		var secretY = document.getElementById("secret_Y");
		
		if(!f.user_id.value.trim()){
			alert("이름을 입력해주세요.");
			f.user_id.focus();
			return false;
		}
		if(!f.qna_title.value.trim()){
			alert("제목을 입력해주세요.");
			f.qna_title.focus();
			return false;
		}
		if(!f.qna_content.value.trim()){
			alert("내용을 입력해주세요.");
			f.qna_content.focus();
			return false;
		}
		if (secretY.checked) {
            if (!f.qna_pw.value.trim()) {
                alert("비밀글을 선택한 경우 문의비밀번호를 입력해주세요.");
                f.qna_pw.focus();
                return false;
            }
        }
	
		f.submit();
	}
</script>
<style>
	#command{
		margin:0 auto;
		text-align:center;
	}
</style>
</head>
<body>
	<div class="container">
		<h2 class="mt-4">문의글 작성</h2>
		<form action="qna_BoardWriteAction.bo" method="post" name="f" enctype="multipart/form-data">
		<input type="hidden" name="car_id" value="${param.car_id}">
			<div class="mb-3 mt-3">
				<label class="form-label">작성자</label>
				<input class="form-control" type="text" name="user_id" value="${user_id}" readonly>
			</div>
			<div class="mb-3">
				<label class="form-label">제목</label>
				<c:if test="${param.car_id ne null}">
					<input class="form-control" type="text" name="qna_title" value="${param.car_id} 차량 문의" readonly>
				</c:if>
				<c:if test="${param.car_id eq null}">
					<select class="form-control" name="qna_title" >
						<option value="" disabled selected>&nbsp;==== 선택하세요 ====&nbsp;</option>
						<c:forEach var="code" items="${allCode}">
    						<c:if test="${code.code_category eq 'qna_subject'}">
								<option value="${code.code_name}">${code.code_value}</option>
							</c:if>
						</c:forEach>			
					</select>
				</c:if>
			</div>		
			<div class="mb-3">
				<label class="form-label">내용</label>
				<textarea class="form-control" style="resize:none;" cols="40" rows="20" name="qna_content" ></textarea>
			</div>
			<div class="mb-3">
				<label class="form-label">파일첨부</label>
				<input class="form-control" type="file" name="qna_file" id="qna_file" >
			</div>
			<div class="mb-3">
				<label class="form-label">비밀글여부</label>
				<input type="radio" name="secret_YN" value="Y" id="secret_Y" checked>&nbsp;예&nbsp;
				<input type="radio" name="secret_YN" value="N" id="secret_N">&nbsp;아니오
			</div>	
			<div class="mb-3" id="passwordRow">
				<label class="form-label" id="passwordInputRow">비밀번호</label>
				<input class="form-control" type="password" name="qna_pw" id="qna_pw">
			</div>
			<div id="command">
				<input class="btn btn-outline-secondary" type="submit" value="등록" onclick="check(); return false;"/>&nbsp;&nbsp;
				<input class="btn btn-outline-secondary" type="reset" value="다시쓰기">
			</div>
		</form>
	</div>
	<script>
	    // 비밀글 라디오 버튼 클릭 이벤트 처리
	    var secretY = document.getElementById("secret_Y");
	    var secretN = document.getElementById("secret_N");
	    var passwordRow = document.getElementById("passwordRow");
	    var passwordInputRow = document.getElementById("passwordInputRow");
	
	    secretY.addEventListener("change", function () {
	        if (secretY.checked) {
	            passwordRow.style.display = "inline-block";
	            passwordInputRow.style.display = "inline-block";
	        } else {
	            passwordRow.style.display = "none";
	            passwordInputRow.style.display = "none"; 
	        }
	    });
	
	    secretN.addEventListener("change", function () {
	        if (secretN.checked) {
	            passwordRow.style.display = "none";
	            passwordInputRow.style.display = "none";
	        }
	    });
	</script>
</body>
</html>