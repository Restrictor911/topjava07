<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Meal list</title>
    </head>
    <body>
        <h2><a href="index.html">Home</a></h2>
        <h2>Meal list</h2>
        <table>
        <c:forEach items="${meals}" var="meal">
            <c:if test="${meal.exceed}">
                <tr style="color: red;">
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td>${meal.dateTime}</td>
                </tr>
            </c:if>
            <c:if test="${!meal.exceed}">
                <tr style="color: green;">
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td>${meal.dateTime}</td>
                </tr>
            </c:if>
        </c:forEach>
        </table>
    </body>
</html>

