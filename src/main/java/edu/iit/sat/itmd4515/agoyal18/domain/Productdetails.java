/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author CG-DTE
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Productdetails.findAll", query = "select p from Productdetails p")
    ,
    @NamedQuery(name = "Productdetails.findBydesc", query = "select p from Productdetails p where p.productdescription = :productdescription")
})
public class Productdetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productid;

    @NotNull
    @Column(unique = true)
    private String productdescription;

    private int quantity;

    private Float price;

    private String image;

    public Productdetails() {
    }

    public Productdetails(String productdescription, int quantity, Float price, String image) {
        this.productdescription = productdescription;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
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

    /**
     * Get the value of productdescription
     *
     * @return the value of productdescription
     */
    public String getProductdescription() {
        return productdescription;
    }

    /**
     * Set the value of productdescription
     *
     * @param productdescription new value of productdescription
     */
    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Productdetails{" + "productdescription=" + productdescription + ", quantity=" + quantity + ", price=" + price + '}';
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
