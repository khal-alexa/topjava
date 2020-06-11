<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<table border="2">
    <tr>
        <th>Id</th>
        <th>Date/Time</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan=2>Action</th>
    </tr>
    <c:forEach var="meal" items="${mealsTo}">
        <tr style="color:${meal.excess ? 'red' : 'green'}">
            <td><c:out value="${meal.getId()}"/></td>
            <td>
                <fmt:parseDate value="${ meal.getDateTime() }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                               type="both"/>
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/>
            </td>
            <td><c:out value="${meal.getDescription()}"/></td>
            <td><c:out value="${meal.getCalories()}"/></td>
            <td><a href="RepositoryServlet?action=edit&mealId=<c:out value="${meal.id}"/>">Update</a></td>
            <td><a href="RepositoryServlet?action=delete&mealId=<c:out value="${meal.id}"/>">Delete</a></td>
        </tr>
        </tr>
    </c:forEach>
</table>
<p><a href="add-meal?action=insert">Add Meal</a></p>
</body>
</html>
