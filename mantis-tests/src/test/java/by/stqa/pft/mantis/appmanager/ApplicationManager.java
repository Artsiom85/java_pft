package by.stqa.pft.mantis.appmanager;


import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Artsiom on 3/8/2017.
 */
public class ApplicationManager {

  private final Properties properties;
  private WebDriver wd;
  private String browser;
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private DbHelper dbHelper;
  private SoapHelper soapHelper;

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
     dbHelper = new DbHelper();
    }

  public void stop() {
    wd.quit();
  }

  public HttpSession newSession(){
    return new HttpSession(this);
  }

  public String getProperty(String key){
    return properties.getProperty(key);
  }
  public RegistrationHelper registration() {
    if (registrationHelper == null ){
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }
  public FtpHelper ftp() {
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }
  public WebDriver getDriver() {
    if(wd==null){
      if (Objects.equals(browser, BrowserType.FIREFOX)){
        wd=new FirefoxDriver();
      } else if (Objects.equals(browser, BrowserType.CHROME)){
        wd=new ChromeDriver();
      }else if (Objects.equals(browser, BrowserType.IE)){
        wd=new InternetExplorerDriver();
      }
      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }
  public MailHelper mail(){
    if(mailHelper == null) {
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }
  public DbHelper db(){
    return dbHelper;
  }

  public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator().getMantisConnectPort(
            new URL(properties.getProperty("http://localhost/mantisbt-1.3.0/api/soap/mantisconnect.php")));
  }

  public SoapHelper soap() {
    if (soapHelper == null) {
      soapHelper = new SoapHelper(this);
    }
    return soapHelper;
  }
}
