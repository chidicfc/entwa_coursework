/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.ctrl;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chidi
 */
@Named(value = "logOutBean")
@RequestScoped
public class LogOutBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public LogOutBean() {
    }

    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);

        session.invalidate();

        return "index?faces-redirect=true";
    }
}
