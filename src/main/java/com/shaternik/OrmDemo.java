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
//           Employee pers = new Employee("Lola", "Mola", 32);
 //        Employee pers2 = new Employee("Gnom", "Fufirs", 57);
//           session.save(pers);
//           session.save(pers2);
           session.beginTransaction();
           System.out.println("=======open transaction========");
           Employee findEmpl = session.get(Employee.class,3L);
           System.out.println("===============" + findEmpl.getName());
           session.delete(findEmpl);
           session.getTransaction().commit();
           System.out.println("=======close transaction========");

       }
    }
}
