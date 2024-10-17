<%-- Denne må alltid være med for å si at resultatet er av typen ... --%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%-- Denne tar bort whitespace i toppen av generert HTML --%>
<%@ page trimDirectiveWhitespaces="true" %>

<%-- Denne må være med for å kunne bruke <c:forEach>-taggen --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="simple.css">
  </head>
  <body>
	<h1>Påmeldingsbekreftelse</h1>
	<p>Påmeldingen er mottatt for:</p>
	<ul>
		<li>${deltager.fornavn}</li>
		<li>${deltager.etternavn}</li>
		<li>${deltager.mobil}</li>
		<li>${deltager.kjonn}</li>
	</ul>
	<p><a href="/deltagerliste">Gå til deltagerliste</a></p>
  </body>
</html>
