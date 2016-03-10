/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.ctrl;

import chidi.entwa.bus.AuthorisationService;
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
    }

    @EJB
    private ProjectIdeaService projectIdeaService;

    @EJB
    private OrganisationService organisationService;

    @EJB
    private AuthorisationService authorisationService;

    private OrganisationBean organisationBean;

    private ProjectIdea projectIdea = new ProjectIdea();

    private Organisation organisation = new Organisation();

    private List<ProjectIdea> approvedOrAllocatedProjectIdeas;

    private List<ProjectIdea> approvedButUnallocatedProjectIdeas;

    private List<ProjectIdea> provisionalProjectIdeas;

    private List<ProjectIdea> archivedProjectIdeas;

    public OrganisationBean getOrganisationBean() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        OrganisationBean orgBean = (OrganisationBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "organisationBean");
        organisationBean = orgBean;
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

    public List<ProjectIdea> getProvisionalProjectIdeas() {
        if (provisionalProjectIdeas == null) {
            provisionalProjectIdeas = getAllProvisionalProjectIdeas();
        }
        return provisionalProjectIdeas;
    }

    public void setProvisionalProjectIdeas(List<ProjectIdea> provisionalProjectIdeas) {
        this.provisionalProjectIdeas = provisionalProjectIdeas;
    }

    public List<ProjectIdea> getArchivedProjectIdeas() {
        if (archivedProjectIdeas == null) {
            archivedProjectIdeas = getAllArchivedProjectIdeas();
        }
        return archivedProjectIdeas;
    }

    public void setArchivedProjectIdeas(List<ProjectIdea> archivedProjectIdeas) {
        this.archivedProjectIdeas = archivedProjectIdeas;
    }

    public String doCreateProjectIdea(ProjectIdea projectIdea, Organisation organisation) {
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

    public List<ProjectIdea> getAllProvisionalProjectIdeas() {
        return projectIdeaService.findAllProvisionalProjectIdeas();
    }

    public List<ProjectIdea> getAllArchivedProjectIdeas() {
        return projectIdeaService.findAllArchivedProjectIdeas();
    }

    public void errorMessage() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "You don't have permission to perfom this action",
                        "You don't have permission to perfom this action"));
    }

    public String doArchiveOrganisation(Organisation organisation, String targetPage) {
        if (authorisationService.canArchiveOrganisation(organisation)) {
            organisationService.archiveOrganisation(organisation);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Organisation archived",
                            "The organisation" + organisation.getName() + " has been archived"));
        } else {
            errorMessage();
        }

        setApprovedButUnallocatedProjectIdeas(getAllApprovedButUnallocatedProjectIdeas());
        setApprovedOrAllocatedProjectIdeas(getAllApprovedOrAllocatedProjectIdeas());
        setProvisionalProjectIdeas(getAllProvisionalProjectIdeas());
        setArchivedProjectIdeas(getAllArchivedProjectIdeas());

        return targetPage;
    }

    public String doUnarchiveOrganisation(Organisation organisation, String targetPage) {
        organisationService.unarchiveOrganisation(organisation);
        
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Organisation unrchived",
                        "The organisation" + organisation.getName() + " has been unarchived"));
        
        setApprovedButUnallocatedProjectIdeas(getAllApprovedButUnallocatedProjectIdeas());
        setApprovedOrAllocatedProjectIdeas(getAllApprovedOrAllocatedProjectIdeas());
        setProvisionalProjectIdeas(getAllProvisionalProjectIdeas());
        setArchivedProjectIdeas(getAllArchivedProjectIdeas());
        
        return targetPage;
    }

    public String doGetSelectedProjectIdea(ProjectIdea projectIdea, Organisation organisation) {
        setProjectIdea(projectIdea);
        getOrganisationBean().setOrganisation(organisation);
        return "submitAProjectIdea.xhtml";
    }

    public String doEditProjectIdea(ProjectIdea projectIdea, Organisation organisation) {
        projectIdeaService.editProjectIdea(projectIdea, organisation);
        
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Project Idea edited",
                        "The project" + projectIdea.getTitle() + " has been edited"));

        return "submitAProjectIdea.xhtml";
    }

    public String doDeleteProjectIdea(ProjectIdea projectIdea, String targetPage) {
        projectIdeaService.deleteProjectIdea(projectIdea);
        
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Project Idea deleted",
                        "The project has been edited"));
        
        setApprovedOrAllocatedProjectIdeas(getAllApprovedOrAllocatedProjectIdeas());
        setApprovedButUnallocatedProjectIdeas(getAllApprovedButUnallocatedProjectIdeas());
        setProvisionalProjectIdeas(getAllProvisionalProjectIdeas());
        setArchivedProjectIdeas(getAllArchivedProjectIdeas());
        
        return targetPage;
    }

    public String doSearchApprovedOrAllocatedProjectIdeasByTitle(String title) {
        List<ProjectIdea> projectIdeas = projectIdeaService.searchApprovedOrAllocatedProjectIdeasByTitle(title);
        setApprovedOrAllocatedProjectIdeas(projectIdeas);
        return "home.xhtml";
    }

    public String doSearchApprovedButUnallocatedProjectIdeasByTitle(String title) {
        List<ProjectIdea> projectIdeas = projectIdeaService.searchApprovedButUnallocatedProjectIdeasByTitle(title);
        setApprovedButUnallocatedProjectIdeas(projectIdeas);
        return "listApprovedIdea.xhtml";
    }

    public String doSearchProvisionalProjectIdeasByTitle(String title) {
        List<ProjectIdea> projectIdeas = projectIdeaService.searchProvisionalProjectIdeasByTitle(title);
        setProvisionalProjectIdeas(projectIdeas);
        return "listProvisionalIdea.xhtml";
    }

    public String doSearchArchivedProjectIdeasByTitle(String title) {
        List<ProjectIdea> projectIdeas = projectIdeaService.searchArchivedProjectIdeasByTitle(title);
        setArchivedProjectIdeas(projectIdeas);
        return "listArchivedIdea.xhtml";
    }

    public boolean geteVal() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("USER");
        // return eVal = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }

}
