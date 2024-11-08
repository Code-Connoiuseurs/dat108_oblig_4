<%-- Denne må alltid være med for å si at resultatet er av typen ... --%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%-- Denne tar bort whitespace i toppen av generert HTML --%>
<%@ page trimDirectiveWhitespaces="true" %>

<%-- Denne må være med for å kunne bruke <c:forEach>-taggen --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="simple.css">
</head>
<body>

    <h2>Logg inn</h2>

    <c:if test="${errors.size() > 0}">
        <p style="color:red;">Feil:</p>
        <ul style="color:red;">
            <c:forEach var="e" items="${errors}">
                <li>${e}</li>
            </c:forEach>
        </ul>
    </c:if>

    <form action="/login" method="post">
        <fieldset>
            <label for="mobil">Mobilnummer:</label>
            <input
                    type="tel" id="mobil" name="mobil" inputmode="numeric"
                    minlength="8" maxlength="8" pattern="^[0-9]{8}$" required
                    oninvalid="this.setCustomValidity('Mobil må være eksakt 8 siffer')"
                    oninput="this.setCustomValidity('')"
            >
            <label for="passord">Passord</label>
            <input
                    type="password" id="passord" name="passord"
                    minlength="8" pattern="^(?=.*\d)(?=.*[a-zæøå])(?=.*[A-ZÆØÅ])(?=.*[\W_])(?!.*\s).{8,}$" required
                    oninvalid="this.setCustomValidity('Passord må være minst 8 tegn langt og inneholde minst ett tall, minst én liten bokstav, minst én stor bokstav og minst ett spesialtegn. Mellomrom ikke tillatt.')"
                    oninput="this.setCustomValidity('')"
            ><br>
            <button type="submit">Login</button>
        </fieldset>
    </form>

</body>
</html>