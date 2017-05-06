package ru.stga.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by serg on 06.05.2017.
 */
public class PwdChangeHelper extends HelperBase {
  public PwdChangeHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl"));
    type(By.name("username"), username);
    click(By.cssSelector(".btn-success"));
    type(By.name("password"), password);
    click(By.cssSelector(".btn-success"));
  }

  public void initPasswordReset(String username) {
    wd.get("http://localhost/mantisbt-2.4.0/manage_user_page.php");
    click(By.linkText(username));
    click(By.cssSelector("input[value='Сбросить пароль']"));
  }

  public void changePassword(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type ='submit']"));
  }
}
