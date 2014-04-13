<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="org.imie.transverse.DTO.Personne"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr"
	dir="ltr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Hello World</title>
</head>
<body>
	<%Personne personne = (Personne) request.getAttribute("selectedPersonne"); %>
	<H2><%=personne.getNom()%></H2>
	<H2><%=personne.getPrenom() %></H2>
</body>

</html>