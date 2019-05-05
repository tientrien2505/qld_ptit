package selenium;

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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.Excel;

/**
 *
 * @author TruongDao
 */
@RunWith(Parameterized.class)
public class TestDangNhap {

    private int id;
    private String user;
    private String pass;
    private boolean expectedResult;

    public TestDangNhap(int id, String user, String pass, boolean expectedResult) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.expectedResult = expectedResult;
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

    @Test
    public void testDangNhap() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\university\\damBaoChatLuongPhanMem\\baiLam\\selenium-java-3.141.59\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8084/QLD_PTIT/");
        driver.findElement(By.id("profile")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("username")).sendKeys(this.user);
        driver.findElement(By.id("password")).sendKeys(this.pass);
        driver.findElement(By.id("dangNhap")).click();
        Thread.sleep(2000);
        Object exp = null;
        if (this.expectedResult) {
            WebElement result = driver.findElement(By.id("admin"));
            assertNotEquals(result, exp);
        } else {
//            WebDriverWait wait = new WebDriverWait(driver, 3000);
//            wait.until(ExpectedConditions.alertIsPresent());
//            Alert alert = driver.switchTo().alert();
//            alert.accept();
            WebElement result = driver.findElement(By.id("error"));
            assertNotEquals(result, exp);
        }
        driver.close();
    }
}
