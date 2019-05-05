/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_script;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import qld.control.SinhVienDao;
import qld.model.SinhVien;
import excel.Excel;
import qld.control.SinhVienLHPDao;
import qld.model.LopHocPhan;

/**
 *
 * @author TruongDao
 */
public class ScriptThemSV {

    private static final int sheet = 0;
    private static final int maLop = 11;
    public static void main(String[] args) throws IOException {
        themSV();
        themSinhVienLHP();
    }
    
    public static void themSV() throws IOException{
        ArrayList<SinhVien> listSV = new ArrayList<>();
        ArrayList<ArrayList<Object>> listObject = null;
        ArrayList<Integer> prototype = new ArrayList<>();
        prototype.add(new Integer(Excel.STRING));
        prototype.add(new Integer(Excel.STRING));
        prototype.add(new Integer(Excel.STRING));
        prototype.add(new Integer(Excel.STRING));
        prototype.add(new Integer(Excel.STRING));
        Excel excel = new Excel(sheet, 5, prototype);
        listObject = excel.getListObject();
        for (ArrayList<Object> o : listObject) {
            SinhVien sv = new SinhVien();
            sv.setMaSV((String) o.get(1));
            sv.setHoTen(((String) o.get(2))+" " +((String) o.get(3)));
            sv.setLop((String) o.get(4));
            listSV.add(sv);
        }
        SinhVienDao svd = new SinhVienDao("QLD_PTIT", "sa", "1");
        for (SinhVien sv : listSV){
            svd.themSinhVien(sv);
        }
    }
    public static void themSinhVienLHP() throws IOException{
        ArrayList<SinhVien> listSV = new ArrayList<>();
        ArrayList<ArrayList<Object>> listObject = null;
        ArrayList<Integer> prototype = new ArrayList<>();
        prototype.add(new Integer(Excel.STRING));
        prototype.add(new Integer(Excel.STRING));
        prototype.add(new Integer(Excel.STRING));
        prototype.add(new Integer(Excel.STRING));
        prototype.add(new Integer(Excel.STRING));
        Excel excel = new Excel(sheet, 5, prototype);
        listObject = excel.getListObject();
        for (ArrayList<Object> o : listObject) {
            SinhVien sv = new SinhVien();
            sv.setMaSV((String) o.get(1));
            sv.setHoTen(((String) o.get(2))+" " +((String) o.get(3)));
            sv.setLop((String) o.get(4));
            listSV.add(sv);
        }
        LopHocPhan lhp = new LopHocPhan(maLop);
        SinhVienLHPDao svlhpdao = new SinhVienLHPDao("QLD_PTIT", "sa", "1");
        for (SinhVien sv : listSV){
            svlhpdao.themSinhVienLHP(sv, lhp);
        }
    }
}
