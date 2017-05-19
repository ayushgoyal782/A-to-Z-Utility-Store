/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.ejb;

import edu.iit.sat.itmd4515.agoyal18.domain.Employee;
import edu.iit.sat.itmd4515.agoyal18.domain.Invoice;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *Employee services manages all CURD operations for Employee
 * @author CG-DTE
 */
@Named
@Stateless
public class EmployeeService {

    @PersistenceContext(unitName = "itmd4515PU")
    private EntityManager em;

    public EmployeeService() {
    }

    public void create(Employee e) {
        em.persist(e);
    }

    public void update(Employee e) {
        em.merge(e);
    }

    public void remove(Employee e) {
        em.remove(em.merge(e));
    }

    public Employee find(Long id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> findAll() {
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }
    
    public List<Invoice> findAllInvoices() {
        return em.createNamedQuery("Invoice.findAll", Invoice.class).getResultList();
    }

    public Employee findByUsername(String username) {
        return em.createNamedQuery("Employee.findByUsername", Employee.class)
                .setParameter("username", username)
                .getSingleResult();
    
}
}
