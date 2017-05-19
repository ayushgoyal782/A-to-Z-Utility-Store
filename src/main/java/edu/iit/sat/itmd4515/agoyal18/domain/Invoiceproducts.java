/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;


/**
 *
 * @author CG-DTE
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Invoiceproducts.findAll", query = "select i from Invoiceproducts i")
    ,
    @NamedQuery(name = "Invoiceproducts.findByName", query = "select i from Invoiceproducts i where i.Sno = :sno")
})
public class Invoiceproducts {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Sno;
    
    
    private int quantity;
    
    private float rate;

    private float tax;
    @Min(1)
    private float total;
    
    @OneToOne
    private Productdetails productdetails;

    public Invoiceproducts() {
         }

        
    public void addProductdetails(Productdetails productdetails){
        this.productdetails=(productdetails);
      
    } 

    

    public Invoiceproducts(int quantity, float rate, float tax, float total) {
        this.quantity = quantity;
        this.rate = rate;
        this.tax = tax;
        this.total = total;
    }
    

    @Override
    public String toString() {
        return ("Sno " + Sno +productdetails+", quantity=" + quantity + ", rate=" + rate + ", tax=" + tax + ", total=" + total );
    }
    
    
    /**
     * Get the value of productdetails
     *
     * @return the value of productdetails
     */
    public Productdetails getProductdetails() {
        return productdetails;
    }

    /**
     * Set the value of productdetails
     *
     * @param productdetails new value of productdetails
     */
    public void setProductdetails(Productdetails productdetails) {
        this.productdetails = productdetails;
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
     * Get the value of tax
     *
     * @return the value of tax
     */
    public float gettax() {
        return tax;
    }

    /**
     * Set the value of tax
     *
     * @param tax new value of tax
     */
    public void settax(float tax) {
        this.tax = tax;
    }

    
    /**
     * Get the value of rate
     *
     * @return the value of rate
     */
    public float getrate() {
        return rate;
    }

    /**
     * Set the value of rate
     *
     * @param rate new value of rate
     */
    public void setrate(float rate) {
        this.rate = rate;
    }


    /**
     * Get the value of quantity
     *
     * @return the value of quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the value of quantity
     *
     * @param quantity new value of quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getSno() {
        return Sno;
    }

    public void setSno(Long Sno) {
        this.Sno = Sno;
    }

}
