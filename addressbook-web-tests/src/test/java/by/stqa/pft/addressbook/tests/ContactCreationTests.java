package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.initNewContactCreation();
    app.fillContactForm(new ContactData("Bob", "Marley", "Rastaman", "Jamaica", "911", "bobmarley@gmail.com"));
    app.returnToHomePage();
  }

}
