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
import edu.iit.sat.itmd4515.agoyal18.ejb.CustomerService;
import edu.iit.sat.itmd4515.agoyal18.ejb.DispatchService;
import edu.iit.sat.itmd4515.agoyal18.ejb.EmployeeService;
import edu.iit.sat.itmd4515.agoyal18.ejb.InvoiceService;
import edu.iit.sat.itmd4515.agoyal18.ejb.InvoiceproductsService;
import edu.iit.sat.itmd4515.agoyal18.ejb.ProductService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *Order Controller manages all Order related operations
 * @author CG-DTE
 */
@ManagedBean
@ViewScoped
public class OrderCreateController extends BaseController {

    @EJB
    private ProductService productService;

    @EJB
    private InvoiceproductsService invoiceproductsService;

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

    private static final Logger LOG = Logger.getLogger(OrderCreateController.class.getName());

    private Employee employee;
    private Customer customer;
    private Invoice invoice;
    private Dispatch dispatch;
    private Invoiceproducts invoiceproducts;
    private Productdetails productdetails;
    private List<Invoiceproducts> allInvoiceProducts;
    private Date d = new Date();
    private float total = 0;
    private int quantity;

    public OrderCreateController() {

    }

    private int q;

    /**
     * Get the value of q
     *
     * @return the value of q
     */
    public int getQ() {
        return q;
    }

    /**
     * Set the value of q
     *
     * @param q new value of q
     */
    public void setQ(int q) {
        this.q = q;
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

    public Invoiceproducts getInvoiceproducts() {
        return invoiceproducts;
    }

    public void setInvoiceproducts(Invoiceproducts invoiceproducts) {
        this.invoiceproducts = invoiceproducts;
    }

    public String cart() {
        return "/customer/cart.xhtml";
    }

    public void createInvoiceProduct(Productdetails p, int quantity) {
        this.quantity = quantity;
        Invoiceproducts ip = new Invoiceproducts();
        System.out.println("@@@@@@@@@@@@@" + allInvoiceProducts);
        System.out.println("Product from create page" + p.toString());
        System.out.println("quantity-" + quantity);
        this.productdetails = p;
        ip.setProductdetails(productdetails);
        ip.setQuantity(quantity);
        ip.setrate(p.getPrice());
        ip.settax((float) (0.14 * quantity * p.getPrice()));
        ip.setTotal((quantity * p.getPrice()) + ip.gettax());
        total = total + ip.getTotal();
        invoiceproductsService.create(ip);
        System.out.println("#############" + allInvoiceProducts);
        productdetails.setQuantity(productdetails.getQuantity() - quantity);
        productService.update(productdetails);
        allInvoiceProducts.add(ip);
        System.out.println("added list");
        System.out.println("when creating" + allInvoiceProducts);

    }

    public String createInvoice(String address) {
        System.out.println("Address from page:-" + address);
        invoice.setCustomer(customer);
        invoice.setDispatch(null);
        invoice.setDate(new Date());
        invoice.setInvoiceproducts(allInvoiceProducts);
        System.out.println("Products in Invoice" + allInvoiceProducts);
        invoice.setTotal(total);
        invoice.setpartyname(address);
        invoiceService.create(invoice);
        return "/customer/welcome.xhtml";

    }

    public void deleteFromAllInvoiceProducts(Invoiceproducts p) {
        System.out.println("Before Deleting from cart-" + allInvoiceProducts);
        total = total - p.getTotal();
        productdetails.setQuantity(productdetails.getQuantity() + quantity);
        productService.update(productdetails);
        allInvoiceProducts.remove(p);
        System.out.println("After Deleting from cart-" + allInvoiceProducts);

    }

    public Productdetails getProductdetails() {
        return productdetails;
    }

    public void setProductdetails(Productdetails productdetails) {
        this.productdetails = productdetails;
    }

    public List<Invoiceproducts> getAllInvoiceProducts() {
        return allInvoiceProducts;
    }

    public void setAllInvoiceProducts(List<Invoiceproducts> allInvoiceProducts) {
        this.allInvoiceProducts = allInvoiceProducts;
    }

    @Override
    @PostConstruct
    public void postContruct() {
        super.postContruct();
        customer = customerService.findByUsername(loginController.getRemoteUser());
        invoice = new Invoice();
        dispatch = new Dispatch();
        invoiceproducts = new Invoiceproducts();
        productdetails = new Productdetails();
        allInvoiceProducts = new ArrayList<>();

    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
