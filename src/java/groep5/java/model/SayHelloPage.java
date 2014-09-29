/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groep5.java.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Kenneth
 */
@ManagedBean
@RequestScoped
public class SayHelloPage {
    private String name;

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public String getSayHello() {
        return "success";
    }
}
