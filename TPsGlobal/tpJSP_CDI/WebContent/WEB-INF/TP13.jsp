<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.imie.transverse.DTO.Personne"%>
<jsp:useBean id="authentifiedPersonne"
	class="org.imie.transverse.DTO.Personne" scope="session"></jsp:useBean>
<header>
	<div>
		<span> <jsp:getProperty property="nom"
				name="authentifiedPersonne" /> - <jsp:getProperty property="prenom"
				name="authentifiedPersonne" />
		</span> <a href="/tpJSP_CDI_EJB/TP13_Deconnection">Deconnection</a>
	</div>
</header>