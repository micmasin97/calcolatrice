<%@ page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome page</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h1>Welcome!</h1>
    Sei nuovo? Registrati.
    <form action="UserServlet" method="post">
    	<input type="text" name="usernameRegister" placeholder="user"/>
    	<input type="password" name="passwordRegister" placeholder="password"/>
    	<input type="password" name="conferma" placeholder="conferma password"/>
    	<input type="text" name="nome" placeholder="nome"/>
    	<input type="text" name="cognome" placeholder="cognome"/>
    	<input type="submit" name="submit" value="register"/>
    </form>
    Login
    <form action="UserServlet" method="post">
    	<input type="text" name="username" placeholder="user"/>
    	<input type="password" name="password" placeholder="password"/>
    	<input type="submit" name="submit" value="login"/>
    </form>
</body>
</html>