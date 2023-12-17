package com.draekk.todomaster.models;

import com.draekk.todomaster.models.User;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-12-08T23:52:56", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Task.class)
public class Task_ { 

    public static volatile SingularAttribute<Task, String> description;
    public static volatile SingularAttribute<Task, Long> id;
    public static volatile SingularAttribute<Task, User> user;
    public static volatile SingularAttribute<Task, Boolean> isCompleted;

}