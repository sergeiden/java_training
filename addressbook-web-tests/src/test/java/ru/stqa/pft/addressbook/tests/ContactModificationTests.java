package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by 1 on 24.03.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      ContactData contact = new ContactData().withName("Ivan").withLname("Ivanov")
              .withPhoto(new File("src/test/resources/Test.jpg")).withAddress("Lenina, 20-45")
              .withHomePhone("926-525-25-25").withEmail("test@test.ru");
      app.contact().create(contact);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("Ivan").withLname("Petrov").withPhoto(new File("src/test/resources/Test.jpg")).withAddress("Lenina, 20-45").withHomePhone("225-78-98").withEmail("newmail@mail.ru");
    app.contact().modify(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}

