<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.List"%>
<%@page import="org.imie.transverse.DTO.Personne"%>
<%@page import="org.imie.transverse.DTO.Promotion"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.imie.interfaceservice.IGestionEcoleService"%>
<%@page import="org.imie.transverse.factory.ConcreteSingletonFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//recuperation profil personne choisie
	Personne selectedPersonne = (Personne) request.getAttribute("selectedPersonne");
	//choix du format date
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	//recuperation liste des promotions
	IGestionEcoleService gestionEcoleService = ConcreteSingletonFactory.getInstance().creategGestionEcoleService();
	List<Promotion> promotions = gestionEcoleService.rechercherPromotion(new Promotion());

%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr"
	dir="ltr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel=stylesheet type="text/css" href="tpJSP/css/style.css">
<title>Update profile</title>
</head>
<body>
	<jsp:include page="/tpJSP/tp5/TP5_SearchForm.jsp" />
</body>
</html>