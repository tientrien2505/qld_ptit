/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qld.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import qld.model.LopHocPhan;
import qld.model.MonHoc;
import qld.model.SinhVien;

/**
 *
 * @author TruongDao
 */
public class SinhVienDao {

    private Connection conn = null;
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private String database = "";
    private String user = "";
    private String pass = "";

    public SinhVienDao(String database, String user, String pass) {
        this.database = database;
        this.user = user;
        this.pass = pass;

        String connectionUrl = "jdbc:sqlserver://localhost;Instance=.\\TRUONGDAO:1433;databaseName=" + database + ";user=" + user + ";password=" + pass;
        try {
            System.out.println("start connect database qld_ptit");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connectionUrl);
            System.out.println("connect successfull");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<SinhVien> getListSinhVien(ArrayList<SinhVien> listSV) {
        ArrayList<SinhVien> list = new ArrayList<>();
        try {
            for (SinhVien sv : listSV) {
                this.stm = this.conn.createStatement();
                String query = "SELECT * FROM dbo.SinhVien WHERE MaSV = '" + sv.getMaSV()+"'";
                ResultSet rs = this.stm.executeQuery(query);
                rs.next();
                sv.setHoTen(rs.getNString("HoTen"));
                list.add(sv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopHocPhanDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStm() {
        return stm;
    }

    public void setStm(Statement stm) {
        this.stm = stm;
    }

    public PreparedStatement getPstm() {
        return pstm;
    }

    public void setPstm(PreparedStatement pstm) {
        this.pstm = pstm;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
