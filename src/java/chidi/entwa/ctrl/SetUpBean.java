/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.ctrl;

import chidi.entwa.ent.User;
import chidi.entwa.pers.UserFacade;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author chidi
 */
@Named(value = "setUpBean")
@RequestScoped
public class SetUpBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private UserFacade userFacade;

    public SetUpBean() {
    }

    public String setTestData() {
        /**
         * Role role1 = new Role(); role1.setDescription("Regular users");
         * role1.setRoleName("USER");
         *
         * Role role2 = new Role(); role2.setDescription("Administrator");
         * role2.setRoleName("ADMIN");
         *
         * Role role3 = new Role(); role3.setDescription("Anonymous user");
         * role3.setRoleName("ANONYMOUS");
         *
         * Role role4 = new Role(); role4.setDescription("Super users");
         * role4.setRoleName("SUPER USER");
         *
         * roleFacade.create(role1); roleFacade.create(role2);
         * roleFacade.create(role3); roleFacade.create(role4);
         

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("john");
        user.setEmailAddress("john_doe@hotmail.com");
        user.setPassword("john");
        user.setConfirmPassword("john");
        userFacade.createUserInRole(user, "USER");

        User user1 = new User();
        user1.setFirstName("George");
        user1.setLastName("Castell");
        user1.setUsername("george");
        user1.setEmailAddress("george_castell@hotmail.com");
        user1.setPassword("george");
        user1.setConfirmPassword("george");
        userFacade.createUserInRole(user1, "USER");

        User user2 = new User();
        user2.setFirstName("Administrator");
        user2.setLastName("Alex");
        user2.setUsername("admin");
        user2.setEmailAddress("alex@hotmail.com");
        user2.setPassword("admin");
        user2.setConfirmPassword("admin");
        userFacade.createUserInRole(user2, "ADMIN");
        * 
        **/
        User user3 = new User();
        user3.setFirstName("Jim");
        user3.setLastName("Jim");
        user3.setUsername("jim1");
        user3.setEmailAddress("jim@hotmail.com");
        user3.setPassword("jim");
        user3.setConfirmPassword("jim");
        userFacade.createUserInRole(user3, "SUPERUSER");

        User user4 = new User();
        user4.setFirstName("Anonymous");
        user4.setLastName("Anonymous");
        user4.setUsername("anonymous1");
        user4.setEmailAddress("anonymous@hotmail.com");
        user4.setPassword("anonymous");
        user4.setConfirmPassword("anonymous");
        userFacade.createUserInRole(user4, "ANONYMOUS");

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Test Data created",
                        "Test Data created"));
        return "/admin/index.xhtml";
    }
}
