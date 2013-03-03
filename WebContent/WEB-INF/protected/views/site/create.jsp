<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h1>Create Post</h1>

<p>Please fill out the following form:</p>

<div class="form">
	<form method="post" action="Site?action=create">
		<!--<p class="note">Fields with <span class="required">*</span> are required.</p> -->

		<div class="row">
			<b>Post Header<input type="text" name="header"></b><br>
			<br>
			<br>
		</div>

		<div class="row">
			<b>Post Text<input type="text" name="text"></b><br>
			<br>
		</div>
		<div class="row buttons">
			<input type="hidden" name="create"> <input type="submit"
				name="submit" value="submit">
		</div>
	</form>
</div>