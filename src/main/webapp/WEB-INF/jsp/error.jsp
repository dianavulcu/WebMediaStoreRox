<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>

timestamp: ${timestamp}</br>
status: ${status}</br>
error: ${error}</br>
message: ${message}</br>
path: ${path}</br>
exception: ${exception}
<br/>
<c:set var="current_exception" value="${pageContext.exception}" />
<c:forEach begin="0" end="10" step="1" var="i">
  <c:if test="${not empty current_exception}">
    <br/><c:out value="${current_exception}" /><br/>
    <c:forEach var="trace" items="${current_exception.stackTrace}">
		<span style="margin-left: 20px;">${trace}</span><br/>
	</c:forEach>
    <c:set var="current_exception" value="${current_exception.cause}" />
  </c:if>
</c:forEach>