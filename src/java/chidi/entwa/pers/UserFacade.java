/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.pers;

import chidi.entwa.ent.User;
import chidi.entwa.ent.Role;
import java.util.ArrayList;
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
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "ProjectIdeasDatabasePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public void createUserInRole(User user, String roleName) {
        TypedQuery<Role> r = em.createQuery("SELECT r FROM Role r WHERE r.roleName = :roleName", Role.class);
        Role role = r.setParameter("roleName", roleName.toUpperCase()).getSingleResult();
        user.setPassword(User.md5Hash(user.getPassword()));
        role.getUsers().add(user);
        em.persist(role);
    }

}
