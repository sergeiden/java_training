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
    if (app.contact().list().size() == 0) {
      ContactData contact = new ContactData().withName("Ivan").withLname("Ivanov").withHomePhone("11-22-90")
              .withMobilePhone("3333-55-66").withWorkPhone("22 22 00").withAddress("Moscow, Lenina, 20-45").withEmail("test@test.ru").withEmail2("test2@bk.ru").withEmail3("test3@bk.ru");
      app.contact().create(contact);
    }
  }

  @Test
  public void testContactDetails() {
    app.goTo().homePage();
    ContactData contact = app.contact().allInfo().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    ContactData contactDetails = app.contact().infoFromDetailsPage(contact);
    assertThat(contactDetails.getDetails().replaceAll("H: ", "").replaceAll("M: ", "").replaceAll("W: ", "").replaceAll("\n", "")
            , equalTo(mergeInfoFromEditForm(contactInfoFromEditForm).replaceAll("\n", "")));
  }

  private String mergeInfoFromEditForm(ContactData contact) {
    return Arrays.asList(contact.getName() + " " + contact.getLname(), contact.getAddress()
            , contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()
            , contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream()
            .collect(Collectors.joining());
  }
}
