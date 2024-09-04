package com.example.servlet;

import com.example.util.TodoStorage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/update")
public class UpdateTodoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String task = request.getParameter("task");
            LocalDate dueDate = LocalDate.parse(request.getParameter("dueDate"));
            boolean completed = Boolean.parseBoolean(request.getParameter("completed"));

            if (task != null && !task.trim().isEmpty()) {
                TodoStorage.updateTodo(id, task, dueDate, completed);
            }
        } catch (NumberFormatException | NullPointerException e) {
            // Log the error or handle it as appropriate for your application
            System.err.println("Error updating todo: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/list");
    }
}
