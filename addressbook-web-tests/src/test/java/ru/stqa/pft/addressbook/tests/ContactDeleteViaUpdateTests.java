package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by 1 on 24.03.2017.
 */
public class ContactDeleteViaUpdateTests extends TestBase {

  @Test
  public void testContactDeleteViaUpdate() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().modifyContact();
    app.getContactHelper().deleteUpdateContact();
  }
}

