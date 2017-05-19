/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

/**
 *
 * @author CG-DTE
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "select i from Invoice i")
    ,
    @NamedQuery(name = "Invoice.findByuserName", query = "select i from Invoice i where i.customer.user.userName = :customer")
    ,
    @NamedQuery(name = "Invoice.findByName", query = "select i from Invoice i where i.partyname = :name")
    ,
    @NamedQuery(name = "Invoice.findBynull", query = "select i from Invoice i where i.dispatch=null")

})
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    @Past
    @Temporal(TemporalType.DATE)
    private Date date;

    //@NotNull(message = "Party name cannot be null")
    private String partyname;

    private float total;

    @ManyToOne
    private Customer customer;

    @OneToMany
    private List<Invoiceproducts> invoiceproducts = new ArrayList<>();

    
    @OneToOne
    private Dispatch dispatch;

    public Invoice(Long sno) {
        this.sno = sno;
    }

    public void addInvoiceproducts(Invoiceproducts invoiceproducts) {
        this.invoiceproducts.add(invoiceproducts);
    }
    /**
     * Get the value of invoiceproducts
     *
     * @return the value of invoiceproducts
     */
    public List<Invoiceproducts> getInvoiceproducts() {
        return this.invoiceproducts;
    }
    
    /**
     * Set the value of invoiceproducts
     *
     * @param invoiceproducts new value of invoiceproducts
     */
    public void setInvoiceproducts(List<Invoiceproducts> invoiceproducts) {
        this.invoiceproducts = invoiceproducts;
    }

    public Invoice() {
    }

    public Invoice(Date date, String partyname, float total) {
        this.date = date;
        this.partyname = partyname;
        this.total = total;
    }

    /**
     * Get the value of customer
     *
     * @return the value of customer
     */
    public Customer getCustomer() {
        return customer;
    }

    public void createdbyCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Set the value of customer
     *
     * @param customer new value of customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Get the value of total
     *
     * @return the value of total
     */
    public float getTotal() {
        return total;
    }

    /**
     * Set the value of total
     *
     * @param total new value of total
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * Get the value of partyname
     *
     * @return the value of partyname
     */
    public String getpartyname() {
        return partyname;
    }

    /**
     * Set the value of partyname
     *
     * @param partyname new value of partyname
     */
    public void setpartyname(String partyname) {
        this.partyname = partyname;
    }

    /**
     * Get the value of date
     *
     * @return the value of date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the value of date
     *
     * @param date new value of date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    public Long getSno() {
        return sno;
    }

    public void setSno(Long sno) {
        this.sno = sno;
    }

  
    public Dispatch getDispatch() {
        return dispatch;
    }

    public void setDispatch(Dispatch dispatch) {
        this.dispatch = dispatch;
    }

    @Override
    public String toString() {
        return "Invoice{" + "sno=" + sno + ", date=" + date + ", partyname=" + partyname + ", total=" + total + ", customer=" + customer + ", invoiceproducts=" + invoiceproducts + ", dispatch=" + dispatch + '}';
    }

}
