package chidi.entwa.ent;

import chidi.entwa.ent.UserGroup;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-10T10:23:20")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> emailAddress;
    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, UserGroup> groups;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> username;

}