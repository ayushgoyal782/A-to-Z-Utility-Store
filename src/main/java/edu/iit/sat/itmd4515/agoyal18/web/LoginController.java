/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 *Login Controller manages all Login operations
 * @author CG-DTE
 */
@Named
@RequestScoped
public class LoginController extends BaseController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    @NotNull(message = "You must enter a username.")
    private String username;
    @NotNull(message = "You must enter a password.")
    private String password;

    public LoginController() {
    }

    public String getRemoteUser() {
        return context.getExternalContext().getRemoteUser();
    }

    public boolean isAdmin() {
        return context.getExternalContext().isUserInRole("ADMIN_ROLE");
    }

    public boolean isCustomer() {
        return context.getExternalContext().isUserInRole("CUST_ROLE");
    }

    public boolean isEmployee() {
        return context.getExternalContext().isUserInRole("EMP_ROLE");
    }

    // action methods
    public String doLogin() {
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            req.login(username, password);
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed Login", "Username and/or Password was not valid.  But I'm not telling you which, because that would be bad security practice."));
            return "/login.xhtml";
        }

        return "/welcome.xhtml?faces-redirect=true";

    }

    public String doLogout() {
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            req.logout();
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed Logout", "I don't know why this would happen - but you have failed to logout."));
            return "/login.xhtml";
        }

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "You have logged out", "You have logged out successfully.  Please don't forget to close your browser."));
        return "/login.xhtml";
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
