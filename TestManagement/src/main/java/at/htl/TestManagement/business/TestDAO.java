package at.htl.TestManagement.business;

import at.htl.TestManagement.model.Test;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class TestDAO {
    @PersistenceContext
    EntityManager em;

    public List<Test> get() {
        TypedQuery<Test> entities = em.createNamedQuery("Test.findAll",Test.class);
        return  entities.getResultList();
    }

    public Test get(long id) {
        TypedQuery<Test> entities = em.createNamedQuery("Test.findById",Test.class);
        entities.setParameter("Id",id);
        return  entities.getSingleResult();
    }

    public void remove(Test entity) {
        entity= em.merge(entity);
        em.remove(entity);
    }

    public Test save(Test entity) {
        entity=em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }

    public Test update(Test entity) {
        entity = em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }
}
