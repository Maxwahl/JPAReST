package at.htl.TestManagement.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Subject.findAll",query = "SELECT s from Subject s"),
        @NamedQuery(name = "Subject.findById",query = "SELECT s from Subject s where s.id = :Id"),
})
public class Subject {
    @Id @GeneratedValue
    private Long id;

    private String title;

    @OneToMany(mappedBy = "subject",fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH})
    Set<Test> tests;


    //region constructors
    public Subject(String title) {
        this.title = title;
        tests = new HashSet<>();

    }

    public Subject() {
    }
    //endregion


    //region getters and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }
    //endregion
}
