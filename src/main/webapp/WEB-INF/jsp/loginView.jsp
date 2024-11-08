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

    <h2>Login</h2>

    <c:if test="${errors.size() > 0}">
        <p style="color:red;">Feil:</p>
        <ul style="color:red;">
            <c:forEach var="e" items="${errors}">
                <li>${e}</li>
            </c:forEach>
        </ul>
    </c:if>

    <form action="/login" method="post">
        <label for="mobil">Mobilnummer:</label>
        <input type="text" id="mobil" name="mobil" required> <br>
        <label for="passord">Passord</label>
        <input type="password" id="passord" name="passord" required> <br>
        <button type="submit">Login</button>
    </form>

</body>
</html>