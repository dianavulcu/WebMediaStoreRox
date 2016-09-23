<%@ page isErrorPage="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<title>Product List</title>
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
					<li class="active"><a href="#">Listă produse</a></li>


				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"> <span class="glyphicon glyphicon-user"></span> Salut ${currentUser.username}</a></li>
				</ul>
			</div>
		</nav>
	</div>
 <div class="container">
		<div class="jumbotron">
			<div class="bs-example">
	<c:forEach items="${aList}" var="aMedia">
		<form action="/addProductToCart" method="post">
			<div class="row">
				<div class="col-xs-1">${aMedia.title}</div>
				<div class="col-xs-1">${aMedia.description}</div>
				<div class="col-xs-1">${aMedia.price}</div>
				<div class="col-xs-1">${aMedia.genre}</div>
				<div class="col-xs-1">
					<div class="form-group">
						<input class="form-control" type="text" value="0" name="cantitate" />
					</div>
				</div>
				<div class="col-xs-2">
					<div class="form-group">
						<input class="btn btn-primary" type="submit" value="Adauga in cos" />
					</div>
				</div>
			</div>
			<input type="hidden" value="${aMedia.code}" name="productCode"/>
			<input type="hidden" value="${productType}" name="productType"/>
		</form>
	</c:forEach>
	<h6>TOTAL: ${fn:length(shoppingCart.cartItems)} produse, cantitate: ${shoppingCart.totalItems}, Total: ${shoppingCart.totalPrice} RON</h6>
<a href="/mainMenu"><button class="btn btn-warning">Înapoi la meniul principal</button></a>
&nbsp&nbsp
<span><a href="/displayCart"><button class="btn btn-success">Afișează coșul</button></a></span>

</div>	
</body>
</html>