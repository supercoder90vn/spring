<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Hi there! Session:
	<%=session.getAttribute("name")%>
	<p />
	Request:
	<%=request.getAttribute("name")%>
	<p />
	Request (using EL): ${name}
	<p />
	<c:out value="${name}"></c:out>
	<p />
	<b>Use sql:query and c:forEach:</b></b><br/>
	<sql:query var="rs" dataSource="jdbc/spring">
		select id, name, email, text from offers
	</sql:query>
	<c:forEach var="row" items="${rs.rows}">
	    ID: ${row.id}<br />
	    Name: ${row.name}<br />
	</c:forEach>
	
	<p />
	<b>Use Controller+ Service+Dao</b> <br/>
	<c:forEach var="row" items="${offers}">
	    ID: ${row.id}<br />
	    Name: ${row.name}<br />
	</c:forEach>
</body>
</html>


