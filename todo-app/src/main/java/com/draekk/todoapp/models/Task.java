
package com.draekk.todoapp.models;


import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private boolean isCompleted;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task() {
    }

    public Task(String description, boolean isCompleted, User user) {
        this.description = description;
        this.isCompleted = isCompleted;
        this.user = user;
    }

    public Task(long id, String description, boolean isCompleted, User user) {
        this.id = id;
        this.description = description;
        this.isCompleted = isCompleted;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
