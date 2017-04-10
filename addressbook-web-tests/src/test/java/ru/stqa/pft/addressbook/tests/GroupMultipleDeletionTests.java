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
    app.goTo().groupPage();
    while (app.group().list().size() < 2) {
      app.group().create(new GroupData("test1", null, null));
    }
  }

  @Test
  public void GroupMultipleDeletionTests() {
    List<GroupData> before = app.group().list();
    app.group().selectGroup(before.size() - 1);
    app.group().delete(before.size() - 2);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() - 2);
  }
}

