
package com.draekk.todoapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String name;
        private String lastName;
        private String email;
        private String username;
        private String password;
        @OneToMany(mappedBy = "user")
        private List<Task> taskList;

        public User() {
        }

        public User(String name, String lastName, String email, String username, String password, List<Task> taskList) {
                this.name = name;
                this.lastName = lastName;
                this.email = email;
                this.username = username;
                this.password = password;
                this.taskList = taskList;
        }

        public User(long id, String name, String lastName, String email, String username, String password, List<Task> taskList) {
                this.id = id;
                this.name = name;
                this.lastName = lastName;
                this.email = email;
                this.username = username;
                this.password = password;
                this.taskList = taskList;
        }

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public List<Task> getTaskList() {
                return taskList;
        }

        public void setTaskList(List<Task> taskList) {
                this.taskList = taskList;
        }
        
        
}
