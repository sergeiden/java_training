package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by 1 on 23.03.2017.
 */
public class GroupDeletionTestsFooter extends TestBase {

  @Test
  public void testGroupDeletionFooter() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroupsFooter();
    app.getGroupHelper().returnToGroupPage();
  }
}

