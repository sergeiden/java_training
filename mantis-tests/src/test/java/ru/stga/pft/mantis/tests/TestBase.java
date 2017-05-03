package ru.stga.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stga.pft.mantis.appmanager.ApplicationManager;

/**
 * Created by 1 on 21.03.2017.
 */
public class TestBase {

  protected static ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  public ApplicationManager getApp() {
    return app;
  }

}
