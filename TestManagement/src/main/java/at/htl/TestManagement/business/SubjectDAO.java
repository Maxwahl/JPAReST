package at.htl.TestManagement.business;

import at.htl.TestManagement.model.Subject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class SubjectDAO {
    @PersistenceContext
    EntityManager em;
    public List<Subject> get() {
        TypedQuery<Subject> entities = em.createNamedQuery("Subject.findAll",Subject.class);
        return  entities.getResultList();
    }

    public Subject get(long id) {
        TypedQuery<Subject> entities = em.createNamedQuery("Subject.findById",Subject.class);
        entities.setParameter("Id",id);
        return  entities.getSingleResult();
    }

    public void remove(Subject entity) {
        entity= em.merge(entity);
        em.remove(entity);
    }

    public Subject save(Subject entity) {
        entity=em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }

    public Subject update(Subject entity) {
        entity = em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }
}
