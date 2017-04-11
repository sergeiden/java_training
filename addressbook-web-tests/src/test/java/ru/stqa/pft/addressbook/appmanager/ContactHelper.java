package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 23.03.2017.
 */
public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void returnToContactsPage() {
    wd.findElement(By.linkText("home page")).click();
  }

  public void submitContactForm() {
    wd.findElement(By.name("submit")).click();
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getLname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getPhone());
    type(By.name("email"), contactData.getEmail());
  }

  public void gotoContactForm() {
    click(By.linkText("add new"));
  }

  public void deleteContact() {
    click(By.xpath("//div/div[4]/form[2]/div[2]/input"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
  }

  public void acceptAlert() {
    wd.switchTo().alert().accept();
  }

  public void selectAllContacts() {
    click(By.id("MassCB"));
  }

  public void modifyContactById(int id) {
    wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
  }

  public void modify(ContactData contact) {
    modifyContactById(contact.getId());
    fillContactForm(contact);
    updateContact();
    contactsCache = null;
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    acceptAlert();
    contactsCache = null;
  }

  public void deleteAll() {
    selectAllContacts();
    deleteContact();
    acceptAlert();
    contactsCache = null;
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public void deleteUpdateContact() {
    click(By.xpath("//div/div[4]/form[2]/input[2]"));
    contactsCache = null;
  }

  public void create(ContactData contact) {
    gotoContactForm();
    fillContactForm(contact);
    submitContactForm();
    contactsCache = null;
    returnToContactsPage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String lname = element.findElement(By.xpath(".//td[2]")).getText();
      String name = element.findElement(By.xpath(".//td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contacts.add(new ContactData().withId(id).withName(name).withLname(lname));
    }
    return contacts;
  }

  private Contacts contactsCache = null;

  public Contacts all() {
    if (contactsCache != null){
      return new Contacts(contactsCache);
    }
    contactsCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String lname = element.findElement(By.xpath(".//td[2]")).getText();
      String name = element.findElement(By.xpath(".//td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contactsCache.add(new ContactData().withId(id).withName(name).withLname(lname));
    }
    return new Contacts(contactsCache);
  }
}


