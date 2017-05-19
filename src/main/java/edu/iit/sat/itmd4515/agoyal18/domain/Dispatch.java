/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author CG-DTE
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Dispatch.findAll", query = "select d from Dispatch d")
    ,
    @NamedQuery(name = "Dispatch.findByinvoice", query = "select d from Dispatch d where d.invoice = :invoice")
    ,
    @NamedQuery(name = "Dispatch.findByEmployee", query = "select d from Dispatch d where d.employee = :employee")

})
public class Dispatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Invoice invoice;

    @OneToOne
    private Employee employee;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.DATE)
    private Date deliverydate;

    private String shippingdetails;

    public Dispatch() {
    }

    public Dispatch(Invoice invoice, Employee employee, Date date, Date deliverydate, String shippingdetails) {

        this.invoice = invoice;
        this.employee = employee;
        this.date = date;
        this.deliverydate = deliverydate;
        this.shippingdetails = shippingdetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void ofInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void createdbyEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }

    public String getShippingdetails() {
        return shippingdetails;
    }

    public void setShippingdetails(String shippingdetails) {
        this.shippingdetails = shippingdetails;
    }

    @Override
    public String toString() {
        return "Dispatch{" + "id=" + id + ", date=" + date + ", deliverydate=" + deliverydate + ", shippingdetails=" + shippingdetails + '}';
    }

}
