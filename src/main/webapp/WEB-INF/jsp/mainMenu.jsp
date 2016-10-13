<%@ page isErrorPage="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Meniu-Principal</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<style type="text/css">
.list-group {
	width: 250px;
}

.bs-example {
	margin: 0px;
}

.usernamePlace {
	float: right;
}

.container {
	
}
</style>
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
		<!-- Collection of nav links and other content for toggling -->
		<div id="navbarCollapse" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Listă produse</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/logout">Log out</a></li>
				<li><a href="#"><span
						class="glyphicon glyphicon-user"></span> Salut
						${currentUser.username}
				</a></li>
			</ul>
		</div>
		</nav>
	</div>


	<div class="">
		<br />
		<div class="container">
			<div class="jumbotron">
				<div class="bs-example">

					<div class="list-group">

						<a href="" class="list-group-item active"> <span
							class="glyphicon "></span> Articole disponibile: <span
							class="badge"></span>
						</a> <a href="productList/CD" class="list-group-item "> <span
							class="glyphicon glyphicon-music"></span> 1.Listă CD-uri <span
							class="badge"></span>
						</a> <a href="productList/DVD" class="list-group-item"> <span
							class="glyphicon glyphicon-film"></span> 2.Listă DVD-uri <span
							class="badge"></span>
						</a> <a href="productList/EBOOK" class="list-group-item"> <span
							class="glyphicon glyphicon-file"></span> 3.Listă Ebook <span
							class="badge"></span>
						</a>

					</div>
				</div>
			</div>
			<br />
			<a href="/myAccount"><button class="btn btn-primary">Afişare istoric cumpărături</button></a>
		</div>
	</div>
</body>
</html>




