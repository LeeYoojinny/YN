<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cure Engine</title>
<link rel="stylesheet" href="css/main_style.css">
</head>
<body>
	<%-- 스피너 --%>

	<jsp:include page="header.jsp"/>
	
	<c:if test="${showPage ne null}">
		<main id="page-content">
			<jsp:include page="${showPage}"/>
		</main>
	</c:if>	
	
	<div>
		<jsp:include page="footer.jsp"/>
	</div>
	
<!-- <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"></script> -->
</body>
</html>