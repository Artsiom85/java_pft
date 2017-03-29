package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Artsiom on 3/14/2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Bob").withLastname("Marley").withAddress("Jamaica").withHomephone("911").withEmail("bobmarley@gmail.com").withGroup("test07"));
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("Loui").withLastname("Armstrong").withAddress("USA").withHomephone("7654321").withEmail("Armstrong@gmail.com").withGroup("test10");
    app.contact().initContactModification(before.size() + 1);
    app.contact().fillContactForm(contact, false);
    app.contact().submitContactModification();
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);

  }

}
