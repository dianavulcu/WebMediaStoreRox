<%@ page isErrorPage="true" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${shoppingCart.cartItems}" var="cartItem" varStatus="status">
${status.index+1}. ${cartItem.media.title} - ${cartItem.media.description}   Cantitate: ${cartItem.quantity} - Pret: ${cartItem.totalPrice} RON<br/>
</c:forEach>
<br/>
<a href="/checkout">0. Check out</a>
</body>
</html>