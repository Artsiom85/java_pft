package by.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import by.stqa.pft.addressbook.model.GroupData;
import by.stqa.pft.addressbook.model.Groups;
import org.openqa.selenium.By;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Artsiom on 4/12/2017.
 */
public class ContactDeleteFromGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {

    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {

      app.contact().create(new ContactData().withFirstname("Donald").withLastname("Trump").withAddress("Washington")
              .withHomephone("80297027079").withMobilephone("+19737382899").withWorkphone("911").withEmail("donald@gmail.com")
              .withEmail2("trump@yahoo.com").withEmail3("donald_trump@tut.by").inGroup(groups.iterator().next()));
      app.goTo().homePage();
    }
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test3").withHeader("test2").withFooter("test1"));
    }
    for(ContactData contact : contacts) {
      if (contact.getGroups().size() == 0) {
        app.goTo().homePage();
        ContactData modifiedContact = contacts.iterator().next();
        GroupData addedGroup = groups.iterator().next();
        app.contact().selectContactById(modifiedContact.getId());
        app.contact().addContactToGroup(addedGroup.getId());
      }
    }
  }

  @Test
  public void testContactDeleteFromGroup(){

    Contacts before = app.db().contacts();
    app.goTo().homePage();
    ContactData modifiedContact = before.iterator().next();
    GroupData removeGroup = modifiedContact.getGroups().iterator().next();
    app.contact().removeContactFromGroup(removeGroup.getId());
    app.contact().selectContactById(modifiedContact.getId());
    app.contact().click(By.cssSelector("input[name='remove']"));
    Contacts after = app.db().contacts();
    after.remove(modifiedContact);
    ContactData modifiedContact2 = new ContactData();
    for(ContactData contact : before) {
      if (contact.equals(modifiedContact)) {
        contact.getGroups().remove(removeGroup);
        after.add(contact);
      }
    }
    modifiedContact2.getGroups().remove(removeGroup);
    System.out.println(modifiedContact2);
    assertThat(after, equalTo(before));
  }
}


