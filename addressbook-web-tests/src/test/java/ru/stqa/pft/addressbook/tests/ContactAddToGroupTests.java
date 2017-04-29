package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by serg on 28.04.2017.
 */
public class ContactAddToGroupTests extends TestBase {
  @BeforeMethod
  public void insurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      ContactData contact = new ContactData().withName("Ivan").withLname("Ivanov")
              .withPhoto(new File("src/test/resources/Test.jpg")).withAddress("Lenina, 20-45")
              .withHomePhone("926-525-25-25").withEmail("test@test.ru");
      app.contact().create(contact);
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test1"));
    }
  }

  @Test
  public void testContactAddToGroup() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    ContactData contactToAddToGroup = contacts.iterator().next();
    GroupData groupToAddContact = groups.iterator().next();
    if (contactToAddToGroup.getGroups().contains(groupToAddContact)) {
      GroupData group = new GroupData().withName("TestAddGroup");
      app.goTo().groupPage();
      app.group().create(group);
      groupToAddContact = group;
  }
    Groups before = app.db().contactsInGroup();
    app.goTo().homePage();
    app.contact().addContactToGroup(contactToAddToGroup.getId(), groupToAddContact.getName());
    Groups after = app.db().contactsInGroup();
    assertThat(after.size(), equalTo(before.size()+1));
  }
}