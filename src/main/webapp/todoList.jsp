<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Todo List</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f0f0f0;
        }
        h1 {
            color: #333;
            text-align: center;
        }
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        input[type="text"], input[type="date"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 3px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 3px;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            background-color: #fff;
            margin-bottom: 10px;
            padding: 15px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .completed {
            text-decoration: line-through;
            color: #888;
        }
        .actions a {
            margin-left: 10px;
            color: #333;
            text-decoration: none;
        }
        .filter {
            margin-bottom: 20px;
            text-align: center;
        }
        .filter a {
            margin: 0 10px;
            color: #333;
            text-decoration: none;
        }
        .filter a.active {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Todo List</h1>
    <form action="${pageContext.request.contextPath}/add" method="post">
        <input type="text" name="task" placeholder="Enter a new task" required>
        <input type="date" name="dueDate" required>
        <input type="submit" value="Add Todo">
    </form>
    
    <div class="filter">
        <a href="${pageContext.request.contextPath}/list?filter=all" class="${currentFilter == 'all' ? 'active' : ''}">All</a>
        <a href="${pageContext.request.contextPath}/list?filter=active" class="${currentFilter == 'active' ? 'active' : ''}">Active</a>
        <a href="${pageContext.request.contextPath}/list?filter=completed" class="${currentFilter == 'completed' ? 'active' : ''}">Completed</a>
    </div>

    <ul>
        <c:forEach var="todo" items="${todos}">
            <li>
                <span class="${todo.completed ? 'completed' : ''}">
                    ${todo.task} 
                    <small>(Due: <fmt:formatDate value="${todo.dueDate}" pattern="yyyy-MM-dd" />)</small>
                </span>
                <span class="actions">
                    <a href="#" onclick="editTodo(${todo.id}, '${todo.task}', '${todo.dueDate}', ${todo.completed})"><i class="fas fa-edit"></i></a>
                    <a href="${pageContext.request.contextPath}/delete?id=${todo.id}"><i class="fas fa-trash"></i></a>
                </span>
            </li>
        </c:forEach>
    </ul>

    <div id="editModal" style="display: none; position: fixed; z-index: 1; left: 0; top: 0; width: 100%; height: 100%; overflow: auto; background-color: rgba(0,0,0,0.4);">
        <div style="background-color: #fefefe; margin: 15% auto; padding: 20px; border: 1px solid #888; width: 80%; max-width: 500px;">
            <form id="editForm" action="${pageContext.request.contextPath}/update" method="post">
                <input type="hidden" id="editId" name="id">
                <input type="text" id="editTask" name="task" required>
                <input type="date" id="editDueDate" name="dueDate" required>
                <label><input type="checkbox" id="editCompleted" name="completed"> Completed</label>
                <input type="submit" value="Update Todo">
            </form>
            <button onclick="document.getElementById('editModal').style.display='none'">Cancel</button>
        </div>
    </div>

    <script>
        function editTodo(id, task, dueDate, completed) {
            document.getElementById('editId').value = id;
            document.getElementById('editTask').value = task;
            document.getElementById('editDueDate').value = dueDate;
            document.getElementById('editCompleted').checked = completed;
            document.getElementById('editModal').style.display = 'block';
        }
    </script>
</body>
</html>
