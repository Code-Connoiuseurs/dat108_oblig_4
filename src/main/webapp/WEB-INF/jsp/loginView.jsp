<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="simple.css">
    <title> Login </title>
</head>

<!-- bruker Mobilnummer med login siden mobil skal være unik til hver person. -->

<body>
    <h2> Login </h2>
    <form action = "${pageContext.request.contextPath}/login" method="post">
        <label for="mobil"> Mobilnummer:</label>
        <input type="text" id="mobil" name="mobil" required> <br>

        <label for="passord"> Passord </label>
        <input type="password" id="passord" name="passord" required> <br>

        <button type="submit"> Login </button>
    </form>

 <c:if test="${not empty loginError}">
    <p style="color:red;">${loginError}</p>  
 </c:if>
 
 <c:if test="${not empty redirectMessage}">
    <p style="color:red;">${redirectMessage}</p>  
 </c:if>
 
 <a href="/paamelding">Til påmelding</a>


</body>
</html>