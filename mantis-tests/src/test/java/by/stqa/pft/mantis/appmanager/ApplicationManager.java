package by.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Artsiom on 3/8/2017.
 */
public class ApplicationManager {

  private final String browser;
  private final Properties properties;
  WebDriver wd;

   public ApplicationManager(String browser)  {
    this.browser = browser;
    properties = new Properties();
  }

   public void init() throws IOException {
     String target = System.getProperty("target","local");
     properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

     if (Objects.equals(browser, BrowserType.FIREFOX)) {
       FirefoxBinary bin = new FirefoxBinary(new File("c:\\Program Files\\Mozilla Firefox\\firefox.exe"));
       wd = new FirefoxDriver(bin, new FirefoxProfile());
     } else if (Objects.equals(browser, BrowserType.CHROME)){
       wd = new ChromeDriver();
     } else if (Objects.equals(browser, BrowserType.IE)){
       wd = new InternetExplorerDriver();
     }
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    }

  public void stop() {
    wd.quit();
  }
}
