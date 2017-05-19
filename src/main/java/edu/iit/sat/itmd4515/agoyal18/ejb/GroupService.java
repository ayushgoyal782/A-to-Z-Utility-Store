/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.ejb;

import edu.iit.sat.itmd4515.agoyal18.domain.security.Group;
import java.util.List;
import javax.ejb.Stateless;

/**
 *Group services manages all CURD operations for Group
 * @author CG-DTE
 */
@Stateless
public class GroupService extends BaseService<Group>{

    public GroupService() {
        super(Group.class);
    }
    
    @Override
    public List<Group> findAll() {
            return getEntityManager().createNamedQuery("Group.findAll", Group.class).getResultList();
    }
    
}
