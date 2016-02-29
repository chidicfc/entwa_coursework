/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.ent;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author chidi
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findByTitle", query = "SELECT p FROM ProjectIdea p WHERE UPPER(p.title) LIKE :title ORDER BY p.title"),
    @NamedQuery(name = "getAllApprovedButUnallocated", query = "SELECT p from ProjectIdea p WHERE p.status = :status ORDER BY p.title"),
    @NamedQuery(name = "getAllApprovedOrAllocated", query = "SELECT p from ProjectIdea p WHERE p.status = :status1 OR p.status = :status2 ORDER BY p.title")
})
@Table(name = "project_idea")
public class ProjectIdea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @NotNull
    private String title;
    @Column(nullable = false)
    @NotNull
    private String aimsAndObjectives;
    @Column(nullable = false)
    @NotNull
    private String academicQuestion;
    @Column(name = "deliverables", nullable = false)
    @NotNull
    private String anticipatedDeliverables;
    @Column(name = "studentName")
    private String student;
    public enum ProjectIdeaState {
        PROVISIONAL, APPROVED, ALLOCATED, WITHDRAWN
    }
    @Column(nullable = false)
    @NotNull
    private ProjectIdeaState status;
    private String createdBy;
    @Column(nullable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSubmitted;
    @Column(nullable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;
    @ManyToOne
    private Organisation organisation;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAimsAndObjectives() {
        return aimsAndObjectives;
    }

    public void setAimsAndObjectives(String aimsAndObjectives) {
        this.aimsAndObjectives = aimsAndObjectives;
    }

    public String getAcademicQuestion() {
        return academicQuestion;
    }

    public void setAcademicQuestion(String academicQuestion) {
        this.academicQuestion = academicQuestion;
    }

    public String getAnticipatedDeliverables() {
        return anticipatedDeliverables;
    }

    public void setAnticipatedDeliverables(String anticipatedDeliverables) {
        this.anticipatedDeliverables = anticipatedDeliverables;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public ProjectIdeaState getStatus() {
        return status;
    }

    public void setStatus(ProjectIdeaState status) {
        this.status = status;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
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
        if (!(object instanceof ProjectIdea)) {
            return false;
        }
        ProjectIdea other = (ProjectIdea) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "chidi.entwa.ent.ProjectIdea[ id=" + id + " ]";
    }
    
}
