/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import support.Excel;

/**
 *
 * @author TruongDao
 */
@RunWith(Parameterized.class)
public class TestConnetDatabase {
    private String database;
    private String user;
    private String pass;
    private boolean expected;

    public TestConnetDatabase(String database, String user, String pass, boolean expected) {
        this.database = database;
        this.user = user;
        this.pass = pass;
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

    @Parameterized.Parameters(name = "Run {index}: database_name={0}, user = {1}, pass = {2}, expectedResult = {3}")
    public static ArrayList<Object> data() throws IOException {
        ArrayList<Object> list = new ArrayList<>();
        ArrayList<Integer> prototype = new ArrayList<>();
        prototype.add(new Integer(Excel.STRING));
        prototype.add(new Integer(Excel.STRING));
        prototype.add(new Integer(Excel.STRING));
        prototype.add(new Integer(Excel.BOOLEAN));
        list = new Excel(1, 4, prototype).getListObject();
        return list;
    }
    
    @Test
    public void testConnection(){
        DiemMonHocDao diemMonHocDao = new DiemMonHocDao(database, user, pass);
        boolean result = diemMonHocDao.connection();
        assertEquals(expected, result);
    }
}
