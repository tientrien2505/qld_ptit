/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import home.control.GiaoVuDAO;
import home.control.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import home.model.GiaoVu;
import home.model.News;

/**
 *
 * @author TruongDao
 */
public class GiaoVuServlet extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        int sl = Integer.parseInt(request.getParameter("sl"));
        int offset = Integer.parseInt(request.getParameter("offset"));
        ArrayList<GiaoVu> listGiaoVu;
        listGiaoVu = new GiaoVu().getListGiaoVu();
        GiaoVuDAO giaoVuDAO = new GiaoVuDAO("LapTrinhWeb", "sa", "1");
        giaoVuDAO.insert(listGiaoVu);
        listGiaoVu = giaoVuDAO.select(offset, sl);
        PrintWriter pw = response.getWriter();
        pw.write(new GiaoVu().giaoVuToJson(listGiaoVu));
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
