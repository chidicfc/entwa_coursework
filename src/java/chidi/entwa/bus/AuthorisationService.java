/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.bus;

import chidi.entwa.ent.Organisation;
import chidi.entwa.ent.ProjectIdea;
import chidi.entwa.pers.OrganisationFacade;
import chidi.entwa.pers.ProjectIdeaFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author chidi
 */
@Stateless
public class AuthorisationService {

    @EJB
    private SessionService sessionService;

    @EJB
    private ProjectIdeaFacade projectIdeaFacade;
    
    @EJB
    private OrganisationFacade organisationFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public boolean isAdmin() {
        return sessionService.getExternalContext().isUserInRole("ADMIN");
    }

    public boolean isSuperUser() {
        return sessionService.getExternalContext().isUserInRole("SUPERUSER");
    }

    public boolean isOwnerOfProjectIdea(ProjectIdea projectIdea) {
        ProjectIdea p = projectIdeaFacade.find(projectIdea.getId());
        String createdBy;
        if (projectIdea.getCreatedBy() == null) {
            createdBy = p.getCreatedBy();
        } else {
            createdBy = projectIdea.getCreatedBy();
        }
        return createdBy.equals(sessionService.getCurrentUser());
    }

    public boolean isOwnerOfOrganisation(Organisation organisation) {  
        Organisation o = organisationFacade.find(organisation.getId());
        String createdBy;
        if (organisation.getCreatedBy() == null) {
            createdBy = o.getCreatedBy();
        } else {
            createdBy = organisation.getCreatedBy();
        }
        
        return createdBy.equals(sessionService.getCurrentUser());
    }

    public boolean isUser() {
        return sessionService.getExternalContext().isUserInRole("USER");
    }

    public boolean isAnonymous() {
        return sessionService.getExternalContext().isUserInRole("ANONYMOUS");
    }

    public boolean canModifyOrganisation(Organisation organisation) {
        boolean result = isSuperUser() || isAdmin() || isOwnerOfOrganisation(organisation);
        return result;
    }

    public boolean canModifyProjectIdea(ProjectIdea projectIdea) {
        boolean result = isSuperUser() || isAdmin() || isOwnerOfProjectIdea(projectIdea);
        return result;
    }

    public boolean canCreate() {
        boolean result = isSuperUser() || isAdmin() || isUser();
        return result;
    }
}
