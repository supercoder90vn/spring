<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<form method="post"
		action="${pageContext.request.contextPath}/doCreate">
		<table class="formtable">
			<tr >
				<td class="label">Name:</td>
				<td class="control"><input name="name" type="text" /></td>
			</tr>
			<tr >
				<td class="label">Email:</td>
				<td class="control"><input name="email" type="text" /></td>
			</tr>
			<tr >
				<td class="label">Your offer:</td>
				<td class="control"><textarea name="text" rows="10" cols="10"></textarea></td>
			</tr>
			<tr >
				<td class="label"></td>
				<td class="control"><input value="Create advert" type="submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>


