<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div id="content">
	<jsp:include page='<%=(String)request.getAttribute("content")%>' />
</div>