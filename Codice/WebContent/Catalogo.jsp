<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Collection<?> libri = (Collection<?>) request.getAttribute("libri");
	LibroBean libro = (LibroBean) request.getAttribute("libro");
	
	CarrelloBean cart = (CarrelloBean) request.getAttribute("cart");

%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,libriPackage.LibroBean,carrelloPackage.CarrelloBean"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <link rel="stylesheet" type="text/css" href="css/biblioteca.css">
     <link rel="stylesheet" type="text/css" href="css/biblioteca-mobile.css" media="screen">
	<title>Biblioteca</title>
</head>

<body>
    <header class="top">
      <img id="headerLogo"
           src="immagini/banner.gif" alt="Biblioteca header logo image">   
   </header>
   
   <nav>
	<ul>
		<li><a href="Home.jsp">Home</a></li>
		<li class="selected"><a href="libro">Catalogo</a>
		<li><a href="ChiSiamo.jsp">Chi Siamo</a></li>
		<li><a href="ComeTrovarci.jsp">Come Trovarci</a></li>
		<li><a href="Contatti.jsp">Contatti</a></li>
    <%
    if ((session.getAttribute("num_tessera") == null) || (session.getAttribute("num_tessera") == "")) { 	
    %>
        <li class="right"><a href="Login.jsp">Accedi</a></li>
     <% }else if(session.getAttribute("num_tessera").equals("admin")) {
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
	<h4>
        <%
         if(null!=request.getAttribute("messaggio")){
           out.println(request.getAttribute("messaggio"));
         }
        %>   
   </h4>
	<%if ((session.getAttribute("num_tessera") == null) || (session.getAttribute("num_tessera") == "")) { 
	}else if(session.getAttribute("num_tessera").equals("admin")){ %>
	    <div id="reg">
	      <h2>Aggiungi Libro</h2>
	              <form action="addlibrocatalogo" method="post">
		          <input type="hidden" name="action" value="aggiungilibro"> 
		
		          <label for="ISBN">ISBN:</label><br> 
		           <input name="ISBN" type="text" maxlength="13" required placeholder="inserisci ISBN"><br> 
		
	          	 <label for="titolo">Titolo:</label><br>
		         <input name="titolo" type="text" maxlength="50" required placeholder="inserisci titolo"><br>
		
		         <label for="lingua">Lingua:</label><br> 
		         <input name="lingua" type="text" maxlength="20" required placeholder="inserisci lingua"><br>

		         <label for="anno_pubblicazione">Anno di pubblicazione:</label><br> 
		         <input name="anno_pubblicazione" type="number"  maxlength="4" required placeholder="inserisci anno di pubblicazione"><br>
		
		          <label for="categoria">Categoria:</label><br> 
		          <input name="categoria" type="text" maxlength="30" required placeholder="inserisci categoria"><br>
	
		          <label for="autore">Autore:</label><br> 
		          <input name="autore" type="text"  maxlength="50" required placeholder="inserisci autore"><br>
		          
		          <label for="casa_editrice">Casa editrice:</label><br> 
		          <input name="casa_editrice" type="text"  maxlength="30" required placeholder="inserisci casa editrice"><br>

		          <input type="submit" value="Send"><input type="reset" value="Reset">
	              </form>
	     </div>
	
	<%  }
       %>
	
	<form method="post" action="libro">
	<div id="cerca">
	  <input type="hidden" name="action" value="Search">
        <p id="cerca">Cerca:
        <input  type="text" name="titolo" placeholder="titolo">
        <input  class="submit1" type="submit" value="Cerca">
        </p>
    </div>
    </form>
    
	<h2 id="l"><a href="libro">Lista</a></h2>

	<table>
        
		<tr>
			<th><a href="libro?sort=titolo">Titolo</a></th>
			<th><a href="libro?sort=lingua">Lingua</a></th>
			<th><a href="libro?sort=Categoria">Categoria</a></th>
			<th></th>
			<th></th>
		</tr>
		
		 <%
		 if (libro != null ) {
			 if ( libro.gettitolo() != "" && libro.getISBN() != null){
	     %>
		<tr>
			<td><a href="libro?action=read&id=<%=libro.getISBN()%>"><%=libro.gettitolo()%></a></td>
			<td><%=libro.getlingua()%></td>
			<td><%=libro.getcategoria()%></td>
               <%         
                        if ((session.getAttribute("num_tessera") == null) || (session.getAttribute("num_tessera") == "")) {
                       %>	
                        	 <td><a href="Login.jsp">Accedi </a>per effettuare un ordine</td>
                        	 <td></td>
                      
                       <% 	 
                        }else if (session.getAttribute("num_tessera").equals("admin")){ 
                        	
                        	
                               if(libro.getcod_pr() != 0){ %>
			                  
			                   <td id="err">In prestito</td>
			                   
			                  
			               <%}else{ %>
		                     <td>Non in prestito</td>
		                     
		                   <% }%>
		              
		                     <td><a href="rimuovilibrocatalogo?action=delete&id=<%=libro.getISBN()%>">Rimuovi dal catalogo</a></td>
		              
		              
		               <%  } else if(cart.trovalibro(libro)){%>
			  
		                     <td>Già aggiunto al carrello</td>
		                     <td></td>
		    
			              <% }else if(libro.getcod_pr() != 0){%>
			                 
			                  <td id="err">Già in prestito</td>
			                  <td></td>
			                      
			               
			              <%}else{ %>
			   
			                  <td><a href="addlibrocarrello?action=addC&id=<%=libro.getISBN()%>">Aggiungi al carrello</a></td>
			                  <td></td>
		</tr>
		<% 
			         }
                 		
		    } else   {
			
		%>
		     <tr>
			    <td colspan="6">Nessun risultato</td>
		    </tr>
		
		
		<%
		   }
		 } else if (libri != null && libri.size() != 0) {
				    Iterator<?> it = libri.iterator();
				    while (it.hasNext()) {
					LibroBean bean = (LibroBean) it.next();
					
		%>
		<tr>
			<td><a href="libro?action=read&id=<%=bean.getISBN()%>"><%=bean.gettitolo()%></a></td>
			<td><%=bean.getlingua()%></td>
			<td><%=bean.getcategoria()%></td>
			           <%         
                        if ((session.getAttribute("num_tessera") == null) || (session.getAttribute("num_tessera") == "")) {
                       %>	
                        	 <td><a href="Login.jsp">Accedi </a>per effettuare un ordine</td>
                        	 <td></td>
                      
                       <% 	 
                        }else if (session.getAttribute("num_tessera").equals("admin")){ 
                        
                             if(bean.getcod_pr() != 0){ %>
			                  
			                  <td id="err">In prestito</td>
			                  
			               <%}else{ %>
		                     <td>Non in prestito</td>
		                     
		                   <% }%>
		                     <td><a href="rimuovilibrocatalogo?action=delete&id=<%=bean.getISBN()%>">Rimuovi dal catalogo</a></td>
		                   
		              <%  } else if(cart.trovalibro(bean)){%>
			  
		                     <td>Già aggiunto al carrello</td>
		                     <td></td>
		    
			              <% }else if(bean.getcod_pr() != 0){%>
			                 
			                  <td id="err">Già in prestito</td>
			                  <td></td>
			                      
			               
			               <%}else{ %>
			   
			                  <td><a href="addlibrocarrello?action=addC&id=<%=bean.getISBN()%>">Aggiungi al carrello</a></td>
			                  <td></td>
		</tr>
				
		<%        
			               } 
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
	
	
	<%			    
    if (libro != null && libro.getISBN() != null) {
	%>
	<div  id="libro" >
	<img src="immagini/libro.png">
	</div>
	<ul id="dettagli" type="disc">
			<li><b>ISBN</b>: <%=libro.getISBN()%><br></li>
			<li><b>Titolo</b>: <%=libro.gettitolo()%><br></li>
			<li><b>Lingua</b>: <%=libro.getlingua()%><br></li>
			<li><b>Anno di pubblicazione</b>: <%=libro.getanno_pubblicazione()%><br></li>
			<li><b>Categoria</b>: <%=libro.getcategoria()%><br></li>
			<li><b>Autore</b>: <%=libro.getautore()%><br></li>
			<li><b>Casa Editrice</b>: <%=libro.getcasa_editrice()%><br></li>
			<% if(libro.getcod_pr() != 0){%>
			<li><b>Prestito</b>: dal <%=libro.getdata_inizio()%> al <%=libro.getdata_fine()%></li>      		
	</ul>
	
	<% 
			}
			
    }
	%>
		

</body>
</html>