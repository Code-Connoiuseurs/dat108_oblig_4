<%-- Denne må alltid være med for å si at resultatet er av typen ... --%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%-- Denne tar bort whitespace i toppen av generert HTML --%>
<%@ page trimDirectiveWhitespaces="false" %>

<%-- Denne må være med for å kunne bruke <c:forEach>-taggen --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="simple.css">
  	<title>Deltagerliste</title>
  </head>
  <body>
	<h1>Deltagerliste</h1>
	<p>Du er innlogget som: ${innloggetDeltager.mobil} / ${innloggetDeltager.fornavn} ${innloggetDeltager.etternavn}</p>
	<table>
		<tr>
			<th>Kjønn</th>
			<th>Navn</th>
			<th>Mobil</th>
		</tr>
		<c:forEach var="deltager" items="${deltagere}">
			<tr ${deltager.mobil.equals(innloggetDeltager.mobil) ? 'style="background-color: green;"' : ''}>
				<td>${deltager.kjonn.equals("mann") ? "&#9794;" : "&#9792;"}</td>
				<td>${deltager.fornavn} ${deltager.etternavn}</td>
				<td>${deltager.mobil}</td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="/paamelding">Tilbake til påmelding</a>

	<form action="/logout" method="post">
		<input type="submit" value="Logg ut">
	</form>

  </body>
</html>
