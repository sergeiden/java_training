package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by serg on 11.04.2017.
 */
public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void insurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() != 0) {
      app.contact().deleteAll();
    }
    ContactData contact = new ContactData().withName("Ivan").withLname("Ivanov").withHomePhone("111 55 78")
            .withMobilePhone("22002").withWorkPhone("333-00-11").withAddress("Lenina, 20-45").withEmail("test@test.ru");
    app.contact().create(contact);
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().allInfo().iterator().next();
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
