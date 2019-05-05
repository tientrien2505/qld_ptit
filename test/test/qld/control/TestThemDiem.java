/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.qld.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import qld.control.DiemMonHocDao;
import qld.model.DiemMonHoc;
import qld.model.MonHoc;
import qld.model.SinhVien;
import support.Excel;

/**
 *
 * @author TruongDao
 */
@RunWith(Parameterized.class)
public class TestThemDiem {
    private static final int SHEET = 2;
    private static final int COL = 7;
    private float CC;
    private float BT;
    private float GK;
    private float CK;
    private int maMonHoc;
    private String maSinhVien;
    private boolean expectedOutput;

    public TestThemDiem(float CC, float BT, float GK, float CK, int maMonHoc, String maSinhVien, boolean expectedOutput) {
        this.CC = CC;
        this.BT = BT;
        this.GK = GK;
        this.CK = CK;
        this.maMonHoc = maMonHoc;
        this.maSinhVien = maSinhVien;
        this.expectedOutput = expectedOutput;
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

    @Parameterized.Parameters(name = "Run {index}: CC={0}, BT = {1}, GK = {2}, CK = {3}, MH = {4}, SV = {5}, expectedResult = {6}")
    public static ArrayList<Object> data() throws IOException {
        ArrayList<Object> list = new ArrayList<>();
        ArrayList<Integer> prototype = new ArrayList<>();
        prototype.add(new Integer(Excel.FLOAT));
        prototype.add(new Integer(Excel.FLOAT));
        prototype.add(new Integer(Excel.FLOAT));
        prototype.add(new Integer(Excel.FLOAT));
        prototype.add(new Integer(Excel.INT));
        prototype.add(new Integer(Excel.STRING));
        prototype.add(new Integer(Excel.BOOLEAN));
        list = new Excel(SHEET, COL, prototype).getListObject();
        return list;
    }
    @Test
    public void testThemDiem() {
        DiemMonHocDao diemMonHocDao = new DiemMonHocDao("QLD_PTIT", "sa", "1");
//        DiemMonHoc diemMonHoc = new DiemMonHoc(1, 8, 9, 9, 9);
        DiemMonHoc diemMonHoc = new DiemMonHoc(1, CC, BT, GK, CK);
//        diemMonHoc.setMonHocID(new MonHoc(1));
        diemMonHoc.setMonHocID(new MonHoc(maMonHoc));
//        diemMonHoc.setSinhVienMaSV(new SinhVien("B15DCCN582"));
        diemMonHoc.setSinhVienMaSV(new SinhVien(maSinhVien));
        Connection conn = diemMonHocDao.getConn();
        try {
            conn.setAutoCommit(false);
            Savepoint savepoint = conn.setSavepoint("savepoint");
            boolean result = diemMonHocDao.themDiemMonHoc(diemMonHoc);
            conn.rollback(savepoint);
//            boolean expectedOutput = true;
            assertEquals(expectedOutput, result);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
