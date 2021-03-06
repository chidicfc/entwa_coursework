/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.ctrl;

import chidi.entwa.ent.Organisation;
import chidi.entwa.ent.ProjectIdea;
import chidi.entwa.ent.Role;
import chidi.entwa.ent.User;
import chidi.entwa.pers.OrganisationFacade;
import chidi.entwa.pers.ProjectIdeaFacade;
import chidi.entwa.pers.UserFacade;
import chidi.entwa.pers.RoleFacade;
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

    @EJB
    private OrganisationFacade organisationFacade;

    @EJB
    private ProjectIdeaFacade projectIdeaFacade;

    @EJB
    private RoleFacade roleFacade;

    public SetUpBean() {
    }

    public String setTestData() {

        Role role1 = new Role();
        role1.setDescription("Regular users");
        role1.setRoleName("USER");

        Role role2 = new Role();
        role2.setDescription("Administrator");
        role2.setRoleName("ADMIN");

        Role role3 = new Role();
        role3.setDescription("Anonymous user");
        role3.setRoleName("ANONYMOUS");

        Role role4 = new Role();
        role4.setDescription("Super users");
        role4.setRoleName("SUPER USER");

        roleFacade.create(role1);
        roleFacade.create(role2);
        roleFacade.create(role3);
        roleFacade.create(role4);

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
        user2.setUsername("alex");
        user2.setEmailAddress("alex@hotmail.com");
        user2.setPassword("admin");
        user2.setConfirmPassword("admin");
        userFacade.createUserInRole(user2, "ADMIN");

        User user3 = new User();
        user3.setFirstName("Jim");
        user3.setLastName("Jim");
        user3.setUsername("jim");
        user3.setEmailAddress("jim@hotmail.com");
        user3.setPassword("jim");
        user3.setConfirmPassword("jim");
        userFacade.createUserInRole(user3,
                "SUPERUSER");

        User user4 = new User();
        user4.setFirstName("Anonymous");
        user4.setLastName("Anonymous");
        user4.setUsername("anonymous");
        user4.setEmailAddress("anonymous@hotmail.com");
        user4.setPassword("anonymous");
        user4.setConfirmPassword("anonymous");
        userFacade.createUserInRole(user4, "ANONYMOUS");

        Organisation organisation = new Organisation();
        organisation.setName("China Scholarship Council(CSC)");
        organisation.setPostalAddress("Level 13,Building A3 No.9 Chegongzhuang Avenue Beijing 100044 P.R.China");
        organisation.setPostCode("100044");
        organisation.setOutline("Sponsor Chinese study abroad");
        organisation.setContactName("Mr.Wei Zhao");
        organisation.setTelephoneNumber("0086-010-66091155");
        organisation.setEmailAddress("wzhao@yahoo.cn");
        organisation.setCreatedBy("john");

        Organisation organisation1 = new Organisation();
        organisation1.setName("UoP SoC Jonathan Crellin");
        organisation1.setPostalAddress("PO1 3HE");
        organisation1.setPostCode("PO1 3HE");
        organisation1.setOutline("HE Education");
        organisation1.setContactName("Jonathan Crellin");
        organisation1.setTelephoneNumber("02392846400");
        organisation1.setEmailAddress("jonathan@port.ac.uk");
        organisation1.setCreatedBy("george");

        Organisation organisation2 = new Organisation();
        organisation2.setName("(ICJS)(CAM)");
        organisation2.setPostalAddress("University of Portsmouth");
        organisation2.setPostCode("PO1 2UP");
        organisation2.setOutline("Higher Education");
        organisation2.setContactName("Victoria Wang");
        organisation2.setTelephoneNumber("02392843400");
        organisation2.setEmailAddress("vic@port.ac.uk");
        organisation2.setCreatedBy("george");

        Organisation organisation3 = new Organisation();
        organisation3.setName("Municipality of Pafos");
        organisation3.setPostalAddress("28th of October Square, P.O. Box 60032, Pafos, Cyprus");
        organisation3.setPostCode("8100");
        organisation3.setOutline("Improve the Services In Pafos");
        organisation3.setContactName("Christos Patsalides");
        organisation3.setTelephoneNumber("26822270");
        organisation3.setEmailAddress("town@pafos.org.cy");
        organisation3.setCreatedBy("john");

        organisationFacade.saveOrganisation(organisation);
        organisationFacade.saveOrganisation(organisation1);
        organisationFacade.saveOrganisation(organisation2);
        organisationFacade.saveOrganisation(organisation3);

        ProjectIdea projectIdea = new ProjectIdea();
        projectIdea.setTitle("Absolutely Pafos");
        projectIdea.setAimsAndObjectives("Evaluate approaches to parallel programming");
        projectIdea.setAnticipatedDeliverables("Android App");
        projectIdea.setAcademicQuestion("Prevalence, techniques, legislation, caselaw, review");
        projectIdea.setStudent("Aris Panayiotou");
        projectIdea.setStatus(ProjectIdea.ProjectIdeaState.ALLOCATED);
        projectIdea.setCreatedBy("george");
        Organisation projectOrg = organisationFacade.getOrganisation("Municipality of Pafos");

        ProjectIdea projectIdea1 = new ProjectIdea();
        projectIdea1.setTitle("IP Camera Hacking");
        projectIdea1.setAimsAndObjectives("Evaluate approaches to parallel programming");
        projectIdea1.setAcademicQuestion("Prevalence, techniques, legislation, caselaw, review");
        projectIdea1.setAnticipatedDeliverables("Report and Technical Report on the issue investigated");
        projectIdea1.setStudent("John Stewart");
        projectIdea1.setStatus(ProjectIdea.ProjectIdeaState.APPROVED);
        projectIdea1.setCreatedBy("john");
        Organisation projectOrg1 = organisationFacade.getOrganisation("UoP SoC Jonathan Crellin");

        ProjectIdea projectIdea2 = new ProjectIdea();
        projectIdea2.setTitle("Detecting Cyber security Anomalies in Large Smart Energy Meter Datasets");
        projectIdea2.setAimsAndObjectives("See \"Highlighted project idea\" on Moodle Masters Project (2015) site, under section on SWS 4.");
        projectIdea2.setAcademicQuestion("Prevalence, techniques, legislation, caselaw, review");
        projectIdea2.setAnticipatedDeliverables("Report and Technical Report on the issue investigated");
        projectIdea2.setStudent("Samuel Doe");
        projectIdea2.setStatus(ProjectIdea.ProjectIdeaState.APPROVED);
        projectIdea2.setCreatedBy("john");
        Organisation projectOrg2 = organisationFacade.getOrganisation("UoP SoC Jonathan Crellin");

        ProjectIdea projectIdea3 = new ProjectIdea();
        projectIdea3.setTitle("JavaScript Teaching Tool");
        projectIdea3.setAimsAndObjectives("Evaluate approaches to parallel programming");
        projectIdea3.setAnticipatedDeliverables("Android App");
        projectIdea3.setAcademicQuestion("Prevalence, techniques, legislation, caselaw, review");
        projectIdea3.setStudent("Aris Panayiotou");
        projectIdea3.setStatus(ProjectIdea.ProjectIdeaState.ALLOCATED);
        projectIdea3.setCreatedBy("george");
        Organisation projectOrg3 = organisationFacade.getOrganisation("Municipality of Pafos");

        projectIdeaFacade.createProject(projectIdea, projectOrg);
        projectIdeaFacade.createProject(projectIdea1, projectOrg1);
        projectIdeaFacade.createProject(projectIdea2, projectOrg2);
        projectIdeaFacade.createProject(projectIdea3, projectOrg3);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Test Data created",
                        "Test Data created"));
        return "/start.xhtml";
    }
}
