package com.shaternik.model;


import com.shaternik.DAO.DaoImpl;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.sql.Timestamp;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeTest {
    DaoImpl<Employee> employeeDao;


    @Before
    public void setUp() throws Exception {
        employeeDao = new DaoImpl<>(Employee.class);
    }

    @Test
    public void step1_createNewEmployee(){
        System.out.println("======add test =====");
        Employee employee = new Employee("Test_name", "Test_surname", 55);
        employeeDao.saveOrUpdate(employee);

        assertTrue(employee.getId()>0);
        System.out.println("======end test 1=====");

    }

    @Test
    public void step2_findEmployee(){
        System.out.println("======find test 2=====");
        Employee employee = employeeDao.find(1L);
        assertNotNull(employee);
        System.out.println(employee.getName());
        System.out.println("======test 2 end=====");
    }

    @Test
    public void step3_updateEmployee(){
        System.out.println("====== update test 3=====");
        Employee employee = employeeDao.find(1L);
        employee.setName("newName");
        employee.setSurname("newSurName");
        employee.setAge(666);

        employeeDao.saveOrUpdate(employee);

        Employee employeeNew = employeeDao.find(1L);
        assertEquals(employee,employeeNew);
        System.out.println("======test 3 end=====");
    }


    @Test
    public void step4_deleteUser(){
        System.out.println("====== dao test 4=====");

        Employee employee = employeeDao.find(5L);
        employeeDao.delete(employee.getId());
        Employee employee2 = employeeDao.find(5L);

        assertNull(employee2);

        System.out.println("====== dao test 4 end=====");
    }


    @After
    public void tearDown() throws Exception {
        employeeDao = null;
    }
}
