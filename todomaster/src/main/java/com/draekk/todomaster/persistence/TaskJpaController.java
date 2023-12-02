/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.draekk.todomaster.persistence;

import com.draekk.todomaster.models.Task;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.draekk.todomaster.models.User;
import com.draekk.todomaster.persistence.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author asahi
 */
public class TaskJpaController implements Serializable {

    public TaskJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public TaskJpaController(){
        emf = Persistence.createEntityManagerFactory("TodoMasterPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Task create(Task task) {
        EntityManager em = null;
        Task createdTask = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user = task.getUser();
            if (user != null) {
                user = em.getReference(user.getClass(), user.getId());
                task.setUser(user);
            }
            em.persist(task);
            em.flush();
            createdTask = em.find(Task.class, task.getId());
            if (user != null) {
                user.getTasks().add(task);
                user = em.merge(user);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return createdTask;
    }

    public void edit(Task task) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Task persistentTask = em.find(Task.class, task.getId());
            User userOld = persistentTask.getUser();
            User userNew = task.getUser();
            if (userNew != null) {
                userNew = em.getReference(userNew.getClass(), userNew.getId());
                task.setUser(userNew);
            }
            task = em.merge(task);
            if (userOld != null && !userOld.equals(userNew)) {
                userOld.getTasks().remove(task);
                userOld = em.merge(userOld);
            }
            if (userNew != null && !userNew.equals(userOld)) {
                userNew.getTasks().add(task);
                userNew = em.merge(userNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = task.getId();
                if (findTask(id) == null) {
                    throw new NonexistentEntityException("The task with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Task task;
            try {
                task = em.getReference(Task.class, id);
                task.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The task with id " + id + " no longer exists.", enfe);
            }
            User user = task.getUser();
            if (user != null) {
                user.getTasks().remove(task);
                user = em.merge(user);
            }
            em.remove(task);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Task> findTaskEntities() {
        return findTaskEntities(true, -1, -1);
    }

    public List<Task> findTaskEntities(int maxResults, int firstResult) {
        return findTaskEntities(false, maxResults, firstResult);
    }

    private List<Task> findTaskEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Task.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Task findTask(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Task.class, id);
        } finally {
            em.close();
        }
    }

    public int getTaskCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Task> rt = cq.from(Task.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
