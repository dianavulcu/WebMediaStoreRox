<%@ page isErrorPage="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<title>Înregistrare user</title>
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
			<!-- Collection of nav links and other content for toggling -->
			<div id="navbarCollapse" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Înregistrare user</a></li>


				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"> <span class="glyphicon glyphicon-user"></span> Login</a></li>
				</ul>
			</div>
		</nav>
	</div>
	<div class="container">
					<div class="jumbotron">
<form action="registerUser" method="POST">
	<div class="row">

							<div class="col-xs-4">
								<div class="form-group">
									<label for="username"><h4>*Username:</h4></label> <input type="text"
										name="userName" class="form-control" id="userName"
										placeholder="Enter Username">
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label for="first_name"><h4>First Name:</h4></label> <input type="text"
												name="first_name" class="form-control" id="first_name"
												placeholder="Enter First Name">
										</div>

									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label for="last_name"><h4>Last Name:</h4></label> <input type="text"
												name="last_name" class="form-control" id="last_name"
												placeholder="Enter Last Name">
										</div>

									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label for="email"><h4>Email:</h4></label> <input type="email"
												name="emailAddress" class="form-control" id="emailAddress"
												placeholder="Enter Email">
										</div>

									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label for="password"><h4>*Password:</h4></label> <input type="password"
												name="password" class="form-control" id="password"
												placeholder="Enter Password">
										</div>

									</div>
								</div>
									<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label for="repeatPassword"><h4>*Repeat password:</h4></label> <input type="password"
												name="repeatPassword" class="form-control" id="repeatPassword"
												placeholder="Repeat Password">
										</div>

									</div>
								</div>

<div class="has-error"><label><h4 class="help-block">${errorMessage}</h4></label></div>
<br/>
<input type="submit"  value="Salveaza" class="btn btn-danger"/>
</form>
</div>
</div>


</body>
</html>