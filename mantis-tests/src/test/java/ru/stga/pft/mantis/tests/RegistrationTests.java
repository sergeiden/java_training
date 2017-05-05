package ru.stga.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by serg on 05.05.2017.
 */
public class RegistrationTests extends TestBase{

  @Test
  public void testRegistration(){
    app.registration().start("user1", "user@localhost.com");

  }
}
