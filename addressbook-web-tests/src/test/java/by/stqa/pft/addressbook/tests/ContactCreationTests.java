package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test (enabled = false)
  public void testContactCreation() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Bob").withLastname("Marley").withAddress("Jamaica").withHomephone("911").withEmail("bobmarley@gmail.com").withGroup("test07");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}


