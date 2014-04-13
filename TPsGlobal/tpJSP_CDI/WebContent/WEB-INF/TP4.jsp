<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.List"%>
<%@page import="org.imie.transverse.DTO.Personne"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr"
	dir="ltr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel=stylesheet type="text/css" href="/tpJSP_CDI_EJB/TP5.css">
<title>Hello World</title>
</head>
<body>
	<%@ include file="TP13.jsp"%>
	<a href="/tpJSP_CDI_EJB/TP4_Controller/create">créer un personne</a>
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
			<c:forEach var="personne" items="${foundPersonnes}">
				<tr>
					<td><c:out value="${personne.nom}" /></td>
					<td><c:out value="${personne.prenom}" /></td>
					<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${personne.dateNaiss}" /></td>
					<td><c:out value="${personne.promotion.libelle}" /></td>
					<td><a href="/tpJSP_CDI_EJB/TP4_Controller/read/${personne.id}">
							selection </a> <a href="/tpJSP_CDI_EJB/TP4_Controller/delete/${personne.id}">
							suppression </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>

</html>