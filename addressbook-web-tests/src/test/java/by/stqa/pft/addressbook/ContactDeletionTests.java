package by.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void ContactDeletionTests() {
        selectContact();
        deleteSelectedContacts();
        returnToHomePage();
    }

    private void returnToHomePage() {
        wd.findElement(By.linkText("home")).click();
    }

    private void deleteSelectedContacts() {
        wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
    }

    private void selectContact() {
        wd.findElement(By.name("selected[]")).click();
    }
}