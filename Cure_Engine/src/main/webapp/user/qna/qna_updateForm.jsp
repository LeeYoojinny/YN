<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글수정</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" 
integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="css/user/qna/updateForm_style.css">
</head>
<script type="text/javascript">
	function check(){
		var secretY = document.getElementById("secret_Y");
		
		if(!f.qna_title.value.trim()){
			alert("제목을 선택해주세요.");
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
<body>
	<div class="container">
	<h2 class="mt-4">게시글 수정</h2>
		<form action="qna_boardUpdateAction.bo" method="post" name="f" enctype="multipart/form-data">
			<input type="hidden" name="qna_num" value="${board.qna_num}">
			<input type="hidden" name="car_id" value="${board.car_id}">
			<div class="mb-3 mt-3">
				<label class="form-label">작성자</label>
				<input class="form-control" type="text" name="user_id" value="${user_id}" readonly>
			</div>
			<div class="mb-3">
				<label class="form-label">제목</label>
				<c:if test="${!fn:contains(board.qna_title, 'q')}">
					<input class="form-control" type="text" name="qna_title" value="${board.qna_title}" readonly>
				</c:if>			
				<c:if test="${fn:contains(board.qna_title, 'q')}">
				<select class="form-control" name="qna_title" >
					<option value="" disabled selected>&nbsp;==== 선택하세요 ====&nbsp;</option>
						<c:forEach var="code" items="${allCode}">
							<c:if test="${code.code_category == 'qna_subject'}">
								<c:if test="${board.qna_title eq code.code_name}">
									<option value="${code.code_name}" selected>${code.code_value}</option>
								</c:if>
								<c:if test="${board.qna_title ne code.code_name}">
									<option value="${code.code_name}">${code.code_value}</option>
								</c:if>
							</c:if>
						</c:forEach>	
				</select>		
				</c:if>	
			</div>		
			<div class="mb-3">
				<label class="form-label">내용</label>
				<textarea class="form-control" style="resize:none;" cols="40" rows="20" name="qna_content">${board.qna_content}</textarea>
			</div>
			<div class="mb-3">
				<label class="form-label">파일첨부</label>
				<input class="form-control" type="file" name="qna_file" id="qna_file" >
				<c:if test="${board.qna_file_origin ne null}">
					<img src="upload/qna_file/${board.qna_file}" id="${board.qna_file}" width="50px;">
					<i class="fa-regular fa-trash-can delete-img" data-image-name="${board.qna_file}" data-update-field="update_img"></i>
				</c:if>
				<input type="hidden" name="update_img" value="noChange">
				<input type="hidden" name="origin_img_sys" value="${board.qna_file}">
				<input type="hidden" name="origin_img_ori" value="${board.qna_file_origin}">
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
			<section id="command">
				<input class="btn btn-outline-secondary" type="submit" value="수정" onclick="check(); return false;"/>
				<input class="btn btn-outline-secondary" type="reset" value="다시쓰기">
				<input class="btn btn-outline-secondary" type="button" value="뒤로" onclick="history.go(-1)">			
			</section>
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
	<script>
	    // delete-img 클릭 시 처리
	    var deleteButtons = document.querySelectorAll(".delete-img");
	
	    deleteButtons.forEach(function(button) {
	        button.addEventListener("click", function() {
	            var imageName = button.getAttribute("data-image-name");
	            console.log(imageName);
	            var updateField = button.getAttribute("data-update-field");
	            
	            var image = document.getElementById(imageName);
	            image.style.display = "none";
	            
	            button.style.display = "none";
	
	            // update 필드 값을 "change"로 설정
	            var updateFieldInput = document.querySelector("input[name='" + updateField + "']");
	            updateFieldInput.value = "change";
	        });
	    });
	</script>
</body>
</html>