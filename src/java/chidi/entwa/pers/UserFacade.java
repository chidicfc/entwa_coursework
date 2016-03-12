/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.pers;

import chidi.entwa.ent.User;
import chidi.entwa.ent.Role;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    public void editUser(User user, String roleName) {
        TypedQuery<Role> r = em.createQuery("SELECT r FROM Role r WHERE r.roleName = :roleName", Role.class);
        Role role = r.setParameter("roleName", roleName.toUpperCase()).getSingleResult();

        User usr = find(user.getId());

        Role initialRole = usr.getRoles().get(0);
        //user.setPassword((usr.getPassword()));
        //user.setId(usr.getId());

        usr.setFirstName(user.getFirstName());
        usr.setLastName(user.getLastName());
        usr.setEmailAddress(user.getEmailAddress());
        usr.setUsername(user.getUsername());
        em.merge(usr);

        if (initialRole != role) {
            initialRole.getUsers().remove(usr);
            em.merge(initialRole);
            usr.getRoles().clear();
            role.getUsers().add(usr);
            usr.getRoles().add(role);
            em.merge(usr);
            em.merge(role);
        }

        //user.setPassword((usr.getPassword()));
    }

    public List<User> getAdminAndRegularUsers() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM  User u INNER JOIN u.roles r WHERE r.roleName in :roleNames ORDER BY UPPER(u.firstName)", User.class);
        List<String> names = Arrays.asList("ADMIN", "USER");
        query.setParameter("roleNames", names);
        return query.getResultList();
    }

    public List<User> searchAdminAndRegularUsersByName(String name) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM  User u INNER JOIN u.roles r WHERE r.roleName in :roleNames AND (UPPER(u.firstName) LIKE :firstName OR UPPER(u.lastName) LIKE :lastName) ORDER BY UPPER(u.firstName)", User.class);
        List<String> names = Arrays.asList("ADMIN", "USER");
        query.setParameter("roleNames", names);
        query.setParameter("firstName", "%" + name.toUpperCase() + "%");
        query.setParameter("lastName", "%" + name.toUpperCase() + "%");
        return query.getResultList();
    }
}
