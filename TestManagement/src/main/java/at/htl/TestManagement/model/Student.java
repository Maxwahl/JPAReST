package at.htl.TestManagement.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Student.findAll",query = "SELECT st from Student st"),
        @NamedQuery(name = "Student.findById",query = "SELECT st from Student st where st.id = :Id"),
})
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "student",fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH})
    Set<Test> tests;

    //region constructors
    public Student(String name) {
        this.name = name;
    }

    public Student() {
    }
    //endregion


    //region getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }
    //endregion
}
