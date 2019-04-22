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
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import qld.model.DiemMonHoc;
import qld.model.GiaoVien;
import qld.model.MonHoc;
import qld.model.SinhVien;
import qld.model.TaiKhoan;

/**
 *
 * @author TruongDao
 */
public class DiemMonHocDao {

    private Connection conn = null;
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private String database = "";
    private String user = "";
    private String pass = "";

    public DiemMonHocDao(String database, String user, String pass) {
        this.database = database;
        this.user = user;
        this.pass = pass;
        connection();
    }
    
// connect database
    public boolean connection() {
        String connectionUrl = "jdbc:sqlserver://localhost;Instance=.\\TRUONGDAO:1433;databaseName=" + database + ";user=" + user + ";password=" + pass;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(connectionUrl);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
// update diem mon hoc
    public boolean capNhatDiem(DiemMonHoc diemMonHoc) {
        // kiem tra ket noi co so du lieu ko?
        if (conn == null)
            return false;
        try {
            // lay diem mon hoc cua sinh vien nay va mon hoc nay
            DiemMonHoc dmh = getDiemMonHoc(diemMonHoc.getSinhVienMaSV(), diemMonHoc.getMonHocID());
            // neu khong ton tai trong csdl thi khong can cap nhat diem
            if (dmh == null) {
                return false;
            }
            // neu ton tai trong csdl thi thuc thi cap nhat
            else {
                // neu ma diem trong csdl va diem moi cap nhat khong khac nhau thi khong can cap nhat
                if (dmh.getDiemBT() == diemMonHoc.getDiemBT() && dmh.getDiemCC() == diemMonHoc.getDiemCC() && dmh.getDiemCK() == diemMonHoc.getDiemCK() && dmh.getDiemGK() == diemMonHoc.getDiemGK()) {
                    return true;
                }
            }
            // neu diem trong csdl khac voi diem cap nhat thi thuc hien lenh cap nhat
            String query = "UPDATE dbo.DiemMonHoc SET DiemCC = ? , DiemBT = ? , DiemGK = ? , DiemCK = ? "
                    + "WHERE MonHocID = ?"
                    + " AND SinhVienMaSV = ?";
            pstm = conn.prepareStatement(query);
            pstm.setFloat(1, diemMonHoc.getDiemCC());
            pstm.setFloat(2, diemMonHoc.getDiemBT());
            pstm.setFloat(3, diemMonHoc.getDiemGK());
            pstm.setFloat(4, diemMonHoc.getDiemCK());
            pstm.setInt(5, diemMonHoc.getMonHocID().getId());
            pstm.setString(6, diemMonHoc.getSinhVienMaSV().getMaSV());
            int soBanCapNhat = pstm.executeUpdate();
            if (soBanCapNhat != 0) {
                DiemMonHoc dmhSau = getDiemMonHoc(diemMonHoc.getSinhVienMaSV(), diemMonHoc.getMonHocID());
                // kiem tra diem mon hoc sau khi cap nhat va truoc khi cap nhat co giong nhau khong
                // giong thi chua cap nhat thanh cong
                if (dmh == dmhSau) {
                    return false;
                } 
                // con khac nhau thi cap nhat thanh cong
                else {
                    return true;
                }
            } 
            else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // va tra ket qua khong cap nhat thanh cong
            return false;
        }
    }

    public boolean themDiemMonHoc(DiemMonHoc diemMonHoc) {
        // kiem tra ket noi csdl co khong
        if (conn == null)
            return false;
        try {
            String query = "INSERT INTO dbo.DiemMonHoc (MonHocID,SinhVienMaSV,DiemCC,DiemBT,DiemGK,DiemCK) values"
                    + "(?,?,?,?,?,?)";
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, diemMonHoc.getMonHocID().getId());
            pstm.setString(2, diemMonHoc.getSinhVienMaSV().getMaSV());
            pstm.setFloat(3, diemMonHoc.getDiemCC());
            pstm.setFloat(4, diemMonHoc.getDiemBT());
            pstm.setFloat(5, diemMonHoc.getDiemGK());
            pstm.setFloat(6, diemMonHoc.getDiemCK());
            pstm.executeUpdate();
            // kiem tra diem co duoc them vao csdl chua
            DiemMonHoc dmh = getDiemMonHoc(diemMonHoc.getSinhVienMaSV(), diemMonHoc.getMonHocID());
            if (dmh == null) {
                return false;
            }
        } catch (SQLException ex) {
            // co ngoai le xay ra thi tra ve ket qua false
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public DiemMonHoc getDiemMonHoc(SinhVien sinhVien, MonHoc monHoc) {
        // kiem tra ket noi csdl
        if (conn == null)
            return null;
        DiemMonHoc diemMH = new DiemMonHoc();
        String query = "SELECT * FROM dbo.DiemMonHoc WHERE SinhVienMaSV = '" + sinhVien.getMaSV()+ "'"
                + "AND MonHocID = " + monHoc.getId();
        try {
            stm = conn.createStatement();
            ResultSet result = stm.executeQuery(query);
            // khong co ket qua
            if (result == null) {
                return null;
            }
            result.next();
            diemMH.setDiemBT(result.getFloat("DiemBT"));
            diemMH.setDiemCC(result.getFloat("DiemCC"));
            diemMH.setDiemCK(result.getFloat("DiemCK"));
            diemMH.setDiemGK(result.getFloat("DiemGK"));
            diemMH.setId(result.getInt("ID"));
            diemMH.setMonHocID(monHoc);
            diemMH.setSinhVienMaSV(sinhVien);
            return diemMH;
        } catch (SQLException ex) {
            Logger.getLogger(DiemMonHocDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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
