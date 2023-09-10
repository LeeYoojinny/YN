<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cure Engine</title>
</head>
<script type="text/javascript">
	function writeCheck(){
		var secretY = document.getElementById("secret_Y");
        var qnaPwInput = document.getElementById("qna_pw");
		
        if(!document.f.qna_content.value) {
			alert("내용을 입력해주세요.");
			document.f.qna_content.focus();
			return false;
		}		

        if (secretY.checked) {
            var qnaPwValue = qnaPwInput.value.trim();
            if (qnaPwValue === "") {
                alert("비밀글을 선택한 경우 문의비밀번호를 입력해주세요.");
                qnaPwInput.focus();
                return false;
            }
        }
        
        document.f.submit;
	}
</script>
<body>
	<h3>문의하기</h3>
	<form action="custQnaRequest.cust" method="post" name="f">
	<input type="hidden" name="qna_num">
		<table>	
			<tr>
				<td>제목</td>				
			</tr>
			<tr>
				<td>
					<input type="text" name="qna_title" value="${car_id} 문의합니다." readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>문의내용</td>
			</tr>
			<tr>
				<td>
					<textarea name="qna_content" style="width:100%;height:200px; resize: none; border: 1px solid #ccc;"></textarea>
				</td>
			</tr>
			<tr>
				<td>첨부파일</td>
			</tr>
			<tr>
				<td>
					<input type="file" name="qna_file">
				</td>
			</tr>
			<tr>
				<td>비밀글</td>
			</tr>
			<tr>
				<td>
					<input type="radio" name="secret_YN" value="Y" id="secret_Y" checked>Y
        			<input type="radio" name="secret_YN" value="N" id="secret_N">N
				</td>
			</tr>
			<tr>
				<td id="passwordRow">문의비밀번호</td>
			</tr>
			<tr>
				<td id="passwordInputRow">
					<input type="text" name="qna_pw" id="qna_pw">
				</td>
			</tr>
			<tr>
				<td>
					<button onclick="writeCheck(); return false;">문의하기</button>
				</td>
			</tr>
		</table>
	</form>
	<script>
	    // 비밀글 라디오 버튼 클릭 이벤트 처리
	    var secretY = document.getElementById("secret_Y");
	    var secretN = document.getElementById("secret_N");
	    var passwordRow = document.getElementById("passwordRow");
	    var passwordInputRow = document.getElementById("passwordInputRow"); // 변경된 id
	
	    secretY.addEventListener("change", function () {
	        if (secretY.checked) {
	            passwordRow.style.display = "table-row";
	            passwordInputRow.style.display = "table-row"; // 변경된 id
	        } else {
	            passwordRow.style.display = "none";
	            passwordInputRow.style.display = "none"; // 변경된 id
	        }
	    });
	
	    secretN.addEventListener("change", function () {
	        if (secretN.checked) {
	            passwordRow.style.display = "none";
	            passwordInputRow.style.display = "none"; // 변경된 id
	        }
	    });
	</script>
</body>
</html>