<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<h1>Login</h1>

<p>Please fill out the following form with your login credentials:</p>

<div class="form">
	 <form method = "post" action = "Site?action=login">
		<!--<p class="note">Fields with <span class="required">*</span> are required.</p> -->
	
		<div class="row">
	         <font size = 6>Enter your name<input type = "text" name = "username"></font><br><br><br>
		</div>
	
		<div class="row">
			<font size = 6>Enter your password<input type="password" name = "password" ></font><br><br>
		</div>
		<div class="row buttons">
	          <input type = "submit" name = "submit" value = "submit" >
		</div>
	</form>
</div>