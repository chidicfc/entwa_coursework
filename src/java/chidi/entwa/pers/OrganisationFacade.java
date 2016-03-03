/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.pers;

import chidi.entwa.ent.Organisation;
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
public class OrganisationFacade extends AbstractFacade<Organisation> {

    @PersistenceContext(unitName = "ProjectIdeasDatabasePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrganisationFacade() {
        super(Organisation.class);
    }
    
    public Organisation createOrganisation(Organisation organisation) {
        organisation.setStatus(Organisation.OrganisationState.ACTIVE);
        organisation.setCreatedBy("Chidi Uba");
        em.persist(organisation);
        return organisation;
    }
    
    public List<Organisation> searchAllActiveOrganisationsByName(String name) {
        TypedQuery<Organisation> query = em.createQuery("findByNameAndStatus", Organisation.class);
        query.setParameter("name", "%" + name.toUpperCase() + "%");
        query.setParameter("status", Organisation.OrganisationState.ACTIVE);
        return query.getResultList();
    }
    
    public List<Organisation> searchAllArchivedOrganisationsByName(String name) {
        TypedQuery<Organisation> query = em.createQuery("findByNameAndStatus", Organisation.class);
        query.setParameter("name", "%" + name.toUpperCase() + "%");
        query.setParameter("status", Organisation.OrganisationState.ARCHIVED);
        return query.getResultList();
    }
    
    public List<Organisation> getAllActiveOrganisations(){
        TypedQuery<Organisation> query = em.createQuery("SELECT o FROM Organisation o WHERE o.status = :status ORDER BY o.name", Organisation.class);
        query.setParameter("status", Organisation.OrganisationState.ACTIVE);
        return query.getResultList();
    }
    
    public List<Organisation> getAllArchivedOrganisations(){
        TypedQuery<Organisation> query = em.createQuery("getAllByStatus", Organisation.class);
        query.setParameter("status", Organisation.OrganisationState.ARCHIVED);
        return query.getResultList();
    }
    
}
