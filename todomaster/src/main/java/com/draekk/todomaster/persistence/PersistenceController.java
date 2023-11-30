
package com.draekk.todomaster.persistence;

import com.draekk.todomaster.models.User;
import java.util.List;

public class PersistenceController {
    
    private UserJpaController userJC = new UserJpaController();
    private TaskJpaController taskJC = new TaskJpaController();

    public void createUser(User user) {
        userJC.create(user);
    }
    
    public List<User> getUserList(){
        return userJC.findUserEntities();
    }
    
}
