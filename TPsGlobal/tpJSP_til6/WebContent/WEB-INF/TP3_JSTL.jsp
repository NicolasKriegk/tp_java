<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="org.imie.transverse.DTO.Personne"%>
<%@page import="org.imie.transverse.DTO.Promotion"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr"
	dir="ltr">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet type="text/css" href="/TPJSP/TP12.css">
</head>
<body>
	<jsp:useBean id="personneSelected"
		class="org.imie.transverse.DTO.Personne" scope="request" />
	<jsp:useBean id="promotions" class="java.util.ArrayList<Promotion>"
		scope="request" />
	<fmt:formatDate var="dateNaiss" pattern="dd/MM/yyyy"
		value="${personneSelected.dateNaiss}" />
	<%@ include file="TP13.jsp"%>
	<%-- TODO inclusion du header --%>
	<form method="POST">
		<div id="formPersonne">
			<div class="row">
				<div class="cell">
					<label for="">nom</label>
				</div>
				<div class="cell">
					<input type="text" name="inputNom" value="${personneSelected.nom}">
				</div>
			</div>
			<div class="row">
				<div class="cell">
					<label for="">prenom</label>
				</div>
				<div class="cell">
					<input type="text" name="inputPrenom"
						value="${personneSelected.prenom}">
				</div>

			</div>
			<div class="row">

				<div class="cell">
					<label for="">Date naissance</label>
				</div>
				<div class="cell">
					<c:if test="${personneSelected.dateNaiss != null}">
						<input type="text" name="inputDateNaiss" value="${dateNaiss}" />
					</c:if>
					<c:if test="${personneSelected.dateNaiss == null}">
						<input type="text" name="inputDateNaiss" value="" />
					</c:if>
				</div>
			</div>
			<div>
				<div class="cell">
					<label for="">promotion</label>
				</div>
				<div class="cell">
					<select name="inputPromotion">
						<option value=""></option>
						<c:forEach var="promotion" items="promotions">
							<c:choose>
								<c:when test="${promotion.id == personneSelected.promotion.id}">
									<option value="${promotion.id}" selected>${promotion.libelle}</option>
								</c:when>
								<c:otherwise>
									<option value="${promotion.id}">${promotion.libelle}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="row">

				<div class="cell">
					<label for="">password</label>
				</div>
				<div class="cell">
					<input type="password" name="inputPassword">
				</div>

			</div>
		</div>
		<input type="hidden" name="inputId"
			value="${personneSelected.getId()}" />
		<input type="submit"
			name="<%=personneSelected.getId() != null ? "update" : "create"%>"
			value="Valider" />
	</form>
	<a href="/TPJSP/TP4_Controller/read/">retour liste</a>
</body>
</html>