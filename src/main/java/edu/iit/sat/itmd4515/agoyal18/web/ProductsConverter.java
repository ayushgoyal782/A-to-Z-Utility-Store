/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.web;

import edu.iit.sat.itmd4515.agoyal18.domain.Productdetails;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Productsconverter converts the product for view in page
 * @author CG-DTE
 */
@FacesConverter("productConverter")
public class ProductsConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(ProductsConverter.class.getName());

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LOG.info("Product converter getas string" + value);
        Long id = Long.parseLong(value);
        Productdetails pd= new Productdetails();
        return pd;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
     LOG.info("get as string" + value.toString());
     return String.valueOf(((Productdetails)value).getProductid());
       
    }

}
