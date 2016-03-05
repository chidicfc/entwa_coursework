/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.ctrl;

import chidi.entwa.bus.OrganisationService;
import chidi.entwa.bus.ProjectIdeaService;
import chidi.entwa.ent.Organisation;
import chidi.entwa.ent.ProjectIdea;
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
@Named(value = "projectIdeaBean")
@RequestScoped
public class ProjectIdeaBean {

    /**
     * Creates a new instance of OrganisationBean
     */
    public ProjectIdeaBean() {
        this.organisation = new Organisation();
    }

    @EJB
    private ProjectIdeaService projectIdeaService;

    @EJB
    private OrganisationService organisationService;

    private OrganisationBean organisationBean;

    private ProjectIdea projectIdea = new ProjectIdea();

    private Organisation organisation = new Organisation();

    private List<ProjectIdea> approvedOrAllocatedProjectIdeas;

    private List<ProjectIdea> approvedButUnallocatedProjectIdeas;

    public OrganisationBean getOrganisationBean() {
        //FacesContext facesContext = FacesContext.getCurrentInstance();
        //OrganisationBean orgBean = (OrganisationBean) facesContext.getApplication().getELResolver().
        //getValue(facesContext.getELContext(), null, "organisationBean");
        //organisationBean = orgBean;
        return organisationBean;
    }

    public void setOrganisationBean(OrganisationBean organisationBean) {
        this.organisationBean = organisationBean;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public ProjectIdea getProjectIdea() {
        return projectIdea;
    }

    public void setProjectIdea(ProjectIdea projectIdea) {
        this.projectIdea = projectIdea;
    }

    public List<ProjectIdea> getApprovedOrAllocatedProjectIdeas() {
        if (approvedOrAllocatedProjectIdeas == null) {
            approvedOrAllocatedProjectIdeas = getAllApprovedOrAllocatedProjectIdeas();
        }
        return approvedOrAllocatedProjectIdeas;
    }

    public void setApprovedOrAllocatedProjectIdeas(List<ProjectIdea> approvedOrAllocatedProjectIdeas) {
        this.approvedOrAllocatedProjectIdeas = approvedOrAllocatedProjectIdeas;
    }

    public List<ProjectIdea> getApprovedButUnallocatedProjectIdeas() {
        if (approvedButUnallocatedProjectIdeas == null) {
            approvedButUnallocatedProjectIdeas = getAllApprovedButUnallocatedProjectIdeas();
        }
        return approvedButUnallocatedProjectIdeas;
    }

    public void setApprovedButUnallocatedProjectIdeas(List<ProjectIdea> approvedButUnallocatedProjectIdeas) {
        this.approvedButUnallocatedProjectIdeas = approvedButUnallocatedProjectIdeas;
    }

    public String doCreateProjectIdea(ProjectIdea projectIdea, Organisation organisation) {
        //organisation = organisationBean.getOrganisation();
        projectIdeaService.addNewProjectIdea(projectIdea, organisation);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Project Idea created",
                        "The project" + projectIdea.getTitle() + " has been created"));

        return "submitAProjectIdea.xhtml";
    }

    public String doViewProjectIdea(ProjectIdea projectIdea) {
        setProjectIdea(projectIdea);
        return "viewProjectIdea.xhtml";
    }

    public List<ProjectIdea> getAllApprovedOrAllocatedProjectIdeas() {
        return projectIdeaService.findAllApprovedOrAllocatedProjectIdeas();
    }

    public List<ProjectIdea> getAllApprovedButUnallocatedProjectIdeas() {
        return projectIdeaService.findAllApprovedButUnallocatedProjectIdeas();
    }

    public String doArchiveOrganisation(Organisation organisation, String targetPage) {
        organisationService.archiveOrganisation(organisation);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Organisation archived",
                        "The organisation" + organisation.getName() + " has been archived"));
        setApprovedButUnallocatedProjectIdeas(getAllApprovedButUnallocatedProjectIdeas());
        setApprovedOrAllocatedProjectIdeas(getAllApprovedOrAllocatedProjectIdeas());
        return targetPage;
    }

}
