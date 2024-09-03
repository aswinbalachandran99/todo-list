package com.example.servlet;

import com.example.dao.TodoDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {
    private TodoDAO todoDAO = new TodoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("todos", todoDAO.getAllTodos());
        request.getRequestDispatcher("/todo-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String task = request.getParameter("task");
            todoDAO.addTodo(task);
        } else if ("toggle".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            todoDAO.toggleTodo(id);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            todoDAO.deleteTodo(id);
        }
        response.sendRedirect(request.getContextPath() + "/todo");
    }
}
