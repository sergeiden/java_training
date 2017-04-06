package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by 1 on 24.03.2017.
 */
public class ContactDeleteViaUpdateTests extends TestBase {

  @Test
  public void testContactDeleteViaUpdate() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "Lenina, 20-45", "926-525-25-25", "test@test.ru"));
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().modifyContact();
    app.getContactHelper().deleteUpdateContact();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }
}

