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
  
<script> 
var i = 0; 
var img = new Array(); 
 
img[0] = "immagini/cit_home.gif"; 
img[1] = "immagini/cit_home1.gif"; 
img[2] = "immagini/cit_home2.gif"; 

function swapImage() 
{ 
document.slideImg.src = img[i]; 
if(i < img.length - 1) i++; 
else i = 0; 
setTimeout("swapImage()",4000); 
} 

</script> 
    
</head>
  
<body onLoad="swapImage()">
   
   <header class="top">
      <img id="headerLogo"
           src="immagini/banner.gif" alt="Biblioteca header logo image">   
   </header>
   
    <nav>
	<ul>
		<li class="selected"><a href="Home.jsp">Home</a></li>
		<li><a href="libro">Catalogo</a>
		<li><a href="ChiSiamo.jsp">Chi Siamo</a></li>
		<li><a href="ComeTrovarci.jsp">Come Trovarci</a></li>
		<li><a href="Contatti.jsp">Contatti</a></li>
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
	
   
	<div id=benvenuto>
	       <p>Benvenuto nel sito della</p>
       <h1>Biblioteca Civica "Bertoliana"</h1>
    </div> 
    
     <aside>
          <h1>ORARI</h1>
          <h2>apertura-chiusura</h2>
          <p>Lunedì 15.00-19.00</p>
          <p>Martedì, Mercoledì, Giovedì, Venerdì 9.00-12.00 - 15.00-19.00</p>
          <p>Sabato 9.00-12.00</p>
          <p>Domenica chiuso</p>
    </aside>  
       
 
    
    <div id=cit>
       <img name="slideImg" src="immagini/cit_home.gif" alt="citazione">    
    </div>
    
    
</body>
</html>