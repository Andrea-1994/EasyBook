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
		<li class="selected"><a href="Contatti.jsp">Contatti</a></li>
    <%
    if ((session.getAttribute("num_tessera") == null) || (session.getAttribute("num_tessera") == "")) {
    %>
        <li class="right"><a href="Login.jsp">Accedi</a></li>
    <%} else if(session.getAttribute("num_tessera").equals("admin")) {
    %>
    <li><a href="prestiti">Prestiti</a></li>
         <li><a class="admin" href="registrazione">Lista Utenti</a></li>
         <li class="right">Ciao, <%=session.getAttribute("nome")%> <a href="Logout.jsp">Logout</a></li>
    <%} else {
    %>
    <li><a href="prestiti">Tuoi Prestiti</a></li>
    <li class="right"><a href="Carrello.jsp"><img id="cart" src="immagini/cart.png"></a></li>
    <li class="right">Ciao, <%=session.getAttribute("nome")%> <a href="Logout.jsp">Logout</a></li>
    <%
    }
    %>
    </ul>
	</nav>
	
	<div id="contatti"> 
	  <p><img border=0 src="immagini/tel.png" align=middle> Telefono: 0444 980132</p>
	  <p><img border=0 src="immagini/mail.png" align=middle> E-mail: biblioteca@live.it</p>
	  <p><img border=0 src="immagini/fax.png" align=middle> Fax: 0444 983193</p>
	</div>
  
    
    <footer>
    </footer>
    
</body>
</html>