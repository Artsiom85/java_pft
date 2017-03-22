package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().createContact(new ContactData("Bob", "Marley", null, "Jamaica", "911", "bobmarley@gmail.com", "test07"));
    app.getNavigationHelper().returnToHomePage();
  }

}
