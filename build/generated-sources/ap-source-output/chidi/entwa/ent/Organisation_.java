package chidi.entwa.ent;

import chidi.entwa.ent.Organisation.OrganisationState;
import chidi.entwa.ent.ProjectIdea;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-14T18:56:51")
@StaticMetamodel(Organisation.class)
public class Organisation_ { 

    public static volatile SetAttribute<Organisation, ProjectIdea> projectIdeas;
    public static volatile SingularAttribute<Organisation, String> outline;
    public static volatile SingularAttribute<Organisation, String> emailAddress;
    public static volatile SingularAttribute<Organisation, String> postalAddress;
    public static volatile SingularAttribute<Organisation, String> telephoneNumber;
    public static volatile SingularAttribute<Organisation, String> createdBy;
    public static volatile SingularAttribute<Organisation, String> contactName;
    public static volatile SingularAttribute<Organisation, String> name;
    public static volatile SingularAttribute<Organisation, String> postCode;
    public static volatile SingularAttribute<Organisation, Long> id;
    public static volatile SingularAttribute<Organisation, OrganisationState> status;

}