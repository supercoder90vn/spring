<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<h2>Create New Account</h2>
<sf:form id="details"
  method="post"
  action="${pageContext.request.contextPath}/createAccount" 
  commandName="user">
	<table class="formtable">
		<tr>
			<td class="label">Username:</td>
			<td><sf:input class="control" path="username" 
					type="text" /><br />
			<div class="error">
					<sf:errors path="username"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Name:</td>
			<td><sf:input class="control" path="name" 
					type="text" /><br />
			<div class="error">
					<sf:errors path="name"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Email:</td>
			<td><sf:input class="control" path="email" 
					type="text" /> <br />
				<div class="error">
					<sf:errors path="email"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Password:</td>
			<td><sf:input id="password" class="control" path="password"
					type="password" /><br />
				<div class="error">
					<sf:errors path="password"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Confirm Password:</td>
			<td><input id="confirmPass" class="control" name="confirmPass"
				type="password" /><br />
				<div id="matchPass"></div></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" value="Create account" type="submit" /></td>
		</tr>
	</table>
</sf:form>


