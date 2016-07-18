<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:choose>
	<c:when test="${hasOffer}">
		<a href="${pageContext.request.contextPath}/createOffer">Edit or delete your current offer.</a>
	</c:when>
	<c:otherwise>
		<a href="${pageContext.request.contextPath}/createOffer">Add a new offer</a>		
	</c:otherwise>
</c:choose>

<sec:authorize access="hasRole('ROLE_ADMIN')">
&nbsp;
	<a href="<c:url value='/admin'/>">Admin</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
&nbsp;
	<a href="<c:url value='/messages'/>">Messages (<span id="numberMessages">0</span>)</a>
</sec:authorize>



<script type="text/javascript">
function updateMessageLink(data){
	//alert(data.number);
	$("#numberMessages").text(data.number);
}
function onLoad(){
	updatePage();
	window.setInterval(updatePage,5000);//5 second
}

function updatePage(){
	//alert("Updating page.")
	$.getJSON("<c:url value="/getMessages"/>",updateMessageLink);

}
$(document).ready(onLoad);
</script>

