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
import qld.model.GiaoVien;
import qld.model.TaiKhoan;

/**
 *
 * @author TruongDao
 */
public class GiaoVienDao {

    private Connection conn = null;
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private String database = "";
    private String user = "";
    private String pass = "";

    public GiaoVienDao(String database, String user, String pass) {
        this.database = database;
        this.user = user;
        this.pass = pass;

        connection();
        
    }
    
    public boolean connection(){
        String connectionUrl = "jdbc:sqlserver://localhost;Instance=.\\TRUONGDAO:1433;databaseName=" + database + ";user=" + user + ";password=" + pass;
        try {
            System.out.println("start connect database qld_ptit");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connectionUrl);
            System.out.println("connect successfull");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public GiaoVien getGiaoVien(TaiKhoan taiKhoan) {
        GiaoVien giaoVien = new GiaoVien();
        try {
            this.stm = this.conn.createStatement();
            String query = "SELECT * FROM dbo.GiaoVien";
            ResultSet rs = this.stm.executeQuery(query);
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                if (rs.getInt("TaiKhoanID") == taiKhoan.getId()) {
                    giaoVien.setMaGV(rs.getString("MaGV"));
                    giaoVien.setDiaChi(rs.getNString("DiaChi"));
                    giaoVien.setEmail(rs.getString("Email"));
                    giaoVien.setHoTen(rs.getNString("HoTen"));
                    giaoVien.setSdt(rs.getString("Sdt"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return giaoVien;
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
