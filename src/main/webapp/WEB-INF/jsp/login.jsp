<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<c:import url="templates/header.jsp" />

<div class="container">
	<div class="jumbotron">
		<h1>Media Store</h1>
		<h2>Bine ați venit!</h2>
		<h3>Vă rugăm să vă autentificați</h3>
		<h4 style="color: red;">${errMessage}</h4>
	</div>
</div>

<form action="login" method="POST" class="form-inline">
	<div class="container">
		<div class="jumbotron">
			<label for="userName">Utilizator:</label> <input class="form-control"
				name="username" /> <label for="userName">Parola:</label> <input
				class="form-control" name="password" type="password" /><br /> <br />
			<input type="submit" value="Autentificare" class="btn btn-danger" />
		</div>
	</div>
</form>
<c:import url="templates/footer.jsp" />