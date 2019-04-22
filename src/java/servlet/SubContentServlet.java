/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import home.control.ContentToJson;
import home.control.NewsDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import home.model.SubContent;

/**
 *
 * @author TruongDao
 */
public class SubContentServlet extends HttpServlet {

    
   
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String link = request.getParameter("link");
//        String path = request.getServletContext().getRealPath("/json/content.json");
//        path = path.replace('\\', '/');
//        new ContentToJson().get(link, path);
//        response.sendRedirect("content.jsp");
        response.setCharacterEncoding("UTF-8");
        SubContent subContent = new SubContent();
        subContent = new NewsDAO("LapTrinhWeb", "sa", "1").selectSubContent(request.getParameter("link"));
        request.setAttribute("subContent", subContent);
        RequestDispatcher rd = request.getRequestDispatcher("news/news.jsp");
        rd.forward(request, response);
//        String json = new SubContent().subContentToJson(subContent);
//        PrintWriter pw = response.getWriter();
//        pw.write(json);    
//        System.out.println(json);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
