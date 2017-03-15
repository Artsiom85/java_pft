package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.appmanager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import sun.plugin2.util.BrowserType;

/**
 * Created by Artsiom on 3/8/2017.
 */
public class TestBase {

  protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);;


  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod
  public void tearDown() {
    app.stop();
  }
}
