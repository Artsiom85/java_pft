package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
    }.getType());
    return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
  }

  @Test (dataProvider = "validContactsFromJson")
  public void testContactCreation (ContactData contact) {
    app.goTo().homePage();
    ContactData innerContact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(innerContact);
    //File photo = new File ("src/test/resources/stru.png");
    app.contact().createContactForDetails(innerContact);
    app.contact().viewContactById(innerContact.getId());
    String contactViewInfo = app.contact().contactDetails();
    assertThat(innerContact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(clean(contactViewInfo), equalTo(mergeAll(contactInfoFromEditForm)));
  }

  @Test(dataProvider = "validContactsFromXml", enabled = false)
  public void testAddNewContact(ContactData contact) {
    //File photo = new File ("src/test/resources/stru.png");
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().createContactForDetails(contact);
    //хэширование - делается быстрая проверка кол-во контактов после создания новой группы
    assertThat(app.contact().count(), equalTo(before.size()+1));
    Contacts after = app.contact().all();
    //вычисляется максимальный идентификатор
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }


  private String mergeOtherInformation(ContactData contact) {
    return Arrays.asList(contact.getAddress(), contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone(),
            contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream().filter(s -> !(s == null || s.equals("")))
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
            .replaceAll("\n\n", "\n");
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
    ContactData contact = new ContactData().withFirstname("Bob'").withLastname("Marley").withAddress("Jamaica").withHomephone("911").withEmail("bobmarley@gmail.com").withGroup("[none]");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}


