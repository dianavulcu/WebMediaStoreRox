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
<title>Vizualizare coş</title>
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
					<li class="active"><a href="#">Vizualizare coş</a></li>


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
<c:forEach items="${shoppingCart.cartItems}" var="cartItem" varStatus="status">
${status.index+1}. ${cartItem.media.title} - ${cartItem.media.description}     Cantitate: ${cartItem.quantity} - Pret: ${cartItem.totalPrice} RON<br/>
</c:forEach>
<br/>
<a href="/checkout"><button class="btn btn-primary" >0. Check out</button></a>
</div>
</body>
</html>