package by.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void ContactDeletionTests() {
        selectContact();
        deleteSelectedContacts();
        app.getGroupHelper().wd.switchTo().alert().accept();
        returnToHomePage();
    }

    private void returnToHomePage() {
        app.getGroupHelper().wd.findElement(By.linkText("home")).click();
    }

    private void deleteSelectedContacts() {
        app.getGroupHelper().wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
    }

    private void selectContact() {
      app.getGroupHelper().selectGroup();
    }
}