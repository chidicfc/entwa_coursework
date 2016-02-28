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
import javax.persistence.Query;

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
        Query query = em.createQuery("SELECT p FROM ProjectIdea p WHERE UPPER(p.title) LIKE :title ORDER BY p.title");
        query.setParameter("title", "%" + title.toUpperCase() + "%");
        return query.getResultList();
    }
}
