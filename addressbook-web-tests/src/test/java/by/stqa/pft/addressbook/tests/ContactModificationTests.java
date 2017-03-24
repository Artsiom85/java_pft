package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Artsiom on 3/14/2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().returnToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Bob", "Marley", null, "Jamaica", "911", "bobmarley@gmail.com", "test07"), false);
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Loui", "Armstrong","blind","USA","7654321","Armstrong@gmail.com","test10"), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);

  }

}
