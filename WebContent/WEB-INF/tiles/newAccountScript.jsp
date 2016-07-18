<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">

function onLoad(){
	
	//alert("Hello");
	//checkPasswordsMatch()
	
	$("#password").keyup(checkPasswordsMatch);
	$("#confirmPass").keyup(checkPasswordsMatch);
	
	$("#details").submit(canSubmit);
}
function canSubmit(){
	
	var password = $("#password").val();
	var confirmPass = $("#confirmPass").val();
	if(password!=confirmPass){
		alert("<fmt:message key='UnmatchedPasswords.User.password'/>");
		return false;
	}else{
		return true;
	}
}
function checkPasswordsMatch(){
	var password = $("#password").val();
	var confirmPass = $("#confirmPass").val();
	if(password.length>3 || confirmPass.length >3){	
		if(password == confirmPass){
			$("#matchPass").text("<fmt:message key='MatchedPasswords.User.password'/>");
			$("#matchPass").addClass("valid");
			$("#matchPass").removeClass("error");
			
		}else{
			$("#matchPass").text("<fmt:message key='UnmatchedPasswords.User.password'/>");
			$("#matchPass").addClass("error");
			$("#matchPass").removeClass("valid");
		}
	}
}

$(document).ready(onLoad);
</script>