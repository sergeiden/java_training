package ru.stga.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stga.pft.mantis.model.UserData;
import ru.stga.pft.mantis.model.Users;

import java.util.List;

/**
 * Created by serg on 21.04.2017.
 */
public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper(){
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Users users(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery( "from UserData where username != 'administrator'" ).list();
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }
}


