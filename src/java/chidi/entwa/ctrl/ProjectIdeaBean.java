/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.ctrl;

import chidi.entwa.bus.ProjectIdeaService;
import chidi.entwa.ent.Organisation;
import chidi.entwa.ent.ProjectIdea;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
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
    
    private OrganisationBean organisationBean;

    private ProjectIdea projectIdea = new ProjectIdea();

    private Organisation organisation = new Organisation();

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
        if (organisation == null){
            organisation = organisationBean.getOrganisation();
        }
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

    public String doCreateProjectIdea() {
        projectIdeaService.addNewProjectIdea(projectIdea, organisation);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Project Idea created",
                        "The project" + projectIdea.getTitle() + " has been created"));

        return "submitAProjectIdea.xhtml";
    }

}
