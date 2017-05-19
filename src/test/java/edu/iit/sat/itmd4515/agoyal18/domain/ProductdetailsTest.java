/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.domain;

import java.util.GregorianCalendar;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *Test Productdetail for various validation.
 * @author CG-DTE
 */
public class ProductdetailsTest {
    
    private static EntityManagerFactory emf;
    private static Validator validator;
    private EntityManager em;
    private EntityTransaction tx;
    
    @BeforeClass
    public static void beforeClassTest(){
        emf = Persistence.createEntityManagerFactory("itmd4515PU_TEST");
        validator= Validation.buildDefaultValidatorFactory().getValidator();
    }
    
    @AfterClass
    public static void afterClassTest(){
        emf.close();
    }
    
    @Before
    public void beforeEachTestMethod(){
       //insert a data in table
        em = emf.createEntityManager();
        tx = em.getTransaction();
        Productdetails p1= new Productdetails("Nut", 1246,300F,"http");
        tx.begin();
        em.persist(p1);
        tx.commit();
        }
    
    @After
    public void afterEachTestMethod(){
     // reading the data from table
        Productdetails p1=         
                em.createNamedQuery("Productdetails.findBydesc", Productdetails.class)
                        .setParameter("productdescription", "Nut")
                        .getSingleResult();
        
        tx.begin();
        em.remove(p1);  //deleting the data from table.
        tx.commit();            
        assertEquals(0l,  em.createNamedQuery("Productdetails.findBydesc", Productdetails.class)
                        .setParameter("productdescription", "Nut")
                        .getResultList().size());// testing for removal of data .
        em.close();
    }
    
      @Test
    public void validateNotNullProductDescription(){
        Productdetails p1= new Productdetails(null, 1246, 450F,"http");
        Set<ConstraintViolation<Productdetails>> violations =  validator.validate(p1);
       
        assertFalse(violations.isEmpty());
        assertTrue(violations.size()==1);
        assertEquals(violations.size(),1);
    }  
    @Test
    public void persistNewProductdetailsTest(){
        Productdetails p2= new Productdetails("Bolt", 1560,500F,"http");
        tx.begin();
        em.persist(p2);
        tx.commit();
        assertNotNull("ID should not be null after persist and commit", p2.getProductid());
        assertTrue("ID should be greater than 0 after commit", p2.getProductid() > 0);
        
        Productdetails p3 = em.find(Productdetails.class, 9780l);
        System.out.println("Product Id "+p3.getProductid()+" Productdetails "+p3.getProductdescription());
        assertEquals(p3.getProductdescription(), "Bolt");
        assertNotNull("Product ID should not be null after persist and commit", p3.getProductid());
        assertTrue("User ID should be greater than 0 after commit", p3.getProductid()> 0);
       
       //Update Query to set new Productdescription 
        tx.begin();
       p3 = em.find(Productdetails.class, 9780l);
       p3.setProductdescription("New Bolt");
       tx.commit();
       System.out.println("ProductId "+p3.getProductid()+" Product updated details "+p3.getProductdescription());
    
        
    }
     @Test(expected = RollbackException.class)
    public void persistNewProductdetailsShouldFailRainyDayTest(){
        // tring to enter the data which is already present in table. It will throw exception.
        Productdetails p1= new Productdetails("Nut", 1246,300F,"http");
        tx.begin();
        em.persist(p1);
        tx.commit();
       
    }
}
