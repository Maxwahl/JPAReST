package at.htl.TestManagement.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedQueries({
        @NamedQuery(name = "Test.findAll",query = "SELECT t from Test t"),
        @NamedQuery(name = "Test.findById",query = "SELECT t from Test t where t.id = :Id"),
})
public class Test {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int grade;
    LocalDateTime dateHeld;
    @JsonbTransient
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Subject subject;
    @JsonbTransient
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Student student;

    //region getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public LocalDateTime getDateHeld() {
        return dateHeld;
    }

    public void setDateHeld(LocalDateTime dateHeld) {
        this.dateHeld = dateHeld;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    //endregion


    //region constructors
    public Test(int grade, LocalDateTime dateHeld, Subject subject, Student student) {
        this.grade = grade;
        this.dateHeld = dateHeld;
        this.subject = subject;
        this.student = student;
    }
    public Test() {
    }
    //endregion
}
