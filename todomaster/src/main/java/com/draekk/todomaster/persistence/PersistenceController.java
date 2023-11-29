
package com.draekk.todomaster.persistence;

import com.draekk.todomaster.models.User;

public class PersistenceController {
    
    private UserJpaController userJC = new UserJpaController();
    private TaskJpaController taskJC = new TaskJpaController();

    public void createUser(User user) {
        userJC.create(user);
    }
    
}
