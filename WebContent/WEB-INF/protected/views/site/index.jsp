<%@ page import="components.UserIdentity" language="java"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
Some info this is site index index
<jsp:include page='./_postslist.jsp' />
<br>
<a href="Site?action=contact">contact</a>
<br>
<a href="Site?action=con">error</a>
<br>
<% if(!new UserIdentity().isAuthenticate(request)) {%>
<a href="Site?action=login">login</a>
<% }else{ %>
<a href="Site?action=logout">logout</a>
<hr>
<a href="Site?action=create">create post</a>
<% } %>