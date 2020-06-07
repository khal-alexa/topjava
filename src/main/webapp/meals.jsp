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
        <th>Date/Time</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach var="meal" items="${mealsTo}">
        <c:if test="${meal.isExcess() == true}">
            <tr style="color: red">
                <td>
                    <fmt:parseDate value="${ meal.getDateTime() }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                   type="both"/>
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/>
                </td>
                <td>
                    <c:out value="${meal.getDescription()}"/>
                </td>
                <td>
                    <c:out value="${meal.getCalories()}"/>
                </td>
            </tr>
        </c:if>

        <c:if test="${meal.isExcess() == false}">
            <tr style="color: green">
                <td>
                    <fmt:parseDate value="${ meal.getDateTime() }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                   type="both"/>
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/>
                </td>
                <td>
                    <c:out value="${meal.getDescription()}"/>
                </td>
                <td>
                    <c:out value="${meal.getCalories()}"/>
                </td>
            </tr>
        </c:if>
    </c:forEach>
</table>
</body>
</html>
