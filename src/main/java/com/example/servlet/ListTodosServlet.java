package com.example.servlet;

import com.example.util.TodoStorage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/list")
public class ListTodosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filter = request.getParameter("filter");
        if (filter == null) {
            filter = "all";
        }
        request.setAttribute("todos", TodoStorage.getFilteredTodos(filter));
        request.setAttribute("currentFilter", filter);
        request.getRequestDispatcher("/todoList.jsp").forward(request, response);
    }
}
