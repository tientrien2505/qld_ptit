/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.qld.model;

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
import qld.model.TaiKhoan;
import support.Excel;

/**
 *
 * @author TruongDao
 */
@RunWith(Parameterized.class)
public class TestTaiKhoan {
    private static final int SHEET = 0;
    private int id;
    private String user;
    private String pass;
    private boolean expectedResult;

    public TestTaiKhoan(int id, String user, String pass, boolean expectedResult) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters(name = "Run {index}: id={0}, user = {1}, pass = {2}, expectedResult = {3}")
    public static ArrayList<Object> data() throws IOException {
        ArrayList<Object> list = new ArrayList<>();
        ArrayList<Integer> prototype = new ArrayList<>();
        prototype.add(new Integer(Excel.INT));
        prototype.add(new Integer(Excel.STRING));
        prototype.add(new Integer(Excel.STRING));
        prototype.add(new Integer(Excel.BOOLEAN));
        list = new Excel(SHEET, 4, prototype).getListObject();
        return list;
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

    /**
     * Test of getId method, of class TaiKhoan.
     *
     * @param a
     */
    @Test
    public void testIsTaiKhoan() {
        System.out.println("isTaiKhoan");
        TaiKhoan taiKhoan = new TaiKhoan(id, user, pass);

        boolean result = taiKhoan.isTaiKhoan();

        assertEquals(expectedResult, result);
    }
}
