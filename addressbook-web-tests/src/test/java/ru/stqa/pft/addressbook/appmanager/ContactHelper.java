package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

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

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void acceptAlert() {
    wd.switchTo().alert().accept();
  }

  public void selectAllContacts() {
    click(By.id("MassCB"));
  }

  public void modifyContact(int index) {
    wd.findElements(By.xpath(".//td[8]")).get(index).click();
  }

  public void modifyContact(int index, ContactData contact) {
    modifyContact(index);
    fillContactForm(contact);
    updateContact();
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public void deleteUpdateContact() {
    click(By.xpath("//div/div[4]/form[2]/input[2]"));
  }

  public void createContact(ContactData contact) {
    gotoContactForm();
    fillContactForm(contact);
    submitContactForm();
    returnToContactsPage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String lname = element.findElement(By.xpath(".//td[2]")).getText();
      String name = element.findElement(By.xpath(".//td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      ContactData contact = new ContactData(id, name, lname, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}


