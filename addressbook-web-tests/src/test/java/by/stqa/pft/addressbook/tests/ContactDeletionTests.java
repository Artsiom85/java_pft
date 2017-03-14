package by.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void ContactDeletionTests() {
        selectContact();
        deleteSelectedContacts();
        switchToWindowPopup();
        returnToHomePage();
    }

}