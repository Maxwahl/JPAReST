package at.htl.TestManagementReloaded.busines;

import at.htl.TestManagementReloaded.model.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;


    @Startup
    @Singleton

    public class InitBean {

        @PersistenceContext
        EntityManager em;

        @PostConstruct
        private void init() {
            System.err.print("******** hello");
            Teacher teacher = new Teacher("Hans", "Wurst", "t001", "4BHIF");
            Test test = new Test(LocalDate.now(), Subject.MATH, teacher);
            Pupil pA = new Pupil("Max", "Mustermann", "if150111", "4BHIF");
            Pupil pB = new Pupil("Max", "MusterB", "if150112", "4BHIF");
            TestGrade tGA = new TestGrade(pA, test, 1);
            TestGrade tGB = new TestGrade(pB, test, 2);
            em.persist(teacher);
            em.persist(test);
            em.persist(pA);
            em.persist(pB);
            em.persist(tGA);
            em.persist(tGB);

        }

    }