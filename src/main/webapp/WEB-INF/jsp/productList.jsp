<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product List</title>
</head>
<body>
<c:forEach items="${aList}" var="aMedia"> 
<div class="row">
	<div class ="col-xs-2">${aMedia.title}</div>
	<div class ="col-xs-2">${aMedia.price}</div>
	<div class ="col-xs-2">${aMedia.genre}</div>
	<div class ="col-xs-4">${aMedia.description}</div>
	<div class ="col-xs-1">${aMedia.description}</div>
</div>
<input type="submit" value="Adauga in cos"/>

</c:forEach>
</body>
</html>