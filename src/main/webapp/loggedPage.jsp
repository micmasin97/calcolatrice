<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@ page import="it.advancia.michele.entity.*"%>
<!DOCTYPE html>
<html>
<%
if (session.getAttribute("user") == null)
{
	request.setAttribute("error", "Impossibile accedere alla seguente funzione se non si è loggati");
	request.getRequestDispatcher("error.jsp").forward(request, response);
} else
{
	User user = (User) session.getAttribute("user");
%>
<head>
<meta charset="ISO-8859-1">
<title>Hello <%= user.getNome() %></title>
</head>
<body>
Ciao <%= user.getNome() %>
<form action="CalculatorSerlvlet" method="POST">
	<input type="number" name="operator1" value="<%= request.getAttribute("operator1")%>" step="0.01">
	<select name="operation">
		<option value="add">+</option>
		<option value="sub">-</option>
		<option value="mul">*</option>
		<option value="div">/</option>
	</select>
	<input type="number" name="operator2" value="<%= request.getAttribute("operator2")%>" step="0.01">
	<input type="submit" value="=">
	<input disabled="disabled" value="<%= (request.getAttribute("result") == null) ? 0 : request.getAttribute("result")%>">
	<input type="reset" value="reset">
</form>
</body>
<%
}
%>
</html>