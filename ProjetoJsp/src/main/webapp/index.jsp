<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagina Jsp</title>
</head>
<body>

	<h1>Login JSP</h1>

	<form action="ServletLogin" method="post">
	<input type="hidden" value="<%= request.getParameter("url") %>" name="url">

		<table>
			<tr>
				<td><label>Login</label> 
				<input name="login" type="text" id="nome"></td>

			</tr>

			<tr>
				<td><label>Senha</label> 
				<input name="senha" type="password" id="senha"></td>

			</tr>

			<tr>
				
				<td><input type="submit" value=Entrar></td>

			</tr>
		</table>

	</form>
	<h4>${msg}</h4>


</body>
</html>