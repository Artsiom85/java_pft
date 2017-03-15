package by.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Artsiom on 3/8/2017.
 */
public class ApplicationManager {
    WebDriver wd;

  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;

  public ApplicationManager (String browser){
    this.browser = browser;
  }
   public void init() {
       if (browser == BrowserType.FIREFOX) {
         wd = new FirefoxDriver(bin, new FirefoxProfile());
         FirefoxBinary bin = new FirefoxBinary(new File("c:\\Program Files\\Mozilla Firefox\\firefox.exe"));
     } else if (browser == BrowserType.CHROME){
       wd = new ChromeDriver();
     } else if (browser == BrowserType.IE){
       wd = new InternetExplorerDriver();
     }

    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/addressbook/");
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }


  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
