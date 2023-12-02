
package com.draekk.todomaster.servlets;

import com.draekk.todomaster.controllers.GeneralController;
import com.draekk.todomaster.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

    GeneralController gc = new GeneralController();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        response.setCharacterEncoding("UTF-8");
        
        String email = (String)request.getParameter("email");
        String password = (String)request.getParameter("password");
        
        User user = gc.getLoginUser(email, password);
        
        if(user != null){
            HttpSession mySession = request.getSession(true);
            mySession.setAttribute("username", user.getUsername());
            mySession.setAttribute("user", user);
            mySession.setAttribute("tasks", user.getTasks());
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.html?message=Wrong+email+or+password");
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
