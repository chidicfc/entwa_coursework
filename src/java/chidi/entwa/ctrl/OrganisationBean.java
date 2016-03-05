/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.ctrl;

import chidi.entwa.bus.OrganisationService;
import chidi.entwa.ent.Organisation;
import chidi.entwa.pers.OrganisationFacade;
import java.util.List;
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

    @EJB
    private OrganisationFacade organisationFacade;

    private Organisation organisation = new Organisation();

    private List<Organisation> organisations;

    private List<Organisation> archivedOrganisations;

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public List<Organisation> getOrganisations() {
        if (organisations == null) {
            organisations = getAllActiveOrganisations();
        }
        return organisations;
    }

    public void setOrganisations(List<Organisation> organisations) {
        this.organisations = organisations;
    }

    public List<Organisation> getArchivedOrganisations() {
        if (archivedOrganisations == null) {
            archivedOrganisations = getAllArchivedOrganisations();
        }
        return archivedOrganisations;
    }

    public void setArchivedOrganisations(List<Organisation> archivedOrganisations) {
        this.archivedOrganisations = archivedOrganisations;
    }

    public String doCreateOrganisation() {
        organisationService.addNewOrganisation(organisation);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Organisation created",
                        "The organisation" + organisation.getName() + " has been created"));

        return "submitAProjectIdea.xhtml";
    }

    public List<Organisation> getAllActiveOrganisations() {
        return organisationService.findAllActiveOrganisations();
    }

    public List<Organisation> getAllArchivedOrganisations() {
        return organisationService.findAllArchivedOrganisations();
    }

    public OrganisationFacade getFacade() {
        return organisationFacade;
    }

    public String doGetOrganisation() {
        return "submitAProjectIdea.xhtml";
    }

    public String doEditOrganisation() {
        organisationService.editOrganisation(organisation);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Organisation edited",
                        "The organisation" + organisation.getName() + " has been edited"));

        return "submitAProjectIdea.xhtml";
    }

    public String doArchiveOrganisation(Organisation organisation, String targetPage) {
        organisationService.archiveOrganisation(organisation);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Organisation archived",
                        "The organisation" + organisation.getName() + " has been archived"));
        setOrganisations(getAllActiveOrganisations());
        return targetPage;
    }

    public String doUnarchiveOrganisation(Organisation organisation, String targetPage) {
        organisationService.unarchiveOrganisation(organisation);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Organisation unarchived",
                        "The organisation" + organisation.getName() + " has been unarchived"));
        setArchivedOrganisations(getAllArchivedOrganisations());
        return targetPage;
    }

    public String doGetSelectedOrganisation(Organisation organisation) {
        setOrganisation(organisation);
        return "submitAProjectIdea.xhtml";
    }

    public String doViewOrganisation(Organisation organisation) {
        setOrganisation(organisation);
        return "viewOrganisation.xhtml";
    }

}
