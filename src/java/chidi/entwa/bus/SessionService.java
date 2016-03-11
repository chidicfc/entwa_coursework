/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.bus;

import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author chidi
 */
@Stateless
public class SessionService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }
    
    public String getCurrentUser(){
        return getExternalContext().getRemoteUser();
    }
}
