/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.pers;

import chidi.entwa.ent.ProjectIdea;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author chidi
 */
@Stateless
public class ProjectIdeaFacade extends AbstractFacade<ProjectIdea> {

    @PersistenceContext(unitName = "ProjectIdeasDatabasePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectIdeaFacade() {
        super(ProjectIdea.class);
    }
    
    public List<ProjectIdea> findByTitle(String title) {
        TypedQuery<ProjectIdea> query = em.createQuery("findByTitle", ProjectIdea.class);
        query.setParameter("title", "%" + title.toUpperCase() + "%");
        return query.getResultList();
    }
    
    public List<ProjectIdea> getAllApprovedButUnallocatedIdeas(){
        TypedQuery<ProjectIdea> query = em.createQuery("getAllApprovedButUnallocated", ProjectIdea.class);
        query.setParameter("status", ProjectIdea.ProjectIdeaState.APPROVED);
        return query.getResultList();
    }
    
    public List<ProjectIdea> getAllApprovedOrAllocatedIdeas(){
        TypedQuery<ProjectIdea> query = em.createQuery("getAllApprovedOrAllocated", ProjectIdea.class);
        query.setParameter("status1", ProjectIdea.ProjectIdeaState.APPROVED);
        query.setParameter("status2", ProjectIdea.ProjectIdeaState.ALLOCATED);
        return query.getResultList();
    }
}
