<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="/recoverPassword" method="POST">
<h3>Esti logat cu userul: </h3> ${userName}
<h3>Password:</h3>
<input type="password" name="password"/><br/><br/>
<input type="password" name="repeatPassword"/><br/><br/>
<input type = "hidden" name="uuid" value = "${uuid}"/>

${errorMessage}
<br/>
<input type="submit" value="Salveaza"/>
</form>
</body>
</html>