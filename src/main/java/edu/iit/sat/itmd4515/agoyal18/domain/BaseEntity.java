/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.domain;

import java.util.Date;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author CG-DTE
 */
@MappedSuperclass
public class BaseEntity {

   
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /**
     *
     */
    @PreUpdate
    @PrePersist
    public void doCreatedDate() {
        createdDate = new Date();
    }

   
    /**
     *
     * @return
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     *
     * @param createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
