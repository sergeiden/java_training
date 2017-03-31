package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by 1 on 24.03.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "Lenina, 20-45", "926-525-25-25", "test@test.ru"));
    }
    app.getContactHelper().modifyContact();
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "Lenina, 20-45", "009", "newmail@mail.ru"));
    app.getContactHelper().updateContact();
  }
}

