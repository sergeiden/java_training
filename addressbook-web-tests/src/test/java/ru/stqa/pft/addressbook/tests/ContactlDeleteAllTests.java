package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Collections;

import static org.testng.Assert.assertEquals;

/**
 * Created by 1 on 24.03.2017.
 */
public class ContactlDeleteAllTests extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withName("Ivan").withLname("Ivanov").withAddress("Lenina, 20-45").withHomePhone("926-525-25-25").withEmail("test@test.ru"));
    }
  }

  @Test
  public void testDeleteAllContacts() {
    app.contact().deleteAll();
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertEquals(after, Collections.emptySet());
  }
}

