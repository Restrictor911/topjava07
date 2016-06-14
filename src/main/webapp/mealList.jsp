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
            <tr style="color: ${meal.exceed ? "red" : "green"};">
                <td>${meal.id}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>${meal.dateTime}</td>
                <td>
                    <a href="meals?action=update&id=<c:out value="${meal.id}"/>">Update</a>
                </td>
                <td>
                    <a href="meals?action=delete&id=<c:out value="${meal.id}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </table>
        <p><a href="meals?action=add">Add Meal</a></p>
    </body>
</html>

