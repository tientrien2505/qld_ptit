/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import qld.control.LopHocPhanDao;
import qld.control.MonHocDao;
import qld.model.GiaoVien;
import qld.model.LopHocPhan;
import qld.model.MonHoc;

/**
 *
 * @author TruongDao
 */
public class MonHocServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String id;
        try {
            id = session.getAttribute("id").toString();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }
        GiaoVien giaoVien = new GiaoVien(id);
        String khoaHoc = request.getParameter("khoaHoc");
        int kyHoc = Integer.parseInt(request.getParameter("kyHoc"));
        ArrayList<LopHocPhan> listLHP = new LopHocPhanDao("QLD_PTIT", "sa", "1").getListLHP(khoaHoc, kyHoc, giaoVien);
        if (listLHP == null) {
            return;
        }

        ArrayList<MonHoc> listMH = new ArrayList<>();
        for (LopHocPhan lhp : listLHP) {
            MonHoc monHoc = new MonHocDao("QLD_PTIT", "sa", "1").getMonHoc(lhp);
            if (!chua(monHoc,listMH)) {
                listMH.add(monHoc);
            }
        }
        if (listMH.isEmpty()) {
            return;
        }
        StringBuffer jsonStringMH = new StringBuffer();
        jsonStringMH.append("[");
        for (MonHoc mh : listMH) {
            jsonStringMH.append("{");
            jsonStringMH.append("\"idMonHoc\":\"" + mh.getId() + "\",");
            jsonStringMH.append("\"monHoc\":\"" + mh.getTen() + "\"");
            jsonStringMH.append("},");
        }
        jsonStringMH = jsonStringMH.deleteCharAt(jsonStringMH.length() - 1);
        jsonStringMH.append("]");

        try {
            response.getWriter().print(jsonStringMH.toString());
        } catch (IOException ex) {
            Logger.getLogger(KhoaHocServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean chua(MonHoc monHoc, ArrayList<MonHoc> listMH){
        for (MonHoc mh:listMH){
            if (monHoc.getId() == mh.getId())
                return true;
        }
        return false;
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
