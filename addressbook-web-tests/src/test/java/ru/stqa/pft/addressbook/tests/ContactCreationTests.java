package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactFromJson() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null){
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validContactFromXml() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null){
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

//  @Test(dataProvider = "validContactFromXml")
//  public void testContactCreation(ContactData contact) {
//    app.goTo().homePage();
//    Contacts before = app.db().contacts();
//    app.contact().create(contact);
//    assertThat(app.contact().count(), equalTo(before.size()+1));
//    Contacts after = app.db().contacts();
//
//    assertThat(after, equalTo(
//            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
//  }

  @Test
  public void testContactCreation() {
    Groups groups = app.db().groups();
    ContactData contact = new ContactData().withName("Ivan").withLname("Ivanov")
            .withPhoto(new File("src/test/resources/Test.jpg")).withAddress("Lenina, 20-45")
            .withHomePhone("926-525-25-25").withEmail("test@test.ru").inGroup(groups.iterator().next());
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size()+1));
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
