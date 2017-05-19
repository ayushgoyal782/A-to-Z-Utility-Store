/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.ejb;

import edu.iit.sat.itmd4515.agoyal18.domain.Invoiceproducts;
import java.util.List;
import javax.ejb.Stateless;

/**
 *Invoiceproducts services manages all CURD operations for Invoiceproducts
 * @author CG-DTE
 */
@Stateless
public class InvoiceproductsService extends BaseService<Invoiceproducts>{

    public InvoiceproductsService() {
         super(Invoiceproducts.class);
    }

    
    public InvoiceproductsService(Class entityClass) {
        super(entityClass);
    }

    @Override
    public List<Invoiceproducts> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
