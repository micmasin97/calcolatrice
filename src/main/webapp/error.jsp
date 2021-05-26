<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if(request.getAttribute("error")==null)
		{
			out.print("Sei nella pagina sbagliata");
		}
		else
		{
			out.print("<h3 style=\"color:red\">"+request.getAttribute("error")+"</h3>");
		}
	%>
</body>
</html>