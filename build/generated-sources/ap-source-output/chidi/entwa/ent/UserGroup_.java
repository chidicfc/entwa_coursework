package chidi.entwa.ent;

import chidi.entwa.ent.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-10T10:23:20")
@StaticMetamodel(UserGroup.class)
public class UserGroup_ { 

    public static volatile SingularAttribute<UserGroup, String> groupName;
    public static volatile SingularAttribute<UserGroup, String> description;
    public static volatile SingularAttribute<UserGroup, Long> id;
    public static volatile ListAttribute<UserGroup, User> users;

}