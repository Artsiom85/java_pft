package by.stqa.pft.addressbook.appmanager;

import by.stqa.pft.addressbook.model.ContactData;
import by.stqa.pft.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Artsiom on 3/14/2017.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactFormForDetails(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    //attach(By.name("photo"), contactData.getPhoto());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("work"), contactData.getWorkphone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }


  public void createContactForDetails(ContactData contact) {
    click(By.linkText("add new"));
    fillContactFormForDetails(contact);
    returnToHomePage();
    contactCache = null;
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    attach(By.name("photo"), contactData.getPhoto());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("email"), contactData.getEmail());
    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() ==  1);
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());}
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void initNewContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void create(ContactData contact) {
    initNewContactCreation();
    fillContactForm(contact, true);
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    closeWindow();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModification();
    fillContactFormForDetails(contact);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void closeWindow() {
    wd.switchTo().alert().accept();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String email1 = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomephone(home).withMobilephone(mobile).withWorkphone(work).withAddress(address)
            .withEmail(email1).withEmail2(email2).withEmail3(email3);
  }

  private void initContactModificationById(int id) {
    // поиск кнопки редактирования для элемента из списка контактов
    /*    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../../"));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();*/

    // вариант поиска по дереву DOM
    //   wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
    // вариант поиска по дереву DOM с использованием подзапроса
    //   wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']/td[8]/a]", id))).click();
    // вариант с xpath с точным определеним локатора
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public String contactDetails() {
    String details = wd.findElement(By.xpath("//*[@id='content']")).getText();
    return details;
  }

  public void viewContactById(int id) {
    wd.findElement(By.cssSelector("a[href*='view.php?id=" + id + "']")).click();
  }

  public void addContactToGroup(int id) {
    wd.findElement(By.cssSelector("select[name='to_group']>option[value='" + id + "']")).click();
    click(By.cssSelector("input[name='add']"));
  }

  public void removeContactFromGroup(int id) {
    wd.findElement(By.cssSelector("select[name='group']>option[value='" + id + "']")).click();
  }

  public void waitAfterClickOk() {
    click(By.linkText("home")); //задержка после удаления контакта для Хрома
  }

}






