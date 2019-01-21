package at.htl.TestManagement.business;

import at.htl.TestManagement.model.Student;
import at.htl.TestManagement.model.Subject;
import at.htl.TestManagement.model.Test;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Startup
@Singleton
public class InitBean {
    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void init(){
        System.err.println("++++++++");
        Subject subject = new Subject("NVS");
        Student student = new Student("Max Wahl");
        Test test = new Test(1, LocalDateTime.now(),subject,student);
        em.persist(student);
        em.persist(subject);
        em.persist(test);

    }


}
