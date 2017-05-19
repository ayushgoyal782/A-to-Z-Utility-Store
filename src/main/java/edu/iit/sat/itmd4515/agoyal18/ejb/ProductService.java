/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.ejb;

import edu.iit.sat.itmd4515.agoyal18.domain.Productdetails;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *Product services manages all CURD operations for Product
 * @author CG-DTE
 */
@Named
@Stateless
public class ProductService extends BaseService<Productdetails>{

    public ProductService() {
        super(Productdetails.class);
    }

    @Override
    public List<Productdetails> findAll() {
            return getEntityManager().createNamedQuery("Productdetails.findAll", Productdetails.class).getResultList();
    }
    
}
