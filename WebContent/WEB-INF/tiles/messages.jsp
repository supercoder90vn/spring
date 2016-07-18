<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="messages">

</div>
<script type="text/javascript">
	var timer;	
	
	function success(data){
		var id = data.target;
		$("#form"+id).toggle();
		$("#alert"+id).text("Message sent.");
		startTimer();
		
	}
	
	function error(data){alert("Error sending message.");}
	function sendMessage(i,name,email) {
		var text = $("#textbox"+ i).val();
		$.ajax({
			"type": 'POST',
			"url": "<c:url value='/sendMessage'/>",
			"data": JSON.stringify({"target":i,"text":text,"name":name,"email":email}),
			"success": success,
			"error": error,
			contentType: "application/json",
			dataType: "json"			
		});
	}
	function startTimer() {	timer = window.setInterval(updatePage, 5000);	}
	function stopTimer() {		window.clearInterval(timer);	}
	function showReply(i) {
		stopTimer();		
		$("#form" + i).toggle();
	}
	//	MESSAGE_DIV	
	function createMessageDiv(message,i) {
		var messageDiv = document.createElement("div");
			messageDiv.setAttribute("class", "message");
		var subjectSpan = document.createElement("span");{
			subjectSpan.setAttribute("class", "subject");
			subjectSpan.appendChild(document.createTextNode(message.subject));				
		}			
		var contentSpan = document.createElement("span");{
			contentSpan.setAttribute("class", "messageBody");
			contentSpan.appendChild(document.createTextNode(message.content));
		}			
		var nameSpan = document.createElement("span");{
			nameSpan.setAttribute("class", "name");
			nameSpan.appendChild(document.createTextNode(message.name
					+ "("));
			var link = document.createElement("a");{
				link.setAttribute("class", "replylink");
				link.setAttribute("href", "#");
				link.setAttribute("onclick", "showReply(" + i + ")");
				//link.onclick = sendMessage;
				link.appendChild(document.createTextNode(message.email));					
			}
			nameSpan.appendChild(link);
			nameSpan.appendChild(document.createTextNode(")"));
		}
		var alertSpan = document.createElement("span");{
			alertSpan.setAttribute("class", "alert");
			alertSpan.setAttribute("id", "alert"+i);
			//alertSpan.appendChild(document.createTextNode("message sent"));
			
		}
		
		var replyForm = document.createElement("form");{				
			replyForm.setAttribute("class", "replyForm");
			replyForm.setAttribute("id", "form" + i);

			var textarea = document.createElement("textarea");
			textarea.setAttribute("class", "replyarea");
			textarea.setAttribute("id", "textbox"+i);
			
			var replyButton = document.createElement("input");{
				replyButton.setAttribute("class", "replyButton");
				replyButton.setAttribute("type", "button");
				replyButton.setAttribute("value", "reply");					
				//replyButton.onclick = sendMessage;		
				//replyButton.setAttribute("onclick", "sendMessage("+i+")");
				
				replyButton.onclick = function(j,name,email){
					return function(){
						sendMessage(j,name,email);
					}
				}(i,message.name,message.email);
				
				
			}
			replyForm.appendChild(textarea);
			replyForm.appendChild(replyButton);
		}

		messageDiv.appendChild(subjectSpan);
		messageDiv.appendChild(contentSpan);
		messageDiv.appendChild(nameSpan);
		messageDiv.appendChild(alertSpan);
		messageDiv.appendChild(replyForm);
		return messageDiv;
	}
	//################################################
	function showMessages(data) {
		$("div#messages").html("");//????
		for (var i = 0; i < data.messages.length; i++) {
			var message = data.messages[i];	
			var messageDiv = createMessageDiv(message,i);			
			$("div#messages").append(messageDiv);
		}
		

	}
	//##############################################
	$(document).ready(onLoad);
	function onLoad() {
		updatePage();		
		startTimer();		
		
	}	
	function updatePage() {
		$.getJSON("<c:url value="/getMessages"/>", showMessages);
	}
	//##############################################
	
// 	function showMessages2(data){
// 		for(var i=0;i<data.messages.length;i++){
// 			var message = data.messages[i];
// 			var text = '<h2>'+
// 			message.subject+
// 			'/h2>From:<string>'+
// 			message.name+
// 			'</strong> '+
// 			message.email+
// 			'<br/><em>'+
// 			message.content+
// 			'</em>'+
// 			'<div class="reply"><strong>Reply:</strong><br/>'+
// 			'<textarea id="'+i+'" class="reply"/><br/>'+
// 			'<input onclick="sendMessage('+i+');" id="button'+i+'" type="button" value="send" class';
// 		}
// 	}
	
	

	
</script>
