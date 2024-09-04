package com.example.servlet;

import com.example.util.TodoStorage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteTodoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                TodoStorage.deleteTodo(id);
            } catch (NumberFormatException e) {
                // Log the error or handle it as appropriate for your application
                System.err.println("Invalid ID format: " + idStr);
            }
        }
        response.sendRedirect(request.getContextPath() + "/list");
    }
}
