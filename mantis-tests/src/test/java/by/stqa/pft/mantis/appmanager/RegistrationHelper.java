package by.stqa.pft.mantis.appmanager;

import by.stqa.pft.mantis.model.MailMessage;
import by.stqa.pft.mantis.model.UserData;
import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Artsiom on 4/13/2017.
 */
public class RegistrationHelper extends HelperBase{


  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Зарегистрироваться']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("span[class='bigger-110']"));
  }

  public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  public void createNewUser() throws IOException, MessagingException {
    Long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost.localdomain", now);
    String user = "user" + now;
    String password = "password";
    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String confirmationLink = app.registration().findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }

  public void enterUsersAdministrator() {
    wd.manage().window().maximize();
    wd.get(app.getProperty("web.baseUrl"));
    type(By.name("username"), "administrator");
    type(By.name("password"), "root");
    click(By.cssSelector("input[value='Login']"));
  }

  public void clickResetPass() {
    wd.get(app.getProperty("web.baseUrl") + "/manage_overview_page.php");
    click(By.linkText("Manage Users"));
    HashSet<UserData> before = app.db().users();
    UserData selectedUser = before.stream().filter(user -> !user.getName().equals("administrator")).findFirst().get();
    click(By.linkText(selectedUser.getName()));
    click(By.cssSelector("input[value='Reset Password']"));
  }
}
