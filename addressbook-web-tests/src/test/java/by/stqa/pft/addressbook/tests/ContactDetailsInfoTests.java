package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Artsiom on 4/2/2017.
 */
public class ContactDetailsInfoTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
       app.contact().create(new ContactData().withFirstname("Bob").withLastname("Marley").withAddress("Jamaica").withHomephone("911").withMobilephone("+74563912")
              .withWorkphone("(123)6758394").withEmail1("bobmarley@gmail.com").withEmail2("Bob@mail.ru").withEmail3("Bob@tut.by").withGroup("test07"));
    }
  }
  @Test
  public void testContactDetailsInfo() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    app.contact().returnToHomePage();
    app.contact().viewContactById(contact.getId());
    String contactViewInfo = app.contact().contactDetails();
    assertThat(clean(contactViewInfo), equalTo(mergeAll(contactInfoFromEditForm)));
  }
  private String mergeOtherInformation(ContactData contact) {
    return Arrays.asList(contact.getAddress(), contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone(),
            contact.getEmail1(), contact.getEmail2(), contact.getEmail3()).stream().filter(s -> !(s == null || s.equals("")))
            .map(ContactDetailsInfoTests::clean).collect(Collectors.joining("\n"));
  }

  private String mergeNames(ContactData contact) {
    return Arrays.asList(contact.getFirstname().replaceAll("\n", ""), contact.getLastname())
            .stream().filter(s -> !(s == null || s.equals("")))
            .map(ContactDetailsInfoTests::clean)
            .collect(Collectors.joining(" "));
  }

  private String mergeAll (ContactData contact) {
    return Arrays.asList(mergeNames(contact), mergeOtherInformation(contact)).stream().collect(Collectors.joining("\n"));
  }

  private static String clean(String contactViewInfo) {
    return contactViewInfo.replaceAll("[HMWP]: ", "").replaceAll("Modify", "")
            .replaceAll("Print", "").replaceAll("\n\n\n\n", "\n")
            .replaceAll("\n\n", "\n")/*.replaceAll("\\s", "").replaceAll("[-()]", "")*/;
  }
}
