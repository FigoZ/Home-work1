package com.shaternik;

import com.shaternik.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OrmDemo {
    public static void main(String[] args) {
       try(
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();) {
           session.beginTransaction();
          Employee pers = new Employee("Pers1", "surPers11", 32);
       Employee pers2 = new Employee("Pers2", "surPers22", 57);
           session.save(pers);
           if(session.isDirty()){
               //session.flush();
               System.out.println("SESSION DIRTY -----1");
           }else {System.out.println("SESSION CLeAN----1");}
           Employee findPers = (Employee) session.get(Employee.class, 1L);
           findPers.setName("Vasiliy");
           findPers.setSurname("Lomonosov");
           if(session.isDirty()){
               System.out.println("SESSION DIRTY ------2");
            //   session.flush();
           }else {System.out.println("SESSION CLeAN----2");}

           session.save(findPers);
           session.save(pers2);
           session.getTransaction().commit();
            session.close();
       }
    }
}
