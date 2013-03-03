<%@ page import="models.WebError" language="java"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<h2>
	Error
	<%= ((WebError)request.getAttribute("error")).getErrorCode() %></h2>

<div class="error">
	<%= ((WebError)request.getAttribute("error")).getErrorMessage()%>
</div>