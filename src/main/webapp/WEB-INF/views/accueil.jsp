<%@page import="org.springframework.context.annotation.Import"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${title}</title>
	<!-- Imports CSS -->
	<link href="resources/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="resources/css/tittineShare.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="contenu">
		<h1 class="titreRecherche">${titrePage}</h1>
		<div class="row">
			<form:form  id="identification" action="/identification">
				<div align="center">	Bienvenue Sur Tittine Share !!! </div>
				<br />
				<div align="center">Exemple&nbsp;: ${autre} -  NOM&nbsp;${nom} - PR&Eacute;NOM&nbsp;: ${prenom}</div>
				${object.nom}
			</form:form>
		</div>
	</div>
	<a href="/ServeurDonnees/map">Carte</a>
</body>
<!-- Imports JS -->
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="resources/js/accueil.js"></script>
</html>