/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.domain;

import javax.persistence.MappedSuperclass;

/**
 *
 * @author CG-DTE
 */
@MappedSuperclass
public class NamedEntity extends BaseEntity {

    /**
     *
     */
    protected String name;

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

}
