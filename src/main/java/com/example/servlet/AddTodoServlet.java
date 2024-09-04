package com.example.servlet;

import com.example.util.TodoStorage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/add")
public class AddTodoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task = request.getParameter("task");
        String dueDateStr = request.getParameter("dueDate");

        if (task != null && !task.trim().isEmpty() && dueDateStr != null && !dueDateStr.trim().isEmpty()) {
            try {
                LocalDate dueDate = LocalDate.parse(dueDateStr);
                TodoStorage.addTodo(task, dueDate);
            } catch (Exception e) {
                // Log the error or handle it as appropriate for your application
                System.err.println("Error adding todo: " + e.getMessage());
            }
        }

        response.sendRedirect(request.getContextPath() + "/list");
    }
}