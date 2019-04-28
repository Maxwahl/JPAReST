package at.htl.TestManagementReloaded.model;

import javax.persistence.*;
import java.util.Set;
@Entity
@NamedQueries({
        @NamedQuery(name = "Pupil.findById",query = "select p from Pupil p where p.id= :Id"),
        @NamedQuery(name = "Pupil.findAll",query = "select p from Pupil p")
})
public class Pupil extends Person {

    private String matNr;

    private String className;


    @OneToMany(mappedBy = "pupil",fetch = FetchType.EAGER)
    private Set<TestGrade> testGrades;

    public Pupil(String firstName, String lastName, String matNr, String className) {
        super(firstName, lastName);
        this.matNr = matNr;
        this.className = className;
    }

    public Pupil() {
    }

    public String getMatNr() {
        return matNr;
    }

    public void setMatNr(String matNr) {
        this.matNr = matNr;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    public Set<TestGrade> getTestGrades() {
        return testGrades;
    }

    public void setTestGrades(Set<TestGrade> testGrades) {
        this.testGrades = testGrades;
    }


}
