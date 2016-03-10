/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.bus;

import chidi.entwa.ent.Organisation;
import chidi.entwa.ent.ProjectIdea;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author chidi
 */
@Stateless
public class AuthorizationService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public boolean isAdmin() {
        return getExternalContext().isUserInRole("ADMIN");
    }

    public boolean isSuperUser() {
        return false;
    }

    public boolean isOwnerOfProjectIdea(ProjectIdea projectIdea) {
        return false;
    }
    
    public boolean isOwnerOfOrganisation(Organisation organisation){
        return false;
    }
    
    public boolean isAnonymous(){
        return false;
    }
    
    public ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }
    
    public String getCurrentUser(){
        return getExternalContext().getRemoteUser();
    }
}
