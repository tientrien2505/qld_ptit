/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author TruongDao
 */
public class testDangNhap {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\university\\damBaoChatLuongPhanMem\\baiLam\\selenium-java-3.141.59\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8084/QLD_PTIT/");
        driver.findElement(By.id("profile")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("username")).sendKeys("tientrien2505");
        driver.findElement(By.id("password")).sendKeys("basmnjnszk465");
        driver.findElement(By.id("dangNhap")).click();
        Thread.sleep(2000);
        if (driver.findElement(By.id("admin")) != null){
            System.out.println("Pass");
        }
        else{
            System.out.println("Fail");
        }
        driver.close();
    }
}
