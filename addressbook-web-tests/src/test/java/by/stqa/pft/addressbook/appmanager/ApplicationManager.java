package by.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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

  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private DbHelper dbHelper;

  public ApplicationManager (String browser)  {
    this.browser = browser;
    properties = new Properties();
  }

   public void init() throws IOException {
     String target = System.getProperty("target","local");
     properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

     dbHelper = new DbHelper();

     //запуск selenium.server
     if ("".equals(properties.getProperty("selenium.server"))) {
       if (Objects.equals(browser, BrowserType.FIREFOX)) {
       FirefoxBinary bin = new FirefoxBinary(new File("c:\\Program Files\\Mozilla Firefox\\firefox.exe"));
       wd = new FirefoxDriver(bin, new FirefoxProfile());
     } else if (Objects.equals(browser, BrowserType.CHROME)) {
         ChromeOptions options = new ChromeOptions();               // открытие
         options.addArguments("start-maximized");       //  браузера Chrome
         wd = new ChromeDriver(options);                            // на весь экран
     } else if (Objects.equals(browser, BrowserType.IE)){
       wd = new InternetExplorerDriver();
     }else if (Objects.equals(browser, BrowserType.EDGE)) {
         wd = new EdgeDriver();
       }
     } else {
       DesiredCapabilities capabilities = new DesiredCapabilities();
       capabilities.setBrowserName(browser);
       wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
     }
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //время ожидания браузера менять здесь!
    wd.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public DbHelper db() {
    return dbHelper;
  }
}
