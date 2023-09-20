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
		if(!f.review_content.value.trim()){
			alert("내용을 입력해주세요.");
			f.review_content.focus();
			return false;
		}
		f.submit();
	}
</script>
<body>
	<div class="container">
	<h2 class="mt-4">게시글 수정</h2>
		<form action="qna_boardUpdateAction.bo" method="post" name="f" enctype="multipart/form-data">
			<input type="hidden" name="qna_num" value="${board.review_num}">
			<input type="hidden" name="car_id" value="${board.car_id}">
			<div class="mb-3 mt-3">
				<label class="form-label">작성자</label>
				<input class="form-control" type="text" name="user_id" value="${user_id}" readonly>
			</div>
			<div class="mb-3">
				<label class="form-label">제목</label>				
				<input class="form-control" type="text" name="review_title" value="${board.review_title}" readonly>			
			</div>		
			<div class="mb-3">
				<label class="form-label">내용</label>
				<textarea class="form-control" style="resize:none;" cols="40" rows="20" name="review_content"
				placeholder="====== 구매해주신 차량에 대한 솔직한 평을 남겨주시길 바랍니다. (악의적인 비방은 통보없이 삭제 될 수 있습니다.)=====">${board.review_content}</textarea>
			</div>
			<div class="mb-3">
				<label class="form-label">파일첨부</label>
				<input class="form-control" type="file" name="review_file1" id="review_file1" accept="image/*">
				<c:if test="${not empty board.review_file1_origin}">
					<img src="upload/qna_file/${board.review_file1}" id="${board.review_file1}" width="70px;">
					<i class="fa-regular fa-trash-can delete-img" data-image-name="${board.review_file1}" data-update-field="update_img1"></i>
				</c:if>
				<input type="hidden" name="update_img1" value="noChange">
				<input type="hidden" name="origin_img_sys1" value="${board.review_file1}">
				<input type="hidden" name="origin_img_ori1" value="${board.review_file1_origin}">
				
				<input class="form-control" type="file" name="review_file2" id="review_file2" accept="image/*">
				<c:if test="${not empty board.review_file2_origin}">
					<img src="upload/qna_file/${board.review_file2}" id="${board.review_file2}" width="70px;">
					<i class="fa-regular fa-trash-can delete-img" data-image-name="${board.review_file2}" data-update-field="update_img2"></i>
				</c:if>
				<input type="hidden" name="update_img2" value="noChange">
				<input type="hidden" name="origin_img_sys2" value="${board.review_file2}">
				<input type="hidden" name="origin_img_ori2" value="${board.review_file2_origin}">
				
				<input class="form-control" type="file" name="review_file3" id="review_file3" accept="image/*">
				<c:if test="${not empty board.review_file3_origin}">
					<img src="upload/qna_file/${board.review_file3}" id="${board.review_file3}" width="70px;">
					<i class="fa-regular fa-trash-can delete-img" data-image-name="${board.review_file3}" data-update-field="update_img3"></i>
				</c:if>
				<input type="hidden" name="update_img3" value="noChange">
				<input type="hidden" name="origin_img_sys3" value="${board.review_file3}">
				<input type="hidden" name="origin_img_ori3" value="${board.review_file3_origin}">				
			</div>
			<section id="command">
				<input class="btn btn-outline-secondary" type="submit" value="수정" onclick="check(); return false;"/>
				<input class="btn btn-outline-secondary" type="reset" value="다시쓰기">
				<input class="btn btn-outline-secondary" type="button" value="뒤로" onclick="history.go(-1)">			
			</section>
		</form>
	</div>
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