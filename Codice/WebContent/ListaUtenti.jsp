<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Collection<?> utenti = (Collection<?>) request.getAttribute("utenti");
	UtenteBean utente = (UtenteBean) request.getAttribute("utente");
	
%>


<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,utentiPackage.UtenteBean"%>
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
    <li><a href="prestiti">Prestiti</a></li>
         <li class="selected"><a class="admin" href="registrazione">Lista Utenti</a></li>
         <li class="right">Ciao, <%=session.getAttribute("nome")%> <a href="Logout.jsp">Logout</a></li>
    </ul>
	</nav>
	
	 <h4>
        <%
         if(null!=request.getAttribute("messaggio")){
           out.println(request.getAttribute("messaggio"));
         }
        %>   
   </h4>
	
	
	  <div id="reg">
	    <h2>Registrazione</h2>
	<form action="registrazione" method="post">
		<input type="hidden" name="action" value="registra"> 
		
		<label for="num_tessera">Numero Tessera:</label><br> 
		<input name="num_tessera" type="text" maxlength="6" required placeholder="inserisci numero tessera"><br> 
		
		<label for="pass">Password:</label><br>
		<input name="pass" type="password" maxlength="10" required placeholder="inserisci password"><br>
		
		<label for="nome_S">Nome:</label><br> 
		<input name="nome_S" type="text" maxlength="20" required placeholder="inserisci nome"><br>

		<label for="cognome_S">Cognome:</label><br> 
		<input name="cognome_S" type="text"  maxlength="20" required placeholder="inserisci cognome"><br>
		
		<label for="data_nascita_S">Data di nascita:</label><br> 
		<input name="data_nascita_S" type="date" required placeholder="inserisci data"><br>
		
		<label for="indirizzo">Indirizzo:</label><br> 
		<input name="indirizzo" type="text"  maxlength="60" required placeholder="inserisci indirizzo"><br>

		<input type="submit" value="Send"><input type="reset" value="Reset">
	</form>
	</div>
	
	 
   
   <table id="utenti">

        <tr>
          <th colspan= "6"><a href="registrazione">Lista Utenti</a></th>
        </tr>
        
		<tr>
		    
			<th><a href="registrazione?sort=num_tessera">N°tessera</a></th>
			<th><a href="registrazione?sort=nome_S">Nome</a></th>
			<th><a href="registrazione?sort=cognome_S">Cognome</a></th>
			<th><a href="registrazione?sort=data_nascita_S">Data di nascita</a></th>
			<th><a href="registrazione?sort=indirizzo">Indirizzo</a></th>
			<th></th>
			
		</tr>
       <% if (utenti != null && utenti.size() != 0) {
				    Iterator<?> it = utenti.iterator();    
				    while (it.hasNext()) {
					UtenteBean bean = (UtenteBean) it.next();
	   %>
		<tr>
			<td><%=bean.getnum_tessera()%></td>
			<td><%=bean.getnome_S()%></td>
			<td><%=bean.getcognome_S()%></td>
			<td><%=bean.getdata_nascita_S()%></td>
			<td><%=bean.getindirizzo()%></td>
			<td><a href="rimuovi_utente?action=delete&id=<%=bean.getnum_tessera()%>">Elimina</a></td>
		</tr>
				
		<%        
				    } 
				 } else {
		%>
		    <tr>
			    <td colspan="6">Nessun risultato</td>
		    </tr>
		<%
			}
		%>
	</table>
    
  
 <%}
   %>

</body>
</html>