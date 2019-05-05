/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.qld.control;

import java.io.IOException;
import java.util.ArrayList;
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
public class TestGetDiem {

    private static final int SHEET = 4;
    private static final int COL = 8;
    private float CC;
    private float BT;
    private float GK;
    private float CK;
    private int maMH;
    private String maSV;
    private int ID;
    private boolean expected;

    public TestGetDiem(float CC, float BT, float GK, float CK, int maMH, String maSV, int ID, boolean expected) {
        this.CC = CC;
        this.BT = BT;
        this.GK = GK;
        this.CK = CK;
        this.maMH = maMH;
        this.maSV = maSV;
        this.ID = ID;
        this.expected = expected;
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
    @Parameterized.Parameters(name = "Run {index}: CC={0}, BT = {1}, GK = {2}, CK = {3}, MH = {4}, SV = {5}, ID ={6}, expectedResult = {7}")
    public static ArrayList<Object> data() throws IOException {
        ArrayList<Object> list = new ArrayList<>();
        ArrayList<Integer> prototype = new ArrayList<>();
        prototype.add(new Integer(Excel.FLOAT));
        prototype.add(new Integer(Excel.FLOAT));
        prototype.add(new Integer(Excel.FLOAT));
        prototype.add(new Integer(Excel.FLOAT));
        prototype.add(new Integer(Excel.INT));
        prototype.add(new Integer(Excel.STRING));
        prototype.add(new Integer(Excel.INT));
        prototype.add(new Integer(Excel.BOOLEAN));
        list = new Excel(SHEET, COL, prototype).getListObject();
        return list;
    }

    @Test
    public void testGetDiemMonHoc() {
        DiemMonHocDao diemMonHocDao = new DiemMonHocDao("QLD_PTIT", "sa", "1");
        SinhVien sinhVien = new SinhVien(maSV);
        MonHoc monHoc = new MonHoc(maMH);
        DiemMonHoc result = diemMonHocDao.getDiemMonHoc(sinhVien, monHoc);
        DiemMonHoc dmh = new DiemMonHoc();
        dmh.setDiemBT(BT);
        dmh.setDiemCC(CC);
        dmh.setDiemCK(CK);
        dmh.setDiemGK(GK);
        dmh.setId(ID);
        dmh.setMonHocID(monHoc);
        dmh.setSinhVienMaSV(sinhVien);
        if (expected) {
            assertEquals(dmh, result);
        }
        else{
            assertNotEquals(dmh, result);
        }
    }
}
