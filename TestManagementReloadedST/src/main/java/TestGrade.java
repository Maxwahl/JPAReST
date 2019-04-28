import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Entity
public class TestGrade {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonbTransient
    private Pupil pupil;

    @ManyToOne
    @JsonbTransient
    private Test test;

    private int grade;

    public TestGrade(Pupil pupil, Test test, int grade) {
        this.pupil = pupil;

        this.test = test;
        this.grade = grade;
    }

    public TestGrade() {
    }

    public Pupil getPupil() {
        return pupil;
    }

    public void setPupil(Pupil pupil) {
        this.pupil = pupil;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
