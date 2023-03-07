package com.project.servlet;
import com.project.db.dao.ConstrainsTypeDao;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/constrainsType")
public class ConstrainsType extends HttpServlet {
    @Inject
    ConstrainsTypeDao constrainsTypeDao;
    //method to handle GET request
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getQueryString());

        request.setAttribute("constrainsTypes",constrainsTypeDao.getAll());
        request.getRequestDispatcher("/list/constrainsType.jsp").forward(request,response);

    }
    //method to handle POST request
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

