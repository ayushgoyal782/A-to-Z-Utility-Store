/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.domain;

import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *Test InvoiceProducts for various validation.
 * @author CG-DTE
 */
public class InvoiceproductsTest {
    private static EntityManagerFactory emf;
    private static Validator validator;
    private static final Logger LOG = Logger.getLogger(InvoiceTest.class.getName());
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
       
        em = emf.createEntityManager();
        tx = em.getTransaction();
           }
    
    @After
    public void afterEachTestMethod(){
      em.close();
    }
    
    @Test
    public void validateOneToOneRelationshipwithProductDetails(){
        Invoiceproducts ip1= new Invoiceproducts(3, 400, 120, 1320);
        Invoiceproducts ip2= new Invoiceproducts(4, 400, 160, 1760);
        Productdetails p1= new Productdetails("Wheel", 100,200F,"http");
        Productdetails p2= new Productdetails("Roller", 23,300F,"http");
        ip1.addProductdetails(p1);
        ip2.addProductdetails(p2);
         tx.begin();
         em.persist(p1);
         em.persist(p2);
         em.persist(ip1);
         em.persist(ip2);
         tx.commit();
         
        Invoiceproducts findinvoiceproducts = em.find(Invoiceproducts.class, ip1.getSno());
        System.out.println("********"+findinvoiceproducts);
        System.out.println("********"+findinvoiceproducts.getProductdetails());
        assertTrue(findinvoiceproducts.getProductdetails().getProductid()>=1);
        
    }
    
    @Test
    public void validateAmount(){
    Invoiceproducts ip1= new Invoiceproducts(3, 400, 120, 0);
    Set<ConstraintViolation<Invoiceproducts>> violations =  validator.validate(ip1);
    assertFalse(violations.isEmpty());
    assertEquals(violations.size(),1);
    } 
       
    @Test
    public void persistNewInvoiceproductsTest(){
        Invoiceproducts ip1= new Invoiceproducts(3, 400, 120, 1320);  
        Productdetails p1= new Productdetails("Flenge", 10,250F,"http");
        ip1.addProductdetails(p1);
        tx.begin();
        assertNull("Sno should be null before persist and commit", ip1.getSno());
        em.persist(p1);
        em.persist(ip1);
        assertNull("Sno should be null after persist but before commit", ip1.getSno());
        tx.commit();
        assertNotNull("Sno should not be null after persist and commit", ip1.getSno());
        assertTrue("Sno should be greater than 0 after commit", ip1.getSno() > 0);
            
        }
    
     
}
