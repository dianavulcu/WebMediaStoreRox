<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Meniu-Principal</title>
</head>
<body>
<div style="float: right">Salut ${currentUser.username}</div>
<a href="productList/CD"><h6>1.Listă CD-uri</h6></a>
<a href="productList/DVD"><h6>2.Listă DVD-uri</h6></a>
<a href="productList/BOOK"><h6>3.Listă Cărți</h6></a>
</body>
</html>