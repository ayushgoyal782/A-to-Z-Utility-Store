/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.domain;

import edu.iit.sat.itmd4515.agoyal18.domain.security.User;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author CG-DTE
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "select e from Employee e")
    ,
    @NamedQuery(name = "Employee.findByName", query = "select e from Employee e where e.fname = :fname")
    ,
    @NamedQuery(name = "Employee.findByUsername", query = "select e from Employee e where e.user.userName = :username")
})
public class Employee extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String fname;
    
    @NotNull
    private String lname;

    private String email;

    private Long contactno;

    private String address;
    
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    public Employee() {
    }

    public Employee(String fname, String lname, String email, Long contactno, String address) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.contactno = contactno;
        this.address = address;
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
        return "Employee{" + "id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", contactno=" + contactno + ", address=" + address + '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
     
}
