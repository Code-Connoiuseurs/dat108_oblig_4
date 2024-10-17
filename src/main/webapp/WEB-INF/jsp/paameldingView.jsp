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
	<h1>Påmelding</h1>
	<form method="POST" action="/paamelding">
		<c:if test="${errors.size() > 0}">
			<ul>
				<c:forEach var="e" items="${errors}">
					<li>${e}</li>
				</c:forEach>			
			</ul>			
		</c:if>
		<fieldset>
			<label for="fornavn">Fornavn</label>
			<input type="text" name="fornavn" minlength="2" maxlength="20" required>
			
			<label for="etternavn">Etternavn</label>
			<input type="text" name="etternavn" minlength="2" maxlength="20" required>
			
			<label for="mobile">Mobil (8 siffer)</label>
			<input type="tel" name="mobil" inputmode="numeric" minlength="8" maxlength="8" required>
			
			<label for="passord">Passord</label>
			<input type="password" name="passord" required>
			
			<label for="passord-re">Passord repetert</label>
			<input type="password" name="passord_re" required>
			
			<label>Kjønn</label>
			<label for="mann">
				<input type="radio" name="kjonn" required value="mann" id="mann">				
				Mann
			</label>
			<label for="kvinne">
				<input type="radio" name="kjonn" required value="kvinne" id="kvinne">				
				Kvinne
			</label>
			
			<input type="submit" value="Registrer">
		</fieldset>
	</form>
  </body>
</html>
