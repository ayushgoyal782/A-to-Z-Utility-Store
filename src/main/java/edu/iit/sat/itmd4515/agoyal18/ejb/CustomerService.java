/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.ejb;

import edu.iit.sat.itmd4515.agoyal18.domain.*;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *Customer services manages all CURD operations for Customer
 * @author CG-DTE
 */
@Named
@Stateless
public class CustomerService extends BaseService<Customer> {

    public CustomerService() {
        super(Customer.class);
    }

    @Override
    public List<Customer> findAll() {
        return getEntityManager().createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }

    public Customer findByUsername(String username) {
        return getEntityManager()
                .createNamedQuery("Customer.findByUsername", Customer.class)
                .setParameter("username", username)
                .getSingleResult();

    }

    public List<Invoice> findByCustomername(String username) {

        return getEntityManager()
                .createNamedQuery("Invoice.findByuserName", Invoice.class
                )
                .setParameter("customer", username)
                .getResultList();
    }
}
