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
    <p>Du er logget inn som: ${deltager.fornavn} ${deltager.etternavn}</p>
	
	<table>
		<tr>
			<th>Kjønn</th>
			<th>Navn</th>
			<th>Mobil</th>
		</tr>
		<c:forEach var="d" items="${deltagere}">
			<c:if test="${d.mobil == deltager.mobil}">
                <tr style="background-color: green">                
            </c:if>
				<td>${d.kjonn ==  "mann" ? "&#9794;" : "&#9792;"}</td>
				<td>${d.fornavn} ${d.etternavn}</td>
				<td>${d.mobil}</td>
			</tr>
		</c:forEach>
	</table>
	
    <form action="logout" method="post">
                <p><input type="submit" value="Logg ut" /></p>
        </form>
  </body>
</html>
