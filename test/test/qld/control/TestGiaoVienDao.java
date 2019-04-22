/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.qld.control;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import qld.control.GiaoVienDao;
import qld.model.GiaoVien;
import qld.model.TaiKhoan;

/**
 *
 * @author TruongDao
 */
public class TestGiaoVienDao {

    public TestGiaoVienDao() {
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
        GiaoVienDao giaoVienDao = new GiaoVienDao("QLD_PTIT", "sa", "1");
        boolean result = giaoVienDao.connection();
        boolean expected = true;
        assertEquals(expected, result);
    }

    @Test
    public void testConnectionFalse() {
        GiaoVienDao giaoVienDao = new GiaoVienDao("QLD_PTIT", "sa", "12");
        boolean result = giaoVienDao.connection();
        boolean expected = false;
        assertEquals(expected, result);
    }

    @Test
    public void testGetGiaoVien() {
        GiaoVien expected = new GiaoVien();
        expected.setMaGV("GV01");
        expected.setDiaChi(null);
        expected.setEmail(null);
        expected.setHoTen("Tester");
        expected.setSdt(null);
        GiaoVienDao giaoVienDao = new GiaoVienDao("QLD_PTIT", "sa", "1");
        TaiKhoan taiKhoan = new TaiKhoan(1);
        GiaoVien result = giaoVienDao.getGiaoVien(taiKhoan);
        assertEquals(expected, result);
    }
}
