<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript">
function onDeleteClick(){	
	var doDelete = confirm("Are you sure you want to dlete ths offer?");
	if(doDelete == false){
		event.preventDefault();	
	}
}
function onReady(){
	//alert("Hello");
	$("#delete").click(onDeleteClick);
}
$(document).ready(onReady);
</script>

<sf:form method="post"
	action="${pageContext.request.contextPath}/doCreate"
	commandName="offer">
	<sf:input type="hidden" path="id"/><!-- save value id -->
	<table class="formtable">
		<tr>
			<td class="label">Your offer:</td>
			<td><sf:textarea class="control" path="text"
					rows="10" cols="10"></sf:textarea><br /> <sf:errors 
					cssClass="error"></sf:errors></td>
		</tr>
 		<tr><td class="label"></td>	<td><input class="control" value="Save advert" type="submit" /></td></tr>
		
		<c:if test="${offer.id !=0}">
			<tr><td class="label"></td><td>&nbsp;</td></tr>
		<tr><td class="label"></td>	<td><input class="delete control" name="delete" id="delete"
			 value="delete this offer." type="submit" /></td></tr>
		</c:if>
		
	</table>
</sf:form>
