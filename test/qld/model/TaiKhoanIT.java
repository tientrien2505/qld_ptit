/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qld.model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import support.Excel;

/**
 *
 * @author TruongDao
 */
@RunWith(Parameterized.class)
public class TaiKhoanIT {

    private int id;
    private String user;
    private String pass;
    private boolean expectedResult;

    public TaiKhoanIT(int id, String user, String pass, boolean expectedResult) {
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
        list = new Excel(0, 4, prototype).getListObject();
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
