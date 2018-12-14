package com.shaternik.model;

import com.shaternik.DAO.DaoImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarTest {
    DaoImpl<Car> carDao;

    @Before
    public void setUp() throws Exception {
        carDao = new DaoImpl<>(Car.class);
    }

    @Test
    public void step1_createNewCar(){
        System.out.println("======add test =====");
        Car mycar = new Car();
        mycar.setSpeed(300);
        mycar.setWeight(2500);
        mycar.setColor("Red");

        carDao.saveOrUpdate(mycar);

       mycar.setColor("Black");
       carDao.saveOrUpdate(mycar);

        assertTrue(mycar.getId()>0);
        System.out.println("======end test 1=====");

    }

    @Test
    public void step2_findCar(){
        System.out.println("======find test 2=====");
        Car mycar = carDao.find(4L);
        assertNotNull(mycar);
       System.out.println(mycar.getColor());
        System.out.println("======test 2 end=====");
    }



    @After
    public void tearDown() throws Exception {
        carDao = null;
    }
}
