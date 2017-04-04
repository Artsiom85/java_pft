package by.stqa.pft.addressbook.generators;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.GroupData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artsiom on 4/3/2017.
 */
public class ContactDatagenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d",description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDatagenerator generator = new ContactDatagenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    save(contacts, new File(file));
  }
  private void save(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for(ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getLastname(), contact.getFirstname(), contact.getAddress(),
              contact.getHomephone(),contact.getMobilephone(),contact.getWorkphone(),contact.getEmail1(),contact.getEmail2(),contact.getEmail3()));
    }
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData()
              .withLastname(String.format("LastName %s", i))
              .withFirstname(String.format("FirstName %s", i))
              .withAddress(String.format("Belarus=>UsA %s", i))
              .withHomephone(String.format("(720)234-7865 %s", i))
              .withMobilephone(String.format("+16507778888 %s", i))
              .withWorkphone(String.format("911 %s", i))
              .withEmail1(String.format("email1@tut.com %s", i))
              .withEmail2(String.format("email2@gmail.com %s", i))
              .withEmail3(String.format("email3@yahoo.com %s", i)));
    }
    return contacts;
  }
}
