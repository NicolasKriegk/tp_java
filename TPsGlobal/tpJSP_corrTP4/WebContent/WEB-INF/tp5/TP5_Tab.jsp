<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.List"%>
<%@page import="org.imie.transverse.DTO.Personne"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr"
	dir="ltr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" media="screen" type="text/css" title="style" href="/tpJSP/css/style.css" />
<title>Hello World</title>
</head>
<body>
	<jsp:include page="/WEB-INF/tp5/TP5_SearchForm.jsp" />
	<hr>
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
			<%
				List<Personne> personnes = (List<Personne>) request
						.getAttribute("foundPersonnes");
				for (Personne personne : personnes) {
			%>
			<tr>
				<td><%=personne.getNom()%></td>
				<td><%=personne.getPrenom()%></td>
				<td><%=personne.getDateNaiss() != null ? personne
						.getDateNaiss().toString() : ""%></td>
				<td><%=personne.getPromotion() != null ? personne
						.getPromotion().getLibelle() : ""%></td>
				<td><a
					href="/tpJSP/TP5_Controller/
					<%=personne.getId()%>">Voir le profil</a></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>

</body>

</html>