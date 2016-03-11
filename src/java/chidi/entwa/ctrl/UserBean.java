/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.ctrl;

import chidi.entwa.bus.UserService;
import chidi.entwa.ent.User;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author chidi
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public UserBean() {
    }

    @EJB
    private UserService userService;

    private User user = new User();
    
    private List<User> users;
    
    private String roleName;
    
    private String searchString;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        if (users == null){
            users = getAdminAndRegularUsers();
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
    
    public String doCreateUser(User user, String roleName) {
        if (!(user.getPassword().equals(user.getConfirmPassword()))) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Passwords don't match",
                            "Passwords don't match"));
            return "/admin/manageUser.xhtml";
        }
        userService.createUserInRole(user, roleName);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "User created",
                            "The user" + user.getUsername() + " has been created"));

        return "/admin/manageUser.xhtml";
    }
    
    public List<User> getAdminAndRegularUsers(){
        return userService.getAdminAndRegularUsers();
    }
    
    public String doSearchAdminAndRegularUsersBy(String name){
        List<User> searchedUsers = userService.searchAdminAndRegularUsersBy(name);
        setUsers(searchedUsers);
        return "index.xhtml";
    }
}
