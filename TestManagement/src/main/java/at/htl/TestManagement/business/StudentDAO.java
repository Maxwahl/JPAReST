package at.htl.TestManagement.business;

import at.htl.TestManagement.model.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class StudentDAO {

    @PersistenceContext
    EntityManager em;


    public List<Student> get() {
        TypedQuery<Student> entities = em.createNamedQuery("Student.findAll",Student.class);
        return  entities.getResultList();
    }

    public Student get(long id) {
        TypedQuery<Student> entities = em.createNamedQuery("Student.findById",Student.class);
        entities.setParameter("Id",id);
        System.err.println(entities.toString());
        return  entities.getSingleResult();
    }

    public void remove(Student entity) {
        entity= em.merge(entity);
        em.remove(entity);
    }

    public Student save(Student entity) {
        entity=em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }

    public Student update(Student entity) {
        entity = em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }
}
