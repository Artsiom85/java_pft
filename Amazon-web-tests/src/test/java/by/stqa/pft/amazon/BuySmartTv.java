package by.stqa.pft.amazon;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class BuySmartTv {
    FirefoxDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void BuySmartTv() {
        wd.get("https://www.amazon.com/");
        wd.findElement(By.cssSelector("span.nav-action-inner")).click();
        wd.findElement(By.id("ap_email")).click();
        wd.findElement(By.id("ap_email")).clear();
        wd.findElement(By.id("ap_email")).sendKeys("korolev.artem87@gmail.com");
        wd.findElement(By.xpath("//input[@class='a-button-input']")).click();
        wd.findElement(By.id("ap_password")).click();
        wd.findElement(By.id("ap_password")).clear();
        wd.findElement(By.id("ap_password")).sendKeys("Amazon2017");
        wd.findElement(By.id("signInSubmit")).click();
        wd.findElement(By.id("twotabsearchtextbox")).click();
        wd.findElement(By.id("twotabsearchtextbox")).clear();
        wd.findElement(By.id("twotabsearchtextbox")).sendKeys("smart tv");
        wd.findElement(By.id("issDiv0")).click();
        if (!wd.findElement(By.xpath("//select[@id='sort']//option[3]")).isSelected()) {
            wd.findElement(By.xpath("//select[@id='sort']//option[3]")).click();
        }
        wd.findElement(By.cssSelector("img.s-access-image.cfMarker")).click();
        wd.findElement(By.id("add-to-cart-button")).click();
        wd.findElement(By.id("siNoCoverage-announce")).click();
        wd.findElement(By.id("add-to-cart-button")).click();
        wd.findElement(By.linkText("Cart2")).click();
        wd.findElement(By.name("submit.delete.CC2YWOG8TK7WA35")).click();
        wd.findElement(By.xpath("//div[@id='nav-flyout-accountList']/div[1]/div")).click();
        wd.findElement(By.linkText("Sign Out")).click();
        wd.findElement(By.id("ap_email")).click();
        wd.findElement(By.id("ap_email")).clear();
        wd.findElement(By.id("ap_email")).sendKeys();
    }
    
    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
