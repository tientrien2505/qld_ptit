/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qld.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import qld.model.DiemMonHoc;
import qld.model.MonHoc;
import qld.model.SinhVien;

/**
 *
 * @author TruongDao
 */
public class testDiemMonHocDao {

    public testDiemMonHocDao() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConnection() {
        DiemMonHocDao diemMonHocDao = new DiemMonHocDao("QLD_PTIT", "sa", "1");
        boolean result = diemMonHocDao.connection();
        boolean expectedOutput = true;
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testCapNhatDiem() {
        DiemMonHocDao diemMonHocDao = new DiemMonHocDao("QLD_PTIT", "sa", "1");
        DiemMonHoc diemMonHoc = new DiemMonHoc(1, 8, 9, 9, 9);
        diemMonHoc.setMonHocID(new MonHoc(1));
        diemMonHoc.setSinhVienMaSV(new SinhVien("B15DCCN582"));
        Connection conn = diemMonHocDao.getConn();
        try {
            conn.setAutoCommit(false);
            Savepoint savepoint = conn.setSavepoint("savepoint");
            boolean result = diemMonHocDao.capNhatDiem(diemMonHoc);
            conn.rollback(savepoint);
            boolean expectedOutput = true;
            assertEquals(expectedOutput, result);
        } catch (SQLException ex) {
            Logger.getLogger(testDiemMonHocDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testThemDiem() {
        DiemMonHocDao diemMonHocDao = new DiemMonHocDao("QLD_PTIT", "sa", "1");
        DiemMonHoc diemMonHoc = new DiemMonHoc(1, 8, 9, 9, 9);
        diemMonHoc.setMonHocID(new MonHoc(1));
        diemMonHoc.setSinhVienMaSV(new SinhVien("B15DCCN582"));
        Connection conn = diemMonHocDao.getConn();
        try {
            conn.setAutoCommit(false);
            Savepoint savepoint = conn.setSavepoint("savepoint");
            boolean result = diemMonHocDao.capNhatDiem(diemMonHoc);
            conn.rollback(savepoint);
            boolean expectedOutput = true;
            assertEquals(expectedOutput, result);
        } catch (SQLException ex) {
            Logger.getLogger(testDiemMonHocDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
