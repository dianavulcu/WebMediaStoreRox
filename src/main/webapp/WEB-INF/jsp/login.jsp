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
		<div class="has-error">
			<h4 class="help-block">${errorMessage}</h4>
		</div>
	</div>
</div>

<form action="login" method="POST">
	<div class="container">
		<div class="jumbotron">
			<div class="row">
				<div class="col-xs-1">
					<div class="form-group">
						<label for="userName">Utilizator:</label>
					</div>
				</div>
				<div class="col-xs-4">
					<div class="form-group">
						<input class="form-control" name="username"/> 
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-1">
					<div class="form-group">
						<label for="userName">Parola:</label> 
					</div>
				</div>
				<div class="col-xs-4">
					<div class="form-group">
						<input class="form-control" name="password" type="password" />
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-xs-2">
					<input type="submit" value="Autentificare" class="btn btn-danger" />
				</div>
				<div class="col-xs-1">
					<a href="register" class="btn btn-link">Înregistrare</a>
				</div>
				<div class="col-xs-1">
					<a href="/forgotPassword" class="btn btn-link">Mi-am uitat parola</a>
				</div>
			</div>


		</div>
	</div>
</form>
<c:import url="templates/footer.jsp" />