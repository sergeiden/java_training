package ru.stga.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stga.pft.mantis.model.UserData;
import ru.stga.pft.mantis.model.Users;

import java.sql.*;

/**
 * Created by serg on 06.05.2017.
 */
public class DbConnectionTest {
  @Test
  public void testDbConnection() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=&serverTimezone=UTC");
      Statement st = conn.createStatement();

      ResultSet rs = st.executeQuery("select username from mantis_user_table");
      Users users = new Users();
      while (rs.next()) {
        users.add(new UserData().withName(rs.getString("username")));
      }
      rs.close();
      st.close();
      conn.close();

      System.out.println(users);

    } catch (SQLException ex) {
    }
  }
}
