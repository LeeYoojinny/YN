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
	<!-- header -->
	<jsp:include page="../header.jsp"/>
	
	<c:if test="${showPage ne null}">
		<main>
			<div>
			<jsp:include page="${showPage}"/>
			</div>
		</main>
	</c:if>	
	<div>
		<jsp:include page="../footer.jsp"/>
	</div>
</body>
</html>