package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;

/**
 * Created by Artsiom on 3/14/2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().selectContact();
    app.getContactHelper().fillContactForm(new ContactData("Bob", "Marley", "Rastaman", "Jamaica", "911", "bobmarley@gmail.com"));
    app.getGroupHelper().initContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
