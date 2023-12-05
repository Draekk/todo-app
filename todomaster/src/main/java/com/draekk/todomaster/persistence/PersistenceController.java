
package com.draekk.todomaster.persistence;

import com.draekk.todomaster.models.Task;
import com.draekk.todomaster.models.User;
import com.draekk.todomaster.persistence.exceptions.NonexistentEntityException;
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

    public Task createTask(Task task) {

        return taskJC.create(task);

    }

    public List<Task> getTaskList() {
        return taskJC.findTaskEntities();
    }

    public void deleteTask(long id) throws NonexistentEntityException {
        taskJC.destroy(id);
    }
    
}
