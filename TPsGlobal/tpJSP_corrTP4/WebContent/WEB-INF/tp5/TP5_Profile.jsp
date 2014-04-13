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
	<form method="POST">
		<div id="formPersonne">
			<div class="row">
				<div class="cell">
					<label>nom</label>
				</div>
				<div class="cell">
					<input type="text" name="inputNom" value="<%=selectedPersonne.getNom() %>">
				</div>
			</div>
			<div class="row">
				<div  class="cell">
					<label>prenom</label>
				</div>
				<div class="cell">
					<input type="text" name="inputPrenom" value="<%=selectedPersonne.getPrenom() %>">
				</div>
			</div>
			<div class="row">
				<div class="cell">
					<label>Date de naissance</label>
				</div>
				<div class="cell">
					<input type="text" name="inputDateNaiss" value="<%=simpleDateFormat.format(selectedPersonne.getDateNaiss()) %>">
				</div>
			</div>
			<div class="row">
				<div class="cell">
					<label>Promotion</label>
				</div>
				<div class="cell">
					<select name="inputPromotion">
						<option value=""></option>
						<%-- affichage des promotions sous forme de liste --%>
						<%for(Promotion promotion : promotions) {
							Boolean selected = false;
							if (selectedPersonne.getPromotion() != null
									&& selectedPersonne.getPromotion().getId()
											.equals(promotion.getId())) {
								selected = true;
							}%>
							<option value="<%=promotion.getId() %>" <%=(selected ? "selected" : "") %>>
								<%=promotion.getLibelle() %>
							</option>
						<%} %>
					</select>
				</div>
			</div>
			<div class="row">
				<div  class="cell">
					<label>Password</label>
				</div>
				<div class="cell">
					<input type="password" name="inputPassword">
				</div>
			</div>
		</div>
		<input type="hidden" name="profileId" value="<%=selectedPersonne.getId() %>"/>
		<input type="submit" name="profileUpdate" value="Valider"/>
		<input type="submit" name="profileDelete" value="Supprimer"/>
	</form>
	<a href="/tpJSP/TP5_Controller">retour liste</a>
</body>
</html>