package chidi.entwa.ent;

import chidi.entwa.ent.Organisation;
import chidi.entwa.ent.ProjectIdea.ProjectIdeaState;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-09T19:16:31")
@StaticMetamodel(ProjectIdea.class)
public class ProjectIdea_ { 

    public static volatile SingularAttribute<ProjectIdea, Date> lastUpdated;
    public static volatile SingularAttribute<ProjectIdea, String> aimsAndObjectives;
    public static volatile SingularAttribute<ProjectIdea, String> student;
    public static volatile SingularAttribute<ProjectIdea, String> createdBy;
    public static volatile SingularAttribute<ProjectIdea, Organisation> organisation;
    public static volatile SingularAttribute<ProjectIdea, Long> id;
    public static volatile SingularAttribute<ProjectIdea, String> title;
    public static volatile SingularAttribute<ProjectIdea, String> academicQuestion;
    public static volatile SingularAttribute<ProjectIdea, Date> dateSubmitted;
    public static volatile SingularAttribute<ProjectIdea, String> anticipatedDeliverables;
    public static volatile SingularAttribute<ProjectIdea, ProjectIdeaState> status;

}