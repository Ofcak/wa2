/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wa2.sem.services;

import cz.cvut.fel.glassfishjspmysql.SelectedDatabase;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;

/**
 *
 * @author Mira
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
        
    }

    private Session getEntityManager(){
        return SelectedDatabase.getHibernateSession();
        
    };

    public void create(T entity) {
        Session s = getEntityManager();
        s.persist(entity);
        s.flush();
        s.close();
        
    }

    public void edit(T entity) {
        Session s = getEntityManager();
        s.merge(entity);
        s.flush();
        s.close();
    }

    public void remove(T entity) {
        Session s = getEntityManager();
        s.delete(s.merge(entity));
        s.flush();
        s.close();
    }

    public T find(Serializable id) {
        Session s = getEntityManager();
        
        T entity = (T) s.get(entityClass, id);
        s.flush();
        s.close();
        
        return entity;
    }

    public List<T> findAll() {
        Session ses = getEntityManager();
        ses.createCriteria(entityClass).list();
        //javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        //cq.select(cq.from(entityClass));
        //return getEntityManager().createQuery(cq).getResultList();
        List<T> list =  ses.createCriteria(entityClass).list();
        ses.flush();
        ses.close();
        
        return list;
    }

    public List<T> findRange(int[] range) {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        cq.select(cq.from(entityClass));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        q.setMaxResults(range[1] - range[0] + 1);
//        q.setFirstResult(range[0]);
//        return q.getResultList();
        return null;
    }

    public int count() {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
//        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        return ((Long) q.getSingleResult()).intValue();
        return -1;
    }
    
}
