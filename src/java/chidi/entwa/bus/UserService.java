/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.bus;

import chidi.entwa.ent.User;
import chidi.entwa.pers.UserFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.EJB;

/**
 *
 * @author chidi
 */
@Stateless
public class UserService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private UserFacade userFacade;
    
    public void createUserInRole(User user, String roleName) {
        userFacade.createUserInRole(user, roleName);
    }
    
    public List<User> getAdminAndRegularUsers() {
        return userFacade.getAdminAndRegularUsers();
    }
    
    public List<User> searchAdminAndRegularUsersBy(String name) {
        if (name == null || name.equals("")) {
            return getAdminAndRegularUsers();
        }
        return userFacade.searchAdminAndRegularUsersByName(name);
    }
    
    public void editUser(User user, String roleName) {
        userFacade.editUser(user, roleName);
    }
}
