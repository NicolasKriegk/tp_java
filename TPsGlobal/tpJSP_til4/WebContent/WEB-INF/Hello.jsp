<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,org.imie.transverse.DTO.Personne" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="screen" type="text/css" title="style" href="/tpJSP/css/style.css" />
<title>Hello world</title>
</head>
<body>
	<span dir="rtl">Hello world!</span>
	<hr>
	<span dir="rtl">texte en parametre de requete: <%= request.getParameter("texte") %></span>
	<hr>
	<%--recuperation de la liste des personnes --%>
	<%List<Personne> personnes = (List<Personne>) request.getAttribute("personneList");%> 
	<%--affichage de la liste des personnes --%>
	<table id="tablePersonne">
		<thead>
			<tr>
				<th>nom</th>
				<th>prenom</th>
				<th>date naissance</th>
				<th>promotion</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<%for(Personne personne : personnes){%>
				<tr>
					<td><%=personne.getNom()%></td>
					<td><%=personne.getPrenom()%></td>
					<td><%=personne.getDateNaiss() != null ? personne.getDateNaiss().toString() : ""%></td>
					<td><%=personne.getPromotion() != null ? personne.getPromotion().getLibelle() : ""%></td>
				</tr>
			<%}%>
		</tbody>
	</table>
	<hr class="semiWidth">
</body>
</html>
