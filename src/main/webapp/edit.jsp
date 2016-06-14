<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>
    <title>Edit form</title>
</head>
<body>
    <form method="POST" action='meals' name="frmAddUser">
        <label>Meal ID:
            <input type="text" readonly="readonly" name="id"
                         value="<c:out value="${meal.id}" />"
        </label>
        <br/>
        <label>Description:
            <input type="text" name="description"
            value="<c:out value="${meal.description}" />" />
            <br/>
        </label>
        <label>Calories:
            <input type="text" name="calories"
                   value="<c:out value="${meal.calories}" />" />
            <br/>
        </label>
        <label>Date:
            <input type="text" name="datetime"
                value="${meal.toDateString()}" />
        </label>
        <br/>
        <input type="submit" />
    </form>
</body>
</html>
