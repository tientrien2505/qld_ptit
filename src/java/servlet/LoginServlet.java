/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import qld.control.GiaoVienDao;
import qld.model.GiaoVien;
import qld.model.TaiKhoan;

/**
 *
 * @author TruongDao
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String signout = request.getParameter("signout");
        if (signout.equals("ok")) {
            session.setAttribute("user", "");
            session.setAttribute("pass", "");
            response.getWriter().write("ok");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("user", "");
            session.setAttribute("name", "");
            session.setAttribute("pass", "");
            session.setAttribute("id", "");
        }
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        // xử lý kiểm tra login
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setUsername(user);
        taiKhoan.setPassword(pass);
        if (taiKhoan.isTaiKhoan()){
            session.setAttribute("user", user);
            session.setAttribute("pass", pass);
            GiaoVien gv = new GiaoVienDao("QLD_PTIT", "sa", "1").getGiaoVien(taiKhoan);
            session.setAttribute("name", gv.getHoTen());
            session.setAttribute("id", gv.getMaGV());
            response.getWriter().write("{\"result\":\"ok\"}");
        } else{
            response.getWriter().write("{\"result\":\"not ok\"}");
        }
        
        
//        response.sendRedirect("view");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
