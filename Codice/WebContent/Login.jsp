<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Biblioteca</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/biblioteca.css">
  <link rel="stylesheet" type="text/css" href="css/biblioteca-mobile.css" media="screen">
</head>
  
<body>
   
   <header class="top">
      <img id="headerLogo"
           src="immagini/banner.gif" alt="Biblioteca header logo image">   
   </header>
   
    <nav>
	<ul>
		<li><a href="Home.jsp">Home</a></li>
		<li><a href="libro">Catalogo</a>
		<li><a href="ChiSiamo.jsp">Chi Siamo</a></li>
		<li><a href="ComeTrovarci.jsp">Come Trovarci</a></li>
		<li><a href="Contatti.jsp">Contatti</a></li>
        <li class="right"><a href="Login.jsp">Accedi</a></li>
    </ul>
	</nav>
	
	<form action="accesso" method="post">
            <h2 id="a"> Accedi </h2>
            <table id="accesso">
                
                <tr>
                    <td>Utente</td>
                    <td><input type="text" name="username" required="required" placeholder="inserisci numero tessera" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="userpass" required="required" placeholder="inserisci password" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input class="submit1" type="submit" value="Accedi" /></td>
                </tr>
            </table>
    </form>
    
    <h4>
        <%
         if(null!=request.getAttribute("messaggio")){
           out.println(request.getAttribute("messaggio"));
         }
        %>   
   </h4>

</body>
</html>