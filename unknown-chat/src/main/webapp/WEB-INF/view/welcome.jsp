<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h4>Hello ${name} you are connect with ${connect}</h4>
	<form action="/msg" method="post">
		<input type="text" name="msg">
		<input type="hidden" name ="name" value=${name}>
		<input type="hidden" name ="connect" value=${connect}> 
		<input type="submit" value="send" >
	</form>
	<h3>${msgFrom}</h3>
	
</body>
</html>