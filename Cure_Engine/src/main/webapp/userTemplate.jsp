<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cure Engine</title>
<style type="text/css">
	main {
		margin-top : 1.5rem;
	}

</style>
</head>
<body>
	<!-- header -->
	<jsp:include page="userHeader.jsp"/>
	
	<c:if test="${showPage ne null}">
		<main>
			<div>
			<jsp:include page="${showPage}"/>
			</div>
		</main>
	</c:if>
<%-- 	
	<div>
		<jsp:include page="userFooter.jsp"/>
	</div> --%>
</body>
</html>