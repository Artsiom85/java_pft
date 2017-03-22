package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;

/**
 * Created by Artsiom on 3/14/2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Bob", "Marley", null, "Jamaica", "911", "bobmarley@gmail.com", "test07"));

    }
  }
}
