/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import qld.control.DiemMonHocDao;
import qld.control.LopHocPhanDao;
import qld.control.SinhVienDao;
import qld.control.SinhVienLHPDao;
import qld.model.DiemMonHoc;
import qld.model.LopHocPhan;
import qld.model.MonHoc;
import qld.model.SinhVien;

/**
 *
 * @author TruongDao
 */
public class DanhSachSVServlet extends HttpServlet {

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
        try {
            int idLopHocPhan = Integer.parseInt(request.getParameter("idLopHocPhan"));
            System.out.println("idLopHocPhan: " + idLopHocPhan);
            LopHocPhan lopHocPhan = new LopHocPhan(idLopHocPhan);
            MonHoc monHoc = new LopHocPhanDao("QLD_PTIT", "sa", "1").getMonHoc(lopHocPhan);
            ArrayList<SinhVien> listSV = new SinhVienLHPDao("QLD_PTIT", "sa", "1").getListSinhVien(lopHocPhan);
            listSV = new SinhVienDao("QLD_PTIT", "sa", "1").getListSinhVien(listSV);
            if (listSV == null || listSV.isEmpty()) {
                System.out.println("danh sach sinh vien trong");
                return;
            }
            StringBuffer jsonStringSinhVien = new StringBuffer();
            jsonStringSinhVien.append("[");
            for (SinhVien sv : listSV) {
                DiemMonHoc diemMH = new DiemMonHocDao("QLD_PTIT", "sa", "1").getDiemMonHoc(sv, monHoc);
                if (diemMH == null){
                    diemMH = new DiemMonHoc(0, 0, 0, 0, 0);
                }
                jsonStringSinhVien.append("{");
                jsonStringSinhVien.append("\"hoTen\":\"" + sv.getHoTen() + "\",");
                jsonStringSinhVien.append("\"maSinhVien\":\"" + sv.getMaSV()+ "\",");
                jsonStringSinhVien.append("\"diemCC\":\"" + diemMH.getDiemCC() + "\",");
                jsonStringSinhVien.append("\"diemBT\":\"" + diemMH.getDiemBT() + "\",");
                jsonStringSinhVien.append("\"diemGK\":\"" + diemMH.getDiemGK() + "\",");
                jsonStringSinhVien.append("\"diemCK\":\"" + diemMH.getDiemCK() + "\"");
                jsonStringSinhVien.append("},");
            }
            jsonStringSinhVien = jsonStringSinhVien.deleteCharAt(jsonStringSinhVien.length() - 1);
            jsonStringSinhVien.append("]");
            
            response.getWriter().print(jsonStringSinhVien.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
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
