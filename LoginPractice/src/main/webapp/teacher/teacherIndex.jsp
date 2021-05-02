<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<%
session =request.getSession(false);
String myEmpName =(String)session.getAttribute("name");
String myRoll =(String)session.getAttribute("roll");

if(myEmpName==null){
	response.sendRedirect("../login.jsp");
}
%>

<meta charset="ISO-8859-1">
<title>Teacher</title>
</head>
<body>
	<a href="../Logout">Logout</a>
	<h1>Welcome <%=myRoll+" "+myEmpName%></h1>
</body>
</html>