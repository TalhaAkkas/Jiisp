<%@ page language="java" import="models.Post" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<% Post post = (Post)request.getAttribute("post"); %>
		<h3><%= post.getHeader()%></h3>
		<%= post.getText()%>
</div>
<form method="post" action="Site?action=post">
	<div class="row buttons">
		<input type="hidden" name="delete" value="true"> 
		<input type="hidden" name="id" value = "<%= post.getId()%>" > 
		<input type="submit"name="submit" value="submit">
	</div>
</form>