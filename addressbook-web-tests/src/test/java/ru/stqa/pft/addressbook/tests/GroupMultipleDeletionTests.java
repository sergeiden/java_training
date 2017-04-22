package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertEquals;

/**
 * Created by 1 on 23.03.2017.
 */
public class GroupMultipleDeletionTests extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
    app.goTo().groupPage();
    while (app.db().groups().size() < 2) {
      app.group().create(new GroupData().withName("Test1"));
    }
  }

  @Test
  public void GroupMultipleDeletionTests() {
    Groups before = app.db().groups();
    app.goTo().groupPage();
    app.group().selectGroup(before.size() - 1);
    app.group().delete(before.size() - 2);
    Groups after = app.db().groups();
    assertEquals(after.size(), before.size() - 2);
  }
}

