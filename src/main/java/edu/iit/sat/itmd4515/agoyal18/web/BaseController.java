/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.web;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 *
 * @author CG-DTE
 */
public abstract class BaseController {

    protected FacesContext context;
    
    protected BaseController() {
    }
    
    @PostConstruct
    public void postContruct(){
        context = FacesContext.getCurrentInstance();
    }
    
}
