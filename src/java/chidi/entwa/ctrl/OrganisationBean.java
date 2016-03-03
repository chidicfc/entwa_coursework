/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.ctrl;

import chidi.entwa.bus.OrganisationService;
import chidi.entwa.ent.Organisation;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author chidi
 */
@Named(value = "organisationBean")
@RequestScoped
public class OrganisationBean {

    /**
     * Creates a new instance of OrganisationBean
     */
    public OrganisationBean() {
    }

    @EJB
    private OrganisationService organisationService;
    private Organisation organisation = new Organisation();

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public String doCreateOrganisation() {
        organisationService.addNewOrganisation(organisation);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Organisation created",
                        "The organisation" + organisation.getName() + " has been created"));

        return "submitAProjectIdea.xhtml";
    }
    
    

}
