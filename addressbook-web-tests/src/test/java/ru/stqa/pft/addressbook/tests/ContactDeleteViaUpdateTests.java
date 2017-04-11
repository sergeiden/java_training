package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

/**
 * Created by 1 on 24.03.2017.
 */
public class ContactDeleteViaUpdateTests extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withName("Ivan").withLname("Ivanov").withAddress("Lenina, 20-45").withPhone("926-525-25-25").withEmail("test@test.ru"));
    }
  }

  @Test
  public void testContactDeleteViaUpdate() {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().modifyContactById(deletedContact.getId());
    app.contact().deleteUpdateContact();
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size()-1);

//    before.remove(index);
//    Assert.assertEquals(before, after);
  }
}

