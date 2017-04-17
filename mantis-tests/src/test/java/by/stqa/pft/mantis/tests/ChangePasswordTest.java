package by.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import by.stqa.pft.mantis.model.MailMessage;
import by.stqa.pft.mantis.model.UserData;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Artsiom on 4/17/2017.
 */
public class ChangePasswordTest extends TestBase{
  @BeforeMethod
  public void ensurePrecondition() throws IOException, MessagingException {

    app.mail().start();


    if (app.db().users().size() == 0  ){
      app.registration().createNewUser();
    }
  }

  @Test
  public void testChangePasswordOfUser() throws IOException, MessagingException {
    String password = String.format("password" + System.currentTimeMillis());
    HashSet<UserData> before = app.db().users();
    UserData modifiedUser = before.iterator().next();
    app.registration().enterUsersAdministrator();
    app.registration().clickResetPass();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 50000);
    String confirmationLink = app.registration().findConfirmationLink(mailMessages, modifiedUser.getEmail());
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(modifiedUser.getName(), password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }



  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }
}
