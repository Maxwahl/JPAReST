package at.htl.TestManagementReloaded.busines;

import at.htl.TestManagementReloaded.model.Pupil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class PupilFacade {

    @PersistenceContext
    EntityManager em;


    public List<Pupil> get() {
        TypedQuery<Pupil> entities = em.createNamedQuery("Pupil.findAll",Pupil.class);
        return  entities.getResultList();
    }

    public Pupil get(long id) {
        TypedQuery<Pupil> entities = em.createNamedQuery("Pupil.findById",Pupil.class);
        entities.setParameter("Id",id);
        System.err.println(entities.toString());
        return  entities.getSingleResult();
    }

    public void remove(Pupil entity) {
        entity= em.merge(entity);
        em.remove(entity);
    }

    public Pupil save(Pupil entity) {
        entity=em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }

    public Pupil update(Pupil entity) {
        entity = em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }
}