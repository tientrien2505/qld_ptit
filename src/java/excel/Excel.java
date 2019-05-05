/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author TruongDao
 */
public class Excel {
    // define path file
    private final File file = new File(".\\testcases\\DSSV.xlsx");
    // define type of data
    public static final int INT = 1;
    public static final int STRING = 2;
    public static final int BOOLEAN = 3;
    public static final int FLOAT = 4;
    private int sheet;
    private int colNum;
    private ArrayList<Integer> prototype;

    public Excel(int sheet, int colNum, ArrayList<Integer> prototype) {
        this.sheet = sheet;
        this.colNum = colNum;
        this.prototype = prototype;
    }
    
    public ArrayList<ArrayList<Object>> getListObject() throws FileNotFoundException, IOException {
        ArrayList<ArrayList<Object>> listObject = new ArrayList<>();
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(this.sheet);
        Iterator<Row> iterator = sheet.iterator();
        //ignore header and title
        iterator.next();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            Iterator<Cell> cellInterator = row.cellIterator();
            ArrayList<Object> o = new ArrayList<>();
            for (int i = 0; i < this.colNum; i++) {
                Cell cell = cellInterator.next();
                Object object;
                switch (this.prototype.get(i).intValue()) {
                    case Excel.INT:
                        object = new BigDecimal(cell.getNumericCellValue()).intValue();
                        o.add(object);
                        break;
                    case Excel.STRING:
                        String data = new DataFormatter().formatCellValue(cell);
                        object = data;
                        o.add(object);
                        break;
                    case Excel.BOOLEAN:
                        object = cell.getBooleanCellValue();
                        o.add(object);
                        break;
                    case Excel.FLOAT:
                        object = new BigDecimal(cell.getNumericCellValue()).floatValue();
                        o.add(object);
                        break;
                    default:
                    // do nothing
                    }
            }
            listObject.add(o);
        }
        return listObject;
    }
}