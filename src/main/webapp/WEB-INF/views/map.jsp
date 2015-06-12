<%@page import="org.springframework.context.annotation.Import"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<!-- Imports CSS -->
<link href="resources/bootstrap/css/bootstrap.css" type="text/css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="resources/css/tittineShare.css">
<script src="http://maps.googleapis.com/maps/api/js"></script>

<script>
			${scriptPoints}

			function initialize(){

				var mapProp = {
  					center:pos${lastPosIndex},
  					zoom:19,
                                        streetViewControl:false,
					zoomControlOptions: {
      						style:google.maps.ZoomControlStyle.SMALL
    					},
  					mapTypeId:google.maps.MapTypeId.ROADMAP
  				};
  
				var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
				map.setTilt(0);

				var myTrip = ${scriptPolyline};

				var flightPath = new google.maps.Polyline({
  					path:myTrip,
  					strokeColor:"#FF5555",
  					strokeOpacity:0.8,
  					strokeWeight:3
				});

				var marker=new google.maps.Marker({
		  			position:pos${lastPosIndex},
		  		});

		  		marker.setMap(map);

				flightPath.setMap(map);
			
			}

			google.maps.event.addDomListener(window, 'load', initialize);
		
		</script>

</head>
<body>
	<h1>Trajet mission</h1>
	<h2>Lattitude&nbsp;: ${lat} - Longitude&nbsp;: ${lat}</h2>
	<div id="googleMap" style="width: 800px; height: 600px;"></div>
</body>

<!-- Imports JS -->
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="resources/js/accueil.js"></script>
</html>