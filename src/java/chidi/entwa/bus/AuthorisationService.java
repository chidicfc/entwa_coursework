/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.bus;

import chidi.entwa.ent.Organisation;
import chidi.entwa.ent.ProjectIdea;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author chidi
 */
@Stateless
public class AuthorisationService {
    @EJB
    private CurrentUserService currentUserService;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public boolean isAdmin() {
        return currentUserService.getExternalContext().isUserInRole("ADMIN");
    }

    public boolean isSuperUser() {
        return currentUserService.getExternalContext().isUserInRole("SUPERUSER");
    }

    public boolean isOwnerOfProjectIdea(ProjectIdea projectIdea) {
        return projectIdea.getCreatedBy().equals(currentUserService.getCurrentUser());
    }
    
    public boolean isOwnerOfOrganisation(Organisation organisation){
        return organisation.getCreatedBy().equals(currentUserService.getCurrentUser());
    }
    
    public boolean isAnonymous(){
        return currentUserService.getExternalContext().isUserInRole("ANONYMOUS");
    }
        
    public boolean canArchiveOrganisation(Organisation organisation){
        boolean result = isSuperUser() || isAdmin() || isOwnerOfOrganisation(organisation);
        return result;
    }
}
