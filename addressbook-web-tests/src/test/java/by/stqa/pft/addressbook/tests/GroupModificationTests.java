package by.stqa.pft.addressbook.tests;

import by.stqa.pft.addressbook.model.GroupData;
import org.testng.annotations.Test;

/**
 * Created by Artsiom on 3/13/2017.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test07", null, "test09"));
    }
  }
}
