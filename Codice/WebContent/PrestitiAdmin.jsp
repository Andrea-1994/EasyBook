<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
    Collection<?> libri = (Collection<?>) request.getAttribute("libri");
    LibroBean libro = (LibroBean) request.getAttribute("libro");

%>	

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,libriPackage.LibroBean"%>
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
         </nav>
        <h4>Pagina riservata all'Amministratore</h4>
    <%} else if(session.getAttribute("num_tessera").equals("admin")) {
        %>
        <li class="selected"><a href="prestiti">Prestiti</a></li>
        <li><a class="admin" href="registrazione">Lista Utenti</a></li>
        <li class="right">Ciao, <%=session.getAttribute("nome")%> <a href="Logout.jsp">Logout</a></li>
        
        </ul>
    
	</nav>
	
	

	<h2 id="p"><a>Lista prestiti</a></h2>
	
	

		<table id="pres">
		
		<tr>
			<th>ISBN</th>
			<th>Titolo</th>
			<th>Prestito</th>
			<th>Tessera Utente</th>
			<th>ID Prestito</th>
			<th></th>
		</tr>
		<% if (libri != null && libri.size() != 0) {
				    Iterator<?> it = libri.iterator();
				    while (it.hasNext()) {
					LibroBean bean = (LibroBean) it.next();
					
		%>
		
		<tr>
			<td><%=bean.getISBN()%></td>
			<td><%=bean.gettitolo()%></td>
			<td> dal <%=bean.getdata_inizio()%><br> al <%=bean.getdata_fine()%></td>
			<td><%=bean.getcod_s()%></td>
			<td><%=bean.getcod_pr()%></td>
			<td><a class="admin"  href="chiusura_prestito?action=Chiudi&id=<%=bean.getcod_pr()%>">Chiudi Prestito</a></td>
		</tr>
		<%        
		         } 
		    } else {
		%>
		
		    <tr>
			    <td colspan="8">Non ci sono prestiti attivi</td>
		    </tr>
		<%
			 }
		
		%>
	</table>
	
<%} 
        %>
	
</body>


</html>