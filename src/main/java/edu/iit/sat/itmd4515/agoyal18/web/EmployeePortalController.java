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
import edu.iit.sat.itmd4515.agoyal18.domain.Productdetails;
import edu.iit.sat.itmd4515.agoyal18.ejb.DispatchService;
import edu.iit.sat.itmd4515.agoyal18.ejb.EmployeeService;
import edu.iit.sat.itmd4515.agoyal18.ejb.InvoiceService;
import edu.iit.sat.itmd4515.agoyal18.ejb.ProductService;
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
 *Employee Controller manages all Employee operations
 * @author CG-DTE
 */
@Named
@RequestScoped
public class EmployeePortalController extends BaseController {

    @EJB
    private ProductService productService;

    private static final Logger LOG = Logger.getLogger(EmployeePortalController.class.getName());

    @EJB
    private InvoiceService invoiceService;

    @EJB
    private DispatchService dispatchService;

    @EJB
    private EmployeeService employeeService;

    @Inject
    LoginController loginController;

    private Employee employee;
    private Customer customer;
    private Invoice invoice;
    private Dispatch dispatch;
    private Productdetails productdetails;

    public EmployeePortalController() {
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

    public Dispatch getDispatch() {
        return dispatch;
    }

    public void setDispatch(Dispatch dispatch) {
        this.dispatch = dispatch;
    }

    public Productdetails getProductdetails() {
        return productdetails;
    }

    public void setProductdetails(Productdetails productdetails) {
        this.productdetails = productdetails;
    }

    public String getInvoiceProductsForPage(Invoice i) {
        List<String> names = new ArrayList<>();
        for (Invoiceproducts ip : i.getInvoiceproducts()) {
            names.add(ip.toString());
        }
        return String.join(",", names);
    }

    public int findbyemployee(Employee employee) {
        return dispatchService.findbyemployee(employee).size();

    }

    public List getInvoiceForPage() {
        return invoiceService.findAll();
    }

    public String doViewOrder(Invoice i) {

        this.invoice = i;
        LOG.info("doViewInvoice: " + invoice.toString());

        return "/employee/viewInvoice.xhtml";
    }

    public String taketoProduct() {
        return "/employee/product.xhtml";
    }

    public String docreateProduct() {
        return "/employee/createProduct.xhtml";
    }

    public String doExecutenewProduct() {

        LOG.info("doExecutenewProduct: " + productdetails.toString());
        productService.create(productdetails);
        context.addMessage(null,
                new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        "Successfully Created!",
                        "Product:-" + productdetails.getProductdescription()
                )
        );
        return "/employee/product.xhtml";
    }

    public String doUpdateOrder(Invoice i) {
        this.invoice = i;
        LOG.info("doUpdateInvoice: " + invoice.toString());
        invoice.setInvoiceproducts(i.getInvoiceproducts());
        return "/employee/editInvoice.xhtml";

    }

    public String doExecuteupdate() {

        LOG.info("doExecuteInvoice: " + invoice.toString());
        dispatchService.updateforEmployee(invoice, employee);
        context.addMessage(null,
                new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        "Successful Update!",
                        "Order reviewed- Shipping Details:-  " + invoice.getDispatch().getShippingdetails()
                )
        );
        return "/employee/welcome.xhtml";
    }

    @Override
    @PostConstruct
    public void postContruct() {
        super.postContruct();
        employee = employeeService.findByUsername(loginController.getRemoteUser());
        invoice = new Invoice();
        dispatch = new Dispatch();
        productdetails = new Productdetails();
        invoice.setDispatch(dispatch);

    }

}
