<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MealsOp</title>
</head>
<body>

<form method="POST" action='add-meal' name="frmAddMeal">
    Meal ID : <input type="text" readonly="readonly" name="Id"
                     value="<c:out value="${meal.id}" />"/> <br/>
    Date/Time : <input
        type="text" name="Date/Time"
        value="<fmt:parseDate value="${ meal.getDateTime() }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                       type="both"/>
        <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/>"/> <br/>

        Description : <input
        type="text" name="Description"
        value="<c:out value="${meal.description}" />"/> <br/>
    Calories : <input
        type="text" name="Calories"
        value="<c:out value="${meal.calories}" />"/> <br/> <input
        type="submit" value="Submit"/>
</form>

</body>
</html>
