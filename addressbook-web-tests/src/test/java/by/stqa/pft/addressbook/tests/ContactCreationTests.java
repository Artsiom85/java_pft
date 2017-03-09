package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.Test;

import org.openqa.selenium.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    initNewContactCreation();
    fillContactForm(new ContactData("Bob", "Marley", "Rastaman", "Jamaica", "911", "bobmarley@gmail.com"));
    returnToHomePage();
  }

  private void returnToHomePage() {
    app.getGroupHelper().wd.findElement(By.linkText("home page")).click();
  }

  private void fillContactForm(ContactData contactData) {
    app.getGroupHelper().wd.findElement(By.name("firstname")).click();
    app.getGroupHelper().wd.findElement(By.name("firstname")).clear();
    app.getGroupHelper().wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
    app.getGroupHelper().wd.findElement(By.name("lastname")).click();
    app.getGroupHelper().wd.findElement(By.name("lastname")).clear();
    app.getGroupHelper().wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
    app.getGroupHelper().wd.findElement(By.name("nickname")).click();
    app.getGroupHelper().wd.findElement(By.name("nickname")).clear();
    app.getGroupHelper().wd.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
    app.getGroupHelper().wd.findElement(By.name("address")).click();
    app.getGroupHelper().wd.findElement(By.name("address")).clear();
    app.getGroupHelper().wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
    app.getGroupHelper().wd.findElement(By.name("home")).click();
    app.getGroupHelper().wd.findElement(By.name("home")).clear();
    app.getGroupHelper().wd.findElement(By.name("home")).sendKeys(contactData.getHomephone());
    app.getGroupHelper().wd.findElement(By.name("email")).click();
    app.getGroupHelper().wd.findElement(By.name("email")).clear();
    app.getGroupHelper().wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    app.getGroupHelper().wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  private void initNewContactCreation() {
    app.getGroupHelper().wd.findElement(By.linkText("add new")).click();
  }

}
