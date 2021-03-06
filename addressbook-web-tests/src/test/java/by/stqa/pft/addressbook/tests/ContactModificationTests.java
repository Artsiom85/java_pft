package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Artsiom on 3/14/2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if(app.db().contacts().size()==0){
      app.contact().create(new ContactData().withFirstname("Bob").withLastname("Marley").withAddress("Jamaica").withHomephone("911").withMobilephone("+74563912")
              .withWorkphone("(123)6758394").withEmail("bobmarley@gmail.com").withEmail2("Bob@mail.ru").withEmail3("Bob@tut.by").withGroup("test07"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {
    //app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Loui").withLastname("Armstrong").withAddress("USA")
            .withHomephone("7654321").withMobilephone("+745639127").withWorkphone("(123)6758394")
            .withEmail("Armstrong@gmail.com").withEmail2("Lou@mail.ru").withEmail3("UsA@tut.by").withGroup("[none]");
    app.contact().modify(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    System.out.println(before.without(modifiedContact).withAdded(contact));
    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
