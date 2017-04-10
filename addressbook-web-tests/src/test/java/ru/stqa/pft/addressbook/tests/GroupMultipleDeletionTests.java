package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by 1 on 23.03.2017.
 */
public class GroupMultipleDeletionTests extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
    app.getNavigationHelper().gotoGroupPage();
    while (app.getGroupHelper().getGroupCount() < 2) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
  }

  @Test
  public void GroupMultipleDeletionTests() {
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().selectGroup(before.size() - 2);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 2);
  }
}
