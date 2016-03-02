/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.ctrl;

import chidi.entwa.ent.ContactPerson;
import chidi.entwa.ent.Organisation;
import chidi.entwa.pers.OrganisationFacade;
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
    private OrganisationFacade organisationFacade;
    private Organisation organisation = new Organisation();
    private ContactPerson contactPerson = new ContactPerson();

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public String doCreateOrganisation() {
        System.out.println("in here");
        organisationFacade.createOrganisation(organisation, contactPerson);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Organisation created",
                        "The organisation" + organisation.getName() + " has been created"));

        return "submitAProjectIdea.xhtml";
    }

}
