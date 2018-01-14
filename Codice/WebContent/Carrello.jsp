<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Collection<?> libri = (Collection<?>) request.getAttribute("libri");
	LibroBean libro = (LibroBean) request.getAttribute("libro");
	
	CarrelloBean cart = (CarrelloBean) session.getAttribute("cart");

%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,libriPackage.LibroBean,carrelloPackage.CarrelloBean"%>
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
        <%
    if ((session.getAttribute("num_tessera") == null) || (session.getAttribute("num_tessera") == "")) {
    %>
        <li class="right"><a href="Login.jsp">Accedi</a></li>
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
	
	<% if(cart != null && cart.getlibri() != null) { 
		List<LibroBean> libcart = cart.getlibri(); 
	     if(libcart.size() != 0){
	%>
	    <div id="ordine">
	     <form action="invio_prestito" method="post">
		 <input type="hidden" name="action" value="prestito"> 
		<input id="submit" type="submit" value="Conferma Ordine">	
	    </form>	
        </div>
	
	<%  }
	 }  %>
		  
	
	<h2 id="c">Carrello</h2>

		<table class="carr">
		
		<tr>
			<th>Titolo</th>
			<th>Azione</th>
		</tr>
		
		<% if(cart != null && cart.getlibri() != null) { %>
		   
		<% List<LibroBean> libcart = cart.getlibri(); 
		     if(libcart.size() != 0){
		      for(LibroBean beancart: libcart) {
		%>
		<tr>
			<td><%=beancart.gettitolo()%></td>
			<td><a href="rimuovilibrocarrello?action=deleteC&id=<%=beancart.getISBN()%>">Elimina dal carrello</a></td>
		</tr>
		<%        
		      } 
		    } else {
		%>
		
		    <tr>
			    <td colspan="2">Carrello vuoto</td>
		    </tr>
		<%
			 }
		  }
		%>
	</table>
	
	<h2>
        <%
         if(null!=request.getAttribute("messaggio")){
           out.println(request.getAttribute("messaggio"));
         }
        %>   
   </h2>
	
	
	
</body>

</body>
</html>