/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import qld.control.DiemMonHocDao;
import qld.control.LopHocPhanDao;
import qld.control.MonHocDao;
import qld.model.DiemMonHoc;
import qld.model.LopHocPhan;
import qld.model.MonHoc;
import qld.model.SinhVien;

/**
 *
 * @author TruongDao
 */
public class NhapDiemServlet extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        StringBuffer buff = new StringBuffer();
        String idSV = request.getParameter("idSinhVien");
        try {
            DiemMonHoc diemMonHoc = new DiemMonHoc();
            diemMonHoc.setDiemBT(Float.parseFloat(request.getParameter("diemBT")));
            System.out.println(Float.parseFloat(request.getParameter("diemBT")));
            diemMonHoc.setDiemCC(Float.parseFloat(request.getParameter("diemCC")));
            diemMonHoc.setDiemGK(Float.parseFloat(request.getParameter("diemGK")));
            diemMonHoc.setDiemCK(Float.parseFloat(request.getParameter("diemCK")));
            MonHoc monHoc = new LopHocPhanDao("QLD_PTIT", "sa", "1").getMonHoc(new LopHocPhan(Integer.parseInt(request.getParameter("idLopHocPhan"))));
            diemMonHoc.setMonHocID(monHoc);
            diemMonHoc.setSinhVienMaSV(new SinhVien(request.getParameter("idSinhVien")));
            buff.append("{\"trangThai\":\"");
            if (new DiemMonHocDao("QLD_PTIT", "sa", "1").capNhatDiem(diemMonHoc)) {
                buff.append("capNhat\",");
            } else if (new DiemMonHocDao("QLD_PTIT", "sa", "1").themDiemMonHoc(diemMonHoc)) {
                buff.append("them\",");
            } else {
                buff.append("notOk\",");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            buff.append("notOk\",");
        } finally{
            buff.append("\"idSV\":\""+idSV+"\"}");
            response.getWriter().print(buff.toString());
        }
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
