
package com.draekk.todomaster.servlets;

import com.draekk.todomaster.controllers.GeneralController;
import com.draekk.todomaster.models.Task;
import com.draekk.todomaster.models.User;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvTask", urlPatterns = {"/SvTask"})
public class SvTask extends HttpServlet {
    
    GeneralController gc = new GeneralController();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvTask</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvTask at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        
        try {
            BufferedReader reader = request.getReader();
            String jsonString = reader.readLine();
            JsonObject json = Json.createReader(new StringReader(jsonString)).readObject();
        
            String description = json.getString("description");
            User user = (User)request.getSession().getAttribute("user");
            
            Task newTask = gc.createTask(description, user);
            
            if(newTask != null) {
                sendTaskCreatedResponse(response, newTask);
            } else {
                sendTaskCreationFailedResponse(response);
            }
            
        } catch (Exception e) {
            sendErrorResponse(response, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void sendTaskCreatedResponse(HttpServletResponse response, Task newTask)
        throws IOException {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        
        builder.add("id", newTask.getId());
        builder.add("description", newTask.getDescription());
        builder.add("isCompleted", newTask.isIsCompleted());
        
        JsonObject taskJson = builder.build();
        
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().println(taskJson.toString());
    }
    
    private void sendTaskCreationFailedResponse(HttpServletResponse response)
        throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().println("Failed to create task");
    }
    
    private void sendErrorResponse(HttpServletResponse response, Exception e)
        throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().println("Error: " + e.getMessage());
    }
    
}
