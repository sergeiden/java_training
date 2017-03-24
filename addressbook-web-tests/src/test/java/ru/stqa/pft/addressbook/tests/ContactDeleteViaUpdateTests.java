package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by 1 on 24.03.2017.
 */
public class ContactDeleteViaUpdateTests extends TestBase {

  @Test
  public void testContactDeleteViaUpdate() {
    app.getContactHelper().gotoContactForm();
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "Lenina, 20-45", "926-525-25-25", "testmail@mail.ru"));
    app.getContactHelper().submitContactForm();
    app.getContactHelper().returnToContactsPage();
    app.getContactHelper().modifyContact();
    app.getContactHelper().deleteUpdateContact();
  }
}

