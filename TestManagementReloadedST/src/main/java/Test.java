import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Test.findById",query = "select t from Test t where t.id= :Id"),
        @NamedQuery(name = "Test.findAll",query = "select t from Test t")
})
public class Test {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateHeld;
    @Enumerated(value = EnumType.STRING)
    private Subject subject;

    @ManyToOne(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
    private Teacher holder;


    @OneToMany(mappedBy = "test")
    private Set<TestGrade> testGrades;
    public Test(LocalDate dateHeld, Subject subject, Teacher holder) {
        this.dateHeld = dateHeld;
        this.subject = subject;
        this.holder = holder;
    }

    public Test() {
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateHeld() {
        return dateHeld;
    }

    public void setDateHeld(LocalDate dateHeld) {
        this.dateHeld = dateHeld;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getHolder() {
        return holder;
    }

    public void setHolder(Teacher holder) {
        this.holder = holder;
    }

}


