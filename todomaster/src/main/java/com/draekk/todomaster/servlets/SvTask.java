
package com.draekk.todomaster.servlets;

import com.draekk.todomaster.controllers.GeneralController;
import com.draekk.todomaster.models.Task;
import com.draekk.todomaster.models.User;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
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
import java.util.List;

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
        
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            System.out.println("saludos desde el doGet");
            User user = (User)request.getSession().getAttribute("user");
            List<Task> taskList = gc.getTaskList(user.getId());
            
            if(taskList != null){
                sendTaskListResponse(response, taskList);
            } else {
                sendFailedResponse(response, "Task list is empty");
            }
        } catch (Exception e) {
            sendErrorResponse(response, e);
        }
        
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
                sendFailedResponse(response, "Task creation failure");
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
    
    private void sendFailedResponse(HttpServletResponse response, String message)
        throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().println(message);
    }
    
    private void sendErrorResponse(HttpServletResponse response, Exception e)
        throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().println("Error: " + e.getMessage());
    }
    
    private void sendTaskListResponse(HttpServletResponse response, List<Task> taskList)
        throws IOException {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(Task t : taskList) {
            builder.add("id", t.getId());
            builder.add("description", t.getDescription());
            builder.add("isCompleted", t.isIsCompleted());
            JsonObject taskJson = builder.build();
            arrayBuilder.add(taskJson);
        }
        JsonArray taskListJson = arrayBuilder.build();
        
        response.getWriter().write(taskListJson.toString());
    }
}
