/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.bus;

import chidi.entwa.ent.Organisation;
import chidi.entwa.pers.OrganisationFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author chidi
 */
@Stateless
public class OrganisationService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private OrganisationFacade organisationFacade;
    
    public void addNewOrganisation (Organisation organisation){
        organisationFacade.createOrganisation(organisation);
    }
    
    public List<Organisation> findAllOrganisations(){
        return organisationFacade.getAllActiveOrganisations();
    }
    
    public void editOrganisation (Organisation organisation){
        organisationFacade.editOrganisation(organisation);
    }
    
    public void archiveOrganisation (Organisation organisation) {
        organisationFacade.archiveOrganisation(organisation);
    }
}
