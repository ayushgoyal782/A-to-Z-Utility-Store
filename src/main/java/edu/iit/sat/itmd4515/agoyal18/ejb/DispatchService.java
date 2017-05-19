/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.ejb;

import edu.iit.sat.itmd4515.agoyal18.domain.Dispatch;
import edu.iit.sat.itmd4515.agoyal18.domain.Employee;
import edu.iit.sat.itmd4515.agoyal18.domain.Invoice;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 *Dispatch services manages all CURD operations for Dispatch
 * @author CG-DTE
 */
@Stateless
public class DispatchService extends BaseService<Dispatch> {

    public DispatchService() {
        super(Dispatch.class);
    }

    public DispatchService(Class entityClass) {
        super(entityClass);
    }

    @Override
    public List<Dispatch> findAll() {
       return getEntityManager().createNamedQuery("Dispatch.findAll", Dispatch.class).getResultList();
  
    }

    public List<Dispatch> findbyemployee(Employee employee) {
        return getEntityManager().createNamedQuery("Dispatch.findByEmployee", Dispatch.class)
                .setParameter("employee", employee)
                .getResultList();
 
    }
    public void updateforEmployee(Invoice dataFromJSF, Employee employee) {

        Invoice OldData = getEntityManager().find(Invoice.class, dataFromJSF.getSno());

        dataFromJSF.setpartyname(OldData.getpartyname());
        dataFromJSF.setCustomer(OldData.getCustomer());
        dataFromJSF.setInvoiceproducts(OldData.getInvoiceproducts());
        dataFromJSF.setDate(OldData.getDate());

        if (OldData.getDispatch()== null) {
            System.out.println("**********null******" + dataFromJSF.getDispatch().toString());
            Dispatch d = new Dispatch(OldData, employee, new Date(), dataFromJSF.getDispatch().getDeliverydate(), dataFromJSF.getDispatch().getShippingdetails());
            super.create(d);
            System.out.println("*****If part*******" + d.toString());
            dataFromJSF.setDispatch(d);

        } else {
            dataFromJSF.getDispatch().setDate(new Date());
            dataFromJSF.getDispatch().setEmployee(employee);
            dataFromJSF.getDispatch().setInvoice(dataFromJSF);
            dataFromJSF.setDispatch(dataFromJSF.getDispatch());
            Dispatch d1 = OldData.getDispatch();
            remove(d1);
        }

        getEntityManager().merge(dataFromJSF);

    }

    public Dispatch findByInvoice(Invoice invoice) {
        return getEntityManager().createNamedQuery("Dispatch.findByinvoice", Dispatch.class)
                .setParameter("invoice", invoice)
                .getSingleResult();

    }

}
