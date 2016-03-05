/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.bus;

import chidi.entwa.ent.Organisation;
import chidi.entwa.ent.ProjectIdea;
import chidi.entwa.pers.ProjectIdeaFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author chidi
 */
@Stateless
public class ProjectIdeaService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private ProjectIdeaFacade projectIdeaFacade;

    public void addNewProjectIdea(ProjectIdea projectIdea, Organisation organisation) {
        projectIdeaFacade.createProjectIdea(projectIdea, organisation);
    }
    
    public List<ProjectIdea> findAllProjectIdeas(){
        return projectIdeaFacade.getAllApprovedOrAllocatedIdeas();
    }
}
