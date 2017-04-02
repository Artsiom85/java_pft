package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Artsiom on 4/1/2017.
 */

public class ContactEmailTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Bob").withLastname("Marley").withAddress("Jamaica").withHomephone("911").withMobilephone("+74563912")
              .withWorkphone("(123)6758394").withEmail1("bobmarley@gmail.com").withEmail2("Bob@mail.ru").withEmail3("Bob@tut.by").withGroup("test07"));
    }
  }

  @Test
  public void testContactEmails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }
}
