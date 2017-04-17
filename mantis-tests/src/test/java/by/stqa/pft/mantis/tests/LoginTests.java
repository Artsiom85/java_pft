package by.stqa.pft.mantis.tests;

import by.stqa.pft.mantis.appmanager.HttpSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by Artsiom on 4/13/2017.
 */
public class LoginTests extends TestBase{

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator", "root"));
    Assert.assertTrue(session.isLoggedInAs("administrator"));
    }
}
