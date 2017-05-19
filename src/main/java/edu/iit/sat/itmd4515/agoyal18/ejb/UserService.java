/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.ejb;

import edu.iit.sat.itmd4515.agoyal18.domain.security.Group;
import edu.iit.sat.itmd4515.agoyal18.domain.security.User;
import java.util.List;
import javax.ejb.Stateless;

/**
 *User services manages all CURD operations for User
 * @author CG-DTE
 */
@Stateless
public class UserService extends BaseService<User>{

    public UserService() {
        super(User.class);
    }

    @Override
    public List<User> findAll() {
            return getEntityManager().createNamedQuery("User.findAll", User.class).getResultList();
    }
     public void createnewuser(User user) {
         
        List<Group> groups = getEntityManager().createNamedQuery("Group.findAll", Group.class).getResultList();

        for (Group group : groups) {
            if (group.getGroupName().equals("CUSTOMERS")) {
                user.addGroup(group);
                getEntityManager().persist(user);
            }
        }
    }
     
     public void createnewuseremployee(User user) {
         
        List<Group> groups = getEntityManager().createNamedQuery("Group.findAll", Group.class).getResultList();

        for (Group group : groups) {
            if (group.getGroupName().equals("EMPLOYEES")) {
                user.addGroup(group);
                getEntityManager().persist(user);
            }
        }
    }

    
}
