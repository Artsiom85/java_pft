package by.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import by.stqa.pft.addressbook.model.GroupData;
import by.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Artsiom on 4/12/2017.
 */
public class  ContactAddToGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
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
      app.goTo().groupPage();
    }
  }

  @Test
  public void testContactAddToGroup(){
    Contacts before = app.db().contacts();
    app.goTo().homePage();
    Groups group = app.db().groups();
    ContactData modifiedContact = before.iterator().next();
    GroupData addedGroup = group.iterator().next();
    app.contact().selectContactById(modifiedContact.getId());
    app.contact().addContactToGroup(addedGroup.getId());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(modifiedContact.inGroup(addedGroup))));
  }
}

