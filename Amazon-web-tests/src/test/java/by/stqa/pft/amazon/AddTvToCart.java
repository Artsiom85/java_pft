package by.stqa.pft.amazon;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.io.File;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class AddTvToCart {
  FirefoxDriver wd;

  @BeforeMethod
  public void setUp() throws Exception {
    FirefoxBinary bin = new FirefoxBinary(new File("c:\\Program Files\\Mozilla Firefox\\firefox.exe"));
    wd = new FirefoxDriver(bin, new FirefoxProfile());
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get("https://www.amazon.com/");
    login("korolev.artem87@gmail.com", "Amazon2017");
  }

  private void login(String username, String password) {
    wd.findElement(By.xpath("//a[@id='nav-link-accountList']//span[.='Hello. Sign in']")).click();
    wd.findElement(By.id("ap_email")).click();
    wd.findElement(By.id("ap_email")).clear();
    wd.findElement(By.id("ap_email")).sendKeys(username);
    wd.findElement(By.xpath("//input[@class='a-button-input']")).click();
    wd.findElement(By.id("ap_password")).click();
    wd.findElement(By.id("ap_password")).sendKeys("\\undefined");
    wd.findElement(By.id("ap_password")).click();
    wd.findElement(By.id("ap_password")).clear();
    wd.findElement(By.id("ap_password")).sendKeys(password);
    wd.findElement(By.id("signInSubmit")).click();
  }

  @Test
  public void testAddTvToCart() {
    wd.findElement(By.id("twotabsearchtextbox")).click();
    wd.findElement(By.id("twotabsearchtextbox")).clear();
    wd.findElement(By.id("twotabsearchtextbox")).sendKeys("smart tv");
    wd.findElement(By.cssSelector("input.nav-input")).click();
    wd.findElement(By.xpath("//li[@id='result_3']/div/div[2]/div/div[1]/div/div/a/img")).click();
    wd.findElement(By.id("add-to-cart-button")).click();
    wd.findElement(By.xpath("//div[@id='upsell_0_0']/div/a/img")).click();
    wd.findElement(By.id("add-to-cart-button")).click();
    wd.findElement(By.linkText("Cart3")).click();
    wd.findElement(By.name("submit.delete.C3S57V85AZUJQ3")).click();
    wd.findElement(By.linkText("Not Arty? Sign Out")).click();
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
