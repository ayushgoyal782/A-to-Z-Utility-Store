/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.ejb;

import edu.iit.sat.itmd4515.agoyal18.domain.security.Group;
import edu.iit.sat.itmd4515.agoyal18.domain.security.User;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author CG-DTE
 */
@Startup
@Singleton
public class StartupBean {

    @EJB
    private GroupService groupService;

    @EJB
    private UserService userService;

    public StartupBean() {
    }

    @PostConstruct
    public void postContruct() {
        Group adminGroup = new Group("ADMINISTRATORS", "System administrator group");
        User adminUser = new User("admin", "admin");
        adminUser.addGroup(adminGroup);

        groupService.create(adminGroup);
        userService.create(adminUser);
    }
}
