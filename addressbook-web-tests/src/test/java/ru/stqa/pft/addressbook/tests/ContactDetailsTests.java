package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by serg on 13.04.2017.
 */
public class ContactDetailsTests extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() != 0) {
      app.contact().deleteAll();
    }
    ContactData contact = new ContactData().withName("Ivan").withLname("Ivanov").withHomePhone("111 55 78")
            .withMobilePhone("22002").withWorkPhone("333-00-11").withAddress("Moscow,\nLenina, 20-45").withEmail("test@test.ru").withEmail2("test2@bk.ru").withEmail3("test3@bk.ru");
    app.contact().create(contact);
  }

  @Test
  public void testContactDetails() {
    app.goTo().homePage();
    ContactData contact = app.contact().allInfo().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    ContactData contactDetails = app.contact().infoFromDetailsPage(contact);
    assertThat(contactDetails.getDetails(), equalTo(mergeInfoFromEditForm(contactInfoFromEditForm)));
  }

  private String mergeInfoFromEditForm(ContactData contact) {
    return Arrays.asList(contact.getName() + " " + contact.getLname(), contact.getAddress() + "\n", "H: " + contact.getHomePhone(), "M: " + contact.getMobilePhone(), "W: " + contact.getWorkPhone() + "\n"
            , contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream()
            .collect(Collectors.joining("\n"));
  }
}
