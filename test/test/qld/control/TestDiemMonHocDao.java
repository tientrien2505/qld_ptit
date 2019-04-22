/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.qld.control;

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
import qld.control.DiemMonHocDao;
import qld.control.testDiemMonHocDao;
import qld.model.DiemMonHoc;
import qld.model.MonHoc;
import qld.model.SinhVien;

/**
 *
 * @author TruongDao
 */
public class TestDiemMonHocDao {

    public TestDiemMonHocDao() {
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
    public void testConnectionTrue() {
        DiemMonHocDao diemMonHocDao = new DiemMonHocDao("QLD_PTIT", "sa", "1");
        boolean expected = true;
        boolean result = diemMonHocDao.connection();
        assertEquals(expected, result);
    }

    @Test
    public void testConnectionFalse() {
        DiemMonHocDao diemMonHocDao = new DiemMonHocDao("QLD_PTIT", "sa", "12");
        boolean expected = false;
        boolean result = diemMonHocDao.connection();
        assertEquals(expected, result);
    }
    
    @Test
    public void testGetDiemMonHocFalse() {
        DiemMonHocDao diemMonHocDao = new DiemMonHocDao("QLD_PTIT", "sa", "1");
        SinhVien sinhVien = new SinhVien("B15DCCN582");
        MonHoc monHoc = new MonHoc(1);
        DiemMonHoc result = diemMonHocDao.getDiemMonHoc(sinhVien, monHoc);
        DiemMonHoc expected = new DiemMonHoc();
        expected.setDiemBT(4);
        expected.setDiemCC(9);
        expected.setDiemCK(9);
        expected.setDiemGK(9);
        expected.setId(1);
        expected.setMonHocID(monHoc);
        expected.setSinhVienMaSV(sinhVien);
        assertNotEquals(expected, result);
    }
    @Test
    public void testGetDiemMonHocTrue() {
        DiemMonHocDao diemMonHocDao = new DiemMonHocDao("QLD_PTIT", "sa", "1");
        SinhVien sinhVien = new SinhVien("B15DCCN582");
        MonHoc monHoc = new MonHoc(1);
        DiemMonHoc result = diemMonHocDao.getDiemMonHoc(sinhVien, monHoc);
        DiemMonHoc expected = new DiemMonHoc();
        expected.setDiemBT(9);
        expected.setDiemCC(9);
        expected.setDiemCK(9);
        expected.setDiemGK(9);
        expected.setId(1);
        expected.setMonHocID(monHoc);
        expected.setSinhVienMaSV(sinhVien);
        assertEquals(expected, result);
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
}
