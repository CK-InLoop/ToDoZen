package com.todozen.controller;

import com.todozen.dao.TaskDAO;
import com.todozen.model.Task;
import com.todozen.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");
        TaskDAO taskDAO = new TaskDAO();
        List<Task> tasks = taskDAO.getTasksByUser(user.getId());
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("jsp/dashboard.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");
        TaskDAO taskDAO = new TaskDAO();
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            Task task = new Task();
            task.setUserId(user.getId());
            task.setTitle(request.getParameter("title"));
            task.setDescription(request.getParameter("description"));
            task.setDueDate(LocalDate.parse(request.getParameter("dueDate")));
            task.setPriority(request.getParameter("priority"));
            task.setCompleted(false);
            task.setTags(request.getParameter("tags"));
            task.setCreatedAt(LocalDate.now());
            taskDAO.addTask(task);
        } else if ("update".equals(action)) {
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            Task task = taskDAO.getTaskById(taskId, user.getId());
            if (task != null) {
                task.setTitle(request.getParameter("title"));
                task.setDescription(request.getParameter("description"));
                task.setDueDate(LocalDate.parse(request.getParameter("dueDate")));
                task.setPriority(request.getParameter("priority"));
                task.setTags(request.getParameter("tags"));
                task.setCompleted("on".equals(request.getParameter("completed")));
                taskDAO.updateTask(task);
            }
        } else if ("delete".equals(action)) {
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            taskDAO.deleteTask(taskId, user.getId());
        }
        response.sendRedirect("tasks");
    }
}
