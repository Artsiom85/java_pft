package by.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreationTests() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test07", "test08", "test09"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
