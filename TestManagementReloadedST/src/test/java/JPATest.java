import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.*;
import javax.transaction.Transaction;
import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class JPATest {

    @PersistenceContext
    static EntityManagerFactory emFactory;
    static EntityManager em;

@BeforeClass
@Transactional
    public static void init(){

        emFactory= Persistence.createEntityManagerFactory("myPU");
        em = emFactory.createEntityManager();
    }
    @Test
    @Transactional
    public void readTest(){
        Person p= em.find(Person.class,1L);
        assertThat(p.getId(),is(1L));
    }

    @Test
    @Transactional
    public void updateTest(){

        Person p= em.find(Person.class,1L);
        p.setFirstName("Hans");
        em.merge(p);
        p = em.find(Person.class,1L);
        assertThat(p.getFirstName(),is("Hans"));
    }
    @Test
    public void insertAndDeleteTest(){
        EntityTransaction t = em.getTransaction();
        t.begin();
        Pupil p = new Pupil("Jan","Meier","if150114","4BHIF");
        p=em.merge(p);
        em.flush();
        em.refresh(p);
        t.commit();
        System.out.println(p.getId());
        em.remove(p);

        assertThat(em.find(Pupil.class,p.getId()),is(nullValue()));
    }
}
