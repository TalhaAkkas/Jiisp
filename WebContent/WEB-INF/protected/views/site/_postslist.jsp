<%@ page language="java" import="components.UserIdentity, java.util.List, models.Post" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<%List postList = (List) request.getAttribute("postslist"); %>
	<%= postList.size() %>
	<% for(int i = 0; i < postList.size(); i++){ %>
		<div>
		
			<% Post post = (Post)postList.get(i);%>
			<% if(new UserIdentity().isAuthenticate(request)) {%>
				<a href="Site?action=post&id=<%= post.getId()%>"><h3><%= post.getHeader()%></h3></a>
			<% }else{ %>
				<h3><%= post.getHeader()%></h3>
			<%} %>
			<%= post.getText()%>
		</div>
	<%} %>
</div>