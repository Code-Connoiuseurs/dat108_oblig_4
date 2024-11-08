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
	  <title>Påmelding</title>
  </head>	
  <body>
	<h1>Påmelding</h1>
	<form method="POST" action="/paamelding">
		<c:if test="${errors.size() > 0}">
			<p style="color:red;">Feil:</p>
			<ul style="color:red;">
				<c:forEach var="e" items="${errors}">
					<li>${e}</li>
				</c:forEach>			
			</ul>			
		</c:if>
		<fieldset>
			<label for="fornavn">Fornavn</label>
			<input
			   type="text" id="fornavn" name="fornavn"
			   minlength="2" maxlength="20" pattern="^[A-ZÆØÅ][a-zæøåA-ZÆØÅ \-]{1,19}$" required
			   oninvalid="this.setCustomValidity('Fornavn må være 2-20 bokstaver, ha stor forbokstav, og kan inneholde mellomrom og bindestrek')"
			   oninput="this.setCustomValidity('')"
			>

			<label for="etternavn">Etternavn</label>
			<input
				type="text" id="etternavn" name="etternavn"
				minlength="2" maxlength="20" pattern="^[a-zæøåA-ZÆØÅ \-]{2,20}$" required
				oninvalid="this.setCustomValidity('Etternavn må være 2-20 bokstaver, ha stor forbokstav, og kan inneholde mellomrom og bindestrek')"
				oninput="this.setCustomValidity('')"
			>
			
			<label for="mobile">Mobil (8 siffer)</label>
			<input
				type="tel" id="mobile" name="mobil" inputmode="numeric"
				minlength="8" maxlength="8" pattern="^[0-9]{8}$" required
				oninvalid="this.setCustomValidity('Mobil må være eksakt 8 siffer')"
				oninput="this.setCustomValidity('')"
			>
			
			<label for="passord">Passord</label>
			<input
				type="password" id="passord" name="passord"
				minlength="8" pattern="^(?=.*\d)(?=.*[a-zæøå])(?=.*[A-ZÆØÅ])(?=.*[\W_])(?!.*\s).{8,}$" required
				oninvalid="this.setCustomValidity(
'Passord må være minst 8 tegn langt og inneholde minst ett tall,\
minst én liten bokstav, minst én stor bokstav og minst ett spesialtegn.\
Mellomrom ikke tillatt.'
				)"
				oninput="this.setCustomValidity('')"
			>
			
			<label for="passord_re">Passord repetert</label>
			<input
				type="password" id="passord_re" name="passord_re"
				minlength="8" pattern="^(?=.*\d)(?=.*[a-zæøå])(?=.*[A-ZÆØÅ])(?=.*[\W_])(?!.*\s).{8,}$" required
				value=""
				oninvalid="this.setCustomValidity(
'Passord må være minst 8 tegn langt og inneholde minst ett tall,\
minst én liten bokstav, minst én stor bokstav og minst ett spesialtegn.\
Mellomrom ikke tillatt.'
				)"
				oninput="this.setCustomValidity('')"
			>
			
			<label>Kjønn</label>
			<label for="mann">
				<input type="radio" name="kjonn" required value="mann" id="mann">
				&#9794; - Mann
			</label>
			<label for="kvinne">
				<input type="radio" name="kjonn" required value="kvinne" id="kvinne">
				&#9792; - Kvinne
			</label>
			
			<input type="submit" value="Registrer">
		</fieldset>
		<a href="/deltagerliste">Se deltagerliste</a>
	</form>
  </body>
</html>
