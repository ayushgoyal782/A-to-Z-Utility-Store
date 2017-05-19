/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.domain;

import java.util.GregorianCalendar;
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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *Test invoice for various validation.
 * @author CG-DTE
 */
public class InvoiceTest {
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
       //insert a data in table
        em = emf.createEntityManager();
        tx = em.getTransaction();
        Invoice i1=new Invoice(new GregorianCalendar(2015,3,1).getTime(), "amw",990);
       
     
        tx.begin();
        em.persist(i1);
        tx.commit();
        }
    
    @After
    public void afterEachTestMethod(){
     // reading the data from table
        Invoice i1 = 
                em.createNamedQuery("Invoice.findByName", Invoice.class)
                        .setParameter("name", "amw")
                        .getSingleResult();
        
        tx.begin();
        em.remove(i1);  //deleting the data from table.
        assertEquals(0l,  em.createNamedQuery("Invoice.findByName", Invoice.class)
                        .setParameter("name", "amw").getResultList().size());// testing for removal of data .
        
        tx.commit();            
        em.close();
    }
    
    @Test
    public void validateRelationshipwithCustomer(){
         Customer u1=new Customer("Ayush","Goyal","a@dff.in",9897898798l,"hkjhghfcgvhbkb");
         Invoice i5=new Invoice(new GregorianCalendar(2014,3,1).getTime(), "Test",10298);
         i5.createdbyCustomer(u1);
         tx.begin();
         em.persist(i5);
         em.persist(u1);
         tx.commit();
         
         Invoice findinvoice = em.find(Invoice.class, i5.getSno());
         System.out.println("********"+findinvoice.getCustomer());
         assertTrue(findinvoice.getCustomer().getId().equals(u1.getId()));
        
    }
    
    @Test
    public void validateRelationshipwithInvoiceProducts(){
        Customer u1=new Customer("pinjkh","Gjjl","aytt@dfkf.in",90007898798l,"kjhkjhkjhkb");
        Invoice i5=new Invoice(new GregorianCalendar(2014,3,1).getTime(), "Test",10298);
        Invoiceproducts ip1= new Invoiceproducts(3, 400, 120, 1320);
        Productdetails p1= new Productdetails("Wheel", 100,200F,"http");
        
        ip1.addProductdetails(p1);
        i5.addInvoiceproducts(ip1);        
        i5.createdbyCustomer(u1);
        tx.begin();
        em.persist(p1);
        em.persist(ip1);
        em.persist(u1);
        em.persist(i5);         
        tx.commit();
         
        Invoice findinvoice = em.find(Invoice.class, i5.getSno());
        System.out.println("****INVOICE****"+findinvoice.getCustomer());        
        System.out.println("****INVOICE PRODUCTS****"+findinvoice.getInvoiceproducts());
        System.out.println("****INVOICE PRODUCT DETAILS****"+findinvoice.getInvoiceproducts().get(0).getProductdetails());
        assertTrue(findinvoice.getInvoiceproducts().size()==1);
        assertTrue(findinvoice.getCustomer().getId().equals(2l));
        assertTrue(findinvoice.getInvoiceproducts().get(0).getProductdetails().getProductid().equals(1013l));
       

  }
    
    @Test
    public void validatePastDateSunnyDay(){
     Invoice i1=new Invoice(new GregorianCalendar(2015,3,1).getTime(), "amw",990);
     Set<ConstraintViolation<Invoice>> violations =  validator.validate(i1);
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void validatePastDateRainyDay(){
        Invoice i1=new Invoice(new GregorianCalendar(2020,3,1).getTime(), "awm", 990);
        Set<ConstraintViolation<Invoice>> violations =  validator.validate(i1);
        
        for(ConstraintViolation<Invoice> violation : violations){
            LOG.info(violation.toString());
        }
        assertFalse(violations.isEmpty());
        assertTrue(violations.size()==1);
        assertEquals(violations.size(),1);
    }
   
    @Test
    public void validateNotNullClassPartyNameTest(){
        Invoice i1=new Invoice(new GregorianCalendar(2016,3,1).getTime(), null, 990);
        Set<ConstraintViolation<Invoice>> violations =  validator.validate(i1);
        
        for(ConstraintViolation<Invoice> violation : violations){
            LOG.info(violation.toString());
        }
        assertFalse(violations.isEmpty());
        assertTrue(violations.size()==1);
        assertEquals(violations.size(),1);
    }
    
    @Test
    public void persistNewInvoiceTest(){
        Invoice i2=new Invoice(new GregorianCalendar(2013,5,12).getTime(), "tan", 1760);
        tx.begin();
        assertNull("Sno should be null before persist and commit", i2.getSno());
        em.persist(i2);
        assertNull("Sno should be null after persist but before commit", i2.getSno());
        tx.commit();
        assertNotNull("Sno should not be null after persist and commit", i2.getSno());
        assertTrue("Sno should be greater than 0 after commit", i2.getSno() > 0);
            
         Invoice i3 = em.find(Invoice.class, 2l);
        System.out.println("Invoice Sno "+i3.getSno()+" Party Name "+i3.getpartyname());
        assertEquals(i3.getpartyname(), "tan");
        //Update Query to set new party name 
        tx.begin();
       i3 = em.find(Invoice.class, 2l);
       i3.setpartyname("Texas Association");
       tx.commit();
       System.out.println("Invoice Sno "+i3.getSno()+" Invoice updated Party name "+i3.getpartyname());
    
    }
        
}
