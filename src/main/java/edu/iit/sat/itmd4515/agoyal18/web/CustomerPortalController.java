/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.web;

import edu.iit.sat.itmd4515.agoyal18.domain.Customer;
import edu.iit.sat.itmd4515.agoyal18.domain.Dispatch;
import edu.iit.sat.itmd4515.agoyal18.domain.Employee;
import edu.iit.sat.itmd4515.agoyal18.domain.Invoice;
import edu.iit.sat.itmd4515.agoyal18.domain.Invoiceproducts;
import edu.iit.sat.itmd4515.agoyal18.ejb.CustomerService;
import edu.iit.sat.itmd4515.agoyal18.ejb.DispatchService;
import edu.iit.sat.itmd4515.agoyal18.ejb.EmployeeService;
import edu.iit.sat.itmd4515.agoyal18.ejb.InvoiceService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *Customer Controller manages all Customer operations
 * @author CG-DTE
 */
@Named
@RequestScoped
public class CustomerPortalController extends BaseController {

    @EJB
    private DispatchService dispatchService;

    @EJB
    private InvoiceService invoiceService;

    @EJB
    private CustomerService customerService;

    @EJB
    private EmployeeService employeeService;

    @Inject
    LoginController loginController;

    private static final Logger LOG = Logger.getLogger(CustomerPortalController.class.getName());

    private Employee employee;
    private Customer customer;
    private Invoice invoice;
    private Dispatch dispatch;

    public CustomerPortalController() {
    }

    public String getInvoiceProductsForPage(Invoice i) {
        List<String> names = new ArrayList<>();
        for (Invoiceproducts ip : i.getInvoiceproducts()) {
            names.add(ip.toString());
        }
        return String.join(",", names);
    }

    /**
     * Get the value of employee
     *
     * @return the value of employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Set the value of employee
     *
     * @param employee new value of employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String doViewOrder(Invoice i) {

        this.invoice = i;
        LOG.info("doViewInvoice: " + invoice.toString());

        return "/customer/viewInvoice.xhtml";
    }

    public String doUpdateOrder(Invoice i) {
        this.invoice = i;
        LOG.info("doUpdateInvoice: " + invoice.toString());
        //invoice.setCustomer(customer);
        invoice.setInvoiceproducts(i.getInvoiceproducts());
        return "/customer/editInvoice.xhtml";

    }

    public String doExecuteupdate() {
        invoice.setCustomer(customer);
        LOG.info("doExecuteInvoice: " + invoice.toString());
        invoiceService.update(invoice);
        context.addMessage(null,
                new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        "Successful Update!",
                        "Updated Order " + invoice.getpartyname()
                )
        );
        return "/customer/welcome.xhtml";
    }

    public String doDeleteOrder(Invoice i) {
        this.invoice=i;
        this.dispatch = i.getDispatch();                //new code checking
        if (i.getDispatch() != null) {
            System.out.println("null nahi hai");
            dispatch=dispatchService.findByInvoice(i);
            dispatch.setInvoice(null);
            System.out.println("" + dispatch.toString());
            dispatchService.update(dispatch);
            

        }
        invoiceService.remove(i);
        if (i.getDispatch() != null) {
                dispatchService.remove(dispatch);
        }
        context.addMessage(null,
                new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        "Successfully Deleted!",
                        "Invoice:-" + invoice.getSno()+"......Please Refresh the page!!!!!!"
                )
        );
                
        return "/customer/welcome.xhtml";
    }

    public String doCreateOrder() {
        return "/customer/createInvoice.xhtml";
    }

    @Override
    @PostConstruct
    public void postContruct() {
        super.postContruct();
        customer = customerService.findByUsername(loginController.getRemoteUser());
        invoice = new Invoice();
        dispatch = new Dispatch();
    }

    public Dispatch getDispatch() {
        return dispatch;
    }

    public void setDispatch(Dispatch dispatch) {
        this.dispatch = dispatch;
    }
}
