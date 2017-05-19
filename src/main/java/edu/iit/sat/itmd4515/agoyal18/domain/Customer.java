/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.domain;

import edu.iit.sat.itmd4515.agoyal18.domain.security.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author CG-DTE
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "select c from Customer c")
    ,
    @NamedQuery(name = "Customer.findByName", query = "select c from Customer c where c.fname = :fname")
    ,
    @NamedQuery(name = "Customer.findByUsername", query = "select c from Customer c where c.user.userName = :username")

})
public class Customer extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    
    @NotNull
    private String fname;
    
    @NotNull
    private String lname;

    private String email;

    private Long contactno;

    private String address;

    @OneToMany(mappedBy = "Customer")
    private List<Invoice> invoice = new ArrayList<>();
    

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    /**
     * Get the value of invoice
     *
     * @return the value of invoice
     */
	 
    public List<Invoice> getInvoice() {
        return invoice;
    }
        
    /**
     * Set the value of invoice
     *
     * @param invoice new value of invoice
     */
    
    public void setInvoice(List<Invoice> invoice) {
        this.invoice = invoice;
    }
	
     public void addInvoice(Invoice invoice) {
        this.invoice.add(invoice);
        invoice.setCustomer(this);
    }
    
    
    public Customer() {
    }

    public Customer(String fname, String lname, String email, Long contactno, String address) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.contactno = contactno;
        this.address = address;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getContactno() {
        return contactno;
    }
    public void setContactno(Long contactno) {
        this.contactno = contactno;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" + "Id=" + Id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", contactno=" + contactno + ", address=" + address + '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
    
}
