/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.web;

import edu.iit.sat.itmd4515.agoyal18.domain.Customer;
import edu.iit.sat.itmd4515.agoyal18.domain.Employee;
import edu.iit.sat.itmd4515.agoyal18.domain.Invoice;
import edu.iit.sat.itmd4515.agoyal18.domain.security.User;
import edu.iit.sat.itmd4515.agoyal18.ejb.CustomerService;
import edu.iit.sat.itmd4515.agoyal18.ejb.DispatchService;
import edu.iit.sat.itmd4515.agoyal18.ejb.EmployeeService;
import edu.iit.sat.itmd4515.agoyal18.ejb.InvoiceService;
import edu.iit.sat.itmd4515.agoyal18.ejb.UserService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Admin Controller manages all Admin operations
 *
 * @author CG-DTE
 */
@Named
@RequestScoped
public class AdminPortalController extends BaseController {

    @EJB
    private EmployeeService employeeService;

    private static final Logger LOG = Logger.getLogger(AdminPortalController.class.getName());

    @EJB
    private CustomerService customerService;

    @EJB
    private UserService userService;

    @EJB
    private DispatchService dispatchService;

    @EJB
    private InvoiceService invoiceService;

    private Invoice invoice;
    private User user;
    private Customer customer;
    private Employee employee;
    private User user1;

    public AdminPortalController() {
    }

    public List allinvoices() {
        return invoiceService.findAll();
    }

    public int countforemployee(Employee e) {
        return dispatchService.findbyemployee(e).size();
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String doViewOrder(Invoice i) {
        this.invoice = i;
        return "/admin/viewInvoice.xhtml";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public String createcustomer() {
        try {
            userService.createnewuser(customer.getUser());

            customerService.create(customer);
        } catch (Exception e) {
            LOG.log(Level.INFO, "Creating a new user has encountered Error", e);

        }

        return "/";
    }

    public String createemployee() {
        try {
            userService.createnewuseremployee(employee.getUser());

            employeeService.create(employee);
        } catch (Exception e) {
            LOG.log(Level.INFO, "Creating a new user has encountered Error", e);

        }
        return "/";
    }

    @Override
    @PostConstruct
    public void postContruct() {
        super.postContruct();
        invoice = new Invoice();
        user = new User();
        customer = new Customer();
        customer.setUser(user);
        user1 = new User();
        employee = new Employee();
        employee.setUser(user1);

    }

}
