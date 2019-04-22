/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuNghiem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author TruongDao
 */
public class ThuNghiem {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("E:\\university\\damBaoChatLuongPhanMem\\baiLam\\QLD_PTIT\\testcases\\taiKhoanTestCase.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ThuNghiem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ThuNghiem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
