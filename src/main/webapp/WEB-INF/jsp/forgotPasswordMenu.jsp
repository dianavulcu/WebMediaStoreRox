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
<form action="generatePassword" method="POST">
<h3>Username:</h3>
<input type="text" name="userName"/>
<h3>Email Address:</h3>
<input type="text" name="emailAddress"/><br/><br/>
${errorMessage}
<br/>
<input type="submit" value="Trimite"/>
</form>
</body>
</html>