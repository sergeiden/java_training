package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by 1 on 23.03.2017.
 */
public class GroupMultipleDeletionTests extends TestBase {

  @Test
  public void GroupMultipleDeletionTests() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectSeveralGroups();
    app.getGroupHelper().deleteSelectedGroups();
  }
}

