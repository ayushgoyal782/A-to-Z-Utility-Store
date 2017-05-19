/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.ejb;

import edu.iit.sat.itmd4515.agoyal18.domain.Dispatch;
import edu.iit.sat.itmd4515.agoyal18.domain.Invoice;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *Invoice services manages all CURD operations for Invoice
 * @author CG-DTE
 */
@Stateless
public class InvoiceService extends BaseService<Invoice> {

    @EJB
    private DispatchService dispatchService;

    public InvoiceService() {
        super(Invoice.class);
    }

    public InvoiceService(Class entityClass) {
        super(entityClass);
    }

    @Override
    public List<Invoice> findAll() {
        return getEntityManager().createNamedQuery("Invoice.findAll", Invoice.class).getResultList();
    }

    public List<Invoice> findnull() {
        return getEntityManager().createNamedQuery("Invoice.findBynull", Invoice.class).getResultList();
    }

    @Override
    public void update(Invoice dataFromJSF) {

        Invoice OldData = getEntityManager().find(Invoice.class, dataFromJSF.getSno());

        dataFromJSF.setInvoiceproducts(OldData.getInvoiceproducts());
        dataFromJSF.setDate(OldData.getDate());
        dataFromJSF.setDispatch(OldData.getDispatch());

        getEntityManager().merge(dataFromJSF);
    }

    public void updateforEmployee(Invoice dataFromJSF) {

        Invoice OldData = getEntityManager().find(Invoice.class, dataFromJSF.getSno());
        dataFromJSF.setpartyname(OldData.getpartyname());
        dataFromJSF.setCustomer(OldData.getCustomer());
        dataFromJSF.setInvoiceproducts(OldData.getInvoiceproducts());
        dataFromJSF.setDate(OldData.getDate());

        if (OldData.getDispatch() == null) {
            System.out.println("******************null null null nul nulll" + dataFromJSF.getDispatch().toString());
            Dispatch d = new Dispatch();
            getEntityManager().persist(d);
            dispatchService.create(d);
            System.out.println("******************" + d.toString());
            d.setDate(OldData.getDispatch().getDate());
            d.setDeliverydate(OldData.getDispatch().getDeliverydate());
            d.setInvoice(OldData);
            d.setShippingdetails(dataFromJSF.getDispatch().getShippingdetails());
        }
        OldData.getDispatch().setShippingdetails(dataFromJSF.getDispatch().getShippingdetails());
        dataFromJSF.setDispatch(OldData.getDispatch());

        getEntityManager().merge(dataFromJSF);
    }

    @Override
    public void remove(Invoice i) {

        // first thing I will do is get a reference - because p MAY NOT be managed
        // coming from the JSF layer
        i = getEntityManager().getReference(Invoice.class, i.getSno());
        
        // next the orchestra
        super.remove(i); //To change body of generated methods, choose Tools | Templates
    }

}
