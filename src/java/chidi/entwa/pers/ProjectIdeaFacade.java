/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.pers;

import chidi.entwa.ent.Organisation;
import chidi.entwa.ent.ProjectIdea;
import java.util.Date;
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

    public void createProjectIdea(ProjectIdea projectIdea, Organisation organisation) {
        Date date = new Date();
        projectIdea.setCreatedBy("Chidi Uba"); // change this
        projectIdea.setDateSubmitted(date);
        projectIdea.setLastUpdated(date);
        projectIdea.setOrganisation(organisation);
        create(projectIdea);
    }

    public void editProjectIdea(ProjectIdea projectIdea, Organisation organisation) {
        ProjectIdea p = find(projectIdea.getId());
        Date date = new Date();
        projectIdea.setCreatedBy("Chidi Uba");
        projectIdea.setDateSubmitted(p.getDateSubmitted());
        projectIdea.setLastUpdated(date);
        projectIdea.setOrganisation(organisation);
        edit(projectIdea);
    }

    public void deleteProjectIdea(ProjectIdea projectIdea) {
        remove(projectIdea);
    }

    public List<ProjectIdea> searchByTitle(String title) {
        TypedQuery<ProjectIdea> query = em.createQuery("SELECT p FROM ProjectIdea p WHERE UPPER(p.title) LIKE :title ORDER BY UPPER(p.title)", ProjectIdea.class);
        query.setParameter("title", "%" + title.toUpperCase() + "%");
        return query.getResultList();
    }

    public List<ProjectIdea> getAllApprovedButUnallocatedIdeas() {
        TypedQuery<ProjectIdea> query = em.createQuery("SELECT p from ProjectIdea p WHERE p.organisation.status = :organisationStatus AND p.status = :status ORDER BY UPPER(p.title)", ProjectIdea.class);
        query.setParameter("organisationStatus", Organisation.OrganisationState.ACTIVE);
        query.setParameter("status", ProjectIdea.ProjectIdeaState.APPROVED);
        return query.getResultList();
    }

    public List<ProjectIdea> getAllApprovedOrAllocatedIdeas() {
        TypedQuery<ProjectIdea> query = em.createQuery("SELECT p from ProjectIdea p WHERE p.organisation.status = :organisationStatus AND (p.status = :status1 OR p.status = :status2) ORDER BY UPPER(p.title)", ProjectIdea.class);
        query.setParameter("organisationStatus", Organisation.OrganisationState.ACTIVE);
        query.setParameter("status1", ProjectIdea.ProjectIdeaState.APPROVED);
        query.setParameter("status2", ProjectIdea.ProjectIdeaState.ALLOCATED);
        return query.getResultList();
    }
}
