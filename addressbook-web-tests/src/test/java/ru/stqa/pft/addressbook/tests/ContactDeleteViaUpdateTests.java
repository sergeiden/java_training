package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by 1 on 24.03.2017.
 */
public class ContactDeleteViaUpdateTests extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData("Ivan", "Ivanov", "Lenina, 20-45", "926-525-25-25", "test@test.ru"));
    }
  }
  @Test
  public void testContactDeleteViaUpdate() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().modifyContact(index);
    app.contact().deleteUpdateContact();
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);
  }
}

