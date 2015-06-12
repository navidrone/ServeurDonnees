<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Créer un trajet</title>
	<!-- Imports CSS -->
	<link href="resources/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
	<link href="resources/datepicker/css/datepicker.css" rel="stylesheet">
	<link rel="stylesheet" href="resources/css/dropzone.css">
	<link rel="stylesheet" href="resources/css/basic.css">
	<link rel="stylesheet" href="resources/css/tittineShare.css">
</head>
<body>
	<!-- Import Header -->
	<%@ include file="header.jsp" %>
	<div id="contenu" style="width:100%">
		<div class="row">
			<form:form  modelAttribute="trajet" id="trajetform" action="/tittineShare/save"
			class="col-sm-12 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3">
				<div class="form-group">
					<label for="villeDepart">Départ</label>
					<input type="text" class="form-control" id="villeDepart" name="villeDepart" placeholder="Entrez une ville de départ">
				</div>
				<div class="form-group">
					<label for="villeArrivee">Arrivé</label>
					<input type="text" class="form-control" id="villeArrivee" name="villeArrivee" placeholder="Entrez une ville d'arrivé">
				</div>
				<div class="form-group">
					<label for="departMinute">Heure de départ</label>
					<select id="departHeure" name="departHeure">
					<% for(int i =0; i<25;i++){ %>
					   <option value="<%=i%>"><%=i %></option>
					   <%} %>
					</select>
					<select id="departMinute" name="departMinute">
					   <option value="0">0</option>
					   <option value="15">15</option>
					   <option value="30">30</option>
					   <option value="45">45</option>
					</select>
				</div>
				
				<div class="form-group">
					<label for="arriveeHeure">Heure d'arrivée</label>
					<select class="form-control" id="arriveeHeure" name="arriveeHeure">
					<% for(int i =0; i<25;i++){ %>
					   <option value="<%=i%>"><%=i %></option>
					   <%} %>
					</select>
					<select class="form-control" id="arriveeMinute" name="arriveeMinute">
					   <option value="0">0</option>
					   <option value="15">15</option>
					   <option value="30">30</option>
					   <option value="45">45</option>
					</select>
				</div>
				<div class="form-group">
					<label for="date">Date</label>
					<input type="text" class="form-control" id="dateDepart" name="date" placeholder="Veuillez saisir une date">
				</div>
				<div class="form-group">
					<label for="vehicule">Véhicule</label>
					<input type="text" class="form-control" id="vehicule" name="vehicule" placeholder="Entrez le type du véhicule">
				</div>
				<div class="form-group">
					<label for="description">Description du trajet</label>
					<input type="text" class="form-control" id="description" name="description" 
					placeholder="Entrez une description sur le trajet">
				</div>		
				<div class="form-group">
					<label for="nbPassagers">Nombre de passagers</label>
					<select class="form-control" id="nbPassagers" name="nbPassagers">
					<% for(int i =1; i<6;i++){ %>
					   <option value="<%=i%>"><%=i %></option>
					   <%} %>
					</select>
				</div>				
				<div class="form-group">
					<label for="lieuDepart">Lieu de rendez-vous (Départ et arrivée)</label>
					<input type="text" class="form-control" id="lieuDepart" name="lieuDepart" 
					placeholder="Entrez une description du lieu de départ">
					<input type="text" class="form-control" id="lieuArrivee" name="lieuArrivee" 
					placeholder="Entrez une description du lieu d'arrivée">
				</div>			
				<button type="submit" class="btn btn-default">Envoyer</button>
			</form:form>
		</div>
	</div>

</body>

<!-- Imports JS -->
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="resources/datepicker/js/bootstrap-datepicker.js"></script>
<script src="resources/datepicker/js/locales/bootstrap-datepicker.fr.js"></script>
<script type="text/javascript">
	$(function() {
		$('#dateDepart').datepicker({
			format: 'dd-mm-yyyy',
			language: 'fr-FR'
		});
	});

</script>
</html>