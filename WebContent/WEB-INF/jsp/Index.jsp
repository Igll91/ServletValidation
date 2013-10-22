<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- Bootstrap -->
<style type="text/css">
<%@ include file="../css/bootstrap.min.css" %>
<%@ include file="../css/custom.css" %>
</style> 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//code.jquery.com/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body class="centerBody">
<div class="col-xs-6 centerDiv">
	<form method="POST" action="Validator.html" enctype="multipart/form-data">
		<div >
			<label for="exampleInputFile">File input</label> <input type="file" name="file" id="file" />
			<p class="help-block">Select file to be validated.</p>
			<input type="submit" value="Confirm" name="Confirm" id="Confirm" class="btn btn-default" />
		</div>
	</form>
</div>
</body>
</html>