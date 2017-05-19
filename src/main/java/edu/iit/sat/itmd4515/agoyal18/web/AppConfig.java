/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.agoyal18.web;

import java.util.TimeZone;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *This Controller converts time to system configuration.
 * @author CG-DTE
 */
@Named
@ApplicationScoped
public class AppConfig {

    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }

}
