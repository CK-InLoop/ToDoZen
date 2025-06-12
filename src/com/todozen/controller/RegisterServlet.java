package com.todozen.controller;

import com.todozen.dao.UserDAO;
import com.todozen.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        if (userDAO.userExists(username)) {
            request.setAttribute("error", "Username already exists");
            request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
            return;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if (userDAO.registerUser(user)) {
            response.sendRedirect("jsp/login.jsp");
        } else {
            request.setAttribute("error", "Registration failed");
            request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
    }
}
