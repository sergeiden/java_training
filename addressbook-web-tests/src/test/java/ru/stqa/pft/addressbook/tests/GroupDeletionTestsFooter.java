package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by 1 on 23.03.2017.
 */
public class GroupDeletionTestsFooter extends TestBase {

  @Test
  public void testGroupDeletionFooter() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroupsFooter();
    app.getGroupHelper().returnToGroupPage();
  }
}

