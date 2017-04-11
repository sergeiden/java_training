package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by serg on 11.04.2017.
 */
public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withName("Ivan").withLname("Ivanov").withAddress("Lenina, 20-45").withHomePhone("926-525-25-25").withEmail("test@test.ru"));
    }
  }

  @Test(enabled = true)
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().allData().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    System.out.println(contactInfoFromEditForm);

    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
  }
  public String cleaned (String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
