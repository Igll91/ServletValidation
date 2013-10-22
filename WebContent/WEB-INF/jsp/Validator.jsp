<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- Bootstrap -->
<style type="text/css">
<%@
include file="../css/bootstrap.min.css"%>
<%@
include file="../css/custom.css"
%>
</style>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//code.jquery.com/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Validator</title>
</head>
<body class="centerBody">
	<div class="row">
		<h1>Validator Page</h1>
		
		<c:choose>
			<c:when test="${empty message}">
	        <h4>No file message.</h4>
	    </c:when>
			<c:otherwise>
				<h4>
					Message: <b><c:out value="${message}" /></b>!
				</h4>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${empty results}">
				<p>Check your file and upload it again.</p>
			</c:when>
			<c:otherwise>
				<table class="table table-hover">
					<c:forEach var="result" items="${results }">
						<tr>
							<td><c:out value="${result }"></c:out></td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>