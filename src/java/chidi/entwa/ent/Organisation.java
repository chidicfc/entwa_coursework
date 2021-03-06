/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.ent;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author chidi
 */
@Entity
public class Organisation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private String postalAddress;

    @Column(nullable = false)
    @NotNull
    private String postCode;

    private String outline;

    @Column(nullable = false)
    @NotNull
    private String contactName;

    @Column(nullable = false)
    @NotNull
    private String emailAddress;

    @Column(nullable = false)
    @NotNull
    private String telephoneNumber;

    @OneToMany(mappedBy = "organisation")
    private Set<ProjectIdea> projectIdeas = new HashSet<>();

    public enum OrganisationState {
        ACTIVE, ARCHIVED
    }

    @Column(nullable = false)
    @NotNull
    private OrganisationState status;

    @Column(nullable = false)
    @NotNull
    private String createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Set<ProjectIdea> getProjectIdeas() {
        return projectIdeas;
    }

    public void setProjectIdeas(Set<ProjectIdea> projectIdeas) {
        this.projectIdeas = projectIdeas;
    }

    public OrganisationState getStatus() {
        return status;
    }

    public void setStatus(OrganisationState status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organisation)) {
            return false;
        }
        Organisation other = (Organisation) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        //return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
        return (name + " [" + contactName + "]" + " (" + id + ")");
    }

}
