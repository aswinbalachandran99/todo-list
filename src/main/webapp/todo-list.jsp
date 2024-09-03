<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Todo List</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 20px; }
        h1 { color: #333; }
        ul { list-style-type: none; padding: 0; }
        li { margin-bottom: 10px; }
        form { margin-bottom: 20px; }
        input[type="text"] { padding: 5px; width: 200px; }
        input[type="submit"] { padding: 5px 10px; }
    </style>
</head>
<body>
    <h1>Todo List</h1>
    <form action="${pageContext.request.contextPath}/todo" method="post">
        <input type="hidden" name="action" value="add">
        <input type="text" name="task" placeholder="Enter a new task" required>
        <input type="submit" value="Add Task">
    </form>
    <ul>
        <c:forEach var="todo" items="${todos}">
            <li>
                <c:choose>
                    <c:when test="${todo.completed}">
                        <del>${todo.task}</del>
                    </c:when>
                    <c:otherwise>
                        ${todo.task}
                    </c:otherwise>
                </c:choose>
                <form action="${pageContext.request.contextPath}/todo" method="post" style="display: inline;">
                    <input type="hidden" name="action" value="toggle">
                    <input type="hidden" name="id" value="${todo.id}">
                    <input type="submit" value="${todo.completed ? 'Undo' : 'Complete'}">
                </form>
                <form action="${pageContext.request.contextPath}/todo" method="post" style="display: inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${todo.id}">
                    <input type="submit" value="Delete">
                </form>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
