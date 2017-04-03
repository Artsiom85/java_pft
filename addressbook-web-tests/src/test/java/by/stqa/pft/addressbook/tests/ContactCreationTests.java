package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Contacts before = app.contact().all();
    File photo = new File ("src/test/resources/stru.png");
    ContactData contact = new ContactData().withFirstname("Bob").withLastname("Marley").withAddress("Jamaica").withHomephone("911").withMobilephone("+74563912")
            .withWorkphone("(123)6758394").withEmail1("bobmarley@gmail.com").withEmail2("Bob@mail.ru").withEmail3("Bob@tut.by").withPhoto(photo).withGroup("[none]");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
  @Test (enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println("Current dir is: " + currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.png");
    System.out.println("File absolute path: " + photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

  @Test (enabled = false)
  public void testBadContactCreation() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Bob'").withLastname("Marley").withAddress("Jamaica").withHomephone("911").withEmail1("bobmarley@gmail.com").withGroup("[none]");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}


