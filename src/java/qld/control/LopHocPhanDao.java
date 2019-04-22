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
import qld.model.GiaoVien;
import qld.model.LopHocPhan;
import qld.model.MonHoc;
import qld.model.TaiKhoan;

/**
 *
 * @author TruongDao
 */
public class LopHocPhanDao {

    private Connection conn = null;
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private String database = "";
    private String user = "";
    private String pass = "";

    public LopHocPhanDao(String database, String user, String pass) {
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

    public ArrayList<LopHocPhan> getListLHP(String khoaHoc, int kyHoc, GiaoVien giaoVien) {
        ArrayList<LopHocPhan> listLHP = new ArrayList<>();
        try {
            this.stm = this.conn.createStatement();
            String query = "SELECT * FROM dbo.LopHocPhan WHERE GiaoVienMaGV = '" + giaoVien.getMaGV()+"'";
            ResultSet rs = this.stm.executeQuery(query);
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                if (rs.getString("KhoaHoc").equals(khoaHoc) && rs.getInt("KyHoc") == kyHoc) {
                    LopHocPhan lopHocPhan = new LopHocPhan();
                    lopHocPhan.setId(rs.getInt("ID"));
                    lopHocPhan.setGiaoVienMaGV(new GiaoVien(rs.getString("GiaoVienMaGV")));
                    lopHocPhan.setKhoaHoc(rs.getString("KhoaHoc"));
                    lopHocPhan.setKip(rs.getInt("Kip"));
                    lopHocPhan.setKyHoc(rs.getInt("KyHoc"));
                    lopHocPhan.setMonHocID(new MonHoc(rs.getInt("MonHocID")));
                    lopHocPhan.setPhong(rs.getNString("Phong"));
                    lopHocPhan.setThu(rs.getString("Thu"));
                    listLHP.add(lopHocPhan);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopHocPhanDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listLHP;
    }

    public MonHoc getMonHoc(LopHocPhan lhp){
        MonHoc mh = new MonHoc();
        try {
            this.stm = this.conn.createStatement();
            String query = "SELECT * FROM dbo.LopHocPhan WHERE ID ="+lhp.getId();
            ResultSet rs = this.stm.executeQuery(query);
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                    mh.setId(rs.getInt("MonHocID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopHocPhanDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return mh;
    }
    public ArrayList<LopHocPhan> getListLHP(GiaoVien giaoVien) {
        ArrayList<LopHocPhan> listLHP = new ArrayList<>();
        try {
            this.stm = this.conn.createStatement();
            String query = "SELECT * FROM dbo.LopHocPhan WHERE GiaoVienMaGV = '"+giaoVien.getMaGV()+"'";
            ResultSet rs = this.stm.executeQuery(query);
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                    LopHocPhan lopHocPhan = new LopHocPhan();
                    lopHocPhan.setKhoaHoc(rs.getString("KhoaHoc"));
                    listLHP.add(lopHocPhan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopHocPhanDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listLHP;
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

    public ArrayList<LopHocPhan> getListLHP(String khoaHoc, int kyHoc, MonHoc monHoc, GiaoVien giaoVien) {
        ArrayList<LopHocPhan> listLHP = new ArrayList<>();
        try {
            this.stm = this.conn.createStatement();
            String query = "SELECT * FROM dbo.LopHocPhan WHERE GiaoVienMaGV = '" + giaoVien.getMaGV()+"'";
            ResultSet rs = this.stm.executeQuery(query);
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                if (rs.getString("KhoaHoc").equals(khoaHoc) && rs.getInt("KyHoc") == kyHoc && rs.getInt("MonHocID") == monHoc.getId()) {
                    LopHocPhan lopHocPhan = new LopHocPhan();
                    lopHocPhan.setId(rs.getInt("ID"));
                    lopHocPhan.setGiaoVienMaGV(new GiaoVien(rs.getString("GiaoVienMaGV")));
                    lopHocPhan.setKhoaHoc(rs.getString("KhoaHoc"));
                    lopHocPhan.setKip(rs.getInt("Kip"));
                    lopHocPhan.setKyHoc(rs.getInt("KyHoc"));
                    lopHocPhan.setMonHocID(new MonHoc(rs.getInt("MonHocID")));
                    lopHocPhan.setPhong(rs.getNString("Phong"));
                    lopHocPhan.setThu(rs.getString("Thu"));
                    listLHP.add(lopHocPhan);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopHocPhanDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listLHP;
    }

}
